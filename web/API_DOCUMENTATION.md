# DominionCore API Documentation

## Base URL
```
https://dominion-core.godkiller1233.dev/api
```

## Authentication
All endpoints require a Bearer token in the Authorization header:
```
Authorization: Bearer YOUR_SERVER_TOKEN
```

---

## Players

### Get All Players
```http
GET /players
```

**Response:**
```json
[
  {
    "uuid": "550e8400-e29b-41d4-a716-446655440000",
    "name": "PlayerName",
    "level": 5,
    "bloodline": "genesis",
    "dominion": "fire",
    "religion": null,
    "online": true,
    "lastSeen": "2026-06-14T20:45:00Z"
  }
]
```

### Get Player by UUID
```http
GET /players/{uuid}
```

### Update Player Data
```http
PUT /players/{uuid}
```

**Request Body:**
```json
{
  "level": 10,
  "bloodline": "dragon",
  "divineEnergy": 75.5,
  "faith": 200
}
```

### Ban Player
```http
POST /players/{uuid}/ban
```

**Request Body:**
```json
{
  "reason": "Hacking detected",
  "duration": 86400
}
```

### Kick Player
```http
POST /players/{uuid}/kick
```

**Request Body:**
```json
{
  "message": "Server maintenance"
}
```

---

## Factions

### Get All Factions
```http
GET /factions
```

### Get Faction by ID
```http
GET /factions/{factionId}
```

### Create Faction
```http
POST /factions
```

**Request Body:**
```json
{
  "name": "Dragon Slayers",
  "king": "550e8400-e29b-41d4-a716-446655440000",
  "symbol": "🐉",
  "treasury": 1000
}
```

### Update Faction
```http
PUT /factions/{factionId}
```

**Request Body:**
```json
{
  "name": "New Name",
  "treasury": 2000
}
```

### Delete Faction
```http
DELETE /factions/{factionId}
```

### Add Faction Member
```http
POST /factions/{factionId}/members
```

**Request Body:**
```json
{
  "uuid": "550e8400-e29b-41d4-a716-446655440000",
  "rank": "member"
}
```

### Remove Faction Member
```http
DELETE /factions/{factionId}/members/{uuid}
```

---

## Religions

### Get All Religions
```http
GET /religions
```

### Get Religion by ID
```http
GET /religions/{religionId}
```

### Create Religion
```http
POST /religions
```

**Request Body:**
```json
{
  "name": "Church of the Dragon",
  "founder": "550e8400-e29b-41d4-a716-446655440000",
  "symbol": "🐉",
  "beliefs": ["power", "strength", "dominion"]
}
```

### Update Religion
```http
PUT /religions/{religionId}
```

### Delete Religion
```http
DELETE /religions/{religionId}
```

### Add Follower
```http
POST /religions/{religionId}/followers
```

**Request Body:**
```json
{
  "uuid": "550e8400-e29b-41d4-a716-446655440000"
}
```

---

## Bloodlines

### Get All Bloodlines
```http
GET /bloodlines
```

### Create Bloodline
```http
POST /bloodlines
```

**Request Body:**
```json
{
  "name": "Phoenix",
  "type": "fire",
  "passive": "flame_aura",
  "active": ["rebirth", "fire_blast"],
  "evolution": "ImmortalPhoenix",
  "weakness": "water"
}
```

### Update Bloodline
```http
PUT /bloodlines/{bloodlineId}
```

### Delete Bloodline
```http
DELETE /bloodlines/{bloodlineId}
```

---

## Dominions

### Get All Dominions
```http
GET /dominions
```

### Create Dominion
```http
POST /dominions
```

**Request Body:**
```json
{
  "name": "Inferno",
  "type": "fire",
  "abilities": ["massive_fireball", "lava_walk", "fire_immunity"],
  "power": 8.5,
  "cooldown": 30
}
```

### Update Dominion
```http
PUT /dominions/{dominionId}
```

### Delete Dominion
```http
DELETE /dominions/{dominionId}
```

---

## Items

### Get All Items
```http
GET /items
```

### Create Item
```http
POST /items
```

**Request Body:**
```json
{
  "name": "Crown of Creation",
  "type": "helmet",
  "rarity": "mythic",
  "damage": 15,
  "attributes": {
    "strength": 5,
    "intelligence": 3
  },
  "specialAbilities": ["creation_aura", "world_shaping"]
}
```

### Update Item
```http
PUT /items/{itemId}
```

### Delete Item
```http
DELETE /items/{itemId}
```

### Give Item to Player
```http
POST /items/{itemId}/give/{uuid}
```

---

## Dungeons

### Get All Dungeons
```http
GET /dungeons
```

### Create Dungeon
```http
POST /dungeons
```

**Request Body:**
```json
{
  "name": "Tower of Creation",
  "difficulty": "hard",
  "minLevel": 20,
  "maxPlayers": 4,
  "bosses": ["genesis_guardian", "reality_warden"],
  "rewards": {
    "xp": 5000,
    "gold": 10000,
    "loot": ["crown_of_creation"]
  },
  "resetTime": "daily"
}
```

