/**
 * This is for the mySQL Data Base - Table Product/Items
 *      >> Class Controller <<
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */


package com.alucontrol.backendv1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController
{
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    //Need to include the Repository

    /** Endpoint to send Products */
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


    /** Endpoint to get back all products */
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProduct()
    {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    /** Endpoint to get back the sum of Scaffolds from DB */
    @GetMapping("/sumScaffolds")
    public ResponseEntity<Long> getSumScaffolds()
    {
        Long sumScaffolds = productRepository.getSumScaffolds();
        return ResponseEntity.ok(sumScaffolds);
    }

}
