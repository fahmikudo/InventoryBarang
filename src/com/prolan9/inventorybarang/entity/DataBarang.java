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
public class DataBarang {
    
    private String kodeBarang;
    private String namaBarang;
    private String kategoriBarang;
    private long hargaBeli;
    private long hargaJual;
    private String satuan;
    private KategoriBarang kategori; 
    
    public DataBarang() {
        this.kategori = new KategoriBarang();
    }

    public DataBarang(
            String kodeBarang, 
            String namaBarang, 
            String kategoriBarang, 
            long hargaBeli, 
            long hargaJual, 
            String satuan, 
            KategoriBarang kategori) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.kategoriBarang = kategoriBarang;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
        this.satuan = satuan;
        this.kategori = kategori;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getKategoriBarang() {
        return kategoriBarang;
    }

    public void setKategoriBarang(String kategoriBarang) {
        this.kategoriBarang = kategoriBarang;
    }

    public long getHargaBeli() {
        return hargaBeli;
    }

    public void setHargaBeli(long hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public long getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(long hargaJual) {
        this.hargaJual = hargaJual;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public KategoriBarang getKategori() {
        return kategori;
    }

    public void setKategori(KategoriBarang kategori) {
        this.kategori = kategori;
    }

    @Override
    public String toString() {
        return "DataBarang{" + "hargaBeli=" + hargaBeli + ", hargaJual=" + hargaJual + '}';
    }

    
    
    
}