### Update Dungeon
```http
PUT /dungeons/{dungeonId}
```

### Delete Dungeon
```http
DELETE /dungeons/{dungeonId}
```

### Start Dungeon Run
```http
POST /dungeons/{dungeonId}/start
```

**Request Body:**
```json
{
  "players": ["uuid1", "uuid2", "uuid3"]
}
```

---

## World Events

### Trigger Event
```http
POST /events/trigger/{eventType}
```

**Event Types:**
- `demon_invasion`
- `titan_awakening`
- `divine_war`
- `blood_moon`
- `reality_collapse`

**Response:**
```json
{
  "success": true,
  "event": "demon_invasion",
  "duration": 1200,
  "message": "Event started!"
}
```

### Get Current Event
```http
GET /events/current
```

### Cancel Current Event
```http
POST /events/cancel
```

---

## Server Stats

### Get Server Statistics
```http
GET /stats
```

**Response:**
```json
{
  "onlinePlayers": 42,
  "totalPlayers": 256,
  "activeFactions": 8,
  "activeReligions": 5,
  "totalGold": 500000,
  "uptime": 86400,
  "tps": 19.8,
  "playerGrowthData": [
    { "date": "2026-06-01", "count": 100 },
    { "date": "2026-06-02", "count": 120 }
  ],
  "bloodlineDistribution": {
    "genesis": 45,
    "dragon": 38,
    "phoenix": 32
  }
}
```

---

## Leaderboards

### Get Leaderboard by Type
```http
GET /leaderboard/{type}
```

**Types:**
- `level`
- `faith`
- `achievements`
- `wealth`
- `killstreak`

**Response:**
```json
[
  {
    "rank": 1,
    "uuid": "550e8400-e29b-41d4-a716-446655440000",
    "name": "PlayerName",
    "score": 1000
  }
]
```

---

## Sync

### Pull All Data from Web
```http
GET /sync/all
```

This endpoint allows servers to sync all data from the web dashboard.

### Sync Player Data
```http
POST /sync/player/{uuid}
```

### Sync Faction Data
```http
POST /sync/faction/{factionId}
```

---

## Error Handling

All errors return appropriate HTTP status codes:

- `200 OK` - Success
- `400 Bad Request` - Invalid parameters
- `401 Unauthorized` - Invalid/missing token
- `403 Forbidden` - Insufficient permissions
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server error

**Error Response:**
```json
{
  "error": true,
  "message": "Player not found",
  "code": 404
}
```

---

## Rate Limiting

All endpoints are rate limited to:
- 100 requests per minute for read operations
- 50 requests per minute for write operations

Rate limit info is returned in headers:
```
X-RateLimit-Limit: 100
X-RateLimit-Remaining: 95
X-RateLimit-Reset: 1623692400
```

---

## Webhooks

You can register webhooks to receive events from the server:

### Register Webhook
```http
POST /webhooks
```

**Request Body:**
```json
{
  "url": "https://your-server.com/webhook",
  "events": ["player_join", "player_leave", "event_triggered"],
  "secret": "your-secret-key"
}
```

### Webhook Events
- `player_join` - Player joins server
- `player_leave` - Player leaves server
- `player_levelup` - Player levels up
- `faction_created` - Faction created
- `religion_created` - Religion created
- `event_triggered` - World event triggered
- `dungeon_completed` - Dungeon completed

**Webhook Payload:**
```json
{
  "event": "player_join",
  "timestamp": "2026-06-14T20:45:00Z",
  "data": {
    "uuid": "550e8400-e29b-41d4-a716-446655440000",
    "name": "PlayerName"
  }
}
```

---

## Example Requests

### Create a Legendary Sword
```bash
curl -X POST https://dominion-core.godkiller1233.dev/api/items \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Blade of Eternity",
    "type": "sword",
    "rarity": "mythic",
    "damage": 25,
    "attributes": {
      "strength": 10,
      "speed": 5
    },
    "specialAbilities": ["time_slash", "eternity_cut"]
  }'
```

### Trigger Demon Invasion
```bash
curl -X POST https://dominion-core.godkiller1233.dev/api/events/trigger/demon_invasion \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### Get Player Leaderboard
```bash
curl -X GET https://dominion-core.godkiller1233.dev/api/leaderboard/level \
  -H "Authorization: Bearer YOUR_TOKEN"
```

---

## SDK Examples

### JavaScript
```javascript
const api = new DominionAPI('https://dominion-core.godkiller1233.dev/api', TOKEN);
const players = await api.getPlayers();
```

### Python
```python
import requests

headers = {'Authorization': f'Bearer {TOKEN}'}
response = requests.get('https://dominion-core.godkiller1233.dev/api/players', headers=headers)
players = response.json()
```

### Java
```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://dominion-core.godkiller1233.dev/api/players"))
    .header("Authorization", "Bearer " + token)
    .GET()
    .build();
HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
```
