package com.cwm.CrazyWaffleMidi.midi.bridge;

import java.lang.ProcessHandle.Info;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class MidiManager {
    public static Synthesizer getSynthesizer() {
        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            return synth;
        } catch (MidiUnavailableException e) {
            System.err.println("Hardware synth is missing :(");

            MidiDevice.Info[] info = MidiSystem.getMidiDeviceInfo();
            for (MidiDevice.Info i : info)  {
                try {
                    MidiDevice device = MidiSystem.getMidiDevice(i);
                    if (device instanceof Synthesizer) {
                        device.open();
                        return (Synthesizer) device;
                    }
                } catch (MidiUnavailableException ex) {
                    continue;
                }
            }
            // Translate Waveform to Synthesizer
            
        }
    }
}
