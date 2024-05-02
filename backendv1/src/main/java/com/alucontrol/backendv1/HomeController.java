package com.alucontrol.backendv1;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController
{

    private final CustomerRepository customerRepository;

    public HomeController (CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/")
    public String home()
    {
        return "index";
    }



    /** Endpoint to get back the number of customers on DB */
    @GetMapping("/qtyCustomers")
    public ResponseEntity<Long> getQtyCustomers()
    {
        Long qtyCustomers = customerRepository.count();
        return ResponseEntity.ok(qtyCustomers);
    }



}
