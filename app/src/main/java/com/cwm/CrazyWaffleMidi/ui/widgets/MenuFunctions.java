package com.cwm.CrazyWaffleMidi.ui.widgets;

import com.cwm.CrazyWaffleMidi.Main;
import com.cwm.CrazyWaffleMidi.audio.AudioPlayer;

public class MenuFunctions {
    public static void play() {
        Thread t = new Thread(() -> {
            AudioPlayer.createTone(Main.window.centralWidget.notes);
        });
        t.start();
    }
    // public static void 
}
