package com.example.produitsutilisateurs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.sql.SQLException;

/**
 * Classe de configuration principale de l'application JAX-RS.
 * Elle définit le chemin de base "/api" pour tous les endpoints et gère
 * l'injection de dépendances (CDI) pour les repositories de Produit et d'Utilisateur.
 */
@ApplicationPath("/api")
@ApplicationScoped
public class ProduitsUtilisateursApplication extends Application {

    private static final String URL = "jdbc:mysql://mysql-turlure.alwaysdata.net:3306/turlure_cooperative"
            + "?useSSL=false"
            + "&allowPublicKeyRetrieval=true"
            + "&serverTimezone=UTC";

    private static final String USER = "turlure_projet";
    private static final String PASSWORD = "projetcooperative";

    /**
     * Fabrique (produit) un repository de produits pour CDI.
     *
     * @return un objet implémentant {@link ProduitRepositoryInterface}
     */
    @Produces
    public ProduitRepositoryInterface createProduitRepo() {
        try {
            return new ProduitRepositoryMariadb(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Impossible de créer le repository Produit", e);
        }
    }

    /**
     * Ferme la connexion au repository de produits quand il n'est plus utilisé.
     *
     * @param repo le repository de produits
     */
    public void closeProduitRepo(@Disposes ProduitRepositoryInterface repo) {
        repo.close();
    }

    /**
     * Fabrique (produit) un repository d'utilisateurs pour CDI.
     *
     * @return un objet implémentant {@link UtilisateurRepositoryInterface}
     */
    @Produces
    public UtilisateurRepositoryInterface createUtilisateurRepo() {
        try {
            return new UtilisateurRepositoryMariadb(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Impossible de créer le repository Utilisateur", e);
        }
    }

    /**
     * Ferme la connexion au repository d'utilisateurs quand il n'est plus utilisé.
     *
     * @param repo le repository d'utilisateurs
     */
    public void closeUtilisateurRepo(@Disposes UtilisateurRepositoryInterface repo) {
        repo.close();
    }
}
