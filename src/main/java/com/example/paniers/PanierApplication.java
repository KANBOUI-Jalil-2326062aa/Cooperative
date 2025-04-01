package com.example.paniers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.sql.SQLException;

/**
 * Classe de configuration de l'application JAX-RS
 * et de l'injection CDI.
 */
@ApplicationPath("/api")
@ApplicationScoped
public class PanierApplication extends Application {

    private static final String URL = "jdbc:mysql://mysql-turlure.alwaysdata.net:3306/turlure_cooperative"
            + "?useSSL=false"
            + "&allowPublicKeyRetrieval=true"
            + "&serverTimezone=UTC";

    private static final String USER = "turlure_projet";
    private static final String PASSWORD = "projetcooperative";


    /**
     * Produit un repository unique pour paniers et produits associés.
     */
    @Produces
    public PanierRepositoryInterface createPanierRepo() {
        try {
            return new PanierRepositoryMariadb(
                    URL, USER, PASSWORD
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
//    @Produces
//    public PanierRepositoryInterface createPanierRepo() {
//        try {
//            return new PanierRepositoryMariadb(
//                    "jdbc:mysql://mysql-turlure.alwaysdata.net:3306/turlure_cooperative",
//                    "turlure_projet",
//                    "projetcooperative"
//            );
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

    /**
     * Ferme la connexion au moment de la destruction.
     */
    public void closePanierRepo(@Disposes PanierRepositoryInterface repo) {
        repo.close();
    }
}