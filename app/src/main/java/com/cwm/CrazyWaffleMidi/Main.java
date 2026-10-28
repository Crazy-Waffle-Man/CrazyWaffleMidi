package com.cwm.CrazyWaffleMidi;

import com.cwm.CrazyWaffleMidi.audio.AudioPlayer;
import com.cwm.CrazyWaffleMidi.ui.widgets.MainWindow;

import io.qt.widgets.QApplication;

public class Main {
    public static MainWindow window;
    public static void main(String[] args) {
        for (String arg : args) {
            if (arg == "--audio-test") {
                AudioPlayer.main(args);
                break;
            }
        }
        QApplication.initialize(args);
        new MainWindow();
        QApplication.exec();
    }

}