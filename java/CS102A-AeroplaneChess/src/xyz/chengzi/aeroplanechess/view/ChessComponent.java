package xyz.chengzi.aeroplanechess.view;

import xyz.chengzi.aeroplanechess.model.ChessBoardLocation;

import javax.swing.*;
import java.awt.*;

public class ChessComponent extends JComponent {
    private Color color;
    private int P;
    private int index;
    private int Number;

    public ChessComponent(Color color, ChessBoardLocation Location, int Number) {
        this.color = color;
        this.P = Location.getColor();
        this.index = Location.getIndex();
        this.Number = Number;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintChess(g);
    }

    public void setNumber(int number) {
        this.Number = number;
    }

    private void paintChess(Graphics g) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int X = 0;
        int Y = 0;
        if (P == 0) {
            switch (index) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 6:
                case 13:
                case 15:
                case 5:
                case 9:
                case 10:
                case 14:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                    X = getWidth() / 2 - 12;
                    Y = getHeight() / 2 - 12;
                    break;
                case 7:
                    X = getWidth() / 15 + 5;
                    Y = getHeight() / 2 + getWidth() / 15 + 5;
                    break;
                case 8:
                    X = 10;
                    Y = 10 + getWidth();
                    break;
                case 11:
                    X = 10;
                    Y = 10;
                    break;
                case 12:
                    X = getWidth() / 15 + 5;
                    Y = getWidth() / 15 + 5;
                    break;
                case 23:
                    X = 10;
                    Y = 10;
                    break;
            }
        } else if (P == 1) {
            switch (index) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 6:
                case 13:
                case 15:
                case 5:
                case 9:
                case 10:
                case 14:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                    X = getWidth() / 2 - 12;
                    Y = getHeight() / 2 - 12;
                    break;
                case 7:
                    X = getWidth() / 15 + 5;
                    Y = getWidth() / 15 + 5;
                    break;
                case 8:
                case 11:
                    X = 10;
                    Y = 10 + getWidth();
                    break;
                case 12:
                    X = getWidth() / 2 + getWidth() / 15 + 5;
                    Y = getWidth() / 15 + 5;
                    break;
                case 23:
                    X = 10;
                    Y = 10;
                    break;
            }
        } else if (P == 2) {
            switch (index) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 6:
                case 13:
                case 15:
                case 5:
                case 9:
                case 10:
                case 14:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                    X = getWidth() / 2 - 12;
                    Y = getHeight() / 2 - 12;
                    break;
                case 7:
                    X = getHeight() / 2 + getWidth() / 15 + 5;
                    Y = getWidth() / 15 + 5;
                    break;
                case 8:
                    X = 10;
                    Y = 10;
                    break;
                case 11:
                    X = 10;
                    Y = 10 + getWidth();
                    break;
                case 12:
                    X = getWidth() / 2 + getWidth() / 15 + 5;
                    Y = getHeight() / 2 + getWidth() / 15 + 5;
                    break;
                case 23:
                    X = 10;
                    Y = 10 + getWidth();
                    break;
            }
        } else if (P == 3) {
            switch (index) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 6:
                case 13:
                case 15:
                case 5:
                case 9:
                case 10:
                case 14:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                    X = getWidth() / 2 - 12;
                    Y = getHeight() / 2 - 12;
                    break;
                case 7:
                    X = getHeight() / 2 + getWidth() / 15 + 5;
                    Y = getWidth() / 2 + getWidth() / 15 + 5;
                    break;
                case 8:
                case 11:
                    X = 10;
                    Y = 10;
                    break;
                case 12:
                    X = getWidth() / 15 + 5;
                    Y = getHeight() / 2 + getWidth() / 15 + 5;
                    break;
                case 23:
                    X = 10;
                    Y = 10 + getWidth();
                    break;
            }
        }
        g.setColor(color);
        g.fillOval(X, Y, 24, 24);
        g.setColor(Color.GRAY);
        g.drawOval(X, Y, 24, 24);
        g.setColor(Color.BLACK);
        g.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        if (Number > 1 && Number != 6) {
            String s = String.format("%d", Number);
            g.drawString(s, X + 7, Y + 18);
        } else if (Number == 6){
            g.drawLine(X, Y, X + 24, Y + 24);
            g.drawLine(X + 24, Y, X, Y + 24);
        }
    }
}
