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
package com.alucontrol.backendv1.Controllers.Dashboard;

import com.alucontrol.backendv1.Exception.ResourceNotFoundException;
import com.alucontrol.backendv1.Projection.ItemsTPriceProjection;
import com.alucontrol.backendv1.Projection.TotalRentProjection;
import com.alucontrol.backendv1.Repository.DashboardRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** This controller contains specific methods for custom operations */
@RestController
public class DashboardController
{
    //Repository for access to Dash data
    private final DashboardRepository dashboardRepository;

    //Repository for access to Projection data

    //Constructor responsible for injecting the repository
    public DashboardController(DashboardRepository dashboardRepository)
    {
        this.dashboardRepository = dashboardRepository;
    }


    /**
     * Endpoint to get back the items Rented from DB
     * I'm not using this anymore
     */
//    @GetMapping("/allRentedItems")
//    public ResponseEntity<List<Rent>> getAllRentedItems()
//    {
//        List<Rent> myList = dashboardRepository.findAll();
//        return ResponseEntity.ok(myList);
//    }

    /** Endpoint to get items and SUM of total prices
     * Pointing to dashboardScript.js */
    @GetMapping("/findItemsTotalPrice")
    //A Projection interface aims to determine which fields of an entity or dataset are to be selected or projected during a query
    public List<ItemsTPriceProjection> getItemsTPriceProjection()
    {
        //handling exceptions
        if(dashboardRepository.findItemsTotalPrice() == null)
        {
            throw new ResourceNotFoundException("From DashboardController: It was not possible to locate items to calculate the total price");
        }
        return dashboardRepository.findItemsTotalPrice();
    }

    /** Endpoint to get items and total price individually
     * Pointing to dashboardScript.js */
    @GetMapping("/findRentItems")
    //A Projection interface aims to determine which fields of an entity or dataset are to be selected or projected during a query
    public List<ItemsTPriceProjection> getRentItems()
    {
        //exception handling
        if(dashboardRepository.findRentItems() == null)
        {
            throw new ResourceNotFoundException("From DashboardController: Rent items not found");
        }
        return dashboardRepository.findRentItems();
    }

    /** Endpoint to get all rent separated by status
     * Pointing to dashboardScript.js */
    @GetMapping("/findRentPaymentStatus")
    //A Projection interface aims to determine which fields of an entity or dataset are to be selected or projected during a query
    public List<TotalRentProjection> getRentPayment()
    {
        if(dashboardRepository.findRentPaymentStatus() == null)
        {
            throw new ResourceNotFoundException("From DashboardController: Rent payment status not found");
        }
        return dashboardRepository.findRentPaymentStatus();
    }
}
