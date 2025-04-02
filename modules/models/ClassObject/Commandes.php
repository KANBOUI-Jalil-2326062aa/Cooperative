<?php
namespace modules\models\ClassObject;

/**
 * Class Commande
 *
 * Représente une commande.
 */
class Commande {
    private $id;
    private $idPanier;
    private $idUtilisateur;

    /**
     * Commande constructor.
     *
     * @param int $id
     * @param int $idPanier
     * @param int $idUtilisateur
     */
    public function __construct($id, $idPanier, $idUtilisateur) {
        $this->id = $id;
        $this->idPanier = $idPanier;
        $this->idUtilisateur = $idUtilisateur;
    }

    /**
     * Obtient l'ID de la commande.
     *
     * @return int
     */
    public function getId() {
        return $this->id;
    }

    /**
     * Obtient l'ID du panier.
     *
     * @return int
     */
    public function getIdPanier() {
        return $this->idPanier;
    }

    /**
     * Obtient l'ID de l'utilisateur.
     *
     * @return int
     */
    public function getIdUtilisateur() {
        return $this->idUtilisateur;
    }

    /**
     * Définit l'ID de la commande.
     *
     * @param int $id
     * @return void
     */
    public function setId($id) {
        $this->id = $id;
    }

    /**
     * Définit l'ID du panier.
     *
     * @param int $idPanier
     * @return void
     */
    public function setIdPanier($idPanier) {
        $this->idPanier = $idPanier;
    }

    /**
     * Définit l'ID de l'utilisateur.
     *
     * @param int $idUtilisateur
     * @return void
     */
    public function setIdUtilisateur($idUtilisateur) {
        $this->idUtilisateur = $idUtilisateur;
    }
}