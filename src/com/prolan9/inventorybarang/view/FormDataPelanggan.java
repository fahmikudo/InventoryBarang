/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolan9.inventorybarang.view;


import com.prolan9.inventorybarang.controller.KoneksiDatabase;
import com.prolan9.inventorybarang.entity.DataPelanggan;
import com.prolan9.inventorybarang.implement.DataPelangganImplement;
import com.prolan9.inventorybarang.model.DataPelangganTableModel;
import com.prolan9.inventorybarang.service.DataPelangganInterface;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FormDataPelanggan extends javax.swing.JDialog {
    
    DataPelangganTableModel tableModel = new DataPelangganTableModel();
    List<DataPelanggan> record=new ArrayList<DataPelanggan>();
    DataPelangganInterface dpServis;
    private final TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
    int row;
    
    public FormDataPelanggan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        dpServis = new DataPelangganImplement();
        tableDataPelanggan.setRowSorter(sorter);
        loadData();
        
        
        setLocationRelativeTo(null);
    }
    
    private void loadData(){
        try {
            record = dpServis.getAll();
            tableModel.setList(record);
            tableDataPelanggan.setModel(tableModel);
        } catch (SQLException ex) {
            Logger.getLogger(FormDataPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void isiTabel(){
        Object data[][]=new Object[record.size()][4];
        int x=0;
        for(DataPelanggan dp:record){
            data[x][0]=dp.getKdPelanggan();
            data[x][1]=dp.getNamaPelanggan();
            data[x][2]=dp.getAlamat();
            data[x][3]=dp.getNoTelepon();
            x++;
        }
        String judul[]={"Kode Pelanggan","Nama Pelanggan","Alamat","No. Telepon"};
        tableDataPelanggan.setModel(new DefaultTableModel(data, judul));
        jScrollPane1.setViewportView(tableDataPelanggan);
    }
    
    private void isiText(){
        DataPelanggan dp = record.get(row);
        txtKdPelanggan.setText(dp.getKdPelanggan());
        txtNamaPelanggan.setText(dp.getNamaPelanggan());
        txtAlamat.setText(dp.getAlamat());
        txtNoTelepon.setText(dp.getNoTelepon());
    }
    
    public void clear(){
        txtKdPelanggan.setText("");
        txtKdPelanggan.setEditable(true);
        txtNamaPelanggan.setText("");
        txtAlamat.setText("");
        txtNoTelepon.setText("");
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
            DataPelanggan dp = new DataPelanggan();
            dp.setKdPelanggan(txtKdPelanggan.getText());
            dp.setNamaPelanggan(txtNamaPelanggan.getText());
            dp.setAlamat(txtAlamat.getText());
            dp.setNoTelepon(txtNoTelepon.getText());
            dpServis.update(dp);
            this.statusAwal();
            dialog("Data berhasil diubah");
        } catch (SQLException ex) {
            Logger.getLogger(FormDataPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    private void delete(){
        try {
            String kdPelanggan = txtKdPelanggan.getText();
            dpServis.delete(kdPelanggan);
            this.statusAwal();
            dialog("Data berhasil dihapus");
        } catch (SQLException ex) {
            Logger.getLogger(FormDataPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void saveOrUpdate(){
        if (btnSave.getText().equals("Save")) {
            try {
                DataPelanggan dp = new DataPelanggan();
                dp.setKdPelanggan(txtKdPelanggan.getText());
                dp.setNamaPelanggan(txtNamaPelanggan.getText());
                dp.setAlamat(txtAlamat.getText());
                dp.setNoTelepon(txtNoTelepon.getText());
                dpServis.insert(dp);
                this.statusAwal();
                dialog("Data berhasil tersimpan");
            } catch (SQLException ex) {
                Logger.getLogger(FormDataPelanggan.class.getName()).log(Level.SEVERE, null, ex);
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
        tableDataPelanggan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtCariPelanggan = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtKdPelanggan = new javax.swing.JTextField();
        txtNamaPelanggan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        txtNoTelepon = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prolan9/inventorybarang/images/Menu.png"))); // NOI18N
        jLabel1.setText("Data Pelanggan");

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

        tableDataPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Pelanggan", "Nama Pelanggan", "Alamat", "No. Telepon"
            }
        ));
        tableDataPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDataPelangganMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDataPelanggan);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setText("Pencarian");

        txtCariPelanggan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtCariPelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariPelangganKeyReleased(evt);
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
                .addComponent(txtCariPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(txtCariPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave)
                    .addComponent(btnDelete)
                    .addComponent(btnRefresh))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Kode Pelanggan");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nama Pelanggan");

        txtKdPelanggan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txtNamaPelanggan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Alamat");

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        jScrollPane2.setViewportView(txtAlamat);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("No. Telepon");

        txtNoTelepon.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKdPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNoTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
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
                    .addComponent(txtKdPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtNoTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        clear();
        btnDelete.setEnabled(false);
        txtKdPelanggan.setEditable(true);
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
        if (txtKdPelanggan.getText().isEmpty()) {
            dialog("Kode Pelanggan mohon diisi");
            txtKdPelanggan.requestFocus();
        }else if (txtNamaPelanggan.getText().isEmpty()) {
            dialog("Nama Pelanggan mohon diisi");
            txtNamaPelanggan.requestFocus();
        } else if (txtAlamat.getText().isEmpty()) {
            dialog("Alamat Pelanggan mohon diisi");
            txtAlamat.requestFocus();
        } else if (txtNoTelepon.getText().isEmpty()){
            dialog("No Telepon Pelanggan mohon diisi");
            txtNoTelepon.requestFocus();
        } else {
            saveOrUpdate();
        }
        
    }//GEN-LAST:event_btnSaveActionPerformed

    private void tableDataPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDataPelangganMouseClicked
        // TODO add your handling code here:
        int click = tableDataPelanggan.rowAtPoint(evt.getPoint());
        txtKdPelanggan.setText((String)tableDataPelanggan.getModel().getValueAt(click, 0));
        txtKdPelanggan.setEditable(false);
        txtNamaPelanggan.setText((String) tableDataPelanggan.getModel().getValueAt(click, 1));
        txtAlamat.setText((String) tableDataPelanggan.getModel().getValueAt(click, 2));
        txtNoTelepon.setText((String) tableDataPelanggan.getModel().getValueAt(click, 3));
        btnSave.setText("Update");
        btnDelete.setEnabled(true);
    }//GEN-LAST:event_tableDataPelangganMouseClicked

    private void txtCariPelangganKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariPelangganKeyReleased
        // TODO add your handling code here:
        String text = txtCariPelanggan.getText();
        if (text.length() == 0) {
          sorter.setRowFilter(null);
        } else {
          sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtCariPelangganKeyReleased

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableDataPelanggan;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtCariPelanggan;
    private javax.swing.JTextField txtKdPelanggan;
    private javax.swing.JTextField txtNamaPelanggan;
    private javax.swing.JTextField txtNoTelepon;
    // End of variables declaration//GEN-END:variables

    
    
}
