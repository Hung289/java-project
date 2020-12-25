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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admi
 */
public class UserDaoImpl implements UserDao{
    Helper dbHelper;
    @Override
    public ResultSet insert(Users users) {
        DatabaseHelper database = new DatabaseHelper();
        try {
            //Lấy data từ form để thêm mới
            Object param[] = new Object[]{
                users.getUsername(),users.getPassword(),users.getImg(),
                users.getName(),users.getPhone(),users.getEmail(),
                users.getNote(),users.getIdDepartment(),users.getIdRoleGroup(),
                users.getBirthday(),users.getGender(),users.getAddress(),
                users.getWorkDay(),users.getStatus(),
            };
            ResultSet kq = database.insertData(Constant.SQL_INSERT_INTO_USERS, param);
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int update(Users users) {
        DatabaseHelper db = new DatabaseHelper();
        Object param[] = new Object[]{users.getId(),
                users.getUsername(),users.getPassword(),users.getImg(),
                users.getName(),users.getPhone(),users.getEmail(),
                users.getNote(),users.getIdDepartment(),users.getIdRoleGroup(),
                users.getBirthday(),users.getGender(),users.getAddress(),
                users.getWorkDay(),users.getStatus(),
            };
        
        try {
            int kq = db.updateDataCall(Constant.SQL_UPDATE_USER, param);
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        return 0;
        
    }

    @Override
    public int delete(int id) {
        DatabaseHelper db = new DatabaseHelper();
        try {
            int kq = db.updateDataCall(Constant.SQL_DELETE_USER, new Integer[]{id});
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public List<Users> getAllUsers() {
        DatabaseHelper db = new DatabaseHelper();
        List<Users> lstUsers = new ArrayList<>();
        String param[] = new String[]{};
        ResultSet rs = null;
        try {
            rs = db.selectData(Constant.SQL_ALL_USERS, param);
            while (rs.next()) {                
                Users users = new Users(
                        rs.getInt("Id"), 
                    rs.getString("Username"), rs.getString("Password"), 
                    rs.getString("Img"), rs.getString("name"), 
                    rs.getString("Phone"), rs.getString("Email"), 
                    rs.getString("Note"), rs.getInt("Id_department"), rs.getInt("Id_role_group"), 
                    rs.getString("Birthday"), rs.getInt("Gender"), 
                    rs.getString("Address"), rs.getInt("Workday"), rs.getInt("Status"));
                lstUsers.add(users);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstUsers;
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
    public List<Users> getUserByRole(Object value) {
        ResultSet rs = null;
        DatabaseHelper db = new DatabaseHelper();
        List<Users> lstUsers = new ArrayList<>();
        Object param[] = new Object[]{
            value
        };
        try {
            rs = db.selectDataCall(Constant.SQL_USERS_BY_ROLE, param);
            while (rs.next()) {                
                Users users = new Users(
                        rs.getInt("Id"), 
                    rs.getString("Username"), rs.getString("Password"), 
                    rs.getString("Img"), rs.getString("name"), 
                    rs.getString("Phone"), rs.getString("Email"), 
                    rs.getString("Note"), rs.getInt("Id_department"), rs.getInt("Id_role_group"), 
                    rs.getString("Birthday"), rs.getInt("Gender"), 
                    rs.getString("Address"), rs.getInt("Workday"), rs.getInt("Status"));
                lstUsers.add(users);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstUsers;
    }

    @Override
    public List<Users> getUserByDepartment(Object value) {
        ResultSet rs = null;
        DatabaseHelper db = new DatabaseHelper();
        List<Users> lstUsers = new ArrayList<>();
        Object param[] = new Object[]{
            value
        };
        try {
            rs = db.selectDataCall(Constant.SQL_USERS_BY_DEPARTMENT, param);
            while (rs.next()) {                
                Users users = new Users(
                        rs.getInt("Id"), 
                    rs.getString("Username"), rs.getString("Password"), 
                    rs.getString("Img"), rs.getString("name"), 
                    rs.getString("Phone"), rs.getString("Email"), 
                    rs.getString("Note"), rs.getInt("Id_department"), rs.getInt("Id_role_group"), 
                    rs.getString("Birthday"), rs.getInt("Gender"), 
                    rs.getString("Address"), rs.getInt("Workday"), rs.getInt("Status"));
                lstUsers.add(users);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstUsers;
    }

    @Override
    public List<Users> getUserByStatus(Object value) {
        ResultSet rs = null;
        DatabaseHelper db = new DatabaseHelper();
        List<Users> lstUsers = new ArrayList<>();
        Object param[] = new Object[]{
            value
        };
        try {
            rs = db.selectDataCall(Constant.SQL_USERS_BY_STATUS, param);
            while (rs.next()) {                
                Users users = new Users(
                        rs.getInt("Id"), 
                    rs.getString("Username"), rs.getString("Password"), 
                    rs.getString("Img"), rs.getString("name"), 
                    rs.getString("Phone"), rs.getString("Email"), 
                    rs.getString("Note"), rs.getInt("Id_department"), rs.getInt("Id_role_group"), 
                    rs.getString("Birthday"), rs.getInt("Gender"), 
                    rs.getString("Address"), rs.getInt("Workday"), rs.getInt("Status"));
                lstUsers.add(users);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstUsers;
    }

    @Override
    public List<Users> searchLike(String filedWhere, String keyWord) {
        ResultSet rs = null;
        DatabaseHelper db = new DatabaseHelper();
        List<Users> lstUsers = new ArrayList<>();
        Object param[] = new Object[]{
            
        };
        try {
            rs = db.selectData("select * from users where "+ filedWhere + " like N'%" +keyWord+"%'", param);
            while (rs.next()) {                
                Users users = new Users(
                        rs.getInt("Id"), 
                    rs.getString("Username"), rs.getString("Password"), 
                    rs.getString("Img"), rs.getString("name"), 
                    rs.getString("Phone"), rs.getString("Email"), 
                    rs.getString("Note"), rs.getInt("Id_department"), rs.getInt("Id_role_group"), 
                    rs.getString("Birthday"), rs.getInt("Gender"), 
                    rs.getString("Address"), rs.getInt("Workday"), rs.getInt("Status"));
                lstUsers.add(users);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstUsers;
    }
    
}
