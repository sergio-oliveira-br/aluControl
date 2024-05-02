// script.js


//Testing the JS connection
function displayTestMessage() {
    alert("O arquivo JavaScript foi carregado com sucesso!");
}



/**
     Page: All
     Objective: When the page is loaded,
        all information will be displayed
 */
$(document).ready(function()
{
    //Page: Index
    loadNumberCustomers(); //this is responsible to display the number of records I have on DB

    //Page: Customer
    loadCustomers(); //this is the table of all my customers

});



/**
    Page: Customers
    Item: Table
    Method: Create a table with
        all customers created via AJAX
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
     Page: Index
     Item: Card
     Method: Get a number of Customers exits
        on database (test)
 */
function loadNumberCustomers()
{
    $.ajax({url: "/qtyCustomers", type: "GET", success: function(data)
        {
            //clean???

            $('#loadNumberCustomers').text('Number of customers: ' + data);

        },
        error: function(xhr, status, error)
        {
            console.error(error);
        }
    })
}


