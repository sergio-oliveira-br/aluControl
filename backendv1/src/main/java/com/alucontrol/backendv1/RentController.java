/**
 * This is for the mySQL Data Base - Table Rent
 *      >> Class Controller <<
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */


package com.alucontrol.backendv1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentController
{
    private final RentRepository rentRepository;

    public RentController(RentRepository rentRepository)
    {
        this.rentRepository = rentRepository;
    }

    /** Endpoint to send rent */
    @PostMapping("/saveRent")
    public ResponseEntity<Rent> saveRent(@RequestParam("rentFirstName")String rentFirstName,
                                         @RequestParam("rentLastName")String rentLastName)
    {
        Rent rent = new Rent();
        rent.setRentFirstName(rentFirstName);
        rent.setRentLastName(rentLastName);

        Rent savedRent = rentRepository.save(rent);
        return ResponseEntity.ok(savedRent);
    }



}
