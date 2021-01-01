/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.dao;

import bkap.entity.CategoryRoom;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author DELL
 */
public interface CategoryRoomDao {
    public List<CategoryRoom> getdataCall();
    public int Insert(CategoryRoom CR);
    public int Update(CategoryRoom CR);
    public int Delete(int id);
    public int GetIdByNameCateRoom(String nameCate);
}
