package sk.kosickaakademia.illiaspivak.company.database;

import sk.kosickaakademia.illiaspivak.company.entity.User;
import sk.kosickaakademia.illiaspivak.company.helpclasses.Connect;
import sk.kosickaakademia.illiaspivak.company.log.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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

    public boolean insertNewUser(User user){
        return false;
    }

    public List<User> getFemales(){
        return null;
    }

    public List<User> getMales(){
        return  null;
    }

    public List<User> getUsersByAge(int from, int to){
        return null;
    }

    private List<User> executeSelect(PreparedStatement ps) throws SQLException {
        return null;
    }

    public List<User> getAllUsers(){

        return null;
    }

    public User getUserById(int id){

        return null;
    }

    public boolean changeAge(int id, int newAge){
        return false;
    }

    public List<User> getUser(String pattern){
        return null;
    }
}
