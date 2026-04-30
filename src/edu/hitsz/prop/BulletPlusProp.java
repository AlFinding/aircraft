package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game.Game;
import edu.hitsz.strategy.RingShoot;
import edu.hitsz.strategy.ShootStrategy;

/**
 * 弹药道具
 */

public class BulletPlusProp extends AbstractProp{

    public BulletPlusProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void applyEffect(Game game){
        HeroAircraft hero = game.getHero();
        ShootStrategy newStrategy = new RingShoot(10, 50, -1);
        hero.getStrategy(newStrategy);
    }

    @Override
    public void effectExpire(Game game){
        HeroAircraft hero = game.getHero();
        hero.getStrategy(null);
    }
}
