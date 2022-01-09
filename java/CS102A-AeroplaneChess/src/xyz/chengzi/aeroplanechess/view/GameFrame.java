package xyz.chengzi.aeroplanechess.view;

import com.sun.deploy.panel.JSmartTextArea;
import xyz.chengzi.aeroplanechess.controller.GameController;
import xyz.chengzi.aeroplanechess.controller.Music;
import xyz.chengzi.aeroplanechess.listener.GameStateListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameFrame extends JFrame implements GameStateListener {
    private static final String[] PLAYER_NAMES = {"Yellow", "Blue", "Green", "Red"};

    private final JLabel statusLabel = new JLabel();
    private final JLabel statusLabel2 = new JLabel();

    public GameFrame(GameController controller) {
        controller.registerListener(this);

        setTitle("My AeroplaneChess");
        setSize(755, 880);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        statusLabel.setLocation(0, 758);
        statusLabel.setFont(statusLabel.getFont().deriveFont(18.0f));
        statusLabel.setSize(400, 20);
        add(statusLabel);
        statusLabel2.setLocation(0, 793);
        statusLabel2.setFont(statusLabel2.getFont().deriveFont(18.0f));
        statusLabel2.setSize(400, 20);
        add(statusLabel2);

        DiceSelectorComponent diceSelectorComponent = new DiceSelectorComponent();
        diceSelectorComponent.setLocation(396, 758);
        add(diceSelectorComponent);
        JMenu game = new JMenu("Game");
        JMenu save = new JMenu("Save");
        JMenu load = new JMenu("Load");
        JMenu setting = new JMenu("Setting");
        JMenu help = new JMenu("Help");
        JMenuItem Restart = new JMenuItem("Restart");
        JMenuItem Information = new JMenuItem("Information");
        JMenuItem Quicksave = new JMenuItem("Quicksave");
        JMenuItem Save = new JMenuItem("Save");
        JMenuItem Quickload = new JMenuItem("Quickload");
        JMenuItem Load = new JMenuItem("Load");
        JMenuItem Gamerules = new JMenuItem("Gamerules");
        JMenuItem Stopmusic = new JMenuItem("Stopmusic");
        JMenuItem Playmusic = new JMenuItem("Playmusic");
        JMenuItem Addrobots = new JMenuItem("Addrobots");
        JMenuBar menu = new JMenuBar();
        menu.add(game);
        menu.add(save);
        menu.add(load);
        menu.add(setting);
        menu.add(help);
        game.add(Restart);
        game.add(Information);
        game.add(Addrobots);
        save.add(Quicksave);
        save.add(Save);
        load.add(Quickload);
        load.add(Load);
        help.add(Gamerules);
        setting.add(Playmusic);
        setting.add(Stopmusic);
        Restart.addActionListener(e -> controller.initializeGame());
        Music music = new Music(new File("G.class.getClassLoader().getResource(\"logon_initial.properties\").getPath();/IdeaProjects/CS102A-AeroplaneChess/Introduction_-_Kristofer_Maddigan.wav"));
        music.play();
        music.setLoop(true);

        Save.addActionListener((e) -> {
            String s = JOptionPane.showInputDialog(null, "请输入存档名：\n", "title", JOptionPane.PLAIN_MESSAGE);
            if (s != null) {
                try {
                    controller.save(s);
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });

        Quicksave.addActionListener(e -> {
            File f = new File("/Users/lee/IdeaProjects/CS102A-AeroplaneChess/Quicksave.txt");
            f.delete();
            try {
                controller.save("Quicksave");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        Information.addActionListener(e -> JOptionPane.showMessageDialog(this, "Designed and Made by Bucky Lee"));

        Quickload.addActionListener(e -> {
            try {
                controller.load(new File("/Users/lee/IdeaProjects/CS102A-AeroplaneChess/file/Quicksave.txt"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        Load.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("/Users/lee/IdeaProjects/CS102A-AeroplaneChess/file"));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("txt", "txt"));
            File file = null;
            int result = fileChooser.showOpenDialog(Load);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            }
            try {
                controller.load(file);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        Playmusic.addActionListener(e -> {
            music.play();
            music.setLoop(true);
        });

        Stopmusic.addActionListener(e -> music.over());


        Gamerules.addActionListener(e -> {
            JFrame jf = new JFrame("Gamerules");
            jf.setSize(800, 500);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            // 创建文本区域组件
            JSmartTextArea textArea = new JSmartTextArea("Two to four players each try to get all their own plane pieces from their hangars, located at the corners of the board, into the base of their own colour in the centre of the board. Each player takes a turn by rolling the two dice. On a turn a player may do the following:\n" +
                    "* Take a piece out of the hangar onto the board. This can only be done by rolling a 6 with either of the dice (e.g., roll a 3-6, or 6-2, or 6-6).\n" +
                    "  \n" +
                    "* Move a piece that is on the board clockwise around the track. The number of spaces moved is derived from the dices with arithmetic operations, maximum 12. For example, rolling a 2 and a 4 will let you to move any one of your plane by 2, 4, 2+4=6, 4-2=2, 4*2=8, or 4/2=2 spaces. Note that a 4 and a 3 cannot make a move of int(4/3) spaces.\n" +
                    "* When a player lands on an opponent's piece, the opponent returns that piece to its hangar.\n" +
                    "* When a plane lands on a space of its own colour, it immediately jumps to the next space of its own colour. Any opposing planes sitting on these squares are sent back to their hangars.\n" +
                    "Besides the above basic rules, there are additional rules for you to implement:\n" +
                    "* There are additional shortcut squares. When a plane lands on one of these of its own colour, it may take the shortcut, and any opposing planes in the path of the shortcut are sent back to their hangars.\n" +
                    "* If the sum of the two dices is no less than 10, whether they are used to enter or move a piece, gives that player another roll. A second sum no less than 10 gives the player a third roll with enter or move. If the player rolls a third sum no less than 10, any pieces moved by the first two steps must return to their hangar and play passes to the next player.\n" +
                    "* When a plane lands on another plane in its own color, the player can choose to stack the pieces and move them as one piece until they reach the centre or are landed on by an opponent. When stacked pieces are sent back to their hangar by an opponent landing on them, they are no longer stacked.\n" +
                    "* When a plane lands on an opposing plane, players determine which gets sent back to its hangar by rolling one die, with the high roll determining the winner. When one plane attacks a stack of planes, it must battle each one by rolling the die. When a stack attacks another stack, the planes battle each other with a series of successive die rolls\n" +
                    "             \n" +
                    "until only one player occupies the square. This rule replaces the third basic rule above.");
            textArea.setLineWrap(true);                         // 自动换行
            textArea.setFont(new Font(null, Font.PLAIN, 18));   // 设置字体

            // 创建滚动面板, 指定滚动显示的视图组件(textArea), 垂直滚动条一直显示, 水平滚动条从不显示
            JScrollPane scrollPane = new JScrollPane(
                    textArea,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
            );

            jf.setContentPane(scrollPane);
            jf.setVisible(true);
        });
        setJMenuBar(menu);

        JButton divide = new JButton("/");
        divide.setLocation(618, 786);
        divide.setFont(divide.getFont().deriveFont(18.0f));
        divide.setSize(30, 30);
        add(divide);
        divide.addActionListener(e -> {
            if (controller.divide()) {
                statusLabel2.setText(String.format("You can move %d steps", controller.getrolledNumber()));
            } else {
                JOptionPane.showMessageDialog(this, "This arithmetic operation can't be done.");
            }
        });

        JButton mul = new JButton("x");
        mul.setLocation(578, 786);
        mul.setFont(mul.getFont().deriveFont(18.0f));
        mul.setSize(30, 30);
        add(mul);
        mul.addActionListener(e -> {
            if (controller.mul()) {
                statusLabel2.setText(String.format("You can move %d steps", controller.getrolledNumber()));
            } else {
                JOptionPane.showMessageDialog(this, "This arithmetic operation can't be done.");
            }
        });

        JButton reduce = new JButton("-");
        reduce.setLocation(538, 786);
        reduce.setFont(reduce.getFont().deriveFont(18.0f));
        reduce.setSize(30, 30);
        add(reduce);
        reduce.addActionListener(e -> {
            if (controller.reduce()) {
                statusLabel2.setText(String.format("You can move %d steps", controller.getrolledNumber()));
            } else {
                JOptionPane.showMessageDialog(this, "This arithmetic operation can't be done.");
            }
        });

        Addrobots.addActionListener(e -> {
            if (controller.getPlayernum() == 4) {
                JOptionPane.showMessageDialog(this, "There's a maximum of 4 player.");
            } else {
                controller.addrobots();
            }
        });

        JButton plus = new JButton("+");
        plus.setLocation(498, 786);
        plus.setFont(plus.getFont().deriveFont(18.0f));
        plus.setSize(30, 30);
        add(plus);
        plus.addActionListener(e -> {
            if (controller.plus()) {
                statusLabel2.setText(String.format("You can move %d steps", controller.getrolledNumber()));
            } else {
                JOptionPane.showMessageDialog(this, "This arithmetic operation can't be done.");
            }
        });

        JButton L = new JButton("L");
        L.setLocation(658, 786);
        L.setFont(L.getFont().deriveFont(18.0f));
        L.setSize(30, 30);
        add(L);
        L.addActionListener(e -> {
            controller.L();
            statusLabel2.setText(String.format("You can move %d steps", controller.getrolledNumber()));
        });

        JButton R = new JButton("R");
        R.setLocation(698, 786);
        R.setFont(R.getFont().deriveFont(18.0f));
        R.setSize(30, 30);
        add(R);
        R.addActionListener(e -> {
            controller.R();
            statusLabel2.setText(String.format("You can move %d steps", controller.getrolledNumber()));
        });

        JButton button = new JButton("roll");
        button.addActionListener((e) -> {
            int dice1;
            int dice2;
            if (diceSelectorComponent.isRandomDice()) {
                int D = controller.rollDice();
                dice1 = D >> 16;
                dice2 = D & 0xffff;
                if (dice1 != -1) {
                    statusLabel.setText(String.format("[%s] Rolled %c (%d) and %c (%d)",
                            PLAYER_NAMES[controller.getCurrentPlayer()], '\u267F' + dice1, dice1, '\u267F' + dice2, dice2));
                    statusLabel2.setText("Please select the operational symbol");
                } else {
                    JOptionPane.showMessageDialog(this, "You have already rolled the dice");
                }
            } else {
                dice1 = controller.setDice((Integer) diceSelectorComponent.getSelectedDice());
                statusLabel.setText(String.format("[%s] You select %d", PLAYER_NAMES[controller.getCurrentPlayer()], dice1));
                statusLabel2.setText(String.format("[%s] You select %d", PLAYER_NAMES[controller.getCurrentPlayer()], dice1));
                JOptionPane.showMessageDialog(this, "You selected " + dice1);
            }
        });
        button.setLocation(668, 756);
        button.setFont(button.getFont().deriveFont(18.0f));
        button.setSize(90, 30);
        add(button);
    }


    @Override
    public void onPlayerStartRound(int player, GameController controller) {
        if (player >= controller.getPlayernum() - controller.getRobotsnum()) {
                player = controller.getCurrentPlayer();
                statusLabel.setText(String.format("[%s] Bot is thinking.", PLAYER_NAMES[player]));
                statusLabel2.setText(String.format("[%s] Bot is thinking.", PLAYER_NAMES[player]));
                controller.robotmove();
                onPlayerEndRound(controller.getCurrentPlayer());
                controller.nextPlayer();
                int l = controller.getCurrentPlayer();
                onPlayerStartRound(l, controller);
        } else {
            statusLabel.setText(String.format("[%s] Please roll the dice", PLAYER_NAMES[player]));
            statusLabel2.setText(String.format("[%s] Please roll the dice", PLAYER_NAMES[player]));
        }
    }

    @Override
    public void onPlayerEndRound(int player) {

    }
}
