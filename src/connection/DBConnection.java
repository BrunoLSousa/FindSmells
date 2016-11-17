/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import structure.DetectionStrategy;
import structure.dao.DetectionStrategyDAO;

/**
 *
 * @author bruno
 */
public class DBConnection {

    public static Connection getConnection() throws ClassNotFoundException {
        Connection connection = null;
        String path = getPath();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + path + "/findSmells.sqlite");
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
                + "name VARCHAR(100) NOT NULL, granularity VARCHAR(20) NOT NULL, expression TEXT NOT NULL, flag INTEGER DEFAULT '0')";
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
                + "project INTEGER NOT NULL, name VARCHAR(500), package VARCHAR(500), ca DOUBLE DEFAULT '-1',"
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

    private static void insertDetectionStrategyGodClass() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement("INSERT INTO detection_strategy(name, granularity, expression, flag) VALUES ('God Class', 'Class', "
                    + "'(lcom > 0.725 AND wmc > 34 AND nof > 8 AND nom > 14)', '0')");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    private static void insertDetectionStrategyLongMethod() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement("INSERT INTO detection_strategy(name, granularity, expression, flag) VALUES ('Long Method', 'Method', "
                    + "'(mloc > 30 AND vg > 4 AND nbd > 3)', '0')");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    private static void insertDetectionStrategyDataClass() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement("INSERT INTO detection_strategy(name, granularity, expression, flag) VALUES ('Data Class', 'Class', "
                    + "'(nsc <= 1 AND dit <= 2 AND nof > 3)', '0')");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    private static void insertDetectionStrategyFeatureEnvy() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement("INSERT INTO detection_strategy(name, granularity, expression, flag) VALUES ('Feature Envy', 'Class', "
                    + "'(lcom > 0.725)', '0')");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection, ps);
        }
    }

    private static void insertDetectionStrategyRefusedBequest() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement("INSERT INTO detection_strategy(name, granularity, expression, flag) VALUES ('Refused Bequest', 'Class', "
                    + "'(six > 0.019)', '0')");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
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
        insertDetectionStrategies();
    }

    private static void insertDetectionStrategies() {
        List<DetectionStrategy> detections = (List<DetectionStrategy>) new DetectionStrategyDAO().selectAll();
        if (detections.isEmpty()) {
            insertDetectionStrategyDataClass();
            insertDetectionStrategyFeatureEnvy();
            insertDetectionStrategyGodClass();
            insertDetectionStrategyLongMethod();
            insertDetectionStrategyRefusedBequest();
        }
    }

    private static String preProcessPath(String path) {
        if (path.startsWith("~")) {
            String osName = System.getProperty("os.name").toLowerCase();
            String homePath = System.getProperty("user.home");
            if (osName.startsWith("windows")) {
                homePath = homePath.replace("\\", "/");
            }
            path = path.replaceFirst("^~", homePath);
        } else if (path.startsWith(".") && !path.startsWith("..")) {
            String osName = System.getProperty("os.name").toLowerCase();
            String currPath = System.getProperty("user.dir");
            if (osName.startsWith("windows")) {
                currPath = currPath.replace("\\", "/");
            }
            path = path.replaceFirst("^\\.", currPath);
        }
        return path;
    }

    private static String getPath() {
        String path = "~/.findsmells";
        path = preProcessPath(path);
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs(); 
        }
        return path;
    }

}
