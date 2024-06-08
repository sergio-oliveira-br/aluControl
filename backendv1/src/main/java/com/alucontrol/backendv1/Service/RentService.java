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
package com.alucontrol.backendv1.Service;

import com.alucontrol.backendv1.Exception.ResourceNotFoundException;
import com.alucontrol.backendv1.Util.LoggerUtil;
import com.alucontrol.backendv1.Model.Product;
import com.alucontrol.backendv1.Repository.ProductRepository;
import com.alucontrol.backendv1.Repository.RentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**This Service Class has methods that contain business logic.*/
@Service
public class RentService
{
    //Repository for access to Rent data
    private final RentRepository rentRepository;
    private final ProductRepository productRepository;

    //Constructor responsible for injecting the repository
    public RentService(RentRepository rentRepository, ProductRepository productRepository)
    {
        this.rentRepository = rentRepository;
        this.productRepository = productRepository;
    }


    /** Used: Product Create Update Controller
     *  Method: Subtracting (item) stock when starting a rental.*/
    public void subtractStockByRentalDates(String itemDescription, int quantity, Date rentStarts, Date rentEnds)
    {
        //Search the product by ID
        //Optional: Used to imply that a value may be present or absent in a given circumstance
        Optional<Product> productOptional = productRepository.findByItemDescription(itemDescription);

        //Check if the product was found
        if(productOptional.isPresent())
        {
            //Retrieve the value contained in the Optional and allocate it to a Product product variable
            Product product = productOptional.get();

            //Check if the product is available in stock
            if(product.getItemAvailableQty() >= quantity)
            {
                //Create a log
                LoggerUtil.info("Renting Item: " + product.getItemDescription());
                LoggerUtil.info("Getting the Item Available Qty: " + product.getItemAvailableQty());
                LoggerUtil.info("Getting the quantity: " + quantity);


                //Take the quantity out of the stock
                product.setItemAvailableQty(product.getItemAvailableQty() - quantity);

                //Create a log
                LoggerUtil.info("The new Qty Available is:  " + product.getItemAvailableQty());

                productRepository.save(product);
            }

            //Exception: out off stock
            else
            {
                throw new ResourceNotFoundException("The product '" + itemDescription + "' does not have enough in stock.");
            }
        }
        //Exception: ID incorrect, product was not found
        else
        {
            throw new ResourceNotFoundException("The product '" + itemDescription + "' does not exist.");
        }
    }


    /** Used: Product Create Update Controller
     *  Method: Adding (item) stock when closing a rental.*/
    public void addStockByRentalStatusFinished(String itemDescription, int quantity)
    {
        System.out.println("Received parameters: itemDescription={}, quantity={}" + itemDescription + quantity);
        //Search the product by ID
        //Optional: Used to imply that a value may be present or absent in a given circumstance
        Optional<Product> productOptional = productRepository.findByItemDescription(itemDescription);

        //Check if the product was found
        if(productOptional.isPresent())
        {
            //Retrieve the value contained in the Optional and allocate it to a Product product variable
            Product product = productOptional.get();

            //Add quantity to available stock
            product.setItemAvailableQty(product.getItemAvailableQty() + quantity);
            productRepository.save(product);
        }
        //Exception: ID incorrect, product was not found
        else
        {
            throw new ResourceNotFoundException("The product '" + itemDescription+ "' was not found");
        }

    }
}
