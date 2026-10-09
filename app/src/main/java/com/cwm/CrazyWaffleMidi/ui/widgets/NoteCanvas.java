package com.cwm.CrazyWaffleMidi.ui.widgets;

import com.cwm.CrazyWaffleMidi.midi.MidiNote;
import com.cwm.CrazyWaffleMidi.midi.MidiNoteSequence;
import com.cwm.CrazyWaffleMidi.ui.midi.PianoRollViewState;

import io.qt.Nullable;
import io.qt.core.QRectF;
import io.qt.gui.QColor;
import io.qt.gui.QPaintEvent;
import io.qt.gui.QPainter;
import io.qt.gui.QPen;
import io.qt.widgets.QWidget;

public class NoteCanvas extends QWidget {
    public final MidiNoteSequence notes;
    
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
        } finally {
            painter.end();
        }
    }

    private void drawBackground(QPainter painter) {
        painter.fillRect(rect(), new QColor(30, 32, 38));
    }
    private void drawGrid(QPainter painter) {
        final double rowHeight = PianoRollViewState.pixelsPerSemitone;
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
}
