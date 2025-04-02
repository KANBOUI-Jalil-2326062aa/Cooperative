package com.example.produitsutilisateurs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Ressource REST pour gérer les Produits.
 *
 * URL d'accès : /api/produits
 * 
 * Opérations proposées :
 *
 * GET /produits         - Récupérer la liste de tous les produits
 * GET /produits/{id}    - Récupérer un produit par son identifiant
 * POST /produits        - Créer un nouveau produit
 * PUT /produits/{id}    - Mettre à jour un produit existant
 * DELETE /produits/{id} - Supprimer un produit
 *
 */
@Path("/produits")
@ApplicationScoped
public class ProduitResource {

    /**
     * Service métier pour manipuler les produits (accès BD, règles métier).
     */
    private ProduitService service;

    /**
     * Constructeur par défaut (pour CDI).
     */
    public ProduitResource() {
    }

    /**
     * Constructeur avec injection du repository (via CDI),
     * et création du service associé.
     *
     * @param repo un objet qui implémente {@link ProduitRepositoryInterface}
     */
    @Inject
    public ProduitResource(ProduitRepositoryInterface repo) {
        this.service = new ProduitService(repo);
    }

    /**
     * GET /produits
     * Récupère la liste de tous les produits en base de données.
     *
     * @return une liste de {@link Produit}, au format JSON
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produit> getAllProduits() {
        return service.getAllProduits();
    }

    /**
     * GET /produits/{id}
     * Récupère un produit spécifique via son identifiant (varchar(50)).
     *
     * @param id l'identifiant du produit recherché
     * @return l'objet {@link Produit} correspondant, ou une erreur 404 s'il n'existe pas
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produit getProduit(@PathParam("id") String id) {
        Produit p = service.getProduitById(id);
        if (p == null) {
            throw new NotFoundException("Le produit " + id + " est introuvable");
        }
        return p;
    }

    /**
     * POST /produits
     * Crée un nouveau produit dans la base de données.
     *
     * @param p l'objet {@link Produit} envoyé au format JSON
     * @return une réponse HTTP :
     *         <ul>
     *           <li>201 (Created) si la création a réussi</li>
     *           <li>400 (Bad Request) sinon</li>
     *         </ul>
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduit(Produit p) {
        boolean created = service.createProduit(p);
        if (created) {
            return Response.status(Response.Status.CREATED)
                    .entity("Produit créé avec succès").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Échec de la création du produit").build();
        }
    }

    /**
     * PUT /produits/{id}
     * Met à jour un produit existant.
     *
     * @param id l'identifiant du produit à mettre à jour
     * @param p  l'objet {@link Produit} contenant les nouvelles valeurs (nom, quantite, prix)
     * @return une réponse HTTP :
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduit(@PathParam("id") String id, Produit p) {
        p.setId(id);
        boolean ok = service.updateProduit(p);
        if (!ok) {
            throw new NotFoundException("Impossible de mettre à jour le produit " + id);
        }
        return Response.ok("Produit mis à jour").build();
    }

    /**
     * DELETE /produits/{id}
     * Supprime un produit dans la base de données.
     *
     * @param id l'identifiant du produit à supprimer
     * @return une réponse HTTP :
     */
    @DELETE
    @Path("{id}")
    public Response deleteProduit(@PathParam("id") String id) {
        boolean ok = service.deleteProduit(id);
        if (!ok) {
            throw new NotFoundException("Impossible de supprimer le produit " + id);
        }
        return Response.ok("Produit supprimé").build();
    }
}
