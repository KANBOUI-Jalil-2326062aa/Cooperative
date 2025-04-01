document.addEventListener('DOMContentLoaded', function() {
    // Initialisation des éléments UI
    const spinner = document.getElementById('loading-spinner');
    const grid = document.getElementById('paniers-grid');
    const errorMsg = document.getElementById('error-message');

    // Chargement initial des données
    loadPaniers();

    // Gestion du modal
    document.querySelector('.close').addEventListener('click', closeModal);
    document.getElementById('panierModal').addEventListener('click', function(e) {
        if (e.target === this) closeModal();
    });

    // Fonction principale de chargement
    async function loadPaniers() {
        try {
            showLoadingState();

            const response = await fetch('http://localhost:8080/Paniers-1.0-SNAPSHOT/api/paniers', {
                headers: {
                    'Accept': 'application/json'
                }
            });

            if (!response.ok) {
                throw new Error(`Erreur HTTP: ${response.status}`);
            }

            const paniers = await response.json();
            renderPaniers(paniers);

            // Stockage des données pour les détails
            window.paniersData = paniers;

        } catch (error) {
            console.error('Erreur de chargement:', error);
            showErrorState(error);
        } finally {
            hideLoadingState();
        }
    }

    // Fonction de rendu des paniers
    function renderPaniers(paniers) {
        if (!paniers || paniers.length === 0) {
            grid.innerHTML = `
                <div class="no-paniers">
                    <i class="fas fa-info-circle"></i>
                    <p>Aucun panier disponible actuellement</p>
                </div>
            `;
            return;
        }

        grid.innerHTML = paniers.map(panier => `
            <div class="panier-card" data-id="${panier.idPanier}">
                <div class="panier-header">
                    <h3>${panier.idPanier}</h3>
                    <span class="price">${panier.prixTotal.toFixed(2)} €</span>
                    ${panier.estValide ? '' : '<span class="badge warning">Indisponible</span>'}
                </div>
                <div class="panier-body">
                    <p><i class="fas fa-calendar-alt"></i> ${formatDate(panier.dateRetrait)}</p>
                    <p><i class="fas fa-map-marker-alt"></i> ${escapeHtml(panier.lieuRelais)}</p>
                    <button class="voir-details">Voir le contenu</button>
                </div>
            </div>
        `).join('');

        // Ajout des écouteurs d'événements
        document.querySelectorAll('.panier-card').forEach(card => {
            card.addEventListener('click', function() {
                const panierId = this.dataset.id;
                const panier = window.paniersData.find(p => p.idPanier === panierId);
                showPanierDetails(panier);
            });
        });
    }

    // Affichage des détails du panier
    function showPanierDetails(panier) {
        if (!panier) return;

        // Remplissage des infos de base
        document.getElementById('modalTitle').textContent = `Panier ${panier.idPanier}`;
        document.getElementById('modalDate').textContent = formatDate(panier.dateRetrait);
        document.getElementById('modalLieu').textContent = panier.lieuRelais;
        document.getElementById('modalPrix').textContent = panier.prixTotal.toFixed(2);

        // Gestion du statut
        const statusElement = document.getElementById('modalStatus');
        statusElement.textContent = panier.estValide ? 'Disponible' : 'Indisponible';
        statusElement.className = panier.estValide ? 'status-available' : 'status-unavailable';

        // Remplissage des produits
        const produitsList = document.getElementById('modalProduits');
        produitsList.innerHTML = panier.produitPaniers.map(produit => `
            <li class="product-item">
                <div class="product-info">
                    <span class="product-name">${produit.produit_id}</span>
                    <span class="product-quantity">${produit.quantite} ${produit.quantite > 1 ? 'unités' : 'unité'}</span>
                </div>
            </li>
        `).join('');

        // Affichage du modal
        document.getElementById('panierModal').style.display = 'block';
    }

    // Fonctions utilitaires
    function formatDate(dateString) {
        const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
        return new Date(dateString).toLocaleDateString('fr-FR', options);
    }

    function escapeHtml(unsafe) {
        return unsafe
            .replace(/&/g, "&amp;")
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;")
            .replace(/"/g, "&quot;")
            .replace(/'/g, "&#039;");
    }

    function closeModal() {
        document.getElementById('panierModal').style.display = 'none';
    }

    // Gestion des états UI
    function showLoadingState() {
        spinner.style.display = 'block';
        grid.style.display = 'none';
        errorMsg.style.display = 'none';
    }

    function hideLoadingState() {
        spinner.style.display = 'none';
        grid.style.display = 'grid';
    }

    function showErrorState(error) {
        errorMsg.innerHTML = `
            <p>Erreur de chargement : ${error.message}</p>
            <button class="retry-button" onclick="window.location.reload()">
                <i class="fas fa-sync-alt"></i> Réessayer
            </button>
        `;
        errorMsg.style.display = 'block';

        grid.innerHTML = `
            <div class="no-paniers">
                <i class="fas fa-exclamation-triangle"></i>
                <p>Impossible de charger les paniers</p>
            </div>
        `;
    }
});