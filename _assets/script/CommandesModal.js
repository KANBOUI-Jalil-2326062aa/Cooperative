document.addEventListener('DOMContentLoaded', function() {
    // Écouteurs pour les cartes de commande
    document.querySelectorAll('.commande-card, .voir-details').forEach(element => {
        element.addEventListener('click', function(e) {
            if (e.target.classList.contains('voir-details')) {
                e.stopPropagation();
            }

            const card = e.target.closest('.commande-card');
            if (!card) return;

            const commandeId = parseInt(card.dataset.id);
            showCommandeDetails(commandeId);
        });
    });

    // Fermeture du modal
    document.querySelector('#commandeModal .close').addEventListener('click', closeModal);
    document.getElementById('commandeModal').addEventListener('click', function(e) {
        if (e.target === this) closeModal();
    });

    // Actions du modal
    document.querySelector('.btn-print').addEventListener('click', function() {
        window.print();
    });

    document.querySelector('.btn-cancel').addEventListener('click', function() {
        if (confirm('Êtes-vous sûr de vouloir annuler cette commande ?')) {
            alert('Commande annulée (fonctionnalité à implémenter)');
            closeModal();
        }
    });
});

function showCommandeDetails(commandeId) {
    const commande = commandesData.find(c => c.id === commandeId);
    if (!commande) return;

    // Remplir les informations du modal
    document.getElementById('modalTitle').textContent = `Commande n°${commande.id}`;
    document.getElementById('modalId').textContent = commande.id;
    document.getElementById('modalDate').textContent = formatDate(commande.dateRetrait);
    document.getElementById('modalLieu').textContent = commande.lieuRelai;
    document.getElementById('modalPanier').textContent = `Panier n°${commande.panierId}`;
    document.getElementById('modalPrix').textContent = commande.prixTotal.toFixed(2);

    // Afficher le modal
    document.getElementById('commandeModal').style.display = 'block';
}

function closeModal() {
    document.getElementById('commandeModal').style.display = 'none';
}

function formatDate(dateString) {
    const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(dateString).toLocaleDateString('fr-FR', options);
}