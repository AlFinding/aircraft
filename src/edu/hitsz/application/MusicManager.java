package edu.hitsz.application;

import java.util.HashMap;
import java.util.Map;

public class MusicManager {

    // 静态存储所有正在播放的音乐
    private static final Map<String, MusicThread> musicMap = new HashMap<>();

    // 私有化构造，禁止实例化
    private MusicManager() {}

    // 播放背景音乐（循环）

    public static void playBackgroundMusic(String filename) {
        stopMusic(filename);

        MusicThread music = new MusicThread(filename);
        music.setLooping(true);
        musicMap.put(filename, music);
        music.start();
    }

    // 播放音效（不循环）
    public static void playSoundEffect(String filename) {
        stopMusic(filename);

        MusicThread music = new MusicThread(filename);
        music.setLooping(false);
        musicMap.put(filename, music);
        music.start();
    }

    // 停止指定音乐
    public static void stopMusic(String filename) {
        MusicThread music = musicMap.get(filename);
        if (music != null) {
            music.stopMusic();
            musicMap.remove(filename);
        }
    }

    // 停止所有音乐
    public static void stopAllMusic() {
        for (MusicThread music : musicMap.values()) {
            music.stopMusic();
        }
        musicMap.clear();
    }
}