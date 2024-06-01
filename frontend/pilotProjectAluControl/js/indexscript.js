/**
 Page: Index
 Item: Card
 Method: Obtain a list of outputs created by clients in the database
 */

$(document).ready(function ()
{
    //this is responsible to display the number
    loadNumberCustomers();

    //This card display the num of rent is Unpaid
    loadNumRentUnpaid();

    loadNumRentStatusNew();
})


function loadNumberCustomers()
{
    $.ajax({url: "/qtyCustomers", type: "GET", success: function(data)
        {
            //clean???

            $('#loadNumberCustomers').text('Number of customers: ' + data);
            console.log(data);

        },
        error: function(xhr, status, error)
        {
            console.error(error);
            console.log(status)
        }
    })
}


function loadNumRentUnpaid ()
{
    $.ajax({url: "/qtyRentUnpaid", type: "GET", success: function(data)
        {
            $('#loadRentUnpaid').text('Number of rents Unpaid is: ' + data);
            console.log("The number of rents Unpaid is: ...")
        },
        error: function(xhr, status, error)
        {
            console.error(error);
        }
    })
}

function loadNumRentStatusNew()
{
    $.ajax({
        url: "/qtyRentStatusNew",
        type: "GET",
        success: function(data)
        {
            $('#loadRentStatusNew').text('You have ' + data + ' New Rents')
        },
        error: function(xhr, status, error)
        {
            console.error(error);
        }
    })
}