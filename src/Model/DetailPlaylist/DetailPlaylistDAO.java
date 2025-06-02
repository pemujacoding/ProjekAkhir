/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DetailPlaylist;

import Model.Connector;
import Model.DaftarLagu.ModelDaftarLagu;
import Model.DetailPlaylist.ModelDetailPlaylist;
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
public class DetailPlaylistDAO {
    public void insert(ModelDetailPlaylist detailPlaylist) {
       try {
            String query = "INSERT INTO detail_playlist (id_playlist, id_lagu) VALUES (?, ?);";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, detailPlaylist.getId_playlist());
            statement.setInt(2, detailPlaylist.getId_lagu());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        } 
    }
    
    public int getIdPlaylist(String judul){
        int hasil = 0;
        try{
              String query = "SELECT * FROM playlist WHERE judul_playlist = ?";
               PreparedStatement statement;
               statement = Connector.Connect().prepareStatement(query);
               statement.setString(1, judul);
               ResultSet resultSet = statement.executeQuery();
               
            if (resultSet.next()) {
                    hasil = resultSet.getInt("id_playlist");
                } else {
                    System.out.println("No user found with username: " + judul);
                }
        } catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
       }
       return hasil;
    }
      
       public List<ModelDaftarLagu> getAll(int id) {
        List<ModelDaftarLagu> listLagu = null;
        
        try {
            
            listLagu = new ArrayList<>();
            
            String query = "SELECT lagu.id_lagu, lagu.judul_lagu, lagu.artis, lagu.tgl_rilis, lagu.link FROM detail_playlist INNER JOIN lagu ON lagu.id_lagu = detail_playlist.id_lagu INNER JOIN playlist ON detail_playlist.id_playlist = playlist.id_playlist  WHERE detail_playlist.id_playlist=?;";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                
                /* membuat objek bernama "lagu" untuk menyimpan data tiap lagu */
                ModelDaftarLagu lagu = new ModelDaftarLagu();
                
                // memasukkan hasil query ke objek lagu
                lagu.setId(resultSet.getInt("id_lagu"));
                lagu.setJudul(resultSet.getString("judul_lagu"));
                lagu.setArtis(resultSet.getString("artis"));
                lagu.setTanggal(resultSet.getString("tgl_rilis"));
                lagu.setLink(resultSet.getString("link"));
                
                listLagu.add(lagu);
            
            }
            // menutup koneksi
            statement.close();
        } catch(SQLException e) {
            System.out.println("Error:" + e.getLocalizedMessage());
        }
        return listLagu;   
    }
       
    public void delete(int id) {
       try {
            String query = "DELETE FROM detail_playlist WHERE id_lagu =?;";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
       }
       catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
           
       }
    }
    
     public boolean cekTambahPlaylist(int id_lagu, int id_playlist){
        boolean status = false;
        try {
            String query = "SELECT * FROM detail_playlist WHERE id_lagu = ? AND id_playlist = ?";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, id_lagu);
            statement.setInt(2, id_playlist);
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
