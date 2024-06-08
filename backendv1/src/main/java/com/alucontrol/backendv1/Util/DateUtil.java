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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**This class DateUtil contains methods to convert strings into date objects.
 * This enhances code reuse and maintenance, and centralizes date conversion logic in a single location.*/
public class DateUtil
{
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static Date convertStringToDate(String dateStr) throws ParseException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        //Create a log
        LoggerUtil.info("Date converted, from:" + dateStr + " to: " + dateFormat.format(new Date()));

        return dateFormat.parse(dateStr);
    }
}
