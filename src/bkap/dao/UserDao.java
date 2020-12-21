/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.dao;

import bkap.entity.Users;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author admi
 */
public interface UserDao {
    public void insert(Users users);
    public int update(Users users);
    public void delete(int id);
    public List<Users> getAllUsers();
    public Users getUserById();
    public boolean dangNhap(Users users);
    public int doiMatKhau(String matkhau,String tendn);
    public void getUsersToTable(JTable jTable);
}
