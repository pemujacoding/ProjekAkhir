/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Playlist;

import Model.Connector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class PlaylistDAO {
     public void insert(ModelPlaylist playlist) {
       try {
            String query = "INSERT INTO playlist (id_user, judul_playlist) VALUES (?, ?);";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, playlist.getId_user());
            statement.setString(2, playlist.getJudul());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        } 
    }
     
    public void update(ModelPlaylist playlist, String judul) {
       try {
            String query = "UPDATE movie set judul_playlist = ? WHERE judul_playlist = ?";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, playlist.getJudul());
            statement.setString(1, judul);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        } 
    }
    
    public void delete(String judul) {
       try {
            String query = "DELETE FROM playlist WHERE judul_playlist =?;";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, judul);
            statement.executeUpdate();
            statement.close();
       }
       catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
           
       }
    }
    
    public int getId(String usn){
        int hasil = 0;
        try{
              String query = "SELECT * FROM user WHERE username = ?";
               PreparedStatement statement;
               statement = Connector.Connect().prepareStatement(query);
               statement.setString(1, usn);
               ResultSet resultSet = statement.executeQuery();
               
            if (resultSet.next()) {
                    hasil = resultSet.getInt("id_user");
                } else {
                    System.out.println("No user found with username: " + usn);
                }
        } catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
           
       }
       return hasil;
    }
    
    public List<ModelPlaylist> getAll(int id) {
         List<ModelPlaylist> listPlaylist = null;

        try {
            listPlaylist = new ArrayList<>();
            String query = "SELECT id_user,judul_playlist FROM playlist WHERE id_user=?";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ModelPlaylist pl = new ModelPlaylist();
                pl.setId_playlist(resultSet.getInt("id_playlist"));
                pl.setId_user(resultSet.getInt("id_user"));
                pl.setJudul(resultSet.getString("judul_playlist"));
                listPlaylist.add(pl);
            }
            statement.close();
        } catch (SQLException e) {
            // Menampilkan pesan error ketika gagal mengambil data.
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listPlaylist;
    }
    
    public boolean cekTambahPlaylist(String judul){
        boolean status = false;
        try {
            String query = "SELECT * FROM playlist WHERE judul_playlist=?";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, judul);
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
}
