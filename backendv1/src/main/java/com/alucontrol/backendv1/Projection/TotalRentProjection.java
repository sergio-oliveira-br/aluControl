/**
 * This is for the mySQL Data Base - Table Customer
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */
package com.alucontrol.backendv1.Projection;

/** A Projection interface aims to determine
 * which fields of an entity or dataset
 * are to be selected or projected during a query.*/
public interface TotalRentProjection
{
    String getRentPaymentStatus();
    Double getRentTotalPrice();
}
