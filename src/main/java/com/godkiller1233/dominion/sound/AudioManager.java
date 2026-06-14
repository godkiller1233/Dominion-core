package com.godkiller1233.dominion.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Audio manager for playing sounds
 */
@OnlyIn(Dist.CLIENT)
public class AudioManager {
    private static final Minecraft minecraft = Minecraft.getInstance();
    
    /**
     * Play spell cast sound
     */
    public static void playSpellCast(String spellType) {
        switch (spellType.toLowerCase()) {
            case "fire":
                playSound(DominionSounds.FIREBALL_CAST, 1.0f, 1.0f);
                break;
            case "ice":
                playSound(DominionSounds.ICE_CAST, 1.0f, 1.0f);
                break;
            case "lightning":
                playSound(DominionSounds.LIGHTNING_CAST, 1.0f, 1.0f);
                break;
        }
    }
    
    /**
     * Play spell impact sound
     */
    public static void playSpellImpact() {
        playSound(DominionSounds.SPELL_IMPACT, 1.0f, 1.0f);
    }
    
    /**
     * Play bloodline unlock sound
     */
    public static void playBloodlineUnlock() {
        playSound(DominionSounds.BLOODLINE_UNLOCK, 1.0f, 1.2f);
    }
    
    /**
     * Play progression sound
     */
    public static void playLevelUp() {
        playSound(DominionSounds.LEVEL_UP, 1.0f, 1.0f);
    }
    
    /**
     * Play ascension sound
     */
    public static void playAscension() {
        playSound(DominionSounds.ASCEND, 1.0f, 0.8f);
    }
    
    /**
     * Play god transformation sound
     */
    public static void playBecomeGod() {
        playSound(DominionSounds.BECOME_GOD, 1.0f, 1.0f);
    }
    
    /**
     * Play world event warning
     */
    public static void playEventWarning() {
        playSound(DominionSounds.EVENT_WARNING, 1.0f, 1.0f);
    }
    
    /**
     * Play UI click sound
     */
    public static void playMenuClick() {
        playSound(DominionSounds.MENU_CLICK, 1.0f, 1.0f);
    }
    
    /**
     * Play success sound
     */
    public static void playSuccess() {
        playSound(DominionSounds.SUCCESS, 1.0f, 1.2f);
    }
    
    /**
     * Generic sound play method
     */
    private static void playSound(net.minecraftforge.registries.RegistryObject<SoundEvent> sound, float volume, float pitch) {
        if (minecraft.player != null && sound.isPresent()) {
            minecraft.level.playLocalSound(
                minecraft.player.getX(),
                minecraft.player.getY(),
                minecraft.player.getZ(),
                sound.get(),
                SoundSource.PLAYERS,
                volume,
                pitch,
                false
            );
        }
    }
}
