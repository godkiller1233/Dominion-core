// Dashboard Script
let currentPage = 'dashboard';
let playerGrowthChart, bloodlineChart;

// Initialize Dashboard
document.addEventListener('DOMContentLoaded', function() {
    loadDashboard();
    setupEventListeners();
});

function setupEventListeners() {
    // Navigation
    document.querySelectorAll('.nav-item').forEach(item => {
        item.addEventListener('click', function(e) {
            e.preventDefault();
            const page = this.dataset.page;
            navigateTo(page);
        });
    });
}

function navigateTo(page) {
    // Hide all pages
    document.querySelectorAll('.page').forEach(p => p.classList.remove('active'));
    document.querySelectorAll('.nav-item').forEach(n => n.classList.remove('active'));

    // Show selected page
    const pageElement = document.getElementById(`${page}-page`);
    if (pageElement) {
        pageElement.classList.add('active');
        document.querySelector(`[data-page="${page}"]`).classList.add('active');
        document.getElementById('page-title').textContent = 
            page.charAt(0).toUpperCase() + page.slice(1).replace(/[-_]/g, ' ');
        currentPage = page;

        // Load page data
        loadPageData(page);
    }
}

async function loadPageData(page) {
    try {
        switch(page) {
            case 'dashboard':
                await loadDashboard();
                break;
            case 'players':
                await loadPlayers();
                break;
            case 'factions':
                await loadFactions();
                break;
            case 'religions':
                await loadReligions();
                break;
            case 'bloodlines':
                await loadBloodlines();
                break;
            case 'dominions':
                await loadDominions();
                break;
            case 'items':
                await loadItems();
                break;
            case 'dungeons':
                await loadDungeons();
                break;
            case 'leaderboard':
                await loadLeaderboard('level');
                break;
        }
    } catch (error) {
        console.error('Error loading page:', error);
    }
}

async function loadDashboard() {
    try {
        const stats = await api.getServerStats();
        const data = stats.data;

        // Update stat cards
        document.getElementById('online-players').textContent = data.onlinePlayers || 0;
        document.getElementById('total-players').textContent = data.totalPlayers || 0;
        document.getElementById('active-factions').textContent = data.activeFactions || 0;
        document.getElementById('active-religions').textContent = data.activeReligions || 0;

        // Create charts
        createPlayerGrowthChart(data.playerGrowthData || []);
        createBloodlineChart(data.bloodlineDistribution || {});

        // Load activity
        displayActivity(data.recentActivity || []);
    } catch (error) {
        console.error('Error loading dashboard:', error);
    }
}

