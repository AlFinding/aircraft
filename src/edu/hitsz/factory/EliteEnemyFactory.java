package edu.hitsz.factory;

import edu.hitsz.aircraft.EliteEnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.strategy.StraightShoot;

public class EliteEnemyFactory implements EnemyFactory{
    @Override
    public EliteEnemyAircraft createEnemy() {

        int x = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()));
        int y = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05);
        int speedX = 0;
        int speedY = 5;
        int hp = 70;
        int shootNum = 1;
        int power = 5;
        int direction = 1;
        ShootStrategy strategy = new StraightShoot(shootNum, power, direction);
        return new EliteEnemyAircraft(x, y, speedX, speedY, hp, strategy);
    }
}
