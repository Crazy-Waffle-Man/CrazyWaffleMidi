package com.cwm.CrazyWaffleMidi.midi;

public class MidiNote {
    private final long id;
    private int pitch; // midi (0-127)
    private long startTick;
    private long durationTicks;
    private int velocity; // volume (1-127)

    public MidiNote(long id, int pitch, long startTick, long durationTicks, int velocity) {
        System.out.println(String.format("Init a MidiNote with %d pitch and %d ticks long", pitch, durationTicks));
        this.id = id;
        this.pitch = Math.clamp(pitch, 0, 127);
        this.startTick = startTick;
        this.durationTicks = durationTicks;
        this.velocity = Math.clamp(velocity, 1, 127);
    }

    public long id(){
        return id;
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
}
