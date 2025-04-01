package com.example.paniers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un panier au sein de la coopérative.
 * La table Panier contient les attributs suivants :
 * - id (identifiant unique)
 * - date_retrait (date de retrait du panier)
 * - lieu_relais (lieu de retrait du panier)
 * - prix_total (prix total du panier)
 * - est_valide (statut du panier)
 * - date_maj (date de dernière mise à jour)
 *
 * En plus, l'entité comporte une liste des produits associés.
 */
public class Panier {

    /**
     * Identifiant unique du panier (clé primaire dans la table Panier).
     */
    protected String idPanier;

    /**
     * Date de retrait du panier.
     */
    protected LocalDate dateRetrait;

    /**
     * Lieu de relais pour le retrait du panier.
     */
    protected String lieuRelais;

    /**
     * Prix total du panier.
     */
    protected float prixTotal;

    /**
     * Indique si le panier est validé.
     */
    protected boolean estValide;

    /**
     * Date de dernière mise à jour du panier.
     */
    protected LocalDate dateMaj;

    /**
     * Liste des produits associés au panier.
     */
    protected List<ProduitPanier> produitPaniers;

    /**
     * Constructeur par défaut.
     */
    public Panier() {
        this.produitPaniers = new ArrayList<>();
    }

    /**
     * Constructeur avec paramètres (hors liste de produits).
     *
     * @param idPanier    identifiant du panier
     * @param dateRetrait date de retrait du panier
     * @param lieuRelais  lieu de relais pour le retrait
     * @param prixTotal   prix total du panier
     * @param estValide   statut du panier (validé ou non)
     * @param dateMaj     date de dernière mise à jour
     */
    public Panier(String idPanier, LocalDate dateRetrait, String lieuRelais,
                  float prixTotal, boolean estValide, LocalDate dateMaj) {
        this.idPanier = idPanier;
        this.dateRetrait = dateRetrait;
        this.lieuRelais = lieuRelais;
        this.prixTotal = prixTotal;
        this.estValide = estValide;
        this.dateMaj = dateMaj;
        this.produitPaniers = new ArrayList<>();
    }

    // --- Getters et setters ---

    public String getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(String idPanier) {
        this.idPanier = idPanier;
    }

    public LocalDate getDateRetrait() {
        return dateRetrait;
    }

    public void setDateRetrait(LocalDate dateRetrait) {
        this.dateRetrait = dateRetrait;
    }

    public String getLieuRelais() {
        return lieuRelais;
    }

    public void setLieuRelais(String lieuRelais) {
        this.lieuRelais = lieuRelais;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public boolean isEstValide() {
        return estValide;
    }

    public void setEstValide(boolean estValide) {
        this.estValide = estValide;
    }

    public LocalDate getDateMaj() {
        return dateMaj;
    }

    public void setDateMaj(LocalDate dateMaj) {
        this.dateMaj = dateMaj;
    }

    public List<ProduitPanier> getProduitPaniers() {
        return produitPaniers;
    }

    public void setProduitPaniers(List<ProduitPanier> produitPaniers) {
        this.produitPaniers = produitPaniers;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "idPanier=" + idPanier +
                ", dateRetrait=" + dateRetrait +
                ", lieuRelais='" + lieuRelais + '\'' +
                ", prixTotal=" + prixTotal +
                ", estValide=" + estValide +
                ", dateMaj=" + dateMaj +
                ", produitsDansPanier=" + produitPaniers +
                '}';
    }
}
