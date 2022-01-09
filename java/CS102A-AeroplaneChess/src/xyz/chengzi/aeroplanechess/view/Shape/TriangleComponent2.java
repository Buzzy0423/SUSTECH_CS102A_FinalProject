package xyz.chengzi.aeroplanechess.view.Shape;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class TriangleComponent2 extends S {
    private final Color color;
    private final int player;
    private final int index;
    private int size;
    private int T[][] = new int[2][3];

    public TriangleComponent2(int size, Color color, int player, int index) {
        setLayout(new GridLayout()); // Use 1x1 grid layout
        setSize(size * 2, size * 2);
        setBackground(new Color(0,0,0,0));
        this.color = color;
        this.player = player;
        this.index = index;
        this.size = size;
        T[0][0] = 0;
        T[0][1] = size * 2;
        T[0][2] = 0;
        T[1][0] = 0;
        T[1][1] = 0;
        T[1][2] = size * 2;
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
        paintTriangle(g);
    }

    private void paintTriangle(Graphics g) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(color);
        ((Graphics2D) g).fill(new GeneralPath(new Polygon(T[0], T[1], 3)));
        g.setColor(Color.WHITE);
        g.fillOval(getWidth() / 15, getWidth() / 15, (getWidth() / 2) - 11, (getHeight() / 2) - 11);
        g.setColor(new Color(200, 200, 200));
        g.drawOval(getWidth() / 15, getWidth() / 15, (getWidth() / 2) - 11, (getHeight() / 2) - 11);
        g.drawLine(0, 0, 0, size * 2 - 1);
        g.drawLine(0, 0, size * 2 - 1, 0);
        g.drawLine(0, size * 2 - 1, size * 2 - 1, 0);
    }
}
