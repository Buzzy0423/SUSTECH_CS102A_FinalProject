package xyz.chengzi.aeroplanechess.view;

import xyz.chengzi.aeroplanechess.listener.ChessBoardListener;
import xyz.chengzi.aeroplanechess.listener.InputListener;
import xyz.chengzi.aeroplanechess.listener.Listenable;
import xyz.chengzi.aeroplanechess.model.ChessBoard;
import xyz.chengzi.aeroplanechess.model.ChessBoardLocation;
import xyz.chengzi.aeroplanechess.model.ChessPiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ChessBoardComponent extends JComponent implements Listenable<InputListener>, ChessBoardListener {
    private static final Color[] BOARD_COLORS = {Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED};
    private static final Color[] PIECE_COLORS = {Color.YELLOW.darker(), Color.BLUE.darker(),
            Color.GREEN.darker(), Color.RED.darker()};

    private final List<InputListener> listenerList = new ArrayList<>();
    private final SquareComponent[][] gridComponents;
    private final int dimension, endDimension;
    private final int gridSize;

    public ChessBoardComponent(int size, int dimension, int endDimension) {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setLayout(null); // Use absolute layout
        setSize(size, size);

        this.gridComponents = new SquareComponent[4][dimension + endDimension];
        this.dimension = dimension;
        this.endDimension = endDimension;
        this.gridSize = size / (dimension + 1);
        initGridComponents();
    }

    private int gridLocation(int player, int index) {
        // FIXME: Calculate proper location for each grid
        int boardIndex = (1 + 13 * player + 4 * index) % (4 * dimension);
        int x, y;
        if (boardIndex < dimension) {
            x = boardIndex * gridSize;
            y = 0;
        } else if (boardIndex < 2 * dimension) {
            x = dimension * gridSize;
            y = (boardIndex - dimension) * gridSize;
        } else if (boardIndex < 3 * dimension) {
            x = (3 * dimension - boardIndex) * gridSize;
            y = dimension * gridSize;
        } else {
            x = 0;
            y = (4 * dimension - boardIndex) * gridSize;
        }
        return x << 16 | y;
    }

    private int endGridLocation(int player, int index) {
        // FIXME: Calculate proper location for each end grid
        int beforeEndGridLocation = gridLocation(player, dimension - 1);
        int x = beforeEndGridLocation >> 16, y = beforeEndGridLocation & 0xffff;
        if (y == 0) {
            y += (index + 1) * gridSize;
        } else if (x == 0) {
            x += (index + 1) * gridSize;
        } else if (y == dimension * gridSize) {
            y -= (index + 1) * gridSize;
        } else {
            x -= (index + 1) * gridSize;
        }
        return x << 16 | y;
    }

    private void initGridComponents() {
        for (int player = 0; player < 4; player++) {
            for (int index = 0; index < dimension; index++) {
                int gridLocation = gridLocation(player, index);
                gridComponents[player][index] = new SquareComponent(gridSize, BOARD_COLORS[player], player, index);
                gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                add(gridComponents[player][index]);
            }
            for (int index = dimension; index < dimension + endDimension; index++) {
                int gridLocation = endGridLocation(player, index - dimension);
                gridComponents[player][index] = new SquareComponent(gridSize, BOARD_COLORS[player], player, index);
                gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                add(gridComponents[player][index]);
            }
        }
    }

    public SquareComponent getGridAt(ChessBoardLocation location) {
        return gridComponents[location.getColor()][location.getIndex()];
    }

    public void setChessAtGrid(ChessBoardLocation location, Color color) {
        removeChessAtGrid(location);
        getGridAt(location).add(new ChessComponent(color));
    }

    public void removeChessAtGrid(ChessBoardLocation location) {
        // Note: re-validation is required after remove / removeAll
        getGridAt(location).removeAll();
        getGridAt(location).revalidate();
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);

        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            JComponent clickedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            if (clickedComponent instanceof SquareComponent) {
                SquareComponent square = (SquareComponent) clickedComponent;
                ChessBoardLocation location = new ChessBoardLocation(square.getPlayer(), square.getIndex());
                for (InputListener listener : listenerList) {
                    if (clickedComponent.getComponentCount() == 0) {
                        listener.onPlayerClickSquare(location, square);
                    } else {
                        listener.onPlayerClickChessPiece(location, (ChessComponent) square.getComponent(0));
                    }
                }
            }
        }
    }

    @Override
    public void onChessPiecePlace(ChessBoardLocation location, ChessPiece piece) {
        setChessAtGrid(location, PIECE_COLORS[piece.getPlayer()]);
        repaint();
    }

    @Override
    public void onChessPieceRemove(ChessBoardLocation location) {
        removeChessAtGrid(location);
        repaint();
    }

    @Override
    public void onChessBoardReload(ChessBoard board) {
        for (int color = 0; color < 4; color++) {
            for (int index = 0; index < board.getDimension(); index++) {
                ChessBoardLocation location = new ChessBoardLocation(color, index);
                ChessPiece piece = board.getChessPieceAt(location);
                if (piece != null) {
                    setChessAtGrid(location, PIECE_COLORS[piece.getPlayer()]);
                } else {
                    removeChessAtGrid(location);
                }
            }
        }
        repaint();
    }

    @Override
    public void registerListener(InputListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void unregisterListener(InputListener listener) {
        listenerList.remove(listener);
    }
}
