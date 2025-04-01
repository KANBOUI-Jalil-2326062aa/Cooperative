package com.example.paniers;

/**
 * Classe représentant l'association entre un panier et un produit.
 * La table ProduitPanier contient les attributs suivants :
 * - panier_id : identifiant du panier
 * - produit_id : identifiant du produit
 * - quantite  : quantité du produit dans le panier
 */
public class ProduitPanier {

    /**
     * Identifiant du panier (clé étrangère vers Panier).
     */
    protected String panier_id;

    /**
     * Identifiant du produit.
     */
    protected String produit_id;

    /**
     * Quantité du produit dans le panier.
     */
    protected float quantite;

    /**
     * Constructeur par défaut.
     */
    public ProduitPanier() {
    }

    /**
     * Constructeur avec paramètres.
     *
     * @param panier_id  identifiant du panier
     * @param produit_id identifiant du produit
     * @param quantite   quantité du produit dans le panier
     */
    public ProduitPanier(String panier_id, String produit_id, float quantite) {
        this.panier_id = panier_id;
        this.produit_id = produit_id;
        this.quantite = quantite;
    }

    // --- Getters et setters ---

    public String getPanier_id() {
        return panier_id;
    }

    public void setPanier_id(String panier_id) {
        this.panier_id = panier_id;
    }

    public String getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(String produit_id) {
        this.produit_id = produit_id;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "ProduitPanier{" +
                "panier_id=" + panier_id +
                ", produit_id=" + produit_id +
                ", quantite=" + quantite +
                '}';
    }
}
