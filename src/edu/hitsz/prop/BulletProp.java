package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.strategy.StraightShoot;

/**
 * 弹药道具
 */

public class BulletProp extends AbstractProp{

    public BulletProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    public void applyEffect(AbstractAircraft aircraft){
        if(aircraft instanceof HeroAircraft) {
            ShootStrategy newStrategy = new StraightShoot(3, 50, -1);
            ((HeroAircraft)aircraft).getStrategy(newStrategy);
        }
    }
}
