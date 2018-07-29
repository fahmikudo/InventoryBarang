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
public class Pembelian {
    
    private String noFaktur;
    private String tglMasuk;
    private long hargaBarang;
    private int jumlahBarang;
    private long totalHarga;
    private DataSupplier dataSupplier;
    private DataBarang dataBarang;

    public Pembelian(String noFaktur) {
        this.noFaktur = noFaktur;
        this.dataSupplier = new DataSupplier();
        this.dataBarang = new DataBarang();
        
    }

    public Pembelian(String noFaktur, String tglMasuk, long hargaBarang, int jumlahBarang, long totalHarga, DataSupplier dataSupplier, DataBarang dataBarang) {
        this.noFaktur = noFaktur;
        this.tglMasuk = tglMasuk;
        this.hargaBarang = hargaBarang;
        this.jumlahBarang = jumlahBarang;
        this.totalHarga = totalHarga;
        this.dataSupplier = dataSupplier;
        this.dataBarang = dataBarang;
    }

    public String getNoFaktur() {
        return noFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
    }

    public String getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(String tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public long getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(long hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public int getJumlahBarang() {
        return jumlahBarang;
    }

    public void setJumlahBarang(int jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }

    public long getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(long totalHarga) {
        this.totalHarga = totalHarga;
    }

    public DataSupplier getDataSupplier() {
        return dataSupplier;
    }

    public void setDataSupplier(DataSupplier dataSupplier) {
        this.dataSupplier = dataSupplier;
    }

    public DataBarang getDataBarang() {
        return dataBarang;
    }

    public void setDataBarang(DataBarang dataBarang) {
        this.dataBarang = dataBarang;
    }

    
    

    @Override
    public String toString() {
        return "Pembelian{" + "jumlahBarang=" + jumlahBarang + ", dataSupplier=" + dataSupplier + ", dataBarang=" + dataBarang + '}';
    }

    

    

    
    
}
