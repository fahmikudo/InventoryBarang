/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.implement;

import com.prolan9.inventorybarang.controller.KoneksiDatabase;
import com.prolan9.inventorybarang.entity.DataBarang;
import com.prolan9.inventorybarang.entity.Pembelian;
import com.prolan9.inventorybarang.service.PembelianInterface;
import java.sql.Date;
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
public class PembelianImplement implements PembelianInterface {

    @Override
    public Pembelian insert(Pembelian pbl) throws SQLException {
        
        
        
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("insert into tb_pembelian values(?,?,?,?,?,?)");
        st.setString(1, pbl.getNoFaktur());
        st.setString(2, pbl.getTglMasuk());
        st.setLong(3, pbl.getHargaBarang());
        st.setInt(4, pbl.getJumlahBarang());
        st.setString(5, pbl.getDataSupplier().getKdSupplier());
        st.setString(6, pbl.getDataBarang().getKodeBarang());
        int status = st.executeUpdate();
        
        
        if (status > 0) {
            updateStok(pbl);
        }

        return pbl;
    }

    @Override
    public void update(Pembelian pbl) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("update tb_pembelian set tgl_masuk=?, harga_barang=?, jumlah_barang=?, kd_supplier=?, kd_barang=? where no_faktur=?");
        st.setString(1, pbl.getTglMasuk());
        st.setLong(2, pbl.getHargaBarang());
        st.setInt(3, pbl.getJumlahBarang());
        st.setString(4, pbl.getDataSupplier().getKdSupplier());
        st.setString(5, pbl.getDataBarang().getKodeBarang());
        st.setString(6, pbl.getNoFaktur());
        int status = st.executeUpdate();
        
        if (status > 0) {
            updateStok(pbl);
        }
        
        
    }

    @Override
    public void delete(String noFaktur) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("delete from tb_pembelian where no_faktur=?");
        st.setString(1, noFaktur);
        int status = st.executeUpdate();
        
        if (status > 0) {
            deleteStok(noFaktur);
        }
    }

    @Override
    public List<Pembelian> getAll() throws SQLException {
        Statement st = KoneksiDatabase.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery("SELECT * , harga_beli * jumlah_barang total_harga\n" +
                                        "FROM tb_pembelian\n" +
                                        "INNER JOIN tb_databarang USING (kd_barang)\n" +
                                        "INNER JOIN tb_datasupplier USING (kd_supplier)\n" +
                                        "INNER JOIN tb_kategoribarang USING (kd_kategoriBarang);");
        
        List<Pembelian> list=new ArrayList<Pembelian>();
        while (rs.next()) {            
            Pembelian pbl = new Pembelian(rs.getString("no_faktur"));
            pbl.setTglMasuk(rs.getString("tgl_masuk"));
            pbl.getDataBarang().setNamaBarang(rs.getString("nama_barang"));
            pbl.getDataBarang().getKategori().setNamaKategori(rs.getString("nama_kategoriBarang"));
            pbl.setHargaBarang(rs.getInt("harga_barang"));
            pbl.getDataBarang().setSatuan(rs.getString("satuan"));
            pbl.setJumlahBarang(rs.getInt("jumlah_barang"));
            pbl.setTotalHarga(rs.getLong("total_harga"));
            pbl.getDataSupplier().setKdSupplier(rs.getString("kd_supplier"));
            pbl.getDataSupplier().setNamaSupplier(rs.getString("nama_supplier"));
            pbl.getDataSupplier().setAlamat(rs.getString("alamat"));
            
            list.add(pbl);
        }
        return list;
    }
    
    public void updateStok(Pembelian pbl) throws SQLException{
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("replace into tb_stokbarang values(?,?,?, now())");
        st.setString(1, pbl.getNoFaktur());
        st.setString(2, pbl.getDataBarang().getKodeBarang());
        st.setInt(3, pbl.getJumlahBarang());
        st.executeUpdate();
    
    }
    
    public void deleteStok(String noFaktur) throws SQLException{
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("delete from tb_stokbarang where no_faktur=?");
        st.setString(1, noFaktur);
        
        st.executeUpdate();
    
    }
    
}
