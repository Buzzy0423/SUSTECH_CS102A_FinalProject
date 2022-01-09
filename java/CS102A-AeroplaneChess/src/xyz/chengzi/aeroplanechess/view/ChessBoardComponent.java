package xyz.chengzi.aeroplanechess.view;

import xyz.chengzi.aeroplanechess.listener.ChessBoardListener;
import xyz.chengzi.aeroplanechess.listener.InputListener;
import xyz.chengzi.aeroplanechess.listener.Listenable;
import xyz.chengzi.aeroplanechess.model.ChessBoard;
import xyz.chengzi.aeroplanechess.model.ChessBoardLocation;
import xyz.chengzi.aeroplanechess.model.ChessPiece;
import xyz.chengzi.aeroplanechess.view.Shape.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ChessBoardComponent extends JComponent implements Listenable<InputListener>, ChessBoardListener {
    public static Color Y = new Color(250, 250, 100);
    public static Color B = new Color(100, 100, 250);
    public static Color R = new Color(250, 100, 100);
    public static Color G = new Color(100, 250, 100);
    private static final Color[] BOARD_COLORS = {Y, B, G, R};
    private static final Color[] PIECE_COLORS = {Y, B, G, R};

    private final List<InputListener> listenerList = new ArrayList<>();
    private final JPanel[][] gridComponents;
    private final int dimension, endDimension;
    private final int gridSize;

    public ChessBoardComponent(int size, int dimension, int endDimension) {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setLayout(null); // Use absolute layout
        setSize(size, size);

        this.gridComponents = new JPanel[4][dimension + endDimension + 1];
        this.dimension = dimension;
        this.endDimension = endDimension;
        this.gridSize = size / dimension;
        initGridComponents();
    }

    private int gridLocation(int player, int index) {
        // FIXME: Calculate proper location for each grid
        int x = 0, y = 0;
        if (player == 1) {
            switch (index) {
                case 0:
                    x = gridSize * 13;
                    y = 0;
                    break;
                case 1:
                    x = gridSize * 15;
                    y = 0;
                    break;
                case 2:
                    x = gridSize * 15;
                    y = gridSize * 2;
                    break;
                case 3:
                    x = gridSize * 13;
                    y = gridSize * 2;
                    break;
                case 4:
                    x = gridSize * 11;
                    y = gridSize * 2;
                    break;
                case 5:
                    x = gridSize * 13;
                    y = gridSize * 4;
                    break;
                case 6:
                    x = gridSize * 15;
                    y = gridSize * 7;
                    break;
                case 7:
                    x = gridSize * 15;
                    y = gridSize * 11;
                    break;
                case 8:
                    x = gridSize * 11;
                    y = gridSize * 11;
                    break;
                case 9:
                    x = gridSize * 10;
                    y = gridSize * 15;
                    break;
                case 10:
                    x = gridSize * 6;
                    y = gridSize * 15;
                    break;
                case 11:
                    x = gridSize * 5;
                    y = gridSize * 11;
                    break;
                case 12:
                    x = 0;
                    y = gridSize * 11;
                    break;
                case 13:
                    x = 0;
                    y = gridSize * 7;
                    break;
                case 14:
                    x = gridSize * 3;
                    y = gridSize * 4;
                    break;
                case 15:
                    x = gridSize * 4;
                    y = gridSize * 2;
                    break;
                case 16:
                    x = gridSize * 8;
                    y = 0;
            }//player1坐标
        } else if (player == 0) {
            switch (index) {
                case 0:
                    x = 0;
                    y = 0;
                    break;
                case 1:
                    x = gridSize * 2;
                    y = 0;
                    break;
                case 2:
                    x = gridSize * 2;
                    y = gridSize * 2;
                    break;
                case 3:
                    x = 0;
                    y = gridSize * 2;
                    break;
                case 4:
                    x = gridSize * 2;
                    y = gridSize * 4;
                    break;
                case 5:
                    x = gridSize * 4;
                    y = gridSize * 3;
                    break;
                case 6:
                    x = gridSize * 7;
                    y = 0;
                    break;
                case 7:
                    x = gridSize * 11;
                    y = 0;
                    break;
                case 8:
                    x = gridSize * 12;
                    y = gridSize * 4;
                    break;
                case 9:
                    x = gridSize * 15;
                    y = gridSize * 6;
                    break;
                case 10:
                    x = gridSize * 15;
                    y = gridSize * 10;
                    break;
                case 11:
                    x = gridSize * 12;
                    y = gridSize * 11;
                    break;
                case 12:
                    x = gridSize * 11;
                    y = gridSize * 15;
                    break;
                case 13:
                    x = gridSize * 7;
                    y = gridSize * 15;
                    break;
                case 14:
                    x = gridSize * 4;
                    y = gridSize * 13;
                    break;
                case 15:
                    x = gridSize * 2;
                    y = gridSize * 11;
                    break;
                case 16:
                    x = 0;
                    y = gridSize * 8;
            }//player2坐标
        } else if (player == 2) {
            switch (index) {
                case 0:
                    x = gridSize * 13;
                    y = gridSize * 13;
                    break;
                case 1:
                    x = gridSize * 15;
                    y = gridSize * 13;
                    break;
                case 2:
                    x = gridSize * 15;
                    y = gridSize * 15;
                    break;
                case 3:
                    x = gridSize * 13;
                    y = gridSize * 15;
                    break;
                case 4:
                    x = gridSize * 14;
                    y = gridSize * 11;
                    break;
                case 5:
                    x = gridSize * 11;
                    y = gridSize * 13;
                    break;
                case 6:
                    x = gridSize * 9;
                    y = gridSize * 15;
                    break;
                case 7:
                    x = gridSize * 5;
                    y = gridSize * 15;
                    break;
                case 8:
                    x = gridSize * 4;
                    y = gridSize * 11;
                    break;
                case 9:
                    x = 0;
                    y = gridSize * 10;
                    break;
                case 10:
                    x = 0;
                    y = gridSize * 6;
                    break;
                case 11:
                    x = gridSize * 4;
                    y = gridSize * 4;
                    break;
                case 12:
                    x = gridSize * 4;
                    y = 0;
                    break;
                case 13:
                    x = gridSize * 9;
                    y = 0;
                    break;
                case 14:
                    x = gridSize * 11;
                    y = gridSize * 3;
                    break;
                case 15:
                    x = gridSize * 14;
                    y = gridSize * 4;
                    break;
                case 16:
                    x = gridSize * 15;
                    y = gridSize * 8;
                    break;
            }//player3坐标
        } else if (player == 3) {
            switch (index) {
                case 0:
                    x = 0;
                    y = gridSize * 13;
                    break;
                case 1:
                    x = gridSize * 2;
                    y = gridSize * 13;
                    break;
                case 2:
                    x = gridSize * 2;
                    y = gridSize * 15;
                    break;
                case 3:
                    x = 0;
                    y = gridSize * 15;
                    break;
                case 4:
                    x = gridSize * 4;
                    y = gridSize * 14;
                    break;
                case 5:
                    x = gridSize * 3;
                    y = gridSize * 11;
                    break;
                case 6:
                    x = 0;
                    y = gridSize * 9;
                    break;
                case 7:
                    x = gridSize;
                    y = gridSize * 4;
                    break;
                case 8:
                    x = gridSize * 5;
                    y = gridSize * 4;
                    break;
                case 9:
                    x = gridSize * 6;
                    y = 0;
                    break;
                case 10:
                    x = gridSize * 10;
                    y = 0;
                    break;
                case 11:
                    x = gridSize * 11;
                    y = gridSize * 4;
                    break;
                case 12:
                    x = gridSize * 15;
                    y = gridSize * 4;
                    break;
                case 13:
                    x = gridSize * 15;
                    y = gridSize * 9;
                    break;
                case 14:
                    x = gridSize * 13;
                    y = gridSize * 11;
                    break;
                case 15:
                    x = gridSize * 11;
                    y = gridSize * 14;
                    break;
                case 16:
                    x = gridSize * 8;
                    y = gridSize * 15;
            }//player4坐标
        }
        return x << 16 | y;
    }//已完成

    private int endGridLocation(int player, int index) {
        // FIXME: Calculate proper location for each end grid
        int x = 0, y = 0;
        if (player == 0) {
            if (index != 23) {
                x = gridSize * (index - dimension + 2);
                y = gridSize * 8;
            } else {
                x = 0;
                y = gridSize * 4;
            }
        } else if (player == 1) {
            if (index != 23) {
                x = gridSize * 8;
                y = gridSize * (index - dimension + 2);
            } else {
                x = gridSize * 12;
                y = 0;
            }
        } else if (player == 2) {
            if (index != 23) {
                x = gridSize * (16 - (index - dimension + 2));
                y = gridSize * 8;
            } else {
                x = gridSize * 16;
                y = gridSize * 11;
            }
        } else if (player == 3) {
            if (index != 23) {
                x = gridSize * 8;
                y = gridSize * (16 - (index - dimension + 2));
            } else {
                x = gridSize * 4;
                y = gridSize * 15;
            }
        }
        return x << 16 | y;
    }//已完成

    private void initGridComponents() {
        for (int player = 0; player < 4; player++) {
            for (int index = 0; index < dimension; index++) {
                int gridLocation = gridLocation(player, index);
                if (player == 0) {
                    switch (index) {
                        case 0:
                            gridComponents[player][index] = new AirportComponent1(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 1:
                            gridComponents[player][index] = new AirportComponent2(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 2:
                            gridComponents[player][index] = new AirportComponent3(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 3:
                            gridComponents[player][index] = new AirportComponent4(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 4:
                        case 6:
                        case 13:
                        case 15:
                            gridComponents[player][index] = new RectangleComponent(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 5:
                        case 9:
                        case 10:
                        case 14:
                        case 16:
                            gridComponents[player][index] = new RectangleComponent2(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 7:
                            gridComponents[player][index] = new HalfTriangle1(gridSize, BOARD_COLORS[player],B, player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 8:
                            gridComponents[player][index] = new HalfTriangle4(gridSize, BOARD_COLORS[player], R, player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 11:
                            gridComponents[player][index] = new HalfTriangle2(gridSize, BOARD_COLORS[player], B, player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 12:
                            gridComponents[player][index] = new TriangleComponent2(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                    }
                } else if (player == 1) {
                    switch (index) {
                        case 0:
                            gridComponents[player][index] = new AirportComponent1(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 1:
                            gridComponents[player][index] = new AirportComponent2(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 2:
                            gridComponents[player][index] = new AirportComponent3(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 3:
                            gridComponents[player][index] = new AirportComponent4(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 4:
                        case 6:
                        case 13:
                        case 15:
                            gridComponents[player][index] = new RectangleComponent2(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 5:
                        case 9:
                        case 10:
                        case 14:
                        case 16:
                            gridComponents[player][index] = new RectangleComponent(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 7:
                            gridComponents[player][index] = new HalfTriangle3(gridSize, BOARD_COLORS[player],G, player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 8:
                            gridComponents[player][index] = new HalfTriangle1(gridSize, BOARD_COLORS[player], Y, player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 11:
                            gridComponents[player][index] = new HalfTriangle4(gridSize, BOARD_COLORS[player], G, player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 12:
                            gridComponents[player][index] = new TriangleComponent3(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                    }
                } else if (player == 2) {
                    switch (index) {
                        case 0:
                            gridComponents[player][index] = new AirportComponent1(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 1:
                            gridComponents[player][index] = new AirportComponent2(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 2:
                            gridComponents[player][index] = new AirportComponent3(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 3:
                            gridComponents[player][index] = new AirportComponent4(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 4:
                        case 6:
                        case 13:
                        case 15:
                            gridComponents[player][index] = new RectangleComponent(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 5:
                        case 9:
                        case 10:
                        case 14:
                        case 16:
                            gridComponents[player][index] = new RectangleComponent2(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 7:
                            gridComponents[player][index] = new HalfTriangle2(gridSize, BOARD_COLORS[player],R, player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 8:
                            gridComponents[player][index] = new HalfTriangle3(gridSize, BOARD_COLORS[player], B, player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 11:
                            gridComponents[player][index] = new HalfTriangle1(gridSize, BOARD_COLORS[player], R, player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 12:
                            gridComponents[player][index] = new TriangleComponent4(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                    }
                } else {
                    switch (index) {
                        case 0:
                            gridComponents[player][index] = new AirportComponent1(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 1:
                            gridComponents[player][index] = new AirportComponent2(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 2:
                            gridComponents[player][index] = new AirportComponent3(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 3:
                            gridComponents[player][index] = new AirportComponent4(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 4:
                        case 6:
                        case 13:
                        case 15:
                            gridComponents[player][index] = new RectangleComponent2(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 5:
                        case 9:
                        case 10:
                        case 14:
                        case 16:
                            gridComponents[player][index] = new RectangleComponent(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 7:
                            gridComponents[player][index] = new HalfTriangle4(gridSize, BOARD_COLORS[player],Y, player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 8:
                            gridComponents[player][index] = new HalfTriangle2(gridSize, BOARD_COLORS[player], G, player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 11:
                            gridComponents[player][index] = new HalfTriangle3(gridSize, BOARD_COLORS[player], Y, player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                        case 12:
                            gridComponents[player][index] = new TriangleComponent1(gridSize, BOARD_COLORS[player], player, index);
                            gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                            add(gridComponents[player][index]);
                            break;
                    }
                }
            }
            for (int index = dimension; index < dimension + endDimension + 1; index++) {
                int gridLocation = endGridLocation(player, index);
                if (index == 23){
                    switch (player){
                        case 0:
                            gridComponents[player][index] = new HalfTriangle3(gridSize, BOARD_COLORS[player],R, player, index);
                            break;
                        case 1:
                            gridComponents[player][index] = new HalfTriangle2(gridSize, BOARD_COLORS[player],Y, player, index);
                            break;
                        case 2:
                            gridComponents[player][index] = new HalfTriangle4(gridSize, BOARD_COLORS[player],B, player, index);
                            break;
                        case 3:
                            gridComponents[player][index] = new HalfTriangle1(gridSize, BOARD_COLORS[player],G, player, index);
                    }
                }else {
                    gridComponents[player][index] = new SquareComponent(gridSize, BOARD_COLORS[player], player, index);
                }
                gridComponents[player][index].setLocation(gridLocation >> 16, gridLocation & 0xffff);
                add(gridComponents[player][index]);

            }
        }
    }//已完成

    public JPanel getGridAt(ChessBoardLocation location) {
        return gridComponents[location.getColor()][location.getIndex()];
    }

    public void setChessAtGrid(ChessBoardLocation location, Color color, int N) {
        removeChessAtGrid(location);
        getGridAt(location).add(new ChessComponent(color, location, N));
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
            if (clickedComponent instanceof S) {
                S square = (S) clickedComponent;
                ChessBoardLocation location = new ChessBoardLocation(square.getPlayer(), square.getIndex());
                for (InputListener listener : listenerList) {
                    if (clickedComponent.getComponentCount() == 0) {
                        listener.onPlayerClickSquare(location, square);
                    } else {
                        try {
                            listener.onPlayerClickChessPiece(location, (ChessComponent) square.getComponent(0));
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onChessPiecePlace(ChessBoardLocation location, ChessPiece piece) {
        setChessAtGrid(location, PIECE_COLORS[piece.getPlayer()], piece.getNumber());
        repaint();
    }

    public void onChessPiecePlace2(ChessBoardLocation location, ChessPiece piece, int N) {
        setChessAtGrid(location, PIECE_COLORS[piece.getPlayer()], piece.getNumber() + N);
    }

    @Override
    public void onChessPieceRemove(ChessBoardLocation location) {
        removeChessAtGrid(location);
        repaint();
    }

    @Override
    public void onChessBoardReload(ChessBoard board) {
        for (int color = 0; color < 4; color++) {
            for (int index = 0; index < board.getDimension() + board.getEndDimension() + 1; index++) {
                ChessBoardLocation location = new ChessBoardLocation(color, index);
                ChessPiece piece = board.getChessPieceAt(location);
                if (piece != null) {
                    setChessAtGrid(location, PIECE_COLORS[piece.getPlayer()], piece.getNumber());
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
