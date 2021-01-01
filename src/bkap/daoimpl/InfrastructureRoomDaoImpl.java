/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bkap.daoimpl;

import bkap.dao.InfrastrctureRoomDao;
import bkap.entity.InfrastructureRoom;
import bkap.utils.Constant;
import bkap.utils.DatabaseHelper;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class InfrastructureRoomDaoImpl implements InfrastrctureRoomDao{

    @Override
    public int insert(InfrastructureRoom infrastructureRoom) {
        DatabaseHelper db = new DatabaseHelper();
        Object param[] = new Object[]{
            infrastructureRoom.getRoom_id(),
            infrastructureRoom.getInfrastructure_id(),
            infrastructureRoom.getQuantity()
        };
        try {
            int rs = db.insertDataCall(Constant.SQL_INSERT_INFRASTRUCTURE_ROOM, param);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(InfrastructureRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int update(InfrastructureRoom infrastructureRoom) {
        DatabaseHelper db = new DatabaseHelper();
        Object param[] = new Object[]{
            infrastructureRoom.getRoom_id(),
            infrastructureRoom.getInfrastructure_id(),
            infrastructureRoom.getQuantity()
        };
        try {
            int kq = db.updateDataCall(Constant.SQL_UPDATE_INFRASTRUVTURE_ROOM, param);
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(InfrastructureRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int selectRecord(int roomId) {
        DatabaseHelper db = new DatabaseHelper();
        Object param[] = new Object[]{
            roomId
        };
        int record = 0;
        try {
            ResultSet rs = db.selectDataCall(Constant.SQL_SELECT_RECORD, param);
            while (rs.next()) {                
                record = rs.getInt("Số Bản Ghi");
            }
            return record;
        } catch (SQLException ex) {
            Logger.getLogger(InfrastructureRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       return 0;
    }

    @Override
    public int delete(int roomId) {
        DatabaseHelper db = new DatabaseHelper();
        Object param[] = new Object[]{
            roomId
        };
        try {
            int kq = db.deleteDataCall(Constant.SQL_DELETE_INFRASTRUCTURE_ROOM, param);
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(InfrastructureRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
