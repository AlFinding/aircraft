package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;

/**
 * 给英雄机回血的道具
 */

public class BloodProp extends AbstractProp{

    static int maxHpRecoveryAmount = 100;

    public BloodProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void applyEffect(AbstractAircraft aircraft){
        if(aircraft instanceof  HeroAircraft) {
            // 随机恢复血量
            ((HeroAircraft)aircraft).recoverHp((int) (Math.random() * maxHpRecoveryAmount));
        }
    }
}
