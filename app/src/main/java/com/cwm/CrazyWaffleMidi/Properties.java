package com.cwm.CrazyWaffleMidi;

public class Properties {
    public static String repo = "https://github.com/Crazy-Waffle-Man/CrazyWaffleMidi";
    public static EditorState editorState = EditorState.SELECT;
    public static EditorView editorView = EditorView.MAIN_MENU;
    public static final String NAME = "Crazy Waffle Midi";
    public static enum EditorState {
        SELECT,
        DRAW,
        KEYBOARD
    }
    public static enum EditorView {
        MAIN_MENU,
        TRACKS,
        PIANO_ROLL,
        WAVEFORM
    }
}
