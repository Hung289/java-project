/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.daoimpl;

import bkap.dao.InfrastructureDao;
import bkap.entity.InfrasQuantity;
import bkap.entity.Infrastructure;
import bkap.entity.Infrastructure;
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
 * @author ADMIN
 */
public class InfrastructureDaoImpl implements InfrastructureDao{

    @Override
    public int insert(Infrastructure obj) {
       int kq=0;
       DatabaseHelper db = new DatabaseHelper();
       Object param[] = new Object[]{
           obj.getName(),
           obj.getPrice()
        };
        try {
            kq = db.insertDataCall(Constant.SQL_INSERT_INTO_INFRASTRUCTURE, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public int update(Infrastructure obj) {
         int kq=0;
       DatabaseHelper db = new DatabaseHelper();
       Object param[] = new Object[]{
           obj.getId(),
           obj.getName(),
           obj.getPrice()
        };
        try {
            kq = db.updateDataCall(Constant.SQL_UPDATE_INFRASTRUCTURE, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public int delete(int id) {
        int kq=0;
       DatabaseHelper db = new DatabaseHelper();
       Object param[] = new Object[]{
          id
        };
        try {
            kq = db.deleteDataCall(Constant.SQL_DELETE_INFRASTRUCTURE, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public List<Infrastructure> getAllInfrastructure() {
         List<Infrastructure> ls = new ArrayList<Infrastructure>();
        DatabaseHelper db = new DatabaseHelper();
        Object param[] = new Object[]{};
        try {
          ResultSet rs = db.selectDataCall(Constant.SQL_SELECT_INFRASTRUCTURE, param);
          while(rs.next()){
              Infrastructure obj = new Infrastructure();
              obj.setId(rs.getInt(1));
              obj.setName(rs.getString(2));
              obj.setDateCreated(rs.getDate(3).toString());
              obj.setPrice(rs.getFloat(4));
              ls.add(obj);
          }
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;    
    }

    @Override
    public Infrastructure getInfrastructureById(int id) {
        Infrastructure obj = new Infrastructure();
        DatabaseHelper db = new DatabaseHelper();
        Object param[] = new Object[]{
            id
        };
        try {
          ResultSet rs = db.selectDataCall(Constant.SQL_SELECT_INFRASTRUCTURE_BY_ID, param);
          while(rs.next()){     
              obj.setId(rs.getInt(1));
              obj.setName(rs.getString(2));
              obj.setPrice(rs.getFloat(4));
              obj.setDateCreated(rs.getDate(3).toString());
          }
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj; 
    }

    @Override
    public List<InfrasQuantity> getNameInfrasByIdRoom(int id) {
        DatabaseHelper db = new DatabaseHelper();
        List<InfrasQuantity> list = new ArrayList<>();
        Integer param[] = new Integer[]{
            id
        };
        try {
            ResultSet rs = db.selectDataCall(Constant.SQL_SELECT_INFRASTRUCTURE_BY_ID_ROOM, param);
            while (rs.next()) {  
                InfrasQuantity infrasQuantity = new InfrasQuantity(rs.getString("Name"), rs.getInt("Quantity"));
                list.add(infrasQuantity);
            }
            
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(InfrastructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
}
