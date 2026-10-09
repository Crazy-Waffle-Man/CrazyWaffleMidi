package com.cwm.CrazyWaffleMidi.ui.widgets;

import com.cwm.CrazyWaffleMidi.midi.MidiNoteSequence;

import io.qt.widgets.QWidget;

public class PianoRollWidget extends QWidget {
    private QWidget timelineRuler;
    private QWidget pianoKeyboard;
    private NoteCanvas noteCanvas;

    public PianoRollWidget() {
        noteCanvas = new NoteCanvas(new MidiNoteSequence());
        //TODO: layout
    }
}
