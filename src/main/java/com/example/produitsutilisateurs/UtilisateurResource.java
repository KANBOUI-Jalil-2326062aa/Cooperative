package com.example.produitsutilisateurs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Ressource REST pour gérer les Utilisateurs.
 * <p>
 * URL d'accès : /api/utilisateurs
 * </p>
 * 
 * Opérations proposées :
 *
 * GET /utilisateurs         - Récupérer la liste de tous les utilisateurs
 * GET /utilisateurs/{id}    - Récupérer un utilisateur par son identifiant
 * POST /utilisateurs        - Créer un nouvel utilisateur
 * PUT /utilisateurs/{id}    - Mettre à jour un utilisateur existant
 * DELETE /utilisateurs/{id} - Supprimer un utilisateur
 */
@Path("/utilisateurs")
@ApplicationScoped
public class UtilisateurResource {

    /**
     * Service métier pour manipuler les utilisateurs.
     */
    private UtilisateurService service;

    /**
     * Constructeur par défaut (pour CDI).
     */
    public UtilisateurResource() {
    }

    /**
     * Constructeur avec injection du repository (via CDI),
     * et création du service associé.
     *
     * @param repo un objet qui implémente {@link UtilisateurRepositoryInterface}
     */
    @Inject
    public UtilisateurResource(UtilisateurRepositoryInterface repo) {
        this.service = new UtilisateurService(repo);
    }

    /**
     * GET /utilisateurs
     * Récupère la liste de tous les utilisateurs en base de données.
     *
     * @return une liste de {@link Utilisateur}, au format JSON
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> getAllUtilisateurs() {
        return service.getAllUtilisateurs();
    }

    /**
     * GET /utilisateurs/{id}
     * Récupère un utilisateur spécifique via son identifiant (int(11)).
     *
     * @param id l'identifiant de l'utilisateur recherché
     * @return l'objet {@link Utilisateur} correspondant, ou une erreur 404 s'il n'existe pas
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur getUtilisateur(@PathParam("id") int id) {
        Utilisateur u = service.getUtilisateurById(id);
        if (u == null) {
            throw new NotFoundException("L'utilisateur " + id + " est introuvable");
        }
        return u;
    }

    /**
     * POST /utilisateurs
     * Crée un nouvel utilisateur dans la base de données.
     *
     * @param u l'objet {@link Utilisateur} envoyé au format JSON
     * @return une réponse HTTP :
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUtilisateur(Utilisateur u) {
        boolean created = service.createUtilisateur(u);
        if (created) {
            return Response.status(Response.Status.CREATED)
                    .entity("Utilisateur créé avec succès").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Échec de la création de l'utilisateur").build();
        }
    }

    /**
     * PUT /utilisateurs/{id}
     * Met à jour un utilisateur existant.
     *
     * @param id l'identifiant de l'utilisateur à mettre à jour
     * @param u  l'objet {@link Utilisateur} contenant les nouvelles valeurs (nom, mdp)
     * @return une réponse HTTP :
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUtilisateur(@PathParam("id") String id, Utilisateur u) {
        u.setId(id);
        boolean ok = service.updateUtilisateur(u);
        if (!ok) {
            throw new NotFoundException("Impossible de mettre à jour l'utilisateur " + id);
        }
        return Response.ok("Utilisateur mis à jour").build();
    }

    /**
     * DELETE /utilisateurs/{id}
     * Supprime un utilisateur dans la base de données.
     *
     * @param id l'identifiant de l'utilisateur à supprimer
     * @return une réponse HTTP :
     */
    @DELETE
    @Path("{id}")
    public Response deleteUtilisateur(@PathParam("id") int id) {
        boolean ok = service.deleteUtilisateur(id);
        if (!ok) {
            throw new NotFoundException("Impossible de supprimer l'utilisateur " + id);
        }
        return Response.ok("Utilisateur supprimé").build();
    }
}
