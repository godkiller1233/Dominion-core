const API_BASE = 'https://dominion-core.godkiller1233.dev/api';
let authToken = localStorage.getItem('authToken') || '';

// Page Navigation
document.querySelectorAll('.menu-item').forEach(item => {
    item.addEventListener('click', function(e) {
        e.preventDefault();
        const page = this.getAttribute('data-page');
        switchPage(page);
    });
});

function switchPage(page) {
    // Hide all pages
    document.querySelectorAll('.page').forEach(p => {
        p.classList.remove('active');
    });

    // Remove active from menu items
    document.querySelectorAll('.menu-item').forEach(m => {
        m.classList.remove('active');
    });

    // Show selected page
    const pageElement = document.getElementById('page-' + page);
    if (pageElement) {
        pageElement.classList.add('active');
    }

    // Mark menu item as active
    document.querySelector(`[data-page="${page}"]`).classList.add('active');

    // Update page title
    document.getElementById('page-title').textContent = 
        document.querySelector(`[data-page="${page}"]`).textContent;

    // Load page data
    loadPageData(page);
}

function loadPageData(page) {
    switch(page) {
        case 'dashboard':
            loadDashboard();
            break;
        case 'players':
            loadPlayers();
            break;
        case 'factions':
            loadFactions();
            break;
        case 'religions':
            loadReligions();
            break;
        case 'events':
            // Events page is static
            break;
    }
}

// Load Dashboard
async function loadDashboard() {
    try {
        const response = await axios.get(`${API_BASE}/dashboard`, {
            headers: { 'Authorization': `Bearer ${authToken}` }
        });

        const data = response.data;

        // Update stats
        document.getElementById('online-players').textContent = data.onlinePlayers;
        document.getElementById('total-factions').textContent = data.totalFactions;
        document.getElementById('total-religions').textContent = data.totalReligions;
        document.getElementById('total-gods').textContent = data.totalGods;

        // Update charts
        createProgressionChart(data.progressionData);
        createDominionsChart(data.dominionsData);

        // Update activity
        loadActivity(data.recentActivity);
    } catch (error) {
        console.error('Failed to load dashboard:', error);
    }
}

function createProgressionChart(data) {
    const ctx = document.getElementById('progression-chart');
    if (ctx) {
        new Chart(ctx, {
            type: 'line',
            data: {
                labels: data.labels,
                datasets: [{
                    label: 'Progression Level',
                    data: data.values,
                    borderColor: '#FF6B35',
                    backgroundColor: 'rgba(255, 107, 53, 0.1)',
                    tension: 0.4,
                    fill: true
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        labels: {
                            color: '#e0e0e0'
                        }
                    }
                },
                scales: {
                    y: {
                        ticks: {
                            color: '#b0b0b0'
                        },
                        grid: {
                            color: 'rgba(255, 107, 53, 0.1)'
                        }
                    },
                    x: {
                        ticks: {
                            color: '#b0b0b0'
                        },
                        grid: {
                            color: 'rgba(255, 107, 53, 0.1)'
                        }
                    }
                }
            }
        });
    }
}

function createDominionsChart(data) {
    const ctx = document.getElementById('dominions-chart');
    if (ctx) {
        new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: data.labels,
                datasets: [{
                    data: data.values,
                    backgroundColor: [
                        '#FF6B35', '#004E89', '#00d084', '#ffd700',
                        '#0084ff', '#ff4444', '#9c27b0'
                    ]
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        labels: {
                            color: '#e0e0e0'
                        }
                    }
                }
            }
        });
    }
}

function loadActivity(activities) {
    const list = document.getElementById('activity-list');
    if (!list) return;

    list.innerHTML = activities.map(activity => `
        <div class="activity-item">
            <strong>${activity.player}</strong> ${activity.action}
            <div class="activity-time">${new Date(activity.timestamp).toLocaleString()}</div>
        </div>
    `).join('');
}

// Load Players
async function loadPlayers() {
    try {
        const response = await axios.get(`${API_BASE}/players`, {
            headers: { 'Authorization': `Bearer ${authToken}` }
        });

        const tbody = document.getElementById('players-table-body');
        tbody.innerHTML = response.data.players.map(player => `
            <tr>
                <td>${player.name}</td>
                <td>${player.bloodline || 'None'}</td>
                <td>${player.dominion || 'None'}</td>
                <td>${player.level}</td>
                <td>${player.faction || 'None'}</td>
                <td><span class="status-badge status-${player.status}">${player.status}</span></td>
                <td>
                    <button class="btn btn-primary" onclick="editPlayer('${player.uuid}')">Edit</button>
                    <button class="btn btn-danger" onclick="deletePlayer('${player.uuid}')">Delete</button>
                </td>
            </tr>
        `).join('');
    } catch (error) {
        console.error('Failed to load players:', error);
    }
}

