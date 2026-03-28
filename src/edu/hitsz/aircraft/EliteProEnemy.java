package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.strategy.SpreadShoot;

import java.util.LinkedList;
import java.util.List;

/**
 * 精英pro敌机
 * 可射击、掉落道具
 */

public class EliteProEnemy extends AbstractAircraft {

    // 每次射击发射子弹数量
    private int shootNum = 3;
    // 子弹威力
    private int power = 7;
    // 子弹射击方向 (向上发射：-1，向下发射：1)
    private int direction = 1;
    // 射击策略
    private ShootStrategy shootStrategy;

    public EliteProEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        shootStrategy = new SpreadShoot(shootNum, power);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        return shootStrategy.shoot(this.locationX, this.locationY, speedY, direction);
    }
}
