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
package com.alucontrol.backendv1.Controllers.Customer;

import com.alucontrol.backendv1.Exception.ResourceNotFoundException;
import com.alucontrol.backendv1.Model.Customer;
import com.alucontrol.backendv1.Repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
/** This controller contains specific methods for custom operations */
public class CustomerStatisticsController
{
    //Repository for access to product data
    private final CustomerRepository customerRepository;

    //Constructor responsible for injecting the repository
    public CustomerStatisticsController(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    /**
     * Endpoint to get back the list of all customer, it will display on Customer.html
     * Pointing to customerScript.js
     */
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers()
    {
        //call the repository method, witch has the info about the Customer
        List<Customer> customers = customerRepository.findAll();

        //handle exception
        if(customerRepository.findAll().isEmpty())
        {
            throw new ResourceNotFoundException("From: Customer Controller: I couldn't find any customers in the database");
        }
        return ResponseEntity.ok(customers);
    }
}
