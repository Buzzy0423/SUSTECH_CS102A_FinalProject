package xyz.chengzi.aeroplanechess.listener;

import xyz.chengzi.aeroplanechess.model.ChessBoardLocation;
import xyz.chengzi.aeroplanechess.view.ChessComponent;
import xyz.chengzi.aeroplanechess.view.SquareComponent;

public interface InputListener extends Listener {
    void onPlayerClickSquare(ChessBoardLocation location, SquareComponent component);

    void onPlayerClickChessPiece(ChessBoardLocation location, ChessComponent component);
}
