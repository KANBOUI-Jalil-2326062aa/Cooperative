package com.example.produitsutilisateurs;

import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Service métier pour gérer la logique applicative liée aux produits.
 * 
 * Cette classe utilise un {@link ProduitRepositoryInterface} pour accéder
 * aux données stockées dans la table "Produit".
 */
public class ProduitService {

    /**
     * Constructeur par défaut.
     */
    public ProduitService() {}

    /**
     * Le repository utilisé pour manipuler les produits en base de données.
     */
    private ProduitRepositoryInterface repo;

    /**
     * Constructeur permettant d'injecter l'interface d'accès aux données (Repository).
     *
     * @param repo un objet implémentant {@link ProduitRepositoryInterface}
     */
    public ProduitService(ProduitRepositoryInterface repo) {
        this.repo = repo;
    }

    /**
     * Récupère la liste de tous les produits disponibles en base de données.
     *
     * @return une liste d'objets {@link Produit}
     */
    public List<Produit> getAllProduits() {
        return repo.getAllProduits();
    }

    /**
     * Récupère un produit grâce à son identifiant.
     *
     * @param id l'identifiant du produit (varchar(50))
     * @return l'objet {@link Produit} correspondant, ou {@code null} si aucun produit ne correspond
     */
    public Produit getProduitById(String id) {
        return repo.getProduit(id);
    }

    /**
     * Crée un nouveau produit dans la base de données.
     *
     * @param p l'objet {@link Produit} à insérer (doit contenir un id, nom, quantite, prix)
     * @return {@code true} si la création a réussi, {@code false} sinon
     */
    public boolean createProduit(Produit p) {
        return repo.createProduit(p);
    }

    /**
     * Met à jour un produit existant.
     *
     * @param p l'objet {@link Produit} contenant l'id du produit à mettre à jour,
     *          ainsi que les nouvelles valeurs pour nom, quantite, prix
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon
     */
    public boolean updateProduit(Produit p) {
        return repo.updateProduit(p);
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
     * Supprime un produit à partir de son identifiant.
     *
     * @param id l'identifiant (varchar(50)) du produit à supprimer
     * @return {@code true} si la suppression a réussi, {@code false} sinon
     */
    public boolean deleteProduit(String id) {
        return repo.deleteProduit(id);
    }
}
