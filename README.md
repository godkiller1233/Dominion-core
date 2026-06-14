# DominionCore - Minecraft Forge Mod

A fully expandable Minecraft RPG/Progression mod for Minecraft 1.20.1 with Forge 47.4.10.

## Features

### Core Systems
- **Bloodline System**: Race/origin system with passive/active abilities, evolutions, and mutations
- **Dominion System**: Special powers that can be equipped and upgraded
- **Religion System**: Player-created religions with followers and divine powers
- **Faction System**: Large-scale player factions with treasury, claims, and wars
- **Devil & Sin System**: Alternative progression path through demonic powers
- **Progression Tree**: Mortal → Bloodline Holder → Dominion Wielder → Religious Figure → Saint/Champion → God → Titan → Primordial → Living Concept

### Custom Scripting Engine

DominionCore includes a powerful scripting system using `.dc` files:

```dc
# Custom Bloodline
bloodline MyCustomBloodline {
  type: creation
  passive: my_passive_ability
  active: [my_active_1, my_active_2]
  evolution: AdvancedForm
}

# Custom Dominion
dominion MyCustomDominion {
  type: fire
  abilities: [flame_strike, fire_shield]
  passive_buffs: [strength, speed]
}

# Custom Religion
religion MyFaith {
  symbol: sacred_cross
  beliefs: [peace, knowledge, power]
}

# Custom Item
item MysticalSword {
  type: sword
  damage: 15
  special: soul_steal
}
```

## Installation

1. Download Minecraft Forge 47.4.10 for Minecraft 1.20.1
2. Install Forge
3. Place the DominionCore jar in your `mods` folder
4. Run Minecraft

## Creating Custom Content

1. Navigate to the `dominion/custom/` folder in your `.minecraft` directory
2. Create `.dc` files with your custom content definitions
3. Reload scripts with `/dominion reload` command (admin only)

## Directory Structure

```
dominion/
├── scripts/          # Core mod scripts (read-only)
├── custom/           # Your custom .dc files
└── data/             # Persistent data storage
```

## Development

### Building
```bash
./gradlew build
```

### Running Dev Environment
```bash
# Client
./gradlew runClient

# Server
./gradlew runServer
```

## File Structure

- `build.gradle` - Forge configuration and dependencies
- `src/main/java/` - Java source code
- `src/main/resources/` - Resource files
- `src/generated/resources/` - Auto-generated resources

## License

MIT License

## Author

Created by godkiller1233
