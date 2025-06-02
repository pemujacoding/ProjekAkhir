/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Playlist;

import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author ASUS
 */
public class ModelTablePlaylist extends AbstractTableModel {
    List<ModelPlaylist> daftarPlaylist;

    String kolom[] = {"Judul"};
    
    public ModelTablePlaylist (List<ModelPlaylist> daftarPlaylist) {
        this.daftarPlaylist = daftarPlaylist;
    }
    @Override
    public int getRowCount() {
        return daftarPlaylist.size();
    }

    @Override
    public int getColumnCount() {
        return kolom.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 :
                return daftarPlaylist.get(rowIndex).getJudul();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return kolom[columnIndex];
    }
}
