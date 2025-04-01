package com.example.paniers;

import java.time.LocalDate;
import java.util.List;
import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 * Service métier pour manipuler les paniers et leurs produits associés.
 *
 * La table Panier comporte :
 * - id (identifiant unique)
 * - date_retrait (date de retrait du panier)
 * - lieu_relais (lieu de retrait)
 * - prix_total (prix total du panier)
 * - est_valide (statut du panier)
 * - date_maj (date de dernière mise à jour)
 *
 * La table ProduitPanier comporte :
 * - panier_id (clé étrangère vers Panier)
 * - produit_id (identifiant du produit)
 * - quantite (quantité du produit dans le panier)
 */
public class PanierService {


    /**
     * Constructeur par défaut.
     */
    public PanierService() {
    }

    /**
     * Accès aux données pour la table Panier et ProduitPanier (via un unique repository).
     */
    protected PanierRepositoryInterface panierRepo;

    /**
     * Constructeur pour injecter le repository.
     *
     * @param panierRepo objet implémentant PanierRepositoryInterface
     */
    public PanierService(PanierRepositoryInterface panierRepo) {
        this.panierRepo = panierRepo;
    }

    /**
     * Retourne la liste de tous les paniers.
     *
     * @return une liste de Panier
     */
    public List<Panier> getAllPaniers() {
        List<Panier> paniers = panierRepo.getAllPaniers();
        for (Panier p : paniers) {
            List<ProduitPanier> produits = panierRepo.getProduitsDuPanier(p.getIdPanier());
            p.setProduitPaniers(produits);
        }
        return paniers;
    }

    /**
     * OPTIONS /paniers/{path} - Gère les requêtes OPTIONS pour le CORS.
     * @return une réponse vide avec les en-têtes CORS appropriés.
     */
    @OPTIONS
    @Path("{path: .*}")
    public Response options() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .build();
    }


    /**
     * Récupère un panier par son ID, en chargeant également la liste des produits associés.
     *
     * @param idPanier identifiant du panier
     * @return un objet Panier, ou null s'il n'est pas trouvé
     */
    public Panier getPanierById(String idPanier) {
        Panier p = panierRepo.getPanier(idPanier);
        if (p != null) {
            List<ProduitPanier> produits = panierRepo.getProduitsDuPanier(idPanier);
            p.setProduitPaniers(produits);
        }
        return p;
    }

    /**
     * Crée un nouveau panier.
     * Si la date de dernière mise à jour (date_maj) n'est pas fixée, on la définit à la date actuelle.
     * Le prix total est initialisé à 0 si nécessaire.
     *
     * @param p objet Panier à créer
     * @return true si la création a réussi, false sinon
     */
    public boolean createPanier(Panier p) {
        if (p.getDateMaj() == null) {
            p.setDateMaj(LocalDate.now());
        }
        if (p.getPrixTotal() == 0) {
            p.setPrixTotal(0);
        }
        return panierRepo.createPanier(p);
    }

    /**
     * Met à jour un panier existant en actualisant la date de dernière mise à jour.
     *
     * @param p objet Panier modifié
     * @return true si la mise à jour a réussi, false sinon
     */
    public boolean updatePanier(Panier p) {
        p.setDateMaj(LocalDate.now());
        return panierRepo.updatePanier(p);
    }

    /**
     * Supprime un panier de la base de données.
     *
     * @param idPanier identifiant du panier à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    public boolean deletePanier(String idPanier) {
        return panierRepo.deletePanier(idPanier);
    }

    /* ------------------------------------------------
       Méthodes relatives aux produits dans le panier
       ------------------------------------------------ */

    /**
     * Ajoute un produit dans un panier avec la quantité spécifiée.
     * Met à jour la date de dernière mise à jour et recalcule le prix total.
     *
     * @param idPanier  identifiant du panier
     * @param idProduit identifiant du produit
     * @param quantite  quantité à ajouter
     * @return true si l'ajout a réussi, false sinon
     */
    public boolean addProduit(String idPanier, String idProduit, float quantite) {
        ProduitPanier pp = new ProduitPanier(idPanier, idProduit, quantite);
        boolean added = panierRepo.addProduitDansPanier(pp);
        if (added) {
            recalculerPrixTotal(idPanier);
            Panier p = panierRepo.getPanier(idPanier);
            if (p != null) {
                p.setDateMaj(LocalDate.now());
                panierRepo.updatePanier(p);
            }
        }
        return added;
    }

    /**
     * Met à jour la quantité d'un produit dans le panier.
     * Met à jour la date de dernière mise à jour et recalcule le prix total.
     *
     * @param idPanier         identifiant du panier
     * @param idProduit        identifiant du produit
     * @param nouvelleQuantite nouvelle quantité
     * @return true si la mise à jour a réussi, false sinon
     */
    public boolean updateProduit(String idPanier, String idProduit, float nouvelleQuantite) {
        ProduitPanier pp = new ProduitPanier(idPanier, idProduit, nouvelleQuantite);
        boolean updated = panierRepo.updateProduitDansPanier(pp);
        if (updated) {
            recalculerPrixTotal(idPanier);
            Panier p = panierRepo.getPanier(idPanier);
            if (p != null) {
                p.setDateMaj(LocalDate.now());
                panierRepo.updatePanier(p);
            }
        }
        return updated;
    }

    /**
     * Retire un produit du panier.
     * Met à jour la date de dernière mise à jour et recalcule le prix total.
     *
     * @param idPanier  identifiant du panier
     * @param idProduit identifiant du produit à retirer
     * @return true si la suppression a réussi, false sinon
     */
    public boolean removeProduit(String idPanier, String idProduit) {
        boolean removed = panierRepo.removeProduitDuPanier(idPanier, idProduit);
        if (removed) {
            recalculerPrixTotal(idPanier);
            Panier p = panierRepo.getPanier(idPanier);
            if (p != null) {
                p.setDateMaj(LocalDate.now());
                panierRepo.updatePanier(p);
            }
        }
        return removed;
    }

    /**
     * Recalcule le prix total du panier en fonction des produits qui y sont ajoutés.
     * Dans cet exemple, on suppose un prix unitaire fixe (1€ par unité) pour simplifier le calcul.
     * Vous pouvez adapter ce calcul pour obtenir le prix unitaire depuis une API externe ou un champ stocké.
     *
     * @param idPanier identifiant du panier à recalculer
     */
    private void recalculerPrixTotal(String idPanier) {
        List<ProduitPanier> produits = panierRepo.getProduitsDuPanier(idPanier);
        float total = 0f;
        for (ProduitPanier pp : produits) {
            float prixUnitaire = 1.0f;
            total += prixUnitaire * pp.getQuantite();
        }
        Panier p = panierRepo.getPanier(idPanier);
        if (p != null) {
            p.setPrixTotal(total);
            panierRepo.updatePanier(p);
        }
    }
}
