/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.implement;

import com.prolan9.inventorybarang.controller.KoneksiDatabase;
import com.prolan9.inventorybarang.entity.DataBarang;
import com.prolan9.inventorybarang.entity.KategoriBarang;
import com.prolan9.inventorybarang.service.DataBarangInterface;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fahmikudo
 */
public class DataBarangImplement implements DataBarangInterface {
    
    @Override
    public DataBarang insert(DataBarang db) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("insert into tb_dataBarang values(?,?,?,?,?,?)");
        st.setString(1, db.getKodeBarang());
        st.setString(2, db.getNamaBarang());
        st.setLong(3, db.getHargaBeli());
        st.setLong(4, db.getHargaJual());
        st.setString(5, db.getSatuan());
        st.setString(6, db.getKategoriBarang());
        st.executeUpdate();
      return db;
    }

    @Override
    public void update(DataBarang db) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("update tb_databarang set nama_barang=?, harga_beli=?, harga_jual=?, satuan=?, kd_kategoriBarang=? where kd_barang=?");
        st.setString(1, db.getNamaBarang());
        st.setLong(2, (long) db.getHargaBeli());
        st.setLong(3, (long) db.getHargaJual());
        st.setString(4, db.getSatuan());
        st.setString(5, db.getKategoriBarang());
        st.setString(6, db.getKodeBarang());
        st.executeUpdate();
    }

    @Override
    public void delete(String kodeBarang) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("delete from tb_databarang where kd_barang=?");
        st.setString(1, kodeBarang);
        st.executeUpdate();
    }

    @Override
    public List<DataBarang> getAll() throws SQLException {
        Statement st = KoneksiDatabase.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery("select * from tb_databarang inner join tb_kategoribarang on tb_databarang.kd_kategoriBarang = tb_kategoribarang.kd_kategoriBarang");
        
        List<DataBarang> list=new ArrayList<DataBarang>();
        while (rs.next()) {            
            DataBarang db = new DataBarang();
            db.setKodeBarang(rs.getString("kd_barang"));
            db.setNamaBarang(rs.getString("nama_barang"));
            db.setHargaBeli(rs.getInt("harga_beli"));
            db.setHargaJual(rs.getInt("harga_jual"));
            db.setSatuan(rs.getString("satuan"));
            db.setKategoriBarang(rs.getString("kd_kategoriBarang"));
            db.setKategori(new KategoriBarang(rs.getString("kd_kategoriBarang"),rs.getString("nama_kategoriBarang")));
            list.add(db);
        }
        return list;
    }
    
}
