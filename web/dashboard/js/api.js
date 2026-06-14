// API Configuration
const API_BASE = 'https://dominion-core.godkiller1233.dev/api';
const SERVER_TOKEN = localStorage.getItem('serverToken') || '';

// API Client
class DominionAPI {
    constructor(baseURL, token) {
        this.baseURL = baseURL;
        this.token = token;
        this.client = axios.create({
            baseURL: baseURL,
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        });
    }

    // Players
    async getPlayers() {
        return this.client.get('/players');
    }

    async getPlayer(uuid) {
        return this.client.get(`/players/${uuid}`);
    }

    async updatePlayer(uuid, data) {
        return this.client.put(`/players/${uuid}`, data);
    }

    async banPlayer(uuid, reason) {
        return this.client.post(`/players/${uuid}/ban`, { reason });
    }

    async kickPlayer(uuid) {
        return this.client.post(`/players/${uuid}/kick`);
    }

    // Factions
    async getFactions() {
        return this.client.get('/factions');
    }

    async getFaction(factionId) {
        return this.client.get(`/factions/${factionId}`);
    }

    async createFaction(data) {
        return this.client.post('/factions', data);
    }

    async updateFaction(factionId, data) {
        return this.client.put(`/factions/${factionId}`, data);
    }

    async deleteFaction(factionId) {
        return this.client.delete(`/factions/${factionId}`);
    }

    // Religions
    async getReligions() {
        return this.client.get('/religions');
    }

    async getReligion(religionId) {
        return this.client.get(`/religions/${religionId}`);
    }

    async createReligion(data) {
        return this.client.post('/religions', data);
    }

    async updateReligion(religionId, data) {
        return this.client.put(`/religions/${religionId}`, data);
    }

    async deleteReligion(religionId) {
        return this.client.delete(`/religions/${religionId}`);
    }

    // Bloodlines
    async getBloodlines() {
        return this.client.get('/bloodlines');
    }

    async createBloodline(data) {
        return this.client.post('/bloodlines', data);
    }

    async updateBloodline(bloodlineId, data) {
        return this.client.put(`/bloodlines/${bloodlineId}`, data);
    }

    async deleteBloodline(bloodlineId) {
        return this.client.delete(`/bloodlines/${bloodlineId}`);
    }

    // Dominions
    async getDominions() {
        return this.client.get('/dominions');
    }

    async createDominion(data) {
        return this.client.post('/dominions', data);
    }

    async updateDominion(dominionId, data) {
        return this.client.put(`/dominions/${dominionId}`, data);
    }

    async deleteDominion(dominionId) {
        return this.client.delete(`/dominions/${dominionId}`);
    }

    // Items
    async getItems() {
        return this.client.get('/items');
    }

    async createItem(data) {
        return this.client.post('/items', data);
    }

    async updateItem(itemId, data) {
        return this.client.put(`/items/${itemId}`, data);
    }

    async deleteItem(itemId) {
        return this.client.delete(`/items/${itemId}`);
    }

    // Dungeons
    async getDungeons() {
        return this.client.get('/dungeons');
    }

    async createDungeon(data) {
        return this.client.post('/dungeons', data);
    }

    async updateDungeon(dungeonId, data) {
        return this.client.put(`/dungeons/${dungeonId}`, data);
    }

    async deleteDungeon(dungeonId) {
        return this.client.delete(`/dungeons/${dungeonId}`);
    }

    // Events
    async triggerEvent(eventType) {
        return this.client.post(`/events/trigger/${eventType}`);
    }

    async getServerStats() {
        return this.client.get('/stats');
    }

    async getLeaderboard(type) {
        return this.client.get(`/leaderboard/${type}`);
    }
}

// Initialize API
const api = new DominionAPI(API_BASE, SERVER_TOKEN);

// Export for use in dashboard.js
window.api = api;
