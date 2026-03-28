package edu.hitsz.factory;

import edu.hitsz.aircraft.EliteEnemy;

public class EliteEnemyFactory implements EnemyFactory{
    @Override
    public EliteEnemy createEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        return new EliteEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
