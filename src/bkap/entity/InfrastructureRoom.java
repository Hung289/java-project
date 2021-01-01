/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bkap.entity;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class InfrastructureRoom {
    private int room_id;
    private int infrastructure_id;
    private int quantity;
    private String dateCreated;

    public InfrastructureRoom(int room_id, int infrastructure_id, int quantity, String dateCreated) {
        this.room_id = room_id;
        this.infrastructure_id = infrastructure_id;
        this.quantity = quantity;
        this.dateCreated = dateCreated;
    }

    public InfrastructureRoom(int room_id, int infrastructure_id, int quantity) {
        this.room_id = room_id;
        this.infrastructure_id = infrastructure_id;
        this.quantity = quantity;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getInfrastructure_id() {
        return infrastructure_id;
    }

    public void setInfrastructure_id(int infrastructure_id) {
        this.infrastructure_id = infrastructure_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    
}
