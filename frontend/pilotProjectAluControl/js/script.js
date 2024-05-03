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

    //Page: Rent
    loadRent();

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
     Item: Table
     Method: Create a table with
     all customers via AJAX
 */
function loadRent()
{
    $.ajax({url: "/rent", type: "GET", success: function(data)
        {
            //first clean
            $('#rentList').empty();

            //Iteration
            data.forEach(function(rent)
            {
                $('#rentList').append('<tr>' +
                '<td>' + rent.id + '</td>' +
                '<td>' + rent.rentFirstName + '<td>');
            });
        },
        error: function(xhr, status, error)
        {
            console.error(error);
        }
    });
}

/**
     Page: Rent
     Item: Form -> Days = (End - Start)
     Method: Simple math to calculate the days
*/
//Making the Math
function mathDays(start, end)
{
    let start_Date = new Date(start);
    let end_Date = new Date(end);
    let diffTime = Math.abs(end_Date - start_Date);
    let diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    return diffDays;
}
//Using the Method
function loadRentDays()
{
    //Variables
    let newStart = document.getElementById('rentStarts').value;
    let newEnd = document.getElementById('rentEnds').value;

    //Call the method
    let rentTotalDays = mathDays(newEnd, newStart);

    //Write in the field
    document.getElementById('rentTotalDays').value = rentTotalDays;
}
//Update everytime that one of these two field is changed
document.getElementById('rentStarts').addEventListener('change',loadRentDays);
document.getElementById('rentEnds').addEventListener('change',loadRentDays);


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