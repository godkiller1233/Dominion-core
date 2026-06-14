package com.godkiller1233.dominion;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class DominionConfig {
    public static final ForgeConfigSpec SPEC;
    public static final Common COMMON;
    
    static {
        final Pair<Common, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Common::new);
        SPEC = pair.getRight();
        COMMON = pair.getLeft();
    }
    
    public static class Common {
        public final ForgeConfigSpec.BooleanValue enableBloodlines;
        public final ForgeConfigSpec.BooleanValue enableDominions;
        public final ForgeConfigSpec.BooleanValue enableReligions;
        public final ForgeConfigSpec.IntValue maxPlayersPerFaction;
        public final ForgeConfigSpec.DoubleValue divineRealmScale;
        
        Common(ForgeConfigSpec.Builder builder) {
            builder.comment("DominionCore Configuration").push("general");
            
            enableBloodlines = builder
                .comment("Enable the Bloodline system")
                .define("enableBloodlines", true);
            
            enableDominions = builder
                .comment("Enable the Dominion system")
                .define("enableDominions", true);
            
            enableReligions = builder
                .comment("Enable the Religion system")
                .define("enableReligions", true);
            
            maxPlayersPerFaction = builder
                .comment("Maximum players per faction")
                .defineInRange("maxPlayersPerFaction", 100, 1, 1000);
            
            divineRealmScale = builder
                .comment("Divine realm dimension scale")
                .defineInRange("divineRealmScale", 2.0, 0.5, 10.0);
            
            builder.pop();
        }
    }
}
