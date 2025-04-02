package com.example.produitsutilisateurs;

import java.util.List;

/**
 * Interface d'accès aux données pour la table "Utilisateurs".
 */
public interface UtilisateurRepositoryInterface {

    /**
     * Ferme la connexion à la base de données (si nécessaire).
     */
    void close();

    /**
     * Récupère la liste de tous les utilisateurs enregistrés dans la base.
     *
     * @return une liste d'objets {@link Utilisateur}, qui peut être vide si aucun utilisateur n'est présent.
     */
    List<Utilisateur> getAllUtilisateurs();

    /**
     * Récupère un utilisateur spécifique à partir de son identifiant unique.
     *
     * @param id l'identifiant de l'utilisateur (int(11)).
     * @return l'objet {@link Utilisateur} correspondant, ou {@code null} si aucun utilisateur ne correspond à cet id.
     */
    Utilisateur getUtilisateur(int id);

    /**
     * Insère un nouvel utilisateur dans la table "Utilisateurs".
     *
     * @param utilisateur l'objet {@link Utilisateur} à insérer (doit contenir un id, nom, mdp).
     * @return {@code true} si l'insertion a réussi, {@code false} sinon.
     */
    boolean createUtilisateur(Utilisateur utilisateur);

    /**
     * Met à jour un utilisateur existant dans la table "Utilisateurs".
     *
     * @param utilisateur l'objet {@link Utilisateur} contenant l'id à modifier et les nouvelles valeurs (nom, mdp).
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon (par exemple si l'id n'existe pas).
     */
    boolean updateUtilisateur(Utilisateur utilisateur);

    /**
     * Supprime un utilisateur de la base de données à partir de son identifiant.
     *
     * @param id l'identifiant de l'utilisateur à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     */
    boolean deleteUtilisateur(int id);
}
