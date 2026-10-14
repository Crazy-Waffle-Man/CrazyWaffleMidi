package com.cwm.CrazyWaffleMidi.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import com.cwm.CrazyWaffleMidi.midi.MidiNote;
import com.cwm.CrazyWaffleMidi.midi.MidiNoteSequence;
import com.cwm.CrazyWaffleMidi.ui.midi.PianoRollViewState;

public class AudioPlayer {
    public static void main(String[] args) {
        try {
            createTone(440, 100, 500);
            createTone(220, 50, 250);
            createTone(Waveform.SINE, 880, 50, 500);
            createTone(Waveform.SAWTOOTH, 440, 50, 250);
            createTone(Waveform.SQUARE, 220, 50, 500);
            createTone(Waveform.TRIANGLE, 880, 50, 750);
            MidiNoteSequence seq = new MidiNoteSequence();
            for (int i = 64; i <= 64 + 13; i++) {
                seq.addMidiNote(i, PianoRollViewState.ticksPerQuarter * (i - 64), PianoRollViewState.ticksPerQuarter, 50);
            }
            for (MidiNote note : seq) {
                note.play(Waveform.SQUARE);
            }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param freq in Hz
     * @param vol
     * @param duration in millis
     * @throws LineUnavailableException
     */
    public static void createTone(int freq, int vol, int duration) throws LineUnavailableException {
        float rate = 44100;
        float ratePerMillis = (float) rate / 1000.0f;
        int samples = Math.round(ratePerMillis * duration);
        byte[] buf;
        AudioFormat format;
        buf = new byte[1];
        format = new AudioFormat(rate, 8, 1, true, false);
        SourceDataLine dataLine = AudioSystem.getSourceDataLine(format);
        dataLine.open(format);
        dataLine.start();
        for (int i = 0; i < samples; i++) {
            double angle = (i/rate)*freq*2.0*Math.PI;
            buf[0] = (byte)(Math.sin(angle)*vol);
            dataLine.write(buf, 0, 1);
        }

        dataLine.drain();
        dataLine.stop();
        dataLine.close();
    }
    public static void createTone(Waveform form, int freq, int vol, int duration) throws LineUnavailableException {
        createTone(form, freq, vol, duration, form.sampleRate());
    }
    public static void createTone(Waveform form, int freq, int vol, int duration, int sampleRate) throws LineUnavailableException {
        float ratePerMillis = (float) sampleRate / 1000.0f;
        int samples = Math.round(ratePerMillis * duration);
        byte[] buf = new byte[1];
        AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
        SourceDataLine dataLine = AudioSystem.getSourceDataLine(format);
        dataLine.open(format);
        dataLine.start();
        for (int i = 0; i < samples; i++) {
            double progress = ((double) i/(double) sampleRate) * freq;
            float sample = form.get(progress)*vol;
            buf[0] = (byte)(Math.clamp(sample, -128, 127));
            dataLine.write(buf, 0, 1);
        }
        dataLine.drain();
        dataLine.stop();
        dataLine.close();
    }
}