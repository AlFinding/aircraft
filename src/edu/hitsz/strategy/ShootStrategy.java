package edu.hitsz.strategy;

import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public interface ShootStrategy {
    // 射击策略接口
    public List<BaseBullet> shoot(int locationX, int locationY, int speedY, int direction);
}
