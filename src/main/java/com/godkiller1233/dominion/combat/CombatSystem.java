package com.godkiller1233.dominion.combat;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.Vec3;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.core.particles.ParticleTypes;

import com.godkiller1233.dominion.data.PlayerDominionData;
import com.godkiller1233.dominion.data.DominionDataManager;

import java.util.Random;

/**
 * Advanced combat system with spells, effects, and abilities
 */
public class CombatSystem {
    private static final Random random = new Random();
    
    /**
     * Fire a spell/projectile
     */
    public static void castFireSpell(ServerPlayer caster, String spellType) {
        PlayerDominionData data = DominionDataManager.getOrCreateData(caster);
        
        switch (spellType.toLowerCase()) {
            case "fireball":
                castFireball(caster);
                break;
            case "ice_lance":
                castIceLance(caster);
                break;
            case "lightning_bolt":
                castLightningBolt(caster);
                break;
            case "meteor_storm":
                castMeteorStorm(caster);
                break;
            case "time_freeze":
                castTimeFreeze(caster);
                break;
        }
    }
    
    private static void castFireball(ServerPlayer caster) {
        Vec3 look = caster.getLookAngle();
        Fireball fireball = new Fireball(caster.level(), caster, 
            look.x * 2, look.y * 2, look.z * 2);
        fireball.setPos(caster.getX(), caster.getEyeY(), caster.getZ());
        caster.level().addFreshEntity(fireball);
        
        // Particles
        caster.level().sendParticles(ParticleTypes.FLAME, 
            caster.getX(), caster.getEyeY(), caster.getZ(),
            20, 0.5, 0.5, 0.5, 0.1);
    }
    
    private static void castIceLance(ServerPlayer caster) {
        // Freeze nearby enemies
        caster.level().getEntitiesOfClass(LivingEntity.class, 
            caster.getBoundingBox().inflate(15),
            entity -> entity != caster && entity.isAlive())
            .forEach(entity -> {
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 2));
                entity.hurt(caster.damageSources().generic(), 5.0f);
            });
        
        caster.level().sendParticles(ParticleTypes.ITEM_SNOWBALL,
            caster.getX(), caster.getY(), caster.getZ(),
            50, 0.5, 0.5, 0.5, 0.2);
    }
    
    private static void castLightningBolt(ServerPlayer caster) {
        Vec3 look = caster.getLookAngle();
        for (double d = 0; d < 30; d += 0.5) {
            Vec3 pos = caster.getEyePosition().add(look.scale(d));
            caster.level().sendParticles(ParticleTypes.ELECTRIC_SPARK,
                pos.x, pos.y, pos.z, 1, 0, 0, 0, 0);
        }
        
        // Damage entities in line
        caster.level().getEntitiesOfClass(LivingEntity.class,
            caster.getBoundingBox().expandTowards(look.scale(30), 2),
            entity -> entity != caster)
            .forEach(entity -> {
                entity.hurt(caster.damageSources().generic(), 8.0f);
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
            });
    }
    
    private static void castMeteorStorm(ServerPlayer caster) {
        for (int i = 0; i < 10; i++) {
            int dx = random.nextInt(40) - 20;
            int dz = random.nextInt(40) - 20;
            Vec3 targetPos = caster.position().add(dx, 50, dz);
            
            // Create falling block/projectile
            Fireball meteor = new Fireball(caster.level(), caster, 0, -5, 0);
            meteor.setPos(targetPos.x, targetPos.y, targetPos.z);
            caster.level().addFreshEntity(meteor);
        }
    }
    
    private static void castTimeFreeze(ServerPlayer caster) {
        // Slow all nearby entities
        caster.level().getEntitiesOfClass(LivingEntity.class,
            caster.getBoundingBox().inflate(20),
            entity -> entity != caster)
            .forEach(entity -> {
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 4));
            });
    }
    
    /**
     * Apply dominion damage bonus
     */
    public static float getDominionDamageBonus(ServerPlayer player) {
        PlayerDominionData data = DominionDataManager.getOrCreateData(player);
        String dominion = data.getActiveDominion();
        
        if (dominion == null) return 1.0f;
        
        switch (dominion.toLowerCase()) {
            case "beast": return 1.5f;
            case "fire": return 1.3f;
            case "demonic": return 1.8f;
            case "divine": return 1.2f;
            default: return 1.0f;
        }
    }
    
    /**
     * Calculate spell damage based on stats
     */
    public static float calculateSpellDamage(ServerPlayer caster, String spellType) {
        PlayerDominionData data = DominionDataManager.getOrCreateData(caster);
        float baseDamage = 5.0f;
        float levelMultiplier = data.getBloodlineLevel() / 10.0f;
        
        switch (spellType.toLowerCase()) {
            case "fireball": return baseDamage * 2 * levelMultiplier;
            case "ice_lance": return baseDamage * 1.8f * levelMultiplier;
            case "lightning_bolt": return baseDamage * 2.5f * levelMultiplier;
            case "meteor_storm": return baseDamage * 3.0f * levelMultiplier;
            case "time_freeze": return baseDamage * 1.5f * levelMultiplier;
            default: return baseDamage;
        }
    }
}
