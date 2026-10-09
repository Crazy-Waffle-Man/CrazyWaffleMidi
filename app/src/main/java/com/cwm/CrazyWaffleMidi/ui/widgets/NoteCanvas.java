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
    private boolean snapEnabled = true;
    
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
            painter.fillRect(new QRectF(x + 1, y + 1, Math.max(1, w - x - 2), PianoRollViewState.pixelsPerSemitone - 2), new QColor(74, 170, 135));
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
        MOVE,
        RESIZE,
        SELECT
    }

    private EditMode mode = EditMode.IDLE;
    private long startTick;
    private long durationTicks;
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
            }
        //TODO: move/resize notes
        }
    }

    @Override
    protected void mouseMoveEvent(@Nullable QMouseEvent event) {
        if (mode == EditMode.IDLE) {
            return;
        }
       switch (mode) {
        case EditMode.CREATE:
            final double x = event.position().x();
            final double y = event.position().y();
            final long endTick = PianoRollViewState.getTick(x);
            durationTicks = Math.max(endTick - startTick, 1); // The note must have *some* length
            pitch = PianoRollViewState.getPitch(y);
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
                //If only I could set the state machine note information to null... Alas...
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
