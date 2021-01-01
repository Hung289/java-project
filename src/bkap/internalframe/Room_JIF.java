/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.internalframe;

import bkap.daoimpl.CategoryRoomDaoImpl;
import bkap.daoimpl.InfrastructureDaoImpl;
import bkap.daoimpl.InfrastructureRoomDaoImpl;
import bkap.daoimpl.RoomDaoImpl;
import bkap.entity.InfrasQuantity;
import bkap.entity.InfrastructureRoom;
import bkap.entity.Room;
import bkap.utils.Constant;
import bkap.utils.DialogThongBao;
import bkap.utils.Helper;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author admi
 */
public class Room_JIF extends javax.swing.JInternalFrame {
    
    Infrastructure_JIF infrastructure_JIF;
    Helper helper ;
    CategoryRoomDaoImpl categoryRoomDaoImpl;
    RoomDaoImpl roomDaoImpl;
    DefaultTableModel dtm;
    List<Room> lstRoom;
    List<String> lstInfras;
    List<InfrasQuantity> lstNameInfras;
    DefaultListModel<String> dlm;
    InfrastructureRoomDaoImpl infrastructureRoomDaoImpl;
    InfrastructureDaoImpl infrastructureDaoImpl;
    private int currentRow = -1;
    
    /**
     * Creates new form Room_JIF
     */
    public Room_JIF() {
        initComponents();
        infrastructure_JIF = new Infrastructure_JIF();
        loadData();
        
        categoryRoomDaoImpl = new CategoryRoomDaoImpl();
        infrastructureRoomDaoImpl = new InfrastructureRoomDaoImpl();
        infrastructureDaoImpl = new InfrastructureDaoImpl();
    }
    
