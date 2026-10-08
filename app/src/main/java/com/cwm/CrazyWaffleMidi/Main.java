package com.cwm.CrazyWaffleMidi;

import io.qt.widgets.QApplication;

public class Main {
    public static void main(String[] args) {
        QApplication.initialize(args);
        new MainWindow();
        QApplication.exec();
    }

}