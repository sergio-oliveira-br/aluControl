/**
 * This is for the mySQL Data Base - Table Customer
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */

package com.alucontrol.backendv1.Model.Product;

import com.alucontrol.backendv1.Model.Rent.Rent;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class Product
{
    /** Here is the original data from the Product */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String itemDescription;

    @Column(nullable = false)
    private int itemQuantity;

    //Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    /** These are data from Rent where it will be connected with THIS TABLE "PRODUCT" */
    @OneToMany //One Product to Many Rent
    private List<Rent> rent;




}
