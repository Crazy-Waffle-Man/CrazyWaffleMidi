package com.cwm.CrazyWaffleMidi.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import com.cwm.CrazyWaffleMidi.midi.MidiNote;
import com.cwm.CrazyWaffleMidi.midi.MidiNoteSequence;
import com.cwm.CrazyWaffleMidi.ui.midi.PianoRollViewState;

public class AudioPlayer {
    public static void main(String[] args) {
        try {
            createTone(stageTone(440, 100, 500));
            createTone(stageTone(220, 50, 250));
            createTone(stageTone(new Waveform(Waveform.SINE), 880, 50, 500));
            createTone(stageTone(new Waveform(Waveform.SAWTOOTH), 440, 50, 250));
            createTone(stageTone(new Waveform(Waveform.SQUARE), 220, 50, 500));
            createTone(stageTone(new Waveform(Waveform.TRIANGLE), 880, 50, 750));
            MidiNoteSequence seq = new MidiNoteSequence();
            seq.addMidiNote(64, 0, PianoRollViewState.ticksPerQuarter, 50);

            seq.addMidiNote(60, PianoRollViewState.ticksPerQuarter, PianoRollViewState.ticksPerQuarter, 50);
            seq.addMidiNote(64, PianoRollViewState.ticksPerQuarter, PianoRollViewState.ticksPerQuarter, 50);
            seq.addMidiNote(67, PianoRollViewState.ticksPerQuarter, PianoRollViewState.ticksPerQuarter, 50);
            playMidiSequence(new Waveform(Waveform.SQUARE), seq, 1000);
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
    public static DataLine stageTone(int freq, int vol, int duration) throws LineUnavailableException {
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
        return dataLine;
    }
    public static DataLine stageTone(Waveform form, int freq, int vol, int duration) throws LineUnavailableException {
        return stageTone(form, freq, vol, duration, form.sampleRate());
    }
    public static DataLine stageTone(Waveform form, int freq, int vol, int duration, int sampleRate) throws LineUnavailableException {
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
        return dataLine;
    }
    public static void createTone(DataLine data) {
        data.drain();
        data.stop();
        data.close();
    }

    public static void playMidiSequence(Waveform form, MidiNoteSequence sequence, int tempo) {
        MidiDataLine[] dataLines = new MidiDataLine[sequence.size()];
        sequence.sort((a,b) -> (int) (a.startTick() - b.startTick()));
        for (int i = 0; i < sequence.size(); i++) {
            dataLines[i] = new AudioPlayer.MidiDataLine(form, sequence.get(i), tempo);
        }
        for (int i = 0; i < sequence.getLast().startTick(); i++) {
            for (MidiDataLine dataLine : dataLines) {
                // try {
                //     Thread.sleep(PianoRollViewState.ticksPerQuarter * tempo / 60 * 1000);
                // } catch (InterruptedException e) {
                //     e.printStackTrace();
                // }
                if (dataLine.startTick != i) {
                    continue;
                }
                new Thread(()->AudioPlayer.createTone(dataLine.dataLine)).start();
            }
        }
    }
    static class MidiDataLine {
        public DataLine dataLine;
        public long startTick;
        public MidiDataLine(Waveform form, MidiNote note, int tempo) {
            int freq = note.getFrequency();
            startTick = note.startTick();
            try {
                dataLine = AudioPlayer.stageTone(form, freq, note.velocity(), (int) ((note.durationTicks() / PianoRollViewState.ticksPerQuarter) * tempo));
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}