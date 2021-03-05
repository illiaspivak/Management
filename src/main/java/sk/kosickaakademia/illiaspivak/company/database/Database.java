package sk.kosickaakademia.illiaspivak.company.database;

import sk.kosickaakademia.illiaspivak.company.entity.User;
import sk.kosickaakademia.illiaspivak.company.helpclasses.Connect;
import sk.kosickaakademia.illiaspivak.company.log.Log;
import sk.kosickaakademia.illiaspivak.company.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Database {
    Util util = new Util();
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

    /**
     * Close connection to the database
     * @param connection
     */
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

    /**
     * Adding a new user to the database
     * @param user
     * @return
     */
    public boolean insertNewUser(User user){
        if(user==null)
            return false;
        String fname = util.normalizeName(user.getFname());
//        String lname = util.normalizeName(user.getLname());
        String lname = user.getLname();
        Connection connection = getConnection();
        String query = "INSERT INTO user (fname, lname, age, gender) VALUES ( ?, ?, ?, ?)";
        if(connection!=null){
            try{
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1,fname);
                ps.setString(2,lname);
                ps.setInt(3,user.getAge());
                ps.setInt(4,user.getGender().getValue());
                int result = ps.executeUpdate();
                closeConnection(connection);
                log.print("New user has been added to the database");
                return result==1;
            }catch(SQLException ex){
                log.error(ex.toString());
            }
        }
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
