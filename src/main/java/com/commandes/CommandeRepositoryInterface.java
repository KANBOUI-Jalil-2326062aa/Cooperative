package com.commandes;

import java.util.List;

/**
 * Interface d'accès aux données pour la table "Commande".
 *
 * Cette interface définit les opérations CRUD pour manipuler les commandes stockées dans la base de données.
 */
public interface CommandeRepositoryInterface {

    /**
     * Ferme la connexion à la base de données, si nécessaire.
     */
    void close();

    /**
     * Récupère la liste de toutes les commandes.
     *
     * @return une liste d'objets {@link Commande}, qui peut être vide si aucune commande n'est enregistrée.
     */
    List<Commande> getAllCommandes();

    /**
     * Récupère une commande spécifique à partir de son identifiant.
     *
     * @param id l'identifiant de la commande (clé primaire).
     * @return l'objet {@link Commande} correspondant, ou {@code null} si aucune commande ne correspond à l'id.
     */
    Commande getCommande(String id);

    /**
     * Insère une nouvelle commande dans la table "Commande".
     *
     * @param commande l'objet {@link Commande} à insérer (doit contenir un id, idPanier et idUtilisateur).
     * @return {@code true} si l'insertion a réussi, {@code false} sinon.
     */
    boolean createCommande(Commande commande);

    /**
     * Met à jour une commande existante dans la table "Commande".
     *
     * @param commande l'objet {@link Commande} contenant l'id à modifier et les nouvelles valeurs.
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon.
     */
    boolean updateCommande(Commande commande);

    /**
     * Supprime une commande de la base de données à partir de son identifiant.
     *
     * @param id l'identifiant de la commande à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     */
    boolean deleteCommande(String id);
}
