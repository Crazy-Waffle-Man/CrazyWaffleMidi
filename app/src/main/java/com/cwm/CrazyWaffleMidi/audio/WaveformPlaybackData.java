package com.cwm.CrazyWaffleMidi.audio;

import java.util.Arrays;

import com.cwm.CrazyWaffleMidi.midi.MidiNote;

public class WaveformPlaybackData {
    private byte[] data;
    private int sampleRate;

    public WaveformPlaybackData(int maxSamples) {
        data = new byte[maxSamples];
        sampleRate = maxSamples;
    }

    public int maxSamples() {
        return data.length;
    }

    public void stretchResizeData(int length) {
        byte[] newData = new byte[length];
        for (int i = 0; i < length; i++) {
            newData[i] = data[(int) Math.floor(((float) i / (float) length) * data.length)];
        }
        data = newData.clone();
    }
    public void repeatResizeData(int length) {
        byte[] newData = new byte[length];
        for (int i = 0; i < length; i++) {
            newData[i] = data[i % data.length];
        }
        data = newData.clone();
    }
    public void emptyResizeData(int length) {
        data = Arrays.copyOf(data, length);
    }
    public byte[] getData() {
        return data.clone();
    }
    public int sampleRate() {
        return sampleRate;
    }

    public void add(Waveform form, int freq, int vol, int duration, int startTime) {
        int startSample = Math.round((startTime / 1000.0f) * form.sampleRate());
        int endSample = Math.round((duration / 1000.0f) * form.sampleRate()) + startSample;

        if (form.sampleRate() > sampleRate) {
            double factor = form.sampleRate() / sampleRate;
            int newLength = (int) (data.length * factor);
            stretchResizeData(newLength);
            sampleRate = form.sampleRate();
        }

        if (maxSamples() < endSample) {
            emptyResizeData(endSample);
        }
        for (int i = startSample; i < endSample; i++) {
            double progress = ((double) (i - startSample) / (double) form.sampleRate()) * freq;
            float sample = form.get(progress) * vol;
            data[i] = (byte) Math.clamp(data[i] + sample, -128, 127);
        }
    }
    public void add(MidiNote note, int millisPerTick) {
        add(Waveform.SINE, note, millisPerTick);
    }
    public void add(Waveform form, MidiNote note, int millisPerTick) {
        add(
            form, 
            note.getFrequency(), 
            note.velocity(), 
            (int) (note.durationTicks() * millisPerTick), 
            (int) (note.startTick() * millisPerTick)
        );
    }
}