async function loadPlayers() {
    try {
        const response = await api.getPlayers();
        const players = response.data;
        const tbody = document.getElementById('players-tbody');
        tbody.innerHTML = '';

        players.forEach(player => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${player.name}</td>
                <td>${player.level}</td>
                <td>${player.bloodline || 'None'}</td>
                <td>${player.dominion || 'None'}</td>
                <td>${player.religion || 'None'}</td>
                <td><span class="status-badge ${player.online ? 'online' : 'offline'}">${player.online ? 'Online' : 'Offline'}</span></td>
                <td>
                    <button class="btn btn-primary" onclick="editPlayer('${player.uuid}')">Edit</button>
                    <button class="btn btn-warning" onclick="kickPlayer('${player.uuid}')">Kick</button>
                </td>
            `;
            tbody.appendChild(row);
        });
    } catch (error) {
        console.error('Error loading players:', error);
    }
}

async function loadFactions() {
    try {
        const response = await api.getFactions();
        const factions = response.data;
        const grid = document.getElementById('factions-grid');
        grid.innerHTML = '';

        factions.forEach(faction => {
            const card = document.createElement('div');
            card.className = 'grid-card';
            card.innerHTML = `
                <h4>${faction.name}</h4>
                <p>King: ${faction.king}</p>
                <p>Members: ${faction.memberCount || 0}</p>
                <p>Treasury: ${faction.treasury || 0} coins</p>
                <div style="margin-top: 10px;">
                    <button class="btn btn-primary" onclick="editFaction('${faction.id}')">Edit</button>
                    <button class="btn btn-warning" onclick="deleteFaction('${faction.id}')">Delete</button>
                </div>
            `;
            grid.appendChild(card);
        });
    } catch (error) {
        console.error('Error loading factions:', error);
    }
}

async function loadReligions() {
    try {
        const response = await api.getReligions();
        const religions = response.data;
        const grid = document.getElementById('religions-grid');
        grid.innerHTML = '';

        religions.forEach(religion => {
            const card = document.createElement('div');
            card.className = 'grid-card';
            card.innerHTML = `
                <h4>${religion.name}</h4>
                <p>Founder: ${religion.founder}</p>
                <p>Followers: ${religion.followerCount || 0}</p>
                <p>Faith: ${religion.faith || 0}</p>
                <div style="margin-top: 10px;">
                    <button class="btn btn-primary" onclick="editReligion('${religion.id}')">Edit</button>
                    <button class="btn btn-warning" onclick="deleteReligion('${religion.id}')">Delete</button>
                </div>
            `;
            grid.appendChild(card);
        });
    } catch (error) {
        console.error('Error loading religions:', error);
    }
}

async function loadBloodlines() {
    try {
        const response = await api.getBloodlines();
        const bloodlines = response.data;
        const grid = document.getElementById('bloodlines-grid');
        grid.innerHTML = '';

        bloodlines.forEach(bloodline => {
            const card = document.createElement('div');
            card.className = 'grid-card';
            card.innerHTML = `
                <h4>${bloodline.name}</h4>
                <p>Type: ${bloodline.type || 'Basic'}</p>
                <p>Users: ${bloodline.userCount || 0}</p>
                <div style="margin-top: 10px;">
                    <button class="btn btn-primary" onclick="editBloodline('${bloodline.id}')">Edit</button>
                    <button class="btn btn-warning" onclick="deleteBloodline('${bloodline.id}')">Delete</button>
                </div>
            `;
            grid.appendChild(card);
        });
    } catch (error) {
        console.error('Error loading bloodlines:', error);
    }
}

async function loadDominions() {
    try {
        const response = await api.getDominions();
        const dominions = response.data;
        const grid = document.getElementById('dominions-grid');
        grid.innerHTML = '';

        dominions.forEach(dominion => {
            const card = document.createElement('div');
            card.className = 'grid-card';
            card.innerHTML = `
                <h4>${dominion.name}</h4>
                <p>Type: ${dominion.type || 'Basic'}</p>
                <p>Power: ${dominion.power || 1}</p>
                <div style="margin-top: 10px;">
                    <button class="btn btn-primary" onclick="editDominion('${dominion.id}')">Edit</button>
                    <button class="btn btn-warning" onclick="deleteDominion('${dominion.id}')">Delete</button>
                </div>
            `;
            grid.appendChild(card);
        });
    } catch (error) {
        console.error('Error loading dominions:', error);
    }
}

async function loadItems() {
    try {
        const response = await api.getItems();
        const items = response.data;
        const grid = document.getElementById('items-grid');
        grid.innerHTML = '';

        items.forEach(item => {
            const card = document.createElement('div');
            card.className = 'grid-card';
            card.innerHTML = `
                <h4>${item.name}</h4>
                <p>Rarity: ${item.rarity || 'Common'}</p>
                <p>Damage: +${item.damage || 0}</p>
                <div style="margin-top: 10px;">
                    <button class="btn btn-primary" onclick="editItem('${item.id}')">Edit</button>
                    <button class="btn btn-warning" onclick="deleteItem('${item.id}')">Delete</button>
                </div>
            `;
            grid.appendChild(card);
        });
    } catch (error) {
        console.error('Error loading items:', error);
    }
}

async function loadDungeons() {
    try {
        const response = await api.getDungeons();
        const dungeons = response.data;
        const grid = document.getElementById('dungeons-grid');
        grid.innerHTML = '';

        dungeons.forEach(dungeon => {
            const card = document.createElement('div');
            card.className = 'grid-card';
            card.innerHTML = `
                <h4>${dungeon.name}</h4>
                <p>Difficulty: ${dungeon.difficulty || 'Normal'}</p>
                <p>Max Players: ${dungeon.maxPlayers || 4}</p>
                <p>Resets: ${dungeon.resetTime || 'Daily'}</p>
                <div style="margin-top: 10px;">
                    <button class="btn btn-primary" onclick="editDungeon('${dungeon.id}')">Edit</button>
                    <button class="btn btn-warning" onclick="deleteDungeon('${dungeon.id}')">Delete</button>
                </div>
            `;
            grid.appendChild(card);
        });
    } catch (error) {
        console.error('Error loading dungeons:', error);
    }
}

async function loadLeaderboard(type) {
    try {
        const response = await api.getLeaderboard(type);
        const leaderboard = response.data;
        const container = document.getElementById('leaderboard-content');
        container.innerHTML = '';

        leaderboard.forEach((entry, index) => {
            const item = document.createElement('div');
            item.className = 'leaderboard-item';
            item.innerHTML = `
                <div class="leaderboard-rank">#${index + 1}</div>
                <div class="leaderboard-info">
                    <img src="https://crafatar.com/avatars/${entry.uuid}?size=32" alt="${entry.name}" class="avatar" style="border-radius: 50%; width: 32px; height: 32px;">
                    <span class="leaderboard-name">${entry.name}</span>
                </div>
                <div class="leaderboard-score">${entry.score || 0}</div>
            `;
            container.appendChild(item);
        });
    } catch (error) {
        console.error('Error loading leaderboard:', error);
    }
}

function createPlayerGrowthChart(data) {
    const ctx = document.getElementById('playerGrowthChart');
    if (playerGrowthChart) playerGrowthChart.destroy();
    playerGrowthChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: data.map(d => d.date) || [],
            datasets: [{
                label: 'Players',
                data: data.map(d => d.count) || [],
                borderColor: '#ff6b35',
                backgroundColor: 'rgba(255, 107, 53, 0.1)',
                borderWidth: 2,
                tension: 0.4
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    labels: { color: '#e0e0e0' }
                }
            },
            scales: {
                y: {
                    ticks: { color: '#e0e0e0' },
                    grid: { color: '#333' }
                },
                x: {
                    ticks: { color: '#e0e0e0' },
                    grid: { color: '#333' }
                }
            }
        }
    });
}

function createBloodlineChart(data) {
    const ctx = document.getElementById('bloodlineChart');
    if (bloodlineChart) bloodlineChart.destroy();
    bloodlineChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: Object.keys(data),
            datasets: [{
                data: Object.values(data),
                backgroundColor: [
                    '#ff6b35',
                    '#4ecdc4',
                    '#ff9f43',
                    '#a29bfe',
                    '#74b9ff',
                    '#81ecec'
                ]
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    labels: { color: '#e0e0e0' }
                }
            }
        }
    });
}

function displayActivity(activities) {
    const list = document.getElementById('activity-list');
    list.innerHTML = '';
    
    activities.forEach(activity => {
        const item = document.createElement('div');
        item.className = 'activity-item';
        item.style.cssText = 'padding: 10px; border-bottom: 1px solid #333; color: #e0e0e0;';
        item.innerHTML = `
            <p>${activity.message}</p>
            <small style="color: #888;">${new Date(activity.timestamp).toLocaleString()}</small>
        `;
        list.appendChild(item);
    });
}

async function triggerEvent(eventType) {
    try {
        await api.triggerEvent(eventType);
        alert(`Event triggered: ${eventType}`);
    } catch (error) {
        console.error('Error triggering event:', error);
        alert('Failed to trigger event');
    }
}

function saveSettings() {
    alert('Settings saved!');
}

function closeModal() {
    document.getElementById('modal').style.display = 'none';
}

function submitForm(event) {
    event.preventDefault();
    alert('Form submitted!');
    closeModal();
}

window.onclick = function(event) {
    const modal = document.getElementById('modal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
}
