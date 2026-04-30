package edu.hitsz.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class RankingListDaoImpl implements RankingListDao {

    private final String[] columnName = new String[]{"ranking", "playerName", "score", "recordTime"};
    private String FILE_PATH;

    public RankingListDaoImpl(String difficulty){
        if(difficulty.equals("easy") || difficulty.equals("normal") || difficulty.equals("difficult")) {
            FILE_PATH = "./src/ranking_data/" + difficulty + "_ranking_list.txt";
        }
    }

    public String[] getColumnName(){
        return columnName;
    }

    // 展示排行榜
    @Override
    public String[][] showRankingList(){
        List<String[]> dataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                // 只添加合法行
                if (arr.length == columnName.length) {
                    dataList.add(arr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 把 List 转成 String[][]
        String[][] data = new String[dataList.size()][columnName.length];
        for (int i = 0; i < dataList.size(); i++) {
            data[i] = dataList.get(i);
        }

        return data;
    }

    // 更新排行榜
    @Override
    public void updateRankingList(String[][] newRankingList){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // 遍历每一行
            for (String[] row : newRankingList) {
                if (row == null) continue;
                // 把一行的每一列用逗号连接起来
                String line = String.join(",", row);
                // 写入文件
                bw.write(line);
                bw.newLine(); // 换行
            }
            System.out.println("保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 新增记录（追加写入文件）
    @Override
    public void addRecord(RankingRecord newRecord) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            String line = " " + "," + newRecord.getPlayerName() + "," + newRecord.getScore() + "," + newRecord.getRecordTime();
            bw.write(line);
            bw.newLine();
            System.out.println("新增记录成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 删除记录（清空排行榜文件）
    @Override
    public void deleteRecord() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // 清空文件内容
            bw.write("");
            System.out.println("清空记录成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
