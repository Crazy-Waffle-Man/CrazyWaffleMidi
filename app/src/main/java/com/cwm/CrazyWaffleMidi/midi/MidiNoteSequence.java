package com.cwm.CrazyWaffleMidi.midi;

import java.util.ArrayList;

import javax.sound.midi.Track;

public class MidiNoteSequence extends ArrayList<MidiNote> {
    private long nextId = 0;
    public void addMidiNote(int pitch, long startTick, long durationTicks, int velocity) {
        add(new MidiNote(nextId, pitch, startTick, durationTicks, velocity));
        nextId ++;
    }
    public boolean apply(Track track) {
        boolean allSuccessful = true;
        for (MidiNote note : this) {
            if (!note.apply(track)) {
                allSuccessful = false;
            }
        }
        return allSuccessful;
    }
}
