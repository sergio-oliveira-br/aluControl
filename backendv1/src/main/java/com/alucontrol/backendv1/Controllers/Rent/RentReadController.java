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

import com.alucontrol.backendv1.Model.Rent;
import com.alucontrol.backendv1.Repository.RentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
/** This controller is dedicated to endpoints that read data */
public class RentReadController
{
    //Repository for access to product data
    private final RentRepository rentRepository;

    //Constructor responsible for injecting the repository
    public RentReadController(RentRepository rentRepository)
    {
        this.rentRepository = rentRepository;
    }

    /** Endpoint to get back all rent */
    @GetMapping("/rent")
    public ResponseEntity<List<Rent>> getAllRent()
    {
        List<Rent> rent = rentRepository.findAll();
        return ResponseEntity.ok(rent);
    }
}
