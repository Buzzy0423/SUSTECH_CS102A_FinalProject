package xyz.chengzi.aeroplanechess.model;

public class ChessPiece {
    private final int player;
    private int Number;

    public ChessPiece(int player,int Number) {
        this.player = player;
        this.Number = Number;
    }

    public void setNumber(int number){
        this.Number =number;
    }

    public int getPlayer() {
        return player;
    }

    public int getNumber() {
        return Number;
    }
}
