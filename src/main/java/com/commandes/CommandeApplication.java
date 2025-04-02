package com.commandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.sql.SQLException;

/**
 * Classe de configuration de l'application JAX-RS pour l'API Commande.
 * 
 * Elle définit le chemin global "/api" pour tous les endpoints et gère l'injection
 * de dépendances (CDI) pour le repository de Commande.
 */
@ApplicationPath("/api")
@ApplicationScoped
public class CommandeApplication extends Application {

    // Informations de connexion à la base de données.
    private static final String URL = "jdbc:mysql://mysql-turlure.alwaysdata.net:3306/turlure_cooperative"
            + "?useSSL=false"
            + "&allowPublicKeyRetrieval=true"
            + "&serverTimezone=UTC";
    private static final String USER = "turlure_projet";
    private static final String PASSWORD = "projetcooperative";

    /**
     * Produit un repository de commandes pour CDI.
     *
     * @return une instance de {@link CommandeRepositoryInterface} implémentée par {@link CommandeRepositoryMariadb}
     */
    @Produces
    public CommandeRepositoryInterface createCommandeRepo() {
        try {
            return new CommandeRepositoryMariadb(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Impossible de créer le repository Commande", e);
        }
    }

    /**
     * Ferme la connexion au repository de commandes quand il n'est plus utilisé.
     *
     * @param repo le repository de commandes à fermer.
     */
    public void closeCommandeRepo(@Disposes CommandeRepositoryInterface repo) {
        repo.close();
    }
}
