
package datos;
import java.sql.*;
import java.util.*;
import java.io.FileInputStream;

public class Connectionn {
    private static String JDBC_DRIVER;
    private static String JDBC_URL;
    private static String JDBC_USER;
    private static String JDBC_PASS;
    private static Driver driver = null;
    private static String JDBC_FILE_NAME = "maven";


public static void loadProperties(String file){
    /*Properties prop = new Properties();
    ResourceBundle bundle = ResourceBundle.getBundle("resources\\"+file+".properties");
    Enumeration e = bundle.getKeys();
    String key = null;
    while(e.hasMoreElements()){
        key = (String) e.nextElement();
        prop.put(key,bundle.getObject(key));
    }*/
    JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver" ;
    JDBC_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    JDBC_USER = "sys as sysdba";
    JDBC_PASS = "welcome1";
}

public static synchronized Connection getConnection()
        throws SQLException
{
    if (driver == null){
        try{
            loadProperties(JDBC_FILE_NAME);
            Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
            driver = (Driver) jdbcDriverClass.newInstance();
            DriverManager.registerDriver(driver);
        } catch(Exception e){
            System.out.println("Fallo en cargar el driver JDBC");
            e.printStackTrace();
        }
    }
    return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);
}
    //Cierre del resultSet
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    //Cierre del PrepareStatement
    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    //Cierre de la conexion
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
