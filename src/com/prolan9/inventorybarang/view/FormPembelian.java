/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.view;

import com.prolan9.inventorybarang.controller.KoneksiDatabase;
import com.prolan9.inventorybarang.entity.DataBarang;
import com.prolan9.inventorybarang.entity.DataSupplier;
import com.prolan9.inventorybarang.entity.KategoriBarang;
import com.prolan9.inventorybarang.entity.Pembelian;
import com.prolan9.inventorybarang.implement.PembelianImplement;
import com.prolan9.inventorybarang.model.PembelianTableModel;
import com.prolan9.inventorybarang.service.PembelianInterface;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



/**
 *
 * @author fahmikudo
 */
public class FormPembelian extends javax.swing.JDialog {

    PembelianTableModel tableModel = new PembelianTableModel();
    List<Pembelian> record = new ArrayList<Pembelian>();
    PembelianInterface pblServis;
    private final TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
    int row;
    List<DataSupplier> supplier = new ArrayList<>();
    List<DataBarang> barang = new ArrayList<>();
    
    public FormPembelian(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadSupplier();
        loadBarang();
        pblServis = new PembelianImplement();
        tablePembelian.setRowSorter(sorter);
        setLocationRelativeTo(null);
        statusAwal();
    }
    
    private void loadData(){
        try {
            record = pblServis.getAll();
            tableModel.setList(record);
            tablePembelian.setModel(tableModel);
        } catch (SQLException ex) {
            Logger.getLogger(FormKategoriBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void isiTabel(){
        Object data[][]=new Object[record.size()][7];
        int x=0;
        Date date1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(Pembelian pmbl:record){ 
            data[x][0] = "";
            try {
                date1=  new SimpleDateFormat("yyyy-MM-dd").parse(pmbl.getTglMasuk());
                data[x][0]=dateFormat.format(date1);
            } catch (ParseException ex) {
                Logger.getLogger(FormPembelian.class.getName()).log(Level.SEVERE, null, ex);
            }
            data[x][1]=pmbl.getDataBarang().getNamaBarang();
            data[x][2]=pmbl.getDataBarang().getKategori().getNamaKategori();
            data[x][3]=pmbl.getHargaBarang();
            data[x][4]=pmbl.getDataBarang().getSatuan();
            data[x][5]=pmbl.getJumlahBarang();
            data[x][6]=pmbl.getTotalHarga();
            x++;
        }
        String judul[]={"Tanggal Masuk","Nama Barang","Kategori Barang","Harga Barang","Satuan","Jumlah Barang","Total Harga"};
        tablePembelian.setModel(new DefaultTableModel(data, judul));
        jScrollPane1.setViewportView(tablePembelian);
    }
    
    public void clear(){
        txtNoFaktur.setText("");
        cmbSupplier.setSelectedItem("-- Pilih Supplier --");
        txtAlamatSupplier.setText("");
        cmbBarang.setSelectedItem("-- Pilih Barang --");
        txtKategoriBarang.setText("");
        txtHargaBarang.setText("");
        txtSatuan.setText("");
        txtJumlah.setText("");

    }
    
    private void statusAwal(){
        clear();
        loadData();
        isiTabel();
        
    }
    
    private void dialog(String text){
        JOptionPane.showMessageDialog(rootPane, text);
    }
    
    private void update(){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Pembelian pmbl = new Pembelian(txtNoFaktur.getText());
                
            pmbl.setTglMasuk(dateFormat.format(dateChooser.getDate()));
            pmbl.setJumlahBarang((int) Long.parseLong(txtJumlah.getText()));
            pmbl.setHargaBarang(Long.parseLong(txtHargaBarang.getText()));
            pmbl.getDataBarang().setKodeBarang(barang.get(cmbBarang.getSelectedIndex()-1).getKodeBarang());
            pmbl.getDataSupplier().setKdSupplier(supplier.get(cmbSupplier.getSelectedIndex()-1).getKdSupplier());
                                
            pblServis.update(pmbl);
            this.statusAwal();
            dialog("Data berhasil diubah");
        } catch (SQLException ex) {
            Logger.getLogger(FormKategoriBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    private void delete(){
        try {
            String noFaktur = txtNoFaktur.getText();
            pblServis.delete(noFaktur);
            this.statusAwal();
            dialog("Data berhasil dihapus");
        } catch (SQLException ex) {
            Logger.getLogger(FormKategoriBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void saveOrUpdate(){
        if (btnSaveOrUpdate.getText().equals("Save")) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Pembelian pmbl = new Pembelian(txtNoFaktur.getText());
                
                pmbl.setTglMasuk(dateFormat.format(dateChooser.getDate()));
                pmbl.setJumlahBarang((int) Long.parseLong(txtJumlah.getText()));
                pmbl.setHargaBarang(Long.parseLong(txtHargaBarang.getText()));
                pmbl.getDataBarang().setKodeBarang(barang.get(cmbBarang.getSelectedIndex()-1).getKodeBarang());
                pmbl.getDataSupplier().setKdSupplier(supplier.get(cmbSupplier.getSelectedIndex()-1).getKdSupplier());
                                
                pblServis.insert(pmbl);
                this.statusAwal();
                dialog("Data berhasil tersimpan");
            } catch (SQLException ex) {
                Logger.getLogger(FormKategoriBarang.class.getName()).log(Level.SEVERE, null, ex);
                  
            }
        } else {
            try {
                update();
                clear();
                
                btnDelete.setEnabled(false);
                btnSaveOrUpdate.setText("Save");
                this.statusAwal();
            } catch (Exception e) {
            }
        }
    
    }
    
    private void loadSupplier(){
        
        try {
            DataSupplier ds;
            Connection conn = KoneksiDatabase.getKoneksi();
            String query = "SELECT * FROM tb_datasupplier";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            cmbSupplier.addItem("-- Pilih Supplier --");
            
            
            while (rs.next()) {
                ds = new DataSupplier(rs.getString("kd_supplier").trim());
                ds.setAlamat(rs.getString("alamat"));
                ds.setNamaSupplier(rs.getString("nama_supplier"));
                supplier.add(ds);
                
                cmbSupplier.addItem(ds.getNamaSupplier());
 
            }
            rs.close();
            ps.close();
            //conn.close();
        } catch (SQLException e) {
            System.out.println("Error in combobox supplier = " + e.getMessage());
        }
    }
    
    private void loadBarang(){
        try {
            DataBarang dbrg;
            Connection conn = KoneksiDatabase.getKoneksi();
            String query = "select * from tb_databarang INNER JOIN tb_kategoribarang USING (kd_kategoriBarang)";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            cmbBarang.addItem("-- Pilih Barang --");
            KategoriBarang kb;
            while (rs.next()) {
                //System.out.println("ishydaiushda");
                //System.out.println(rs.getString("nama_barang"));
                kb = new KategoriBarang(rs.getString("kd_kategoriBarang").trim());
                kb.setNamaKategori(rs.getString("nama_kategoriBarang"));
                dbrg = new DataBarang(
                        rs.getString("kd_barang"),
                        rs.getString("nama_barang"),
                        rs.getString("kd_kategoriBarang"),
                        rs.getLong("harga_beli"),
                        rs.getLong("harga_jual"),
                        rs.getString("satuan"),
                    kb    
                );
                barang.add(dbrg);
                cmbBarang.addItem(dbrg.getNamaBarang());
                
                
            }
            rs.close();
            ps.close();
            //conn.close();
        } catch (SQLException e) {
            System.out.println("Error in combobox barang = " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserDialog1 = new datechooser.beans.DateChooserDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNoFaktur = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtAlamatSupplier = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtKategoriBarang = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtHargaBarang = new javax.swing.JTextField();
        txtSatuan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePembelian = new javax.swing.JTable();
        btnSaveOrUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        cmbBarang = new javax.swing.JComboBox<>();
        cmbSupplier = new javax.swing.JComboBox<>();
        txtSubTotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCariPembelian = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prolan9/inventorybarang/images/Menu.png"))); // NOI18N
        jLabel1.setText("Transaksi Pembelian");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setText("No. Faktur");

        txtNoFaktur.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setText("Nama Supplier");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setText("Alamat");

        txtAlamatSupplier.setEditable(false);
        txtAlamatSupplier.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setText("Nama Barang");

        txtKategoriBarang.setEditable(false);
        txtKategoriBarang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setText("Kategori Barang");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setText("Harga Barang");

        txtHargaBarang.setEditable(false);
        txtHargaBarang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txtSatuan.setEditable(false);
        txtSatuan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setText("Satuan");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel9.setText("Jumlah Barang");

        txtJumlah.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJumlahKeyReleased(evt);
            }
        });

        tablePembelian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tanggal Masuk", "Nama Barang", "Kategori Barang", "Harga Barang", "Satuan", "Jumlah Barang", "Total Harga"
            }
        ));
        tablePembelian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePembelianMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePembelian);

        btnSaveOrUpdate.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnSaveOrUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prolan9/inventorybarang/images/Save.png"))); // NOI18N
        btnSaveOrUpdate.setText("Save");
        btnSaveOrUpdate.setPreferredSize(new java.awt.Dimension(93, 27));
        btnSaveOrUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveOrUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prolan9/inventorybarang/images/Delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setPreferredSize(new java.awt.Dimension(93, 27));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prolan9/inventorybarang/images/Refresh.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel10.setText("Tanggal Masuk");

        cmbBarang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmbBarang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBarangItemStateChanged(evt);
            }
        });

        cmbSupplier.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmbSupplier.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSupplierItemStateChanged(evt);
            }
        });

        txtSubTotal.setEditable(false);
        txtSubTotal.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel11.setText("Sub Total");

        txtCariPembelian.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtCariPembelian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariPembelianKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel12.setText("Cari Pembelian");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtNoFaktur, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtAlamatSupplier)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtKategoriBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(txtCariPembelian)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(cmbBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(txtSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtHargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtSubTotal)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnSaveOrUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRefresh)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNoFaktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(28, 28, 28))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAlamatSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKategoriBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSaveOrUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh)
                    .addComponent(txtCariPembelian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveOrUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveOrUpdateActionPerformed
        // TODO add your handling code here:
        if (txtNoFaktur.getText().isEmpty()) {
            dialog("No. Faktur mohon diisi");
            txtNoFaktur.requestFocus();
        }else if (cmbSupplier.getSelectedItem().toString().equals("-- Pilih Supplier --")) {
            dialog("Nama Supplier mohon diisi");
            cmbSupplier.requestFocus();
        } else if (cmbBarang.getSelectedItem().toString().equals("-- Pilih Barang --")) {
            dialog("Nama Barang mohon diisi");
            cmbSupplier.requestFocus();
        } else {
            saveOrUpdate();
        }
    }//GEN-LAST:event_btnSaveOrUpdateActionPerformed

    private void cmbSupplierItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSupplierItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object item = evt.getItem();
            if(cmbSupplier.getSelectedIndex()>0){
                txtAlamatSupplier.setText(supplier.get(cmbSupplier.getSelectedIndex()-1).getAlamat());
            }
        }
    }//GEN-LAST:event_cmbSupplierItemStateChanged

    private void cmbBarangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBarangItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object item = evt.getItem();
            if(cmbBarang.getSelectedIndex()>0){
                //txtKategoriBarang.setText(supplier.get(cmbSupplier.getSelectedIndex()-1).getAlamat());
                txtKategoriBarang.setText(barang.get(cmbBarang.getSelectedIndex()-1).getKategori().getNamaKategori());
                txtHargaBarang.setText(Long.toString(barang.get(cmbBarang.getSelectedIndex()-1).getHargaBeli()));
                txtSatuan.setText(barang.get(cmbBarang.getSelectedIndex()-1).getSatuan());
            }
        }
    }//GEN-LAST:event_cmbBarangItemStateChanged

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        clear();
        btnDelete.setEnabled(false);
        txtNoFaktur.setEditable(true);
        btnSaveOrUpdate.setText("Save");
        this.statusAwal();
        
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            delete();
            clear();
            btnDelete.setEnabled(false);
            btnSaveOrUpdate.setText("Save");
            this.statusAwal();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyReleased
        if (cmbBarang.getSelectedIndex() > 0 && txtJumlah.getText().length() > 0 && txtJumlah.getText().matches("\\d*")) {
            long subTotal = Long.parseLong(txtJumlah.getText()) * Long.parseLong(txtHargaBarang.getText());
            txtSubTotal.setText(String.valueOf(subTotal));
        }
    }//GEN-LAST:event_txtJumlahKeyReleased

    private void tablePembelianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePembelianMouseClicked
        // TODO add your handling code here:
        
        
        int click = tablePembelian.rowAtPoint(evt.getPoint());
        try {
            dateChooser.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(record.get(click).getTglMasuk()));
        } catch (ParseException ex) {
            Logger.getLogger(FormPembelian.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtNoFaktur.setText(record.get(click).getNoFaktur());
        txtNoFaktur.setEditable(false);
        cmbSupplier.setSelectedItem(record.get(click).getDataSupplier().getNamaSupplier());
        txtAlamatSupplier.setText(record.get(click).getDataSupplier().getAlamat());
        cmbBarang.setSelectedItem(record.get(click).getDataBarang().getNamaBarang());
        txtKategoriBarang.setText(record.get(click).getDataBarang().getKategori().getNamaKategori());
        txtHargaBarang.setText(String.valueOf(record.get(click).getHargaBarang()));
        txtSatuan.setText(record.get(click).getDataBarang().getSatuan());
        txtJumlah.setText(String.valueOf(record.get(click).getJumlahBarang()));
        txtSubTotal.setText(String.valueOf(record.get(click).getTotalHarga()));
        
        btnSaveOrUpdate.setText("Update");
        
        btnDelete.setEnabled(true);
    }//GEN-LAST:event_tablePembelianMouseClicked

    private void txtCariPembelianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariPembelianKeyReleased
        // TODO add your handling code here:
        String text = txtCariPembelian.getText();
        if (text.length() == 0) {
          sorter.setRowFilter(null);
        } else {
          sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
        
        
    }//GEN-LAST:event_txtCariPembelianKeyReleased
    
    
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSaveOrUpdate;
    private javax.swing.JComboBox<String> cmbBarang;
    private javax.swing.JComboBox<String> cmbSupplier;
    private com.toedter.calendar.JDateChooser dateChooser;
    private datechooser.beans.DateChooserDialog dateChooserDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablePembelian;
    private javax.swing.JTextField txtAlamatSupplier;
    private javax.swing.JTextField txtCariPembelian;
    private javax.swing.JTextField txtHargaBarang;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKategoriBarang;
    private javax.swing.JTextField txtNoFaktur;
    private javax.swing.JTextField txtSatuan;
    private javax.swing.JTextField txtSubTotal;
    // End of variables declaration//GEN-END:variables
}
