package edu.hitsz.strategy;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class StraightShoot implements ShootStrategy {
    // 射击属性
    private int shootNum = 0;
    private int power = 0;

    public StraightShoot(int shootNum, int power) {
        this.shootNum = shootNum;
        this.power = power;
    }

    @Override
    public List<BaseBullet> shoot(int locationX, int locationY, int speedY, int direction) {
        List<BaseBullet> res = new LinkedList<>();
        BaseBullet bullet;
        int Y = locationY + direction*2;
        int bulletSpeedY = speedY + direction * 5;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new HeroBullet(locationX + (i*2 - shootNum + 1)*10, Y, 0, bulletSpeedY, power);
            res.add(bullet);
        }
        return res;
    }
}
