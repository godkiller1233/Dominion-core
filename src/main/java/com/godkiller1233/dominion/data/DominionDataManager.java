package com.godkiller1233.dominion.data;

import java.util.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.network.PacketDistributor;

import com.godkiller1233.dominion.DominionCore;
import com.godkiller1233.dominion.network.DominionSyncPacket;

public class DominionDataManager {
    private static Map<UUID, PlayerDominionData> playerData = new HashMap<>();
    
    public static PlayerDominionData getOrCreateData(ServerPlayer player) {
        UUID uuid = player.getUUID();
        return playerData.computeIfAbsent(uuid, k -> {
            PlayerDominionData data = new PlayerDominionData(uuid.toString());
            loadDataFromNBT(player, data);
            return data;
        });
    }
    
    public static PlayerDominionData getData(ServerPlayer player) {
        return playerData.get(player.getUUID());
    }
    
    public static void unloadPlayer(UUID uuid) {
        playerData.remove(uuid);
    }
    
    public static void savePlayerData(ServerPlayer player) {
        PlayerDominionData data = playerData.get(player.getUUID());
        if (data != null) {
            CompoundTag tag = saveDataToNBT(data);
            // Save to persistent storage (database or NBT file)
            DominionCore.LOGGER.info("Saved data for player: " + player.getName().getString());
        }
    }
    
    public static void loadDataFromNBT(ServerPlayer player, PlayerDominionData data) {
        // Load from NBT/database
        // This is a placeholder - implement with your storage system
    }
    
    public static CompoundTag saveDataToNBT(PlayerDominionData data) {
        CompoundTag tag = new CompoundTag();
        tag.putString("uuid", data.getUUID());
        tag.putString("bloodline", data.getBloodlineId() != null ? data.getBloodlineId() : "");
        tag.putFloat("bloodline_level", data.getBloodlineLevel());
        tag.putString("active_dominion", data.getActiveDominion() != null ? data.getActiveDominion() : "");
        tag.putFloat("dominion_power", data.getDominionPower());
        tag.putFloat("divine_energy", data.getDivineEnergy());
        tag.putFloat("faith", data.getFaith());
        tag.putInt("worshippers", data.getWorshippers());
        tag.putString("devil_status", data.getDevilStatus());
        tag.putInt("souls", data.getSoulCount());
        return tag;
    }
    
    public static void sendPlayerDataToClient(ServerPlayer player, PlayerDominionData data) {
        // Send sync packet to client
        DominionSyncPacket packet = new DominionSyncPacket(data);
        net.minecraftforge.network.NetworkDirection.PLAY_TO_CLIENT.send(packet, new PacketDistributor.PlayerList(Collections.singletonList(player)).getPlayers().stream().findAny().orElse(null));
    }
}
