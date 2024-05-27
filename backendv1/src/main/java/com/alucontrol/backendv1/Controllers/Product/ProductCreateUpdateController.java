/**
 * This is for the mySQL Data Base - Table Product/Items
 *      >> Class Controller <<
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */


package com.alucontrol.backendv1.Controllers.Product;

import com.alucontrol.backendv1.Model.Product.Product;
import com.alucontrol.backendv1.Repository.Product.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**This controller is dedicated to endpoints that create and update records*/
@RestController
public class ProductCreateUpdateController
{
    //Repository for access to product data
    private final ProductRepository productRepository;

    //Constructor responsible for injecting the repository
    public ProductCreateUpdateController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /** Endpoint to send Products to my DB*/
    @PostMapping("/saveProduct")
    public ResponseEntity<Product> saveProduct(@RequestParam("itemDescription") String itemDescription,
                                               @RequestParam("itemQuantity") int itemQuantity)
    {
        Product product = new Product();
        product.setItemDescription(itemDescription);
        product.setItemQuantity(itemQuantity);

        Product savedProduct = productRepository.save(product);

        //Double Check
        System.out.println("Received:" + "\n" + itemDescription);

        return ResponseEntity.ok(savedProduct);
    }
}
