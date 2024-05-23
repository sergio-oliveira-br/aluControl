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

    //Page: Product
    loadProduct();

});



/**
    Page: Customers
    Item: Table
    Method: Create a table with
        all customers via AJAX
*/
function loadCustomers()
{
    $.ajax({url: "/customers", type: "GET", success: function(data)
        {
            //first clean
            $('#customerList').empty();

            //Iterate over clients and add them to the list
            data.forEach(function(customer)
            {
                $('#customerList').append('<tr>' +
                                                '<td>' + customer.id + '</td>' +
                                                '<td>' + customer.firstName + '<td>'+
                                                '<td>' + customer.lastName + '<td>' +
                                                '<td>' + customer.city + '<td>' +
                                          '</tr>');
            });
        },
        error: function(xhr, status, error)
        {
            console.error(error);
        }
    });
}





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
document.addEventListener("DOMContentLoaded", function()
{
    const ctx = document.getElementById('myChart');

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
            datasets: [{
                label: '# of Votes',
                data: [12, 19, 3, 5, 2, 3],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
});

  






