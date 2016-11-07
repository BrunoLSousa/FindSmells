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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metrics.MetricMethod;
import metrics.MetricType;
import structure.Artifact;
import structure.Method;
import structure.Project;
import structure.Type;
import structure.log.Log;

/**
 *
 * @author bruno
 */
public class LogDAO implements DAO {

    @Override
    public void register(Object object) {
        Log log = (Log) object;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            String source = getInformationSource(log.getArtifact());
            ps = connection.prepareStatement("INSERT INTO log(project, name_artifact, source_artifact, package_artifact, metric, subject, message, date_log) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, log.getArtifact().getProject().getId());
            ps.setObject(2, log.getArtifact().getName());
            ps.setObject(3, source);
            ps.setObject(4, log.getArtifact().getPack());
            ps.setString(5, log.getMetric());
            ps.setString(6, log.getSubject());
            ps.setString(7, log.getMessage());
            ps.setObject(8, log.getDate());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    @Override
    public void update(Object object) {
        Log log = (Log) object;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            String source = getInformationSource(log.getArtifact());
            ps = connection.prepareStatement("UPDATE log SET project=?, name_artifact=?, source_artifact=?, package_artifact=?, metric=?, subject=?, message=?, date_log=? WHERE id=?");
            ps.setInt(1, log.getArtifact().getProject().getId());
            ps.setObject(2, log.getArtifact().getName());
            ps.setObject(3, source);
            ps.setObject(4, log.getArtifact().getPack());
            ps.setString(5, log.getMetric());
            ps.setString(6, log.getSubject());
            ps.setString(7, log.getMessage());
            ps.setObject(8, log.getDate());
            ps.setInt(9, log.getId());
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
            ps = connection.prepareStatement("SELECT * FROM log ORDER BY date_log DESC");
            ResultSet rs = ps.executeQuery();
            List<Log> logs = new ArrayList<>();
            while (rs.next()) {
                Artifact artifact = getInformationArtifact(rs);
                Log log = new Log(rs.getInt("id"), artifact, rs.getString("metric"), rs.getString("subject"), rs.getString("message"), rs.getDate("date_log"));
                logs.add(log);
            }
            return logs;
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
            ps = connection.prepareStatement("SELECT * FROM log WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Log log = null;
            if (rs.next()) {
                Artifact artifact = getInformationArtifact(rs);
                log = new Log(rs.getInt("id"), artifact, rs.getString("metric"), rs.getString("subject"), rs.getString("message"), rs.getDate("date_log"));
            }
            return log;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
        return null;
    }

    private String getInformationSource(Artifact artifact) {
        if (artifact instanceof Method) {
            Method method = (Method) artifact;
            return method.getSource();
        } else if (artifact instanceof Type) {
            Type type = (Type) artifact;
            return type.getSource();
        }
        return null;
    }

    private Artifact getInformationArtifact(ResultSet rs) throws SQLException {
        Project project = (Project) new ProjectDAO().selectById(rs.getInt("project"));
        if (Arrays.toString(MetricMethod.values()).contains(rs.getString("metric"))) {
            return new Method(rs.getString("name_artifact"), project, rs.getString("source_artifact"), rs.getString("package_artifact"));
        } else if (Arrays.toString(MetricType.values()).contains(rs.getString("metric"))) {
            return new Type(rs.getString("name_artifact"), project, rs.getString("source_artifact"), rs.getString("package_artifact"));
        } else {
            return new structure.Package(rs.getString("name_artifact"), project, rs.getString("package_artifact"));
        }
    }
    
    public List<Log> selectLogsByFilter(Project project, String metric, String subject){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            String sql = createSqlFilter(project, metric, subject);
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Log> logs = new ArrayList<>();
            while (rs.next()) {
                Artifact artifact = getInformationArtifact(rs);
                Log log = new Log(rs.getInt("id"), artifact, rs.getString("metric"), rs.getString("subject"), rs.getString("message"), rs.getDate("date_log"));
                logs.add(log);
            }
            return logs;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
        return null;
    }
    
    private String createSqlFilter(Project project, String metric, String subject){
        String sql = "SELECT * FROM log ";
        String connector = "WHERE";
        if(project.getId() != 0){
            sql += connector + " project='"+project.getId()+"' ";
            connector = "AND";
        }
        if(!metric.equals("None")){
            sql += connector + " metric='"+metric+"' ";
            connector = "AND";
        }
        if(!subject.equals("None")){
            sql += connector + " subject='"+subject+"' ";
        }
        sql += "ORDER BY date_log DESC";
        return sql;
    }

}
