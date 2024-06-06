package com.alucontrol.backendv1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/** This is responsible for managing the persistence of Rent data
 *  in the database, providing a simplified interface for CRUD operations
 */

@Repository
public interface RentRepository extends JpaRepository<Rent, Long>
{
    //Method for counting unpaid records
    @Query(value = "SELECT COUNT(*) FROM AluControlV1.rent\n WHERE rent_payment_status = 'UnPaid'", nativeQuery = true)

    Long countUnpaidRents();
}


