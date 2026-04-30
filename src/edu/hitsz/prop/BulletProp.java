package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game.Game;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.strategy.SpreadShoot;

/**
 * 弹药道具
 */

public class BulletProp extends AbstractProp{

    public BulletProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void applyEffect(Game game){
        HeroAircraft hero = game.getHero();
        ShootStrategy newStrategy = new SpreadShoot(3, 50, -1);
        hero.getStrategy(newStrategy);
    }

    @Override
    public void effectExpire(Game game){
        HeroAircraft hero = game.getHero();
        hero.getStrategy(null);
    }
}
