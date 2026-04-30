package edu.hitsz.application.Game;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.factory.EnemyFactory;

public class EasyGame extends Game {
    public EasyGame() {
        super("easy");
        this.bossNumMax = 0;
        this.enemyMaxNumber = 4;
        this.enemySpawnCycle = 70;
        this.shootCycle = 25;
    }

    @Override
    public EnemyAircraft getEnemyRandom(double seed) {
        EnemyFactory factory;
        // 按概率区间选择敌机
        if (seed <= 0.4) {
            // 普通敌机
            factory = this.mobEnemyFactory;
        } else if (seed <= 0.7) {
            // 精英敌机
            factory = eliteEnemyFactory;
        } else if (seed <= 0.9) {
            // 精英+敌机
            factory = elitePlusEnemyFactory;
        } else {
            // 精英Pro敌机
            factory = eliteProEnemyFactory;
        }
        return factory.createEnemy();
    }
}
