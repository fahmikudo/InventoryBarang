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
public class DataSupplier {
    
    private String kdSupplier;
    private String namaSupplier;
    private String alamat;
    private String noTelepon;

    public DataSupplier() {
    }
    
    public DataSupplier(String kdSupplier) {
        this.kdSupplier = kdSupplier;
        
    }
    
    public DataSupplier(String kdSupplier, String namaSupplier, String alamat, String noTelepon) {
        this.kdSupplier = kdSupplier;
        this.namaSupplier = namaSupplier;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
    }

    
    public String getKdSupplier() {
        return kdSupplier;
    }

    public void setKdSupplier(String kdSupplier) {
        this.kdSupplier = kdSupplier;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
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

    @Override
    public String toString() {
        return "DataSupplier{" + "kdSupplier=" + kdSupplier + ", namaSupplier=" + namaSupplier + ", alamat=" + alamat + '}';
        //return namaSupplier;
    }

    
    
    
    
    
}
