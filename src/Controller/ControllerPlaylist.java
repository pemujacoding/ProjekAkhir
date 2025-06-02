/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Playlist.ModelPlaylist;
import Model.Playlist.ModelTablePlaylist;
import Model.Playlist.PlaylistDAO;
import View.DaftarLagu.Menu;
import View.DetailPlaylist.PilihPlaylist;
import View.Playlist.*;
import View.User.LogIn;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ControllerPlaylist {
    Playlist halamanView;
    TambahPlaylist halamanTambah;
    EditPlaylist halamanEdit;
    PilihPlaylist halamanPilih;
    PlaylistDAO daoPlaylist = new PlaylistDAO();
    
    List<ModelPlaylist>daftarPlaylist;
    
    public ControllerPlaylist(Playlist halamanView){
        this.halamanView = halamanView ;
    }
    public ControllerPlaylist(TambahPlaylist halamanTambah){
        this.halamanTambah = halamanTambah ;
    }
    public ControllerPlaylist(EditPlaylist halamanEdit){
        this.halamanEdit = halamanEdit ;
    }
    public ControllerPlaylist(PilihPlaylist halamanPilih){
        this.halamanPilih = halamanPilih ;
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
    public void tampilkanDaftarPlaylist2(String usn) {
        
        int id_user = daoPlaylist.getId(usn);
        daftarPlaylist = daoPlaylist.getAll(id_user);
        ModelTablePlaylist table = new ModelTablePlaylist(daftarPlaylist);
        halamanPilih.getPlaylistTabel().setModel(table);
    }
    public void UpdatePlaylist(String ply){
        boolean status = false;
        try {
            ModelPlaylist playlistEdit = new ModelPlaylist();
            
            String playlist = halamanEdit.getJudulInput();

            if ("".equals(playlist)) {
                throw new Exception("Data tidak boleh kosong!");
            }
            else{
                playlistEdit.setJudul(playlist);
                status = daoPlaylist.cekTambahPlaylist(playlist);
                if (!playlist.equals(ply) && status == true){
                        throw new Exception("Judul sudah terpakai");
                }
                else{
                    daoPlaylist.update(playlistEdit,ply);
                    JOptionPane.showMessageDialog(null, "Playlist berhasil diedit :D");
                }
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
      public void HapusPlaylist(String ply) {
        try{
            int input = JOptionPane.showConfirmDialog(
                null,
               "Yakin hapus playlist",
               "Hapus Playlist",
               JOptionPane.YES_NO_OPTION
            );
            if (input == 0) {
                daoPlaylist.delete(ply);
                JOptionPane.showMessageDialog(null, "Berhasil menghapus playlist.");
            }
        }
        catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        
    }
}
