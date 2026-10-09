package com.cwm.CrazyWaffleMidi.ui.widgets;

import com.cwm.CrazyWaffleMidi.midi.MidiNote;
import com.cwm.CrazyWaffleMidi.midi.MidiNoteSequence;
import com.cwm.CrazyWaffleMidi.ui.midi.PianoRollViewState;

import io.qt.Nullable;
import io.qt.core.QRectF;
import io.qt.core.Qt;
import io.qt.gui.QColor;
import io.qt.gui.QMouseEvent;
import io.qt.gui.QPaintEvent;
import io.qt.gui.QPainter;
import io.qt.gui.QPen;
import io.qt.widgets.QWidget;

public class NoteCanvas extends QWidget {
    public final MidiNoteSequence notes;
    private boolean snapEnabled = false; //TODO: Adjust the numbers so that this doesn't break things when set to true
    
    public NoteCanvas(MidiNoteSequence notes) {
        this.notes = notes;
    }

    @Override
    protected void paintEvent(@Nullable QPaintEvent event) {
        QPainter painter = new QPainter(this);
        try {
            drawBackground(painter);
            drawGrid(painter);
            drawNotes(painter);
            drawPreviewNote(painter);
        } finally {
            painter.end();
        }
    }

    private void drawBackground(QPainter painter) {
        painter.fillRect(rect(), new QColor(30, 32, 38));
    }
    private void drawGrid(QPainter painter) {
        // final double rowHeight = PianoRollViewState.pixelsPerSemitone;
        for (int pitch = 0; pitch <= 127; pitch++) {
            double y = PianoRollViewState.getY(pitch);
            painter.setPen(new QPen(pitch % 12 == 0 ? new QColor(80, 84, 95) : new QColor(49, 52, 61)));
            painter.drawLine(0, (int) y, width(), (int) y);
        }

        final double beatWidth = PianoRollViewState.pixelsPerQuarter;
        for (double x = 0; x < width(); x += beatWidth) {
            painter.setPen(new QPen(new QColor(60, 64, 74)));
            painter.drawLine((int) x, 0, (int) x, height());
        }
    }
    private void drawNotes(QPainter painter) {
        for (MidiNote note : notes) {
            final double x = PianoRollViewState.getX(note.startTick());
            final double y = PianoRollViewState.getY(note.pitch());
            final double w = PianoRollViewState.getX(note.endTick());
            if (selectedNotes != null) {
                painter.fillRect(
                    new QRectF(x + 1, y + 1, Math.max(1, w - x - 2), PianoRollViewState.pixelsPerSemitone - 2), 
                    selectedNotes.contains(note)
                        ? new QColor(198, 135, 189) 
                        : new QColor(74, 170, 135)
                );
            } else {
                painter.fillRect(new QRectF(x + 1, y + 1, Math.max(1, w - x - 2), PianoRollViewState.pixelsPerSemitone - 2), new QColor(74, 170, 135));
            }
        }
    }
    private void drawPreviewNote(QPainter painter) {
        if (mode == EditMode.IDLE) {
            return;
        }
        final double x = PianoRollViewState.getX(startTick);
        final double y = PianoRollViewState.getY(pitch);
        final double w = PianoRollViewState.getX(startTick + durationTicks);
        painter.fillRect(new QRectF(x + 1, y + 1, Math.max(1, w - x - 2), PianoRollViewState.pixelsPerSemitone - 2), new QColor(14, 110, 75));
    }

    public static long snapTick(long tick, long interval) {
        return Math.round((double) tick / interval) * interval;
    }

    public static enum EditMode {
        IDLE,
        CREATE,
        MOVE, // Drop notes back in where ID matches TODO: implement
        RESIZE, // TODO: implement
        SELECT // TODO: fix
    }

    private EditMode mode = EditMode.IDLE;
    private long startTick;
    private long durationTicks;
    private double pressX;
    private double pressY;
    private MidiNoteSequence selectedNotes;
    private int pitch;
    private long snapInterval = PianoRollViewState.ticksPerQuarter;

    @Override
    protected void mousePressEvent(@Nullable QMouseEvent event) {
        if (event.button() == Qt.MouseButton.LeftButton) {
            final double x = event.position().x();
            final double y = event.position().y();
            MidiNoteSequence hits = findNotesAt(x, y); 
            if (hits.isEmpty()) { // Create note if there isn't one here
                pitch = PianoRollViewState.getPitch(y);
                if (pitch < 0 || pitch > 127) {
                    return;
                }
                startTick = snapEnabled? snapTick(PianoRollViewState.getTick(x), snapInterval) : PianoRollViewState.getTick(x);
                durationTicks = 1;
                mode = EditMode.CREATE;
                update();
                return;
            }
        } else if (event.button() == Qt.MouseButton.RightButton) {
            pressX = event.position().x();
            pressY = event.position().y();
            mode = EditMode.SELECT;
            return;
        }
    }

    @Override
    protected void mouseMoveEvent(@Nullable QMouseEvent event) {
       switch (mode) {
        case EditMode.IDLE:
            return;
        case EditMode.CREATE:
            final double x = event.position().x();
            final double y = event.position().y();
            final long endTick = snapEnabled? snapTick(PianoRollViewState.getTick(x), snapInterval) : PianoRollViewState.getTick(x);
            final long delta = endTick - startTick;
            durationTicks = Math.max(delta, 1); // The note must have *some* length
            pitch = PianoRollViewState.getPitch(y);
            update();
            break;
        default:
            break;
       }
    }

    @Override
    protected void mouseReleaseEvent(@Nullable QMouseEvent event) {
        if (event.button() != Qt.MouseButton.LeftButton) {
            return;
        }
        switch (mode) {
            case EditMode.CREATE:
                notes.addMidiNote(pitch, startTick, durationTicks, 64);
                mode = EditMode.IDLE;
                update();
                //If only I could set the state machine note information to null... Alas...
                break;
            case EditMode.SELECT:
                final double x = event.position().x();
                final double y = event.position().y();
                QRectF rect = new QRectF(pressX, pressY, x, y);
                for (MidiNote note : notes) {
                    if (!rect.contains(PianoRollViewState.getX(note.startTick()), PianoRollViewState.getY(note.pitch()))) {
                        continue;
                    }
                    selectedNotes.add(note);
                }
                update();
                break;
            default:
                break;
        }
    }

    public MidiNoteSequence findNotesAt(final double x, final double y) {
        MidiNoteSequence sequence = new MidiNoteSequence();
        for (MidiNote note : notes) {
            final double noteTop = PianoRollViewState.getY(note.pitch());
            final double noteBottom = noteTop + PianoRollViewState.pixelsPerSemitone;
            final double noteStart = PianoRollViewState.getX(note.startTick());
            final double noteEnd = noteStart + PianoRollViewState.getX(note.durationTicks());
             if (x < noteStart || x > noteEnd || y < noteTop || y > noteBottom) {
                continue; // There's no note here.
            }
            sequence.add(note);
        }
        return sequence;
    }
}
