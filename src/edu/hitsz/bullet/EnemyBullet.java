package edu.hitsz.bullet;

import edu.hitsz.basic.DeviceObserver;

/**
 * 敌机子弹
 * @Author hitsz
 */
public class EnemyBullet extends BaseBullet implements DeviceObserver {

    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
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
