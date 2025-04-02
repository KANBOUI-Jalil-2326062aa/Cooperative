<?php

namespace modules\controllers;

require __DIR__ . '/../views/HomepageView.php';

use modules\views\HomepageView;

/**
 * Class HomepageController
 *
 * Contrôleur pour gérer la page d'accueil.
 */
class HomepageController {
    /**
     * Exécute l'affichage de la page d'accueil.
     *
     * @return void
     */
    public function execute() {
        (new HomepageView())->show();
    }
}