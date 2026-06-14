# DominionCore - Sound, Music, and Discord Integration

## 🎵 Sound Effects System

### Sound Categories

#### Combat Sounds
- **Fireball Cast** - Whoosh with fire crackling
- **Ice Cast** - Magical freezing sound
- **Lightning Cast** - Electric zap
- **Spell Impact** - Explosion sound

#### Progression Sounds
- **Level Up** - Uplifting chime
- **Bloodline Unlock** - Powerful transformation sound
- **Become God** - Epic ascension music sting
- **Evolution** - Magical upgrade sound

#### Dominion Sounds
- **Dominion Equip** - Magical activation
- **Dominion Activate** - Power surge

#### UI Sounds
- **Menu Click** - UI interaction
- **Success** - Achievement unlock sound

#### Ambient Sounds
- **Divine Ambient** - Heavenly background
- **Demonic Ambient** - Hellish atmosphere

### Usage Example
```java
AudioManager.playSpellCast("fire");
AudioManager.playBloodlineUnlock();
AudioManager.playLevelUp();
```

---

## 🎼 Music System

### Music Tracks

#### Dimension Music
- **Divine Realm** - Ethereal, heavenly soundtrack
- **Hell Circle 1-3** - Progressive demonic music
- **Tartarus** - Dark, ominous orchestral
- **Chaos Dimension** - Chaotic, distorted sounds
- **Void Realm** - Silence with eerie elements

#### Event Music
- **Demon Invasion** - Action/battle theme
- **Titan Awakening** - Epic boss battle music
- **Divine War** - Epic conflict music
- **Blood Moon** - Dark, intense theme
- **Reality Collapse** - Glitchy, reality-breaking music

#### Boss Music
- **Boss Battle** - Intense combat theme
- **Titan Battle** - Epic final boss music

### Usage Example
```java
MusicManager.playMusic("divine_realm");
MusicManager.playMusic("demon_invasion");
MusicManager.setVolume(0.8f);
```

---

## 🤖 Discord Bot Integration

### Features

#### Server Notifications
- Player reaches godhood
- World events triggered
- Faction creation
- Religion founded
- Server status updates

#### Webhook Configuration
```json
{
  "discord_webhook_url": "https://discordapp.com/api/webhooks/YOUR_WEBHOOK_ID/YOUR_WEBHOOK_TOKEN",
  "discord_bot_token": "YOUR_BOT_TOKEN"
}
```

### Usage Examples

#### Send Simple Message
```java
discordBot.sendMessage("Player123 just killed the Demon Lord!");
```

#### Send Embed Message
```java
discordBot.sendEmbed(
    "👑 New God Ascended",
    "Player123 has become a god!",
    0xFF6B35  // Orange color
);
```

#### Notify God Ascension
```java
discordBot.notifyBecomeGod("Player123");
```

#### Notify World Event
```java
discordBot.notifyWorldEvent("Demon Invasion");
```

#### Notify Faction Creation
```java
discordBot.notifyFactionCreation("Dragons", "Player123");
```

#### Notify Religion Creation
```java
discordBot.notifyReligionCreation("PyreWorship", "Player123");
```

#### Send Server Status
```java
discordBot.sendServerStatus(42, 100, "DominionCore PvP");
```

### Discord Channel Setup

1. Create a Discord server
2. Create a text channel (e.g., `#server-notifications`)
3. Create a webhook:
   - Channel Settings → Integrations → Webhooks
   - Create Webhook
   - Copy Webhook URL
4. Add webhook URL to server config

### Embed Colors
- Primary (Orange): `0xFF6B35`
- Success (Green): `0x00d084`
- Danger (Red): `0xFF4444`
- Info (Blue): `0x0084ff`
- Warning (Yellow): `0xFFD700`

---

## 📁 Sound File Structure

Place audio files in `src/main/resources/assets/dominion/sounds/`:

```
sounds/
├── spell/
│   ├── fireball_cast.ogg
│   ├── ice_cast.ogg
│   ├── lightning_cast.ogg
│   └── impact.ogg
├── bloodline/
│   ├── unlock.ogg
│   ├── upgrade.ogg
│   └── evolution.ogg
├── dominion/
│   ├── equip.ogg
│   └── activate.ogg
├── progression/
│   ├── level_up.ogg
│   ├── become_god.ogg
│   └── ascend.ogg
├── event/
│   ├── warning.ogg
│   └── start.ogg
├── ui/
│   ├── menu_click.ogg
│   └── success.ogg
├── ambient/
│   ├── divine.ogg
│   └── demonic.ogg
└── music/
    ├── overworld_ambient.ogg
    ├── divine_realm.ogg
    ├── hell_circle_1.ogg
    ├── demon_invasion.ogg
    ├── titan_awakening.ogg
    └── boss_battle.ogg
```

### File Format Requirements
- Format: OGG Vorbis
- Sample Rate: 44.1kHz or 48kHz
- Bitrate: 128-192 kbps

---

## 🎤 Voice Chat Integration (Optional)

For future implementation:
- Player team voice channels
- Faction voice communications
- Boss raid voice coordination

---

## 🔧 Configuration

### Server Config
```toml
[audio]
enable_sounds = true
master_volume = 0.7
speech_enabled = false
enable_music = true
music_volume = 0.5

[discord]
enable_discord = true
webhook_url = "https://discordapp.com/api/webhooks/..."
bot_token = "token_here"
log_achievements = true
log_events = true
log_factions = true
log_religions = true
```

---

## 📊 Event-Sound Mapping

| Event | Sound |
|-------|-------|
| Player kills enemy | Spell Impact |
| Level up | Level Up |
| Bloodline unlock | Bloodline Unlock |
| Dominion equip | Dominion Equip |
| Become God | Become God |
| World event start | Event Start + Music |
| Faction created | Success |
| Religion created | Success |

---

## 🎵 Music Crossfading

Music automatically crossfades when changing dimensions or events:
- Fade duration: 2 seconds
- Smooth volume transition
- No jarring audio switches

---

**Next Phase:** Texture/item models, NPCs & bosses, dungeons & raids
