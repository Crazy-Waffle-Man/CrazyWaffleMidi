package com.cwm.CrazyWaffleMidi.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class AudioPlayer {
    // public static void main(String[] args) {
    //     try {
    //         createTone(440, 100, 500);                                                   // Sine
    //         createTone(220, 50, 250);                                                    // Sine
    //         createTone(new Waveform(t -> (float)  Math.sin(t*2*Math.PI)), 880, 50, 500); // Sine
    //         createTone(new Waveform(t -> (float) (2*t-1)), 440, 50, 250);                // Sawtooth
    //         createTone(new Waveform(t -> (float) (2*Math.round(t) - 1)), 220, 50, 500);  // Square
    //     } catch (LineUnavailableException e) {
    //         e.printStackTrace();
    //     }
    // }

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