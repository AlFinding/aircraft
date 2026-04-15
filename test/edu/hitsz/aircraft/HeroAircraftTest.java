package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import org.junit.jupiter.api.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    private static HeroAircraft heroAircraft;

    @BeforeAll
    static void beforeAll() {
        System.out.println("**--- Executed once before all test methods in this class ---**");
        heroAircraft = HeroAircraft.getInstance();
        heroAircraft.decreaseHp(50);
    }

    @AfterAll
    static void afterAll() {
        System.out.println("**--- Executed once after all test methods in this class ---**");
        heroAircraft = null;
    }

    @Test
    void shoot() {
        System.out.println("**--- Test shoot method executed ---**");
        List<BaseBullet> bullets = heroAircraft.shoot();
        assertNotNull(bullets);
    }

    @Test
    void recoverHp() {
        System.out.println("**--- Test recoverHp method executed ---**");
        int hp_before = heroAircraft.getHp();
        heroAircraft.recoverHp(10);
        assertEquals(hp_before + 10, heroAircraft.getHp());
    }

//    @Disabled
    @Test
    void vanish() {
        System.out.println("**--- Test vanish method executed ---**");
        heroAircraft.vanish();
        assertTrue(heroAircraft.notValid());
    }
}