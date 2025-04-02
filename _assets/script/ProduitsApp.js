document.addEventListener('DOMContentLoaded', function() {
    const spinner = document.getElementById('loading-spinner');
    const grid = document.getElementById('produits-grid');
    const errorMsg = document.getElementById('error-message');

    loadProduits();

    async function loadProduits() {
        try {
            showLoadingState();

            const response = await fetch('http://localhost:8080/ProduitsUtilisateurs-1.0-SNAPSHOT/api/produits', {
                headers: {
                    'Accept': 'application/json'
                }
            });

            if (!response.ok) {
                throw new Error(`Erreur HTTP: ${response.status}`);
            }

            const produits = await response.json();
            renderProduits(produits);

        } catch (error) {
            console.error('Erreur de chargement:', error);
            showErrorState(error);
        } finally {
            hideLoadingState();
        }
    }

    function renderProduits(produits) {
        if (!produits || produits.length === 0) {
            grid.innerHTML = `
                <div class="no-produits">
                    <i class="fas fa-info-circle"></i>
                    <p>Aucun produit disponible actuellement</p>
                </div>
            `;
            return;
        }

        grid.innerHTML = produits.map(produit => `
            <div class="produit-card">
                <div class="produit-header">
                    <h3>${produit.nom}</h3>
                </div>
                <div class="produit-body">
                    <p><i class="fas fa-box-open"></i> Quantité: ${produit.quantite}</p>
                    <p><i class="fas fa-tag"></i> Prix: <span class="price">${produit.prix.toFixed(2)} €</span></p>
                </div>
            </div>
        `).join('');
    }

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
            <div class="no-produits">
                <i class="fas fa-exclamation-triangle"></i>
                <p>Impossible de charger les produits</p>
            </div>
        `;
    }
});