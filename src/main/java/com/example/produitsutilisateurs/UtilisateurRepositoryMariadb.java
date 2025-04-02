package com.example.produitsutilisateurs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de {@link UtilisateurRepositoryInterface} pour un stockage dans une base de données
 * MariaDB. Les informations sur les utilisateurs sont stockées dans la table "Utilisateurs".
 */
public class UtilisateurRepositoryMariadb implements UtilisateurRepositoryInterface {

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
    public UtilisateurRepositoryMariadb(String dbUrl, String dbUser, String dbPwd)
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
     * Récupère la liste de tous les utilisateurs présents dans la table "Utilisateurs".
     *
     * @return une liste d'objets {@link Utilisateur}.
     *         Peut être vide si aucun enregistrement n'existe dans la base.
     */
    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> results = new ArrayList<>();
        String query = "SELECT * FROM Utilisateurs";  // Nom de la table en BD
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur u = mapRowToUtilisateur(rs);
                results.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    /**
     * Récupère un utilisateur spécifique à partir de son identifiant (clé primaire).
     *
     * @param id l'identifiant de l'utilisateur (int(11)).
     * @return l'objet {@link Utilisateur} correspondant, ou {@code null} si aucun utilisateur ne correspond.
     */
    @Override
    public Utilisateur getUtilisateur(int id) {
        Utilisateur u = null;
        String query = "SELECT * FROM Utilisateurs WHERE id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = mapRowToUtilisateur(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;
    }

    /**
     * Insère un nouvel utilisateur dans la table "Utilisateurs".
     *
     * @param utilisateur l'objet {@link Utilisateur} à insérer (avec un id, nom, mdp).
     * @return {@code true} si l'insertion a réussi, {@code false} sinon.
     */
    @Override
    public boolean createUtilisateur(Utilisateur utilisateur) {
        String query = "INSERT INTO Utilisateurs (id, nom, mdp) VALUES (?,?,?)";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, utilisateur.getId());
            ps.setString(2, utilisateur.getNom());
            ps.setString(3, utilisateur.getMdp());
            int rows = ps.executeUpdate();
            return (rows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Met à jour un utilisateur existant dans la table "Utilisateurs".
     *
     * @param utilisateur l'objet {@link Utilisateur} contenant l'id de l'utilisateur à mettre à jour,
     *                    ainsi que les nouvelles valeurs (nom, mdp).
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon (par ex. si l'id n'existe pas).
     */
    @Override
    public boolean updateUtilisateur(Utilisateur utilisateur) {
        String query = "UPDATE Utilisateurs SET nom=?, mdp=? WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, utilisateur.getNom());
            ps.setString(2, utilisateur.getMdp());
            ps.setString(3, utilisateur.getId());
            int rows = ps.executeUpdate();
            return (rows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Supprime un utilisateur de la base de données à partir de son identifiant.
     *
     * @param id l'identifiant (int(11)) de l'utilisateur à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     */
    @Override
    public boolean deleteUtilisateur(int id) {
        String query = "DELETE FROM Utilisateurs WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return (rows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convertit un {@link ResultSet} en objet {@link Utilisateur}.
     *
     * @param rs le ResultSet représentant une ligne de la table "Utilisateurs".
     * @return un objet {@link Utilisateur} initialisé avec les valeurs du tuple.
     * @throws SQLException si une erreur se produit lors de la lecture du ResultSet.
     */
    private Utilisateur mapRowToUtilisateur(ResultSet rs) throws SQLException {
        Utilisateur u = new Utilisateur();
        u.setId(rs.getString("id"));
        u.setNom(rs.getString("nom"));
        u.setMdp(rs.getString("mdp"));
        return u;
    }
}
