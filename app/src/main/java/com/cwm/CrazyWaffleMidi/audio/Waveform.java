package com.cwm.CrazyWaffleMidi.audio;

import java.util.function.Function;

public class Waveform {
    public static final Function<Double, Float> SINE = t -> (float)  Math.sin(t*2*Math.PI);
    public static final Function<Double, Float> SAWTOOTH = t -> (float) (2*t+1);
    public static final Function<Double, Float> SQUARE = t -> (float) (2*Math.round(t) - 1);
    public static final Function<Double, Float> TRIANGLE = t -> (float) (4*Math.abs(t + 0.25 - Math.floor(t + 0.75)));
    private final float[] samples;
    private final int sampleRate;

    public Waveform(Function<Double, Float> foo, int sampleRate) {
        this.sampleRate = sampleRate;
        this.samples = new float[sampleRate];

        for (int i = 0; i < sampleRate; i++) {
            double phase = (double) i / sampleRate;
            samples[i] = foo.apply(phase);
        }
    }

    public Waveform(Function<Double, Float> foo) {
        this(foo, 44100);
    }

    public int sampleRate() {
        return sampleRate;
    }

    public float get(double phase) {
        phase = ((phase % 1.0) + 1.0) % 1.0;
        int index = (int) (phase * sampleRate);
        return samples[index];
    }
}