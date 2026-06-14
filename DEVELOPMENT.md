# DominionCore Mod - Comprehensive Development

## 🎮 Project Structure

### Core Systems
- **Script Engine** - `.dc` file parser for custom content
- **Event System** - Player events, combat, progression
- **Command System** - In-game commands for all features
- **Data Management** - Persistent player data storage
- **Network System** - Client-server synchronization

### Client Side (GUI)
- **DominionMenuScreen** - Main circular menu with animated options
- **BloodlineSelectionScreen** - Select from available bloodlines
- **DominionSelectionScreen** - Equip dominions
- **ReligionScreen** - Create/manage religions
- **FactionScreen** - Faction management
- **StatusScreen** - View progression path
- **DominionHUD** - Overlay showing current status

### Server Side
- **PlayerEventHandler** - Handles player joins, deaths, damage
- **ServerEventHandler** - Server startup, command registration
- **DominionDataManager** - Load/save player data
- **AbilityExecutor** - Execute dominion/bloodline abilities

### Data Structures
- **PlayerDominionData** - Stores all player progression
- **Faction** - Faction system with treasury, claims, wars
- **Religion** - Religion system with followers and faith

## 🚀 Features Implemented

✅ Build configuration for Forge 47.4.10
✅ Script engine with .dc file system
✅ Player data persistence
✅ Event handling
✅ Command system
✅ GUI screens (6 screens)
✅ HUD overlay
✅ Ability execution
✅ Faction system
✅ Religion system
✅ Network synchronization

## 📁 Directory Structure

```
src/main/java/com/godkiller1233/dominion/
├── DominionCore.java              # Main mod class
├── DominionConfig.java            # Configuration
├── ability/
│   └── AbilityExecutor.java       # Execute abilities
├── client/
│   └── screen/
│       ├── DominionMenuScreen.java
│       ├── BloodlineSelectionScreen.java
│       ├── DominionSelectionScreen.java
│       ├── ReligionScreen.java
│       ├── FactionScreen.java
│       ├── StatusScreen.java
│       └── DominionHUD.java
├── command/
│   ├── BloodlineCommand.java
│   ├── DominionCommand.java
│   ├── ReligionCommand.java
│   ├── FactionCommand.java
│   ├── ReloadCommand.java
│   └── InfoCommand.java
├── core/
│   ├── DominionScriptEngine.java
│   ├── ScriptContext.java
│   └── ScriptFunction.java
├── data/
│   ├── PlayerDominionData.java
│   └── DominionDataManager.java
├── event/
│   ├── PlayerEventHandler.java
│   ├── ClientEventHandler.java
│   └── ServerEventHandler.java
├── faction/
│   └── Faction.java
├── network/
│   ├── DominionNetworkHandler.java
│   └── DominionSyncPacket.java
├── religion/
│   └── Religion.java
└── script/
    ├── DominionScript.java
    ├── impl/
    │   ├── BloodlineScript.java
    │   ├── DominionScript.java
    │   ├── ReligionScript.java
    │   └── ItemScript.java
    └── parser/
        └── DCParser.java
```

## 🎮 Commands

```
/dominion bloodline          # View current bloodline
/dominion dominion           # View active dominion
/dominion religion           # View religion status
/dominion faction            # View faction status
/dominion info               # Full status display
/dominion reload             # Reload all .dc scripts (admin)
```

## 📝 Custom Content Example

Create `dominion/custom/my_content.dc`:

```dc
bloodline Phoenix {
  type: fire
  passive: flame_aura
  active: [rebirth, fire_blast]
  evolution: ImmortalPhoenix
}

dominion Inferno {
  type: fire
  ability: [massive_fireball, lava_walk, fire_immunity]
  passive: fire_damage_boost
}
```

## 🔧 Next Steps

- [ ] Database integration (JSON/SQLite)
- [ ] Dimension/realm system
- [ ] Advanced combat mechanics
- [ ] PvP balancing
- [ ] Custom items and equipment
- [ ] World events (invasions, apocalypses)
- [ ] More GUI refinement
- [ ] Sound effects and particles
- [ ] Configuration menu

## 📦 Building & Running

```bash
# Build the mod
./gradlew build

# Run client
./gradlew runClient

# Run server
./gradlew runServer

# Generate resources
./gradlew runData
```

## 🌟 Key Features

- **Expandable**: Add custom content with simple .dc files
- **Non-linear**: Multiple progression paths
- **Balanced**: Both PvE and PvP gameplay
- **Social**: Factions, religions, player-driven content
- **Endgame**: Multiple endgame goals (God, Titan, Primordial, etc.)

---

**Created by**: godkiller1233
**Version**: 1.0.0
**License**: MIT
