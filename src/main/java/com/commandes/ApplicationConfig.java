package com.commandes;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe de configuration de l'application JAX-RS
 * et de l'injection CDI.
 *
 * Cette classe définit le chemin d'accès de l'API
 * et enregistre les classes de ressources.
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application {
    /**
     * Constructeur par défaut.
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(CorsFilter.class);
        return classes;
    }
}