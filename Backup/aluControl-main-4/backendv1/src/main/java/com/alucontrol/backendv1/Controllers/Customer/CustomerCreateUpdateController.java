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

import com.alucontrol.backendv1.Repository.CustomerRepository;
import com.alucontrol.backendv1.Model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**This controller is dedicated to endpoints that create and update records*/
@RestController
public class CustomerCreateUpdateController
{
    //Repository for access to product data
    private final CustomerRepository customerRepository;

    //Constructor responsible for injecting the repository
    public CustomerCreateUpdateController(CustomerRepository customerRepository) {
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
}
