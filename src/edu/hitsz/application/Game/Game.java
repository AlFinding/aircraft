package edu.hitsz.application.Game;

import edu.hitsz.aircraft.*;
import edu.hitsz.application.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.dao.RankingListDaoImpl;
import edu.hitsz.factory.*;
import edu.hitsz.prop.AbstractProp;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.Timer;

/**
 * 游戏主面板，游戏启动
 * @author hitsz
 */
public abstract class Game extends JPanel {

    protected int backGroundTop = 0;

    //调度器, 用于定时任务调度
    private final Timer timer;
    //时间间隔(ms)，控制刷新频率
    private final int timeInterval = 20;

    //所有飞行物实例
    private final HeroAircraft heroAircraft;
    private final List<EnemyAircraft> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<AbstractProp>  props;

    //工厂实例
    protected final MobEnemyFactory mobEnemyFactory;
    protected final EliteEnemyFactory eliteEnemyFactory;
    protected final ElitePlusEnemyFactory elitePlusEnemyFactory;
    protected final EliteProEnemyFactory eliteProEnemyFactory;
    protected final BossEnemyFactory bossEnemyFactory;

    // 排行榜数据Dao
    private RankingListDaoImpl rankingListDao;
    // Tablemodel用于实时接收更改操作数据
    public RankingModel model;

    //出现Boss敌机的分数阈值
    protected int scoreLimit = 50;

    //Boss敌机的最大数量
    protected int bossNum = 0;
    protected int bossEver = 0;
    protected int bossNumMax = 1;
    private static Boolean bossBgmStartFlag = false;

    // bgm开启标志
    private static Boolean bgmStartFlag = false;

    //屏幕中出现的敌机最大数量
    protected int enemyMaxNumber = 5;

    //敌机生成周期
    protected double enemySpawnCycle  =  50;
    private int enemySpawnCounter = 0;

    // 敌机属性倍率
    protected float enemyAttributeRate = 1;

    //英雄机和敌机射击周期
    protected double shootCycle = 10;
    private int shootCounter = 0;

    //当前玩家分数
    protected int score = 0;

    //游戏结束标志
    private boolean gameOverFlag = false;

