/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.entity;

/**
 *
 * @author fahmikudo
 */
public class DataPelanggan {
    
    private String kdPelanggan;
    private String namaPelanggan;
    private String alamat;
    private String noTelepon;

    public DataPelanggan() {
    }
    
    public DataPelanggan(String kdPelanggan) {
        this.kdPelanggan = kdPelanggan;
        
    }
    
    public DataPelanggan(String kdPelanggan, String namaPelanggan, String alamat, String noTelepon) {
        this.kdPelanggan = kdPelanggan;
        this.namaPelanggan = namaPelanggan;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
    }

    public String getKdPelanggan() {
        return kdPelanggan;
    }

    public void setKdPelanggan(String kdPelanggan) {
        this.kdPelanggan = kdPelanggan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }
    
    
    
}
