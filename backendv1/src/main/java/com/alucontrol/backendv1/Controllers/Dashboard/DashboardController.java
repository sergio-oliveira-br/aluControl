/**
 * This is for the mySQL Data Base - Table Product/Items
 *      >> Class Controller <<
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */
package com.alucontrol.backendv1.Controllers.Dashboard;

import com.alucontrol.backendv1.Model.Rent;
import com.alucontrol.backendv1.Repository.RentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** This controller contains specific methods for custom operations */
@RestController
public class DashboardController
{
    //Repository for access to Rent data
    private final RentRepository rentRepository;

    //Constructor responsible for injecting the repository
    public DashboardController(RentRepository rentRepository)
    {
       this.rentRepository = rentRepository;
    }

    /**
     * Endpoint to get back the items Rented from DB
     */
    @GetMapping("/allRentedItems")
    public List<Rent> getAllRentedItems()
    {
        List<Rent> allRentedItems = rentRepository.findAll();
        return allRentedItems;
    }

}
