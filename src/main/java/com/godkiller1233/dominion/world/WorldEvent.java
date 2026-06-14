package com.godkiller1233.dominion.world;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.List;

/**
 * Special world events that can be triggered
 */
public abstract class WorldEvent {
    protected static final Logger LOGGER = LogManager.getLogger();
    protected MinecraftServer server;
    protected long startTime;
    protected long duration; // in ticks
    protected boolean isActive = false;
    
    public WorldEvent(MinecraftServer server, long duration) {
        this.server = server;
        this.duration = duration;
    }
    
    public void start() {
        isActive = true;
        startTime = server.getTickCount();
    }
    
    public void update(MinecraftServer server) {
        if (!isActive) return;
    }
    
    public boolean isComplete() {
        return isActive && (server.getTickCount() - startTime >= duration);
    }
    
    public abstract String getEventName();
    
    /**
     * Demon Invasion - Spawns hostile demons across the world
     */
    public static class DemonInvasion extends WorldEvent {
        private int spawnWave = 0;
        
        public DemonInvasion(MinecraftServer server) {
            super(server, 24000); // 20 minutes
        }
        
        @Override
        public void start() {
            super.start();
            broadcastEvent("\u00a74\u00a7l\u2694 DEMON INVASION STARTED \u2694");
        }
        
        @Override
        public void update(MinecraftServer server) {
            super.update(server);
            long elapsed = server.getTickCount() - startTime;
            
            // Spawn waves every 5 seconds
            if (elapsed % 100 == 0) {
                spawnWave++;
                spawnDemonWave(spawnWave);
            }
        }
        
        private void spawnDemonWave(int wave) {
            List<ServerPlayer> players = server.getPlayerList().getPlayers();
            Random random = new Random();
            
            for (ServerPlayer player : players) {
                Level level = player.level();
                for (int i = 0; i < 3 + wave; i++) {
                    BlockPos pos = player.blockPosition().offset(
                        random.nextInt(100) - 50,
                        0,
                        random.nextInt(100) - 50
                    );
                    Zombie demon = new Zombie(EntityType.ZOMBIE, level);
                    demon.setPos(pos.getX(), pos.getY(), pos.getZ());
                    level.addFreshEntity(demon);
                }
            }
        }
        
        @Override
        public String getEventName() {
            return "Demon Invasion";
        }
    }
    
    /**
     * Titan Awakening - A titan emerges and threatens the world
     */
    public static class TitanAwakening extends WorldEvent {
        public TitanAwakening(MinecraftServer server) {
            super(server, 36000); // 30 minutes
        }
        
        @Override
        public void start() {
            super.start();
            broadcastEvent("\u00a75\u00a7l⚡ TITAN AWAKENING ⚡");
        }
        
        @Override
        public String getEventName() {
            return "Titan Awakening";
        }
    }
    
    /**
     * Divine War - Gods and demons battle for supremacy
     */
    public static class DivineWar extends WorldEvent {
        public DivineWar(MinecraftServer server) {
            super(server, 48000); // 40 minutes
        }
        
        @Override
        public void start() {
            super.start();
            broadcastEvent("\u00a7b\u00a7l✏ DIVINE WAR ✏");
            
            // Give players debuffs
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 24000, 1));
            }
        }
        
        @Override
        public String getEventName() {
            return "Divine War";
        }
    }
    
    /**
     * Blood Moon - Enhanced demon activity and strange effects
     */
    public static class BloodMoon extends WorldEvent {
        public BloodMoon(MinecraftServer server) {
            super(server, 24000); // 20 minutes
        }
        
        @Override
        public void start() {
            super.start();
            broadcastEvent("\u00a7c\u00a7l✍ BLOOD MOON RISES ✍");
            
            // Make night 2x as long
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 24000));
            }
        }
        
        @Override
        public String getEventName() {
            return "Blood Moon";
        }
    }
    
    /**
     * Reality Collapse - Reality becomes unstable
     */
    public static class RealityCollapse extends WorldEvent {
        public RealityCollapse(MinecraftServer server) {
            super(server, 18000); // 15 minutes
        }
        
        @Override
        public void start() {
            super.start();
            broadcastEvent("\u00a75\u00a7l☡ REALITY COLLAPSE ☡");
        }
        
        @Override
        public void update(MinecraftServer server) {
            super.update(server);
            
            // Random damage to players
            Random random = new Random();
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                if (random.nextDouble() < 0.01) {
                    player.hurt(player.damageSources().generic(), 5.0f);
                }
            }
        }
        
        @Override
        public String getEventName() {
            return "Reality Collapse";
        }
    }
    
    protected void broadcastEvent(String message) {
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            player.displayClientMessage(Component.literal(message), false);
        }
    }
}
