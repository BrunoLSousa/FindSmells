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
import java.util.logging.Level;
import java.util.logging.Logger;
import structure.Cycle;

/**
 *
 * @author bruno
 */
public class CycleDAO implements DAO{

    @Override
    public void register(Object object) {
        Cycle cycle = (Cycle) object;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("INSERT INTO cycle(project, name, nodes, diameter) VALUES(?, ?, ?, ?)");
            ps.setInt(1, cycle.getProject().getId());
            ps.setString(2, cycle.getName());
            ps.setInt(3, cycle.getNodes());
            ps.setInt(4, cycle.getDiameter());
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
            ps = connection.prepareStatement("SELECT MAX(id) as id FROM cycle");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int idCycle = rs.getInt("id");
            return idCycle;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
        return 0;
    }

    public int register(Cycle cycle) {
        Object object = (Object) cycle;
        register(object);
        int id = getLastID();
        return id;
    }
    
    public void registerCycleData(int idCycle, String pack){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("INSERT INTO data_cycle(cycle, package) VALUES(?, ?)");
            ps.setInt(1, idCycle);
            ps.setString(2, pack);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    @Override
    public void update(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object selectById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
