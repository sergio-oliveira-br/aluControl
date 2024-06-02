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

import com.alucontrol.backendv1.Model.Rent;
import com.alucontrol.backendv1.Projection.ItemsTPriceProjection;
import com.alucontrol.backendv1.Projection.TotalRentProjection;
import com.alucontrol.backendv1.Repository.DashboardRepository;
import org.springframework.http.ResponseEntity;
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
     */
    @GetMapping("/allRentedItems")
    public ResponseEntity<List<Rent>> getAllRentedItems()
    {
        List<Rent> myList = dashboardRepository.findAll();
        return ResponseEntity.ok(myList);
    }

    /** Endpoint to get items and SUM of total prices */
    @GetMapping("/findItemsTotalPrice")
    public List<ItemsTPriceProjection> getItemsTPriceProjection()
    {
        return dashboardRepository.findItemsTotalPrice();
    }

    /** Endpoint to get items and total price individually */
    @GetMapping("/findRentItems")
    public List<ItemsTPriceProjection> getRentItems()
    {
        return dashboardRepository.findRentItems();
    }

    /** Endpoint to get all rent separated by status */
    @GetMapping("/findRentPaymentStatus")
    public List<TotalRentProjection> getRentPayment()
    {
        return dashboardRepository.findRentPaymentStatus();
    }

}
