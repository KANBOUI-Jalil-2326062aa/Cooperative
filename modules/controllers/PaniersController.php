<?php

namespace modules\controllers;

require_once __DIR__ . '/../models/PaniersModel.php';
require_once __DIR__ . '/../views/PaniersView.php';

use modules\views\PaniersView;
use modules\models\PaniersModel;

class PaniersController {

    private $paniersModel;
    public function __construct() {
        $this->paniersModel = new PaniersModel();
    }

    public function execute() {
        (new PaniersView())->show();
    }
}