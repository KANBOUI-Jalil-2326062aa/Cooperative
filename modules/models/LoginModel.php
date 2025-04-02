<?php

namespace modules\models;
require_once __DIR__ . '/../../_assets/includes/Database.php';

use _assets\includes\Database;

/**
 * Class LoginModel
 *
 * Modèle pour gérer l'authentification des utilisateurs.
 */
class LoginModel {
    /**
     * @var \PDO
     */
    private $pdo;

    /**
     * LoginModel constructor.
     */
    public function __construct() {
        $this->pdo = Database::getInstance();
    }

    /**
     * Authentifie un utilisateur avec son nom d'utilisateur et son mot de passe.
     *
     * @param string $username Le nom d'utilisateur.
     * @param string $password Le mot de passe.
     * @return array|false Les informations de l'utilisateur si authentifié, sinon false.
     */
    public function authenticate($username, $password) {
        $stmt = $this->pdo->prepare("SELECT * FROM Utilisateurs WHERE nom = :username AND mdp = :password");
        $stmt->bindParam(':username', $username);
        $stmt->bindParam(':password', $password);
        $stmt->execute();

        return $stmt->fetch(\PDO::FETCH_ASSOC);
    }
}