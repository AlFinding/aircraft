package edu.hitsz.aircraft;

import edu.hitsz.factory.PropFactory;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.strategy.ShootStrategy;

import java.util.LinkedList;

/**
 * Boss敌机
 * 可射击、掉落道具
 */

public class EliteProEnemyAircraft extends EnemyAircraft {

    public EliteProEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy strategy) {
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
        if (seed <= 0.3) {
            type = "blood";
        } else if (seed <= 0.5) {
            type = "bullet";
        } else if (seed <= 0.65) {
            type = "bullet_plus";
        } else if (seed <= 0.8) {
            type = "bomb";
        } else {
            type = "freeze";
        }
        props.add(PropFactory.createProp(type, locationX, locationY, speedX, speedY));

        return props;
    }

    @Override
    public void onBomb(){
        // 损失最大血量的一半
        this.decreaseHp(this.maxHp/2);
    }

    @Override
    public void onFreeze(){
        Runnable freezeRun = () -> {
            try {
                int speedXBefore = this.speedX;
                int speedYBefore = this.speedY;
                this.speedX = speedXBefore/3;
                this.speedY = speedYBefore/3;
                Thread.sleep(5000);
                this.speedX = speedXBefore;
                this.speedY = speedYBefore;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(freezeRun).start();
    }
}
