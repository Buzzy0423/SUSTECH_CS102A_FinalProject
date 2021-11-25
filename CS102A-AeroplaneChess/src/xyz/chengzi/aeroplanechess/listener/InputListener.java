package xyz.chengzi.aeroplanechess.listener;


import xyz.chengzi.aeroplanechess.model.ChessBoardLocation;
import xyz.chengzi.aeroplanechess.view.ChessComponent;
import xyz.chengzi.aeroplanechess.view.Shape.*;

public interface InputListener extends Listener {
    void onPlayerClickSquare(ChessBoardLocation location, S component);

    void onPlayerClickChessPiece(ChessBoardLocation location, ChessComponent component) throws InterruptedException;

}
