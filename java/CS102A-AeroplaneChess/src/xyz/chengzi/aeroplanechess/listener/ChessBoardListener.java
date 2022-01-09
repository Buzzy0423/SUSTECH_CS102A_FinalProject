package xyz.chengzi.aeroplanechess.listener;

import xyz.chengzi.aeroplanechess.model.ChessBoard;
import xyz.chengzi.aeroplanechess.model.ChessBoardLocation;
import xyz.chengzi.aeroplanechess.model.ChessPiece;

public interface ChessBoardListener extends Listener {
    void onChessPiecePlace(ChessBoardLocation location, ChessPiece piece);

    void onChessPieceRemove(ChessBoardLocation location);

    void onChessBoardReload(ChessBoard board);

    void onChessPiecePlace2(ChessBoardLocation location, ChessPiece piece, int n);
}
