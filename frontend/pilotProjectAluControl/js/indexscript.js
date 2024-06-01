$(document).ready(function ()
{
    //This card display the num of rent is "Unpaid"
    loadNumRentUnpaid();

    //This card display the num of rent is witch Status is "New"
    loadNumRentStatusNew();

    //This card display the num of rent is witch Status is "In Progress"
    loadRentStatusInProgress();
})


/**
 Page: Index
 Item: Card (UNPAID)
 Method: Obtain the number of rent witch the status is UNPAID
 */
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


/**
 Page: Index
 Item: Card (RENT STATUS NEW)
 Method: Obtain the number of rent witch the status is NEW
 */
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

/**
 Page: Index
 Item: Card (Rent Status: IN PROGRESS)
 Method: Obtain the number of rent witch the status is "In Progress"
 */
function loadRentStatusInProgress()
{
    $.ajax({
        url: "/qtyRentStatusInProgress",
        type: "GET",
        success: function(data)
        {
            $('#loadRentStatusInProgress').text('You have ' + data + ' Rents In Progress');
        },
        error: function(xhr, status, error)
        {
            console.error(error);
        }
    })
}