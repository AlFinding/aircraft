package edu.hitsz.dao;

public class RankingRecord {
    // 榜单内容包含名次、玩家名、得分、难度、记录时间
    private int ranking;
    private int score;
    private String recordTime;
    private String difficulty;
    private String playerName;

    public RankingRecord(int ranking, int score, String playerName, String recordTime, String difficulty) {
        this.ranking = ranking;
        this.score = score;
        this.recordTime = recordTime;
        this.difficulty = difficulty;
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
