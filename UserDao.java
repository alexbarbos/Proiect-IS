package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;
import util.DbUtil;

public class UserDao {

    private Connection connection;
    //PreparedStatement preparedStatement;

    public UserDao() {
        connection = DbUtil.getConnection();
    }

    public void addUser(User user) throws IOException {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into users (userid, userName, password) values (?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setInt(1, user.getUserid());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User loginUser(User user) throws IOException {
    	User user1 = new User();
    	try {
            String sqlQuery1 = "select * from users where userName=? and password=?";
        	PreparedStatement preparedStatement = connection
                    .prepareStatement(sqlQuery1);
        	// Parameters start with 1
        	preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
        	// execute select SQL statement
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	String userName = rs.getString("userName");
            	String password = rs.getString("password");            	
      
            	user1.setUserName(userName);
                user1.setPassword(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }   
   
		return user1;
    }
    
    public void deleteUser(int userId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from users where userid=?");
            // Parameters start with 1
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set userName=?, password=?" +
                            "where userid=?");
            // Parameters start with 1
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getUserid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                User user = new User();
                user.setUserid(rs.getInt("userid"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserById(int userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from users where userid=?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUserid(rs.getInt("userid"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
