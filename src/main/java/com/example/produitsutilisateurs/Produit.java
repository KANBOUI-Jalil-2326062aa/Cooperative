package com.example.produitsutilisateurs;

public class Produit {

    /**
     * Nom du produit.
     */
    protected String nom;

    /**
     * Quantité en stock du produit.
     */
    protected int quantite;

    /**
     * Identifant du panier.(Clé Primaire)
     */
    protected String id;

    /**
     * Prix du produit.
     */
    protected float prix;


    /**
     * Constructeur par défaut.
     */
    public Produit() {
    }

    public Produit(int quantite, String nom, String id, float prix) {
        this.quantite = quantite;
        this.nom = nom;
        this.id = id;
        this.prix = prix;
    }


    // --- Getters et setters ---


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }


    @Override
    public String toString() {
        return "Produit{" +
                "nom='" + nom + '\'' +
                ", quantite=" + quantite +
                ", id='" + id + '\'' +
                ", prix=" + prix +
                '}';
    }
}
