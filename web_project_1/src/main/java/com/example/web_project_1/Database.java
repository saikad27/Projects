package com.example.web_project_1;

import java.sql.*;

public class Database {
    private static String url = "jdbc:mysql://localhost:3306/web_project_1";
    private static String user = "root";
    private static String password = "Saikad@1234";

    public static boolean register(String username,String email,long mobile,String accountPassword){
        try(Connection conn = DriverManager.getConnection(url,user,password);){
            Statement statement = conn.createStatement();
            String query = "INSERT INTO REGISTERED_USERS VALUES('"+username+"','"+email+"','"+mobile+"','"+accountPassword+"')";
            statement.executeUpdate(query);
            return true;
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return false;
    }
    public static boolean emailAlreadyExists(String email){
        try(Connection conn = DriverManager.getConnection(url,user,password);){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM WEB_PROJECT_1.REGISTERED_USERS WHERE REGISTERED_USERS.EMAIL='"+email+"'");
            resultSet.next();
            String existingEmail = resultSet.getString("EMAIL");
            if(existingEmail.equals(email)){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
