/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.daoimpl;

import bkap.dao.ServiceDao;
import bkap.entity.Service;
import bkap.utils.Constant;
import bkap.utils.DatabaseHelper;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author ADMIN
 */
public class ServiceDaoImp implements ServiceDao{

    @Override
    public int insert(Service obj) {
       int kq=0;
       DatabaseHelper db = new DatabaseHelper();
       Object param[] = new Object[]{
           obj.getName(),
           obj.getPrice()
        };
        try {
            kq = db.insertDataCall(Constant.SQL_INSERT_INTO_SERVICE, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public int update(Service obj) {
       int kq=0;
       DatabaseHelper db = new DatabaseHelper();
       Object param[] = new Object[]{
           obj.getId(),
           obj.getName(),
           obj.getPrice()
        };
        try {
            kq = db.updateDataCall(Constant.SQL_UPDATE_SERVICE, param);
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
            kq = db.deleteDataCall(Constant.SQL_DELETE_SERVICE, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public List<Service> getAllService() {
        List<Service> ls = new ArrayList<Service>();
        DatabaseHelper db = new DatabaseHelper();
        Object param[] = new Object[]{};
        try {
          ResultSet rs = db.selectDataCall(Constant.SQL_SELECT_SERVICE, param);
          while(rs.next()){
              Service obj = new Service();
              obj.setId(rs.getInt(1));
              obj.setName(rs.getString(2));
              obj.setPrice(rs.getFloat(3));
              obj.setDateCreated(rs.getDate(4).toString());
              ls.add(obj);
          }
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;     
    }

    @Override
    public Service getServiceById(int id) {
        Service obj = new Service();
        DatabaseHelper db = new DatabaseHelper();
        Object param[] = new Object[]{
            id
        };
        try {
          ResultSet rs = db.selectDataCall(Constant.SQL_SELECT_SERVICE_BY_ID, param);
          while(rs.next()){     
              obj.setId(rs.getInt(1));
              obj.setName(rs.getString(2));
              obj.setPrice(rs.getFloat(3));
              obj.setDateCreated(rs.getDate(4).toString());
          }
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj; 
    }
    
}
