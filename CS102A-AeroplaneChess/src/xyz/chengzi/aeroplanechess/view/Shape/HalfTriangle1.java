package xyz.chengzi.aeroplanechess.view.Shape;

import java.awt.*;

public class HalfTriangle1 extends S {
    private final Color color;
    private final Color color2;
    private final int player;
    private final int index;
    private int[][] P = new int[2][3];

    public HalfTriangle1(int size, Color color,Color color2, int player, int index) {
        setLayout(new GridLayout(1, 1)); // Use 1x1 grid layout
        setSize(size, size * 2);
        this.color = color;
        this.color2 = color2;
        this.player = player;
        this.index = index;
        setBackground(new Color(0, 0, 0, 0));
        P[0][0] = 0;
        P[1][0] = 0;
        P[0][1] = getWidth();
        P[1][1] = 0;
        P[0][2] = getWidth();
        P[1][2] = getHeight() / 2;
    }

    public Color getColor() {
        return color;
    }

    public int getPlayer() {
        return player;
    }

    public int getIndex() {
        return index;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintSquare(g);
    }

    private void paintSquare(Graphics g) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(color2);
        g.fillPolygon(P[0], P[1], 3);
        g.setColor(Color.WHITE);
        g.fillOval(5, 5 + getWidth(), getWidth() - 11, getHeight() / 2 - 11);
        g.setColor(new Color(200, 200, 200));
        g.drawOval(5, 5 + getWidth(), getWidth() - 11, getHeight() / 2 - 11);
        g.drawLine(0,0,0,getHeight());
        g.drawLine(0,0,getWidth(),getHeight() / 2);
        g.drawLine(0,0,getWidth(),0);
        g.drawLine(0,getHeight(),getWidth(),getHeight());
    }
}
