package edu.hitsz.factory;

import edu.hitsz.aircraft.ElitePlusEnemy;

public class ElitePlusEnemyFactory implements EnemyFactory{
    @Override
    public ElitePlusEnemy createEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        return new ElitePlusEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
