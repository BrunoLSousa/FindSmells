/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure.dao;

import connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metrics.Granulatiry;
import structure.DetectionStrategy;

/**
 *
 * @author bruno
 */
public class DetectionStrategyDAO implements DAO {

    @Override
    public void register(Object object) {
        DetectionStrategy detectionStrategy = (DetectionStrategy) object;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("INSERT INTO detection_strategy(name, granularity, expression) VALUES(?, ?, ?)");
            ps.setString(1, detectionStrategy.getName());
            ps.setString(2, detectionStrategy.getGranularity().toString());
            ps.setString(3, detectionStrategy.getExpression());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    @Override
    public void update(Object object) {
        DetectionStrategy detectionStrategy = (DetectionStrategy) object;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("UPDATE detection_strategy SET name=?, granularity=?, expression=? WHERE id=?");
            ps.setString(1, detectionStrategy.getName());
            ps.setString(2, detectionStrategy.getGranularity().toString());
            ps.setString(3, detectionStrategy.getExpression());
            ps.setInt(4, detectionStrategy.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("DELETE FROM detection_strategy WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    @Override
    public Object selectAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("SELECT * FROM detection_strategy ORDER BY name ASC");
            ResultSet rs = ps.executeQuery();
            List<DetectionStrategy> detectionStrategies = new ArrayList<>();
            while (rs.next()) {
                DetectionStrategy detectionStrategy = new DetectionStrategy(rs.getInt("id"), rs.getString("name"), Granulatiry.valueOf(rs.getString("granularity")), rs.getString("expression"));
                detectionStrategies.add(detectionStrategy);
            }
            return detectionStrategies;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
        return null;
    }

    @Override
    public Object selectById(Integer id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("SELECT * FROM detection_strategy WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            DetectionStrategy detectionStrategy = null;
            if (rs.next()) {
                detectionStrategy = new DetectionStrategy(rs.getInt("id"), rs.getString("name"), Granulatiry.valueOf(rs.getString("granularity")), rs.getString("expression"));
            }
            return detectionStrategy;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
        return null;
    }

    public List<DetectionStrategy> selectDetectionStrategiesByFilter(String detectionStrategy, String granularity) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            String sql = createSqlFilter(detectionStrategy, granularity);
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<DetectionStrategy> detectionStrategies = new ArrayList<>();
            while (rs.next()) {
                DetectionStrategy strategy = new DetectionStrategy(rs.getInt("id"), rs.getString("name"), Granulatiry.valueOf(rs.getString("granularity")), rs.getString("expression"));
                detectionStrategies.add(strategy);
            }
            return detectionStrategies;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
        return null;
    }

    private String createSqlFilter(String detectionStrategy, String granularity) {
        String sql = "SELECT * FROM detection_strategy ";
        String connector = "WHERE";
        if (!detectionStrategy.isEmpty()) {
            sql += connector + " name LIKE '%" + detectionStrategy + "%' ";
            connector = "AND";
        }
        if (!granularity.equals("None")) {
            sql += connector + " granularity='" + granularity + "' ";
        }
        sql += "ORDER BY name ASC";
        return sql;
    }
    
    public void remove(int id){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("DELETE FROM detection_strategy WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

}
