<?php

namespace modules\models;

require_once __DIR__ . '/../../_assets/includes/Database.php';
require_once __DIR__ . '/ClassObject/Paniers.php';

use modules\models\ClassObject\Paniers;

class PaniersModel {
    private $apiUrl = 'http://localhost:8080/Paniers-1.0-SNAPSHOT/api/paniers';

    public function getPaniers() {
        try {
            $ch = curl_init($this->apiUrl);
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
            curl_setopt($ch, CURLOPT_HTTPHEADER, [
                'Accept: application/json'
            ]);

            $response = curl_exec($ch);
            $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);

            if ($httpCode !== 200) {
                throw new \Exception("API returned status code: $httpCode");
            }

            $apiData = json_decode($response, true);

            return array_map([$this, 'transformApiData'], $apiData);
        } catch (\Exception $e) {
            error_log('API Error: ' . $e->getMessage());
            return [];
        }
    }

    private function transformApiData($panierData) {
        $produits = [];
        foreach ($panierData['produitPaniers'] as $produit) {
            $produits[$produit['produit_id']] = [
                'quantite' => $produit['quantite'],
                'prix_unitaire' => $this->calculateUnitPrice($produit)
            ];
        }

        return new Paniers(
            $panierData['idPanier'],
            $panierData['dateRetrait'],
            $panierData['lieuRelais'],
            $panierData['prixTotal'],
            $produits,
            $panierData['estValide']
        );
    }

    private function calculateUnitPrice($produit) {
        // Calcul approximatif basé sur le prix total
        // A adapter selon votre logique métier
        return $produit['quantite'] > 0
            ? round($produit['prixTotal'] / $produit['quantite'], 2)
            : 0;
    }

    public function getPanierById($id) {
        $paniers = $this->getPaniers();
        foreach ($paniers as $panier) {
            if ($panier->getId() === $id) {
                return $panier;
            }
        }
        return null;
    }
}