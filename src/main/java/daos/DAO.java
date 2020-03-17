package daos;

import models.ConnectionFactory;
import models.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DAO implements UserDAO {
    public User findById(Integer id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users_schema.users WHERE id=" + id);
            if(rs.next()){
                return extractUserFromResults(rs);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Set<User> findAll() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            Set users = new HashSet();
            while(rs.next()){
                User user = extractUserFromResults(rs);
                users.add(user);
            }
            return users;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public User getUserByNameAndPassword(String user, String pass) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE user=? AND pass=?");
            ps.setString(1,user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return extractUserFromResults(rs);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public boolean update(User dto) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE users SET name=?, pass=?, age=? WHERE id=?");
            ps.setString(1,dto.getName());
            ps.setString(2,dto.getPass());
            ps.setInt(3, dto.getAge());
            ps.setInt(4, dto.getId());
            int i = ps.executeUpdate();
            if(i==1){
                return true;
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean create(User dto) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users VALUES (NULL, ?, ?, ?)");
            ps.setString(1,dto.getName());
            ps.setString(2, dto.getPass());
            ps.setInt(3, dto.getAge());
            int i = ps.executeUpdate();
            if(i==1){
                return true;
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean delete(User dto) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM users WHERE id=" + dto.getId());
            if(i==1){
                return true;
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    private User extractUserFromResults(ResultSet rs) throws SQLException{
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setPass(rs.getString("password"));
        user.setAge(rs.getInt("age"));
        return user;
    }

}
