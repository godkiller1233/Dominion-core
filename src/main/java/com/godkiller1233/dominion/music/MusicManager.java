package com.godkiller1233.dominion.music;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Music system for different dimensions and events
 */
@OnlyIn(Dist.CLIENT)
public class MusicManager {
    private static final Minecraft minecraft = Minecraft.getInstance();
    private static String currentTrack = "";
    private static float musicVolume = 0.7f;
    
    private static final Map<String, String> MUSIC_TRACKS = new HashMap<>();
    
    static {
        // Overworld music
        MUSIC_TRACKS.put("overworld", "music.overworld_ambient");
        MUSIC_TRACKS.put("overworld_night", "music.overworld_night");
        
        // Dimension music
        MUSIC_TRACKS.put("divine_realm", "music.divine_realm");
        MUSIC_TRACKS.put("hell_circle_1", "music.hell_circle_1");
        MUSIC_TRACKS.put("hell_circle_2", "music.hell_circle_2");
        MUSIC_TRACKS.put("hell_circle_3", "music.hell_circle_3");
        MUSIC_TRACKS.put("tartarus", "music.tartarus");
        MUSIC_TRACKS.put("chaos_dimension", "music.chaos_dimension");
        MUSIC_TRACKS.put("void_realm", "music.void_realm");
        
        // Event music
        MUSIC_TRACKS.put("demon_invasion", "music.demon_invasion");
        MUSIC_TRACKS.put("titan_awakening", "music.titan_awakening");
        MUSIC_TRACKS.put("divine_war", "music.divine_war");
        MUSIC_TRACKS.put("blood_moon", "music.blood_moon");
        MUSIC_TRACKS.put("reality_collapse", "music.reality_collapse");
        
        // Boss music
        MUSIC_TRACKS.put("boss_battle", "music.boss_battle");
        MUSIC_TRACKS.put("titan_battle", "music.titan_battle");
    }
    
    /**
     * Play music for a location/event
     */
    public static void playMusic(String trackName) {
        if (currentTrack.equals(trackName)) return;
        
        // Stop current music
        stopMusic();
        currentTrack = trackName;
        
        // Play new music
        String musicFile = MUSIC_TRACKS.getOrDefault(trackName, "music.default");
        playMusicTrack(musicFile);
    }
    
    private static void playMusicTrack(String musicFile) {
        if (minecraft.level != null && minecraft.player != null) {
            minecraft.level.playLocalSound(
                minecraft.player.getX(),
                minecraft.player.getY(),
                minecraft.player.getZ(),
                new SoundEvent(new net.minecraft.resources.ResourceLocation(
                    com.godkiller1233.dominion.DominionCore.MOD_ID, musicFile)),
                SoundSource.MUSIC,
                musicVolume,
                1.0f,
                true
            );
        }
    }
    
    /**
     * Stop current music
     */
    public static void stopMusic() {
        if (!currentTrack.isEmpty()) {
            // Stop music logic
            currentTrack = "";
        }
    }
    
    /**
     * Set music volume (0.0 - 1.0)
     */
    public static void setVolume(float volume) {
        musicVolume = Math.max(0.0f, Math.min(1.0f, volume));
    }
    
    public static float getVolume() {
        return musicVolume;
    }
    
    public static String getCurrentTrack() {
        return currentTrack;
    }
}
