package edu.hitsz.factory;

import edu.hitsz.aircraft.ElitePlusEnemyAircraft;
import edu.hitsz.aircraft.EliteProEnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.strategy.SpreadShoot;
import edu.hitsz.strategy.StraightShoot;

public class ElitePlusEnemyFactory implements EnemyFactory{
    @Override
    public ElitePlusEnemyAircraft createEnemy() {

        int x = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()));
        int y = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05);
        int speedX = (int)(Math.random() * 4) - 2;;
        int speedY = 5;
        int hp = 100;
        int shootNum = 2;
        int power = 7;
        int direction = 1;
        ShootStrategy strategy = new StraightShoot(shootNum, power, direction);
        return new ElitePlusEnemyAircraft(x, y, speedX, speedY, hp, strategy);
    }

    @Override
    public ElitePlusEnemyAircraft createEnemy(float increasement) {

        int x = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()));
        int y = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05);
        int speedX = (int)(Math.random() * 4) - 2;;
        int speedY = 3;
        int hp = 120;
        int shootNum = 2;
        int power = 3;
        int direction = 1;
        ShootStrategy strategy = new StraightShoot(shootNum, power, direction);
        return new ElitePlusEnemyAircraft(x, y, (int)(speedX * increasement),
                (int)(speedY * increasement), (int)(hp * increasement), strategy);
    }
}
