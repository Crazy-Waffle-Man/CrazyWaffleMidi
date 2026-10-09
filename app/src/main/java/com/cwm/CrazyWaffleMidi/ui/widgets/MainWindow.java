package com.cwm.CrazyWaffleMidi.ui.widgets;

import com.cwm.CrazyWaffleMidi.Properties;
import com.cwm.CrazyWaffleMidi.midi.MidiNoteSequence;

import io.qt.core.QCoreApplication;
import io.qt.core.QRect;
import io.qt.gui.QAction;
import io.qt.widgets.QMainWindow;
import io.qt.widgets.QMenu;
import io.qt.widgets.QMenuBar;
import io.qt.widgets.QVBoxLayout;


// Qt Widgets Designer goes crazy... Unfortunately no export for Java tho so I have to write this manually
public class MainWindow extends QMainWindow {
    public QAction open = new QAction(this);
    public QAction save = new QAction(this);
    public QAction saveAs = new QAction(this);
    public QAction mp3 = new QAction(this);
    public QAction ogg = new QAction(this);
    public QAction track = new QAction(this);
    public QAction pianoRoll = new QAction(this);
    public QAction synth = new QAction(this);
    public QAction zoomIn = new QAction(this);
    public QAction zoomOut = new QAction(this);
    public QAction resetZoom = new QAction(this);
    public NoteCanvas centralWidget = new NoteCanvas(new MidiNoteSequence()); //TODO: replace with PianoRollWidget when it's ready
    public QVBoxLayout verticalLayout = new QVBoxLayout(this);
    public QMenuBar menuBar = new QMenuBar(this);
    public QMenu file = new QMenu(menuBar);
    public QMenu edit = new QMenu(menuBar);
    public QMenu export = new QMenu(menuBar);
    public QMenu view = new QMenu(menuBar);
    public QMenu settings = new QMenu(menuBar);

    public MainWindow() {
        if (objectName().isEmpty()) {
            setObjectName(Properties.NAME);
        }
        this.resize(800, 600);

        open.setObjectName("action_open");
        save.setObjectName("action_save");
        saveAs.setObjectName("action_save_as");
        mp3.setObjectName("action_mp3");
        ogg.setObjectName("action_ogg");
        track.setObjectName("action_track");
        pianoRoll.setObjectName("action_piano_roll");
        synth.setObjectName("action_synth");
        zoomIn.setObjectName("action_zoom_in");
        zoomOut.setObjectName("action_zoom_out");
        resetZoom.setObjectName("action_reset_zoom");

        centralWidget.setObjectName("central_widget");

        menuBar.setObjectName("menu_bar");
        menuBar.setGeometry(new QRect(0, 0, 800, 30));

        file.setObjectName("menu_file");
        edit.setObjectName("menu_edit");
        export.setObjectName("menu_export");
        view.setObjectName("menu_view");
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

        // verticalLayout.addWidget(menuBar);
        setCentralWidget(centralWidget);

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
}
