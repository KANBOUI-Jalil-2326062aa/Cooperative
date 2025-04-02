<?php

namespace modules\controllers;

require __DIR__ . '/../views/CommandesView.php';

use modules\views\CommandesView;

/**
 * Class CommandeController
 *
 * Contrôleur pour gérer les commandes.
 */
class CommandeController {
    /**
     * Exécute l'affichage des commandes.
     *
     * @return void
     */
    public function execute() {
        (new CommandesView())->show();
    }
}