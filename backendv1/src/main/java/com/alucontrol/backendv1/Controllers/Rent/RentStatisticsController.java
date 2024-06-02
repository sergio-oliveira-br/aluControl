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
package com.alucontrol.backendv1.Controllers.Rent;

import com.alucontrol.backendv1.Repository.RentRepository;
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
