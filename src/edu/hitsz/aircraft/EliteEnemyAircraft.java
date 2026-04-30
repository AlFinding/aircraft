package edu.hitsz.aircraft;

import edu.hitsz.factory.PropFactory;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.strategy.ShootStrategy;

/**
 * Boss敌机
 * 可射击、掉落道具
 */

public class EliteEnemyAircraft extends EnemyAircraft {

    public EliteEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy strategy) {
        super(locationX, locationY, speedX, speedY, hp,  strategy);
    }

    @Override
    public void onBomb(){
        this.vanish();
    }

    @Override
    public void onFreeze(){
        Runnable freezeRun = () -> {
            try {
                int speedXBefore = this.speedX;
                int speedYBefore = this.speedY;
                this.speedX = 0;
                this.speedY = 0;
                Thread.sleep(4000);
                this.speedX = speedXBefore;
                this.speedY = speedYBefore;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(freezeRun).start();
    }
}
