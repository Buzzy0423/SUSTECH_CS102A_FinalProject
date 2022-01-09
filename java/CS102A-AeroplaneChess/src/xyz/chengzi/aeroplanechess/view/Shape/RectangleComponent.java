package xyz.chengzi.aeroplanechess.view.Shape;

import java.awt.*;

public class RectangleComponent extends S {
    private final Color color;
    private final int player;
    private final int index;

    public RectangleComponent(int size, Color color, int player, int index) {
        setLayout(new GridLayout(1, 1)); // Use 1x1 grid layout
        setSize(size, size * 2);
        this.color = color;
        this.player = player;
        this.index = index;
        setBackground(new Color(0,0,0,0));
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
        g.fillRect(1, 1, getWidth() - 1, getHeight() -1);
        g.setColor(Color.WHITE);
        g.fillOval(5, 5 + getWidth() / 2, getWidth() - 11, getHeight() / 2 - 11);
        g.setColor(new Color(200, 200, 200));
        g.drawOval(5, 5 + getWidth() / 2, getWidth() - 11, getHeight() / 2 - 11);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
