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
public class RoleGroup {
    private int id;
    private String idGroupRole;
    private String Name;
    private String Note;

    public RoleGroup(int id, String idGroupRole, String Name, String Note) {
        this.id = id;
        this.idGroupRole = idGroupRole;
        this.Name = Name;
        this.Note = Note;
    }

    public RoleGroup() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdGroupRole() {
        return idGroupRole;
    }

    public void setIdGroupRole(String idGroupRole) {
        this.idGroupRole = idGroupRole;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }
    
    
}
