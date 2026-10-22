package com.cwm.CrazyWaffleMidi.audio;

import java.util.HashMap;

public class WaveformPlaybackData {
    public HashMap<Integer, Byte> data;

    public WaveformPlaybackData() {
        data = new HashMap<Integer, Byte>();
    }

    public void add(Waveform form, int freq, int vol, int duration, int startTime) {
        float ratePerMillis = (float) form.sampleRate() / 1000.0f;
        int samples = Math.round(ratePerMillis * duration);
        for (int i = 0; i < samples; i++) {
            double progress = ((double) i/(double) form.sampleRate()) * freq;
            float sample = form.get(progress)*vol;
            byte value = (byte) Math.clamp(sample, -128, 127);
            value = (byte) Math.clamp(value + data.get(i), -128, 127);
            data.replace(i, value);
        }
    }
}
