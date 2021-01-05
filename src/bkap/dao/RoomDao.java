/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.dao;

import bkap.entity.Room;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface RoomDao {
    public int insert(Room room);
    public List<Room> getAllRooms();
    public int update(Room room);
    public int delete(int id);
    public Room getRoomById(int id);
    public List<Room> searchByID(String fieldwhere, String keyword);
    public List<Room> searchByName(String fieldwhere, String keyword);
    public List<Room> searchByCateRoom(Object id);
    public List<Room> searchByStatus(Object id);
    public List<Room> searchByCateStatusPrice(int cateId, int status, float priceMin, float priceMax);
}
