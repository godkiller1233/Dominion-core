# DominionCore API Documentation

## Base URL
```
https://dominion-core.godkiller1233.dev/api
```

## Authentication
All endpoints (except `/auth/login` and `/auth/register`) require an Authorization header:
```
Authorization: Bearer {token}
```

---

## Authentication Endpoints

### POST /auth/login
Authenticate a server and receive token.

**Request:**
```json
{
  "username": "admin",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "expiresIn": 86400
}
```

### POST /auth/verify
Verify if a token is valid.

**Response:**
```json
{
  "valid": true,
  "username": "admin",
  "expiresAt": "2026-06-15T12:00:00Z"
}
```

### POST /auth/register
Register a new server.

**Request:**
```json
{
  "username": "myserver",
  "password": "secure_password",
  "serverName": "DominionCore PvP"
}
```

---

## Dashboard Endpoints

### GET /dashboard
Fetch dashboard statistics and recent activity.

**Response:**
```json
{
  "onlinePlayers": 42,
  "totalPlayers": 256,
  "totalFactions": 8,
  "totalReligions": 5,
  "totalGods": 3,
  "progressionData": {
    "labels": ["Mortal", "Bloodline Holder", "Dominion Wielder", "God"],
    "values": [120, 80, 50, 3]
  },
  "dominionsData": {
    "labels": ["Fire", "Water", "Divine", "Beast"],
    "values": [45, 35, 40, 30]
  },
  "recentActivity": [
    {
      "player": "Player123",
      "action": "became a God",
      "timestamp": "2026-06-14T20:30:00Z"
    }
  ]
}
```

---

## Player Endpoints

### GET /players
Fetch all players.

**Query Parameters:**
- `limit`: 10
- `offset`: 0
- `search`: (optional) search term
- `sort`: `name` | `level` | `faction`

**Response:**
```json
{
  "players": [
    {
      "uuid": "550e8400-e29b-41d4-a716-446655440000",
      "name": "Player123",
      "bloodline": "Genesis",
      "dominion": "Fire",
      "level": 5,
      "faction": "Dragons",
      "status": "online",
      "lastSeen": "2026-06-14T20:45:00Z"
    }
  ],
  "total": 256,
  "page": 1,
  "pageSize": 10
}
```

### GET /players/{uuid}
Fetch a specific player's details.

**Response:**
```json
{
  "uuid": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Player123",
  "bloodline": "Genesis",
  "bloodlineLevel": 5,
  "dominion": "Fire",
  "dominionPower": 8.5,
  "religion": "PyreWorship",
  "faith": 150,
  "faction": "Dragons",
  "factionRank": "Elite",
  "divineEnergy": 75,
  "devilStatus": "mortal",
  "souls": 0,
  "isGod": false,
  "isTitan": false,
  "achievements": ["first_blood", "evolved", "power_wielder"],
  "joinedAt": "2026-01-15T10:00:00Z"
}
```

### POST /players
Create a new player.

**Request:**
```json
{
  "name": "NewPlayer",
  "bloodline": "Genesis",
  "dominion": "Fire",
  "level": 1
}
```

### PUT /players/{uuid}
Update player data.

**Request:**
```json
{
  "bloodline": "Dragon",
  "dominion": "Water",
  "level": 10,
  "divineEnergy": 80
}
```

### DELETE /players/{uuid}
Delete a player (admin only).

---

## Faction Endpoints

### GET /factions
Fetch all factions.

**Response:**
```json
{
  "factions": [
    {
      "id": "faction_001",
      "name": "Dragons",
      "king": "Player123",
      "memberCount": 25,
      "treasury": 50000,
      "level": 3,
      "allies": ["faction_002"],
      "enemies": [],
      "claimedChunks": 150,
      "mainCapital": "Dragon's Lair",
      "cities": ["Dragon's Lair", "Fire Peak"]
    }
  ]
}
```

### GET /factions/{id}
Fetch a specific faction.

### POST /factions
Create a new faction.

**Request:**
```json
{
  "name": "Dragons",
  "king": "550e8400-e29b-41d4-a716-446655440000",
  "treasury": 1000
}
```

### PUT /factions/{id}
Update faction data.

**Request:**
```json
{
  "treasury": 50000,
  "mainCapital": "Dragon's Lair"
}
```

### POST /factions/{id}/members/{playerUuid}
Add member to faction.

### DELETE /factions/{id}/members/{playerUuid}
Remove member from faction.

### POST /factions/{id}/allies/{targetFactionId}
Add faction ally.

### POST /factions/{id}/enemies/{targetFactionId}
Add faction enemy (declare war).

---

## Religion Endpoints

### GET /religions
Fetch all religions.

**Response:**
```json
{
  "religions": [
    {
      "id": "religion_001",
      "name": "PyreWorship",
      "founder": "Player123",
      "symbol": "🔥",
      "beliefs": ["rebirth", "transformation"],
      "followerCount": 15,
      "faith": 250,
      "level": 2,
      "currentProphet": "Player456",
      "saints": ["Player456", "Player789"]
    }
  ]
}
```

