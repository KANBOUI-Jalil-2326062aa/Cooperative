<?php

namespace _assets\includes;

/**
 * Class Database
 *
 * Gère la connexion à la base de données.
 */
class Database {
    private $pdo;
    private static $instance = null;
    private $host = 'mysql-turlure.alwaysdata.net';
    private $db = 'turlure_cooperative';
    private $user = 'turlure_projet';
    private $pass = 'projetcooperative';

    /**
     * Database constructor.
     *
     * Initialise la connexion à la base de données.
     */
    private function __construct() {
        try {
            $this->pdo = new \PDO("mysql:host={$this->host};dbname={$this->db};charset=utf8",
                $this->user, $this->pass,
                [
                    \PDO::ATTR_ERRMODE => \PDO::ERRMODE_EXCEPTION,
                    \PDO::ATTR_DEFAULT_FETCH_MODE => \PDO::FETCH_ASSOC
                ]);
        } catch (\PDOException $e) {
            die("Erreur de connexion à la base de données : " . $e->getMessage());
        }
    }

    /**
     * Obtient l'instance de la connexion PDO.
     *
     * @return \PDO
     */
    public static function getInstance() {
        if (!self::$instance) {
            self::$instance = new Database();
        }
        return self::$instance->pdo;
    }
}