package xyz.chengzi.aeroplanechess;

import xyz.chengzi.aeroplanechess.controller.GameController;
import xyz.chengzi.aeroplanechess.model.ChessBoard;
import xyz.chengzi.aeroplanechess.view.ChessBoardComponent;
import xyz.chengzi.aeroplanechess.view.GameFrame;

import javax.swing.*;

public class AeroplaneChess {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.win.uiScaleX", "96dpi");
        System.setProperty("sun.java2d.win.uiScaleY", "96dpi");
        SwingUtilities.invokeLater(() -> {
            ChessBoardComponent chessBoardComponent = new ChessBoardComponent(760, 13, 6);
            ChessBoard chessBoard = new ChessBoard(13, 6);
            GameController controller = new GameController(chessBoardComponent, chessBoard);

            GameFrame mainFrame = new GameFrame(controller);
            mainFrame.add(chessBoardComponent);
            mainFrame.setVisible(true);
            controller.initializeGame();
        });
    }
}
