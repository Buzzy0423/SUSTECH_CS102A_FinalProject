package xyz.chengzi.aeroplanechess.model;

import xyz.chengzi.aeroplanechess.listener.ChessBoardListener;
import xyz.chengzi.aeroplanechess.listener.Listenable;
import xyz.chengzi.aeroplanechess.view.Battle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChessBoard extends Component implements Listenable<ChessBoardListener> {
    private final List<ChessBoardListener> listenerList = new ArrayList<>();
    private final Square[][] grid;
    private final int dimension, endDimension;
    private int Y = 0;
    private int B = 0;
    private int G = 0;
    private int R = 0;

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public void setG(int g) {
        G = g;
    }

    public void setR(int r) {
        R = r;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getG() {
        return G;
    }

    public int getR() {
        return R;
    }

    @Override
    public int getY() {
        return Y;
    }

    public ChessBoard(int dimension, int endDimension) {
        this.grid = new Square[4][dimension + endDimension + 1];
        this.dimension = dimension;
        this.endDimension = endDimension;

        initGrid();
    }

    private void initGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < dimension + endDimension + 1; j++) {
                grid[i][j] = new Square(new ChessBoardLocation(i, j));
            }
        }
    }

    public void placeInitialPieces(int N) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < dimension + endDimension + 1; j++) {
                grid[i][j].setPiece(null);
            }
        }
        for (int counter = 0; counter < N; counter++) {
            grid[counter][0].setPiece(new ChessPiece(counter, 1));
            grid[counter][1].setPiece(new ChessPiece(counter, 1));
            grid[counter][2].setPiece(new ChessPiece(counter, 1));
            grid[counter][3].setPiece(new ChessPiece(counter, 1));
        }
        listenerList.forEach(listener -> listener.onChessBoardReload(this));
    }

    public void loadpieces(ArrayList<String> strings) {
        initGrid();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < dimension + endDimension + 1; j++) {
                grid[i][j].setPiece(null);
            }
        }
        String[] inf;
        for (String s : strings) {
            inf = s.split(" ");
            grid[Integer.parseInt(inf[2])][Integer.parseInt(inf[3])].setPiece(new ChessPiece(Integer.parseInt(inf[0]), Integer.parseInt(inf[1])));
        }
        listenerList.forEach(listener -> listener.onChessBoardReload(this));

    }

    public Square getGridAt(ChessBoardLocation location) {
        return grid[location.getColor()][location.getIndex()];
    }

    public int getDimension() {
        return dimension;
    }

    public int getEndDimension() {
        return endDimension;
    }

    public ChessPiece getChessPieceAt(ChessBoardLocation location) {
        return getGridAt(location).getPiece();
    }

    public void setChessPieceAt(ChessBoardLocation location, ChessPiece piece) {
        getGridAt(location).setPiece(piece);
        listenerList.forEach(listener -> listener.onChessPiecePlace(location, piece));
    }

    public void setChessPieceAtR(ChessBoardLocation location, ChessPiece piece, int N) {
        getGridAt(location).setPiece(piece);
        listenerList.forEach(listener -> listener.onChessPiecePlace2(location, piece, N));
    }

    public void setChessPieceAtE(ChessBoardLocation location, ChessPiece piece) {
        getGridAt(location).setPiece(piece);
        listenerList.forEach(listener -> listener.onChessPiecePlace(location, piece));

    }


    public ChessPiece removeChessPieceAt(ChessBoardLocation location) {
        ChessPiece piece = getGridAt(location).getPiece();
        getGridAt(location).setPiece(null);
        listenerList.forEach(listener -> listener.onChessPieceRemove(location));
        return piece;
    }


    public ChessBoardLocation moveChessPiece(ChessBoardLocation src, int steps, boolean I) {
        ChessBoardLocation dest = src;
        int C = getChessPieceAt(src).getPlayer();
        int num = getChessPieceAt(src).getNumber();
        int S = -1;
        int cou = 0;
        if (src.getIndex() >= 4) {//大于4正常走
            if (src.getIndex() >= 16 && C == src.getColor() && src.getIndex() != 23) {//绕圈走
                for (int counter = 0; counter < steps; counter++) {
                    if (dest.getIndex() != 22) {
                        dest = nextLocationE(dest);//向前走
                    } else {
                        break;
                    }
                    cou = counter;
                }
                for (int counter = 0; counter < steps - cou - 1; counter++) {
                    dest = nextLocationB(dest);
                }
                if (getChessPieceAt(dest) != null && dest.getIndex() != src.getIndex()) {
                    Whenmeet(src, dest, C);
                } else {
                    setChessPieceAt(dest, removeChessPieceAt(src));
                }//在终点赛道走
                Final(dest, num, C);
                return dest;
            } else if (src.getIndex() == 23) {
                ChessBoardLocation cache = null;
                switch (C){
                    case 0:
                        cache = new ChessBoardLocation(3,7);
                        break;
                    case 1:
                        cache = new ChessBoardLocation(0,7);
                        break;
                    case 2:
                        cache = new ChessBoardLocation(1,7);
                        break;
                    case 3:
                        cache = new ChessBoardLocation(2,7);
                }
                for (int counter = 0;counter < steps - 1;counter++){
                    cache = nextLocation(cache);
                }
                dest = cache;
            } else {
                for (int counter = 0; counter < steps; counter++) {
                    dest = nextLocation(dest);
                    if (dest.getIndex() == 16 && C == dest.getColor()) {
                        S = counter;
                        break;
                    }//走到相应位置16格时换前进方式
                }
                if (S != -1) {
                    for (int counter = S; counter < steps - 1; counter++) {
                        if (dest.getIndex() < 22) {
                            dest = nextLocationE(dest);//向前走
                        } else {
                            dest = nextLocationB(dest);//向后走
                        }
                    }
                    if (getChessPieceAt(dest) != null) {
                        Whenmeet(src, dest, C);
                    } else {
                        setChessPieceAt(dest, removeChessPieceAt(src));
                    }
                    Final(dest, num, C);
                }//在enddimension上前进
            }//前进太多返回
            //dest已经是最终坐标
            if (dest.getIndex() == 8 && dest.getColor() == C) {
                if (getChessPieceAt(dest) != null) {
                    setChessPieceAt(Airportspace(dest), new ChessPiece(getChessPieceAt(dest).getPlayer(), 1));
                }
                if (shortcut(src, C)) {
                    return new ChessBoardLocation(C, 11); //shortcut
                } else {
                    return dest;
                }
            } else if (dest.getColor() == C && dest.getIndex() < 16) {
                ChessBoardLocation next = new ChessBoardLocation(C, dest.getIndex() + 1);
                if (getChessPieceAt(dest) != null) {
                    Whenmeet(src, dest, C);
                    if (getChessPieceAt(next) != null) {
                        Whenmeet(dest, next, C);
                    } else {
                        setChessPieceAt(next, removeChessPieceAt(dest));
                    }//相同颜色且有棋子
                } else {
                    if (getChessPieceAt(next) != null) {
                        Whenmeet(src, next, C);
                    } else {
                        setChessPieceAt(next, removeChessPieceAt(src));
                    }
                }
                if (next.getIndex() == 8 && next.getColor() == C) {
                    if (shortcut(next, C)) {
                        return new ChessBoardLocation(C, 11);
                    } else {
                        return next;
                    }
                }//相同颜色无棋子
                return next;
            } else if (dest.getIndex() <= 16) {
                if (getChessPieceAt(dest) != null) {
                    Whenmeet(src, dest, C);
                } else {
                    setChessPieceAt(dest, removeChessPieceAt(src));
                }
            }
            return dest;
            //判断落点有没有敌方棋子，有的话送回家
        } else {
            if (I) {
                if (getChessPieceAt(new ChessBoardLocation(C, 23)) != null) {
                    Whenmeet(src, new ChessBoardLocation(C, 23), getChessPieceAt(src).getPlayer());
                } else {
                    setChessPieceAt(new ChessBoardLocation(C, 23), removeChessPieceAt(src));
                }
            }//起飞 🛫
            return new ChessBoardLocation(C, 4);
        }
    }//啊吧啊吧

    public ChessBoardLocation Airportspace(ChessBoardLocation chessBoardLocation) {
        int I = 0;
        int c = getChessPieceAt(chessBoardLocation).getPlayer();
        for (int counter = 0; counter < 4; counter++) {
            if (getChessPieceAt(grid[c][counter].getLocation()) == null) {
                I = counter;
                break;
            }
        }
        return grid[c][I].getLocation();
    }//检测飞机场哪个位置有空位

    public void Whenmeet(ChessBoardLocation src, ChessBoardLocation chessBoardLocation, int C) {
        int c = getChessPieceAt(chessBoardLocation).getPlayer();
        int N = getChessPieceAt(chessBoardLocation).getNumber();
        if (C != c) {//落在敌机上
            Battle battle = new Battle(getChessPieceAt(src).getNumber(), N);
            R = battle.result();
            if (R >> 16 == 1) {
                for (int counter = 0; counter < N; counter++) {
                    setChessPieceAt(Airportspace(chessBoardLocation), new ChessPiece(c, 1));
                }
                removeChessPieceAt(chessBoardLocation);
                setChessPieceAt(chessBoardLocation, new ChessPiece(getChessPieceAt(src).getPlayer(), R & 0xffff));
                for (int counter = 0; counter < getChessPieceAt(src).getNumber() - (R & 0xffff); counter++) {
                    setChessPieceAt(Airportspace(src), new ChessPiece(getChessPieceAt(src).getPlayer(), 1));
                }
                removeChessPieceAt(src);
            } else {
                for (int counter = 0; counter < getChessPieceAt(src).getNumber(); counter++) {
                    setChessPieceAt(Airportspace(src), new ChessPiece(getChessPieceAt(src).getPlayer(), 1));
                }
                removeChessPieceAt(src);
                for (int counter = 0; counter < N - (R & 0xffff); counter++) {
                    setChessPieceAt(Airportspace(chessBoardLocation), new ChessPiece(c, 1));
                }
                removeChessPieceAt(chessBoardLocation);
                setChessPieceAt(chessBoardLocation, new ChessPiece(c, (R & 0xffff)));
            }
        } else {//落在友机上
            ChessPiece chessPiece = getChessPieceAt(chessBoardLocation);
            int n = getChessPieceAt(chessBoardLocation).getNumber() + getChessPieceAt(src).getNumber();
            removeChessPieceAt(chessBoardLocation);
            setChessPieceAtR(chessBoardLocation, chessPiece, getChessPieceAt(src).getNumber());//更新棋子位置
            removeChessPieceAt(src);
            getChessPieceAt(chessBoardLocation).setNumber(n);//更新棋子number，其实重复了。。。
        }//去除原来的棋子
    }//判断落点是否有棋子，并进行移动

    public boolean shortcut(ChessBoardLocation src, int C) {
        int n = JOptionPane.showConfirmDialog(null, "Oops,you seem meet a shortcut, would you like to take the shortcut?", "Confirm", JOptionPane.YES_NO_OPTION); //返回值为0或1
        if (n == 0) {
            ChessBoardLocation dest = new ChessBoardLocation(C, 11);
            if (getChessPieceAt(dest) != null) {
                Whenmeet(src, dest, C);
            } else {
                setChessPieceAt(dest, removeChessPieceAt(src));
            }
            return true;
        } else {
            return false;
        }
    }

    public void Final(ChessBoardLocation dest, int num, int C) {
        if (dest.getIndex() == 22) {
            for (int counter = 0; counter < num; counter++) {
                setChessPieceAtE(Airportspace(dest), new ChessPiece(C, 6));
            }
            removeChessPieceAt(dest);
            switch (C) {
                case 0:
                    Y += num;
                    break;
                case 1:
                    B += num;
                    break;
                case 2:
                    G += num;
                    break;
                case 3:
                    R += num;
                    break;
            }
        }//到终点移回机库
    }

    public void Back(ChessBoardLocation chessBoardLocation) {
        if (getChessPieceAt(chessBoardLocation) != null) {
            int nn = getChessPieceAt(chessBoardLocation).getNumber();
            int cc = getChessPieceAt(chessBoardLocation).getPlayer();
            for (int counter = 0; counter < nn; counter++) {
                setChessPieceAt(Airportspace(chessBoardLocation), new ChessPiece(cc, 1));
            }
            removeChessPieceAt(chessBoardLocation);
        }
    }

    public ChessBoardLocation nextLocation(ChessBoardLocation location) {
        int C = location.getColor();
        int I = location.getIndex();
        if (C == 3) {
            C = 0;
        } else {
            C++;
        }
        if (I >= 4 && I < 7) {
            I += 10;
        } else {
            I -= 3;
        }
        return new ChessBoardLocation(C, I);
    }//前进到下一格

    public ChessBoardLocation nextLocationE(ChessBoardLocation location) {
        return new ChessBoardLocation(location.getColor(), location.getIndex() + 1);
    }//在end dimension上前进一格

    public ChessBoardLocation nextLocationB(ChessBoardLocation location) {
        return new ChessBoardLocation(location.getColor(), location.getIndex() - 1);
    }//在end dimension上后退一格

    public ArrayList<String> getallchesspiece() {
        ArrayList<String> chesspiece = new ArrayList<>();
        for (int counter = 0; counter <= 3; counter++) {
            for (int counter2 = 0; counter2 <= 23; counter2++) {
                ChessBoardLocation L = new ChessBoardLocation(counter, counter2);
                if (getChessPieceAt(L) != null) {
                    chesspiece.add(String.format("%d %d %d %d", getChessPieceAt(L).getPlayer(), getChessPieceAt(L).getNumber(), counter, counter2));
                }
            }
        }
        return chesspiece;
    }

    public ChessBoardLocation[][] getallchesspiecelocation() {
        int Y = 0;
        int B = 0;
        int G = 0;
        int R = 0;
        ChessBoardLocation[][] CL = new ChessBoardLocation[4][4];
        for (int counter = 0; counter <= 3; counter++) {
            for (int counter2 = 0; counter2 <= 23; counter2++) {
                ChessBoardLocation L = new ChessBoardLocation(counter, counter2);
                if (getChessPieceAt(L) != null) {
                    for (int counter3 = 0; counter3 < getChessPieceAt(L).getNumber(); counter3++) {
                        if (getChessPieceAt(L).getPlayer() == 0 && getChessPieceAt(L).getNumber() != 6) {
                            CL[getChessPieceAt(L).getPlayer()][Y] = L;
                            Y++;
                        } else if (getChessPieceAt(L).getPlayer() == 1 && getChessPieceAt(L).getNumber() != 6) {
                            CL[getChessPieceAt(L).getPlayer()][B] = L;
                            B++;
                        } else if (getChessPieceAt(L).getPlayer() == 2 && getChessPieceAt(L).getNumber() != 6) {
                            CL[getChessPieceAt(L).getPlayer()][G] = L;
                            G++;
                        } else if (getChessPieceAt(L).getPlayer() == 3 && getChessPieceAt(L).getNumber() != 6) {
                            CL[getChessPieceAt(L).getPlayer()][R] = L;
                            R++;
                        }
                    }
                }
            }
        }
        return CL;
    }

    public void removeallchesspiece() {
        for (int counter = 0; counter <= 3; counter++) {
            for (int counter2 = 0; counter2 <= 22; counter2++) {
                ChessBoardLocation L = new ChessBoardLocation(counter, counter2);
                if (getChessPieceAt(L) != null) {
                    removeChessPieceAt(L);
                }
            }
        }
    }

    @Override
    public void registerListener(ChessBoardListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void unregisterListener(ChessBoardListener listener) {
        listenerList.remove(listener);
    }
}
