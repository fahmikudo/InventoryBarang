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
public class StokBarang {
    
    private String noFaktur;
    private DataBarang dataBarang;
    private int stok;
    private String tglInput;

    public StokBarang() {
        this.dataBarang = new DataBarang();
    }
    
    
    public StokBarang(String noFaktur) {
        this.noFaktur = noFaktur;
        this.dataBarang = new DataBarang();
    }
    
    public StokBarang(String noFaktur, DataBarang dataBarang, int stok, String tglInput) {
        this.noFaktur = noFaktur;
        this.dataBarang = dataBarang;
        this.stok = stok;
        this.tglInput = tglInput;
    }

    public String getNoFaktur() {
        return noFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
    }

    public DataBarang getDataBarang() {
        return dataBarang;
    }

    public void setDataBarang(DataBarang dataBarang) {
        this.dataBarang = dataBarang;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getTglInput() {
        return tglInput;
    }

    public void setTglInput(String tglInput) {
        this.tglInput = tglInput;
    }

    
    
    
    
    
    
    
}
