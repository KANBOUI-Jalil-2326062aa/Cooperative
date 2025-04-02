package com.example.produitsutilisateurs;

import java.util.List;

/**
 * Interface d'accès aux données pour la table "Produit".
 */
public interface ProduitRepositoryInterface {

    /**
     * Ferme la connexion à la base de données (si nécessaire).
     */
    void close();

    /**
     * Récupère la liste de tous les produits présents dans la base.
     *
     * @return une liste d'objets {@link Produit}, qui peut être vide si aucun produit n'est enregistré.
     */
    List<Produit> getAllProduits();

    /**
     * Récupère un produit spécifique à partir de son identifiant.
     *
     * @param id l'identifiant du produit (varchar(50)).
     * @return l'objet {@link Produit} correspondant, ou {@code null} si aucun produit ne correspond à l'id.
     */
    Produit getProduit(String id);

    /**
     * Insère un nouveau produit dans la table "Produit".
     *
     * @param produit l'objet {@link Produit} à insérer (doit contenir un id, nom, quantite, prix).
     * @return {@code true} si l'insertion a réussi, {@code false} sinon.
     */
    boolean createProduit(Produit produit);

    /**
     * Met à jour un produit existant dans la table "Produit".
     *
     * @param produit l'objet {@link Produit} contenant l'id à modifier et les nouvelles valeurs (nom, quantite, prix).
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon (par exemple si l'id n'existe pas).
     */
    boolean updateProduit(Produit produit);

    /**
     * Supprime un produit de la base de données à partir de son identifiant.
     *
     * @param id l'identifiant du produit à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     */
    boolean deleteProduit(String id);
}
