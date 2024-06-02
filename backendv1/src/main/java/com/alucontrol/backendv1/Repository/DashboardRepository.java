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
package com.alucontrol.backendv1.Repository;


import com.alucontrol.backendv1.Model.Rent;
import com.alucontrol.backendv1.Projection.ItemsTPriceProjection;
import com.alucontrol.backendv1.Projection.TotalRentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface DashboardRepository extends JpaRepository<Rent, Long>
{
    /**This method will present the relation between item vs total price rented*/
    @Query("SELECT RentProjection.rentItem AS rentItem," +
            "SUM(RentProjection.rentTotalPrice) AS rentTotalPrice " +
            "FROM Rent RentProjection " +
            "GROUP BY RentProjection.rentItem")
    List<ItemsTPriceProjection> findItemsTotalPrice();


    /**This method will present each item rented */
    @Query("SELECT RentProjection.rentItem AS rentItem, " +
            "RentProjection.rentTotalPrice AS rentTotalPrice " +
            "FROM Rent RentProjection")
    List<ItemsTPriceProjection> findRentItems();


    /**This method will present all rent separated by status */
    @Query("SELECT RentProjection.rentPaymentStatus AS rentPaymentStatus," +
            "SUM(RentProjection.rentTotalPrice) AS rentTotalPrice " +
            "FROM Rent RentProjection " +
            "GROUP BY RentProjection.rentPaymentStatus")
    List<TotalRentProjection> findRentPaymentStatus();

}