    public Game(String difficulty) {
        heroAircraft = HeroAircraft.getInstance();
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        props = new LinkedList<>();

        mobEnemyFactory = new MobEnemyFactory();
        eliteEnemyFactory = new EliteEnemyFactory();
        elitePlusEnemyFactory = new ElitePlusEnemyFactory();
        eliteProEnemyFactory = new EliteProEnemyFactory();
        bossEnemyFactory = new BossEnemyFactory();

        rankingListDao = new RankingListDaoImpl(difficulty);
        // 从Dao获取数据，初始化model
        String[] columnName = rankingListDao.getColumnName();
        String[][]tableData = rankingListDao.showRankingList();
        model = new RankingModel(tableData, columnName);

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

        this.timer = new Timer("game-action-timer", true);
    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public final void action() {
        // 开启背景音乐
        if(bgmStartFlag == false) {
            MusicManager.playBackgroundMusic("./src/videos/bgm.wav");
            bgmStartFlag = true;
        }
        // 定时任务：绘制、对象产生、碰撞判定、及结束判定
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
            // 难度随时间变化
            difficultyIncrease();
            // 产生敌机
            getEnemyAction();
            // 飞机发射子弹
            shootAction();
            // 子弹移动
            bulletsPropsMoveAction();
            // 飞机移动
            aircraftsMoveAction();
            // 撞击检测
            crashCheckAction();
            // 后处理
            postProcessAction();
            // 重绘界面
            repaint();
            // 游戏结束检查
            checkResultAction();
            }
        };
        // 以固定延迟时间进行执行：本次任务执行完成后，延迟 timeInterval 再执行下一次
        timer.schedule(task,0,timeInterval);
    }

    //***********************
    //      Action 各部分
    //***********************
    public void difficultyIncrease(){}

    public abstract EnemyAircraft getEnemyRandom(double seed);

    private void getEnemyAction(){
        // 周期性随机产生敌机
        enemySpawnCounter++;
        if (enemySpawnCounter >= enemySpawnCycle) {
            enemySpawnCounter = 0;
            if (enemyAircrafts.size() < enemyMaxNumber) {
                double seed = Math.random();
                EnemyAircraft enemy = getEnemyRandom(seed);
                if(enemy != null) {
                    enemyAircrafts.add(enemy);
                }
            }
        }
        // 产生boss音效
        if(bossNum >= 1 && bossBgmStartFlag == false) {
            MusicManager.stopMusic("./src/videos/bgm.wav");
            MusicManager.playBackgroundMusic("./src/videos/bgm_boss.wav");
            bossBgmStartFlag = true;
        } else if(bossNum == 0 && bossBgmStartFlag == true){
            MusicManager.stopMusic("./src/videos/bgm_boss.wav");
            MusicManager.playBackgroundMusic("./src/videos/bgm.wav");
            bossBgmStartFlag = false;
        }
    }

    private void shootAction() {
        shootCounter++;
        if (shootCounter >= shootCycle) {
            shootCounter = 0;
            // 英雄机射击
            heroBullets.addAll(heroAircraft.shoot());
            // 敌机射击
            for(AbstractAircraft enemy : enemyAircrafts){
                enemyBullets.addAll(enemy.shoot());
            }
        }
    }

    private void bulletsPropsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
        for (AbstractProp prop : props) {
            prop.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // 敌机子弹攻击英雄机
        for (BaseBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
            if (heroAircraft.crash(bullet)) {
                // 英雄机撞击到敌机子弹
                // 英雄机损失一定生命值
                // 播放子弹击中的音效
                MusicManager.playSoundEffect("./src/videos/bullet_hit.wav");
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }

        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (EnemyAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    // 播放受击音效
                    MusicManager.playSoundEffect("./src/videos/bullet_hit.wav");
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        if(enemyAircraft instanceof BossEnemyAircraft){
                            bossNum -= 1;
                            score += 100;
                        }
                        LinkedList<AbstractProp> droppedProps = enemyAircraft.dropProp();
                        if (droppedProps != null) {
                            props.addAll(droppedProps);
                        }
                        score += 10;
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // 我方获得道具，道具生效
        for (AbstractProp prop : props) {
            if (prop.notValid()) {
                continue;
            }
            if (heroAircraft.crash(prop)) {
                // 播放获得道具的音效
                MusicManager.playSoundEffect("./src/videos/get_supply.wav");
                Runnable propRun = () -> {
                    try {
                        prop.applyEffect(this);
                        prop.vanish();
                        Thread.sleep(3000);
                        prop.effectExpire(this);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
                new Thread(propRun).start();
            }
        }

    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 删除无效的道具
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        props.removeIf(AbstractFlyingObject::notValid);
    }

    /**
     * 检查游戏是否结束，若结束：关闭线程池
     */
    private void checkResultAction(){
        // 游戏结束检查英雄机是否存活
        if (heroAircraft.getHp() <= 0) {
            // 停止播放所有音乐
            MusicManager.stopAllMusic();
            // 播放游戏结束的音效
            MusicManager.playSoundEffect("./src/videos/game_over.wav");
            timer.cancel(); // 取消定时器并终止所有调度任务
            gameOverFlag = true;
            System.out.println("Game Over!");

            // 提示输入用户名保存记录
            String message = "游戏结束，你的分数是：" + score + "\n请输入用户名以保存记录:";
            String playerName = JOptionPane.showInputDialog(this, message);
            if(playerName != null){
                String difficulty = "Easy";

                // 获取时间
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String timeStr = now.format(fmt);

                // 新增记录
                model.addRow(new String[]{"",playerName,Integer.toString(score), timeStr});
                model.sort();
            }

            // 展示排行榜窗口
            RankingList rankingList = new RankingList(model);
            Main.cardPanel.add(rankingList.getMainPanel());
            Main.cardLayout.last(Main.cardPanel);
        }
    }

    // 推出前保存数据
    public void saveBeforeExit() {
        // 保存表格、写文件
        String[][] newList = new String[model.getRowCount()][model.getColumnCount()];
        for (int i = 0; i < model.getRowCount(); i++) {
            for(int j = 0; j < model.getColumnCount(); j++){
                newList[i][j] = model.getValueAt(i, j).toString();
            }
        }
        rankingListDao.updateRankingList(newList);
    }

    public HeroAircraft getHero(){
        return heroAircraft;
    }

    public List<EnemyAircraft> getEnemy(){
        return enemyAircrafts;
    }

    public List<BaseBullet> getEnemyBullet(){
        return enemyBullets;
    }

    //***********************
    //      Paint 各部分
    //***********************
    /**
     * 重写 paint方法
     * 通过重复调用paint方法，实现游戏动画
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        paintBackground(g);

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, enemyAircrafts);

        // 绘制道具
        paintImageWithPositionRevised(g, props);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    public void paintBackground(Graphics g) {
        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_EASY_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_EASY_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }
    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.isEmpty()) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(Color.RED);
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE: " + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE: " + this.heroAircraft.getHp(), x, y);
    }
}

;
