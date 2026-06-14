package com.godkiller1233.dominion.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLServerStoppingEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import com.godkiller1233.dominion.DominionCore;
import com.godkiller1233.dominion.data.PlayerDominionData;
import com.godkiller1233.dominion.data.DominionDataManager;

@Mod.EventBusSubscriber(modid = DominionCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEventHandler {
    
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (!player.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
            DominionCore.LOGGER.info("Player joined: " + player.getName().getString());
            
            // Load or create player dominion data
            PlayerDominionData data = DominionDataManager.getOrCreateData(serverPlayer);
            DominionDataManager.sendPlayerDataToClient(serverPlayer, data);
        }
    }
    
    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        if (!player.level().isClientSide) {
            DominionCore.LOGGER.info("Player logged out: " + player.getName().getString());
            DominionDataManager.unloadPlayer(player.getUUID());
        }
    }
    
    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        if (!player.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
            PlayerDominionData data = DominionDataManager.getOrCreateData(serverPlayer);
            
            // Apply bloodline bonuses on respawn
            String bloodlineId = data.getBloodlineId();
            if (bloodlineId != null && !bloodlineId.isEmpty()) {
                applyBloodlineEffects(serverPlayer, data);
            }
        }
    }
    
    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            PlayerDominionData data = DominionDataManager.getData(player);
            if (data != null && data.getActiveDominion() != null) {
                // Apply dominion damage resistance/amplification
                String dominion = data.getActiveDominion();
                float damageModifier = getDominionDamageModifier(dominion);
                event.setAmount(event.getAmount() * damageModifier);
            }
        }
    }
    
    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            PlayerDominionData data = DominionDataManager.getData(player);
            if (data != null) {
                String bloodlineId = data.getBloodlineId();
                // Check for resurrection abilities
                if ("phoenix".equalsIgnoreCase(bloodlineId)) {
                    // Resurrect with health penalty
                    player.setHealth(player.getMaxHealth() / 4);
                    event.setCanceled(true);
                    DominionCore.LOGGER.info(player.getName().getString() + " was resurrected by Phoenix bloodline!");
                }
            }
        }
    }
    
    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.RightClickEmpty event) {
        Player player = event.getEntity();
        if (!player.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
            PlayerDominionData data = DominionDataManager.getData(serverPlayer);
            if (data != null && data.hasActiveAbility()) {
                // Handle ability activation
                data.decrementAbilityCooldown();
            }
        }
    }
    
    private static void applyBloodlineEffects(ServerPlayer player, PlayerDominionData data) {
        String bloodlineId = data.getBloodlineId();
        float level = data.getBloodlineLevel();
        
        switch (bloodlineId.toLowerCase()) {
            case "genesis":
                // Create bonuses
                break;
            case "null":
                // Void abilities
                break;
            case "dragon":
                // Dragon abilities
                break;
            case "phoenix":
                // Fire/resurrection
                break;
        }
    }
    
    private static float getDominionDamageModifier(String dominion) {
        switch (dominion.toLowerCase()) {
            case "beast":
                return 0.9f; // Take 10% less damage
            case "divine":
                return 0.85f; // Take 15% less damage
            case "demonic":
                return 1.1f; // Take 10% more damage but deal more
            default:
                return 1.0f;
        }
    }
}
