package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class SpreadShoot implements ShootStrategy {
    // 射击属性
    private final int shootNum;
    private final int power;
    private final int direction;
    private final int speedXMax = 3;

    public SpreadShoot(int shootNum, int power, int direction) {
        this.shootNum = shootNum;
        this.power = power;
        this.direction = direction;
    }

    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft) {
        List<BaseBullet> res = new LinkedList<>();
        int centerX = aircraft.getLocationX();
        int centerY = aircraft.getLocationY() + direction * 2;
        int bulletSpeedY = aircraft.getSpeedY() + direction * 5;

        // 假设 speedX 范围是从 -speedXMax 到 speedXMax
        double speedStep = (shootNum <= 1) ? 0 : (2.0 * speedXMax) / (shootNum - 1);

        for (int i = 0; i < shootNum; i++) {
            int bulletSpeedX = (int) Math.round(-speedXMax + (i * speedStep));
            int X = (int) (centerX + (i - (shootNum - 1) / 2.0) * 10);
            if (direction == -1) {
                res.add(new HeroBullet(X, centerY, bulletSpeedX, bulletSpeedY, power));
            } else {
                res.add(new EnemyBullet(X, centerY, bulletSpeedX, bulletSpeedY, power));
            }
        }
        return res;
    }
}
