/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.model;

import com.prolan9.inventorybarang.entity.DataSupplier;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author fahmikudo
 */
public class DataSupplierTableModel extends AbstractTableModel {
    
    private List<DataSupplier> list = new ArrayList<>();
    private final String[] columnNames = {"Kode Supplier", "Nama Supplier", "Alamat", "No. Telepon"};

    public void setList(List<DataSupplier> list) {
        this.list = list;
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getKdSupplier();
            case 1:
                return list.get(rowIndex).getNamaSupplier();
            case 2:
                return list.get(rowIndex).getAlamat();
            case 3:
                return list.get(rowIndex).getNoTelepon();
            default:
                return null;
        
        }
    }
    
    
    
}
