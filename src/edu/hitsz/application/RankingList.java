package edu.hitsz.application;

import edu.hitsz.application.Game.Game;
import edu.hitsz.application.RankingModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RankingList {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JTable listTable;
    private JLabel Title;
    private JScrollPane scrollPane;
    private JButton deleteButton;

    public RankingList(RankingModel model) {

        //JTable并不存储自己的数据，而是从表格模型那里获取它的数据
        listTable.setModel(model);
        scrollPane.setViewportView(listTable);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = listTable.getSelectedRow();
                System.out.println(row);
                int result = JOptionPane.showConfirmDialog(deleteButton,
                        "是否确定中删除？");
                if (JOptionPane.YES_OPTION == result && row != -1) {
                    model.removeRow(row);
                    model.sort();
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
