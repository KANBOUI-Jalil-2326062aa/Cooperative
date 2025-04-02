package com.commandes;

import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Service métier pour manipuler les commandes.
 *
 * Cette classe utilise un {@link CommandeRepositoryInterface} pour accéder aux données
 * stockées dans la table "Commande". Elle fournit des méthodes pour récupérer, créer,
 * mettre à jour et supprimer des commandes.
 */
public class CommandeService {

    /**
     * Constructeur par défaut.
     */
    public CommandeService() {
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
     * Repository utilisé pour manipuler les commandes dans la base de données.
     */
    private CommandeRepositoryInterface repo;

    /**
     * Constructeur permettant d'injecter l'interface d'accès aux données.
     *
     * @param repo un objet implémentant {@link CommandeRepositoryInterface}
     */
    public CommandeService(CommandeRepositoryInterface repo) {
        this.repo = repo;
    }

    /**
     * Récupère la liste de toutes les commandes.
     *
     * @return une liste d'objets {@link Commande} pouvant être vide si aucune commande n'est enregistrée.
     */
    public List<Commande> getAllCommandes() {
        return repo.getAllCommandes();
    }

    /**
     * Récupère une commande spécifique à partir de son identifiant.
     *
     * @param id l'identifiant de la commande.
     * @return l'objet {@link Commande} correspondant, ou {@code null} si aucune commande ne correspond.
     */
    public Commande getCommandeById(String id) {
        return repo.getCommande(id);
    }

    /**
     * Crée une nouvelle commande dans la base de données.
     *
     * @param commande l'objet {@link Commande} à créer (doit contenir un id, idPanier et idUtilisateur).
     * @return {@code true} si la création a réussi, {@code false} sinon.
     */
    public boolean createCommande(Commande commande) {
        return repo.createCommande(commande);
    }

    /**
     * Met à jour une commande existante dans la base de données.
     *
     * @param commande l'objet {@link Commande} contenant l'id à modifier et les nouvelles valeurs.
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon.
     */
    public boolean updateCommande(Commande commande) {
        return repo.updateCommande(commande);
    }

    /**
     * Supprime une commande de la base de données à partir de son identifiant.
     *
     * @param id l'identifiant de la commande à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     */
    public boolean deleteCommande(String id) {
        return repo.deleteCommande(id);
    }
}
