/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.model;

import com.prolan9.inventorybarang.entity.DataBarang;
import com.prolan9.inventorybarang.entity.KategoriBarang;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author fahmikudo
 */
public class DataBarangTableModel extends AbstractTableModel {
    
    private List<DataBarang> list = new ArrayList<>();
    private final String[] columnNames = {"Kode Barang", "Nama Barang", "Kategori Barang", "Harga Beli", "Harga Jual", "Satuan"};

    public void setList(List<DataBarang> list) {
        this.list = list;
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column]; //To change body of generated methods, choose Tools | Templates.
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
            return list.get(rowIndex).getKodeBarang();
        case 1:
            return list.get(rowIndex).getNamaBarang();
        case 2:
            return list.get(rowIndex).getKategori().getNamaKategori();
        case 3:
            return list.get(rowIndex).getHargaBeli();
        case 4:
            return list.get(rowIndex).getHargaJual();
        case 5:
            return list.get(rowIndex).getSatuan();
        default:
            return null;
        }
    }
    
}
