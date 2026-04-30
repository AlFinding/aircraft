package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteProEnemyAircraft;
import edu.hitsz.aircraft.EnemyAircraft;

public interface EnemyFactory {
    EnemyAircraft createEnemy();
    EnemyAircraft createEnemy(float increasement);
}
