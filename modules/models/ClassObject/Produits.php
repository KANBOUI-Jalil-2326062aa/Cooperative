<?php

namespace modules\models;

class Produits {

    private $id;

    private $nom;

    private $quantite;

    private $prix;

    public function __construct($id, $nom, $quantite, $prix) {
        $this->id = $id;
        $this->nom = $nom;
        $this->quantite = $quantite;
        $this->prix = $prix;
    }

    public function getId() {
        return $this->id;
    }

    public function getNom() {
        return $this->nom;
    }

    public function getQuantite() {
        return $this->quantite;
    }

    public function getPrix() {
        return $this->prix;
    }

    public function setId($id) {
        $this->id = $id;
    }

    public function setNom($nom) {
        $this->nom = $nom;
    }

    public function setQuantite($quantite) {
        $this->quantite = $quantite;
    }

    public function setPrix($prix) {
        $this->prix = $prix;
    }
}