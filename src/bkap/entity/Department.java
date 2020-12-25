/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.entity;

/**
 *
 * @author admi
 */
public class Department {
    private int id;
    private String id_department;
    private String name;
    private String note;

    public Department(int id, String id_department, String name, String note) {
        this.id = id;
        this.id_department = id_department;
        this.name = name;
        this.note = note;
    }

    public Department(String id_department, String name, String note) {
        this.id_department = id_department;
        this.name = name;
        this.note = note;
    }
    
    

    public Department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_department() {
        return id_department;
    }

    public void setId_department(String id_department) {
        this.id_department = id_department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}
