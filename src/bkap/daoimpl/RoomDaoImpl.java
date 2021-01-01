/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bkap.daoimpl;

import bkap.dao.RoomDao;
import bkap.entity.Room;
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
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class RoomDaoImpl implements RoomDao{

    @Override
    public int insert(Room room) {
        DatabaseHelper db = new DatabaseHelper();
        
        try {
            int kq = db.insertRoom(Constant.SQL_INSERT_ROOM, room);
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public List<Room> getAllRooms() {
        DatabaseHelper db = new DatabaseHelper();
        List<Room> lstRoom = new ArrayList<>();
        String param[] = new String[]{};
        try {
            ResultSet rs = db.selectDataCall(Constant.SQL_SELECT_ROOM, param);
            while (rs.next()) {                
                Room r = new Room(
                        rs.getInt("Id"),
                        rs.getString("Name"), 
                        rs.getFloat("Price"), 
                        rs.getFloat("Acreage"), 
                        rs.getString("Img"), 
                        rs.getInt("People"), 
                        rs.getString("Note"), 
                        rs.getInt("Status"), 
                        rs.getInt("CategoryRoom_id"));
                lstRoom.add(r);
            }
            return lstRoom;
        } catch (SQLException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int update(Room room) {
        DatabaseHelper db = new DatabaseHelper();
        Object param[] = new Object[]{
            room.getId(),room.getName(),room.getPrice(),room.getAcreage(),
            room.getImg(),room.getPeople(),room.getNote(),room.getStatus(),room.getCategoryRoom()
        };
        try {
            int kq = db.updateDataCall(Constant.SQL_UPDATE_ROOM, param);
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        DatabaseHelper db = new DatabaseHelper();
        Object param[] = new Object[]{
            id
        };
        try {
            int kq = db.deleteDataCall(Constant.SQL_DELETE_ROOM, param);
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
