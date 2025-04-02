document.addEventListener('DOMContentLoaded', function() {
    const spinner = document.getElementById('loading-spinner');
    const grid = document.getElementById('commandes-grid');
    const errorMsg = document.getElementById('error-message');

    loadCommandes();

    async function loadCommandes() {
        try {
            showLoadingState();
            const response = await fetch('http://localhost:8080/Commandes-1.0-SNAPSHOT/api/commandes', {
                headers: {
                    'Accept': 'application/json'
                }
            });

            if (!response.ok) {
                throw new Error(`Failed to fetch (HTTP ${response.status})`);
            }

            const commandes = await response.json();
            renderCommandes(commandes);

        } catch (error) {
            console.error('Erreur de chargement:', error);
            showErrorState(error);
        } finally {
            hideLoadingState();
        }
    }

    function renderCommandes(commandes) {
        if (!commandes || commandes.length === 0) {
            grid.innerHTML = `
                <div class="no-commandes">
                    <i class="fas fa-info-circle"></i>
                    <p>Aucune commande disponible</p>
                </div>
            `;
            return;
        }

        grid.innerHTML = commandes.map(commande => `
            <div class="commande-card">
                <div class="commande-header">
                    <h3>Commande ${commande.id}</h3>
                </div>
                <div class="commande-body">
                    <p><i class="fas fa-shopping-basket"></i> Panier: ${commande.idPanier}</p>
                    <p><i class="fas fa-user"></i> Utilisateur: ${commande.idUtilisateur}</p>
                    <div class="commande-footer">
                        <button class="print-btn" 
                                data-commande-id="${commande.id}"
                                data-panier-id="${commande.idPanier}"
                                data-user-id="${commande.idUtilisateur}">
                            <i class="fas fa-print"></i> Imprimer
                        </button>
                    </div>
                </div>
            </div>
        `).join('');

        document.querySelectorAll('.print-btn').forEach(btn => {
            btn.addEventListener('click', function() {
                const commandeId = this.getAttribute('data-commande-id');
                const panierId = this.getAttribute('data-panier-id');
                const userId = this.getAttribute('data-user-id');
                printCommande(commandeId, panierId, userId);
            });
        });
    }

    function printCommande(commandeId, panierId, userId) {
        const printWindow = window.open('', '_blank');
        const date = new Date().toLocaleDateString('fr-FR', {
            weekday: 'long',
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });

        printWindow.document.write(`
            <!DOCTYPE html>
            <html>
            <head>
                <title>Bon de commande ${commandeId}</title>
                <meta charset="UTF-8">
                <style>
                    body { font-family: 'Arial', sans-serif; margin: 0; padding: 20px; color: #333; }
                    .print-container { max-width: 800px; margin: 0 auto; }
                    .print-header { text-align: center; margin-bottom: 20px; }
                    .print-header h1 { color: #4361ee; margin-bottom: 5px; }
                    .print-info { margin: 20px 0; }
                    .print-info p { margin: 5px 0; }
                    .print-section { margin-bottom: 15px; }
                    .print-date { text-align: right; margin-bottom: 20px; }
                    @media print {
                        body { padding: 10mm; }
                    }
                </style>
            </head>
            <body>
                <div class="print-container">
                    <div class="print-header">
                        <h1>Bon de commande</h1>
                    </div>
                    <div class="print-date">
                        <p>Date: ${date}</p>
                    </div>
                    <div class="print-section">
                        <h2>Informations commande</h2>
                        <div class="print-info">
                            <p><strong>Référence commande:</strong> ${commandeId}</p>
                            <p><strong>Numéro panier:</strong> ${panierId}</p>
                            <p><strong>ID Utilisateur:</strong> ${userId}</p>
                        </div>
                    </div>
                    <div class="print-footer">
                        <p>Merci pour votre commande !</p>
                    </div>
                </div>
                <script>
                    setTimeout(function() {
                        window.print();
                        window.close();
                    }, 200);
                </script>
            </body>
            </html>
        `);
        printWindow.document.close();
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
            <div class="no-commandes">
                <i class="fas fa-exclamation-triangle"></i>
                <p>Impossible de charger les commandes</p>
            </div>
        `;
    }
});