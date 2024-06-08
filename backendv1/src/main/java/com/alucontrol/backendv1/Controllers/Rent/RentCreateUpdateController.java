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

import com.alucontrol.backendv1.Util.LoggerUtil;
import com.alucontrol.backendv1.Model.Rent;
import com.alucontrol.backendv1.Repository.RentRepository;
import com.alucontrol.backendv1.Service.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**This controller is dedicated to endpoints that create and update records*/
@RestController
public class RentCreateUpdateController
{
    //Repository for access to product data
    private final RentRepository rentRepository;

    private final RentService rentService;

    //Constructor responsible for injecting the repository
    public RentCreateUpdateController(RentRepository rentRepository, RentService rentService)
    {
        this.rentRepository = rentRepository;
        this.rentService = rentService;
    }

    /** Endpoint to send rent */
    @PostMapping("/saveRent")
    public ResponseEntity<Rent> saveRent(@RequestParam("rentFirstName")String rentFirstName,
                                         //@RequestParam("rentLastName")String rentLastName,
                                         @RequestParam("rentAddress")String rentAddress,
                                         @RequestParam("rentItem")String rentItem,
                                         @RequestParam("rentPrice")double rentPrice, //they must always have a value
                                         @RequestParam("rentQtyItem")Integer rentQtyItem,
                                         @RequestParam("rentStarts")String rentStarts,
                                         @RequestParam("rentEnds")String rentEnds,
                                         @RequestParam("rentTotalDays")Integer rentTotalDays,
                                         @RequestParam("rentTotalPrice")double rentTotalPrice,
                                         @RequestParam("rentDetails") String rentDetails,
                                         @RequestParam("rentPaymentStatus") String rentPaymentStatus,
                                         @RequestParam("rentStatus") String rentStatus)

    {
        Rent rent = new Rent();
        rent.setRentFirstName(rentFirstName);
        //rent.setRentLastName(rentLastName);
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
        rent.setRentStatus(rentStatus);


        //Converting data strings into Data objects
        Date startDate;
        Date endDate;
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            startDate = dateFormat.parse(rentStarts);
            endDate = dateFormat.parse(rentEnds);
        }
        catch (ParseException e) {
            return ResponseEntity.internalServerError().build();
        }
        //When a rental is created, make a call to subtract inventory
        rentService.subtractStockByRentalDates(rentItem, rentQtyItem, startDate, endDate);

        Rent savedRent = rentRepository.save(rent);
        System.out.println("Save Rent endpoint accessed.");
        return ResponseEntity.ok(savedRent);

    }



    /** Endpoint to update rent status (by selecting the option "Finished" on Rent.html)*/
    @PutMapping("/rent/status/{id}")
    public ResponseEntity<Rent> updateRentStatus(@PathVariable Long id,
                                                 @RequestParam("rentStatus") String rentStatus)
    {
        //Create a log
        LoggerUtil.info("Updating rent status for ID: " + id);
        LoggerUtil.info("Rent Status: " + rentStatus);

        //Search the product by ID
        //Optional: Used to imply that a value may be present or absent in a given circumstance
        Optional<Rent> rentOptional = rentRepository.findById(id);

        //Check if the product was found
        if(rentOptional.isPresent())
        {
            Rent rent = rentOptional.get();
            rent.setRentStatus(rentStatus);
            Rent savedRent = rentRepository.save(rent);

            if("Finished".equals(rentStatus))
            {
                //Local Variable: These take the values passed as parameters.
                //The values are copied, which means that if the Rent object is modified, the values of the local variables are not affected.
                int quantityReturned = rent.getRentQtyItem();
                String itemDescription = rent.getRentItem();

                //Create a log
                LoggerUtil.info("Rent status updated successfully. ID: " + id);
                LoggerUtil.info("Rent Item: " + rent.getRentItem());
                LoggerUtil.info("Quantity Returned to the stock: " + quantityReturned);

                //Execute the method
                rentService.addStockByRentalStatusFinished(itemDescription, quantityReturned);
            }

            return ResponseEntity.ok(savedRent);
        }
        //Exception: ID incorrect, product was not found
        else
        {
            LoggerUtil.error("Rent not found for ID: " + id);
            return ResponseEntity.notFound().build();
        }

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
            //rent.setRentLastName(updatedRent.getRentLastName());
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
            rent.setRentStatus(updatedRent.getRentStatus());

            Rent savedRent = rentRepository.save(rent);
            return ResponseEntity.ok(savedRent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
