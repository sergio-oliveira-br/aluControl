/**
 * This is for the mySQL Data Base - Table Rent
 *      >> Class Controller <<
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */
package com.alucontrol.backendv1.Controllers.Index;


import com.alucontrol.backendv1.Repository.RentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController
{
    //Repository for access to Rent data
    private final RentRepository rentRepository;

    //Constructor responsible for injecting the repository
    public HomeController (RentRepository rentRepository)
    {
        this.rentRepository = rentRepository;
    }

    /** Main endpoint for the home page, indicating that the endpoint is active  */
    @GetMapping("/")
    public String home()
    {
        return "index";
    }


    /** Endpoint to get back the number of rent UNPAID from DB
     *  Pointing to indexScript.js*/
    @GetMapping("/qtyRentUnpaid")
    public ResponseEntity<Long> getQtyRentUnpaid()
    {
        Long qtyRentUnpaid = rentRepository.countUnpaidRents();
        return ResponseEntity.ok(qtyRentUnpaid);
    }


    /** Endpoint to get the number of rent witch has the status "NEW"
     *  Pointing to indexScript.js */
    @GetMapping("/qtyRentStatusNew")
    public ResponseEntity<Long> getQtyRentStatusNew()
    {
        Long qtyRentStatusInProgress = rentRepository.countRentStatusNew();
        return ResponseEntity.ok(qtyRentStatusInProgress);
    }

    /** Endpoint to get the number of rent witch has the status "IN PROGRESS"
     *  Pointing to indexScript.js */
    @GetMapping("/qtyRentStatusInProgress")
    public ResponseEntity<Long> getQtyRentStatusInProgress()
    {
        Long qtyRentStatusInProgress = rentRepository.countRentStatusInProgress();
        return ResponseEntity.ok(qtyRentStatusInProgress);
    }

}
