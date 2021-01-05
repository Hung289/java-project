/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.entity;

/**
 *
 * @author ADMIN
 */
public class Service {
    
    private int id;
    private String name;
    private float price;
    private String dateCreated;

    public Service() {
    }

    public Service(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Service(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return name + " - " + price;
    }
    
    
    
    
    
    
}
