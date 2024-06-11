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
import com.alucontrol.backendv1.Model.Rent;
import com.alucontrol.backendv1.Projection.QtyRentStatusUnpaidProjection;
import com.alucontrol.backendv1.Projection.SummaryRentStatusProjection;
import com.alucontrol.backendv1.Repository.RentRepository;
import com.alucontrol.backendv1.Util.LoggerUtil;
import org.apache.commons.logging.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
    public Long getQtyRentUnpaid()
    {
        try
        {
            //Log
            LoggerUtil.info("Searching for all rents Unpaid");

            //call the repository method, witch has the info about the RENT
            Long qtyRentUnpaid = rentRepository.countUnpaidRents();

            if(qtyRentUnpaid == null)
            {
                throw new ResourceNotFoundException("Oops! The database does not contain any Unpaid Rent");
            }
            return qtyRentUnpaid;
        }
        catch (Exception e)
        {
            LoggerUtil.error("While searching for unpaid rentals, an error occurred. " + e.getMessage());
            throw new ResourceNotFoundException("Could not find a number of unpaid rentals");
        }
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

    /**                 ---
     * These methods bellow, display summary information
     * within the cards, by clicking on tge buttons
     *                  ---
     * */

    /** Endpoint to get the all rents witch the status is "NEW"
     *  Pointing to indexScript.js (CARD) */
    @GetMapping("/listRentStatusNew") //GetMapping annotated methods handle the HTTP GET requests matched with the given URI expression
    public List<SummaryRentStatusProjection> getListRentStatusNew()
    {
        try
        {
            LoggerUtil.info("Search for rentals that have a new status.");

            if(rentRepository.getNewRentsList() == null)
            {
                throw new ResourceNotFoundException("Oops! The database does not contain any rentals with a 'new' status");
            }

            LoggerUtil.info("Found " + rentRepository.getNewRentsList() + " new status rents");
            return rentRepository.getNewRentsList();
        }
        catch (Exception e)
        {
            LoggerUtil.error("Attempting to obtain rentals with new status resulted in an error. " + e.getMessage());
            throw new ResourceNotFoundException("Could not find a list of rentals with a status new.");
        }
    }

    /** Endpoint to get the all rents witch the status is "IN PROGRESS"
     *  Pointing to indexScript.js (CARD) */
    @GetMapping("/listRentStatusInProgress") //GetMapping annotated methods handle the HTTP GET requests matched with the given URI expression
    public List<SummaryRentStatusProjection> getListRentStatusInProgress()
    {
        try
        {
            LoggerUtil.info("Get a list of current rentals in progress.");

            if(rentRepository.getInProgressRentsList() == null)
            {
                throw new ResourceNotFoundException("Oops! The database does not contain any rentals with a 'in progress' status");
            }

            LoggerUtil.info("Found " + rentRepository.getNewRentsList() + " rentals in progress.");
            return rentRepository.getInProgressRentsList();
        }
        catch (Exception e)
        {
            LoggerUtil.error("While searching for rentals with an ongoing status, an error occurred. " + e.getMessage());
            throw new ResourceNotFoundException("Could not find a list of rentals with a status in progress.");
        }
    }
}
