/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.implement;

import com.prolan9.inventorybarang.controller.KoneksiDatabase;
import com.prolan9.inventorybarang.entity.KategoriBarang;
import com.prolan9.inventorybarang.service.KategoriBarangInterface;
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
public class KategoriBarangImplement implements KategoriBarangInterface {

    @Override
    public KategoriBarang insert(KategoriBarang kb) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("insert into tb_kategoriBarang values(?,?)");
        st.setString(1, kb.getKdKategori());
        st.setString(2, kb.getNamaKategori());
        
        st.executeUpdate();
        return kb;
    }

    @Override
    public void update(KategoriBarang kb) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("update tb_kategoriBarang set nama_kategoriBarang=? where kd_kategoriBarang=?");
        st.setString(1, kb.getNamaKategori());
        st.setString(2, kb.getKdKategori());
        
        st.executeUpdate();
    }

    @Override
    public void delete(String kdKategori) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("delete from tb_kategoriBarang where kd_kategoriBarang=?");
        st.setString(1, kdKategori);
        st.executeUpdate();
    }

    @Override
    public List<KategoriBarang> getAll() throws SQLException {
        Statement st = KoneksiDatabase.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery("select * from tb_kategoriBarang");
        
        List<KategoriBarang>list=new ArrayList<KategoriBarang>();
        while (rs.next()) {            
            KategoriBarang kb = new KategoriBarang();
            kb.setKdKategori(rs.getString("kd_kategoriBarang"));
            kb.setNamaKategori(rs.getString("nama_kategoriBarang"));
            list.add(kb);
        }
        return list;
    }
    
    
    
}
