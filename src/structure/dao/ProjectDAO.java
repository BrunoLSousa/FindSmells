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
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metrics.MetricProject;
import structure.Project;

/**
 *
 * @author bruno
 */
public class ProjectDAO implements DAO{

    @Override
    public void register(Object object) {
        Project project = (Project) object;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("INSERT INTO project(name) VALUES(?)");
            ps.setString(1, project.getName());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    private int getLastID() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("SELECT MAX(id) as id FROM project");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int idProject = rs.getInt(1);
            return idProject;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
        return 0;
    }

    public int register(Project project) {
        Object object = (Object) project;
        register(object);
        int id = getLastID();
        return id;
    }
    
    public HashMap<MetricProject, Double> selectMetricsProject(Project project){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("SELECT nop, tloc FROM measure_project WHERE project=?");
            ps.setInt(1, project.getId());
            ResultSet rs = ps.executeQuery();
            HashMap<MetricProject, Double> metrics = new HashMap<>();
            if (rs.next()) {
                metrics.put(MetricProject.NOP, rs.getDouble("nop"));
                metrics.put(MetricProject.TLOC, rs.getDouble("tloc"));
            }
            return metrics;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
        return null;
    }
    
    public void registerMetricProject(Project project){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("INSERT INTO measure_project(project, nop, tloc) VALUES(?, ?, ?)");
            ps.setInt(1, project.getId());
            int index = 2;
            for(MetricProject metric : MetricProject.values()){
                ps.setObject(index, project.getMetricProject(metric));
                index++;
            }
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }
    
    public void updateMetricProject(Project project){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("UPDATE measure_project SET nop=?, tloc=? WHERE project=?");
            ps.setInt(1, project.getId());
            int index = 1;
            for(MetricProject metric : MetricProject.values()){
                ps.setObject(index, project.getMetricProject(metric));
                index++;
            }
            ps.setInt(index, project.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    @Override
    public void update(Object object) {
        Project project = (Project) object;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("UPDATE project SET name=? WHERE id=?");
            ps.setString(1, project.getName());
            ps.setInt(2, project.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }
    
    public void remove(Integer id){
        MethodDAO.removeByIdProject(id);
        PackageDAO.removeByIdProject(id);
        TypeDAO.removeByIdProject(id);
        ProjectDAO.removeMeasureProjectByIdProject(id);
        LogDAO.removeByIdProject(id);
        CycleDAO.removeCycleDataByIdProject(id);
        CycleDAO.removeCycleByIdProject(id);
        removeProject(id);
    }
    
    public static void removeMeasureProjectByIdProject(Integer idProject){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("DELETE FROM measure_project WHERE project=?");
            ps.setInt(1, idProject);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }
    
    private void removeProject(Integer id){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("DELETE FROM project WHERE id=?");
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
            ps = connection.prepareStatement("SELECT * FROM project ORDER BY name ASC");
            ResultSet rs = ps.executeQuery();
            List<Project> projects = new ArrayList<>();
            while (rs.next()) {
                Project project = new Project(rs.getInt("id"), rs.getString("name"));
                projects.add(project);
            }
            return projects;
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
            ps = connection.prepareStatement("SELECT * FROM project WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Project project = null;
            if (rs.next()) {
                project = new Project(rs.getInt("id"), rs.getString("name"));
            }
            return project;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
        return null;
    }
    
    public List<Project> selectProjectsByFilter(String nameProject) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            String sql = createSqlFilter(nameProject);
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Project> projects = new ArrayList<>();
            while (rs.next()) {
                Project project = new Project(rs.getInt("id"), rs.getString("name"));
                projects.add(project);
            }
            return projects;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
        return null;
    }

    private String createSqlFilter(String nameProject) {
        String sql = "SELECT * FROM project ";
        String connector = "WHERE";
        if (!nameProject.isEmpty()) {
            sql += connector + " name LIKE '%" + nameProject + "%' ";
        }
        sql += " ORDER BY name ASC";
        return sql;
    }

}
