/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author fahmikudo
 */
public class KoneksiDatabase {
    private static Connection koneksi;

    
    public static Connection getKoneksi(){
        //cek koneksi
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/db10115197inventorybarang";
                String user = "root";
                String password = "";
                
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                
                koneksi = DriverManager.getConnection(url, user, password);
                
            } catch (SQLException e) {
                System.out.println("Error Membuat Koneksi");
            }
        }
        
        return koneksi;
    }

    
}
