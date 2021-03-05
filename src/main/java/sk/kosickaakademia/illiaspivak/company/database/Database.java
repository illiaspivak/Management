package sk.kosickaakademia.illiaspivak.company.database;

import sk.kosickaakademia.illiaspivak.company.entity.User;
import sk.kosickaakademia.illiaspivak.company.helpclasses.Connect;
import sk.kosickaakademia.illiaspivak.company.log.Log;
import sk.kosickaakademia.illiaspivak.company.util.Util;

import java.sql.*;
import java.util.ArrayList;
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
        String lname = util.normalizeName(user.getLname());
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

    private List<User> executeSelect(PreparedStatement ps) throws SQLException {
        ResultSet rs =  ps.executeQuery();
        List<User> list = new ArrayList<>();
        while(rs.next()){
            String fname = rs.getString("fname");
            String lname = rs.getString("lname");
            int age = rs.getInt("age");
            int id = rs.getInt("id");
            int gender = rs.getInt("gender");
            User user = new User(id,fname,lname,age,gender);
            list.add(user);
        }
        return list;
    }

    /**
     * Select women from the database
     * @return
     */
    public List<User> getFemales(){
        List<User> list = new ArrayList<>();
        String query = "SELECT * FROM user WHERE gender = 1";
        try {
            Connection connection = getConnection();
            if(connection == null){
                log.error("No connection");
                return null;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            list = executeSelect(ps);
            closeConnection(connection);
            log.print("Selected women from the database");
            return list;
        }catch(Exception ex){
            log.error(ex.toString());
        }
        return null;
    }

    /**
     * Select men from the database
     * @return
     */
    public List<User> getMales(){
        List<User> list = new ArrayList<>();
        String query = "SELECT * FROM user WHERE gender = 0";
        try {
            Connection connection = getConnection();
            if(connection == null){
                log.error("No connection");
                return null;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            list = executeSelect(ps);
            closeConnection(connection);
            log.print("Selected men from the database");
            return list;
        }catch(Exception ex){
            log.error(ex.toString());
        }
        return null;
    }

    /**
     * Select users from the age range
     * @param from
     * @param to
     * @return
     */
    public List<User> getUsersByAge(int from, int to){
        if(from<to){
            List<User> list = new ArrayList<>();
            String query = "SELECT * FROM user WHERE age >= ? AND age <= ?";
            try {
                Connection connection = getConnection();
                if(connection == null){
                    log.error("No connection");
                    return null;
                }
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1,from);
                ps.setInt(2, to);
                list = executeSelect(ps);
                closeConnection(connection);
                log.print("Users of this age range are selected from the database");
                return list;
            }catch(Exception ex){
                log.error(ex.toString());
            }
        }
        return null;
    }

    public List<User> getAllUsers(){
        List<User> list = new ArrayList<>();
        String query = "SELECT * FROM user";
        try {
            Connection connection = getConnection();
            if(connection ==null){
                log.error("No connection");
                return null;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                int age = rs.getInt("age");
                int id = rs.getInt("id");
                int gender = rs.getInt("gender");
                User user=new User(id,fname,lname,age,gender);
                list.add(user);
            }
            connection.close();
            return list;
        }catch(Exception ex){
            log.error(ex.toString());
        }
        return null;
    }

    public User getUserById(int number){
        String query = "SELECT * FROM user WHERE id = ?";
        try {
            Connection connection = getConnection();
            if(connection == null){
                log.error("No connection");
                return null;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,number);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                int age = rs.getInt("age");
                int id = rs.getInt("id");
                int gender = rs.getInt("gender");
                User user = new User(id,fname,lname,age,gender);
                connection.close();
                return user;
            }
        }catch(Exception ex){
            log.error(ex.toString());
        }
        return null;
    }

    public boolean changeAge(int id, int newAge){
        return false;
    }

    public List<User> getUser(String pattern){
        return null;
    }
}
