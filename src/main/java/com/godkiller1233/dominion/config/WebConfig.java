package com.godkiller1233.dominion.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class WebConfig {
    public static final ForgeConfigSpec SPEC;
    public static final Web WEB;
    
    static {
        final Pair<Web, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Web::new);
        SPEC = pair.getRight();
        WEB = pair.getLeft();
    }
    
    public static class Web {
        public final ForgeConfigSpec.ConfigValue<String> webServerUrl;
        public final ForgeConfigSpec.ConfigValue<String> serverToken;
        public final ForgeConfigSpec.BooleanValue enableWebSync;
        public final ForgeConfigSpec.IntValue syncInterval;
        public final ForgeConfigSpec.BooleanValue enableWebDashboard;
        
        Web(ForgeConfigSpec.Builder builder) {
            builder.comment("DominionCore Web Integration").push("web");
            
            webServerUrl = builder
                .comment("Web server URL for dashboard and sync")
                .define("webServerUrl", "https://dominion-core.godkiller1233.dev");
            
            serverToken = builder
                .comment("Authentication token for web server")
                .define("serverToken", "your-token-here");
            
            enableWebSync = builder
                .comment("Enable automatic syncing with web server")
                .define("enableWebSync", true);
            
            syncInterval = builder
                .comment("Sync interval in seconds (min 60)")
                .defineInRange("syncInterval", 300, 60, 3600);
            
            enableWebDashboard = builder
                .comment("Enable web dashboard access")
                .define("enableWebDashboard", true);
            
            builder.pop();
        }
    }
}
