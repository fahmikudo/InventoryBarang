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
public class KategoriBarang {
    
    private String kdKategori;
    private String namaKategori;
    
    
    
    public KategoriBarang() {
    }
    
    public KategoriBarang(String kdKategori) {
        this.kdKategori = kdKategori;
    }
    
    public KategoriBarang(String kdKategori, String namaKategori) {
        this.kdKategori = kdKategori;
        this.namaKategori = namaKategori;
    }

    public String getKdKategori() {
        return kdKategori;
    }

    public void setKdKategori(String kdKategori) {
        this.kdKategori = kdKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    @Override
    public String toString() {
        return namaKategori; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
