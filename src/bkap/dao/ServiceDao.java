/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.dao;

import bkap.entity.Service;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface ServiceDao {
    
    public int insert(Service obj);
    public int update(Service obj);
    public int delete(int id);
    public List<Service> getAllService();
    public Service getServiceById(int id);
    
}
