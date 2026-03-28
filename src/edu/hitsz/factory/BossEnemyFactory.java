package edu.hitsz.factory;

import edu.hitsz.aircraft.BossEnemy;

public class BossEnemyFactory implements EnemyFactory{
    @Override
    public BossEnemy createEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        return new BossEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
