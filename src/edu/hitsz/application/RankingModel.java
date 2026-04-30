package edu.hitsz.application;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 自定义的model便于动态维护每一局的排行榜数据
public class RankingModel extends DefaultTableModel {

    String[] columnName;

    public RankingModel(String[][] tableData, String[] columnName) {
        super(tableData, columnName);
        this.columnName = columnName;
    }

    public void sort() {
        // 把所有行取出来
        List<String[]> rows = new ArrayList<>();
        for (int i = 0; i < this.getRowCount(); i++) {
            String[] newRow = new String[columnName.length];
            for (int j = 0; j < columnName.length; j++) {
                newRow[j] = this.getValueAt(i, j).toString();
            }
            rows.add(newRow);
        }

        // 按成绩排序（数字升序）
        final int scoreCol = 2;
        Collections.sort(rows, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int s1 = Integer.parseInt(o1[scoreCol]);
                int s2 = Integer.parseInt(o2[scoreCol]);
                return Integer.compare(s2, s1);  // 升序
            }
        });

        // 清空原来的表格
        this.setRowCount(0);
        // 把排好序的行加回去
        final int rankCol = 0;
        for (int i = 0; i < rows.size(); i++) {
            rows.get(i)[rankCol] = Integer.toString(i + 1);
            this.addRow(rows.get(i));
        }
    }
}
