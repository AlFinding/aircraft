package edu.hitsz.factory;

import edu.hitsz.aircraft.MobEnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.strategy.StraightShoot;

public class MobEnemyFactory implements EnemyFactory{
    @Override
    public MobEnemyAircraft createEnemy() {

        int x = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()));
        int y = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05);
        int speedX = 0;
        int speedY = 3;
        int hp = 50;
        int shootNum = 0;
        int power = 0;
        int direction = 1;
        ShootStrategy strategy = new StraightShoot(shootNum, power, direction);
        return new MobEnemyAircraft(x, y, speedX, speedY, hp, strategy);
    }
}
