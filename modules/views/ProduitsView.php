<?php
namespace modules\views;

require_once __DIR__ . '/../models/ProduitsModel.php';

use modules\models\ProduitsModel;

/**
 * Class ProduitsView
 *
 * Vue pour afficher les produits.
 */
class ProduitsView {
    /**
     * @var ProduitsModel
     */
    private $produitsModel;

    /**
     * ProduitsView constructor.
     */
    public function __construct() {
        $this->produitsModel = new ProduitsModel();
    }

    /**
     * Affiche la vue des produits.
     *
     * @return void
     */
    public function show() {
        include 'Header.php';

        echo <<<HTML
        <main class="produits-container">
            <h1>Nos Produits</h1>
            <p class="subtitle">Découvrez notre sélection de produits frais</p>

            <div id="loading-spinner" class="spinner"></div>
            <div id="error-message" class="error-message"></div>
            <div id="produits-grid" class="produits-grid"></div>
        </main>

        <link rel="stylesheet" href="../../_assets/styles/Produits.css">
        <script src="../../_assets/script/ProduitsApp.js"></script>
        HTML;

        include 'Footer.php';
    }
}