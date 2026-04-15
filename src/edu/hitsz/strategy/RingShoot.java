package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class RingShoot implements ShootStrategy {
    private final int shootNum; // 一圈发射子弹的数量
    private final int power;
    private final int direction;
    private final int bulletSpeed = 10; // 子弹速度

    public RingShoot(int shootNum, int power, int direction) {
        this.shootNum = shootNum;
        this.power = power;
        this.direction = direction;
    }

    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft) {
        List<BaseBullet> res = new LinkedList<>();

        // 核心位置：飞机中心
        int centerX = aircraft.getLocationX();
        int centerY = aircraft.getLocationY() + direction * 2;

        // 计算每个子弹之间的角度间隔 (弧度)
        double angleStep = 2 * Math.PI / shootNum;

        for (int i = 0; i < shootNum; i++) {
            // 当前子弹的角度
            double angle = i * angleStep;

            // 利用三角函数计算速度分量
            // vx = v * cos(θ), vy = v * sin(θ)
            int bulletSpeedX = (int) (bulletSpeed * Math.cos(angle));
            int bulletSpeedY = (int) (bulletSpeed * Math.sin(angle));

            if (direction == -1) {
                HeroBullet bullet = new HeroBullet(centerX, centerY, bulletSpeedX, bulletSpeedY, power);
                res.add(bullet);
            } else {
                EnemyBullet bullet = new EnemyBullet(centerX, centerY, bulletSpeedX, bulletSpeedY, power);
                res.add(bullet);
            }
        }
        return res;
    }
}