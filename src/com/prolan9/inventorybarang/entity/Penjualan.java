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
public class Penjualan {
    
    private String noFaktur;
    private String tglKeluar;
    private long hargaBarang;
    private int jumlahBarang;
    private long totalHarga;
    private DataPelanggan dataPelanggan;
    private DataBarang dataBarang;

    public Penjualan(String noFaktur) {
        this.noFaktur = noFaktur;
        this.dataPelanggan = new DataPelanggan();
        this.dataBarang = new DataBarang();
    }

    public Penjualan(String noFaktur, String tglKeluar, long hargaBarang, int jumlahBarang, long totalHarga, DataPelanggan dataPelanggan, DataBarang dataBarang) {
        this.noFaktur = noFaktur;
        this.tglKeluar = tglKeluar;
        this.hargaBarang = hargaBarang;
        this.jumlahBarang = jumlahBarang;
        this.totalHarga = totalHarga;
        this.dataPelanggan = dataPelanggan;
        this.dataBarang = dataBarang;
    }

    public String getNoFaktur() {
        return noFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
    }

    public String getTglKeluar() {
        return tglKeluar;
    }

    public void setTglKeluar(String tglKeluar) {
        this.tglKeluar = tglKeluar;
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

    public DataPelanggan getDataPelanggan() {
        return dataPelanggan;
    }

    public void setDataPelanggan(DataPelanggan dataPelanggan) {
        this.dataPelanggan = dataPelanggan;
    }

    public DataBarang getDataBarang() {
        return dataBarang;
    }

    public void setDataBarang(DataBarang dataBarang) {
        this.dataBarang = dataBarang;
    }
    
    
    
    
    
    
}
