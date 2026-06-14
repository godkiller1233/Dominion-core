package com.godkiller1233.dominion.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.godkiller1233.dominion.data.PlayerDominionData;
import java.util.function.Supplier;

public class DominionSyncPacket {
    private PlayerDominionData data;
    
    public DominionSyncPacket(PlayerDominionData data) {
        this.data = data;
    }
    
    public DominionSyncPacket(FriendlyByteBuf buf) {
        this.data = new PlayerDominionData(buf.readUtf());
        this.data.setBloodlineId(buf.readUtf());
        this.data.setBloodlineLevel(buf.readFloat());
        this.data.setActiveDominion(buf.readUtf());
        this.data.setDominionPower(buf.readFloat());
        this.data.setDivineEnergy(buf.readFloat());
        this.data.setFaith(buf.readFloat());
        this.data.setWorshippers(buf.readInt());
    }
    
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(data.getUUID());
        buf.writeUtf(data.getBloodlineId() != null ? data.getBloodlineId() : "");
        buf.writeFloat(data.getBloodlineLevel());
        buf.writeUtf(data.getActiveDominion() != null ? data.getActiveDominion() : "");
        buf.writeFloat(data.getDominionPower());
        buf.writeFloat(data.getDivineEnergy());
        buf.writeFloat(data.getFaith());
        buf.writeInt(data.getWorshippers());
    }
    
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // Handle on client side
        });
        return true;
    }
}
