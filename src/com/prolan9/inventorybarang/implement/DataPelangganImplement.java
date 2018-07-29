/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.implement;

import com.prolan9.inventorybarang.controller.KoneksiDatabase;
import com.prolan9.inventorybarang.entity.DataPelanggan;
import com.prolan9.inventorybarang.service.DataPelangganInterface;
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
public class DataPelangganImplement implements DataPelangganInterface {

    @Override
    public DataPelanggan insert(DataPelanggan dp) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("insert into tb_datapelanggan values(?,?,?,?)");
        st.setString(1, dp.getKdPelanggan());
        st.setString(2, dp.getNamaPelanggan());
        st.setString(3, dp.getAlamat());
        st.setString(4, dp.getNoTelepon());

        st.executeUpdate();
        return dp;

    }

    @Override
    public void update(DataPelanggan dp) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("update tb_datapelanggan set nama_pelanggan=?, alamat=?, no_telepon=? where kd_pelanggan=?");
        st.setString(1, dp.getNamaPelanggan());
        st.setString(2, dp.getAlamat());
        st.setString(3, dp.getNoTelepon());
        st.setString(4, dp.getKdPelanggan());

        st.executeUpdate();

    }

    @Override
    public void delete(String kdPelanggan) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("delete from tb_datapelanggan where kd_pelanggan=?");
        st.setString(1, kdPelanggan);

        st.executeUpdate();
    }

    @Override
    public List<DataPelanggan> getAll() throws SQLException {
        Statement st = KoneksiDatabase.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery("select * from tb_datapelanggan");

        List<DataPelanggan> list = new ArrayList<DataPelanggan>();
        while (rs.next()) {
            DataPelanggan dp = new DataPelanggan();
            dp.setKdPelanggan(rs.getString("kd_pelanggan"));
            dp.setNamaPelanggan(rs.getString("nama_pelanggan"));
            dp.setAlamat(rs.getString("alamat"));
            dp.setNoTelepon(rs.getString("no_telepon"));
            list.add(dp);
        }
        return list;
        
    }

}
