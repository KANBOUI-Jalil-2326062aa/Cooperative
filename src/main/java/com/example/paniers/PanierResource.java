package com.example.paniers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Ressource REST associée aux paniers.
 *
 * Cette classe expose les endpoints pour gérer les paniers ainsi que
 * les produits associés contenus dans chaque panier.
 */
@Path("/paniers")
@ApplicationScoped
public class PanierResource {

    /**
     * Service métier pour manipuler les paniers et leurs produits associés.
     */
    private PanierService service;

    /**
     * Constructeur vide pour CDI.
     */
    public PanierResource() {}

    /**
     * Constructeur avec injection du repository unique,
     * et création du service.
     *
     * @param panierRepo objet implémentant PanierRepositoryInterface
     */
    @Inject
    public PanierResource(PanierRepositoryInterface panierRepo) {
        this.service = new PanierService(panierRepo);
    }

    /**
     * GET /paniers - Liste tous les paniers.
     *
     * @return une liste de paniers au format JSON.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Panier> getAllPaniers() {
        return service.getAllPaniers();
    }

    /**
     * GET /paniers/{id} - Récupère un panier précis.
     *
     * @param idPanier identifiant du panier.
     * @return le panier correspondant au format JSON, ou une erreur 404 si non trouvé.
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Panier getPanier(@PathParam("id") String idPanier) {
        Panier p = service.getPanierById(idPanier);
        if (p == null) {
            throw new NotFoundException("Le panier " + idPanier + " n'existe pas");
        }
        return p;
    }

    /**
     * POST /paniers - Crée un nouveau panier.
     *
     * @param newPanier objet Panier reçu en JSON.
     * @return une réponse HTTP 201 (CREATED) si le panier a été créé, sinon une erreur.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPanier(Panier newPanier) {
        boolean created = service.createPanier(newPanier);
        if (created) {
            return Response.status(Response.Status.CREATED).entity("Panier créé").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * PUT /paniers/{id} - Met à jour un panier existant.
     *
     * @param idPanier identifiant du panier à mettre à jour.
     * @param panierMaj objet Panier contenant les modifications.
     * @return une réponse HTTP 200 (OK) si la mise à jour a réussi, sinon une erreur.
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePanier(@PathParam("id") String idPanier, Panier panierMaj) {
        panierMaj.setIdPanier(idPanier);
        boolean ok = service.updatePanier(panierMaj);
        if (!ok) {
            throw new NotFoundException("Impossible de mettre à jour le panier " + idPanier);
        }
        return Response.ok("Panier mis à jour").build();
    }

    /**
     * DELETE /paniers/{id} - Supprime un panier.
     *
     * @param idPanier identifiant du panier à supprimer.
     * @return une réponse HTTP 200 (OK) si la suppression a réussi, sinon une erreur.
     */
    @DELETE
    @Path("{id}")
    public Response deletePanier(@PathParam("id") String idPanier) {
        boolean ok = service.deletePanier(idPanier);
        if (!ok) {
            throw new NotFoundException("Impossible de supprimer le panier " + idPanier);
        }
        return Response.ok("Panier supprimé").build();
    }

    /* ---------------------------------------------------------
       Endpoints relatifs aux produits dans un panier
       --------------------------------------------------------- */

    /**
     * POST /paniers/{idPanier}/produits
     * Ajoute un produit dans le panier.
     *
     * Le JSON envoyé doit contenir les champs : produit_id et quantite.
     * L'id du panier est forcé par le path pour garantir la cohérence.
     *
     * @param idPanier identifiant du panier.
     * @param produitData objet ProduitPanier reçu en JSON.
     * @return une réponse HTTP indiquant le succès ou l'échec de l'opération.
     */
    @POST
    @Path("{idPanier}/produits")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduit(@PathParam("idPanier") String idPanier, ProduitPanier produitData) {
        produitData.setPanier_id(idPanier);

        boolean added = service.addProduit(produitData.getPanier_id(),
                produitData.getProduit_id(),
                produitData.getQuantite());
        if (!added) {
            throw new NotFoundException("Impossible d'ajouter le produit");
        }
        return Response.ok("Produit ajouté").build();
    }

    /**
     * PUT /paniers/{idPanier}/produits/{idProduit}
     * Modifie la quantité d'un produit dans le panier.
     *
     * Le JSON envoyé doit contenir le champ quantite.
     * L'id du panier et du produit sont forcés par le path.
     *
     * @param idPanier identifiant du panier.
     * @param idProduit identifiant du produit.
     * @param produitData objet ProduitPanier reçu en JSON.
     * @return une réponse HTTP indiquant le succès ou l'échec de l'opération.
     */
    @PUT
    @Path("{idPanier}/produits/{idProduit}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduit(@PathParam("idPanier") String idPanier,
                                  @PathParam("idProduit") String idProduit,
                                  ProduitPanier produitData) {
        produitData.setPanier_id(idPanier);
        produitData.setProduit_id(idProduit);

        boolean ok = service.updateProduit(idPanier, idProduit, produitData.getQuantite());
        if (!ok) {
            throw new NotFoundException("Impossible de mettre à jour le produit dans le panier");
        }
        return Response.ok("Produit mis à jour").build();
    }

    /**
     * DELETE /paniers/{idPanier}/produits/{idProduit}
     * Retire un produit du panier.
     *
     * @param idPanier identifiant du panier.
     * @param idProduit identifiant du produit à retirer.
     * @return une réponse HTTP indiquant le succès ou l'échec de l'opération.
     */
    @DELETE
    @Path("{idPanier}/produits/{idProduit}")
    public Response removeProduit(@PathParam("idPanier") String idPanier,
                                  @PathParam("idProduit") String idProduit) {
        boolean ok = service.removeProduit(idPanier, idProduit);
        if (!ok) {
            throw new NotFoundException("Impossible de retirer ce produit du panier");
        }
        return Response.ok("Produit retiré du panier").build();
    }
}
