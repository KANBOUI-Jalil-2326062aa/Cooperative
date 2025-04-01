package com.example.paniers;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation JDBC (MariaDB) de PanierRepositoryInterface.
 *
 * Cette classe gère l'accès aux données pour la table Panier et
 * la table ProduitPanier.
 */
public class PanierRepositoryMariadb implements PanierRepositoryInterface {

    /**
     * Connexion à la base de données.
     */
    protected Connection dbConnection;

    /**
     * Constructeur.
     *
     * @param dbUrl  URL de connexion à la base de données.
     * @param dbUser Nom d'utilisateur.
     * @param dbPwd  Mot de passe.
     * @throws SQLException           en cas d'erreur SQL.
     * @throws ClassNotFoundException si le driver MariaDB n'est pas trouvé.
     */
    public PanierRepositoryMariadb(String dbUrl, String dbUser, String dbPwd)
            throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        this.dbConnection = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
    }

    /**
     * Ferme la connexion à la base de données.
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

    /* --------------------------------------
       Méthodes pour gérer le Panier
       -------------------------------------- */

    /**
     * Récupère la liste de tous les paniers.
     */
    @Override
    public List<Panier> getAllPaniers() {
        List<Panier> result = new ArrayList<>();
        String query = "SELECT * FROM Panier";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Panier p = mapRowToPanier(rs);
                result.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Récupère un panier spécifique par son id.
     */
    @Override
    public Panier getPanier(String idPanier) {
        Panier p = null;
        String query = "SELECT * FROM Panier WHERE id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, idPanier);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = mapRowToPanier(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    /**
     * Crée un nouveau panier dans la base de données.
     */
    @Override
    public boolean createPanier(Panier panier) {
        String query = "INSERT INTO Panier (id, date_retrait, lieu_relais, prix_total, est_valide, date_maj) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, panier.getIdPanier());
            ps.setDate(2, (panier.getDateRetrait() != null) ? Date.valueOf(panier.getDateRetrait()) : null);
            ps.setString(3, panier.getLieuRelais());
            ps.setFloat(4, panier.getPrixTotal());
            ps.setBoolean(5, panier.isEstValide());
            ps.setDate(6, (panier.getDateMaj() != null) ? Date.valueOf(panier.getDateMaj()) : null);
            int rows = ps.executeUpdate();
            return (rows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Met à jour un panier existant.
     */
    @Override
    public boolean updatePanier(Panier panier) {
        String query = "UPDATE Panier SET date_retrait = ?, lieu_relais = ?, prix_total = ?, est_valide = ?, date_maj = ? WHERE id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setDate(1, (panier.getDateRetrait() != null) ? Date.valueOf(panier.getDateRetrait()) : null);
            ps.setString(2, panier.getLieuRelais());
            ps.setFloat(3, panier.getPrixTotal());
            ps.setBoolean(4, panier.isEstValide());
            ps.setDate(5, (panier.getDateMaj() != null) ? Date.valueOf(panier.getDateMaj()) : null);
            ps.setString(6, panier.getIdPanier());
            int rows = ps.executeUpdate();
            return (rows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Supprime un panier ainsi que ses produits associés.
     */
    @Override
    public boolean deletePanier(String idPanier) {
        String deleteProduitQuery = "DELETE FROM ProduitPanier WHERE panier_id = ?";
        String deletePanierQuery = "DELETE FROM Panier WHERE id = ?";
        try {
            // Suppression des produits associés
            try (PreparedStatement ps1 = dbConnection.prepareStatement(deleteProduitQuery)) {
                ps1.setString(1, idPanier);
                ps1.executeUpdate();
            }
            // Suppression du panier
            try (PreparedStatement ps2 = dbConnection.prepareStatement(deletePanierQuery)) {
                ps2.setString(1, idPanier);
                int rows = ps2.executeUpdate();
                return (rows > 0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Récupère la liste des produits d'un panier.
     */
    @Override
    public List<ProduitPanier> getProduitsDuPanier(String idPanier) {
        List<ProduitPanier> result = new ArrayList<>();
        String query = "SELECT * FROM ProduitPanier WHERE panier_id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, idPanier);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProduitPanier pp = mapRowToProduitPanier(rs);
                result.add(pp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Ajoute un produit dans le panier (table ProduitPanier).
     */
    @Override
    public boolean addProduitDansPanier(ProduitPanier produitPanier) {
        String query = "INSERT INTO ProduitPanier (panier_id, produit_id, quantite) VALUES (?,?,?)";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, produitPanier.getPanier_id());
            ps.setString(2, produitPanier.getProduit_id());
            ps.setFloat(3, produitPanier.getQuantite());
            int rows = ps.executeUpdate();
            return (rows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Met à jour un produit déjà associé à un panier (par exemple, la quantité).
     */
    @Override
    public boolean updateProduitDansPanier(ProduitPanier produitPanier) {
        String query = "UPDATE ProduitPanier SET quantite = ? WHERE panier_id = ? AND produit_id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setFloat(1, produitPanier.getQuantite());
            ps.setString(2, produitPanier.getPanier_id());
            ps.setString(3, produitPanier.getProduit_id());
            int rows = ps.executeUpdate();
            return (rows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Supprime un produit d'un panier.
     */
    @Override
    public boolean removeProduitDuPanier(String idPanier, String idProduit) {
        String query = "DELETE FROM ProduitPanier WHERE panier_id = ? AND produit_id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, idPanier);
            ps.setString(2, idProduit);
            int rows = ps.executeUpdate();
            return (rows > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Méthode de mapping d'une ligne de résultat SQL vers un objet Panier.
     *
     * @param rs ResultSet
     * @return un objet Panier
     * @throws SQLException en cas d'erreur d'accès aux données
     */
    private Panier mapRowToPanier(ResultSet rs) throws SQLException {
        Panier p = new Panier();
        p.setIdPanier(rs.getString("id"));
        // Les colonnes de la BD pour Panier sont : date_retrait, lieu_relais, prix_total, est_valide, date_maj
        Date dr = rs.getDate("date_retrait");
        p.setDateRetrait((dr != null) ? dr.toLocalDate() : null);
        p.setLieuRelais(rs.getString("lieu_relais"));
        p.setPrixTotal(rs.getFloat("prix_total"));
        p.setEstValide(rs.getBoolean("est_valide"));
        Date dmaj = rs.getDate("date_maj");
        p.setDateMaj((dmaj != null) ? dmaj.toLocalDate() : null);
        return p;
    }

    /**
     * Méthode de mapping d'une ligne de résultat SQL vers un objet ProduitPanier.
     *
     * @param rs ResultSet
     * @return un objet ProduitPanier
     * @throws SQLException en cas d'erreur d'accès aux données
     */
    private ProduitPanier mapRowToProduitPanier(ResultSet rs) throws SQLException {
        ProduitPanier pp = new ProduitPanier();
        pp.setPanier_id(rs.getString("panier_id"));
        pp.setProduit_id(rs.getString("produit_id"));
        pp.setQuantite(rs.getFloat("quantite"));
        return pp;
    }
}
