/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.view;


import com.prolan9.inventorybarang.controller.KoneksiDatabase;
import com.prolan9.inventorybarang.entity.DataBarang;
import com.prolan9.inventorybarang.entity.KategoriBarang;
import com.prolan9.inventorybarang.implement.DataBarangImplement;
import com.prolan9.inventorybarang.implement.KategoriBarangImplement;
import com.prolan9.inventorybarang.model.DataBarangTableModel;
import com.prolan9.inventorybarang.model.KategoriBarangTableModel;
import com.prolan9.inventorybarang.service.DataBarangInterface;
import com.prolan9.inventorybarang.service.KategoriBarangInterface;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author fahmikudo
 */
public class FormDataBarang extends javax.swing.JDialog {
    
    DataBarangTableModel tableModel = new DataBarangTableModel();
    List<DataBarang> record=new ArrayList<DataBarang>();
    DataBarangInterface dbServis;
    private final TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
    int row;
    List<KategoriBarang> kategori = new ArrayList<>();
    
    
    public FormDataBarang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        dbServis = new DataBarangImplement();
        tableBarang.setRowSorter(sorter);
        
        loadKategori();
        loadData();
        
       
        setLocationRelativeTo(null);
    }
    
    private void loadData(){
        try {
            record = dbServis.getAll();
            tableModel.setList(record);
            tableBarang.setModel(tableModel);
        } catch (SQLException ex) {
            Logger.getLogger(FormKategoriBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void isiTabel(){
        Object data[][]=new Object[record.size()][6];
        int x=0;
        for(DataBarang db:record){
            data[x][0]=db.getKodeBarang();
            data[x][1]=db.getNamaBarang();
            data[x][2]=db.getKategoriBarang();
            data[x][3]=db.getHargaBeli();
            data[x][4]=db.getHargaJual();
            data[x][5]=db.getSatuan();
            x++;
        }
        String judul[]={"Kode Barang","Nama Barang","Kategori Barang","Harga Beli","Harga Jual","Satuan"};
        tableBarang.setModel(new DefaultTableModel(data, judul));
        jScrollPane1.setViewportView(tableBarang);
    }
    
    private void isiText(){
        DataBarang db = record.get(row);
        txtKdBarang.setText(db.getKodeBarang());
        txtNamaBarang.setText(db.getNamaBarang());
        cmbKategori.setSelectedItem(db.getKategoriBarang());
        txtHargaBeli.setText(Long.toString((long) db.getHargaBeli()));
        txtHargaJual.setText(Long.toString((long) db.getHargaJual()));
        txtSatuan.setText(db.getSatuan());
    }
    
    public void clear(){
        txtKdBarang.setText("");
        txtKdBarang.setEnabled(true);
        txtNamaBarang.setText("");
        cmbKategori.setSelectedItem("-- Pilih Kategori --");
        txtHargaBeli.setText("");
        txtHargaJual.setText("");
        txtSatuan.setText("");
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
            DataBarang db = new DataBarang();
            db.setKodeBarang(txtKdBarang.getText());
            db.setNamaBarang(txtNamaBarang.getText());
            db.setHargaBeli(Long.parseLong(txtHargaBeli.getText()));
            db.setHargaJual(Long.parseLong(txtHargaJual.getText()));
            db.setSatuan(txtSatuan.getText());
            db.setKategoriBarang(kategori.get(cmbKategori.getSelectedIndex()-1).getKdKategori());
            
            dbServis.update(db);
            this.statusAwal();
            dialog("Data berhasil diubah");
        } catch (SQLException ex) {
            Logger.getLogger(FormKategoriBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    private void delete(){
        try {
            String kdBarang = txtKdBarang.getText();
            dbServis.delete(kdBarang);
            this.statusAwal();
            dialog("Data berhasil dihapus");
        } catch (SQLException ex) {
            Logger.getLogger(FormKategoriBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void saveOrUpdate(){
        if (btnSave.getText().equals("Save")) {
            try {
                DataBarang db = new DataBarang();
                
                db.setKodeBarang(txtKdBarang.getText());
                db.setNamaBarang(txtNamaBarang.getText());
                db.setHargaBeli(Long.parseLong(txtHargaBeli.getText()));
                db.setHargaJual(Long.parseLong(txtHargaJual.getText()));
                db.setSatuan(txtSatuan.getText());
                db.setKategoriBarang(kategori.get(cmbKategori.getSelectedIndex()-1).getKdKategori());
                
                dbServis.insert(db);
                this.statusAwal();
                dialog("Data berhasil tersimpan");
            } catch (SQLException ex) {
                Logger.getLogger(FormKategoriBarang.class.getName()).log(Level.SEVERE, null, ex);
                  
            }
        } else {
            try {
                update();
                clear();
                loadData();
                btnDelete.setEnabled(false);
                btnSave.setText("Save");
            } catch (Exception e) {
            }
        }
    
    }
    
    
    private void loadKategori(){
        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            String query = "SELECT * FROM tb_kategoribarang";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            cmbKategori.addItem("-- Pilih Kategori --");
            while (rs.next()) {
                kategori.add(new KategoriBarang(rs.getString("kd_kategoriBarang"),rs.getString("nama_kategoriBarang")));
                String kategori = rs.getString("nama_kategoriBarang");
                cmbKategori.addItem(kategori);
            }
            rs.close();
            ps.close();
            //conn.close();
        } catch (SQLException e) {
            System.out.println("Error in combobox nama kategori barang = " + e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBarang = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtCariBarang = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtKdBarang = new javax.swing.JTextField();
        txtNamaBarang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtHargaBeli = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtHargaJual = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSatuan = new javax.swing.JTextField();
        cmbKategori = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prolan9/inventorybarang/images/Menu.png"))); // NOI18N
        jLabel1.setText("Data Barang");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        tableBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Kategori Barang", "Harga Beli", "Harga Jual", "Satuan"
            }
        ));
        tableBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBarang);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setText("Pencarian");

        txtCariBarang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtCariBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariBarangKeyReleased(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prolan9/inventorybarang/images/Save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prolan9/inventorybarang/images/Delete.png"))); // NOI18N
        btnDelete.setText("Delete");
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave)
                    .addComponent(btnDelete)
                    .addComponent(btnRefresh))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Kode Barang");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nama Barang");

        txtKdBarang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txtNamaBarang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Kategori Barang");

        txtHargaBeli.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Harga Beli");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Harga Jual");

        txtHargaJual.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Satuan");

        txtSatuan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        cmbKategori.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(35, 35, 35)
                        .addComponent(txtKdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(66, 66, 66)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtKdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtHargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(txtSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        clear();
        btnDelete.setEnabled(false);
        txtKdBarang.setEditable(true);
        btnSave.setText("Save");
        loadData();
        
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            delete();
            clear();
            loadData();
            btnDelete.setEnabled(false);
            btnSave.setText("Save");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (txtKdBarang.getText().isEmpty()) {
            dialog("Kode Barang mohon diisi");
            txtKdBarang.requestFocus();
        }else if (txtNamaBarang.getText().isEmpty()) {
            dialog("Nama Barang mohon diisi");
            txtNamaBarang.requestFocus();
        } else if (cmbKategori.getSelectedItem().toString().equals("-- Pilih Kategori --")){
            dialog("Kategori Barang mohon diisi");
        } else if(txtHargaBeli.getText().isEmpty()) {
            dialog("Harga Beli mohon diisi");
        } else if(txtHargaJual.getText().isEmpty()){
            dialog("Harga Jual mohon diisi");
        } else if(txtSatuan.getText().isEmpty()){
            dialog("Satuan mohon diisi");
        } else {
            saveOrUpdate();
        }
        
    }//GEN-LAST:event_btnSaveActionPerformed

    private void tableBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBarangMouseClicked
        // TODO add your handling code here:
        int click = tableBarang.rowAtPoint(evt.getPoint());
        txtKdBarang.setText((String)tableBarang.getModel().getValueAt(click, 0));
        txtKdBarang.setEditable(false);
        txtNamaBarang.setText((String) tableBarang.getModel().getValueAt(click, 1));
        cmbKategori.setSelectedItem(tableBarang.getModel().getValueAt(click, 2).toString());
        txtHargaBeli.setText((tableBarang.getModel().getValueAt(click, 3).toString()));
        txtHargaJual.setText((tableBarang.getModel().getValueAt(click, 4).toString()));
        txtSatuan.setText((String) tableBarang.getModel().getValueAt(click, 5));
        btnSave.setText("Update");
        
        btnDelete.setEnabled(true);
    }//GEN-LAST:event_tableBarangMouseClicked

    private void txtCariBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariBarangKeyReleased
        // TODO add your handling code here:
        String text = txtCariBarang.getText();
        if (text.length() == 0) {
          sorter.setRowFilter(null);
        } else {
          sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtCariBarangKeyReleased

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbKategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableBarang;
    private javax.swing.JTextField txtCariBarang;
    private javax.swing.JTextField txtHargaBeli;
    private javax.swing.JTextField txtHargaJual;
    private javax.swing.JTextField txtKdBarang;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtSatuan;
    // End of variables declaration//GEN-END:variables

    
    
}
