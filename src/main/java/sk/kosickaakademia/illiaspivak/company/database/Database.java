package sk.kosickaakademia.illiaspivak.company.database;

import sk.kosickaakademia.illiaspivak.company.helpclasses.Connect;
import sk.kosickaakademia.illiaspivak.company.log.Log;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {


    Log log = new Log();
    /**
     * Connecting to the database
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(Connect.getUrl(), Connect.getUsername(), Connect.getPassword());
            log.print("Success");
            return connection;
        }catch (Exception ex) {
            log.error(ex.toString());
        }
        return null;
    }

    public void closeConnection(Connection connection)  {
        if(connection!=null) {
            try {
                connection.close();
                log.print("Connection closed!");
            }catch(SQLException e){
                log.error(e.toString());
            }
        }
    }
}
