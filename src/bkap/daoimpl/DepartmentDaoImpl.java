/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.daoimpl;

import bkap.dao.DepartmentDao;
import bkap.entity.Department;
import bkap.utils.Constant;
import bkap.utils.DatabaseHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admi
 */
public class DepartmentDaoImpl implements DepartmentDao{

    @Override
    public int insert(Department department) {
        DatabaseHelper db = new DatabaseHelper();
        String param[] = new String[]{
            department.getId_department(),department.getName(),department.getNote()
        };
        int kq = 0;
        try {
            kq = db.insertDataCall(Constant.SQL_INSERT_DEPARTMENT, param);
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return kq;
    }

    @Override
    public int update(Department department) {
        DatabaseHelper db = new DatabaseHelper();
        Object param[] = new Object[]{
            department.getId(),department.getId_department(),department.getName(),department.getNote()
        };
        int kq = 0;
        try {
            kq = db.updateDataCall(Constant.SQL_UPDATE_DEPARTMENT, param);     
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
                
    }

    @Override
    public int delete(int id) {
        DatabaseHelper db = new DatabaseHelper();
        Object param[] = new Object[]{id};
        try {
            int kq = db.updateDataCall(Constant.SQL_DELETE_DEPARTMENT, param);
            return kq;
        } catch (Exception ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
            
    }

    @Override
    public int getIdByDepartment(String idDepartment) {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        int id = 0;
        
        
        try {
            String param[] = new String[]{idDepartment};
            ResultSet rs = databaseHelper.selectData(Constant.SQL_GET_ID_DEPARTMENT, param);
            while (rs.next()) {                
                id = rs.getInt("Id");
            }
            System.out.println("bkap.daoimpl.DepartmentDaoImpl.getIdByDepartment()"+id);
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public List<Department> getAllDepartment() {
        DatabaseHelper db = new DatabaseHelper();
        List<Department> lstDepartments = new ArrayList<>();
        String param[] = new String[]{};
        try {
            ResultSet rs = db.selectDataCall(Constant.SQL_GET_ALL_DEPARTMENT, param);
            while (rs.next()) {                
                Department d = new Department(rs.getInt("Id"), 
                        rs.getString("Id_department"), 
                        rs.getString("Name"), 
                        rs.getString("Note"));
                lstDepartments.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstDepartments;
    }
    
}
