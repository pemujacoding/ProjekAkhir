/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Connector;

/**
 *
 * @author ASUS
 */
public class UserDAO {
    public void insert(ModelUser user) {
       try {
            String query = "INSERT INTO user (nama_user, username, password) VALUES (?, ?, ?);";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, user.getNama());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            statement.close();
       }
       catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
           
       }
    }
     public void update(ModelUser user, String usn) {
       try {
            String query = "UPDATE user SET nama_user =?, username=?, password=? WHERE username=?;";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, user.getNama());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, usn);
            statement.executeUpdate();
            statement.close();
       }
       catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
           
       }
    }
    public void delete(String username) {
       try {
            String query = "DELETE FROM user WHERE username =?;";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, username);
            statement.executeUpdate();
            statement.close();
       }
       catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
           
       }
    }
    
    public boolean cekAkun(String username, String password){
        boolean status = false;
        try {
            String query = "SELECT * FROM user WHERE username=? AND password =?;";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next()){
                status = true;
            }
            resultset.close();
            statement.close();
       }
       catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
           
       }
        return status;
    }
    
    public boolean cekBuatAkun(String username){
        boolean status = false;
        try {
            String query = "SELECT * FROM user WHERE username=?";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next()){
                status = true;
            }
            resultset.close();
            statement.close();
       }
       catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
           
       }
        return status;
    }
    
    public ModelUser getAkun (String username){
        ModelUser user = new ModelUser();
        try{
            String query = "SELECT * FROM user WHERE username = ?";  
            PreparedStatement statement ;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                 user.setId(resultSet.getInt("id_user"));
                 user.setNama(resultSet.getString("nama_user"));
                 user.setUsername(resultSet.getString("username"));
                 user.setPassword(resultSet.getString("password"));
            }
             statement.close();
        }catch (SQLException e) {
            System.out.println("Output Failed: " + e.getLocalizedMessage());
           
       }
        return user;
    }
}
