package xyz.chengzi.aeroplanechess.controller;

import xyz.chengzi.aeroplanechess.listener.GameStateListener;
import xyz.chengzi.aeroplanechess.listener.InputListener;
import xyz.chengzi.aeroplanechess.listener.Listenable;
import xyz.chengzi.aeroplanechess.model.ChessBoard;
import xyz.chengzi.aeroplanechess.model.ChessBoardLocation;
import xyz.chengzi.aeroplanechess.model.ChessPiece;
import xyz.chengzi.aeroplanechess.util.RandomUtil;
import xyz.chengzi.aeroplanechess.view.ChessBoardComponent;
import xyz.chengzi.aeroplanechess.view.ChessComponent;
import xyz.chengzi.aeroplanechess.view.SquareComponent;

import java.util.ArrayList;
import java.util.List;

public class GameController implements InputListener, Listenable<GameStateListener> {
    private final List<GameStateListener> listenerList = new ArrayList<>();
    private final ChessBoardComponent view;
    private final ChessBoard model;

    private Integer rolledNumber;
    private int currentPlayer;

    public GameController(ChessBoardComponent chessBoardComponent, ChessBoard chessBoard) {
        this.view = chessBoardComponent;
        this.model = chessBoard;

        view.registerListener(this);
        model.registerListener(view);
    }

    public ChessBoardComponent getView() {
        return view;
    }

    public ChessBoard getModel() {
        return model;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void initializeGame() {
        model.placeInitialPieces();
        rolledNumber = null;
        currentPlayer = 0;
        listenerList.forEach(listener -> listener.onPlayerStartRound(currentPlayer));
    }

    public int rollDice() {
        if (rolledNumber == null) {
            return rolledNumber = RandomUtil.nextInt(1, 6);
        } else {
            return -1;
        }
    }

    public int nextPlayer() {
        rolledNumber = null;
        return currentPlayer = (currentPlayer + 1) % 4;
    }


    @Override
    public void onPlayerClickSquare(ChessBoardLocation location, SquareComponent component) {
        System.out.println("clicked " + location.getColor() + "," + location.getIndex());
    }

    @Override
    public void onPlayerClickChessPiece(ChessBoardLocation location, ChessComponent component) {
        if (rolledNumber != null) {
            ChessPiece piece = model.getChessPieceAt(location);
            if (piece.getPlayer() == currentPlayer) {
                model.moveChessPiece(location, rolledNumber);
                listenerList.forEach(listener -> listener.onPlayerEndRound(currentPlayer));
                nextPlayer();
                listenerList.forEach(listener -> listener.onPlayerStartRound(currentPlayer));
            }
        }
    }

    @Override
    public void registerListener(GameStateListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void unregisterListener(GameStateListener listener) {
        listenerList.remove(listener);
    }
}
