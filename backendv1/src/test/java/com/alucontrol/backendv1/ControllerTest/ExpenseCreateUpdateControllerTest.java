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

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/** This test focuses on the "saveRent" method by checking:

 -> Whether the method correctly saves a rent in the database.
 -> If the method returns a Rent object with the correct data.
 -> If the method throws an exception when an error occurs.
 -> Whether the method creates a correct log when the client is saved successfully or when an error occurs.*/
@ExtendWith(MockitoExtension.class)
public class ExpenseCreateUpdateControllerTest
{
    //Inject the ExpenseCreateUpdateController Controller Instance into the Test

    //Create a Mock Instance of the ExpenseRepository

    /** The success case: where the product is saved correctly in DB*/


    /** The error case, where an exception is thrown when trying to save the product to the database */

}
