<?php

namespace modules\views;

require_once __DIR__ . '/../models/CommandeModel.php';

use modules\models\CommandesModel;

class CommandeView {
    private $commandesModel;

    public function __construct() {
        $this->commandesModel = new CommandesModel();
    }

    public function show() {
        include 'Header.php';

        echo '<main class="commandes-container">';
        echo '<h1>Mes Commandes</h1>';
        echo '<p class="subtitle">Historique de vos commandes passées</p>';

        $commandes = $this->commandesModel->getCommandes();

        if (!empty($commandes)) {
            echo '<div class="commandes-grid">';
            foreach ($commandes as $commande) {
                echo $this->renderCommandeCard($commande);
            }
            echo '</div>';
        } else {
            echo '<p class="no-commandes">Vous n\'avez pas encore passé de commande.</p>';
        }

        // Modal pour les détails de commande
        echo '
        <div id="commandeModal" class="modal">
            <div class="modal-content">
                <span class="close">&times;</span>
                <h2 id="modalTitle">Détails de la commande</h2>
                <div class="modal-body">
                    <div class="modal-info">
                        <p><strong>N° Commande:</strong> <span id="modalId"></span></p>
                        <p><strong>Date de retrait:</strong> <span id="modalDate"></span></p>
                        <p><strong>Lieu de retrait:</strong> <span id="modalLieu"></span></p>
                        <p><strong>Panier associé:</strong> <span id="modalPanier"></span></p>
                        <p><strong>Prix total:</strong> <span id="modalPrix"></span> €</p>
                    </div>
                    <div class="modal-actions">
                        <button class="btn-print">Imprimer</button>
                        <button class="btn-cancel">Annuler la commande</button>
                    </div>
                </div>
            </div>
        </div>';

        echo '</main>';

        // Préparation des données pour JavaScript
        $commandesData = [];
        foreach ($commandes as $commande) {
            $commandesData[] = [
                'id' => $commande->getId(),
                'panierId' => $commande->getPanierId(),
                'lieuRelai' => $commande->getLieuRelai(),
                'dateRetrait' => $commande->getDateRetrait(),
                'prixTotal' => $commande->getPrixTotal()
            ];
        }

        echo '<script>';
        echo 'var commandesData = ' . json_encode($commandesData) . ';';
        echo '</script>';

        echo '<link rel="stylesheet" href="/_assets/styles/Commandes.css">';
        echo '<link rel="stylesheet" href="/_assets/styles/CommandesModal.css">';
        echo '<script src="/_assets/script/CommandesModal.js"></script>';
        include 'Footer.php';
    }

    private function renderCommandeCard($commande) {
        return '
        <div class="commande-card" data-id="' . $commande->getId() . '">
            <div class="commande-header">
                <h3>Commande n°' . $commande->getId() . '</h3>
                <span class="price">' . number_format($commande->getPrixTotal(), 2) . ' €</span>
            </div>
            <div class="commande-body">
                <p><i class="fas fa-calendar-alt"></i> ' . $commande->getDateRetrait() . '</p>
                <p><i class="fas fa-map-marker-alt"></i> ' . htmlspecialchars($commande->getLieuRelai()) . '</p>
                <p><i class="fas fa-shopping-basket"></i> Panier n°' . $commande->getPanierId() . '</p>
                <button class="voir-details">Détails</button>
            </div>
        </div>';
    }
}