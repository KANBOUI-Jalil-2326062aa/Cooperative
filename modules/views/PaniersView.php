<?php
namespace modules\views;

/**
 * Class PaniersView
 *
 * Vue pour afficher les paniers.
 */
class PaniersView {
    /**
     * Affiche la vue des paniers.
     *
     * @return void
     */
    public function show() {
        include 'Header.php';

        echo <<<HTML
        <main class="paniers-container">
            <div class="page-header">
                <h1>Nos Paniers</h1>
                <p class="subtitle">Découvrez nos paniers préparés avec soin</p>
            </div>

            <div id="loading-spinner" class="spinner"></div>
            <div id="error-message" class="error-message" style="display:none;"></div>
            <div id="paniers-grid" class="paniers-grid"></div>

            <div id="panierModal" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <h2 id="modalTitle">Détails du panier</h2>
                    <div class="modal-body">
                        <div class="modal-info">
                            <div class="info-row">
                                <span class="info-label">Date de retrait:</span>
                                <span id="modalDate" class="info-value"></span>
                            </div>
                            <div class="info-row">
                                <span class="info-label">Lieu de retrait:</span>
                                <span id="modalLieu" class="info-value"></span>
                            </div>
                            <div class="info-row">
                                <span class="info-label">Prix total:</span>
                                <span id="modalPrix" class="info-value"></span>
                            </div>
                            <div class="info-row">
                                <span class="info-label">Statut:</span>
                                <span id="modalStatus" class="info-value"></span>
                            </div>
                        </div>
                        <div class="modal-products">
                            <h3>Contenu:</h3>
                            <ul id="modalProduits"></ul>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <link rel="stylesheet" href="../../_assets/styles/Paniers.css">
        <link rel="stylesheet" href="../../_assets/styles/PaniersModal.css">
        <script src="../../_assets/script/PaniersApp.js"></script>
        HTML;

        include 'Footer.php';
    }
}