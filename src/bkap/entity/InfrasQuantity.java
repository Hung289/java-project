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
public class InfrasQuantity {
    private String name;
    private int quantity;

    public InfrasQuantity(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InfrasQuantity{" + "name=" + name + ", quantity=" + quantity + '}';
    }
    
    
}
