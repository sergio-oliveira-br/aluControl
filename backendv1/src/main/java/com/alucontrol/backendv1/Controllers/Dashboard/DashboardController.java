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
import com.alucontrol.backendv1.Util.LoggerUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** This controller contains specific methods for custom operations
 * It is the responsibility of this layer to receive requests, call methods from the service layer, and return HTTP responses */
@RestController
public class DashboardController
{
    //Repository for access to Dash data
    private final DashboardRepository dashboardRepository;

    //Constructor responsible for injecting the repository
    public DashboardController(DashboardRepository dashboardRepository)
    {
        this.dashboardRepository = dashboardRepository;
    }


    /** Endpoint to get items and SUM of total prices
     * Pointing to dashboardScript.js */
    @GetMapping("/findItemsTotalPrice")
    //A Projection interface aims to determine which fields of an entity or dataset are to be selected or projected during a query
    public List<ItemsTPriceProjection> getItemsTotalPrice()
    {
        try{
            //handling exceptions
            if(dashboardRepository.findItemsTotalPrice() == null)
            {
                throw new ResourceNotFoundException("From DashboardController: It was not possible to locate items to calculate the total price");
            }
            return dashboardRepository.findItemsTotalPrice();

        }
        catch (Exception e)
        {
            LoggerUtil.error("An error occurred while fetching items and their total prices: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to retrieve items and their total prices");
        }
    }

    /** Endpoint to get items and total price individually
     * Pointing to dashboardScript.js */
    @GetMapping("/findRentItems")
    //A Projection interface aims to determine which fields of an entity or dataset are to be selected or projected during a query
    public List<ItemsTPriceProjection> getRentItems()
    {
        try {
            //exception handling
            if(dashboardRepository.findRentItems() == null)
            {
                throw new ResourceNotFoundException("From DashboardController: Rent items not found");
            }
            return dashboardRepository.findRentItems();
        }
        catch (Exception e)
        {
            LoggerUtil.error("An error occurred while fetching rent items: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to retrieve rent items");
        }
    }

    /** Endpoint to get all rent separated by status
     * Pointing to dashboardScript.js */
    @GetMapping("/findRentPaymentStatus")
    //A Projection interface aims to determine which fields of an entity or dataset are to be selected or projected during a query
    public List<TotalRentProjection> getRentPayment()
    {
        try
        {
            if(dashboardRepository.findRentPaymentStatus() == null)
            {
                throw new ResourceNotFoundException("From DashboardController: Rent payment status not found");
            }
            return dashboardRepository.findRentPaymentStatus();
        }
        catch (Exception e)
        {
            LoggerUtil.error("An error occurred while fetching rent payment: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to retrieve rent payment");
        }
    }
}
