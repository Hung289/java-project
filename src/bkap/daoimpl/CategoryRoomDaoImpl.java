/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.daoimpl;

import bkap.dao.CategoryRoomDao;
import bkap.entity.CategoryRoom;
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
 * @author DELL
 */
public class CategoryRoomDaoImpl implements CategoryRoomDao {

    @Override
    public int Insert(CategoryRoom CR) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        DatabaseHelper databaseHelper = new DatabaseHelper();
        int idinsert = 0;
        Object params[] = new Object[]{
            CR.getName(),
            CR.getStatus()
        };
        try {
            idinsert = databaseHelper.insertDataCall(Constant.SQL_INSERT_CATEGORYROOM, params);
            return idinsert;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
        
    }

    @Override
    public int Update(CategoryRoom CR) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     DatabaseHelper databaseHelper = new DatabaseHelper();
      int numOfItem;
        try {
            Object param[] = new Object[]{
               
                CR.getName(),
                CR.getStatus(),
                     CR.getId()
            };
            numOfItem = databaseHelper.updateDataCall(Constant.SQL_UPDATE_CATEGORYROOM,param );
        } catch (Exception ex) {
            numOfItem = -1;
            Logger.getLogger(CategoryRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numOfItem;
    }

    @Override
    public int Delete(int id) {
        try {
            //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            DatabaseHelper data = new DatabaseHelper();
            int iddelete;
            iddelete = data.deleteDataCall(Constant.SQL_DELETE_CATEGORYROOM, id);
            return iddelete;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public List<CategoryRoom> getdataCall() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        DatabaseHelper databaseHelper = new DatabaseHelper();
        List<CategoryRoom> listCR = new ArrayList<>();
        try {
            String param[] = new String[]{};
            ResultSet data = databaseHelper.selectDataCall(Constant.SQL_SELECT_CATEGORYROOM, param);

            while (data.next()) {
                CategoryRoom item = new CategoryRoom(data.getInt(1), data.getString(2), data.getInt(3), data.getString(4));
                listCR.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return listCR;
    }

    @Override
    public int GetIdByNameCateRoom(String nameCate) {
        DatabaseHelper db = new DatabaseHelper();
        
        
        String param[] = new String[]{nameCate};
        int id = 0;
        try {
            ResultSet data = db.selectDataCall(Constant.SQL_GET_ID_BY_CATEGORYROOM, param);
            while (data.next()) {                
                id = data.getInt("Id");
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
