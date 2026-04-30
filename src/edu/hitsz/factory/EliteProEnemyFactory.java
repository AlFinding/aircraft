package edu.hitsz.factory;

import edu.hitsz.aircraft.EliteProEnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.strategy.SpreadShoot;

public class EliteProEnemyFactory implements EnemyFactory{
    @Override
    public EliteProEnemyAircraft createEnemy() {
        int x = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()));
        int y = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05);
        int speedX = (int)(Math.random() * 6) - 3;;
        int speedY = 5;
        int hp = 150;
        int shootNum = 3;
        int power = 5;
        int direction = 1;
        ShootStrategy strategy = new SpreadShoot(shootNum, power, direction);
        return new EliteProEnemyAircraft(x, y, speedX, speedY, hp, strategy);
    }

    @Override
    public EliteProEnemyAircraft createEnemy(float increasement) {
        int x = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()));
        int y = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05);
        int speedX = (int)(Math.random() * 6) - 3;;
        int speedY = 6;
        int hp = 150;
        int shootNum = 3;
        int power = 10;
        int direction = 1;
        ShootStrategy strategy = new SpreadShoot(shootNum, power, direction);
        return new EliteProEnemyAircraft(x, y, (int)(speedX * increasement),
                (int)(speedY * increasement), (int)(hp * increasement), strategy);
    }
}
