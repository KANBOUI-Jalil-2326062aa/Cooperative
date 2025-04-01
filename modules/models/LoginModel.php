<?php

namespace modules\models;
require_once __DIR__ . '/../../_assets/includes/Database.php';

use _assets\includes\Database;

class LoginModel {
    private $pdo;

    public function __construct() {
        $this->pdo = Database::getInstance();
    }

    public function authenticate($username, $password) {
        $stmt = $this->pdo->prepare("SELECT * FROM Utilisateurs WHERE nom = :username AND mdp = :password");
        $stmt->bindParam(':username', $username);
        $stmt->bindParam(':password', $password);
        $stmt->execute();

        return $stmt->fetch(\PDO::FETCH_ASSOC);
    }
}