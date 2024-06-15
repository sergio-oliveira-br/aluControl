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



import com.alucontrol.backendv1.Controllers.Customer.CustomerCreateUpdateController;
import com.alucontrol.backendv1.Model.Customer;
import com.alucontrol.backendv1.Model.Product;
import com.alucontrol.backendv1.Repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

//Reference: https://site.mockito.org
/** This test focuses on the "saveCustomer" method by checking:
 *
 -> Whether the method correctly saves a client in the database.
 -> If the method returns a Customer object with the correct data.
 -> If the method throws an exception when an error occurs.
 -> Whether the method creates a correct log when the client is saved successfully or when an error occurs.*/

@ExtendWith(MockitoExtension.class) //enables the use of Mockito in the test.
public class CustomerCreateUpdateControllerTest
{
    //Inject the CustomerCreateUpdateController Controller Instance into the Test.
    @InjectMocks
    private CustomerCreateUpdateController customerCreateUpdateController;

    //Creates a Mock instance of the CustomerRepository
    @Mock
    private CustomerRepository customerRepository;

    //The success case, where the customer is saved correctly in the database.
    @Test
    public void saveCustomerSuccess()
    {
        //Create a instance object
        Customer customerTest = new Customer();
        customerTest.setFirstName("Sergio");
        customerTest.setLastName("Oliveira");
        customerTest.setPhoneNumber("0862721772");
        customerTest.setAdditionalInfo("This is myTest");
        customerTest.setCity("Dublin");


        //Stubbing
        //Sets the behavior of the Repository save method, returning the created client
        //When the save method is called in the customerRepository object with the customerTest argument, return the customerTest itself as a result
        when(customerRepository.save(any(Customer.class))).thenReturn(customerTest);

        ResponseEntity<Customer> response = customerCreateUpdateController
                .saveCustomer("Sergio", "Oliveira", "0862721772", "This is myTest", "Dublin");

        //Assert:
        //Tests the saveCustomer method, which saves a new client to the database
        //The method is expected to return a response with a status code of 200 (OK) and the client saved
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customerTest, response.getBody());
        verify(customerRepository).save(any(Customer.class));
    }

    //The error case, where an exception is thrown when trying to save the client to the database.
    @Test
    public void saveCustomerError() throws Exception
    {
        //Mockito will throw a RuntimeException exception instead of actually saving the client to the database
        doThrow(new RuntimeException("Error saving customer")).when(customerRepository).save(any(Customer.class));

        ResponseEntity<Customer> response = customerCreateUpdateController
                .saveCustomer("Sergio", "Oliveira", "0862721772", "This is my Test", "Dublin");

        //Checks whether the server response is an internal error (500) and whether the response body is empty
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        //Checks if the response body is empty, as there is nothing to be returned in case of error
        assertNull(response.getBody());
    }
}
