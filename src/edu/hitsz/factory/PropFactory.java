package edu.hitsz.factory;

import edu.hitsz.prop.*;

public class PropFactory {
    // 静态工厂方法
    public static AbstractProp createProp(String type, int locationX, int locationY, int speedX, int speedY) {
        switch (type) {
            case "blood":
                return new BloodProp(locationX, locationY, speedX, speedY);
            case "bomb":
                return new BombProp(locationX, locationY, speedX, speedY);
            case "bullet":
                return new BulletProp(locationX, locationY, speedX, speedY);
            case "bullet_plus":
                return new BulletPlusProp(locationX, locationY, speedX, speedY);
            case "freeze":
                return new FreezeProp(locationX, locationY, speedX, speedY);
            default:
                throw new IllegalArgumentException("Unknown prop type " + type);
        }
    }
}
