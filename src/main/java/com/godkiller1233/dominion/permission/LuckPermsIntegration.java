package com.godkiller1233.dominion.permission;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

/**
 * Integration with LuckPerms for permission management
 */
public class LuckPermsIntegration {
    private static final Logger LOGGER = LogManager.getLogger();
    private static LuckPerms api;
    private static boolean enabled = false;
    
    public static void initialize(LuckPerms luckPerms) {
        api = luckPerms;
        enabled = true;
        LOGGER.info("LuckPerms integration enabled");
    }
    
    /**
     * Check if player has a Dominion permission
     */
    public static boolean hasPermission(UUID playerUUID, String permission) {
        if (!enabled || api == null) return false;
        
        try {
            User user = api.getUserManager().getUser(playerUUID);
            if (user == null) return false;
            
            return user.getCachedData().getPermissionData().checkPermission("dominion." + permission).asBoolean();
        } catch (Exception e) {
            LOGGER.debug("Failed to check LuckPerms permission", e);
            return false;
        }
    }
    
    /**
     * Grant Dominion permission to player
     */
    public static void grantPermission(UUID playerUUID, String permission) {
        if (!enabled || api == null) return;
        
        try {
            User user = api.getUserManager().getUser(playerUUID);
            if (user != null) {
                user.data().add(Node.builder("dominion." + permission).build());
                api.getUserManager().saveUser(user);
                LOGGER.info("Granted permission to " + playerUUID + ": " + permission);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to grant LuckPerms permission", e);
        }
    }
    
    /**
     * Revoke Dominion permission from player
     */
    public static void revokePermission(UUID playerUUID, String permission) {
        if (!enabled || api == null) return;
        
        try {
            User user = api.getUserManager().getUser(playerUUID);
            if (user != null) {
                user.data().remove(Node.builder("dominion." + permission).build());
                api.getUserManager().saveUser(user);
                LOGGER.info("Revoked permission from " + playerUUID + ": " + permission);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to revoke LuckPerms permission", e);
        }
    }
    
    /**
     * Set player group (for faction ranks, etc.)
     */
    public static void setGroup(UUID playerUUID, String group) {
        if (!enabled || api == null) return;
        
        try {
            User user = api.getUserManager().getUser(playerUUID);
            if (user != null) {
                api.getGroupManager().modifyGroup(api.getGroupManager().getGroup(group), g -> {
                    if (g != null) {
                        user.data().add(Node.builder("group." + group).build());
                    }
                });
                api.getUserManager().saveUser(user);
            }
        } catch (Exception e) {
            LOGGER.debug("Failed to set LuckPerms group", e);
        }
    }
    
    public static boolean isEnabled() {
        return enabled;
    }
}
