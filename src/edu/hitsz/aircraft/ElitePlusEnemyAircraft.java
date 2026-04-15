package edu.hitsz.aircraft;

import edu.hitsz.factory.PropFactory;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.strategy.ShootStrategy;

/**
 * Boss敌机
 * 可射击、掉落道具
 */

public class ElitePlusEnemyAircraft extends EnemyAircraft {

    public ElitePlusEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy strategy) {
        super(locationX, locationY, speedX, speedY, hp,  strategy);
    }

}
