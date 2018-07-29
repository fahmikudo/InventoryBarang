/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.model;

import com.prolan9.inventorybarang.entity.Pembelian;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author fahmikudo
 */
public class PembelianTableModel extends AbstractTableModel {
    
    private List<Pembelian> list = new ArrayList<>();
    private final String[] columnNames = {"Tanggal Masuk", "No. Faktur", "Nama Supplier", "Alamat", "Nama Barang", "Kategori Barang", "Harga Barang","Satuan","Jumlah Barang"};

    public void setList(List<Pembelian> list) {
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
            return list.get(rowIndex).getTglMasuk();
        case 1:
            return list.get(rowIndex).getNoFaktur();
        case 2:
            return list.get(rowIndex).getDataSupplier().getNamaSupplier();
        case 3:
            return list.get(rowIndex).getDataSupplier().getAlamat();
        case 4:
            return list.get(rowIndex).getDataBarang().getNamaBarang();
        case 5:
            return list.get(rowIndex).getDataBarang().getKategoriBarang();
        case 6:
            return list.get(rowIndex).getDataBarang().getHargaJual();
        case 7:
            return list.get(rowIndex).getDataBarang().getSatuan();
        case 8:
            return list.get(rowIndex).getJumlahBarang();
        default:
            return null;
        }
    }
    
}
