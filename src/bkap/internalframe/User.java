/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.internalframe;

import bkap.dao.RoleGroupDao;
import bkap.daoimpl.DepartmentDaoImpl;
import bkap.daoimpl.RoleGroupDaoImpl;
import bkap.daoimpl.UserDaoImpl;
import bkap.entity.Users;
import bkap.utils.Constant;
import bkap.utils.DialogThongBao;
import bkap.utils.Helper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author admi
 */
public class User extends javax.swing.JInternalFrame {
    Helper helper;
    UserDaoImpl daoImpl;
    SimpleDateFormat dateFormat;
    RoleGroupDaoImpl roleGroupDaoImpl;
    DepartmentDaoImpl departmentDaoImpl;
    DefaultTableModel dtm ;
    List<Users> listUsers;
    private int currentRow = -1;
    
    /**
     * Creates new form User
     */
    public User() {
        initComponents();
        loadData();
//        helper.validateFieldInPanel(pThongTinNV);
        roleGroupDaoImpl = new RoleGroupDaoImpl();
        departmentDaoImpl = new DepartmentDaoImpl();
        btnSua.setEnabled(false);
        btnDondep.setEnabled(false);
        btnXoa.setEnabled(false);
    }
    
    private void loadData() {
        helper = new Helper();
        daoImpl = new UserDaoImpl();
        try {
            //        daoImpl.getUsersToTable(tblUsers);
            loadTableUsers();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Vừa vào thì load dữ liệu của vai trò cho combobox quyền của người dùng
        helper.loadDataIntoComboBox(cboVaiTro, Constant.SQL_ALL_ROLES, "Id_group_role");
        helper.loadDataIntoComboBox(cboVaiTroTk, Constant.SQL_ALL_ROLES, "Id_group_role");
        //Vừa vào thì load dữ liệu của phòng ban vào combobox phòng ban
        helper.loadDataIntoComboBox(cboPhongBan, Constant.SQL_ALL_DEPARTMENT, "Name");
        helper.loadDataIntoComboBox(cboPhongBanTk, Constant.SQL_ALL_DEPARTMENT, "Name");
        
    }
    
    public void loadTableUsers() throws SQLException {
        String columnsName[] = {
            "Id","Họ Tên", "SĐT", "Email", "Ngày sinh", "Giới tính", "Phòng Ban", "Trạng thái"
        };
        dtm = new DefaultTableModel(columnsName, 0);
        dtm.getDataVector().removeAllElements();//reset toafn bo du liru bang
        listUsers = daoImpl.getAllUsers();
        for (int i = 0; i < listUsers.size(); i++) {
            Users users = listUsers.get(i);
            Vector row = new Vector<>();
            row.add(users.getId());
            row.add(users.getName());
            row.add(users.getPhone());
            row.add(users.getEmail());
            row.add(users.getBirthday());
            row.add((users.getGender() == 1) ? "Nam" : "Nữ");
            row.add(helper.getFieldByField("department", "Id_department", "Id",users.getIdDepartment()));
            row.add((users.getStatus() == 1) ? "Hiện" : "Ẩn");
            dtm.addRow(row);
        }
        tblUsers.setModel(dtm);
        tblUserTK.setModel(dtm);
        
        tblUsers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnThemMoi.setEnabled(false);
                btnSua.setEnabled(true);
                btnDondep.setEnabled(true);
                btnXoa.setEnabled(true);
                if(tblUsers.getSelectedRow() >= 0 && tblUsers.getSelectedRow() < listUsers.size()) {
                    currentRow = tblUsers.getSelectedRow();
                    lblId.setText(String.valueOf(listUsers.get(currentRow).getId()));
                    txtTen.setText(listUsers.get(currentRow).getName());
                    txtEmail.setText(listUsers.get(currentRow).getEmail());
                    txtDiaChi.setText(listUsers.get(currentRow).getAddress());
                    String dateBirth = listUsers.get(currentRow).getBirthday();
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        if(dateBirth != null) {
                            txtNgaySinh.setDate(dateFormat.parse(dateBirth));
                        }else{
                            txtNgaySinh.setDate(null);
                        }                        
                    } catch (ParseException ex) {
                        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(listUsers.get(currentRow).getGender() == 1) {
                        rdNam.setSelected(true);
                    }else {
                        rdNu.setSelected(true);
                    } 
                    if(listUsers.get(currentRow).getStatus() == 1) {
                        chkTrangthai.setSelected(true);
                    }else{
                        chkTrangthai.setSelected(false);
                    }
                    txtSoDienThoai.setText(listUsers.get(currentRow).getPhone());
                    txtSoNgayLam.setText(String.valueOf(listUsers.get(currentRow).getWorkDay()));
                    cboVaiTro.setSelectedIndex(listUsers.get(currentRow).getIdRoleGroup());
                    cboPhongBan.setSelectedIndex(listUsers.get(currentRow).getIdDepartment());
                    txtTenDangNhap.setText(listUsers.get(currentRow).getUsername());
                    txtMatKhau.setText(listUsers.get(currentRow).getPassword());
                    lblImgMouse.setIcon(new ImageIcon(listUsers.get(currentRow).getImg()));
                }
            }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        topPanel = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        pThongTinNV = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSoNgayLam = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cboVaiTro = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cboPhongBan = new javax.swing.JComboBox<>();
        chkTrangthai = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblImgMouse = new javax.swing.JLabel();
        lblPath = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        btnDondep = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThemMoi = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        cboTieuChiTim = new javax.swing.JComboBox<>();
        txtKeyWorkTK = new javax.swing.JTextField();
        cboVaiTroTk = new javax.swing.JComboBox<>();
        cboPhongBanTk = new javax.swing.JComboBox<>();
        btnTK = new javax.swing.JButton();
        cboTrangThaiTk = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        srcollUser1 = new javax.swing.JScrollPane();
        tblUserTK = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Nhân Viên");

        topPanel.setBackground(new java.awt.Color(204, 255, 153));
        topPanel.setLayout(new java.awt.BorderLayout());

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Quản Lý Nhân Viên");
        topPanel.add(lblTitle, java.awt.BorderLayout.CENTER);

        pThongTinNV.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("Email: ");

        jLabel3.setText("Tên: ");

        jLabel4.setText("Ngày Sinh:");

        jLabel5.setText("Địa chỉ: ");

        jLabel6.setText("Giới tính: ");

        buttonGroup1.add(rdNam);
        rdNam.setText("Nam");

        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");

        jLabel7.setText("Số ĐT");

        jLabel8.setText("Số ngày làm: ");

        jLabel9.setText("Vai trò: ");

        cboVaiTro.setName("vaitro"); // NOI18N

        jLabel10.setText("Tên đăng nhập:");

        jLabel11.setText("Mật khẩu: ");

        jLabel12.setText("Phòng ban:");

        cboPhongBan.setName("phongban"); // NOI18N

        chkTrangthai.setText("Trạng thái");
        chkTrangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTrangthaiActionPerformed(evt);
            }
        });

        jLabel1.setText("ID");

        lblId.setText("...");

        lblImgMouse.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblImgMouse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImgMouseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pThongTinNVLayout = new javax.swing.GroupLayout(pThongTinNV);
        pThongTinNV.setLayout(pThongTinNVLayout);
        pThongTinNVLayout.setHorizontalGroup(
            pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongTinNVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pThongTinNVLayout.createSequentialGroup()
                        .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pThongTinNVLayout.createSequentialGroup()
                                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pThongTinNVLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdNam)
                                        .addGap(10, 10, 10)
                                        .addComponent(rdNu))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pThongTinNVLayout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pThongTinNVLayout.createSequentialGroup()
                                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pThongTinNVLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDiaChi))
                                    .addGroup(pThongTinNVLayout.createSequentialGroup()
                                        .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtEmail)
                                            .addGroup(pThongTinNVLayout.createSequentialGroup()
                                                .addComponent(lblId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(3, 3, 3))
                                            .addComponent(txtTen))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pThongTinNVLayout.createSequentialGroup()
                                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9))
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboVaiTro, 0, 163, Short.MAX_VALUE)
                                    .addComponent(txtSoDienThoai)
                                    .addComponent(txtSoNgayLam)
                                    .addComponent(cboPhongBan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(pThongTinNVLayout.createSequentialGroup()
                                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                    .addComponent(txtMatKhau)))))
                    .addComponent(lblPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pThongTinNVLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(chkTrangthai)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pThongTinNVLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblImgMouse, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        pThongTinNVLayout.setVerticalGroup(
            pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongTinNVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pThongTinNVLayout.createSequentialGroup()
                        .addComponent(lblImgMouse, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkTrangthai)
                        .addGap(32, 32, 32))
                    .addGroup(pThongTinNVLayout.createSequentialGroup()
                        .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1)
                            .addComponent(lblId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(txtSoNgayLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pThongTinNVLayout.createSequentialGroup()
                                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(10, 10, 10)))
                        .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pThongTinNVLayout.createSequentialGroup()
                                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(39, 39, 39)
                                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(rdNam)
                                    .addComponent(rdNu)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pThongTinNVLayout.createSequentialGroup()
                                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pThongTinNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))))
                        .addGap(18, 18, 18)
                        .addComponent(lblPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))))
        );

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkap/icon/edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setPreferredSize(new java.awt.Dimension(90, 30));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnDondep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkap/icon/eraser.png"))); // NOI18N
        btnDondep.setText("Dọn dẹp");
        btnDondep.setPreferredSize(new java.awt.Dimension(90, 30));
        btnDondep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDondepActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkap/icon/delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setPreferredSize(new java.awt.Dimension(90, 30));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThemMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkap/icon/add.png"))); // NOI18N
        btnThemMoi.setText("Thêm");
        btnThemMoi.setPreferredSize(new java.awt.Dimension(90, 30));
        btnThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMoiActionPerformed(evt);
            }
        });

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblUsers);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDondep, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addComponent(pThongTinNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pThongTinNV, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDondep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông Tin Nhân Viên", jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        cboTieuChiTim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Tiêu chí --", "Id", "Tên", "Vai Trò", "Phòng Ban", "Trạng Thái" }));
        cboTieuChiTim.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTieuChiTimItemStateChanged(evt);
            }
        });

        txtKeyWorkTK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKeyWorkTKKeyReleased(evt);
            }
        });

        cboVaiTroTk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboVaiTroTk.setEnabled(false);
        cboVaiTroTk.setName("vaitro"); // NOI18N
        cboVaiTroTk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboVaiTroTkItemStateChanged(evt);
            }
        });

        cboPhongBanTk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboPhongBanTk.setEnabled(false);
        cboPhongBanTk.setName("phongban"); // NOI18N
        cboPhongBanTk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPhongBanTkItemStateChanged(evt);
            }
        });
        cboPhongBanTk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPhongBanTkActionPerformed(evt);
            }
        });

        btnTK.setText("Bỏ Lọc");
        btnTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKActionPerformed(evt);
            }
        });

        cboTrangThaiTk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- TT --", "Ẩn", "Hiện" }));
        cboTrangThaiTk.setEnabled(false);
        cboTrangThaiTk.setName("trangthai"); // NOI18N
        cboTrangThaiTk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTrangThaiTkItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboTieuChiTim, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtKeyWorkTK, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboVaiTroTk, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboPhongBanTk, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTrangThaiTk, 0, 85, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTK)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTieuChiTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKeyWorkTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboVaiTroTk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPhongBanTk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTK)
                    .addComponent(cboTrangThaiTk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        tblUserTK.setModel(new javax.swing.table.DefaultTableModel(
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
        srcollUser1.setViewportView(tblUserTK);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(srcollUser1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(srcollUser1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tìm Kiếm", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    String tenNV, ngaysinh, dchi, sdt, tendn, mkhau, email, img;
    int songaylam, vaitro, gioitinh, trangthai,phongban,id;
    private void prepareData() {
        if(lblId.getText() == "...") {
            id = 0;
        }else{
            id = Integer.parseInt(lblId.getText());
        }
        
        tenNV = txtTen.getText();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        ngaysinh = dateFormat.format(txtNgaySinh.getDate());
        dchi = txtDiaChi.getText();
        if(rdNam.isSelected()) {
            gioitinh = 1;
        }else {
            gioitinh = 0;
        }
        email = txtEmail.getText();
        sdt = txtSoDienThoai.getText();
        songaylam = Integer.parseInt(txtSoNgayLam.getText());
        if(cboVaiTro.isEnabled()) {
            trangthai = 1;
        }else {
            trangthai = 0;
        }
        img = lblPath.getText();
        tendn = txtTenDangNhap.getText();
        mkhau = txtMatKhau.getText();
        vaitro = roleGroupDaoImpl.getIdByGroupRole(cboVaiTro.getSelectedItem().toString());
        System.out.println("bkap.internalframe.User.prepareData()"+cboPhongBan.getSelectedItem().toString());
        phongban = departmentDaoImpl.getIdByDepartment(cboPhongBan.getSelectedItem().toString());
        if(chkTrangthai.isSelected()) {
            trangthai = 1;
        }else {
            trangthai = 0;
        }
    }
    
    
    
    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        // TODO add your handling code here:
        if(helper.checkFormValid(pThongTinNV)){
            daoImpl = new UserDaoImpl();
            prepareData();
          
            Users users = new Users(tendn, mkhau, img, tenNV, sdt, email, "null", phongban, vaitro, ngaysinh, gioitinh, dchi, songaylam, trangthai);
            ResultSet rs = daoImpl.insert(users);
            if(rs != null) {
                DialogThongBao.showSuccess(this, Constant.MSG_SUCCESS_INSERT_USERS, Constant.MSG_SUCCESS_INSERT_USERS);
            }
            loadData();
            
        }else{
            helper.messageNullValuesForForm(pThongTinNV);            

            
        }
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if(DialogThongBao.showAlert(this, Constant.MSG_XAC_NHAN_XOA, txtTen.getText()) == JOptionPane.OK_OPTION){
            daoImpl = new UserDaoImpl();
            if(daoImpl.delete(Integer.parseInt(lblId.getText())) > 0) {
                listUsers.remove(currentRow);
                dtm.removeRow(currentRow);
                dtm.fireTableDataChanged();
                loadData();
            }else {
                 DialogThongBao.showError(this, Constant.MSG_SUCCESS_DELETE , Constant.MSG_SUCCESS_DELETE);
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if(helper.checkFormValid(pThongTinNV)) {
            daoImpl = new UserDaoImpl();
            prepareData();
            Users users = new Users(id, tendn, mkhau, "null", tenNV, sdt, email, "null", phongban, vaitro, ngaysinh, gioitinh, dchi, songaylam, trangthai);
            int kq = daoImpl.update(users);
            if(kq>0) {
                DialogThongBao.showSuccess(this, Constant.MSG_SUCCESS_UPDATE_USER, Constant.MSG_SUCCESS_UPDATE_USER);
            }
            loadData();
        }else{
            helper.messageNullValuesForForm(pThongTinNV);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void chkTrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTrangthaiActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_chkTrangthaiActionPerformed

    private void lblImgMouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgMouseMouseClicked
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(jPanel1);
        lblImgMouse.setIcon(new ImageIcon(chooser.getSelectedFile().getAbsolutePath()));
        lblPath.setText(chooser.getSelectedFile().getAbsolutePath());
        lblPath.setVisible(false);
    }//GEN-LAST:event_lblImgMouseMouseClicked

    private void cboPhongBanTkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPhongBanTkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPhongBanTkActionPerformed

    private void cboTieuChiTimItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTieuChiTimItemStateChanged
        // TODO add your handling code here:
        if(cboTieuChiTim.getSelectedItem().equals("Vai Trò")) {
            cboVaiTroTk.setEnabled(true);
            txtKeyWorkTK.setEnabled(false);
            txtKeyWorkTK.setText(null);
            cboPhongBanTk.setEnabled(false);
            cboTrangThaiTk.setEnabled(false);
        }else if(cboTieuChiTim.getSelectedItem().equals("Phòng Ban")) {
            cboVaiTroTk.setEnabled(false);
            txtKeyWorkTK.setEnabled(false);
            txtKeyWorkTK.setText(null);
            cboPhongBanTk.setEnabled(true);
            cboTrangThaiTk.setEnabled(false);
        }else if(cboTieuChiTim.getSelectedItem().equals("Trạng Thái")){
            cboVaiTroTk.setEnabled(false);
            txtKeyWorkTK.setEnabled(false);
            txtKeyWorkTK.setText(null);
            cboPhongBanTk.setEnabled(false);
            cboTrangThaiTk.setEnabled(true);
        }else if(cboTieuChiTim.getSelectedItem().equals("-- Tiêu chí --")){
            cboVaiTroTk.setEnabled(false);
            txtKeyWorkTK.setEnabled(true);
            cboPhongBanTk.setEnabled(false);
            cboTrangThaiTk.setEnabled(false);
        }else{ 
            txtKeyWorkTK.setEnabled(true);
            cboVaiTroTk.setEnabled(false);
            cboVaiTroTk.setSelectedIndex(0);
            cboPhongBanTk.setEnabled(false);
            cboPhongBanTk.setSelectedIndex(0);
            cboTrangThaiTk.setEnabled(false);
            cboTrangThaiTk.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cboTieuChiTimItemStateChanged

    private void cboVaiTroTkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboVaiTroTkItemStateChanged
        // TODO add your handling code here:
        if(cboVaiTroTk.getSelectedIndex() != 0) {
            try {
                String tenVaitro = cboVaiTroTk.getSelectedItem().toString();
                int Id = (int) helper.getFieldByFieldString("role_group", "Id", "Id_group_role" ,tenVaitro);
                searchWhere(tblUserTK, Id,1);
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cboVaiTroTkItemStateChanged

    private void cboPhongBanTkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPhongBanTkItemStateChanged
        // TODO add your handling code here:
        if(cboPhongBanTk.getSelectedIndex() != 0) {
            
            try {
                String tenPhongBan = cboPhongBanTk.getSelectedItem().toString();
                int Id = (int) helper.getFieldByFieldString("department", "Id", "Name", tenPhongBan);
                searchWhere(tblUserTK, Id, 2);
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cboPhongBanTkItemStateChanged

    private void cboTrangThaiTkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTrangThaiTkItemStateChanged
        // TODO add your handling code here:
        if(cboTrangThaiTk.getSelectedIndex() != 0) {
            int Id = 0;
            try {
                String tenTrangThai = cboTrangThaiTk.getSelectedItem().toString();
                if(tenTrangThai == "Hiện") {
                    Id = 1;
                }else{
                    Id = 0;
                }   
                searchWhere(tblUserTK, Id, 3);
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cboTrangThaiTkItemStateChanged

    private void txtKeyWorkTKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyWorkTKKeyReleased
        // TODO add your handling code here:
        String tieuchi = cboTieuChiTim.getSelectedItem().toString();
        String keyword = txtKeyWorkTK.getText();
        if(tieuchi.equals("Id")) {
            if(!keyword.isEmpty()){
                try {
                    searchlike(tblUserTK, "Id", keyword);
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(tieuchi.equals("Tên")) {
            if(!keyword.isEmpty()) {
                try {
                    searchlike(tblUserTK, "Name", keyword);
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_txtKeyWorkTKKeyReleased

    private void btnTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKActionPerformed
        // TODO add your handling code here:
        cboTieuChiTim.setSelectedIndex(0);
        cboVaiTroTk.setSelectedIndex(0);
        cboPhongBanTk.setSelectedIndex(0);
        txtKeyWorkTK.setText(null);
        try {
            loadTableUsers();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnTKActionPerformed

    private void btnDondepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDondepActionPerformed
        // TODO add your handling code here:
        clearInput();
        btnThemMoi.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnDondep.setEnabled(false);
        
    }//GEN-LAST:event_btnDondepActionPerformed

    public void clearInput() {
        lblId.setText("...");
        txtDiaChi.setText(null);
        txtEmail.setText(null);
        txtMatKhau.setText(null);
        txtNgaySinh.setDate(null);
        txtSoDienThoai.setText(null);
        txtSoNgayLam.setText(null);
        txtTen.setText(null);
        txtTenDangNhap.setText(null);
        cboVaiTro.setSelectedIndex(0);
        cboPhongBan.setSelectedIndex(0);
    }
    
    public void searchWhere(JTable tbl , Object whereValue, int check) throws SQLException {
        
        String columnsName[] = {
            "Id","Họ Tên", "SĐT", "Email", "Ngày sinh", "Giới tính", "Phòng Ban", "Trạng thái"
        };
        dtm = new DefaultTableModel(columnsName, 0);
        dtm.getDataVector().removeAllElements();//reset toafn bo du liru bang
        if(check == 1){
            listUsers = daoImpl.getUserByRole(whereValue);
        }else if(check == 2) {
            listUsers = daoImpl.getUserByDepartment(whereValue);
        }else if(check == 3) {
            listUsers = daoImpl.getUserByStatus(whereValue);
        }
        
        for (int i = 0; i < listUsers.size(); i++) {
            Users users = listUsers.get(i);
            Vector row = new Vector<>();
            row.add(users.getId());
            row.add(users.getName());
            row.add(users.getPhone());
            row.add(users.getEmail());
            row.add(users.getBirthday());
            row.add((users.getGender() == 1) ? "Nam" : "Nữ");
            row.add(helper.getFieldByField("department", "Id_department", "Id",users.getIdDepartment()));
            row.add((users.getStatus() == 1) ? "Hiện" : "Ẩn");
            dtm.addRow(row);
        }
        tbl.setModel(dtm);
    }
    
    public void searchlike(JTable tbl, String fieldWhere, String keyword) throws SQLException{
        String columnsName[] = {
            "Id","Họ Tên", "SĐT", "Email", "Ngày sinh", "Giới tính", "Phòng Ban", "Trạng thái"
        };
        dtm = new DefaultTableModel(columnsName, 0);
        dtm.getDataVector().removeAllElements();//reset toafn bo du liru bang
        listUsers = daoImpl.searchLike(fieldWhere, keyword);
        for (int i = 0; i < listUsers.size(); i++) {
            Users users = listUsers.get(i);
            Vector row = new Vector<>();
            row.add(users.getId());
            row.add(users.getName());
            row.add(users.getPhone());
            row.add(users.getEmail());
            row.add(users.getBirthday());
            row.add((users.getGender() == 1) ? "Nam" : "Nữ");
            row.add(helper.getFieldByField("department", "Id_department", "Id",users.getIdDepartment()));
            row.add((users.getStatus() == 1) ? "Hiện" : "Ẩn");
            dtm.addRow(row);
        }
        tbl.setModel(dtm);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDondep;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTK;
    private javax.swing.JButton btnThemMoi;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboPhongBan;
    private javax.swing.JComboBox<String> cboPhongBanTk;
    private javax.swing.JComboBox<String> cboTieuChiTim;
    private javax.swing.JComboBox<String> cboTrangThaiTk;
    private javax.swing.JComboBox<String> cboVaiTro;
    private javax.swing.JComboBox<String> cboVaiTroTk;
    private javax.swing.JCheckBox chkTrangthai;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblImgMouse;
    private javax.swing.JLabel lblPath;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pThongTinNV;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JScrollPane srcollUser1;
    private javax.swing.JTable tblUserTK;
    private javax.swing.JTable tblUsers;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtKeyWorkTK;
    private javax.swing.JTextField txtMatKhau;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtSoNgayLam;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
}
