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

package com.alucontrol.backendv1.Exception;

import com.alucontrol.backendv1.Util.LoggerUtil;
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
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex)
    {
        LoggerUtil.error("ResourceNotFoundException handled: " + ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    //Handles all other generic exceptions
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGenericException(Exception ex)
    {
        LoggerUtil.error("GenericException handled: " + ex.getMessage(), ex);
        return new ResponseEntity<>("Oops! There are something wrong =[", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
