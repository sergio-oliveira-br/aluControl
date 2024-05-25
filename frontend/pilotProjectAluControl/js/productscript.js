/**
 Page: Product
 Method: Ready()

 Info: As soon as the page's Document Object Model (DOM)
 is ready for manipulation, JavaScript code can be executed.
 */
$(document).ready(function()
{
    //Load the table
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

