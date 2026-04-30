package edu.hitsz.prop;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.Game.Game;
import edu.hitsz.basic.DeviceObserver;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.ArrayList;
import java.util.List;

/**
 * 炸弹道具
 */

public class BombProp extends AlarmSubject{

    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void applyEffect(Game game){
        List<DeviceObserver> observers = new ArrayList<>();
        List<EnemyAircraft> enemy = game.getEnemy();
        List<BaseBullet> enemyBullet = game.getEnemyBullet();
        for(EnemyAircraft e : enemy){
            observers.add(e);
        }
        for(BaseBullet b : enemyBullet){
            observers.add((EnemyBullet)b);
        }
        this.addDevice(observers);
        trigger();
    }

    @Override
    public void notifyDevices(){
        for (DeviceObserver device : devices) {
            device.onBomb();
        }
    }

    @Override
    public void trigger(){
        System.out.println("==== 炸弹道具生效！====");
        notifyDevices();
    }
}
