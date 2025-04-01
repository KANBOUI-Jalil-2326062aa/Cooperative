<?php

namespace modules\models\ClassObject;


class Commandes {

    private $id;

    private $panierId;

    private $lieuRelai;

    private $dateRetrait;

    private $prixTotal;

    public function __construct($id, $panierId, $lieuRelai, $dateRetrait, $prixTotal) {
        $this->id = $id;
        $this->panierId = $panierId;
        $this->lieuRelai = $lieuRelai;
        $this->dateRetrait = $dateRetrait;
        $this->prixTotal = $prixTotal;
    }

    public function getId() {
        return $this->id;
    }

    public function getPanierId() {
        return $this->panierId;
    }

    public function getLieuRelai() {
        return $this->lieuRelai;
    }

    public function getDateRetrait() {
        return $this->dateRetrait;
    }

    public function getPrixTotal() {
        return $this->prixTotal;
    }

    public function setId($id) {
        $this->id = $id;
    }

    public function setPanierId($panierId) {
        $this->panierId = $panierId;
    }

    public function setLieuRelai($lieuRelai) {
        $this->lieuRelai = $lieuRelai;
    }

    public function setDateRetrait($dateRetrait) {
        $this->dateRetrait = $dateRetrait;
    }

    public function setPrixTotal($prixTotal) {
        $this->prixTotal = $prixTotal;
    }


}