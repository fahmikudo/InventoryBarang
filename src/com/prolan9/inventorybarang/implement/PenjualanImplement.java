/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.implement;

import com.prolan9.inventorybarang.controller.KoneksiDatabase;
import com.prolan9.inventorybarang.entity.Penjualan;
import com.prolan9.inventorybarang.service.PenjualanInterface;
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
public class PenjualanImplement implements PenjualanInterface {

    @Override
    public Penjualan insert(Penjualan pnjl) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("insert into tb_penjualan values(?,?,?,?,?,?)");
       
        st.setString(1, pnjl.getNoFaktur());
        st.setString(2, pnjl.getTglKeluar());
        st.setLong(3, pnjl.getHargaBarang());
        st.setInt(4, pnjl.getJumlahBarang());
        st.setString(5, pnjl.getDataPelanggan().getKdPelanggan());
        st.setString(6, pnjl.getDataBarang().getKodeBarang());
        int status = st.executeUpdate();
        if (status > 0) {
            updateStok(pnjl);
        }
        return pnjl;
        
    }

    @Override
    public void update(Penjualan pnjl) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("update tb_penjualan set tgl_keluar=?, harga_barang=?, jumlah_barang=?, kd_pelanggan=?, kd_barang=? where no_faktur=?");
       
        st.setString(1, pnjl.getTglKeluar());
        st.setLong(2, pnjl.getHargaBarang());
        st.setInt(3, pnjl.getJumlahBarang());
        st.setString(4, pnjl.getDataPelanggan().getKdPelanggan());
        st.setString(5, pnjl.getDataBarang().getKodeBarang());
        st.setString(6, pnjl.getNoFaktur());
        int status = st.executeUpdate();
        if (status > 0) {
            updateStok(pnjl);
        }

    }

    @Override
    public void delete(String noFaktur) throws SQLException {
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("delete from tb_penjualan where no_faktur=?");
        st.setString(1, noFaktur);
        int status = st.executeUpdate();
        if (status > 0) {
            deleteStok(noFaktur);
        }
    }

    @Override
    public List<Penjualan> getAll() throws SQLException {
        Statement st = KoneksiDatabase.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery("SELECT * , harga_beli * jumlah_barang total_harga\n" +
                                        "FROM tb_penjualan\n" +
                                        "INNER JOIN tb_databarang USING (kd_barang)\n" +
                                        "INNER JOIN tb_datapelanggan USING (kd_pelanggan)\n" +
                                        "INNER JOIN tb_kategoribarang USING (kd_kategoriBarang);");
        
        List<Penjualan> list=new ArrayList<Penjualan>();
        while (rs.next()) {            
            Penjualan pnjl = new Penjualan(rs.getString("no_faktur"));
            pnjl.setTglKeluar(rs.getString("tgl_keluar"));
            pnjl.getDataBarang().setNamaBarang(rs.getString("nama_barang"));
            pnjl.getDataBarang().getKategori().setNamaKategori(rs.getString("nama_kategoriBarang"));
            pnjl.setHargaBarang(rs.getInt("harga_barang"));
            pnjl.getDataBarang().setSatuan(rs.getString("satuan"));
            pnjl.setJumlahBarang(rs.getInt("jumlah_barang"));
            pnjl.setTotalHarga(rs.getLong("total_harga"));
            pnjl.getDataPelanggan().setKdPelanggan(rs.getString("kd_pelanggan"));
            pnjl.getDataPelanggan().setNamaPelanggan(rs.getString("nama_pelanggan"));
            pnjl.getDataPelanggan().setAlamat(rs.getString("alamat"));
            
            list.add(pnjl);
        }
        return list;
    }
    
    public void updateStok(Penjualan pnjl) throws SQLException{
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("replace into tb_stokbarang values(?,?,?, now())");
        st.setString(1, pnjl.getNoFaktur());
        st.setString(2, pnjl.getDataBarang().getKodeBarang());
        st.setInt(3, 0-Math.abs(pnjl.getJumlahBarang()));
        st.executeUpdate();
    
    }
    
    public void deleteStok(String noFaktur) throws SQLException{
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("delete from tb_stokbarang where no_faktur=?");
        st.setString(1, noFaktur);
        st.executeUpdate();
    
    }
    
    /**
     *
     * @param kdBarang
     * @return
     * @throws SQLException
     */
    @Override
    public String getStok(String kdBarang) throws SQLException{
        
        PreparedStatement st = KoneksiDatabase.getKoneksi().prepareStatement("select sum(stok) jml FROM tb_stokbarang where kd_barang = ?");
        st.setString(1, kdBarang);
        
        ResultSet rs = st.executeQuery();
        
        if (rs.next()) {            
            
            return rs.getString("jml");
        }
        return "";
    }
    
    
}
