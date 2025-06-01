/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DaftarLagu.*;
import View.DaftarLagu.*;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Asus
 */
public class ControllerDaftarLagu {
    ViewDaftarLagu halamanDaftarLagu;
    ViewPutarLagu halamanPutarLagu;
    
    DAODaftarLagu daoDaftarLagu;
    List<ModelDaftarLagu>daftarLagu;
    
    public ControllerDaftarLagu(ViewDaftarLagu halamanDaftarLagu) {
        this.halamanDaftarLagu = halamanDaftarLagu;
        daoDaftarLagu = new DAODaftarLagu();
    }
    
    public ControllerDaftarLagu(ViewPutarLagu halamanPutarLagu) {
        this.halamanPutarLagu = halamanPutarLagu;
        daoDaftarLagu = new DAODaftarLagu();
    }
    
    public void tampilkanDaftarLagu() {
        /*
            mengambil daftar lagu dari tabel database,
            kemudian dipindahkan ke dalam variabel bernama list
        */
        
        daftarLagu = daoDaftarLagu.getAll();
        ModelTabelLagu tabel = new ModelTabelLagu(daftarLagu);
        halamanDaftarLagu.getTabelDaftarLagu().setModel(tabel);
        
        
    }
    
}
