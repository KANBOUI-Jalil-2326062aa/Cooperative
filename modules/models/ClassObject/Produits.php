<?php

namespace modules\models;

class Produits {

    private $id;

    private $nom;

    private $quantite;

    private $prix;

    /**
     * Produits constructor.
     *
     * @param int $id
     * @param string $nom
     * @param int $quantite
     * @param float $prix
     */
    public function __construct($id, $nom, $quantite, $prix) {
        $this->id = $id;
        $this->nom = $nom;
        $this->quantite = $quantite;
        $this->prix = $prix;
    }

    /**
     * Obtient l'ID du produit.
     *
     * @return int
     */
    public function getId() {
        return $this->id;
    }

    /**
     * Obtient le nom du produit.
     *
     * @return string
     */
    public function getNom() {
        return $this->nom;
    }

    /**
     * Obtient la quantité du produit.
     *
     * @return int
     */
    public function getQuantite() {
        return $this->quantite;
    }

    /**
     * Obtient le prix du produit.
     *
     * @return float
     */
    public function getPrix() {
        return $this->prix;
    }

    /**
     * Définit l'ID du produit.
     *
     * @param int $id
     * @return void
     */
    public function setId($id) {
        $this->id = $id;
    }

    /**
     * Définit le nom du produit.
     *
     * @param string $nom
     * @return void
     */
    public function setNom($nom) {
        $this->nom = $nom;
    }

    /**
     * Définit la quantité du produit.
     *
     * @param int $quantite
     * @return void
     */
    public function setQuantite($quantite) {
        $this->quantite = $quantite;
    }

    /**
     * Définit le prix du produit.
     *
     * @param float $prix
     * @return void
     */
    public function setPrix($prix) {
        $this->prix = $prix;
    }
}