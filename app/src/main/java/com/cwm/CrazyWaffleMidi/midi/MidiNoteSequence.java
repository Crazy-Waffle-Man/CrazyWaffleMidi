package com.cwm.CrazyWaffleMidi.midi;

import java.util.ArrayList;

public class MidiNoteSequence extends ArrayList<MidiNote> {
    private long nextId = 0;
    public void addMidiNote(int pitch, long startTick, long durationTicks, int velocity) {
        add(new MidiNote(nextId, pitch, startTick, durationTicks, velocity));
        nextId ++;
    }
}
