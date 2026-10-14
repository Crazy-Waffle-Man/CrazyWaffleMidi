package com.cwm.CrazyWaffleMidi.midi;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.sound.sampled.LineUnavailableException;

import com.cwm.CrazyWaffleMidi.audio.AudioPlayer;
import com.cwm.CrazyWaffleMidi.audio.Waveform;
import com.cwm.CrazyWaffleMidi.ui.midi.PianoRollViewState;

public class MidiNote {
    private final long id;
    private int pitch; // midi (0-127)
    private long startTick;
    private long durationTicks;
    private int velocity; // volume (1-127)

    public MidiNote(long id, int pitch, long startTick, long durationTicks, int velocity) {
        // System.out.println(String.format("Init a MidiNote with %d pitch and %d ticks long", pitch, durationTicks));
        this.id = id;
        this.pitch = Math.clamp(pitch, 0, 127);
        this.startTick = startTick;
        this.durationTicks = durationTicks;
        this.velocity = Math.clamp(velocity, 1, 127);
    }
    public MidiNote() {
        // Null note
        id = -1;
    }

    public long id(){
        return id != -1 ? id : null;
    }
    public int pitch(){
        return pitch;
    }
    public long startTick(){
        return startTick;
    }
    public long durationTicks(){
        return durationTicks;
    }
    public int velocity(){
        return velocity;
    }
    public long endTick() {
        return startTick + durationTicks;
    }

    public void play(Waveform form) {
        try {
            AudioPlayer.createTone(form, getFrequency(), velocity, (int) (durationTicks / PianoRollViewState.ticksPerQuarter) * 1000);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public int getFrequency() {
        return (int) Math.round(Math.pow(2, ((double)(pitch - 69)) / 12.0d) * 440);
    }

    public void setPitch(int pitch) {
        this.pitch = Math.clamp(pitch, 0, 127);
    }
    public void setStartTick(long tick) {
        startTick = Math.max(0, tick);
    }
    public void setDurationTicks(long ticks) {
        durationTicks = Math.max(1, ticks);
    }
    public void setVelocity(int velocity) {
        this.velocity = Math.clamp(velocity, 1, 127);
    }

    public boolean apply(Track track) {
        try {
            ShortMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, pitch, velocity);
            track.add(new MidiEvent(start, startTick));

            ShortMessage end = new ShortMessage(ShortMessage.NOTE_OFF, 0, pitch, 0);
            track.add(new MidiEvent(end, endTick()));
            return true;
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
            return false;
        }
    }
}
