/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.model;

import com.prolan9.inventorybarang.entity.StokBarang;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author fahmikudo
 */
public class StokTableModel extends AbstractTableModel {
    
    private List<StokBarang> list = new ArrayList<>();
    private final String[] columnNames = {"Nama Barang","Nama Kategori Barang", "Stok", "Last Update"};

    public void setList(List<StokBarang> list) {
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
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getNoFaktur();
            case 1:
                return list.get(rowIndex).getDataBarang().getKodeBarang();
            case 2:
                return list.get(rowIndex).getDataBarang().getNamaBarang();
            case 3:
                return list.get(rowIndex).getStok();
            case 4:
                return list.get(rowIndex).getTglInput();
            default:
                return null;
        }
    }
    
    
    
}
