/**
 * This is for the mySQL Data Base - Table Product/Items
 *      >> Class Controller <<
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */


package com.alucontrol.backendv1.Controllers.Product;

import com.alucontrol.backendv1.Model.Product;
import com.alucontrol.backendv1.Repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    /** Endpoint to get a specific rent by ID (by clicking on Edit into the table)*/
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable Long id)
    {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent())
        {
            return ResponseEntity.ok(productOptional.get());
        }

        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    /** Endpoint to update a specific rent by ID */
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct)
    {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent())
        {
            Product product = productOptional.get();

            product.setItemDescription(updatedProduct.getItemDescription());
            product.setItemQuantity(updatedProduct.getItemQuantity());

            Product savedProduct = productRepository.save(product);
            return ResponseEntity.ok(savedProduct);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
