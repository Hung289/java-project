/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.dao;

import bkap.entity.RoleGroup;

/**
 *
 * @author admi
 */
public interface RoleGroupDao {
    public void insert(RoleGroup roleGroup);
    public void update(RoleGroup roleGroup);
    public void delete(int id);
    public int getIdByGroupRole(String groupRole);
}
