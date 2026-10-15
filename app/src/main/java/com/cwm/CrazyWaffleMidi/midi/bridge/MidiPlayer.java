package com.cwm.CrazyWaffleMidi.midi.bridge;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

// Thanks to https://reintech.io/blog/java-midi-programming-creating-manipulating-midi-data
public class MidiPlayer {
    private final Object LOCK = new Object();
    private Sequencer sequencer;
    private boolean isPlaying = false;

    public MidiPlayer() throws MidiUnavailableException {
        synchronized (LOCK) {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
        }
    }

    public void play(Sequence sequence) throws InvalidMidiDataException {
        synchronized (LOCK) {
            if (isPlaying) {
                sequencer.stop();
            }
            sequencer.setSequence(sequence);
            sequencer.start();
            isPlaying = true;
        }
    }

    public void stop() {
        synchronized (LOCK) {
            if (isPlaying && sequencer != null) {
                sequencer.stop();
                isPlaying = false;
            }
        }
    }

    public void cleanup() {
        synchronized (LOCK) {
            if (isPlaying) {
                sequencer.stop();
            }
            sequencer.close();
        }
    }
}
