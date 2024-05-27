package com.alucontrol.backendv1.Repository.Rent;

import com.alucontrol.backendv1.Model.Rent.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/** This is responsible for managing the persistence of Rent data
 *  in the database, providing a simplified interface for CRUD operations
 */

@Repository
public interface RentRepository extends JpaRepository<Rent, Long>
{
    //Method for counting unpaid records -> Display on Index Page via HomeController
    @Query(value = "SELECT COUNT(*) FROM AluControlV1.rent WHERE rent_payment_status = 'UnPaid'", nativeQuery = true)
    Long countUnpaidRents();

    //(SCAFFOLDS): Method for display the qty of item Scaffolds RENTED -> Display on Product Page via ProductController
    @Query(value = "SELECT SUM(rent_qty_item) FROM AluControlV1.rent WHERE rent_item = 'Scaffolds'", nativeQuery = true)
    Long sumScaffoldsRented();
}


