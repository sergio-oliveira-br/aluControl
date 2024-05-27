/**
 * This is for the mySQL Data Base - Table Customer
 *      >> Class Controller <<
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */

package com.alucontrol.backendv1.Controllers.Customer;

import com.alucontrol.backendv1.Repository.Customer.CustomerRepository;
import com.alucontrol.backendv1.Model.Customer.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController
{

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /** Endpoint to send customers */
    @PostMapping("/saveCustomer")
    public ResponseEntity<Customer> saveCustomer(@RequestParam("firstName") String firstName,
                                                 @RequestParam("lastName") String lastName,
                                                 @RequestParam("phoneNumber") String phoneNumber,
                                                 @RequestParam("additionalInfo") String additionalInfo,
                                                 @RequestParam("city") String city)
    {

         Customer customer = new Customer();
         customer.setFirstName(firstName);
         customer.setLastName(lastName);
         customer.setPhoneNumber(phoneNumber);
         customer.setAdditionalInfo(additionalInfo);
         customer.setCity(city);

         Customer savedCustomer = customerRepository.save(customer);


        System.out.println("\nReceived client data: " +
                "\n" + firstName +
                "\n" + lastName +
                "\n" + phoneNumber +
                "\n" + additionalInfo +
                "\n" + city);

        return ResponseEntity.ok(savedCustomer);
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
