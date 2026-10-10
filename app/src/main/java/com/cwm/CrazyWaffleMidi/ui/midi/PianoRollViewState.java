package com.cwm.CrazyWaffleMidi.ui.midi;

public final class PianoRollViewState {
    public static int ticksPerQuarter = 960;
    public static int beatsPerBar = 4;
    public static int beatUnit = 4;

    public static double pixelsPerQuarter = 48.0;
    public static double pixelsPerSemitone = 12.0;

    public static long horizontalScrollTick = 0;
    public static int topPitch = 84;

    public static double getX(long tick) {
        return (tick - horizontalScrollTick) * pixelsPerQuarter / ticksPerQuarter;
    }
    public static double getY(int pitch) {
        return (topPitch - pitch) * pixelsPerSemitone;
    }
    public static long getTick(double x) { 
        // Shouldn't be used to set midi note data; midi notes are the source of truth.
        // Use when converting cursor position for creating a new note
        final double ticksPerPixel = ticksPerQuarter / pixelsPerQuarter;
        final double ticks = x * ticksPerPixel;
        return horizontalScrollTick + Math.round(ticks);
    }
    public static int getPitch(double y) {
        return topPitch - (int) Math.floor(y / pixelsPerSemitone);
    }
}
