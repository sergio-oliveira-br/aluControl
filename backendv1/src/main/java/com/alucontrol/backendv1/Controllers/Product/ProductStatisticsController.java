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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** This controller contains specific methods for custom operations */
@RestController
public class ProductStatisticsController
{
    //Repository for access to product data
    private final ProductRepository productRepository;

    //Constructor responsible for injecting the repository
    public ProductStatisticsController(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
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
