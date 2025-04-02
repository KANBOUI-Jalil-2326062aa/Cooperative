<?php

namespace modules\controllers;

require_once __DIR__ . '/../models/ProduitsModel.php';
require_once __DIR__ . '/../views/ProduitsView.php';

use modules\views\ProduitsView;
use modules\models\ProduitsModel;

/**
 * Class ProduitsController
 *
 * Contrôleur pour gérer les produits.
 */
class ProduitsController {
    /**
     * @var ProduitsModel
     */
    private $produitsModel;

    /**
     * ProduitsController constructor.
     */
    public function __construct() {
        $this->produitsModel = new ProduitsModel();
    }

    /**
     * Exécute l'affichage des produits.
     *
     * @return void
     */
    public function execute() {
        (new ProduitsView())->show();
    }
}