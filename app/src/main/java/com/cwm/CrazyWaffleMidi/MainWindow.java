package com.cwm.CrazyWaffleMidi;

import io.qt.widgets.QLabel;
import io.qt.widgets.QMainWindow;
import io.qt.widgets.QWidget;

public class MainWindow extends QMainWindow {
    public MainWindow() {
        super();
        setWindowTitle(Properties.NAME);
        QWidget mainWidget = new QWidget(this);
        QLabel label = new QLabel(mainWidget);
        label.setText("Test");
        show();
    }
}
