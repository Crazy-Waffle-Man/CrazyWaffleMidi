package com.cwm.CrazyWaffleMidi.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class AudioPlayer {
    // public static void main(String[] args) {
    //     try {
    //         createTone(440, 100, 5000);
    //         createTone(220, 50, 2500);
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
        float ratePerMillis = rate / 1000;
        int samples = Math.round(ratePerMillis * duration);
        byte[] buf;
        AudioFormat format;
        buf = new byte[1];
        format = new AudioFormat(rate, 8, 1, true, false);
        SourceDataLine dataLine = AudioSystem.getSourceDataLine(format);
        dataLine.open(format);
        dataLine.start();
        for (int i = 0; i < samples; i++) {
            // TODO: integrate with Waveform and its sampleRate
            // Something something i < sampleRate Waveform#get(i)
            double angle = (i/rate)*freq*2.0*Math.PI;
            buf[0] = (byte)(Math.sin(angle)*vol);
            dataLine.write(buf, 0, 1);
        }

        dataLine.drain();
        dataLine.stop();
        dataLine.close();
    }
}