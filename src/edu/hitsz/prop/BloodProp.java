package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game.Game;

/**
 * 给英雄机回血的道具
 */

public class BloodProp extends AbstractProp{

    static int maxHpRecoveryAmount = 100;

    public BloodProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void applyEffect(Game game){
        HeroAircraft hero = game.getHero();
        // 随机恢复血量
        hero.recoverHp((int) (Math.random() * maxHpRecoveryAmount));
    }
}
