package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public interface ShootStrategy {
    // 射击策略接口
    List<BaseBullet> shoot(AbstractAircraft aircraft);
}