// Load Factions
async function loadFactions() {
    try {
        const response = await axios.get(`${API_BASE}/factions`, {
            headers: { 'Authorization': `Bearer ${authToken}` }
        });

        const grid = document.getElementById('factions-grid');
        grid.innerHTML = response.data.factions.map(faction => `
            <div class="faction-card">
                <h3>${faction.name}</h3>
                <p>👑 King: ${faction.king}</p>
                <p>👥 Members: ${faction.memberCount}</p>
                <p>💰 Treasury: ${faction.treasury}</p>
                <button class="btn btn-primary" onclick="editFaction('${faction.id}')">Edit</button>
            </div>
        `).join('');
    } catch (error) {
        console.error('Failed to load factions:', error);
    }
}

// Load Religions
async function loadReligions() {
    try {
        const response = await axios.get(`${API_BASE}/religions`, {
            headers: { 'Authorization': `Bearer ${authToken}` }
        });

        const grid = document.getElementById('religions-grid');
        grid.innerHTML = response.data.religions.map(religion => `
            <div class="religion-card">
                <h3>${religion.name}</h3>
                <p>⛪ Founder: ${religion.founder}</p>
                <p>👥 Followers: ${religion.followerCount}</p>
                <p>✨ Faith: ${religion.faith}</p>
                <button class="btn btn-primary" onclick="editReligion('${religion.id}')">Edit</button>
            </div>
        `).join('');
    } catch (error) {
        console.error('Failed to load religions:', error);
    }
}

// Modal Functions
function openPlayerModal() {
    document.getElementById('player-modal').classList.add('show');
}

function openFactionModal() {
    document.getElementById('faction-modal').classList.add('show');
}

function openReligionModal() {
    document.getElementById('religion-modal').classList.add('show');
}

function closeModal(modalId) {
    document.getElementById(modalId).classList.remove('show');
}

// Close modal when clicking outside
window.onclick = function(event) {
    if (event.target.classList.contains('modal')) {
        event.target.classList.remove('show');
    }
}

// Form Submissions
document.getElementById('player-form')?.addEventListener('submit', async (e) => {
    e.preventDefault();
    try {
        await axios.post(`${API_BASE}/players`, {
            name: document.getElementById('modal-player-name').value,
            bloodline: document.getElementById('modal-bloodline').value,
            dominion: document.getElementById('modal-dominion').value,
            level: document.getElementById('modal-level').value
        }, {
            headers: { 'Authorization': `Bearer ${authToken}` }
        });
        closeModal('player-modal');
        loadPlayers();
    } catch (error) {
        alert('Error creating player: ' + error.message);
    }
});

document.getElementById('faction-form')?.addEventListener('submit', async (e) => {
    e.preventDefault();
    try {
        await axios.post(`${API_BASE}/factions`, {
            name: document.getElementById('modal-faction-name').value,
            king: document.getElementById('modal-faction-king').value,
            treasury: document.getElementById('modal-treasury').value
        }, {
            headers: { 'Authorization': `Bearer ${authToken}` }
        });
        closeModal('faction-modal');
        loadFactions();
    } catch (error) {
        alert('Error creating faction: ' + error.message);
    }
});

// Trigger World Event
async function triggerEvent(eventType) {
    if (!confirm(`Trigger ${eventType}? This will affect all players.`)) return;

    try {
        await axios.post(`${API_BASE}/events/trigger`, {
            type: eventType
        }, {
            headers: { 'Authorization': `Bearer ${authToken}` }
        });
        alert('Event triggered successfully!');
    } catch (error) {
        alert('Error triggering event: ' + error.message);
    }
}

// Save Settings
async function saveSettings() {
    try {
        await axios.post(`${API_BASE}/settings`, {
            serverName: document.getElementById('server-name').value,
            maxPlayers: document.getElementById('max-players').value,
            enableSync: document.getElementById('enable-sync').checked,
            syncInterval: document.getElementById('sync-interval').value,
            eventFrequency: document.getElementById('event-frequency').value
        }, {
            headers: { 'Authorization': `Bearer ${authToken}` }
        });
        alert('Settings saved successfully!');
    } catch (error) {
        alert('Error saving settings: ' + error.message);
    }
}

// Initialize
window.addEventListener('load', () => {
    loadDashboard();
});
