package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class StraightShoot implements ShootStrategy {
    // 射击属性
    private final int shootNum;
    private final int power;
    private final int direction;

    public StraightShoot(int shootNum, int power, int direction) {
        this.shootNum = shootNum;
        this.power = power;
        this.direction = direction;
    }

    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft) {
        List<BaseBullet> res = new LinkedList<>();
        int Y = aircraft.getLocationY() + direction*2;
        int bulletSpeedY = aircraft.getSpeedY() + direction * 5;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            int X = aircraft.getLocationX() + (i * 2 - shootNum + 1) * 10;
            if(direction == -1) {
                HeroBullet bullet = new HeroBullet(X, Y, 0, bulletSpeedY, power);
                res.add(bullet);
            } else if(direction == 1) {
                EnemyBullet bullet = new EnemyBullet(X, Y, 0, bulletSpeedY, power);
                res.add(bullet);
            }
        }
        return res;
    }
}
