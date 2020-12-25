/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.entity;

import bkap.utils.Constant;

/**
 *
 * @author DELL
 */
public class CategoryRoom {
    private int id;
    private String name;
    private int status;
    private String datecreated;
public CategoryRoom(){}
 public CategoryRoom( String name, int status, int id) {
        
        this.name = name;
        this.status = status;
        this.id = id;
        
    }
    public CategoryRoom( String name, int status) {
//        this.id = id;
        this.name = name;
        this.status = status;
        
    }

    public CategoryRoom(int id, String name, int status, String datecreated) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.datecreated = datecreated;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }
    
}
