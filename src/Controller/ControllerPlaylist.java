/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Playlist.ModelPlaylist;
import Model.Playlist.ModelTablePlaylist;
import Model.Playlist.PlaylistDAO;
import View.Playlist.*;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ControllerPlaylist {
    Playlist halamanView;
    TambahPlaylist halamanTambah;
    PlaylistDAO daoPlaylist = new PlaylistDAO();
    
    List<ModelPlaylist>daftarPlaylist;
    
    public ControllerPlaylist(Playlist halamanView){
        this.halamanView = halamanView ;
    }
    public ControllerPlaylist(TambahPlaylist halamanTambah){
        this.halamanTambah = halamanTambah ;
    }
    public void insertPlaylist(String usn){
        boolean status = false;
        try {
            ModelPlaylist playlistBaru = new ModelPlaylist();
             
            String judul = halamanTambah.getJudulInput();

            if (judul.isEmpty()) {
                throw new Exception("Data tidak boleh kosong!");
            } 
            else{
                playlistBaru.setJudul(judul);
                status = daoPlaylist.cekTambahPlaylist(judul);
                if (status == true){
                    throw new Exception("Judul playlist sudah ada");
                }
                else{
                    int id_user = daoPlaylist.getId(usn);
                    playlistBaru.setJudul(judul);
                    playlistBaru.setId_user(id_user);
                    daoPlaylist.insert(playlistBaru);
                    JOptionPane.showMessageDialog(null, "Playlist berhasil ditambah :D");
//                    halamanView.dispose();

                    System.out.println("Berhasil menambahkan playlist baru");
                }
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void tampilkanDaftarPlaylist(String usn) {
        
        int id_user = daoPlaylist.getId(usn);
        daftarPlaylist = daoPlaylist.getAll(id_user);
        ModelTablePlaylist table = new ModelTablePlaylist(daftarPlaylist);
        halamanView.getPlaylistTabel().setModel(table);
    }
}
