package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.basic.DeviceObserver;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.strategy.SpreadShoot;

import java.util.LinkedList;
import java.util.List;

/**
 * 所有种类飞机的抽象父类
 * @author hitsz
 */
public abstract class EnemyAircraft extends AbstractAircraft implements DeviceObserver {
    // 射击策略
    private final ShootStrategy shootStrategy;

    public EnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy strategy) {
        super(locationX, locationY, speedX, speedY, hp);
        this.shootStrategy =  strategy;
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    // 掉落道具
    public LinkedList<AbstractProp> dropProp() {
        return null;
    }

    // 飞机射击方法
    public List<BaseBullet> shoot() {
        return shootStrategy.shoot(this);
    }
}


