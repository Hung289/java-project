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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admi
 */
public class DepartmentDaoImpl implements DepartmentDao{

    @Override
    public void insert(Department department) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Department department) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
