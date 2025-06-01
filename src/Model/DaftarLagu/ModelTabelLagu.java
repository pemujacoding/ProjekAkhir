/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DaftarLagu;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Asus
 */
public class ModelTabelLagu extends AbstractTableModel{

    List<ModelDaftarLagu>daftarLagu;
    
    String kolom[] = {"No", "Judul Lagu", "Artis"};
    
    public ModelTabelLagu(List<ModelDaftarLagu>daftarLagu) {
        this.daftarLagu = daftarLagu;
    }
    
    @Override
    public int getRowCount() {
        return daftarLagu.size();
    }

    @Override
    public int getColumnCount() {
        return kolom.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return kolom[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return daftarLagu.get(rowIndex).getId();
            case 1:
                return daftarLagu.get(rowIndex).getJudul();
            case 2:
                return daftarLagu.get(rowIndex).getArtis();
            default:
                return null;
        }
    }
}
    
