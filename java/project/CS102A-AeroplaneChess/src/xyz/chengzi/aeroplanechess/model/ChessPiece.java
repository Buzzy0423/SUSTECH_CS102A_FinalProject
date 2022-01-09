package xyz.chengzi.aeroplanechess.model;

public class ChessPiece {
    private final int player;

    public ChessPiece(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }
}
