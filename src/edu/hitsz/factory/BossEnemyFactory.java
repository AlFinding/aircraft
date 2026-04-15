package edu.hitsz.factory;

import edu.hitsz.aircraft.BossEnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.strategy.RingShoot;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.strategy.SpreadShoot;

public class BossEnemyFactory implements EnemyFactory{
    @Override
    public BossEnemyAircraft createEnemy() {

        int x = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()));
        int y = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05);
        int speedX = (int)(Math.random() * 6) - 3;
        int speedY = 0;
        int hp = 500;
        int shootNum = 20;
        int power = 10;
        int direction = 1;
        ShootStrategy strategy = new RingShoot(shootNum, power, direction);
        return new BossEnemyAircraft(x, y, speedX, speedY, hp, strategy);
    }
}
