/**
 * This is for the mySQL Data Base - Table Rent
 *      >> Class Controller <<
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */


package com.alucontrol.backendv1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
                                         @RequestParam("rentLastName")String rentLastName,
                                         @RequestParam("rentAddress")String rentAddress,
                                         @RequestParam("rentItem")String rentItem,
                                         @RequestParam("rentPrice") int rentPrice,
                                         @RequestParam("rentQtyItem")int rentQtyItem,
                                         @RequestParam("rentStarts")int rentStarts,
                                         @RequestParam("rentEnds")int rentEnds,
                                         @RequestParam("rentTotalDays")int rentTotalDays,
                                         @RequestParam("rentTotalPrice") int rentTotalPrice,
                                         @RequestParam("rentDetails") String rentDetails)

    {
        Rent rent = new Rent();
        rent.setRentFirstName(rentFirstName);
        rent.setRentLastName(rentLastName);
        rent.setRentAddress(rentAddress);
        rent.setRentItem(rentItem);
        rent.setRentPrice(rentPrice);
        rent.setRentQtyItem(rentQtyItem);
        rent.setRentStarts(rentStarts);
        rent.setRentEnds(rentEnds);
        rent.setRentTotalDays(rentTotalDays);
        rent.setRentTotalPrice(rentTotalPrice);
        rent.setRentDetails(rentDetails);

        Rent savedRent = rentRepository.save(rent);
        return ResponseEntity.ok(savedRent);
    }


    /** Endpoint to get back all rent */
    @GetMapping("/rent")
    public ResponseEntity<List<Rent>> getAllRent()
    {
        List<Rent> rent = rentRepository.findAll();
        return ResponseEntity.ok(rent);
    }
}
