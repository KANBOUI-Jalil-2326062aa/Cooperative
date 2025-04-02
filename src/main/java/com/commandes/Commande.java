package com.commandes;

public class Commande {

    /**
     * Identifant de la commande.(Clé Primaire)
     */
    protected String id;

    /**
     * Identifant du panier.
     */
    protected String idPanier;

    /**
     * Identifant de l'utilisateur.
     */
    protected String idUtilisateur;


    /**
     * Constructeur par défaut.
     */
    public Commande() {
    }

    /**
     *
     * @param id
     * @param idPanier
     * @param idUtilisateur
     */
    public Commande(String id, String idPanier, String idUtilisateur) {
        this.id = id;
        this.idPanier = idPanier;
        this.idUtilisateur = idUtilisateur;
    }

    // --- Getters et setters ---


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(String idPanier) {
        this.idPanier = idPanier;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id='" + id + '\'' +
                ", idPanier='" + idPanier + '\'' +
                ", idUtilisateur='" + idUtilisateur + '\'' +
                '}';
    }
}
