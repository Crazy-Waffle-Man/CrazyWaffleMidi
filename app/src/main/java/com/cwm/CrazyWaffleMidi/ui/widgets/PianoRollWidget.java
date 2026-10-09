package com.cwm.CrazyWaffleMidi.ui.widgets;

import com.cwm.CrazyWaffleMidi.midi.MidiNoteSequence;

import io.qt.widgets.QWidget;

public class PianoRollWidget extends QWidget {
    // private QWidget timelineRuler;
    // private QWidget pianoKeyboard;
    // private NoteCanvas noteCanvas;

    public PianoRollWidget() {
       new PianoRollWidget(new MidiNoteSequence());
        //TODO: layout
    }
    public PianoRollWidget(MidiNoteSequence seq) {
        // noteCanvas = new NoteCanvas(seq);
    }
}
