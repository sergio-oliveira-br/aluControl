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





/**
    Page: Rent
    Item: Form -> Total Price = (Days * Qty * UnitPrice)
    Method: Simple math to calculate the total price
*/
//Method to calculate
function loadTotalPrice()
{
    //Variable
    let newTotalDays =parseInt(document.getElementById('rentTotalDays').value);
    let newTotalQty = parseInt(document.getElementById('rentQtyItem').value);
    let newUnitPrice = parseInt(document.getElementById('rentPrice').value);

    //Call the Method
    let newRentTotalPrice = Math.ceil(newTotalDays * newTotalQty * newUnitPrice);

    //Write the field
    document.getElementById('rentTotalPrice').value = newRentTotalPrice;
}
//Update everytime that one of these three field is changed
document.getElementById('rentTotalDays').addEventListener("change", loadTotalPrice)
document.getElementById('rentQtyItem').addEventListener("change", loadTotalPrice)
document.getElementById('rentPrice').addEventListener("change", loadTotalPrice)
document.getElementById('rentStarts').addEventListener('change',loadTotalPrice);
document.getElementById('rentEnds').addEventListener('change',loadTotalPrice);



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

  






