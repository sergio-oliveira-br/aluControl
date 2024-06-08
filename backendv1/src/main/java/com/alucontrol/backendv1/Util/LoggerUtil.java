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
package com.alucontrol.backendv1.Util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** This is a utility class that provides a way to create and manage loggers.
 *  The purpose of this is to create Loggers and set them up with different levels.*/
public class LoggerUtil
{

    //Creating a static logger
    private static final Logger logger = LogManager.getLogger(LoggerUtil.class);

    //Save an INFO msg
    public static void info(String message) {
        logger.info(message);
    }

    //Save a DEBUG msg
    public static void debug(String message) {
        logger.debug(message);
    }

    //Save a WARN msg
    public static void warn(String message) {
        logger.warn(message);
    }

    //Save an ERROR msg
    public static void error(String message) {
        logger.error(message);
    }

    //Save an ERROR msg and Exception
    public static void error(String message, Throwable t) {
        logger.error(message, t);
    }

}
