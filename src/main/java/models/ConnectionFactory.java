package models;

import com.mysql.jdbc.Driver;
import daos.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://localhost:3306/users_schema?autoReconnect=true&useSSL=false";
    public static final String USER = "root";
    public static final String PASS = "sanosuke";

    public static Connection getConnection(){
        try{
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL,USER,PASS);
        } catch(SQLException ex){
            throw new RuntimeException("db connection error");
        }
    }

    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection();
        DAO dao = new DAO();
        User bill = new User(2,"bill", "qwerty", 29);
        dao.create(bill);
    }


}
