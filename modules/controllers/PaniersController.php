<?php

namespace modules\controllers;

require_once __DIR__ . '/../models/PaniersModel.php';
require_once __DIR__ . '/../views/PaniersView.php';

use modules\views\PaniersView;
use modules\models\PaniersModel;

/**
 * Class PaniersController
 *
 * Contrôleur pour gérer les paniers.
 */
class PaniersController {

    /**
     * @var PaniersModel
     */
    private $paniersModel;

    /**
     * PaniersController constructor.
     */
    public function __construct() {
        $this->paniersModel = new PaniersModel();
    }

    /**
     * Exécute l'affichage des paniers.
     *
     * @return void
     */
    public function execute() {
        (new PaniersView())->show();
    }
}