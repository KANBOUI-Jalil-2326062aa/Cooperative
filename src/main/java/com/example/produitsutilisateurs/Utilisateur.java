package com.example.produitsutilisateurs;

/**
 * Entité représentant un utilisateur.
 */
public class Utilisateur {

    /**
     * Identifiant (clé primaire).
     */
    private String id;

    /**
     * Nom de l'utilisateur
     */
    private String nom;

    /**
     * Mot de passe
     */
    private String mdp;

    public Utilisateur() {
    }

    public Utilisateur(String id, String nom, String mdp) {
        this.id = id;
        this.nom = nom;
        this.mdp = mdp;
    }

    // -- Getters / Setters --

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }
}
