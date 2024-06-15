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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

/** This test focuses on the "saveProduct" method by checking:
 *
 -> Whether the method correctly saves a client in the database.
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
}
