package com.cwm.CrazyWaffleMidi;
import java.net.URI;
import java.net.URISyntaxException;


public class Properties {
    public static URI repoUri;
    public static EditorState editorState = EditorState.SELECT;
    public static final String NAME = "Crazy Waffle Midi";
    public static void init(){
        try {
            repoUri = new URI("https://github.com/Crazy-Waffle-Man/CrazyWaffleMidi");
        } catch (URISyntaxException e) {
            repoUri = null;
            e.printStackTrace();
        }
    }
    public static enum EditorState {
        SELECT,
        DRAW,
        KEYBOARD
    }
}
