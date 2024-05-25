// script.js






/**
     Page: All
     Objective: When the page is loaded,
        all information will be displayed
 */
$(document).ready(function()
{
     //this is responsible to display the number of records I have on DB

    //Page: Customer
    loadCustomers(); //this is the table of all my customers
    
});






/**
 Page: Products
 Item: Table
 Method: Create a table with
 all products via AJAX
*/
function loadProduct()
{
    $.ajax({url: "/product", type: "GET", success: function(data)
        {
            //first clean
            $('#productList').empty();

            //Iteration
            data.forEach(function(product)
            {
                $('#productList').append('<tr>' +
                        '<td>' + product.id + '</td>' +
                        '<td>' + product.itemDescription + '</td>' +
                        '<td>'+ product.itemQuantity+'</td>'
                );
            });
        },
        error: function(xhr, status, error)
        {
            console.error(error);
        }
    });
}








/**
 Page: Dashboard
 Item: Chart
 Method: Display rent value per dau
*/


  






