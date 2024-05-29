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
    /** Display on Index.html */
    //...via HomeController
    //(UNPAID RENT)Method for counting unpaid records
    @Query(value = "SELECT COUNT(*) FROM AluControlV1.rent WHERE rent_payment_status = 'UnPaid'", nativeQuery = true)
    Long countUnpaidRents();

    /** Display on Product.html */
    //...via ProductStatisticsController
    //(SCAFFOLDS): Method for display the qty of item Scaffolds SUM RENTED
    @Query(value = "SELECT SUM(rent_qty_item) FROM AluControlV1.rent WHERE rent_item = 'Scaffolds'", nativeQuery = true)
    Long sumScaffoldsRented();

    /**
     * Display on Dashboard.html
     */
    //...via DashboardController
    @Query(value = "SELECT rent_item, rent_total_price FROM AluControlV1.rent", nativeQuery = true)
    List<Rent> findAllRentItems();


    /** NO Display */
    //(SCAFFOLDS): Method for SUM ALL item Scaffolds
    //This will not display in anywhere, but it will use to calculating the items available
    @Query(value = "SELECT SUM(item_quantity) FROM AluControlV1.products WHERE item_description = 'Scaffolds';", nativeQuery = true)
    Long getSumScaffolds();
}


