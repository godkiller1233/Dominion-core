package com.godkiller1233.dominion.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ClientConfig {
    public static final ForgeConfigSpec SPEC;
    public static final Client CLIENT;
    
    static {
        final Pair<Client, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Client::new);
        SPEC = pair.getRight();
        CLIENT = pair.getLeft();
    }
    
    public static class Client {
        public final ForgeConfigSpec.BooleanValue showHUD;
        public final ForgeConfigSpec.BooleanValue showParticles;
        public final ForgeConfigSpec.BooleanValue enableShaders;
        public final ForgeConfigSpec.DoubleValue hudScale;
        public final ForgeConfigSpec.BooleanValue darkMode;
        
        Client(ForgeConfigSpec.Builder builder) {
            builder.comment("DominionCore Client Configuration").push("client");
            
            showHUD = builder
                .comment("Show HUD overlay")
                .define("showHUD", true);
            
            showParticles = builder
                .comment("Show spell particles")
                .define("showParticles", true);
            
            enableShaders = builder
                .comment("Enable visual shaders")
                .define("enableShaders", true);
            
            hudScale = builder
                .comment("HUD scale multiplier")
                .defineInRange("hudScale", 1.0, 0.5, 2.0);
            
            darkMode = builder
                .comment("Use dark mode for GUI")
                .define("darkMode", false);
            
            builder.pop();
        }
    }
}
