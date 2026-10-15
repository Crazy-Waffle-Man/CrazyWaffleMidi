package com.cwm.CrazyWaffleMidi.midi.bridge;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

import com.cwm.CrazyWaffleMidi.ui.midi.PianoRollViewState;

public class TrackManager {
    private Sequence sequence;

    public TrackManager() {
        try {
            sequence = new Sequence(Sequence.PPQ, PianoRollViewState.ticksPerQuarter);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    public void write(String filename) {
        try {
            MidiSystem.write(sequence, 1, new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Track createTrack() {
        return sequence.createTrack();
    }

    public Track[] getTracks() {
        return sequence.getTracks();
    }

    public Track getTrack(int i) {
        return getTracks()[i];
    }

    public Sequence getSequence() {
        return sequence;
    }
}
