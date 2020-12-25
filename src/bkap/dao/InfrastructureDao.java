/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.dao;

import bkap.entity.Infrastructure;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface InfrastructureDao {
    public int insert(Infrastructure obj);
    public int update(Infrastructure obj);
    public int delete(int id);
    public List<Infrastructure> getAllInfrastructure();
    public Infrastructure getInfrastructureById(int id);
}
