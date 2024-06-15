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
package com.alucontrol.backendv1.Controllers.Product;

import com.alucontrol.backendv1.Model.Product;
import com.alucontrol.backendv1.Repository.ProductRepository;
import com.alucontrol.backendv1.Util.LoggerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**This controller is dedicated to endpoints that create and update records
 * It is the responsibility of this layer to receive requests, call methods from the service layer, and return HTTP responses */
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
        try
        {
            //create a new product object and set its attributes
            Product product = new Product();
            product.setItemDescription(itemDescription);
            product.setItemQuantity(itemQuantity);

            //Initialize itemAvailableQty with the same itemQuantity value
            product.setItemAvailableQty(itemQuantity);

            Product savedProduct = productRepository.save(product);
            LoggerUtil.info("Saving product: " + product.getItemDescription()); //create a log

            return ResponseEntity.ok(savedProduct);
        }
        catch (Exception e) {
            LoggerUtil.error("An error occurred while saving product data: " + e.getMessage(), e); //create log
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); //Return an internal error
        }

    }

    /** Endpoint to get a specific rent by ID (by clicking on Edit into the table)*/
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable Long id)
    {
        try
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
        } catch (Exception e)
        {
            LoggerUtil.error("Error occurred while fetching product by ID: " + id, e); //create a log
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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

            LoggerUtil.info("Updating product: " + product.getItemDescription()); //create a log

            return ResponseEntity.ok(savedProduct);
        }
        else
        {
            LoggerUtil.error("Product with ID: " + id + " not found"); //create a log
            return ResponseEntity.notFound().build();
        }
    }
}
