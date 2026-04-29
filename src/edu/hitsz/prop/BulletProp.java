package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.strategy.SpreadShoot;
import edu.hitsz.strategy.StraightShoot;

/**
 * 弹药道具
 */

public class BulletProp extends AbstractProp{

    public BulletProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void applyEffect(AbstractAircraft aircraft){
        if(aircraft instanceof HeroAircraft) {
            ShootStrategy newStrategy = new SpreadShoot(3, 50, -1);
            ((HeroAircraft)aircraft).getStrategy(newStrategy);
        }
    }

    @Override
    public void effectExpire(AbstractAircraft aircraft){
        if(aircraft instanceof HeroAircraft) {
            ((HeroAircraft)aircraft).getStrategy(null);
        }
    }
}
