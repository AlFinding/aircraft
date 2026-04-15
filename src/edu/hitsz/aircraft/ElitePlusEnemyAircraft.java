package edu.hitsz.aircraft;

import edu.hitsz.factory.PropFactory;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.strategy.ShootStrategy;

import java.util.LinkedList;

/**
 * Boss敌机
 * 可射击、掉落道具
 */

public class ElitePlusEnemyAircraft extends EnemyAircraft {

    public ElitePlusEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy strategy) {
        super(locationX, locationY, speedX, speedY, hp,  strategy);
    }

    @Override
    public LinkedList<AbstractProp> dropProp() {
        // 产生道具补给
        LinkedList<AbstractProp> props = new LinkedList<>();
        int speedX = 0;
        int speedY = this.getSpeedY();
        int locationX = this.getLocationX();
        int locationY = this.getLocationY();

        double seed = Math.random();
        String type;
        if (seed <= 0.4) {
            type = "blood";
        } else if (seed <= 0.65) {
            type = "bullet";
        } else if (seed <= 0.85) {
            type = "bullet_plus";
        } else {
            type = "bomb";
        }

        props.add(PropFactory.createProp(type, locationX, locationY, speedX, speedY));
        return props;
    }
}
