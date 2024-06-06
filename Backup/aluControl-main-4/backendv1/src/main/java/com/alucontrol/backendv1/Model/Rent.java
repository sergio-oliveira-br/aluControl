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

//Indicates that this class is a JPA entity and will be mapped to a table in the database
@Entity

//Specifies the name of the table in the database to which the entity will be mapped
@Table(name = "rent")
public class Rent
{
    /** Here is the original data from the Rent */
    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String rentFirstName;

    @Column(nullable = true)
    private String rentLastName;

    @Column(nullable = true)
    private String rentAddress;

    @Column(nullable = true)
    private String rentItem; //this will be deleted

    @Column(nullable = true)
    private double rentPrice;

    @Column(nullable = true)
    private int rentQtyItem;

    @Column(nullable = true)
    private String rentStarts;

    @Column(nullable = true)
    private String rentEnds;

    @Column(nullable = true)
    private int rentTotalDays;

    @Column(nullable = true)
    private double rentTotalPrice;

    @Column(nullable = true)
    private String rentDetails;

    @Column(nullable = true)
    private String rentPaymentStatus;

    @Column(nullable = true)
    private String rentStatus;


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

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getRentQtyItem() {
        return rentQtyItem;
    }

    public void setRentQtyItem(int rentQtyItem) {
        this.rentQtyItem = rentQtyItem;
    }

    public String getRentStarts() {
        return rentStarts;
    }

    public void setRentStarts(String rentStarts) {
        this.rentStarts = rentStarts;
    }

    public String getRentEnds() {
        return rentEnds;
    }

    public void setRentEnds(String rentEnds) {
        this.rentEnds = rentEnds;
    }

    public int getRentTotalDays() {
        return rentTotalDays;
    }

    public void setRentTotalDays(int rentTotalDays) {
        this.rentTotalDays = rentTotalDays;
    }

    public double getRentTotalPrice() {
        return rentTotalPrice;
    }

    public void setRentTotalPrice(double rentTotalPrice) {
        this.rentTotalPrice = rentTotalPrice;
    }

    public String getRentDetails() {
        return rentDetails;
    }

    public void setRentDetails(String rentDetails) {
        this.rentDetails = rentDetails;
    }

    public String getRentPaymentStatus() {
        return rentPaymentStatus;
    }

    public void setRentPaymentStatus(String paymentStatus) {
        this.rentPaymentStatus = paymentStatus;
    }

    public String getRentStatus() {return rentStatus;}

    public void setRentStatus(String rentStatus) {this.rentStatus = rentStatus;}
}
