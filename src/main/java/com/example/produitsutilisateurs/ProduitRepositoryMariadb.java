package com.example.produitsutilisateurs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de {@link ProduitRepositoryInterface} pour un stockage dans une base de données
 * MariaDB. Les informations sur les produits sont stockées dans la table "Produit".
 */
public class ProduitRepositoryMariadb implements ProduitRepositoryInterface {

    /**
     * Connexion à la base de données MariaDB.
     */
    private Connection dbConnection;

    /**
     * Constructeur de la classe qui initialise la connexion JDBC.
     *
     * @param dbUrl  l'URL de la base de données (ex : "jdbc:mariadb://...").
     * @param dbUser l'identifiant utilisateur (login).
     * @param dbPwd  le mot de passe associé.
     * @throws SQLException           si un problème SQL se produit.
     * @throws ClassNotFoundException si le driver JDBC n'est pas trouvé.
     */
    public ProduitRepositoryMariadb(String dbUrl, String dbUser, String dbPwd)
            throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        this.dbConnection = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
    }

    /**
     * Ferme la connexion à la base de données, si elle est ouverte.
     */
    @Override
    public void close() {
        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupère la liste de tous les produits présents dans la table "Produit".
     *
     * @return une liste d'objets {@link Produit}.
     *         Peut être vide s'il n'y a aucun enregistrement dans la base.
     */
    @Override
    public List<Produit> getAllProduits() {
        List<Produit> results = new ArrayList<>();
        String query = "SELECT * FROM Produits";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produit p = mapRowToProduit(rs);
                results.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    /**
     * Récupère un produit spécifique à partir de son identifiant.
     *
     * @param id l'identifiant du produit (varchar(50), clé primaire).
     * @return l'objet {@link Produit} correspondant, ou {@code null} si aucun produit ne correspond.
     */
    @Override
    public Produit getProduit(String id) {
        Produit p = null;
        String query = "SELECT * FROM Produits WHERE id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = mapRowToProduit(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    /**
     * Insère un nouveau produit dans la table "Produit".
     *
     * @param produit l'objet {@link Produit} à insérer (avec un id, nom, quantite, prix).
     * @return {@code true} si l'insertion a réussi, {@code false} sinon.
     */
    @Override
    public boolean createProduit(Produit produit) {
        String query = "INSERT INTO Produits (id, nom, quantite, prix) VALUES (?,?,?,?)";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, produit.getId());
            ps.setString(2, produit.getNom());
            ps.setInt(3, produit.getQuantite());
            ps.setFloat(4, produit.getPrix());
            int rows = ps.executeUpdate();
            return (rows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Met à jour un produit existant dans la table "Produit".
     *
     * @param produit l'objet {@link Produit} contenant l'id du produit à mettre à jour,
     *                ainsi que les nouvelles valeurs (nom, quantite, prix).
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon (par ex. si l'id n'existe pas).
     */
    @Override
    public boolean updateProduit(Produit produit) {
        String query = "UPDATE Produits SET nom=?, quantite=?, prix=? WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, produit.getNom());
            ps.setInt(2, produit.getQuantite());
            ps.setFloat(3, produit.getPrix());
            ps.setString(4, produit.getId());
            int rows = ps.executeUpdate();
            return (rows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Supprime un produit de la base de données à partir de son identifiant.
     *
     * @param id l'identifiant (varchar(50)) du produit à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     */
    @Override
    public boolean deleteProduit(String id) {
        String query = "DELETE FROM Produits WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, id);
            int rows = ps.executeUpdate();
            return (rows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convertit un {@link ResultSet} en objet {@link Produit}.
     *
     * @param rs le ResultSet représentant une ligne de la table "Produit".
     * @return un objet {@link Produit} initialisé avec les valeurs du tuple.
     * @throws SQLException si une erreur se produit lors de la lecture du ResultSet.
     */
    private Produit mapRowToProduit(ResultSet rs) throws SQLException {
        Produit p = new Produit();
        p.setId(rs.getString("id"));
        p.setNom(rs.getString("nom"));
        p.setQuantite(rs.getInt("quantite"));
        p.setPrix(rs.getInt("prix"));
        return p;
    }
}
