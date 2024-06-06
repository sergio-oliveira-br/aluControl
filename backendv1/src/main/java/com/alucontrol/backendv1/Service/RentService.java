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

import com.alucontrol.backendv1.Controllers.Exception.ResourceNotFoundException;
import com.alucontrol.backendv1.Model.Product;
import com.alucontrol.backendv1.Model.Rent;
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


    /** Used: removeItemByDate() and
     *  Method: This method checks if the current date is within the rental period */
    //Parameters rentStarts and rentEnds, indicating that they are the start and end dates of the rental, respectively
    private boolean isCurrentDateIsWithinRentalDate(Date rentStarts, Date rentEnds)
    {

        Date currentDate = new Date(); //get the current date

        //Verify that the current date is between the start and end date of the rental
        return (currentDate.after(rentStarts) || currentDate.equals(rentStarts)) &&
                (currentDate.before(rentEnds) || currentDate.equals(rentEnds));
    }


    /** Used: Product Create Update Controller
     *  Method: Subtracting (item) stock when starting a rental.*/
    private void subtractStockByRentalDates(Long productId, int quantity, Date rentStarts, Date rentEnds)
    {
        //Search the product by ID
        //Optional: Used to imply that a value may be present or absent in a given circumstance
        Optional<Product> productOptional = productRepository.findById(productId);

        //Check if the product was found
        if(productOptional.isPresent())
        {
            //Retrieve the value contained in the Optional and allocate it to a Product product variable
            Product product = productOptional.get();

            //Check if the product is available in stock
            if(product.getItemAvailableQty() >= quantity)
            {
                //Check if the current date is between the start and end date of the rental
                if(isCurrentDateIsWithinRentalDate(rentStarts, rentEnds))
                {
                    //Take the quantity out of the stock
                    product.setItemAvailableQty(product.getItemAvailableQty() - quantity);
                    productRepository.save(product);
                }
                //Exception: current date is out off start and end date of the rental
                else
                {
                    throw new ResourceNotFoundException("The product with ID " + productId+ " cannot be rented." +
                            "\nCurrent date is out off start and end date of the rental");
                }
            }
            //Exception: out off stock
            else
            {
                throw new ResourceNotFoundException("The product with ID " + productId+ " does not have enough in stock.");
            }
        }
        //Exception: ID incorrect, product was not found
        else
        {
            throw new ResourceNotFoundException("The product with ID " + productId+ " does not exist.");
        }
    }


    /** Used: Product Create Update Controller
     *  Method: Adding (item) stock when closing a rental.*/
    private void addStockByRentalDates(Long productId, int quantity)
    {
        //Search the product by ID
        //Optional: Used to imply that a value may be present or absent in a given circumstance
        Optional<Product> productOptional = productRepository.findById(productId);

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
            throw new ResourceNotFoundException("The product with ID " + productId+ " was not found");
        }
    }
}
