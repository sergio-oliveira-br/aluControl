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
 Page: All
 Objective: When the page is loaded,
 all information will be displayed
 */
$(document).ready(function()
{
    //Create a table with all customers via AJAX
    loadCustomers();

});



/**
 Page: Customers
 Item: Table
 Method: Create a table with
 all customers via AJAX
 */
function loadCustomers()
{
    ajaxRequest("/customers", function (data)
    {
        //first clean
        $('#customerList').empty();

        //Iterate over clients and add them to the list
        data.forEach(function(customer)
        {
            $('#customerList').append('<tr>' +
                '<td>' + customer.id + '</td>' +
                '<td>' + customer.firstName + '</td>'+
                '<td>' + customer.lastName + '</td>' +
                '<td>' + customer.city + '</td>' +
                '</tr>');
        });
    });
}

