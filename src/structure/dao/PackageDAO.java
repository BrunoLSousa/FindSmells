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
import metrics.MetricPackage;
import structure.DetectionStrategy;
import structure.Package;
import structure.Project;

/**
 *
 * @author bruno
 */
public class PackageDAO implements DAOMetric {

    @Override
    public void register(Object object) {
        Package pack = (Package) object;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("INSERT INTO measure_package(project, name, package, ca, ce, noc, noi, rma, rmd, rmi) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, pack.getProject().getId());
            ps.setString(2, pack.getName());
            ps.setString(3, pack.getPack());
            int index = 4;
            for (MetricPackage metric : MetricPackage.values()) {
                ps.setObject(index, pack.getValueMetric(metric));
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
        Package pack = (Package) object;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("UPDATE measure_package SET project=?, name=?, package=?, ca=?, ce=?, noc=?, noi=?, rma=?, rmd=?, rmi=? WHERE id=?");
            ps.setInt(1, pack.getProject().getId());
            ps.setString(2, pack.getName());
            ps.setString(3, pack.getPack());
            int index = 4;
            for (MetricPackage metric : MetricPackage.values()) {
                ps.setObject(index, pack.getValueMetric(metric));
                index++;
            }
            ps.setInt(index, pack.getId());
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

    @Override
    public Object selectByObject(Object object) {
        Package pack = (Package) object;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            String sql = createQuerySelectByObject(pack);
            ps = connection.prepareStatement(sql);
            ps = assignAttributeInQuery(ps, pack);
            ResultSet rs = ps.executeQuery();
            List<Package> packages = new ArrayList<>();
            while (rs.next()) {
                Package p = new Package(rs.getString("name"), pack.getProject(), rs.getString("package"));
                p.setId(rs.getInt("id"));
                for (MetricPackage metric : MetricPackage.values()) {
                    String nameMetric = metric.toString().toLowerCase();
                    p.updateValueMetric(nameMetric, rs.getDouble(nameMetric));
                }
                packages.add(p);
            }
            return packages;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
        return null;
    }

    private String createQuerySelectByObject(Package pack) {
        String sql = "SELECT * FROM measure_package WHERE ";
        sql = (pack.getName() == null) ? (sql + "name is NULL AND ") : (sql + "name=? AND ");
        sql = (pack.getPack() == null) ? (sql + "package is NULL AND project=?") : (sql + "package=? AND project=?");
        return sql;
    }

    private PreparedStatement assignAttributeInQuery(PreparedStatement ps, Package pack) throws SQLException {
        int index = 1;
        if (pack.getName() != null) {
            ps.setObject(index, pack.getName());
            index++;
        }
        if (pack.getPack() != null) {
            ps.setObject(index, pack.getPack());
            index++;
        }
        ps.setInt(index, pack.getProject().getId());
        return ps;
    }
    
    @Override
    public Object applyDetectionStrategy(DetectionStrategy detectionStrategy, Project project) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("SELECT * FROM measure_package WHERE (" + detectionStrategy.getExpression() + ") AND project=?");
            ps.setInt(1, project.getId());
            ResultSet rs = ps.executeQuery();
            List<Package> packages = new ArrayList<>();
            while (rs.next()) {
                Package pack = new Package(rs.getInt("id"), rs.getString("name"), project, rs.getString("package"));
                for (MetricPackage metric : MetricPackage.values()) {
                    String nameMetric = metric.toString().toLowerCase();
                    pack.updateValueMetric(nameMetric, rs.getDouble(nameMetric));
                }
                packages.add(pack);
            }
            return packages;
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
            ps = connection.prepareStatement("SELECT nop FROM measure_project WHERE project=?");
            ps.setInt(1, project.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("nop");
            }
            return 0;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
        return 0;
    }

}
