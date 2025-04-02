<?php

namespace modules\models;

require_once __DIR__ . '/../../_assets/includes/Database.php';
require_once __DIR__ . '/ClassObject/Paniers.php';

use modules\models\ClassObject\Paniers;
use _assets\includes\Database;

/**
 * Class PaniersModel
 *
 * Modèle pour gérer les paniers.
 */
class PaniersModel {

    /**
     * @var \PDO
     */
    private $pdo;

    /**
     * PaniersModel constructor.
     */
    public function __construct()
    {
        $this->pdo = Database::getInstance();
    }
}