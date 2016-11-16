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
import metrics.MetricType;
import structure.DetectionStrategy;
import structure.Project;
import structure.Type;

/**
 *
 * @author bruno
 */
public class TypeDAO implements DAOMetric {

    @Override
    public Object selectByObject(Object object) {
        Type type = (Type) object;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            String sql = createQuerySelectByObject(type);
            ps = connection.prepareStatement(sql);
            ps = assignAttributeInQuery(ps, type);
            ResultSet rs = ps.executeQuery();
            List<Type> types = new ArrayList<>();
            while (rs.next()) {
                Type t = new Type(rs.getString("name"), type.getProject(), rs.getString("source"), rs.getString("package"));
                t.setId(rs.getInt("id"));
                for (MetricType metric : MetricType.values()) {
                    String nameMetric = metric.toString().toLowerCase();
                    t.updateValueMetric(nameMetric, rs.getDouble(nameMetric));
                }
                types.add(t);
            }
            return types;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
        return null;
    }

    @Override
    public void register(Object object) {
        Type type = (Type) object;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("INSERT INTO measure_class(project, name, source, package, dit, lcom, nof, nom, norm, nsc, nsf, nsm, six, wmc) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, type.getProject().getId());
            ps.setString(2, type.getName());
            ps.setString(3, type.getSource());
            ps.setString(4, type.getPack());
            int index = 5;
            for (MetricType metric : MetricType.values()) {
                ps.setObject(index, type.getValueMetric(metric));
                index++;
            }
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    @Override
    public void update(Object object) {
        Type type = (Type) object;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("UPDATE measure_class SET project=?, name=?, source=?, package=?, dit=?, lcom=?, nof=?, nom=?, norm=?, nsc=?, nsf=?, nsm=?, six=?, wmc=? WHERE id=?");
            ps.setInt(1, type.getProject().getId());
            ps.setString(2, type.getName());
            ps.setString(3, type.getSource());
            ps.setString(4, type.getPack());
            int index = 5;
            for (MetricType metric : MetricType.values()) {
                ps.setObject(index, type.getValueMetric(metric));
                index++;
            }
            ps.setInt(index, type.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    @Override
    public Object selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object selectById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String createQuerySelectByObject(Type type) {
        String sql = "SELECT * FROM measure_class WHERE ";
        sql = (type.getName() == null) ? (sql + "name is NULL AND ") : (sql + "name=? AND ");
        sql = (type.getSource() == null) ? (sql + "source is NULL AND ") : (sql + "source=? AND ");
        sql = (type.getPack() == null) ? (sql + "package is NULL AND project=?") : (sql + "package=? AND project=?");
        return sql;
    }

    private PreparedStatement assignAttributeInQuery(PreparedStatement ps, Type type) throws SQLException {
        int index = 1;
        if (type.getName() != null) {
            ps.setObject(index, type.getName());
            index++;
        }
        if (type.getSource() != null) {
            ps.setObject(index, type.getSource());
            index++;
        }
        if (type.getPack() != null) {
            ps.setObject(index, type.getPack());
            index++;
        }
        ps.setInt(index, type.getProject().getId());
        return ps;
    }

    @Override
    public Object applyDetectionStrategy(DetectionStrategy detectionStrategy, Project project) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("SELECT * FROM measure_class WHERE (" + detectionStrategy.getExpression() + ") AND project=?");
            ps.setInt(1, project.getId());
            ResultSet rs = ps.executeQuery();
            List<Type> types = new ArrayList<>();
            while (rs.next()) {
                Type type = new Type(rs.getInt("id"), rs.getString("name"), project, rs.getString("source"), rs.getString("package"));
                for (MetricType metric : MetricType.values()) {
                    String nameMetric = metric.toString().toLowerCase();
                    type.updateValueMetric(nameMetric, rs.getDouble(nameMetric));
                }
                types.add(type);
            }
            return types;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
        return null;
    }

    @Override
    public int totalArtifacts(Project project) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("SELECT sum(noc) as noc FROM measure_package WHERE project=?");
            ps.setInt(1, project.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("noc");
            }
            return 0;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
        return 0;
    }
    
    public static void removeByIdProject(Integer idProject){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("DELETE FROM measure_type WHERE project=?");
            ps.setInt(1, idProject);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

}
