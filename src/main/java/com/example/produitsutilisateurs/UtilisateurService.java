package com.example.produitsutilisateurs;

import java.util.List;

/**
 * Service métier pour gérer la logique applicative liée aux utilisateurs.
 * 
 * Cette classe utilise un {@link UtilisateurRepositoryInterface} pour accéder
 * aux données stockées dans la table "Utilisateurs".
 */
public class UtilisateurService {

    /**
     * Le repository utilisé pour manipuler les utilisateurs en base de données.
     */
    private UtilisateurRepositoryInterface repo;

    /**
     * Constructeur permettant d'injecter l'interface d'accès aux données (Repository).
     *
     * @param repo un objet implémentant {@link UtilisateurRepositoryInterface}
     */
    public UtilisateurService(UtilisateurRepositoryInterface repo) {
        this.repo = repo;
    }

    /**
     * Récupère la liste de tous les utilisateurs dans la base de données.
     *
     * @return une liste d'objets {@link Utilisateur}
     */
    public List<Utilisateur> getAllUtilisateurs() {
        return repo.getAllUtilisateurs();
    }

    /**
     * Récupère un utilisateur grâce à son identifiant.
     *
     * @param id l'identifiant de l'utilisateur (int(11))
     * @return l'objet {@link Utilisateur} correspondant, ou {@code null} si aucun utilisateur ne correspond
     */
    public Utilisateur getUtilisateurById(int id) {
        return repo.getUtilisateur(id);
    }

    /**
     * Crée un nouvel utilisateur dans la base de données.
     *
     * @param u l'objet {@link Utilisateur} à insérer (doit contenir un id, nom, mdp)
     * @return {@code true} si la création a réussi, {@code false} sinon
     */
    public boolean createUtilisateur(Utilisateur u) {
        return repo.createUtilisateur(u);
    }

    /**
     * Met à jour un utilisateur existant.
     *
     * @param u l'objet {@link Utilisateur} contenant l'id de l'utilisateur à mettre à jour,
     *          ainsi que les nouvelles valeurs pour nom et mdp
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon
     */
    public boolean updateUtilisateur(Utilisateur u) {
        return repo.updateUtilisateur(u);
    }

    /**
     * Supprime un utilisateur à partir de son identifiant.
     *
     * @param id l'identifiant (int(11)) de l'utilisateur à supprimer
     * @return {@code true} si la suppression a réussi, {@code false} sinon
     */
    public boolean deleteUtilisateur(int id) {
        return repo.deleteUtilisateur(id);
    }
}
