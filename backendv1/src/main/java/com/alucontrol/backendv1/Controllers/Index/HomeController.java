package com.alucontrol.backendv1.Controllers.Index;


import com.alucontrol.backendv1.Repository.Rent.RentRepository;
import com.alucontrol.backendv1.Repository.Customer.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController
{

    private final CustomerRepository customerRepository;
    private final RentRepository rentRepository;

    public HomeController (CustomerRepository customerRepository, RentRepository rentRepository)
    {
        this.customerRepository = customerRepository;
        this.rentRepository = rentRepository;
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


    /** Endpoint to get back the number of customers from DB -> Pointing to indexScript.js*/
    @GetMapping("/qtyRentUnpaid")
    public ResponseEntity<Long> getQtyRentUnpaid()
    {
        Long qtyRentUnpaid = rentRepository.countUnpaidRents();
        return ResponseEntity.ok(qtyRentUnpaid);
    }

}
