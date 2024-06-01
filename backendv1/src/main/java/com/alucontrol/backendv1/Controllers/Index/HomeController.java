package com.alucontrol.backendv1.Controllers.Index;


import com.alucontrol.backendv1.Repository.RentRepository;
import com.alucontrol.backendv1.Repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController
{
    private final RentRepository rentRepository;

    public HomeController (RentRepository rentRepository)
    {
        this.rentRepository = rentRepository;
    }

    @GetMapping("/")
    public String home()
    {
        return "index";
    }


    /** Endpoint to get back the number of customers from DB
     *  Pointing to indexScript.js*/
    @GetMapping("/qtyRentUnpaid")
    public ResponseEntity<Long> getQtyRentUnpaid()
    {
        Long qtyRentUnpaid = rentRepository.countUnpaidRents();
        return ResponseEntity.ok(qtyRentUnpaid);
    }
    

    /** Endpoint to get the number of rent witch has the status "NEW"
     *  Pointing to indexScript.js */
    @GetMapping("/qtyRentStatusNew")
    public ResponseEntity<Long> getQtyRentStatusNew()
    {
        Long qtyRentStatusInProgress = rentRepository.countRentStatusNew();
        return ResponseEntity.ok(qtyRentStatusInProgress);
    }

}
