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
package com.alucontrol.backendv1.Controllers.Index;


import com.alucontrol.backendv1.Exception.ResourceNotFoundException;
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
        //call the repository method, witch has the info about the RENT
        Long qtyRentUnpaid = rentRepository.countUnpaidRents();

        //exception handling
        if (qtyRentUnpaid == null)
        {
            throw new ResourceNotFoundException("Oops! There are no Rents in the database");
        }

        return ResponseEntity.ok(qtyRentUnpaid);
    }


    /** Endpoint to get the number of rent witch has the status "NEW"
     *  Pointing to indexScript.js */
    @GetMapping("/qtyRentStatusNew")
    public ResponseEntity<Long> getQtyRentStatusNew()
    {
        //call the repository method, witch has the info about the RENT
        Long qtyRentStatusNew = rentRepository.countRentStatusNew();

        //exception handling
        if (qtyRentStatusNew == null)
        {
            throw new ResourceNotFoundException("Oops! There are no Rents in the database");
        }

        return ResponseEntity.ok(qtyRentStatusNew);
    }

    /** Endpoint to get the number of rent witch has the status "IN PROGRESS"
     *  Pointing to indexScript.js */
    @GetMapping("/qtyRentStatusInProgress")
    public ResponseEntity<Long> getQtyRentStatusInProgress()
    {
        //call the repository method, witch has the info about the RENT
        Long qtyRentStatusInProgress = rentRepository.countRentStatusInProgress();

        //exception handling
        if (qtyRentStatusInProgress == null)
        {
            throw new ResourceNotFoundException("Oops! There are no Rents in the database");
        }

        return ResponseEntity.ok(qtyRentStatusInProgress);
    }

}
