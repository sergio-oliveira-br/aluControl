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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** This controller is dedicated to endpoints that read data */
@RestController
public class ProductReadController
{
    //Repository for access to product data
    private final ProductRepository productRepository;

    //Constructor responsible for injecting the repository
    public ProductReadController(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    /** Endpoint to get back all products */
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProduct()
    {
        List<Product> products = productRepository.findAll(); //findAll() is method inherited from CrudRepository
        return ResponseEntity.ok(products);
    }

}
