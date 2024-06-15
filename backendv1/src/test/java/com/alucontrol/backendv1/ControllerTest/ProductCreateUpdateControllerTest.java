/**
 * National College of Ireland - NCI
 *    Higher Diploma in Computing
 *         Final Project
 *              ---
 * Author: Sergio Vinicio da Silva Oliveira
 * ID: x23170981@student.ncirl.ie
 * Project Commencing May 2024
 * Version: 1.0 - Test
 */
package com.alucontrol.backendv1.ControllerTest;


//Reference: https://site.mockito.org

import com.alucontrol.backendv1.Controllers.Product.ProductCreateUpdateController;
import com.alucontrol.backendv1.Model.Product;
import com.alucontrol.backendv1.Repository.ProductRepository;
import com.alucontrol.backendv1.Util.LoggerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

/** This test focuses on the "saveProduct" method by checking:

 -> Whether the method correctly saves a Product in the database.
 -> If the method returns a Customer object with the correct data.
 -> If the method throws an exception when an error occurs.
 -> Whether the method creates a correct log when the client is saved successfully or when an error occurs.*/

@ExtendWith(MockitoExtension.class)
public class ProductCreateUpdateControllerTest
{
    //Inject the CustomerCreateUpdateController Controller Instance into the Test.
    @InjectMocks
    private ProductCreateUpdateController productCreateUpdateController;

    //Create a Mock Instance of the ProductRepository
    @Mock
    private ProductRepository productRepository;


    //The success case: where the product is saved correctly in DB
    @Test
    public void saveProductSuccess()
    {
        //create a instance object
        Product product = new Product();
        product.setItemDescription("ItemTest");
        product.setItemQuantity(99);

        /** Stubbing: Set the behavior of the Repository save method.
          * When the save method is called in the productRepository object
          * with the product argument, return the product itself as a result */
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ResponseEntity<Product> response;
        response = productCreateUpdateController.saveProduct(product.getItemDescription(), product.getItemQuantity());


        /** The method is expected to return a response with a status code of 200 (OK) and the client saved */
        assertEquals("Status code should be 200: ", HttpStatus.OK, response.getStatusCode());
        assertEquals("Returned customer should match: ", product, response.getBody());
        verify(productRepository).save(any(Product.class));

    }

    //The error case, where an exception is thrown when trying to save the product to the database.
    @Test
    public void saveProductError() throws Exception
    {
        //Mockito will throw a RuntimeException exception instead of actually saving the client to the database
        doThrow(new RuntimeException("Error saving product")).when(productRepository).save(any(Product.class));

        ResponseEntity<Product> response;
        response = productCreateUpdateController.saveProduct("itemTest99", 99);

        //Checks whether the server response is an internal error (500) and whether the response body is empty
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        //Checks if the response body is empty, as there is nothing to be returned in case of error
        assertNull(response.getBody());
    }
}
