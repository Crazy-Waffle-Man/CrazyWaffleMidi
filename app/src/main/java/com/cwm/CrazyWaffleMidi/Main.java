package com.cwm.CrazyWaffleMidi;

import io.qt.widgets.QApplication;

public class Main {
    public static void main(String[] args) {
        Properties.init();
        
        QApplication.initialize(args);
        new MainWindow();
        QApplication.exec();
    }

}