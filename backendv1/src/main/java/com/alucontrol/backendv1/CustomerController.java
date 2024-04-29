package com.alucontrol.backendv1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController
{

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

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



    /*
    @PostMapping("/api/client")
    public String saveClient(@RequestBody Costumer costumer)
    {
        System.out.println("Received client data: " + costumer);
               return "Client data received";
    }

     */
}
