package com.godkiller1233.dominion.ability;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

/**
 * Ability system for Dominion powers
 */
public class AbilityExecutor {
    
    public static void executeAbility(ServerPlayer player, String abilityId, String source) {
        switch (source.toLowerCase()) {
            case "dominion":
                executeDominionAbility(player, abilityId);
                break;
            case "bloodline":
                executeBloodlineAbility(player, abilityId);
                break;
            case "religion":
                executeReligionAbility(player, abilityId);
                break;
        }
    }
    
    private static void executeDominionAbility(ServerPlayer player, String abilityId) {
        switch (abilityId.toLowerCase()) {
            case "fire_attack":
                executeFireAttack(player);
                break;
            case "fire_shield":
                executeFireShield(player);
                break;
            case "ice_spike":
                executeIceSpike(player);
                break;
            case "time_slow":
                executeTimeSlow(player);
                break;
        }
    }
    
    private static void executeBloodlineAbility(ServerPlayer player, String abilityId) {
        switch (abilityId.toLowerCase()) {
            case "create_structures":
                executeCreateStructures(player);
                break;
            case "flight":
                executeFlight(player);
                break;
            case "strength":
                executeStrength(player);
                break;
        }
    }
    
    private static void executeReligionAbility(ServerPlayer player, String abilityId) {
        switch (abilityId.toLowerCase()) {
            case "divine_blessing":
                executeDivineBlessing(player);
                break;
            case "heal_followers":
                executeHealFollowers(player);
                break;
        }
    }
    
    private static void executeFireAttack(ServerPlayer player) {
        player.level().explode(null, player.getX(), player.getY() + 1, player.getZ(), 3.0f, net.minecraft.world.level.block.Blocks.FIRE.defaultBlockState());
    }
    
    private static void executeFireShield(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 1));
    }
    
    private static void executeIceSpike(ServerPlayer player) {
        // Freeze nearby entities
        player.level().getEntitiesOfClass(net.minecraft.world.entity.LivingEntity.class,
            player.getBoundingBox().inflate(8)).forEach(entity -> {
            if (entity != player) {
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
            }
        });
    }
    
    private static void executeTimeSlow(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 150, 0));
    }
    
    private static void executeCreateStructures(ServerPlayer player) {
        // Placeholder for structure creation
    }
    
    private static void executeFlight(ServerPlayer player) {
        player.getAbilities().mayfly = true;
        player.onUpdateAbilities();
    }
    
    private static void executeStrength(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1));
    }
    
    private static void executeDivineBlessing(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0));
    }
    
    private static void executeHealFollowers(ServerPlayer player) {
        // Heal nearby players
        player.level().getEntitiesOfClass(ServerPlayer.class,
            player.getBoundingBox().inflate(16)).forEach(follower -> {
            follower.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
        });
    }
}
