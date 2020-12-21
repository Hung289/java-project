/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.daoimpl;

import bkap.dao.RoleGroupDao;
import bkap.entity.RoleGroup;
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
public class RoleGroupDaoImpl implements RoleGroupDao{

    @Override
    public void insert(RoleGroup roleGroup) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(RoleGroup roleGroup) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIdByGroupRole(String groupRole) {
        DatabaseHelper database = new DatabaseHelper();
        int id = 0;
        try {
            
            String param[] = new String[]{groupRole};
            ResultSet rs = database.selectData(Constant.SQL_GET_ID_GROUP_ROLE, param);
            while (rs.next()) {                
                id = rs.getInt("Id");
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(RoleGroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
