<?php
namespace modules\views;

require_once __DIR__ . '/../models/CommandesModel.php';

use modules\models\CommandesModel;

/**
 * Class CommandesView
 *
 * Vue pour afficher les commandes.
 */
class CommandesView {
    /**
     * @var CommandesModel
     */
    private $commandesModel;

    /**
     * CommandesView constructor.
     */
    public function __construct() {
        $this->commandesModel = new CommandesModel();
    }

    /**
     * Affiche la vue des commandes.
     *
     * @return void
     */
    public function show() {
        include 'Header.php';

        echo <<<HTML
        <main class="commandes-container">
            <h1>Mes Commandes</h1>
            <p class="subtitle">Historique de vos commandes passées</p>

            <div id="loading-spinner" class="spinner"></div>
            <div id="error-message" class="error-message"></div>
            <div id="commandes-grid" class="commandes-grid"></div>
        </main>

        <link rel="stylesheet" href="../../_assets/styles/Commandes.css">
        <script src="../../_assets/script/CommandesApp.js"></script>
        HTML;

        include 'Footer.php';
    }
}