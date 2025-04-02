package com.commandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Ressource REST pour gérer les commandes.
 *
 * Cette classe expose les endpoints pour effectuer les opérations CRUD sur la table "Commande".
 *
 * Endpoints exposés :
 * - GET /commandes           : Récupérer la liste de toutes les commandes.
 * - GET /commandes/{id}      : Récupérer une commande spécifique par son identifiant.
 * - POST /commandes          : Créer une nouvelle commande.
 * - PUT /commandes/{id}      : Mettre à jour une commande existante.
 * - DELETE /commandes/{id}   : Supprimer une commande.
 */
@Path("/commandes")
@ApplicationScoped
public class CommandeResource {

    /**
     * Service métier pour manipuler les commandes.
     */
    private CommandeService service;

    /**
     * Constructeur par défaut (pour CDI).
     */
    public CommandeResource() {
    }

    /**
     * Constructeur avec injection du repository via CDI,
     * et initialisation du service Commande.
     *
     * @param repo un objet implémentant {@link CommandeRepositoryInterface}
     */
    @Inject
    public CommandeResource(CommandeRepositoryInterface repo) {
        this.service = new CommandeService(repo);
    }

    /**
     * GET /commandes
     * Récupère la liste de toutes les commandes.
     *
     * @return une liste d'objets {@link Commande} au format JSON.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Commande> getAllCommandes() {
        return service.getAllCommandes();
    }

    /**
     * GET /commandes/{id}
     * Récupère une commande spécifique par son identifiant.
     *
     * @param id l'identifiant de la commande.
     * @return l'objet {@link Commande} correspondant, ou une erreur 404 si introuvable.
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Commande getCommande(@PathParam("id") String id) {
        Commande commande = service.getCommandeById(id);
        if (commande == null) {
            throw new NotFoundException("La commande " + id + " est introuvable");
        }
        return commande;
    }

    /**
     * POST /commandes
     * Crée une nouvelle commande dans la base de données.
     *
     * @param commande l'objet {@link Commande} envoyé au format JSON.
     * @return une réponse HTTP 201 (Created) si la création a réussi, ou 400 (Bad Request) sinon.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCommande(Commande commande) {
        boolean created = service.createCommande(commande);
        if (created) {
            return Response.status(Response.Status.CREATED)
                           .entity("Commande créée avec succès")
                           .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Échec de la création de la commande")
                           .build();
        }
    }

    /**
     * PUT /commandes/{id}
     * Met à jour une commande existante dans la base de données.
     *
     * @param id l'identifiant de la commande à mettre à jour.
     * @param commandeMaj l'objet {@link Commande} contenant les modifications.
     * @return une réponse HTTP 200 (OK) si la mise à jour a réussi, ou une erreur 404 si la commande est introuvable.
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCommande(@PathParam("id") String id, Commande commandeMaj) {
        // Forcer l'identifiant pour être sûr de mettre à jour la bonne commande.
        commandeMaj.setId(id);
        boolean updated = service.updateCommande(commandeMaj);
        if (!updated) {
            throw new NotFoundException("Impossible de mettre à jour la commande " + id);
        }
        return Response.ok("Commande mise à jour").build();
    }

    /**
     * DELETE /commandes/{id}
     * Supprime une commande de la base de données.
     *
     * @param id l'identifiant de la commande à supprimer.
     * @return une réponse HTTP 200 (OK) si la suppression a réussi, ou une erreur 404 si elle échoue.
     */
    @DELETE
    @Path("{id}")
    public Response deleteCommande(@PathParam("id") String id) {
        boolean deleted = service.deleteCommande(id);
        if (!deleted) {
            throw new NotFoundException("Impossible de supprimer la commande " + id);
        }
        return Response.ok("Commande supprimée").build();
    }
}
