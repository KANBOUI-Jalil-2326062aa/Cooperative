<?php

namespace modules\models\ClassObject;

/**
 * Class Paniers
 *
 * Représente un panier.
 */
class Paniers {

    private $id;
    private $date_retrait;
    private $lieu_relais;
    private $prix_total;
    private $liste_produits;

    /**
     * Paniers constructor.
     *
     * @param int $id
     * @param string $date_retrait
     * @param string $lieu_relais
     * @param float $prix_total
     * @param array $liste_produits
     */
    public function __construct($id, $date_retrait, $lieu_relais, $prix_total, $liste_produits) {
        $this->id = $id;
        $this->date_retrait = $date_retrait;
        $this->lieu_relais = $lieu_relais;
        $this->prix_total = $prix_total;
        $this->liste_produits = $liste_produits;
    }

    /**
     * Obtient l'ID du panier.
     *
     * @return int
     */
    public function getId() {
        return $this->id;
    }

    /**
     * Obtient la date de retrait.
     *
     * @return string
     */
    public function getDateRetrait() {
        return $this->date_retrait;
    }

    /**
     * Obtient le lieu de relais.
     *
     * @return string
     */
    public function getLieuRelais() {
        return $this->lieu_relais;
    }

    /**
     * Obtient le prix total.
     *
     * @return float
     */
    public function getPrixTotal() {
        return $this->prix_total;
    }

    /**
     * Obtient la liste des produits.
     *
     * @return array
     */
    public function getListeProduits() {
        return $this->liste_produits;
    }

    /**
     * Définit l'ID du panier.
     *
     * @param int $id
     * @return void
     */
    public function setId($id) {
        $this->id = $id;
    }

    /**
     * Définit la date de retrait.
     *
     * @param string $date_retrait
     * @return void
     */
    public function setDateRetrait($date_retrait) {
        $this->date_retrait = $date_retrait;
    }

    /**
     * Définit le lieu de relais.
     *
     * @param string $lieu_relais
     * @return void
     */
    public function setLieuRelais($lieu_relais) {
        $this->lieu_relais = $lieu_relais;
    }

    /**
     * Définit le prix total.
     *
     * @param float $prix_total
     * @return void
     */
    public function setPrixTotal($prix_total) {
        $this->prix_total = $prix_total;
    }

    /**
     * Définit la liste des produits.
     *
     * @param array $liste_produits
     * @return void
     */
    public function setListeProduits($liste_produits) {
        $this->liste_produits = $liste_produits;
    }

}