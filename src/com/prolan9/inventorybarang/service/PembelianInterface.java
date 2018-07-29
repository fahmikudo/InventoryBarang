/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.service;

import com.prolan9.inventorybarang.entity.Pembelian;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author fahmikudo
 */
public interface PembelianInterface {
    
    Pembelian insert(Pembelian pbl) throws SQLException;
    
    void update(Pembelian pbl) throws SQLException;
    
    void delete(String noFaktur) throws SQLException;
    
    List<Pembelian> getAll() throws SQLException;
    
}
