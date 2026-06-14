package com.godkiller1233.dominion.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import com.godkiller1233.dominion.DominionCore;

/**
 * Sound effects and audio system for DominionCore
 */
public class DominionSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = 
        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DominionCore.MOD_ID);
    
    // Combat Sounds
    public static final RegistryObject<SoundEvent> FIREBALL_CAST = 
        registerSound("spell.fireball_cast");
    public static final RegistryObject<SoundEvent> ICE_CAST = 
        registerSound("spell.ice_cast");
    public static final RegistryObject<SoundEvent> LIGHTNING_CAST = 
        registerSound("spell.lightning_cast");
    public static final RegistryObject<SoundEvent> SPELL_IMPACT = 
        registerSound("spell.impact");
    
    // Bloodline Sounds
    public static final RegistryObject<SoundEvent> BLOODLINE_UNLOCK = 
        registerSound("bloodline.unlock");
    public static final RegistryObject<SoundEvent> BLOODLINE_UPGRADE = 
        registerSound("bloodline.upgrade");
    public static final RegistryObject<SoundEvent> EVOLUTION = 
        registerSound("bloodline.evolution");
    
    // Dominion Sounds
    public static final RegistryObject<SoundEvent> DOMINION_EQUIP = 
        registerSound("dominion.equip");
    public static final RegistryObject<SoundEvent> DOMINION_ACTIVATE = 
        registerSound("dominion.activate");
    
    // Progression Sounds
    public static final RegistryObject<SoundEvent> LEVEL_UP = 
        registerSound("progression.level_up");
    public static final RegistryObject<SoundEvent> BECOME_GOD = 
        registerSound("progression.become_god");
    public static final RegistryObject<SoundEvent> ASCEND = 
        registerSound("progression.ascend");
    
    // Event Sounds
    public static final RegistryObject<SoundEvent> EVENT_WARNING = 
        registerSound("event.warning");
    public static final RegistryObject<SoundEvent> EVENT_START = 
        registerSound("event.start");
    
    // UI Sounds
    public static final RegistryObject<SoundEvent> MENU_CLICK = 
        registerSound("ui.menu_click");
    public static final RegistryObject<SoundEvent> SUCCESS = 
        registerSound("ui.success");
    
    // Ambient Sounds
    public static final RegistryObject<SoundEvent> DIVINE_AMBIENT = 
        registerSound("ambient.divine");
    public static final RegistryObject<SoundEvent> DEMONIC_AMBIENT = 
        registerSound("ambient.demonic");
    
    private static RegistryObject<SoundEvent> registerSound(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(
            new ResourceLocation(DominionCore.MOD_ID, name)));
    }
}
