package com.commandes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation JDBC (MariaDB) de CommandeRepositoryInterface.
 *
 * Cette classe gère l'accès aux données pour la table "Commande"
 * et implémente les opérations CRUD pour l'entité {@link Commande}.
 */
public class CommandeRepositoryMariadb implements CommandeRepositoryInterface {

    /**
     * Connexion à la base de données.
     */
    protected Connection dbConnection;

    /**
     * Constructeur.
     *
     * @param dbUrl  l'URL de connexion à la base de données.
     * @param dbUser le nom d'utilisateur.
     * @param dbPwd  le mot de passe.
     * @throws SQLException           en cas d'erreur SQL.
     * @throws ClassNotFoundException si le driver MariaDB n'est pas trouvé.
     */
    public CommandeRepositoryMariadb(String dbUrl, String dbUser, String dbPwd)
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.dbConnection = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
    }

    /**
     * Ferme la connexion à la base de données.
     */
    @Override
    public void close() {
        try {
            if (dbConnection != null && !dbConnection.isClosed()){
                dbConnection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Récupère la liste de toutes les commandes.
     *
     * @return une liste d'objets {@link Commande}.
     */
    @Override
    public List<Commande> getAllCommandes() {
        List<Commande> result = new ArrayList<>();
        String query = "SELECT * FROM Commande";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Commande commande = mapRowToCommande(rs);
                result.add(commande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Récupère une commande spécifique à partir de son identifiant.
     *
     * @param id l'identifiant de la commande.
     * @return l'objet {@link Commande} correspondant, ou {@code null} si aucune commande ne correspond à l'id.
     */
    @Override
    public Commande getCommande(String id) {
        Commande commande = null;
        String query = "SELECT * FROM Commande WHERE id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                commande = mapRowToCommande(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commande;
    }

    /**
     * Insère une nouvelle commande dans la table "Commande".
     *
     * @param commande l'objet {@link Commande} à insérer (doit contenir un id, idPanier et idUtilisateur).
     * @return {@code true} si l'insertion a réussi, {@code false} sinon.
     */
    @Override
    public boolean createCommande(Commande commande) {
        String query = "INSERT INTO Commande (id, panierId, utilisateurId) VALUES (?,?,?)";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, commande.getId());
            ps.setString(2, commande.getIdPanier());
            ps.setString(3, commande.getIdUtilisateur());
            int rows = ps.executeUpdate();
            return (rows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Met à jour une commande existante dans la table "Commande".
     *
     * @param commande l'objet {@link Commande} contenant l'id à modifier et les nouvelles valeurs.
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon.
     */
    @Override
    public boolean updateCommande(Commande commande) {
        String query = "UPDATE Commande SET panierId = ?, utilisateurId = ? WHERE id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, commande.getIdPanier());
            ps.setString(2, commande.getIdUtilisateur());
            ps.setString(3, commande.getId());
            int rows = ps.executeUpdate();
            return (rows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Supprime une commande de la table "Commande" à partir de son identifiant.
     *
     * @param id l'identifiant de la commande à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     */
    @Override
    public boolean deleteCommande(String id) {
        String query = "DELETE FROM Commande WHERE id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, id);
            int rows = ps.executeUpdate();
            return (rows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Mappe une ligne du ResultSet vers un objet {@link Commande}.
     *
     * @param rs le ResultSet représentant une ligne de la table "Commande".
     * @return un objet {@link Commande} initialisé avec les valeurs du tuple.
     * @throws SQLException en cas d'erreur lors de la lecture du ResultSet.
     */
    private Commande mapRowToCommande(ResultSet rs) throws SQLException {
        Commande commande = new Commande();
        commande.setId(rs.getString("id"));
        commande.setIdPanier(rs.getString("panierId"));
        commande.setIdUtilisateur(rs.getString("utilisateurId"));
        return commande;
    }
}
