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
package com.alucontrol.backendv1.Projection;

/** A Projection interface aims to determine
 * which fields of an entity or dataset
 * are to be selected or projected during a query.*/
public interface ItemQtyDateProjection
{
    String getRentItem();
    Double getRentQtyItem();
    String getRentStarts();
}
