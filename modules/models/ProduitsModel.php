<?php
namespace modules\models;

require_once __DIR__ . '/ClassObject/Produits.php';
require_once __DIR__ . '/../../_assets/includes/Database.php';

use modules\models\ClassObject\Produits;
use _assets\includes\Database;

/**
 * Class ProduitsModel
 *
 * Modèle pour gérer les produits.
 */
class ProduitsModel {
    /**
     * @var \PDO
     */
    private $pdo;

    /**
     * ProduitsModel constructor.
     */
    public function __construct()
    {
        $this->pdo = Database::getInstance();
    }
}