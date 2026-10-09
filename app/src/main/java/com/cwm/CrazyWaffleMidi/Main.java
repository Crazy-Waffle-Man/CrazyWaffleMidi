package com.cwm.CrazyWaffleMidi;

import com.cwm.CrazyWaffleMidi.ui.widgets.MainWindow;

import io.qt.widgets.QApplication;

public class Main {
    public static void main(String[] args) {
        QApplication.initialize(args);
        new MainWindow();
        QApplication.exec();
    }

}