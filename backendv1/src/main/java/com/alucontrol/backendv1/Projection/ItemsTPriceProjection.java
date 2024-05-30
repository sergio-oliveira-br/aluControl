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
public interface ItemsTPriceProjection
{
    String getRentItem();
    Double getRentTotalPrice();

    //For Projection Interface,
    //it is usually used the wrapper class "Double"
    //instead of the primitive type "double".

    //This is because the results of JPA queries can be null if,
    //for example, a field is not present or is calculated at runtime,
    //and a primitive type cannot be null
}
