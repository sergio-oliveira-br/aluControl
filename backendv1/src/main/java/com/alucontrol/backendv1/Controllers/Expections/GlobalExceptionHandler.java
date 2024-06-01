/**
 *     Global handler of exceptions
 *      >> Class Controller <<
 * Development starting on 30, April 2024
 * Author: Sergio Oliveira
 * Version 1.0
 */

package com.alucontrol.backendv1.Controllers.Expections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Capture exceptions thrown anywhere in the application
 *                  ---
 * This approach ensures that the application
 * handles exceptions consistently and robustly,
 * improving the reliability and usability of the system.*/
@ControllerAdvice
public class GlobalExceptionHandler
{
    //Handles exceptions from resources not found
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    //Handles all other generic exceptions
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Oops Something, there are something wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
