<?php

namespace modules\models\ClassObject;

class Paniers {

    private $id;

    private $date_retrait;

    private $lieu_relais;

    private $prix_total;

    private $liste_produits;

    public function __construct($id, $date_retrait, $lieu_relais, $prix_total, $liste_produits) {
        $this->id = $id;
        $this->date_retrait = $date_retrait;
        $this->lieu_relais = $lieu_relais;
        $this->prix_total = $prix_total;
        $this->liste_produits = $liste_produits;
    }

    public function getId() {
        return $this->id;
    }

    public function getDateRetrait() {
        return $this->date_retrait;
    }

    public function getLieuRelais() {
        return $this->lieu_relais;
    }

    public function getPrixTotal() {
        return $this->prix_total;
    }

    public function getListeProduits() {
        return $this->liste_produits;
    }

    public function setId($id) {
        $this->id = $id;
    }

    public function setDateRetrait($date_retrait) {
        $this->date_retrait = $date_retrait;
    }

    public function setLieuRelais($lieu_relais) {
        $this->lieu_relais = $lieu_relais;
    }

    public function setPrixTotal($prix_total) {
        $this->prix_total = $prix_total;
    }

    public function setListeProduits($liste_produits) {
        $this->liste_produits = $liste_produits;
    }

}