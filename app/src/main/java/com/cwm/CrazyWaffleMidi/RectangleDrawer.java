package com.cwm.CrazyWaffleMidi;

import io.qt.core.QRect;
import io.qt.core.Qt;
import io.qt.gui.QBrush;
import io.qt.gui.QColor;
import io.qt.gui.QPainter;
import io.qt.gui.QPen;
import io.qt.widgets.QWidget;

public class RectangleDrawer extends QWidget {
    public void paintRectangle(QRect rect, QColor color) {
        QPainter painter = new QPainter();
        painter.begin(this);
        painter.setPen(new QPen(color, 0, Qt.PenStyle.SolidLine));
        painter.setBrush(new QBrush(color, Qt.BrushStyle.SolidPattern));
        painter.drawRect(rect);
    }
}
