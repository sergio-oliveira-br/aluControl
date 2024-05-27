/**
 * This is for the mySQL Data Base - Table Customer
 *      >> Class Controller <<
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */
package com.alucontrol.backendv1.Controllers.Customer;

import com.alucontrol.backendv1.Model.Customer.Customer;
import com.alucontrol.backendv1.Repository.Customer.CustomerRepository;
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
     * Endpoint to get back the Number of Unpaid Rents
     */
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }
}
