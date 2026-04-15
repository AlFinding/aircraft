package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;

/**
 * 冰冻道具
 */

public class FreezeProp extends AbstractProp{

    public FreezeProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    public void applyEffect(AbstractAircraft aircraft){
        System.out.println("FreezeProp apply effect!!!");
    }
}
