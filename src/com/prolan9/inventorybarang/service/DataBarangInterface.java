/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.service;

import com.prolan9.inventorybarang.entity.DataBarang;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author fahmikudo
 */
public interface DataBarangInterface {
    DataBarang insert(DataBarang db) throws SQLException;
    
    void update(DataBarang db) throws SQLException;
    
    void delete(String kodeBarang) throws SQLException;
    
    List<DataBarang> getAll() throws SQLException;
}
