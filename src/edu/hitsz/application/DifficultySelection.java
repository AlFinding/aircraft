package edu.hitsz.application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultySelection {

    // 进行的游戏
    private Game currentGame;

    private JButton Normal;
    private JButton Easy;
    private JButton Difficult;
    private JPanel MainPanel;

    public DifficultySelection() {
        Easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentGame = new Game();
                Main.cardPanel.add(currentGame);
                currentGame.action();
                Main.cardLayout.last(Main.cardPanel);
            }
        });
        Normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Difficult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DifficultySelection");
        frame.setContentPane(new DifficultySelection().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
