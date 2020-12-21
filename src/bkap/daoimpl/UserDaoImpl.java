/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.daoimpl;

import bkap.dao.UserDao;
import bkap.entity.Users;
import bkap.utils.Constant;
import bkap.utils.DatabaseHelper;
import bkap.utils.Helper;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admi
 */
public class UserDaoImpl implements UserDao{
    Helper dbHelper;
    @Override
    public void insert(Users users) {
        DatabaseHelper database = new DatabaseHelper();
        //Lấy data từ form để thêm mới
        
    }

    @Override
    public int update(Users users) {
        
        return 0;
        
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Users> getAllUsers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Users getUserById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean dangNhap(Users users) {
        DatabaseHelper database = new DatabaseHelper();
        try {
            String param[] = new String[]{
                users.getUsername(), users.getUsername(), users.getPassword()
            };
            ResultSet rs = database.selectData(Constant.SQL_LOGIN, param);
            if(rs != null) {
                rs.last();
                if(rs.getRow() > 0) {
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public int doiMatKhau(String matkhau, String tendn) {
        try {
            DatabaseHelper database = new DatabaseHelper();
            String params[] = new String[]{matkhau,tendn};
            int row = database.updateData(Constant.SQL_UPDATE_PASSWORD, params);
            System.out.println(row);
            return row;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return 0;
    }

    @Override
    public void getUsersToTable(JTable tblUsers) {
        DatabaseHelper database = new DatabaseHelper();
        Helper dbHelper = new Helper();
        String columnsName[] = {
            "STT","Mã User","Họ Tên", "SĐT", "Email", "Phòng Ban"
        };
        DefaultTableModel model = new DefaultTableModel(columnsName, 0);
        String param[] = new String[]{};
        try {
            ResultSet rs = database.selectData(Constant.SQL_ALL_USERS, param);
            int stt = 1;
            while (rs.next()) {                
                model.addRow(new Object[]{
                    stt++,
                    rs.getString("Username"),
                    rs.getString("Name"),
                    rs.getString("Phone"),
                    rs.getString("Email"),
                    dbHelper.getFieldByField("department", "Id_department", "Id",rs.getInt("Id_department")),
//                    rs.getInt("Id_department")
                });
                
            }
            tblUsers.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
