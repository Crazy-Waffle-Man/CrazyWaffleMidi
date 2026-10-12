package com.cwm.CrazyWaffleMidi.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class AudioPlayer {
    private static void _play(Waveform samples) throws LineUnavailableException {
        AudioFormat format = new AudioFormat(
            44100, 
            16, 
            1, 
            true, 
            false
        );

        try (SourceDataLine line = AudioSystem.getSourceDataLine(format)) {
            line.open(format);
            line.start();
            byte[] buff = new byte[4096];

            for (int offset = 0; offset < samples.size(); ) {
                int count = Math.min(buff.length / 2, samples.size() - offset);
                for (int i = 0; i < count; i++) {
                    float sample = Math.clamp(samples.get(offset + i), -1.0f, 1.0f);
                    short pcm = (short) Math.round(sample * Short.MAX_VALUE);
                    buff[i*2] = (byte) pcm;
                    buff[i*2 + 1] = (byte) (pcm >>> 8);
                }
                line.write(buff, 0, count * 2);
                offset += count;
            }
            line.drain();
        }
    }

    public static void play(Waveform samples) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    _play(samples);
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
        Waveform exampleForm = new Waveform((t)->(float) Math.sin(2*Math.PI*t), 44100);
        try {
            _play(exampleForm.repeat(10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
