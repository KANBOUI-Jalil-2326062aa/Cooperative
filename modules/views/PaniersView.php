<?php
namespace modules\views;

class PaniersView {
    public function show() {
        include 'Header.php';

        echo <<<HTML
        <main class="paniers-container">
            <h1>Nos Paniers</h1>
            <div id="loading-spinner" class="spinner"></div>
            <div id="paniers-grid" class="paniers-grid"></div>
            <div id="error-message" class="error-message" style="display:none;"></div>

            <!-- Modal Structure -->
            <div id="panierModal" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <h2 id="modalTitle">Détails du panier</h2>
                    <div class="modal-body">
                        <div class="modal-info">
                            <p><strong>Date de retrait:</strong> <span id="modalDate"></span></p>
                            <p><strong>Lieu de retrait:</strong> <span id="modalLieu"></span></p>
                            <p><strong>Prix total:</strong> <span id="modalPrix"></span> €</p>
                            <p><strong>Statut:</strong> <span id="modalStatus"></span></p>
                        </div>
                        <div class="modal-products">
                            <h3>Contenu du panier:</h3>
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