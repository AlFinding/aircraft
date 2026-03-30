package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.strategy.SpreadShoot;

/**
 * 弹药道具
 */

public class BulletPlusProp extends AbstractProp{

    public BulletPlusProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void applyEffect(AbstractAircraft aircraft){
        if(aircraft instanceof HeroAircraft) {
            ShootStrategy newStrategy = new SpreadShoot(5, 50, -1);
            ((HeroAircraft)aircraft).getStrategy(newStrategy);
        }
    }
}
