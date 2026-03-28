package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.strategy.StraightShoot;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    // 唯一英雄机实例
    private static volatile HeroAircraft instance = null;
    // 每次射击发射子弹数量
    private int shootNum = 1;
    // 子弹威力
    private int power = 30;
    // 子弹射击方向 (向上发射：-1，向下发射：1)
    private int direction = -1;
    // 射击策略
    private ShootStrategy shootStrategy;
    private int resetStrategyCnt = 0;
    private int resetStrategy = 10;
    // 创建实例函数私有
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        shootStrategy = new StraightShoot(shootNum, power);
    }
    // 获取新的射击策略
    public void getStrategy(ShootStrategy newStrategy) {
        if (instance == null) {
            // 还没创建 → 先创建再设置
            instance = getInstance();
        }
        instance.shootStrategy = newStrategy;
        resetStrategyCnt = 0;
    }

    // 创建唯一实例函数
    public static HeroAircraft getInstance(){
        if(instance == null){
            synchronized(HeroAircraft.class){
                if(instance == null){
                    instance = new HeroAircraft(Main.WINDOW_WIDTH / 2,
                            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                            0, 0, 100);
                }
            }
        }
        return instance;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    @Override
    /*
      通过射击策略产生子弹
      @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        if(resetStrategyCnt++ >= resetStrategy){
            // 一定时间后恢复
            resetStrategyCnt = 0;
            shootStrategy = new StraightShoot(shootNum, power);
            instance.getStrategy(shootStrategy);
        }
        return shootStrategy.shoot(instance.locationX, instance.locationY, speedY, direction);
    }

    // 恢复血量
    public void recoverHp(int recovery_Amount){
        hp += recovery_Amount;
        if(hp >= maxHp){
            hp = maxHp;
        }
    }
}
