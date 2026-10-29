package com.cwm.CrazyWaffleMidi.ui.widgets;

import com.cwm.CrazyWaffleMidi.ui.midi.PianoRollViewState;

import io.qt.Nullable;
import io.qt.core.QRectF;
import io.qt.gui.QColor;
import io.qt.gui.QPaintEvent;
import io.qt.gui.QPainter;
import io.qt.widgets.QWidget;

public class PianoKeys extends QWidget {
    public PianoKeys(QWidget parent) {
        super(parent);
    }

    @Override
    protected void paintEvent(@Nullable QPaintEvent event) {
        QPainter painter = new QPainter(this);
        try {
            drawWhiteKeys(painter); 
            // Draw white keys first so that black keys go on top
            // without any extra code
            drawBlackKeys(painter);
        } finally {
            painter.end();
        }
    }

    private void drawWhiteKeys(QPainter painter) {
        QColor white = new QColor(255,255, 255);
        for (int pitch = 0; pitch < 128; pitch ++) {
            switch (pitch % 12) {
                case 0:
                    painter.fillRect(
                        new QRectF(
                            0, PianoRollViewState.getY(pitch - 0.4),
                            30, PianoRollViewState.pixelsPerSemitone
                        ),
                        white
                    );
                    break;
                case 2:
                    painter.fillRect(
                        new QRectF(
                            0, PianoRollViewState.getY(pitch - 0.4),
                            30, PianoRollViewState.pixelsPerSemitone
                        ),
                        white
                    );
                    break;
                case 4:
                    painter.fillRect(
                        new QRectF(
                            0, PianoRollViewState.getY(pitch),
                            30, PianoRollViewState.pixelsPerSemitone / 10
                        ),
                        white
                );
                    break;
                case 5:
                    painter.fillRect(
                        new QRectF(
                            0, PianoRollViewState.getY(pitch - 0.4),
                            30, PianoRollViewState.pixelsPerSemitone
                        ),
                        white
                    );
                    break;
                case 7:
                    painter.fillRect(
                        new QRectF(
                            0, PianoRollViewState.getY(pitch - 0.4),
                            30, PianoRollViewState.pixelsPerSemitone
                        ),
                        white
                    );
                    break;
                case 9:
                    painter.fillRect(
                        new QRectF(
                            0, PianoRollViewState.getY(pitch - 0.4),
                            30, PianoRollViewState.pixelsPerSemitone
                        ),
                        white
                    );
                    break;
                case 11:
                    painter.fillRect(
                        new QRectF(
                            0, PianoRollViewState.getY(pitch),
                            30, PianoRollViewState.pixelsPerSemitone
                        ),
                        white
                    );
                    break;
                default:
                    break;
            }
        }
    }
    private void drawBlackKeys(QPainter painter) {

    }
}
