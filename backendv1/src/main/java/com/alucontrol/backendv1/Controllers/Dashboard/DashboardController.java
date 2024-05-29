/**
 * This is for the mySQL Data Base - Table Product/Items
 *      >> Class Controller <<
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */
package com.alucontrol.backendv1.Controllers.Dashboard;

import com.alucontrol.backendv1.Model.Rent;
import com.alucontrol.backendv1.Projection.ItemsAndTotalPriceProjection;
import com.alucontrol.backendv1.Repository.DashboardRepository;
import com.alucontrol.backendv1.Repository.RentRepository;
import org.springframework.data.jpa.repository.Query;
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


}
