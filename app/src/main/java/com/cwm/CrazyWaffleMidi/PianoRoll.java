package com.cwm.CrazyWaffleMidi;

import io.qt.widgets.QWidget;

public class PianoRoll extends RectangleDrawer {
    public static int noteHeight = 20;
    public static int quarterNoteWidth = 20; // do math to figure these out
    public PianoRoll(QWidget parent) {
        super(parent);
    }
    /**
     * Get the width of a note `multiplier` times the length of a quarter note.
     * @param multiplier
     * @return
    */
    public static double getNoteWidth(double multiplier) {
        return multiplier * quarterNoteWidth;
    }
}
