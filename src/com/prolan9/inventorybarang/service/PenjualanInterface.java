/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.service;

import com.prolan9.inventorybarang.entity.Penjualan;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author fahmikudo
 */
public interface PenjualanInterface {
    
    Penjualan insert(Penjualan pnjl) throws SQLException;
    
    void update(Penjualan pnjl) throws SQLException;
    
    void delete(String noFaktur) throws SQLException;
    
    List<Penjualan> getAll() throws SQLException;
    
    String getStok(String kdBarang) throws SQLException;
    
}
