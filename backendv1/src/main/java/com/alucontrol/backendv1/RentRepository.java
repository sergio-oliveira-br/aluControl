package com.alucontrol.backendv1;

import org.springframework.data.jpa.repository.JpaRepository;

/** This is responsible for managing the persistence of Rent data
 *  in the database, providing a simplified interface for CRUD operations
 */

public interface RentRepository extends JpaRepository<Rent, Long>
{}
