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
public class Room {
    private int id;
    private String name;
    private float price;
    private float acreage;
    private String img;
    private int people;
    private String note;
    private int status;
    private int categoryRoom;

    public Room(int id, String name, float price, float acreage, String img, int people, String note, int status, int categoryRoom) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.acreage = acreage;
        this.img = img;
        this.people = people;
        this.note = note;
        this.status = status;
        this.categoryRoom = categoryRoom;
    }

    public Room(String name, float price, float acreage, String img, int people, String note, int status, int categoryRoom) {
        this.name = name;
        this.price = price;
        this.acreage = acreage;
        this.img = img;
        this.people = people;
        this.note = note;
        this.status = status;
        this.categoryRoom = categoryRoom;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getAcreage() {
        return acreage;
    }

    public void setAcreage(float acreage) {
        this.acreage = acreage;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCategoryRoom() {
        return categoryRoom;
    }

    public void setCategoryRoom(int categoryRoom) {
        this.categoryRoom = categoryRoom;
    }
    
    
}
