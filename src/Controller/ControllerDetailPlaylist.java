/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DaftarLagu.ModelDaftarLagu;
import Model.DaftarLagu.ModelTabelLagu;
import Model.DetailPlaylist.DetailPlaylistDAO;
import Model.DetailPlaylist.ModelDetailPlaylist;
import View.DetailPlaylist.DetailPlaylist;
import View.DetailPlaylist.PilihPlaylist;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ControllerDetailPlaylist {
    DetailPlaylist halamanDetail;
    PilihPlaylist halamanPilih;
    
    DetailPlaylistDAO daoDetail = new DetailPlaylistDAO();
    List<ModelDaftarLagu>daftarLagu;
    
    public ControllerDetailPlaylist(DetailPlaylist halamanDetail){
        this.halamanDetail = halamanDetail;
    }
    public ControllerDetailPlaylist(PilihPlaylist halamanPilih){
        this.halamanPilih = halamanPilih;
    }
    public void tampilkanDaftarLagu() {
        /*
            mengambil daftar lagu dari tabel database,
            kemudian dipindahkan ke dalam variabel bernama list
        */
        int id_playlist = daoDetail.getIdPlaylist(halamanDetail.getJudulPlaylist().toString());
        daftarLagu = daoDetail.getAll(id_playlist);
        ModelTabelLagu tabel = new ModelTabelLagu(daftarLagu);
        halamanDetail.getLaguTabel().setModel(tabel);
        
        
    }
    
    public void hapusLaguPlaylist(int id){
        try{
            int input = JOptionPane.showConfirmDialog(
                null,
               "Yakin hapus lagu dari playlist",
               "Hapus lagu",
               JOptionPane.YES_NO_OPTION
            );
            if (input == 0) {
                daoDetail.delete(id);
                JOptionPane.showMessageDialog(null, "Berhasil menghapus lagu.");
            }
        }
        catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void tambahLagu(int id, String judul){
        boolean status = false;
        try {
            ModelDetailPlaylist detailBaru = new ModelDetailPlaylist();
             

            if (judul.isEmpty()) {
                throw new Exception("Data tidak boleh kosong!");
            } 
            else{
                int id_playlist = daoDetail.getIdPlaylist(judul);
                int id_lagu = id;
                status = daoDetail.cekTambahPlaylist(id_lagu, id_playlist);
                if (status == true){
                    throw new Exception("Sudah ditambah ke playlist");
                }
                else{
                    detailBaru.setId_lagu(id_lagu);
                    detailBaru.setId_playlist(id_playlist);
                    daoDetail.insert(detailBaru);
                    JOptionPane.showMessageDialog(null, "Playlist berhasil ditambah :D");
                    System.out.println("Berhasil menambahkan playlist baru");
                }
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    
}
