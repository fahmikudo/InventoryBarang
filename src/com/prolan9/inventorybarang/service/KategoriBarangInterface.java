/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.service;

import com.prolan9.inventorybarang.entity.KategoriBarang;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author fahmikudo
 */
public interface KategoriBarangInterface {
    
    KategoriBarang insert(KategoriBarang kb) throws SQLException;
    
    void update(KategoriBarang kb) throws SQLException;
    
    void delete(String kdKategori) throws SQLException;
    
    List<KategoriBarang> getAll() throws SQLException;
    
    
}
