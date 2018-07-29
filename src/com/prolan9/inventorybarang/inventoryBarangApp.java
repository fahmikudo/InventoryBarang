/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang;

import com.alee.laf.WebLookAndFeel;
import com.prolan9.inventorybarang.view.Login;
import com.prolan9.inventorybarang.view.FormKategoriBarang;
import com.prolan9.inventorybarang.view.InventoryBarangMainMenu;
import java.sql.SQLException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author fahmikudo
 */
public class inventoryBarangApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
            UIManager.setLookAndFeel(new WebLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println(ex.getMessage());
        }
        Login lg = new Login();
        lg.setLocationRelativeTo(null);
        lg.setVisible(true);
        
        //InventoryBarangMainMenu mm = new InventoryBarangMainMenu();
        //mm.setVisible(true);
    }
    
}
