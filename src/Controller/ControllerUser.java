/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import View.User.*;
import Model.User.*;
import View.DaftarLagu.Menu;
import java.util.List;
import javax.swing.*;
/**
 *
 * @author ASUS
 */
public class ControllerUser {
    LogIn halamanLogin;
    SignUp halamanSignup;
    ViewAkun halamanView;
    UserDAO daoUser ;
    
    public ControllerUser (LogIn halamanLogin){
        this.halamanLogin = halamanLogin;
        daoUser = new UserDAO();
    }
    public ControllerUser (SignUp halamanSignup){
        this.halamanSignup = halamanSignup;
        daoUser = new UserDAO();
    }
    
    public ControllerUser (ViewAkun halamanView){
        this.halamanView = halamanView;
        daoUser = new UserDAO();
    }
    public boolean loginUser(String usn, String pass){
        boolean status =  false;
        try{

            if (usn.isEmpty() || pass.isEmpty()) {
                throw new Exception("Data tidak boleh kosong!");
            }
            else{
                status = daoUser.cekAkun(usn, pass);
                if (status == true && usn != null){
                    JOptionPane.showMessageDialog(null,"Berhasil Login");
                    halamanLogin.dispose();
                    new Menu(usn).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Password atau username salah");
                }
            }
            
        }
        catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return status;
    }
    
    public void ViewDataAkun(String username){
        try{
            ModelUser dataAkun = new ModelUser();
            dataAkun = daoUser.getAkun(username);
            halamanView.setNamaInput(dataAkun.getNama());
            halamanView.setPasswordInput(dataAkun.getPassword());
        }catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

    }
    
    public void insertUser(){
        boolean status = false;
        try {
            ModelUser userBaru = new ModelUser();
            
            String nama = halamanSignup.getNamaInput();
            String username = halamanSignup.getUsernameInput();
            String password = halamanSignup.getPasswordInput();

            if ("".equals(nama) || "".equals(username) || "".equals(password)) {
                throw new Exception("Data tidak boleh kosong!");
            } 
            else{
                userBaru.setNama(nama);
                userBaru.setUsername(username);
                userBaru.setPassword(password);
                 status = daoUser.cekBuatAkun(username);
                if (password.length() != 8){
                    throw new Exception("Password harus 8 karakter");
                }
                else if (status == true){
                    throw new Exception("Username sudah terpakai");
                }
                else{
                    daoUser.insert(userBaru);
                    JOptionPane.showMessageDialog(null, "Akun berhasil dibuat :D");
                    halamanSignup.dispose();
                    new LogIn().setVisible(true);
                }
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    public void UpdateUser(String usn){
        boolean status = false;
        try {
            ModelUser userEdit = new ModelUser();
            
            String nama = halamanView.getNamaInput();
            String username = halamanView.getUsernameInput();
            String password = halamanView.getPasswordInput();

            if ("".equals(nama) || "".equals(username) || "".equals(password)) {
                throw new Exception("Data tidak boleh kosong!");
            }
            else{
                userEdit.setNama(nama);
                userEdit.setUsername(username);
                userEdit.setPassword(password);
                status = daoUser.cekBuatAkun(username);
                if (!username.equals(usn) && status == true){
                        throw new Exception("Username sudah terpakai");
                }
                else{
                    daoUser.update(userEdit,usn);
                    JOptionPane.showMessageDialog(null, "Akun berhasil diedit :D");
                    halamanView.dispose();
                    new Menu(username).setVisible(true);
                }
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
      public void HapusUser(String username) {
        int input = JOptionPane.showConfirmDialog(
                null,
                "Yakin hapus akun",
                "Hapus Akun",
                JOptionPane.YES_NO_OPTION
        );
        if (input == 0) {
            daoUser.delete(username);
            JOptionPane.showMessageDialog(null, "Berhasil menghapus akun.");
            halamanView.dispose();
            new LogIn().setVisible(true);
        }
    }
}
