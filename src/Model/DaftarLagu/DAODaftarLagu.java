/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DaftarLagu;

import Model.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class DAODaftarLagu {
    public List<ModelDaftarLagu> getAll() {
        List<ModelDaftarLagu> listLagu = null;
        
        try {
            
            listLagu = new ArrayList<>();
            
            Statement statement = Connector.Connect().createStatement();
            
            String query = "SELECT * FROM lagu;";
            ResultSet resultSet = statement.executeQuery(query);
            
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
}
