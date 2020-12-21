/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.dao;

import bkap.entity.Department;

/**
 *
 * @author admi
 */
public interface DepartmentDao {
    public void insert(Department department);
    public void update(Department department);
    public void delete(int id);
    public int getIdByDepartment(String idDepartment);
}
