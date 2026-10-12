package com.cwm.CrazyWaffleMidi.audio;

import java.util.function.Function;

public class Waveform {
    private float[] samples;
    private int sampleRate;
    
    public Waveform(float[] samples, int sampleRate) {
        this.samples = samples.clone();
        this.sampleRate = sampleRate;
        for (float s : this.samples) {
            Math.clamp(s, -1, 1);
        }
    }
    public Waveform(Function<Double, Float> foo, int sampleRate) {
        this.sampleRate = sampleRate;
        samples = new float[sampleRate];
        for (int i = 0; i < sampleRate; i++) {
            try {
                samples[i] = foo.apply((double)1/i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public int size() {
        return samples.length;
    }
    public float get(int i) {
        return samples[i];
    }
    public int sampleRate() {
        return sampleRate;
    }
    public double duration() {
        return (double) samples.length / sampleRate;
    }
    public Waveform repeat(int times) {
        float[] newArray = new float[samples.length * times];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = samples[i % samples.length];
        }
        return new Waveform(this.samples, sampleRate);
    }
}
