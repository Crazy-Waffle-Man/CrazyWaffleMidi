package com.cwm.CrazyWaffleMidi;

import io.qt.core.QCoreApplication;
import io.qt.core.QRect;
import io.qt.gui.QAction;
import io.qt.widgets.QMainWindow;
import io.qt.widgets.QMenu;
import io.qt.widgets.QMenuBar;
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
    public QMenu edit;
    public QMenu export;
    public QMenu view;
    public QMenu settings;

    public MainWindow() {
        if (objectName().isEmpty()) {
            setObjectName(Properties.NAME);
        }
        this.resize(800, 600);
        open = new QAction(this);
        open.setObjectName("action_open");
        save = new QAction(this);
        save.setObjectName("action_save");
        saveAs = new QAction(this);
        saveAs.setObjectName("action_save_as");
        mp3 = new QAction(this);
        mp3.setObjectName("action_mp3");
        ogg = new QAction(this);
        ogg.setObjectName("action_ogg");
        track = new QAction(this);
        track.setObjectName("action_track");
        pianoRoll = new QAction(this);
        pianoRoll.setObjectName("action_piano_roll");
        synth = new QAction(this);
        synth.setObjectName("action_synth");
        zoomIn = new QAction(this);
        zoomIn.setObjectName("action_zoom_in");
        zoomOut = new QAction(this);
        zoomOut.setObjectName("action_zoom_out");
        resetZoom = new QAction(this);
        resetZoom.setObjectName("action_reset_zoom");

        centralWidget = new QWidget(this);
        centralWidget.setObjectName("central_widget");

        menuBar = new QMenuBar(this);
        menuBar.setObjectName("menu_bar");
        menuBar.setGeometry(new QRect(0, 0, 800, 30));

        file = new QMenu(menuBar);
        file.setObjectName("menu_file");
        edit = new QMenu(menuBar);
        edit.setObjectName("menu_edit");
        export = new QMenu(menuBar);
        export.setObjectName("menu_export");
        view = new QMenu(menuBar);
        view.setObjectName("menu_view");
        settings = new QMenu(menuBar);
        settings.setObjectName("menu_settings");

        setMenuBar(menuBar);

        menuBar.addAction(file.menuAction());
        menuBar.addAction(edit.menuAction());
        menuBar.addAction(view.menuAction());
        menuBar.addAction(settings.menuAction());

        file.addAction(open);
        file.addAction(save);
        file.addAction(saveAs);
        file.addAction(export.menuAction());
        
        export.addAction(mp3);
        export.addAction(ogg);

        edit.addAction(track);
        edit.addAction(pianoRoll);
        edit.addAction(synth);

        view.addAction(zoomIn);
        view.addAction(zoomOut);
        view.addAction(resetZoom);

        retranslateUi(this);

        show();
    }

    private void retranslateUi(QMainWindow window) {
        window.setWindowTitle(QCoreApplication.translate("MainWindow", Properties.NAME));
        open.setText(QCoreApplication.translate("MainWindow", "&Open"));
        save.setText(QCoreApplication.translate("MainWindow", "&Save"));
        saveAs.setText(QCoreApplication.translate("MainWindow", "Save &as"));
        mp3.setText(QCoreApplication.translate("MainWindow", ".mp&3"));
        ogg.setText(QCoreApplication.translate("MainWindow", ".&ogg"));
        track.setText(QCoreApplication.translate("MainWindow", "&Track"));
        pianoRoll.setText(QCoreApplication.translate("MainWindow", "&Piano roll"));
        synth.setText(QCoreApplication.translate("MainWindow", "&Synth"));
        zoomIn.setText(QCoreApplication.translate("MainWindow", "Zoom &in"));
        zoomOut.setText(QCoreApplication.translate("MainWindow", "Zoom &out"));
        resetZoom.setText(QCoreApplication.translate("MainWindow", "&Reset zoom"));
        file.setTitle(QCoreApplication.translate("MainWindow", "&File"));
        export.setTitle(QCoreApplication.translate("MainWindow", "&Export"));
        edit.setTitle(QCoreApplication.translate("MainWindow", "&Edit"));
        view.setTitle(QCoreApplication.translate("MainWindow", "&View"));
        settings.setTitle(QCoreApplication.translate("MainWindow", "&Settings"));
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
