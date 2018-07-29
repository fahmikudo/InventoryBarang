/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.implement;

import com.prolan9.inventorybarang.controller.KoneksiDatabase;
import com.prolan9.inventorybarang.entity.DataSupplier;
import com.prolan9.inventorybarang.service.DataSupplierInterface;
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
public class DataSupplierImplement implements DataSupplierInterface {

    @Override
    public DataSupplier insert(DataSupplier ds) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("insert into tb_datasupplier values(?,?,?,?)");
        st.setString(1, ds.getKdSupplier());
        st.setString(2, ds.getNamaSupplier());
        st.setString(3, ds.getAlamat());
        st.setString(4, ds.getNoTelepon());
        
        st.executeUpdate();
        return ds;
    }

    @Override
    public void update(DataSupplier ds) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("update tb_datasupplier set nama_supplier=?, alamat=?, no_telepon=? where kd_supplier=?");
        st.setString(1, ds.getNamaSupplier());
        st.setString(2, ds.getAlamat());
        st.setString(3, ds.getNoTelepon());
        st.setString(4, ds.getKdSupplier());
        
        st.executeUpdate();
    }

    @Override
    public void delete(String kdSupplier) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("delete from tb_datasupplier where kd_supplier=?");
        st.setString(1, kdSupplier);
        
        st.executeUpdate();
    }

    @Override
    public List<DataSupplier> getAll() throws SQLException {
        Statement st = KoneksiDatabase.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery("select * from tb_datasupplier");
        
        List<DataSupplier> list = new ArrayList<DataSupplier>();
        while (rs.next()) {            
            DataSupplier ds = new DataSupplier();
            ds.setKdSupplier(rs.getString("kd_supplier"));
            ds.setNamaSupplier(rs.getString("nama_supplier"));
            ds.setAlamat(rs.getString("alamat"));
            ds.setNoTelepon(rs.getString("no_telepon"));
            list.add(ds);
        }
        return list;
        
    }
    
}
