/**
 * This is for the mySQL Data Base - Table Rent
 *      >> Class Controller <<
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */


package com.alucontrol.backendv1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

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
                                         @RequestParam("rentPrice")double rentPrice, //they must always have a value
                                         @RequestParam("rentQtyItem")Integer rentQtyItem,
                                         @RequestParam("rentStarts")String rentStarts,
                                         @RequestParam("rentEnds")String rentEnds,
                                         @RequestParam("rentTotalDays")Integer rentTotalDays,
                                         @RequestParam("rentTotalPrice")double rentTotalPrice,
                                         @RequestParam("rentDetails") String rentDetails,
                                         @RequestParam("rentPaymentStatus") String rentPaymentStatus)

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
        rent.setRentPaymentStatus(rentPaymentStatus);

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



    /** Endpoint to get a specific rent by ID (by clicking on Edit into the table)*/
    @GetMapping("/rent/{id}")
    public ResponseEntity<Rent> getRentById(@PathVariable Long id) {
        Optional<Rent> rentOptional = rentRepository.findById(id);
        if (rentOptional.isPresent()) {
            return ResponseEntity.ok(rentOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /** Endpoint to update a specific rent by ID */
    @PutMapping("/rent/{id}")
    public ResponseEntity<Rent> updateRent(@PathVariable Long id, @RequestBody Rent updatedRent) {
        Optional<Rent> rentOptional = rentRepository.findById(id);
        if (rentOptional.isPresent()) {
            Rent rent = rentOptional.get();
            rent.setRentFirstName(updatedRent.getRentFirstName());
            rent.setRentLastName(updatedRent.getRentLastName());
            rent.setRentAddress(updatedRent.getRentAddress());
            rent.setRentItem(updatedRent.getRentItem());
            rent.setRentPrice(updatedRent.getRentPrice());
            rent.setRentQtyItem(updatedRent.getRentQtyItem());
            rent.setRentStarts(updatedRent.getRentStarts());
            rent.setRentEnds(updatedRent.getRentEnds());
            rent.setRentTotalDays(updatedRent.getRentTotalDays());
            rent.setRentTotalPrice(updatedRent.getRentTotalPrice());
            rent.setRentDetails(updatedRent.getRentDetails());
            rent.setRentPaymentStatus(updatedRent.getRentPaymentStatus());

            Rent savedRent = rentRepository.save(rent);
            return ResponseEntity.ok(savedRent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
