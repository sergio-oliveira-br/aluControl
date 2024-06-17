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
import com.alucontrol.backendv1.Projection.ItemQtyDateProjection;
import com.alucontrol.backendv1.Projection.SummaryRentStatusProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/** This is responsible for managing the persistence of Rent data
 *  in the database, providing a simplified interface for CRUD operations
 */
@Repository
public interface RentRepository extends JpaRepository<Rent, Long>
{
    /** Display: on Index.html via HomeController
     *  Item: UNPAID RENT
     *  Method: Counting of unpaid rented records and display on the card
     * */
    @Query(value = "SELECT COUNT(QtyRentStatusUnpaidProjection.rentPaymentStatus) AS rentPaymentStatus " +
            "FROM Rent QtyRentStatusUnpaidProjection " +
            "WHERE rentPaymentStatus = 'UnPaid'")
    Long countUnpaidRents();



    /** Display: on Index.html via HomeController
     *  Item: NEW
     *  Method: Rental count with status of: "NEW"
     * */
    @Query(value = "SELECT COUNT(rent_status) " +
            "FROM AluControlV1.rent " +
            "WHERE rent_status = 'new'",
            nativeQuery = true) //Native query allows to perform mySQL queries directly, exactly the same on workbench
    Long countRentStatusNew();


    /** Display: on Index.html via HomeController
     *  Item: NEW
     *  Method: Rental count with status of: "In Progress"
     * */
    @Query(value = "SELECT COUNT(rent_status) " +
            "FROM AluControlV1.rent " +
            "WHERE rent_status = 'In Progress'",
            nativeQuery = true) //Native query allows to perform mySQL queries directly, exactly the same on workbench
    Long countRentStatusInProgress();


    /** Display: on Product.html via ProductStatisticsController
     *  Item: SCAFFOLDS
     *  Method: Counting of Scaffolds that was rented
     * */
    @Query(value = "SELECT SUM(rent_qty_item) " +
            "FROM AluControlV1.rent " +
            "WHERE rent_item = 'Scaffolds'",
            nativeQuery = true) //Native query allows to perform mySQL queries directly, exactly the same on workbench
    Long sumScaffoldsRented();


    /** Display: NO Display
     * Item: SCAFFOLDS
     * Method: Calculating the items available
     * */
    @Query(value = "SELECT SUM(item_quantity) " +
            "FROM AluControlV1.products " +
            "WHERE item_description = 'Scaffolds'",
            nativeQuery = true) //Native query allows to perform mySQL queries directly, exactly the same on workbench
    Long getSumScaffolds();




    /** Display: on Stock.html via RentStatisticsController
     * Item: SCAFFOLDS
     * Method: To construct a graph that will display each rented has been done
     * */
    @Query(value = "SELECT RentStockProjection.rentQtyItem AS rentQtyItem, " +
            "RentStockProjection.rentItem AS rentItem, " +
            "RentStockProjection.rentStarts AS rentStarts " +
            "FROM Rent RentStockProjection " +
            "WHERE rentItem = 'Scaffolds'")
    List<ItemQtyDateProjection> getScaffoldsQtyRented();



    /** Display: on Index.html via HomeController
     * Status: NEW
     * Method: Display a table with ALL Rents witch status is NEW
     * */
    @Query(value = "SELECT RentStatusProjection.id AS id, " +
            "RentStatusProjection.rentFirstName AS rentFirstName, " +
            "RentStatusProjection.rentQtyItem AS rentQtyItem, " +
            "RentStatusProjection.rentAddress AS rentAddress, " +
            "RentStatusProjection.rentPrice AS rentPrice," +
            "RentStatusProjection.rentStarts AS rentStarts," +
            "RentStatusProjection.rentEnds AS rentEnds," +
            "RentStatusProjection.rentDetails AS rentDetails," +
            "RentStatusProjection.rentTotalDays AS rentTotalDays," +
            "RentStatusProjection.rentItem AS rentItem, " +
            "RentStatusProjection.rentPaymentStatus AS rentPaymentStatus," +
            "RentStatusProjection.rentTotalPrice AS rentTotalPrice, " +
            "RentStatusProjection.rentStatus  AS rentStatus " +
            "FROM Rent RentStatusProjection " + //This data came from Projection
            "WHERE rentStatus = 'New'")
    List<SummaryRentStatusProjection> getNewRentsList();



