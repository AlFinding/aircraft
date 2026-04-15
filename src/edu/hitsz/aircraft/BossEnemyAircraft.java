package edu.hitsz.aircraft;

import edu.hitsz.factory.PropFactory;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.strategy.SpreadShoot;

import java.util.LinkedList;

/**
 * Boss敌机
 * 可射击、掉落道具
 */

public class BossEnemyAircraft extends EnemyAircraft {

    private int propDropNum = 3;

    public BossEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy strategy) {
        super(locationX, locationY, speedX, speedY, hp,  strategy);
    }

    @Override
    public LinkedList<AbstractProp> dropProp() {
        // 产生道具补给
        LinkedList<AbstractProp> props = new LinkedList<>();
        int speedY = 5;
        int locationY = this.getLocationY();

        for(int i = 0; i < propDropNum; i++){
            double seed = Math.random();
            String type;
            if (seed <= 0.4) {
                type = "blood";
            } else if (seed <= 0.65) {
                type = "bullet";
            } else if (seed <= 0.85) {
                type = "bullet_plus";
            } else if (seed <= 0.95) {
                type = "bomb";
            } else {
                type = "freeze";
            }
            int locationX = this.getLocationX() + (propDropNum/2 - i + 1)*20;
            props.add(PropFactory.createProp(type, locationX, locationY, 0, speedY));
        }

        return props;
    }
}
