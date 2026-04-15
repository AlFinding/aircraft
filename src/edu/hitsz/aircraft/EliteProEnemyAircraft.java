package edu.hitsz.aircraft;

import edu.hitsz.factory.PropFactory;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.strategy.ShootStrategy;

/**
 * Boss敌机
 * 可射击、掉落道具
 */

public class EliteProEnemyAircraft extends EnemyAircraft {

    public EliteProEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy strategy) {
        super(locationX, locationY, speedX, speedY, hp,  strategy);
    }

    @Override
    public AbstractProp dropProp() {
        // 获得分数，产生道具补给
        int speedX = 0;
        int speedY = this.getSpeedY();
        int locationX = this.getLocationX();
        int locationY = this.getLocationY();
        double seed = Math.random();
        String type;
        if (seed <= 0.5) {
            type = "blood";
        } else if (seed <= 0.7) {
            type = "bullet";
        } else if (seed <= 0.9) {
            type = "bullet_plus";
        } else {
            type = "bomb";
        }
        return PropFactory.createProp(type, locationX, locationY, speedX, speedY);
    }
}
