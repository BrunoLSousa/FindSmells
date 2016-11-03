/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class DBConnection {

    public static Connection getConnection() throws ClassNotFoundException {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:findSmells.sqlite");
        } catch (ClassNotFoundException ex) {
            String errorMessage = "Driver não encontrado!";
            throw new ClassNotFoundException(errorMessage, ex);
        } catch (SQLException ex) {
            String errorMessage = "Erro ao estabelecer conexão!";
            throw new ClassNotFoundException(errorMessage, ex);
        }
        return connection;
    }

    private static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível fechar a conexão!" + e.getCause());
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível fechar a conexão!" + e.getCause());
        }
        closeConnection(connection);
    }

    private static void createTableProject() {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "CREATE TABLE IF NOT EXISTS project (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , name VARCHAR(100) NOT NULL )";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    private static void createTableCycle() {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "CREATE TABLE IF NOT EXISTS cycle (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "project INTEGER NOT NULL, name VARCHAR(100) NOT NULL, nodes INTEGER NOT NULL, "
                + "diameter INTEGER NOT NULL, FOREIGN KEY(project) REFERENCES project(id))";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    private static void createTableDataCycle() {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "CREATE TABLE IF NOT EXISTS data_cycle(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "cycle INTEGER NOT NULL, package VARCHAR(100) NOT NULL, FOREIGN KEY (cycle) REFERENCES cycle(id))";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    private static void createTableDetectionStrategy() {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "CREATE TABLE IF NOT EXISTS detection_strategy(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "name VARCHAR(100) NOT NULL, expression TEXT NOT NULL)";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    private static void createTableLog() {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "CREATE TABLE IF NOT EXISTS log(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "project INTEGER NOT NULL, name_artifact VARCHAR(500) DEFAULT NULL, source_artifact VARCHAR(500) DEFAULT NULL,"
                + "package_artifact VARCHAR(500) DEFAULT NULL, metric VARCHAR(10), subject VARCHAR(100) NOT NULL, "
                + "message TEXT NOT NULL, date_log DATETIME NOT NULL, FOREIGN KEY(project) REFERENCES project(id))";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    private static void createTableMeasureClass() {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "CREATE TABLE IF NOT EXISTS measure_class(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, project INTEGER NOT NULL, "
                + "name VARCHAR(500), source VARCHAR(500), package VARCHAR(500), dit DOUBLE DEFAULT '-1', lcom double DEFAULT '-1', "
                + "nof double DEFAULT '-1', nom double DEFAULT '-1', norm double DEFAULT '-1', nsc double DEFAULT '-1', "
                + "nsf double DEFAULT '-1', nsm double DEFAULT '-1', six double DEFAULT '-1', wmc double DEFAULT '-1', "
                + "FOREIGN KEY(project) REFERENCES project(id))";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    private static void createTableMeasureMethod() {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "CREATE TABLE IF NOT EXISTS measure_method(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "project INTEGER NOT NULL, name VARCHAR, source VARCHAR, package VARCHAR, mloc DOUBLE DEFAULT '-1', "
                + "nbd DOUBLE DEFAULT '-1', par DOUBLE DEFAULT '-1', vg DOUBLE DEFAULT '-1', FOREIGN KEY(project) REFERENCES project(id))";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    private static void createTableMeasurePackage() {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "CREATE TABLE IF NOT EXISTS measure_package(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "project INTEGER NOT NULL, name VARCHAR(500), source VARCHAR(500), package VARCHAR(500), ca DOUBLE DEFAULT '-1',"
                + "ce DOUBLE DEFAULT '-1', noc DOUBLE DEFAULT '-1', noi DOUBLE DEFAULT '-1', rma DOUBLE DEFAULT '-1', "
                + "rmd DOUBLE DEFAULT '-1', rmi DOUBLE DEFAULT '-1', FOREIGN KEY (project) REFERENCES project(id))";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    private static void createTableMeasureProject() {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "CREATE TABLE IF NOT EXISTS measure_project (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, project INTEGER NOT NULL, "
                + "nop DOUBLE, tloc DOUBLE, FOREIGN KEY(project) REFERENCES project(id))";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    private static void insertTeste() {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("INSERT INTO project(name) VALUES(?)");
            ps.setString(1, "teste");
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    public static void createDataBase() {
        createTableProject();
        createTableDetectionStrategy();
        createTableCycle();
        createTableDataCycle();
        createTableLog();
        createTableMeasureClass();
        createTableMeasureMethod();
        createTableMeasurePackage();
        createTableMeasureProject();
    }

    public static void main(String[] args) {
        DBConnection.createDataBase();

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("SELECT * FROM project");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "      " + rs.getString("name"));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

}
