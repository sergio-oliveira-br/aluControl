/**
 * This is for the mySQL Data Base - Table Product/Items
 *      >> Class Controller <<
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */


package controller;

import repository.ProductRepository;
import data.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController
{
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    //Need to include thr Repository

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
