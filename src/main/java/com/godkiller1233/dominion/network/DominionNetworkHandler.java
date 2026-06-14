package com.godkiller1233.dominion.network;

import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraft.resources.ResourceLocation;

import com.godkiller1233.dominion.DominionCore;

public class DominionNetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(DominionCore.MOD_ID, "main"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );
    
    private static int id = 0;
    
    public static void register() {
        INSTANCE.messageBuilder(DominionSyncPacket.class, id++, NetworkDirection.PLAY_TO_CLIENT)
            .decoder(DominionSyncPacket::new)
            .encoder(DominionSyncPacket::toBytes)
            .consumerNetworkThread(DominionSyncPacket::handle)
            .add();
    }
}
