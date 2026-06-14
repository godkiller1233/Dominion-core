package com.godkiller1233.dominion.world;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;

import java.util.Random;

/**
 * Handles generation of divine realms, hell circles, and special dimensions
 */
public class DimensionGenerator {
    private static final Random random = new Random();
    
    /**
     * Generate Divine Realm - floating islands with divine structures
     */
    public static void generateDivineRealm(Level level) {
        // Create floating islands at Y=100+
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(500) - 250;
            int z = random.nextInt(500) - 250;
            int y = 100 + random.nextInt(50);
            
            generateFloatingIsland(level, x, y, z);
        }
    }
    
    /**
     * Generate Hell Circle - lava flows, hellstone, demonic mobs
     */
    public static void generateHellCircle(Level level, int circle) {
        // More hostile the deeper you go
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(500) - 250;
            int z = random.nextInt(500) - 250;
            int y = 30 + (circle * 20);
            
            generateLavaLake(level, x, y, z);
            spawnDemonicMobs(level, x, y, z, circle);
        }
    }
    
    /**
     * Generate Tartarus - dangerous, warden territory
     */
    public static void generateTartarus(Level level) {
        // Create obsidian structures
        for (int i = 0; i < 15; i++) {
            int x = random.nextInt(500) - 250;
            int z = random.nextInt(500) - 250;
            int y = 40;
            
            generateObsidianSpire(level, x, y, z);
        }
    }
    
    /**
     * Generate Chaos Dimension - reality instability
     */
    public static void generateChaosDimension(Level level) {
        // Random blocks everywhere
        for (int x = -250; x < 250; x += 50) {
            for (int z = -250; z < 250; z += 50) {
                for (int y = 0; y < 100; y++) {
                    if (random.nextDouble() < 0.3) {
                        level.setBlock(new BlockPos(x, y, z), 
                            getRandomBlock(), 3);
                    }
                }
            }
        }
    }
    
    private static void generateFloatingIsland(Level level, int x, int y, int z) {
        // Create a roughly spherical island
        for (int dx = -30; dx < 30; dx++) {
            for (int dz = -30; dz < 30; dz++) {
                for (int dy = -15; dy < 15; dy++) {
                    double distance = Math.sqrt(dx*dx + dz*dz + dy*dy);
                    if (distance < 25) {
                        if (distance > 20) {
                            level.setBlock(new BlockPos(x+dx, y+dy, z+dz), 
                                Blocks.GRASS_BLOCK.defaultBlockState(), 3);
                        } else {
                            level.setBlock(new BlockPos(x+dx, y+dy, z+dz), 
                                Blocks.DIRT.defaultBlockState(), 3);
                        }
                    }
                }
            }
        }
    }
    
    private static void generateLavaLake(Level level, int x, int y, int z) {
        for (int dx = -20; dx < 20; dx++) {
            for (int dz = -20; dz < 20; dz++) {
                if (random.nextDouble() < 0.5) {
                    level.setBlock(new BlockPos(x+dx, y, z+dz), 
                        Blocks.LAVA.defaultBlockState(), 3);
                }
            }
        }
    }
    
    private static void generateObsidianSpire(Level level, int x, int y, int z) {
        for (int dy = 0; dy < 40; dy++) {
            int size = 10 - (dy / 4);
            for (int dx = -size; dx < size; dx++) {
                for (int dz = -size; dz < size; dz++) {
                    level.setBlock(new BlockPos(x+dx, y+dy, z+dz), 
                        Blocks.OBSIDIAN.defaultBlockState(), 3);
                }
            }
        }
    }
    
    private static void spawnDemonicMobs(Level level, int x, int y, int z, int circle) {
        // Spawn progressively harder mobs
        for (int i = 0; i < 5 + circle * 3; i++) {
            Zombie zombie = new Zombie(EntityType.ZOMBIE, level);
            zombie.setPos(x + random.nextInt(20), y + 1, z + random.nextInt(20));
            level.addFreshEntity(zombie);
        }
    }
    
    private static net.minecraft.world.level.block.Block getRandomBlock() {
        switch (random.nextInt(6)) {
            case 0: return Blocks.STONE;
            case 1: return Blocks.DIRT;
            case 2: return Blocks.SAND;
            case 3: return Blocks.GRAVEL;
            case 4: return Blocks.OBSIDIAN;
            default: return Blocks.COBBLESTONE;
        }
    }
}
