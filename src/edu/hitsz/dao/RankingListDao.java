package edu.hitsz.dao;

public interface RankingListDao {
    // 展示排行榜
    String[][] showRankingList();

    // 更新排行榜
    void updateRankingList(String[][] newRankingList);

    // 新增记录
    void addRecord(RankingRecord newRecord);

    // 删除记录
    void deleteRecord();
}
