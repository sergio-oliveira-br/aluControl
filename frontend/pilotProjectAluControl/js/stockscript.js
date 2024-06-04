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

/**
 Page: Stock
 Item: Chart
 Method: Display Chart -
 Reference: https://www.chartjs.org/docs/latest/samples/line/multi-axis.html
 */
$(document).ready(function ()
{
    //each product in our database
    $.ajax({url: '',
        method: 'GET',
        dataType: 'json',
        success: function (data)
        {
            //variables


            //graph
            const ctx = document.getElementById('myStockChart');

            //config
            const config = new Chart(ctx2,{})

            //setup

        }
    });
}