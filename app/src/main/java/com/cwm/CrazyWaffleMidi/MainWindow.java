package com.cwm.CrazyWaffleMidi;

import io.qt.gui.QAction;
import io.qt.gui.QDesktopServices;
import io.qt.widgets.QLabel;
import io.qt.widgets.QMainWindow;
import io.qt.widgets.QMenu;
import io.qt.widgets.QMenuBar;
import io.qt.widgets.QPushButton;
import io.qt.widgets.QVBoxLayout;
import io.qt.widgets.QWidget;


// Qt Widgets Designer goes crazy... Unfortunately no export for Java tho so I have to write this manually
public class MainWindow extends QMainWindow {
    public QAction open;
    public QAction save;
    public QAction saveAs;
    public QAction mp3;
    public QAction ogg;
    public QAction track;
    public QAction pianoRoll;
    public QAction synth;
    public QAction zoomIn;
    public QAction zoomOut;
    public QAction resetZoom;
    public QWidget centralWidget;
    public QVBoxLayout verticalLayout;
    public QMenuBar menuBar;
    public QMenu file;
    public QMenu export;
    public QMenu view;
    public QMenu settings;

    public MainWindow() {

    }

    /*
    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName("MainWindow");
        MainWindow->resize(800, 600);
        action_Open = new QAction(MainWindow);
        action_Open->setObjectName("action_Open");
        action_Save = new QAction(MainWindow);
        action_Save->setObjectName("action_Save");
        actionSave_as = new QAction(MainWindow);
        actionSave_as->setObjectName("actionSave_as");
        action_Midi = new QAction(MainWindow);
        action_Midi->setObjectName("action_Midi");
        action_mp_3 = new QAction(MainWindow);
        action_mp_3->setObjectName("action_mp_3");
        action_ogg = new QAction(MainWindow);
        action_ogg->setObjectName("action_ogg");
        action_Track = new QAction(MainWindow);
        action_Track->setObjectName("action_Track");
        action_Piano_roll = new QAction(MainWindow);
        action_Piano_roll->setObjectName("action_Piano_roll");
        action_Synth = new QAction(MainWindow);
        action_Synth->setObjectName("action_Synth");
        actionZoom_in = new QAction(MainWindow);
        actionZoom_in->setObjectName("actionZoom_in");
        actionZoom_out = new QAction(MainWindow);
        actionZoom_out->setObjectName("actionZoom_out");
        action_Reset_zoom = new QAction(MainWindow);
        action_Reset_zoom->setObjectName("action_Reset_zoom");
        centralwidget = new QWidget(MainWindow);
        centralwidget->setObjectName("centralwidget");
        verticalLayout = new QVBoxLayout(centralwidget);
        verticalLayout->setObjectName("verticalLayout");
        MainWindow->setCentralWidget(centralwidget);
        menuBar = new QMenuBar(MainWindow);
        menuBar->setObjectName("menuBar");
        menuBar->setGeometry(QRect(0, 0, 800, 30));
        menuFile = new QMenu(menuBar);
        menuFile->setObjectName("menuFile");
        menu_Export = new QMenu(menuFile);
        menu_Export->setObjectName("menu_Export");
        menuEdit = new QMenu(menuBar);
        menuEdit->setObjectName("menuEdit");
        menuView = new QMenu(menuBar);
        menuView->setObjectName("menuView");
        menuSettings = new QMenu(menuBar);
        menuSettings->setObjectName("menuSettings");
        MainWindow->setMenuBar(menuBar);

        menuBar->addAction(menuFile->menuAction());
        menuBar->addAction(menuEdit->menuAction());
        menuBar->addAction(menuView->menuAction());
        menuBar->addAction(menuSettings->menuAction());
        menuFile->addAction(action_Open);
        menuFile->addAction(action_Save);
        menuFile->addAction(actionSave_as);
        menuFile->addAction(menu_Export->menuAction());
        menu_Export->addAction(action_mp_3);
        menu_Export->addAction(action_ogg);
        menuEdit->addAction(action_Track);
        menuEdit->addAction(action_Piano_roll);
        menuEdit->addAction(action_Synth);
        menuView->addAction(actionZoom_in);
        menuView->addAction(actionZoom_out);
        menuView->addAction(action_Reset_zoom);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QCoreApplication::translate("MainWindow", "MainWindow", nullptr));
        action_Open->setText(QCoreApplication::translate("MainWindow", "&Open", nullptr));
        action_Save->setText(QCoreApplication::translate("MainWindow", "&Save", nullptr));
        actionSave_as->setText(QCoreApplication::translate("MainWindow", "Save &as", nullptr));
        action_Midi->setText(QCoreApplication::translate("MainWindow", "&Midi (.mid)", nullptr));
        action_mp_3->setText(QCoreApplication::translate("MainWindow", ".mp&3", nullptr));
        action_ogg->setText(QCoreApplication::translate("MainWindow", ".&ogg", nullptr));
        action_Track->setText(QCoreApplication::translate("MainWindow", "&Track", nullptr));
        action_Piano_roll->setText(QCoreApplication::translate("MainWindow", "&Piano roll", nullptr));
        action_Synth->setText(QCoreApplication::translate("MainWindow", "&Synth", nullptr));
        actionZoom_in->setText(QCoreApplication::translate("MainWindow", "Zoom &in", nullptr));
        actionZoom_out->setText(QCoreApplication::translate("MainWindow", "Zoom &out", nullptr));
        action_Reset_zoom->setText(QCoreApplication::translate("MainWindow", "&Reset zoom", nullptr));
        menuFile->setTitle(QCoreApplication::translate("MainWindow", "&File", nullptr));
        menu_Export->setTitle(QCoreApplication::translate("MainWindow", "&Export", nullptr));
        menuEdit->setTitle(QCoreApplication::translate("MainWindow", "&Edit", nullptr));
        menuView->setTitle(QCoreApplication::translate("MainWindow", "&View", nullptr));
        menuSettings->setTitle(QCoreApplication::translate("MainWindow", "&Settings", nullptr));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

    */
}