    private void loadData() {
        helper =new Helper();
        roomDaoImpl = new RoomDaoImpl();
//        helper.loadDataIntoComboBox(cboInfras, Constant.SQL_SELECT_INFRASTRUCTURE, "Name");
        helper.loadDataIntoComboBox(cboCateRoom, Constant.SQL_SELECT_CATEGORYROOM, "Name");
        try {
            loadTableRoom();
        } catch (SQLException ex) {
            Logger.getLogger(Room_JIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            loadDataIntoList();
        } catch (SQLException ex) {
            Logger.getLogger(Room_JIF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadTableRoom() throws SQLException {
        String columnsName[] = {
            "Id", "Name", "Price/Day", "Acreage", "People", "Status", "CateRoom"
        };
        dtm = new DefaultTableModel(columnsName, 0);
        dtm.getDataVector().removeAllElements();
        lstRoom = roomDaoImpl.getAllRooms();
        lstNameInfras = new ArrayList<>();
        for (int i = 0; i < lstRoom.size(); i++) {
            Room r = lstRoom.get(i);
            Vector row = new Vector<>();
            row.add(r.getId());
            row.add(r.getName());
            row.add(r.getPrice());
            row.add(r.getAcreage());
            row.add(r.getPeople());
            row.add((r.getStatus() == 1) ? "Hiện" : "Ẩn");
            row.add(helper.getFieldByField("category_room", "Name", "Id", r.getCategoryRoom()));
            dtm.addRow(row);
        }
        tblRoom.setModel(dtm);
        
        tblRoom.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnThemMoi.setEnabled(false);
                btnSua.setEnabled(true);
                btnXoa.setEnabled(true);
                if(tblRoom.getSelectedRow() >= 0 && tblRoom.getSelectedRow() < lstRoom.size()) {
                    currentRow = tblRoom.getSelectedRow();
                    lblId.setText(String.valueOf(lstRoom.get(currentRow).getId()));
                    txtName.setText(lstRoom.get(currentRow).getName());
                    txtAcreage.setText(String.valueOf(lstRoom.get(currentRow).getAcreage()));
                    txtPrice.setText(String.valueOf(lstRoom.get(currentRow).getPrice()));
                    txtPeople.setText(String.valueOf(lstRoom.get(currentRow).getPeople()));
                    txtNote.setText(lstRoom.get(currentRow).getNote());
                    cboCateRoom.setSelectedIndex(lstRoom.get(currentRow).getCategoryRoom());
                    if(lstRoom.get(currentRow).getStatus() == 1) {
                        cbStatus.setSelected(true);
                    }else {
                        cbStatus.setSelected(false);
                    }
                    
                    lstNameInfras = infrastructureDaoImpl.getNameInfrasByIdRoom(lstRoom.get(currentRow).getId());
                    String columnsName[] = {
                        "Name", "Quantity"
                    };
                    dtm = new DefaultTableModel(columnsName,0);
                    dtm.getDataVector().removeAllElements();
                    for (int i = 0; i < lstNameInfras.size(); i++) {
                        Vector row = new Vector<>();
                        row.add(lstNameInfras.get(i).getName());
                        row.add(lstNameInfras.get(i).getQuantity());
                        dtm.addRow(row);
                        
                    }

                    tblInfras.setModel(dtm);
                }
            }
        });
    }
    
    private void loadDataIntoList() throws SQLException {
        dlm = new DefaultListModel<>();
        
        ResultSet rs = helper.loadDataIntoList(Constant.SQL_SELECT_INFRASTRUCTURE);
        while (rs.next()) {            
            dlm.addElement(rs.getString("Name"));
        }
        listInfras.setModel(dlm);
        listInfras.setSelectedIndex(1);
        listInfras.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formAddRoom = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAcreage = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPeople = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listInfras = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblInfras = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cboCateRoom = new javax.swing.JComboBox<>();
        cbStatus = new javax.swing.JCheckBox();
        btnLoadList = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblRoom = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThemMoi = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        formAddRoom.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Phòng"));

        jLabel1.setText("Id:");

        lblId.setText("...");

        jLabel3.setText("Name: ");

        jLabel4.setText("Price:");

        jLabel5.setText("Acreage:");

        jLabel6.setText("People:");

        txtNote.setColumns(20);
        txtNote.setRows(5);
        jScrollPane1.setViewportView(txtNote);

        jLabel7.setText("Note:");

        jLabel10.setText("Infrastructure:");

        jScrollPane2.setViewportView(listInfras);

        tblInfras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane4.setViewportView(tblInfras);

        jButton1.setText(">>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("<<");

        jLabel8.setText("Cate_Room");

        cboCateRoom.setName("cateRoom"); // NOI18N

        cbStatus.setText("Trạng Thái");

        btnLoadList.setText("LoadList");
        btnLoadList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formAddRoomLayout = new javax.swing.GroupLayout(formAddRoom);
        formAddRoom.setLayout(formAddRoomLayout);
        formAddRoomLayout.setHorizontalGroup(
            formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formAddRoomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblId)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addComponent(txtName)
                    .addComponent(txtPrice)
                    .addComponent(txtAcreage)
                    .addComponent(txtPeople)
                    .addComponent(cboCateRoom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(57, 57, 57)
                .addGroup(formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formAddRoomLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formAddRoomLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formAddRoomLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLoadList)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(formAddRoomLayout.createSequentialGroup()
                        .addGroup(formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(cbStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        formAddRoomLayout.setVerticalGroup(
            formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formAddRoomLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblId)
                        .addComponent(jLabel1)))
                .addGroup(formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formAddRoomLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formAddRoomLayout.createSequentialGroup()
                                .addGroup(formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtAcreage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtPeople, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(cboCateRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(formAddRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)))
                            .addGroup(formAddRoomLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(formAddRoomLayout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jButton1)
                                .addGap(32, 32, 32)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLoadList)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formAddRoomLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbStatus)
                        .addGap(19, 19, 19))))
        );

        tblRoom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tblRoom);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkap/icon/delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setPreferredSize(new java.awt.Dimension(90, 30));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkap/icon/edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setPreferredSize(new java.awt.Dimension(90, 30));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThemMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkap/icon/add_.png"))); // NOI18N
        btnThemMoi.setText("Thêm");
        btnThemMoi.setPreferredSize(new java.awt.Dimension(90, 30));
        btnThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMoiActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkap/icon/eraser.png"))); // NOI18N
        jButton3.setText("Dọn Dẹp");
        jButton3.setPreferredSize(new java.awt.Dimension(90, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(formAddRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formAddRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        // TODO add your handling code here:
        if(helper.checkFormValid(formAddRoom)) {
            roomDaoImpl = new RoomDaoImpl();
            prepareData();
            
            Room r = new Room(name, price, acreage, img, people, note, status, categoryRoom);
            int id = roomDaoImpl.insert(r);
            
            Map<String, Integer> ls = new HashMap<>();
        for (int count = 0; count < tblInfras.getRowCount(); count++){
            String name =tblInfras.getValueAt(count, 0).toString();
            Integer quantity =Integer.parseInt(tblInfras.getValueAt(count, 1).toString());
            ls.put(name,quantity);
        }
        for (Map.Entry<String, Integer> entry : ls.entrySet()) {
            String key = entry.getKey();
            int infras_id = 0;
                try {
                    //chuyển từ tên cơ sở vật chất sang Id
                    infras_id =  (int) helper.getFieldByFieldString("infrastructure", "Id", "Name", key);
                } catch (SQLException ex) {
                    Logger.getLogger(Room_JIF.class.getName()).log(Level.SEVERE, null, ex);
                }
            int val = entry.getValue(); 
            InfrastructureRoom infrastructureRoom = new InfrastructureRoom(id, infras_id,val);
            int kq = infrastructureRoomDaoImpl.insert(infrastructureRoom);
        }
        }else {
            helper.messageNullValuesForForm(formAddRoom);
        }
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        List<String> value = listInfras.getSelectedValuesList();
        String columnsName[] = {
            "Name", "Quantity"
        };
        DefaultTableModel dtm1;
        dtm1 = new DefaultTableModel(columnsName,0);
        dtm1.getDataVector().removeAllElements();
        for (int i = 0; i < value.size(); i++) {
            Vector row = new Vector<>();
            row.add(value.get(i));
            row.add(0);
            dtm1.addRow(row);
            dlm.remove(listInfras.getSelectedIndex());
        }
        
        tblInfras.setModel(dtm1);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnLoadListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadListActionPerformed
        try {
            // TODO add your handling code here:
            loadDataIntoList();
            List<String> value = listInfras.getSelectedValuesList();
            DefaultTableModel dtm1;
            String columnsName[] = {
                "Name", "Quantity"
            };
            dtm1 = new DefaultTableModel(columnsName,0);
            dtm1.getDataVector().removeAllElements();
            tblInfras.setModel(dtm1);
        } catch (SQLException ex) {
            Logger.getLogger(Room_JIF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLoadListActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if(helper.checkFormValid(formAddRoom)) {
            roomDaoImpl = new RoomDaoImpl();
            prepareData();
            Room r = new Room(id, name, price, acreage, img, people, note, status, categoryRoom);
            int numberRecord = infrastructureRoomDaoImpl.selectRecord(id);
            int kq = roomDaoImpl.update(r);
            if(numberRecord != tblInfras.getRowCount()) {
                int delete = infrastructureRoomDaoImpl.delete(id);
                     Map<String, Integer> ls = new HashMap<>();
                for (int count = 0; count < tblInfras.getRowCount(); count++){
                    String name =tblInfras.getValueAt(count, 0).toString();
                    Integer quantity =Integer.parseInt(tblInfras.getValueAt(count, 1).toString());
                    ls.put(name,quantity);
                }
                for (Map.Entry<String, Integer> entry : ls.entrySet()) {
                    String key = entry.getKey();
                    int infras_id = 0;
                        try {
                            //chuyển từ tên cơ sở vật chất sang Id
                            infras_id =  (int) helper.getFieldByFieldString("infrastructure", "Id", "Name", key);
                        } catch (SQLException ex) {
                            Logger.getLogger(Room_JIF.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    int val = entry.getValue(); 
                    InfrastructureRoom infrastructureRoom = new InfrastructureRoom(id, infras_id,val);
                    int kq1 = infrastructureRoomDaoImpl.insert(infrastructureRoom);
                    loadData();
                }
                DialogThongBao.showSuccess(this, Constant.MSG_SUCCESS_UPDATE, Constant.MSG_SUCCESS_UPDATE);
            }else{
                List<InfrasQuantity> ls = new ArrayList<>();
                for (int count = 0; count < tblInfras.getRowCount(); count++){
                    String name =tblInfras.getValueAt(count, 0).toString();
                    Integer quantity =Integer.parseInt(tblInfras.getValueAt(count, 1).toString());
                    InfrasQuantity infrasQuantity = new InfrasQuantity(name, quantity);           
                    ls.add(infrasQuantity);
                }
                for (InfrasQuantity l : ls) {
                    String nameInfras = l.getName();
                    int quantity = l.getQuantity();
                    //Chuyeen ten sang Id
                    int infras_id = 0;
                    try {
                        infras_id =  (int) helper.getFieldByFieldString("infrastructure", "Id", "Name", nameInfras);
                    } catch (SQLException ex) {
                        Logger.getLogger(Room_JIF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    InfrastructureRoom infrastructureRoom = new InfrastructureRoom(id, infras_id, quantity);
                    int rs = infrastructureRoomDaoImpl.update(infrastructureRoom);
                    
                    loadData();
                }
                
                        DialogThongBao.showSuccess(this, Constant.MSG_SUCCESS_UPDATE, Constant.MSG_SUCCESS_UPDATE);
                
            }     
        }else{
            helper.messageNullValuesForForm(formAddRoom);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if(DialogThongBao.showAlert(this, Constant.MSG_XAC_NHAN_XOA,txtName.getText()) == JOptionPane.OK_OPTION){
            roomDaoImpl = new RoomDaoImpl();
            infrastructureRoomDaoImpl = new InfrastructureRoomDaoImpl();
            
            if(tblInfras.getRowCount() == 0) {
                if(roomDaoImpl.delete(Integer.parseInt(lblId.getText())) > 0){
                    lstRoom.remove(currentRow);
                    dtm.removeRow(currentRow);
                    dtm.fireTableDataChanged();
                    System.out.println("bkap.internalframe.Room_JIF.btnXoaActionPerformed()"+"HOHO");
                    
                } 
                loadData();
            }else{
                if(infrastructureRoomDaoImpl.delete(Integer.parseInt(lblId.getText())) != 0) {
                    if(roomDaoImpl.delete(Integer.parseInt(lblId.getText())) > 0){
                        lstRoom.remove(currentRow);
                        dtm.removeRow(currentRow);
                        dtm.fireTableDataChanged();
                        System.out.println("bkap.internalframe.Room_JIF.btnXoaActionPerformed()"+"HOHO");
                        
                    }  
                    loadData();
                }else{
                    DialogThongBao.showError(this, Constant.MSG_SUCCESS_DELETE , Constant.MSG_SUCCESS_DELETE);
                }
            }           
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void clearInput() {
        lblId.setText("...");
        txtName.setText(null);
        txtAcreage.setText(null);
        txtNote.setText(null);
        txtPeople.setText(null);
        txtPrice.setText(null);
        cboCateRoom.setSelectedIndex(0);
        cbStatus.setSelected(false);
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        clearInput();
        btnThemMoi.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    String name,img,note;
    float price,acreage;
    int people,status,categoryRoom,id;
    private void prepareData() {
        if(lblId.getText() == "...") {
            id = 0;
        }else{
            id = Integer.parseInt(lblId.getText());
        }
        name = txtName.getText();
        img = "";
        note = txtNote.getText();
        price = Float.parseFloat(txtPrice.getText());
        acreage = Float.parseFloat(txtAcreage.getText());
        people = Integer.parseInt(txtPeople.getText());
        if(cbStatus.isSelected()) {
            status = 1;
        }else {
            status = 0;
        }
        categoryRoom = categoryRoomDaoImpl.GetIdByNameCateRoom(cboCateRoom.getSelectedItem().toString());
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoadList;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThemMoi;
    private javax.swing.JButton btnXoa;
    private javax.swing.JCheckBox cbStatus;
    private javax.swing.JComboBox<String> cboCateRoom;
    private javax.swing.JPanel formAddRoom;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblId;
    private javax.swing.JList<String> listInfras;
    private javax.swing.JTable tblInfras;
    private javax.swing.JTable tblRoom;
    private javax.swing.JTextField txtAcreage;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextArea txtNote;
    private javax.swing.JTextField txtPeople;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
