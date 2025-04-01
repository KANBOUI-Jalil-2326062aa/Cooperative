<?php

namespace modules\models;

require_once __DIR__ . '/ClassObject/Commandes.php';

use modules\models\ClassObject\Commandes;

class CommandesModel {
    private $commandes;

    public function __construct() {
        // Données d'exemple - à remplacer par votre vraie source de données
        $this->commandes = [
            new Commandes(1, 5, "Boulangerie du Marché - Paris 10ème", "2023-06-15", 24.50),
            new Commandes(2, 8, "Primeur des Ternes - Paris 17ème", "2023-06-18", 32.75),
            new Commandes(3, 12, "Fromagerie du Plateau - Paris 18ème", "2023-06-20", 42.30),
        ];
    }

    public function getCommandes() {
        return $this->commandes;
    }

    public function getCommandeById($id) {
        foreach ($this->commandes as $commande) {
            if ($commande->getId() == $id) {
                return $commande;
            }
        }
        return null;
    }
}