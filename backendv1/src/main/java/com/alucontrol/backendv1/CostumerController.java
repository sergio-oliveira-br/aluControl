package com.alucontrol.backendv1;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CostumerController
{

    @PostMapping("/saveClient")
    public String saveClient(@RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("phoneNumber") String phoneNumber,
                             @RequestParam("additionalInfo") String additionalInfo,
                             @RequestParam("city") String city
    )
    {

        System.out.println("\nReceived client data: " +
                "\n" + firstName +
                "\n" + lastName +
                "\n" + phoneNumber +
                "\n" + additionalInfo +
                "\n" + city);

        return "Client data received";
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