    /** Display: on Index.html via HomeController
     * Status: IN PROGRESS
     * Method: Display a table with ALL Rents witch status is 'In Progress'
     * */
    @Query(value = "SELECT RentStatusProjection.id AS id, " +
            "RentStatusProjection.rentFirstName AS rentFirstName, " +
            "RentStatusProjection.rentQtyItem AS rentQtyItem, " +
            "RentStatusProjection.rentAddress AS rentAddress, " +
            "RentStatusProjection.rentPrice AS rentPrice," +
            "RentStatusProjection.rentStarts AS rentStarts," +
            "RentStatusProjection.rentEnds AS rentEnds," +
            "RentStatusProjection.rentDetails AS rentDetails," +
            "RentStatusProjection.rentTotalDays AS rentTotalDays," +
            "RentStatusProjection.rentItem AS rentItem, " +
            "RentStatusProjection.rentPaymentStatus AS rentPaymentStatus," +
            "RentStatusProjection.rentTotalPrice AS rentTotalPrice, " +
            "RentStatusProjection.rentStatus  AS rentStatus " +
            "FROM Rent RentStatusProjection " + //This data came from Projection
            "WHERE rentStatus = 'In Progress'")
    List<SummaryRentStatusProjection> getInProgressRentsList();


    /** Display: on Index.html via HomeController
     * Status: UNPAID
     * Method: Display a table with ALL Rents witch status is 'Unpaid'
     * */
    @Query(value = "SELECT RentStatusProjection.id AS id, " +
            "RentStatusProjection.rentFirstName AS rentFirstName, " +
            "RentStatusProjection.rentQtyItem AS rentQtyItem, " +
            "RentStatusProjection.rentAddress AS rentAddress, " +
            "RentStatusProjection.rentPrice AS rentPrice," +
            "RentStatusProjection.rentStarts AS rentStarts," +
            "RentStatusProjection.rentEnds AS rentEnds," +
            "RentStatusProjection.rentDetails AS rentDetails," +
            "RentStatusProjection.rentTotalDays AS rentTotalDays," +
            "RentStatusProjection.rentItem AS rentItem, " +
            "RentStatusProjection.rentPaymentStatus AS rentPaymentStatus," +
            "RentStatusProjection.rentTotalPrice AS rentTotalPrice, " +
            "RentStatusProjection.rentStatus  AS rentStatus " +
            "FROM Rent RentStatusProjection " + //This data came from Projection
            "WHERE rentPaymentStatus = 'Unpaid'") //Filter
    List<SummaryRentStatusProjection> getUnpaidRents();



    /** Display: on Index.html via HomeController
     * Status: PAID
     * Method: Display a table with ALL Rents witch status is 'Paid'
     * */
    @Query(value = "SELECT RentStatusProjection.id AS id, " +
            "RentStatusProjection.rentFirstName AS rentFirstName, " +
            "RentStatusProjection.rentQtyItem AS rentQtyItem, " +
            "RentStatusProjection.rentAddress AS rentAddress, " +
            "RentStatusProjection.rentPrice AS rentPrice," +
            "RentStatusProjection.rentStarts AS rentStarts," +
            "RentStatusProjection.rentEnds AS rentEnds," +
            "RentStatusProjection.rentDetails AS rentDetails," +
            "RentStatusProjection.rentTotalDays AS rentTotalDays," +
            "RentStatusProjection.rentItem AS rentItem, " +
            "RentStatusProjection.rentPaymentStatus AS rentPaymentStatus," +
            "RentStatusProjection.rentTotalPrice AS rentTotalPrice, " +
            "RentStatusProjection.rentStatus  AS rentStatus " +
            "FROM Rent RentStatusProjection " + //This data came from Projection
            "WHERE rentPaymentStatus = 'Paid'") //Filter
    List<SummaryRentStatusProjection> getPaidRents();

}


