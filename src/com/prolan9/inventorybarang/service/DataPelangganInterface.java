/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.service;

import com.prolan9.inventorybarang.entity.DataPelanggan;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author fahmikudo
 */
public interface DataPelangganInterface {
    
    DataPelanggan insert(DataPelanggan dp) throws SQLException;
    
    void update(DataPelanggan dp) throws SQLException;
    
    void delete(String kdPelanggan) throws SQLException;
    
    List<DataPelanggan> getAll() throws SQLException;
    
    
}