### GET /religions/{id}
Fetch a specific religion.

### POST /religions
Create a new religion.

**Request:**
```json
{
  "name": "PyreWorship",
  "founder": "550e8400-e29b-41d4-a716-446655440000",
  "symbol": "🔥",
  "beliefs": ["rebirth", "transformation"]
}
```

### PUT /religions/{id}
Update religion data.

### POST /religions/{id}/followers/{playerUuid}
Add follower to religion.

### POST /religions/{id}/saints/{playerUuid}
Make a player a saint.

### POST /religions/{id}/prophet/{playerUuid}
Set religion prophet.

---

## Content Endpoints

### POST /content/bloodlines
Create a custom bloodline.

**Request:**
```json
{
  "name": "Phoenix",
  "type": "fire",
  "description": "A firebird bloodline",
  "passiveAbility": "flame_aura",
  "activeAbilities": ["rebirth", "fire_blast"],
  "evolution": "ImmortalPhoenix",
  "weakness": "water"
}
```

### POST /content/dominions
Create a custom dominion.

**Request:**
```json
{
  "name": "Inferno",
  "type": "fire",
  "abilities": ["massive_fireball", "lava_walk", "fire_immunity"],
  "passiveBuffs": ["fire_damage_boost"],
  "powerLevel": 8,
  "cooldown": 30
}
```

### POST /content/items
Create a custom item.

**Request:**
```json
{
  "name": "Blade of Eternity",
  "type": "sword",
  "rarity": "legendary",
  "damage": 15,
  "specialAbility": "time_slash",
  "attributeModifiers": {
    "strength": 5,
    "speed": 3
  }
}
```

---

## World Event Endpoints

### GET /events
Fetch current and past world events.

### POST /events/trigger
Manually trigger a world event.

**Request:**
```json
{
  "type": "demon_invasion" | "titan_awakening" | "divine_war" | "blood_moon" | "reality_collapse"
}
```

**Response:**
```json
{
  "success": true,
  "event": "Demon Invasion",
  "duration": 1200,
  "affectedPlayers": 42
}
```

### GET /events/{eventType}
Fetch event history.

---

## Dimension Endpoints

### GET /dimensions
Fetch all dimensions.

**Response:**
```json
{
  "dimensions": [
    {
      "id": "divine_realm",
      "name": "Divine Realm",
      "description": "For gods and ascended beings",
      "requiredLevel": 50,
      "difficulty": "hard",
      "playerCount": 5
    }
  ]
}
```

### PUT /dimensions/{id}
Update dimension settings.

---

## Achievement Endpoints

### GET /achievements
Fetch all achievements.

### GET /players/{uuid}/achievements
Fetch player's unlocked achievements.

### POST /players/{uuid}/achievements/{achievementId}
Unlock an achievement for a player.

---

## Leaderboard Endpoints

### GET /leaderboards/level
Fetch level leaderboard.

**Query Parameters:**
- `limit`: 50
- `offset`: 0

**Response:**
```json
{
  "leaderboard": [
    {
      "rank": 1,
      "player": "Player123",
      "level": 99,
      "bloodline": "Genesis",
      "points": 5000
    }
  ]
}
```

### GET /leaderboards/faith
Fetch faith/religion leaderboard.

### GET /leaderboards/wealth
Fetch wealth leaderboard.

### GET /leaderboards/gods
Fetch god tier leaderboard.

---

## Settings Endpoints

### GET /settings
Fetch server settings.

### POST /settings
Update server settings.

**Request:**
```json
{
  "serverName": "DominionCore PvP",
  "maxPlayers": 100,
  "enableWebSync": true,
  "syncInterval": 300,
  "eventFrequency": "normal"
}
```

---

## Sync Endpoints

### GET /sync/all
Pull all data from server (admin only).

### POST /sync/all
Push all data to server (admin only).

### GET /sync/status
Check sync status.

---

## Error Responses

All errors return appropriate HTTP status codes:

```json
{
  "error": "Error message",
  "code": "ERROR_CODE",
  "timestamp": "2026-06-14T20:30:00Z"
}
```

### Common Status Codes
- `200` - OK
- `201` - Created
- `400` - Bad Request
- `401` - Unauthorized
- `403` - Forbidden
- `404` - Not Found
- `500` - Internal Server Error

---

## Rate Limiting

API requests are rate limited to 100 requests per minute per token.

Headers in response:
```
X-RateLimit-Limit: 100
X-RateLimit-Remaining: 95
X-RateLimit-Reset: 1623710400
```

---

## Pagination

List endpoints support pagination:

**Query Parameters:**
- `limit`: items per page (default 10, max 100)
- `offset`: starting position (default 0)

**Response Headers:**
```
X-Total-Count: 256
X-Page-Count: 26
X-Current-Page: 1
```
