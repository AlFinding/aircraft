package edu.hitsz.application.Game;

import edu.hitsz.aircraft.BossEnemyAircraft;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.factory.EnemyFactory;

import java.awt.*;

public class DifficultGame extends Game {
    private long startTime = 0;
    private long nowTime = 0;
    private long lastTime = 0;

    public DifficultGame() {
        super("difficult");
        this.bossNumMax = 1;
        this.scoreLimit = 100;
        this.enemyMaxNumber = 8;
        this.enemySpawnCycle = 50;
        this.shootCycle = 15;
        this.startTime = System.currentTimeMillis()/1000;
        this.nowTime = startTime;
        this.lastTime = this.nowTime;
    }

    @Override
    public void difficultyIncrease() {
        // 获取当前时间（秒）
        this.nowTime = System.currentTimeMillis() / 1000;

        // 只有 过去1秒 才更新一次难度 + 打印
        if (this.nowTime - this.lastTime >= 1) {

            // ===== 每秒只执行一次 =====
            if(this.shootCycle >= 8){
                this.shootCycle -= 0.05;
            }
            if(this.enemySpawnCycle >= 20){
                this.enemySpawnCycle -= 0.5;
            }
            if(this.enemyAttributeRate <= 2){
                this.enemyAttributeRate = 1 + (this.nowTime - this.startTime) * 0.01f;
            }

            // 打印日志
            System.out.println("！！！！！！！提高难度！！！！！！！");
            System.out.println("敌机产生周期： " + this.enemySpawnCycle);
            System.out.println("敌机属性提升倍率： " + this.enemyAttributeRate);
            System.out.println("射击周期： " + this.shootCycle);

            // 更新最后触发时间
            this.lastTime = this.nowTime;
        }
    }

    @Override
    public EnemyAircraft getEnemyRandom(double seed) {
        EnemyFactory factory;
        // 按概率区间选择敌机
        if (seed <= 0.3) {
            // 精英敌机
            factory = eliteEnemyFactory;
        } else if (seed <= 0.6) {
            // 精英+敌机
            factory = elitePlusEnemyFactory;
        } else if (seed <= 0.9) {
            // 精英Pro敌机
            factory = eliteProEnemyFactory;
        } else {
            // BOSS敌机
            // 要分数大于阈值才生成boss敌机
            if(this.score >= scoreLimit && this.bossNum < bossNumMax) {
                factory = bossEnemyFactory;
                this.bossNum++;
                this.bossEver++;
            } else {
                factory = mobEnemyFactory;
            }
        }

        EnemyAircraft enemy = factory.createEnemy(this.enemyAttributeRate);
        if(enemy instanceof BossEnemyAircraft){
            int deltaHp = (int)(enemy.getHp() * (this.bossEver - 1) * 0.5);
            ((BossEnemyAircraft)enemy).increaseHp(deltaHp);
            System.out.println("Boss敌机血量提升为:" + enemy.getHp());
        }
        return enemy;
    }

    @Override
    public void paintBackground(Graphics g) {
        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_DIFFICULT_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_DIFFICULT_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }
    }
}



