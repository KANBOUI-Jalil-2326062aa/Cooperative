package com.example.paniers;

import com.example.paniers.Panier;
import com.example.paniers.ProduitPanier;

import java.util.List;

/**
 * Interface d'accès aux données pour les Paniers
 * ET pour les produits associés (ProduitPanier).
 */
public interface PanierRepositoryInterface {

    /**
     * Ferme la connexion à la base de données, si besoin
     */
    void close();

    /**
     * Récupère la liste de tous les paniers
     */
    List<Panier> getAllPaniers();

    /**
     * Récupère un panier spécifique (par son id)
     */
    Panier getPanier(String idPanier);

    /**
     * Crée un nouveau panier
     */
    boolean createPanier(Panier panier);

    /**
     * Met à jour un panier existant
     */
    boolean updatePanier(Panier panier);

    /**
     * Supprime un panier
     */
    boolean deletePanier(String idPanier);

    /**
     * Récupère la liste des produits d'un panier
     */
    List<ProduitPanier> getProduitsDuPanier(String idPanier);

    /**
     * Ajoute un produit dans le panier (avec quantité, etc.)
     */
    boolean addProduitDansPanier(ProduitPanier produitPanier);

    /**
     * Met à jour un produit déjà associé à un panier
     * (par ex. modifier la quantité)
     */
    boolean updateProduitDansPanier(ProduitPanier produitPanier);

    /**
     * Supprime un produit d'un panier
     */
    boolean removeProduitDuPanier(String idPanier, String idProduit);
}
