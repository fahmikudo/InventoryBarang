/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.service;

import com.prolan9.inventorybarang.entity.DataSupplier;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author fahmikudo
 */
public interface DataSupplierInterface {
    
    DataSupplier insert(DataSupplier ds) throws SQLException;
    
    void update(DataSupplier ds) throws SQLException;
    
    void delete(String kdSupplier) throws SQLException;
    
    List<DataSupplier> getAll() throws SQLException;
    
    
}
