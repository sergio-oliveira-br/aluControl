package com.alucontrol.backendv1.Repository;

import com.alucontrol.backendv1.Model.Rent;
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
     *  Method: Counting of unpaid rented records
     * */
    @Query(value = "SELECT COUNT(*) " +
            "FROM AluControlV1.rent " +
            "WHERE rent_payment_status = 'UnPaid'",
            nativeQuery = true) //Native query allows to perform mySQL queries directly, exactly the same on workbench
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
}


