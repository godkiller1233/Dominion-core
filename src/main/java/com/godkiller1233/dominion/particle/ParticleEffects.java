package com.godkiller1233.dominion.particle;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.phys.Vec3;

/**
 * Custom particle effects for spells and abilities
 */
public class ParticleEffects {
    
    public static void spawnFireBurst(Level level, Vec3 pos) {
        for (int i = 0; i < 20; i++) {
            level.addParticle(ParticleTypes.FLAME,
                pos.x, pos.y, pos.z,
                Math.random() * 0.2 - 0.1,
                Math.random() * 0.2,
                Math.random() * 0.2 - 0.1);
        }
    }
    
    public static void spawnIceBurst(Level level, Vec3 pos) {
        for (int i = 0; i < 20; i++) {
            level.addParticle(ParticleTypes.SNOWFLAKE,
                pos.x, pos.y, pos.z,
                Math.random() * 0.1 - 0.05,
                Math.random() * 0.1,
                Math.random() * 0.1 - 0.05);
        }
    }
    
    public static void spawnLightningBurst(Level level, Vec3 pos) {
        for (int i = 0; i < 30; i++) {
            level.addParticle(ParticleTypes.ELECTRIC_SPARK,
                pos.x, pos.y, pos.z,
                Math.random() * 0.3 - 0.15,
                Math.random() * 0.3 - 0.15,
                Math.random() * 0.3 - 0.15);
        }
    }
    
    public static void spawnDivineBurst(Level level, Vec3 pos) {
        for (int i = 0; i < 25; i++) {
            level.addParticle(ParticleTypes.ENCHANTED_HIT,
                pos.x, pos.y, pos.z,
                Math.random() * 0.2 - 0.1,
                Math.random() * 0.3,
                Math.random() * 0.2 - 0.1);
        }
    }
    
    public static void spawnDemonicBurst(Level level, Vec3 pos) {
        for (int i = 0; i < 20; i++) {
            level.addParticle(ParticleTypes.SOUL_FIRE_FLAME,
                pos.x, pos.y, pos.z,
                Math.random() * 0.2 - 0.1,
                Math.random() * 0.2,
                Math.random() * 0.2 - 0.1);
        }
    }
    
    public static void spawnHealingAura(Level level, Vec3 pos) {
        for (int i = 0; i < 15; i++) {
            level.addParticle(ParticleTypes.HAPPY_VILLAGER,
                pos.x, pos.y + 1, pos.z,
                Math.random() * 0.1 - 0.05,
                Math.random() * 0.15,
                Math.random() * 0.1 - 0.05);
        }
    }
}
