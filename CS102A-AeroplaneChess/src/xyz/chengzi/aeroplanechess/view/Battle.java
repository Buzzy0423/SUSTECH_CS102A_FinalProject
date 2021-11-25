package xyz.chengzi.aeroplanechess.view;

import xyz.chengzi.aeroplanechess.util.RandomUtil;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Battle extends JDialog {
    int result;
    int cache1;
    int cache2;
    int n1;
    int n2;
    int l = 0;
    int r = 0;
    int counter = 0;
    ArrayList<Integer> dice1 = new ArrayList();
    ArrayList<Integer> dice2 = new ArrayList();

    public Battle(int N1, int N2) {
        n1 = N1;
        n2 = N2;
        while (n1 != 0 && n2 != 0){
            cache1 = RandomUtil.nextInt(1,6);
            cache2 = RandomUtil.nextInt(1,6);
            if (cache1 > cache2){
                n2--;
            }else if (cache2 > cache1){
                n1--;
            }
            dice1.add(cache1);
            dice2.add(cache2);
        }
        if (n1 == 0){
            result =  2 << 16 | n2;
        }
        if (n2 == 0){
            result =  1 << 16 | n1;
        }
    }
    public int result(){
        setLayout(null);
        setVisible(true);
        setTitle("Battle");
        setSize(700, 800);
        setLocationRelativeTo(null);
        setModal(true);
        JButton jb = new JButton("Roll!");
        JLabel L = new JLabel();
        JLabel R = new JLabel();
        JLabel Tip = new JLabel();
        JLabel score = new JLabel();
        add(jb);
        add(L);
        add(R);
        add(Tip);
        add(score);
        score.setLocation(320, 250);
        score.setFont(score.getFont().deriveFont(30.0f));
        score.setSize(500,100);
        score.setText("0 : 0");
        L.setLocation(80, 100);
        L.setFont(L.getFont().deriveFont(30.0f));
        L.setSize(300, 100);
        L.setText("--");
        R.setLocation(450, 100);
        R.setFont(R.getFont().deriveFont(30.0f));
        R.setSize(350, 100);
        R.setText("--");
        Tip.setLocation(190, 350);
        Tip.setFont(Tip.getFont().deriveFont(26.0f));
        Tip.setSize(700, 100);
        jb.setBounds(225, 600, 250, 100);
        jb.addActionListener(e -> {
                if (dice1.get(counter) > dice2.get(counter)){
                    L.setText(String.format("Attacker\n" +
                            "  %c",'\u267F' + dice1.get(counter)));
                    R.setText(String.format("Defender\n" +
                            "  %c",'\u267F' + dice2.get(counter)));
                    Tip.setText("Attacker win this round!");
                    l++;
                    score.setText(String.format("%d : %d",l,r));
                }else if (dice2.get(counter) > dice1.get(counter)) {
                    L.setText(String.format("Attacker\n" +
                            "  %c",'\u267F' + dice1.get(counter)));
                    R.setText(String.format("Defender\n" +
                            "  %c",'\u267F' + dice2.get(counter)));
                    Tip.setText("Defender win this round!");
                    r++;
                    score.setText(String.format("%d : %d",l,r));
                }else {
                    L.setText(String.format("Attacker\n" +
                            "  %c",'\u267F' + dice1.get(counter)));
                    R.setText(String.format("Defender\n" +
                            "  %c",'\u267F' + dice2.get(counter)));
                    Tip.setText("Oops,please roll again.");
                }

                if (n1 == 0 && counter == dice1.size() - 1){
                    Tip.setText(String.format("Defender win!\nPlease close the window."));
                }
                if (n2 == 0 && counter == dice1.size() - 1){
                    Tip.setText(String.format("Attacker win!\nPlease close the window."));
                }
                counter++;
        });
        return result;
    }
}
