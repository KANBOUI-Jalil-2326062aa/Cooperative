<?php

namespace modules\models;

require_once __DIR__ . '/ClassObject/Commandes.php';
require_once __DIR__ . '/../../_assets/includes/Database.php';

use modules\models\ClassObject\Commandes;
use _assets\includes\Database;

/**
 * Class CommandesModel
 *
 * Modèle pour gérer les commandes.
 */
class CommandesModel {

    /**
     * @var \PDO
     */
    private $pdo;

    /**
     * CommandesModel constructor.
     */
    public function __construct()
    {
        $this->pdo = Database::getInstance();
    }
}