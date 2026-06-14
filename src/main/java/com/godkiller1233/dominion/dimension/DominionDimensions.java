package com.godkiller1233.dominion.dimension;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import com.godkiller1233.dominion.DominionCore;

/**
 * Custom dimensions for DominionCore
 */
public class DominionDimensions {
    public static final DeferredRegister<DimensionType> DIMENSION_TYPES = 
        DeferredRegister.create(ForgeRegistries.DIMENSION_TYPES, DominionCore.MOD_ID);
    
    // Divine Realm - for gods and high-level players
    public static final ResourceKey<Level> DIVINE_REALM = 
        ResourceKey.create(net.minecraft.world.level.Level.DIMENSION_REGISTRY_KEY, 
            new net.minecraft.resources.ResourceLocation(DominionCore.MOD_ID, "divine_realm"));
    
    // Circles of Hell - for devils and demonic players
    public static final ResourceKey<Level> HELL_CIRCLE_1 = 
        ResourceKey.create(net.minecraft.world.level.Level.DIMENSION_REGISTRY_KEY,
            new net.minecraft.resources.ResourceLocation(DominionCore.MOD_ID, "hell_circle_1"));
    
    public static final ResourceKey<Level> HELL_CIRCLE_2 = 
        ResourceKey.create(net.minecraft.world.level.Level.DIMENSION_REGISTRY_KEY,
            new net.minecraft.resources.ResourceLocation(DominionCore.MOD_ID, "hell_circle_2"));
    
    public static final ResourceKey<Level> HELL_CIRCLE_3 = 
        ResourceKey.create(net.minecraft.world.level.Level.DIMENSION_REGISTRY_KEY,
            new net.minecraft.resources.ResourceLocation(DominionCore.MOD_ID, "hell_circle_3"));
    
    // Tartarus - for wardens
    public static final ResourceKey<Level> TARTARUS = 
        ResourceKey.create(net.minecraft.world.level.Level.DIMENSION_REGISTRY_KEY,
            new net.minecraft.resources.ResourceLocation(DominionCore.MOD_ID, "tartarus"));
    
    // Chaos Dimension - unstable reality
    public static final ResourceKey<Level> CHAOS_DIMENSION = 
        ResourceKey.create(net.minecraft.world.level.Level.DIMENSION_REGISTRY_KEY,
            new net.minecraft.resources.ResourceLocation(DominionCore.MOD_ID, "chaos_dimension"));
    
    // Void - nothingness dimension
    public static final ResourceKey<Level> VOID_REALM = 
        ResourceKey.create(net.minecraft.world.level.Level.DIMENSION_REGISTRY_KEY,
            new net.minecraft.resources.ResourceLocation(DominionCore.MOD_ID, "void_realm"));
    
    // Limbo - lost souls dimension
    public static final ResourceKey<Level> LIMBO = 
        ResourceKey.create(net.minecraft.world.level.Level.DIMENSION_REGISTRY_KEY,
            new net.minecraft.resources.ResourceLocation(DominionCore.MOD_ID, "limbo"));
}
