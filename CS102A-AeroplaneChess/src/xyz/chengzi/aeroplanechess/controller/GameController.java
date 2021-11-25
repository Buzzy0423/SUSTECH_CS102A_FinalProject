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
import xyz.chengzi.aeroplanechess.view.Shape.S;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameController implements InputListener, Listenable<GameStateListener> {
    private final List<GameStateListener> listenerList = new ArrayList<>();
    private final ChessBoardComponent view;
    private final ChessBoard model;

    private Integer rolledNumber;
    private Integer dice;
    private Integer chance = 3;
    private boolean luck;
    private int currentPlayer;
    private boolean I = false;
    private final ArrayList<ChessBoardLocation> list = new ArrayList();
    private int playernum;
    private int robotsnum;
    private ChessBoardLocation[][] robots = new ChessBoardLocation[4][4];

    public GameController(ChessBoardComponent chessBoardComponent, ChessBoard chessBoard) {
        this.view = chessBoardComponent;
        this.model = chessBoard;
        view.registerListener(this);
        model.registerListener(view);
        for (int counter = 0; counter < 4; counter++) {
            for (int counter2 = 0; counter2 < 4; counter2++) {
                robots[counter][counter2] = new ChessBoardLocation(counter, counter2);
            }
        }
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
        robotsnum = 0;
        Object[] options = new Object[]{"1", "2", "3", "4"};
        playernum = 1 + JOptionPane.showOptionDialog(null, "Please choose player number", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        model.placeInitialPieces(playernum);
        rolledNumber = null;
        currentPlayer = 0;
        listenerList.forEach(listener -> {
            try {
                listener.onPlayerStartRound(currentPlayer, this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    public int getrolledNumber() {
        return rolledNumber;
    }

    public void addrobots() {
        robotsnum++;
        playernum++;
        model.placeInitialPieces(playernum);
        rolledNumber = null;
        currentPlayer = 0;
        listenerList.forEach(listener -> {
            try {
                listener.onPlayerStartRound(currentPlayer, this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public int getPlayernum() {
        return playernum;
    }

    public int getRobotsnum() {
        return robotsnum;
    }

    public int rollDice() {
        if (dice == null) {
            int x = RandomUtil.nextInt(1, 6);
            int y = RandomUtil.nextInt(1, 6);
            if (x + y >= 10) {
                luck = true;
            }
            dice = x << 16 | y;
            return dice;
        } else {
            return -1;
        }
    }

    public int setDice(int N) {
        if (N > 5) {
            I = true;
        }
        if (N >= 10){
            luck = true;
        }
        return rolledNumber = N;
    }

    public boolean plus() {
        if (dice != null) {
            int A = dice >> 16;
            int B = dice & 0xffff;
            rolledNumber = A + B;
            if (A == 6 || B == 6) {
                I = true;
            }
            dice = null;
            return true;
        } else {
            return false;
        }
    }

    public boolean reduce() {
        if (dice != null) {
            int A = dice >> 16;
            int B = dice & 0xffff;
            if (A > B) {
                rolledNumber = A - B;
                dice = null;

            } else if (B > A) {
                rolledNumber = B - A;
                dice = null;
            } else {
                return false;
            }
            if (A == 6 || B == 6) {
                I = true;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean mul() {
        if (dice != null) {
            int A = dice >> 16;
            int B = dice & 0xffff;
            if (A * B <= 12) {
                rolledNumber = A * B;
                dice = null;
                if (A == 6 || B == 6) {
                    I = true;
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean divide() {
        if (dice != null) {
            int A = dice >> 16;
            int B = dice & 0xffff;
            if (A > B) {
                if ((float) A / (float) B % 1 == 0) {
                    rolledNumber = A / B;
                    dice = null;
                    if (A == 6 || B == 6) {
                        I = true;
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                if (((float) B / (float) A) % 1 == 0) {
                    rolledNumber = B / A;
                    dice = null;
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    public void R() {
        rolledNumber = dice & 0xffff;
        dice = null;
    }

    public void L() {
        rolledNumber = dice >> 16;
        dice = null;
    }


    public int nextPlayer() {
        rolledNumber = null;
        return currentPlayer = (currentPlayer + 1) % playernum;
    }

    public void save(String string) throws IOException {
        File file = new File(String.format("/Users/lee/IdeaProjects/CS102A-AeroplaneChess/file/%s.txt", string));
        PrintWriter pw = new PrintWriter(file);
        for (String s : model.getallchesspiece()) {
            pw.println(s);
        }
        pw.println(currentPlayer);
        pw.close();
    }

    public void load(File file) throws IOException {
        model.removeallchesspiece();
        FileReader fr = new FileReader(file);
        BufferedReader bf = new BufferedReader(fr);
        ArrayList pieces = new ArrayList();
        String str;
        while ((str = bf.readLine()) != null) {
            if (str.length() > 1) {
                pieces.add(str);
            } else {
                currentPlayer = Integer.parseInt(str);
            }
        }
        bf.close();
        fr.close();
        model.loadpieces(pieces);
        listenerList.forEach(listener -> {
            try {
                listener.onPlayerStartRound(currentPlayer, this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public void onPlayerClickSquare(ChessBoardLocation location, S component) {
        System.out.println("clicked " + location.getColor() + "," + location.getIndex());
    }


    @Override
    public void onPlayerClickChessPiece(ChessBoardLocation location, ChessComponent component) throws InterruptedException {
        if (rolledNumber != null) {
            ChessPiece piece = model.getChessPieceAt(location);
            if (piece.getPlayer() == currentPlayer && piece.getNumber() != 6) {
                ChessBoardLocation chessBoardLocation = model.moveChessPiece(location, rolledNumber, I);
                I = false;
                listenerList.forEach(listener -> listener.onPlayerEndRound(currentPlayer));
                for (int counter = 0; counter < 3; counter++) {
                    if (Checkwinner()) {
                        nextPlayer();
                    }
                }
                if (!luck) {
                    nextPlayer();
                    chance = 3;
                } else {
                    list.add(chessBoardLocation);
                    chance--;
                    luck = false;
                }
                if (chance < 1) {
                    for (Object C : list) {
                        model.Back((ChessBoardLocation) C);
                    }
                    chance = 3;
                    nextPlayer();
                    list.clear();
                }
                listenerList.forEach(listener -> {
                    try {
                        listener.onPlayerStartRound(currentPlayer, this);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        checkwin();
    }

    public boolean Checkwinner() {
        boolean S = true;
        for (int counter = 0; counter < 4; counter++) {
            if (model.getChessPieceAt(new ChessBoardLocation((currentPlayer + 1) % 4, counter)) == null) {
                S = false;
            } else {
                if (model.getChessPieceAt(new ChessBoardLocation((currentPlayer + 1) % 4, counter)).getNumber() != 6) {
                    S = false;
                }
            }
        }
        return S;
    }

    public void robotmove() {
            int N = currentPlayer;
            int d1 = RandomUtil.nextInt(1, 6);
            int d2 = RandomUtil.nextInt(1, 6);
            boolean R;
            ChessBoardLocation cache1 = null;
            ChessBoardLocation cache2 = null;
            robots = model.getallchesspiecelocation();
            if (d1 == 6 || d2 == 6) {
                R = true;
            } else {
                R = false;
            }
            if (R) {
                for (int counter2 = 0; counter2 < 4; counter2++) {
                    if (robots[N][counter2].getIndex() < 4 && model.getChessPieceAt(robots[N][counter2]).getNumber() != 6) {
                        cache1 = robots[N][counter2];
                        break;
                    }
                }
                if (cache1 != null) {
                    model.moveChessPiece(cache1, d1 + d2, true);
                } else {
                    model.moveChessPiece(robots[N][3], d1 + d2, true);
                }
                checkwin();
            } else {
                for (int counter2 = 0; counter2 < 4; counter2++) {
                    if (robots[N][counter2].getIndex() >= 4 && model.getChessPieceAt(robots[N][counter2]).getNumber() != 6) {
                        cache2 = robots[N][counter2];
                        break;
                    }
                }
                if (cache2 != null) {
                    if (cache2.getIndex() > 16) {
                        model.moveChessPiece(cache2,d1, false);
                    } else {
                        model.moveChessPiece(cache2, d1 + d2, false);
                    }
                } else {
                }
            }
            checkwin();
    }

    public void checkwin() {
        if (model.getY() == 4) {
            JOptionPane.showMessageDialog(null, "Yellow win!");
            model.setY(-1);
        } else if (model.getB() == 4) {
            JOptionPane.showMessageDialog(null, "Blue win!");
            model.setB(-1);
        } else if (model.getG() == 4) {
            JOptionPane.showMessageDialog(null, "Green win!");
            model.setG(-1);
        } else if (model.getR() == 4) {
            JOptionPane.showMessageDialog(null, "Red win!");
            model.setR(-1);
        }
        if (model.getR() == -1 && model.getG() == -1 && model.getB() == -1 && model.getY() == -1) {
            JOptionPane.showMessageDialog(null, "Game End");
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
