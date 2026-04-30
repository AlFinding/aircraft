package edu.hitsz.basic;

public interface DeviceObserver {
    // 炸弹爆炸后的反应
    void onBomb();
    // 冰冻生效的反应
    void onFreeze();
}
