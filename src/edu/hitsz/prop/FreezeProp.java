package edu.hitsz.prop;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.Game.Game;
import edu.hitsz.basic.DeviceObserver;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.ArrayList;
import java.util.List;

/**
 * 冰冻道具
 */

public class FreezeProp extends AlarmSubject{

    public FreezeProp(int locationX, int locationY, int speedX, int speedY){
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
            device.onFreeze();
        }
    }

    @Override
    public void trigger(){
        System.out.println("==== 冰冻道具生效！====");
        notifyDevices();
    }
}
