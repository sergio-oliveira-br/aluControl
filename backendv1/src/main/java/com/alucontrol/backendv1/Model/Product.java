/**
 * National College of Ireland - NCI
 *    Higher Diploma in Computing
 *         Final Project
 *              ---
 * Author: Sergio Vinicio da Silva Oliveira
 * ID: x23170981@student.ncirl.ie
 * Project Commencing May 2024
 * Version: 1.0
 */
package com.alucontrol.backendv1.Model;

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

    @Column(nullable = false)
    private double itemAvailableQty;

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

    public double getItemAvailableQty() {return itemAvailableQty;}

    public void setItemAvailableQty(double itemAvailableQty) {this.itemAvailableQty = itemAvailableQty;}
}
