/**
 * This is for the mySQL Data Base - Table Rent
 *      >> Class Controller <<
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */

package com.alucontrol.backendv1.Controllers.Rent;

import com.alucontrol.backendv1.Repository.Rent.RentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** This controller contains specific methods for custom operations */
@RestController
public class RentStatisticsController
{
    //Repository for access to product data
    private final RentRepository rentRepository;

    //Constructor responsible for injecting the repository
    public RentStatisticsController(RentRepository rentRepository)
    {
        this.rentRepository = rentRepository;
    }

    /** Endpoint to get back the number of Scaffolds was Rented from DB
     *  Pointing to productScript.js and product.html */
    @GetMapping("/sumScaffoldsRented")
    public ResponseEntity<Long> getItemScaffoldsRented()
    {
        Long totalScaffoldsRented = rentRepository.sumScaffoldsRented();
        return ResponseEntity.ok(totalScaffoldsRented);
    }

    /** Endpoint to get back the number of Scaffolds AVAILABLE from DB
     *  Pointing to productScript.js and product.html */
    @GetMapping("/qtyScaffoldsAvailable")
    public ResponseEntity<Long> getScaffoldsAvailable()
    {
        Long totalScaffoldsRented = rentRepository.sumScaffoldsRented();
        Long sumScaffolds = rentRepository.getSumScaffolds();

        Long scaffoldsAvailable = sumScaffolds - totalScaffoldsRented;

        return ResponseEntity.ok(scaffoldsAvailable);

    }



}