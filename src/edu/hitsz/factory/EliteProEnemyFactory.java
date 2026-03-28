package edu.hitsz.factory;

import edu.hitsz.aircraft.EliteProEnemy;

public class EliteProEnemyFactory implements EnemyFactory{
    @Override
    public EliteProEnemy createEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        return new EliteProEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
