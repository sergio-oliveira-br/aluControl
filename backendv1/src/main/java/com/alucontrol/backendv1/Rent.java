/**
 * This is for the mySQL Data Base - Table Customer
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */


package com.alucontrol.backendv1;

import jakarta.persistence.*;

import javax.xml.crypto.Data;

@Entity
@Table(name = "rent")
public class Rent
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String rentFirstName;

    @Column(nullable = false)
    private String rentLastName;

    @Column(nullable = true)
    private String rentAddress;

    @Column(nullable = true)
    private String rentItem;

    @Column(nullable = true)
    private int rentPrice;

    @Column(nullable = true)
    private int rentQtyItem;

    @Column(nullable = true)
    private int rentStarts;

    @Column(nullable = true)
    private int rentEnds;

    @Column(nullable = true)
    private int rentTotalDays;

    @Column(nullable = true)
    private int rentTotalPrice;

    @Column(nullable = true)
    private String rentDetails;


    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRentFirstName() {
        return rentFirstName;
    }

    public void setRentFirstName(String rentFirstName) {
        this.rentFirstName = rentFirstName;
    }

    public String getRentLastName() {
        return rentLastName;
    }

    public void setRentLastName(String rentLastName) {
        this.rentLastName = rentLastName;
    }

    public String getRentAddress() {
        return rentAddress;
    }

    public void setRentAddress(String rentAddress) {
        this.rentAddress = rentAddress;
    }

    public String getRentItem() {
        return rentItem;
    }

    public void setRentItem(String rentItem) {
        this.rentItem = rentItem;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getRentQtyItem() {
        return rentQtyItem;
    }

    public void setRentQtyItem(int rentQtyItem) {
        this.rentQtyItem = rentQtyItem;
    }

    public int getRentStarts() {
        return rentStarts;
    }

    public void setRentStarts(int rentStarts) {
        this.rentStarts = rentStarts;
    }

    public int getRentEnds() {
        return rentEnds;
    }

    public void setRentEnds(int rentEnds) {
        this.rentEnds = rentEnds;
    }

    public int getRentTotalDays() {
        return rentTotalDays;
    }

    public void setRentTotalDays(int rentTotalDays) {
        this.rentTotalDays = rentTotalDays;
    }

    public int getRentTotalPrice() {
        return rentTotalPrice;
    }

    public void setRentTotalPrice(int rentTotalPrice) {
        this.rentTotalPrice = rentTotalPrice;
    }

    public String getRentDetails() {
        return rentDetails;
    }

    public void setRentDetails(String rentDetails) {
        this.rentDetails = rentDetails;
    }
}
