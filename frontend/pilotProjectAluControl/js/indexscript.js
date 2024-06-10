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
$(document).ready(function ()
{
    //This card display the num of rent is "Unpaid"
    loadNumRentUnpaid();

    //This card display the num of rent is witch Status is "New"
    loadNumRentStatusNew();

    //This card display the num of rent is witch Status is "In Progress"
    loadRentStatusInProgress();
})

/** Function: Handle errors, showing the error message to the user */
function handleError(errorMessage)
{
    alert(errorMessage);
}


/**
 Page: Index
 Item: Card (UNPAID)
 Method: Obtain the number of rent witch the status is UNPAID
 */
function loadNumRentUnpaid ()
{
    $.ajax({ //allows updating parts of a web page without reloading the entire page
        url: "/qtyRentUnpaid", //indicates the endpoint
        type: "GET", //HTTP request methods used to retrieve data from the server (backend), indicating by the endpoint specified by the URL
        success: function(data)
        {
            //Providing a feedback according the data
            if(data == 0)
            {
                $('#loadRentUnpaid').text("That's Good! All your rentals have been paid");
            }

            else
            {
                $('#loadRentUnpaid').text('You have ' + data + ' rentals unpaid.');
                alert("You have " + data + " rentals unpaid.");
            }
        },
        error: function(xhr, status, error)
        {
            console.log(error)

            //Msg from BackEnd Exception
            let errorMessage = xhr.responseText;
            alert("From the Server: " + errorMessage);
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
    $.ajax({ //allows updating parts of a web page without reloading the entire page
        url: "/qtyRentStatusNew", //indicates the endpoint
        type: "GET", //HTTP request methods used to retrieve data from the server (backend)
        success: function(data)
        {
            $('#loadRentStatusNew').text('You have ' + data + ' New Rents')
        },
        error: function(xhr, status, error)
        {
            console.error(error);

            //Msg from BackEnd Exception
            let errorMessage = xhr.responseText;
            alert("From the Server: " + errorMessage);
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
    $.ajax({ //allows updating parts of a web page without reloading the entire page
        url: "/qtyRentStatusInProgress", //indicates the endpoint
        type: "GET", //HTTP request methods used to retrieve data from the server (backend)
        success: function(data)
        {
            $('#loadRentStatusInProgress').text('You have ' + data + ' Rents In Progress');
        },
        error: function(xhr, status, error)
        {
            console.error(error);

            //Msg from BackEnd Exception
            let errorMessage = xhr.responseText;
            alert("From the Server: " + errorMessage);
        }
    })
}


/**
 Page: Index
 Item: Card (RENT STATUS NEW) -> BUTTON -> Open Modal
 Method: The modal display a table with ALL Rents witch status is new
 */
function displayAllRentStatusNew()
{
    alert("All rents have been paid");


    <!-- Open the modal -->
    let displayAllRentStatusNewModal = new bootstrap.Modal(document.getElementById('displayAllRentStatusNewModal'));
    displayAllRentStatusNewModal.show();

    //Display the table
    loadRentListStatusNew();
}

/**                                     ------------
 Page: Index
 Item: Card (RENT STATUS NEW) -> BUTTON -> Open Modal
 Method: The modal display a table with ALL Rents witch status is new
 */

function loadRentListStatusNew()
{
    $.ajax({
        url: "/listRentStatusNew", //indicates the endpoint, (HomeController)
        type: "GET", //HTTP request methods used to RETRIEVE data from the server (backend)

        success: function(data)
        {
            //clean
            $('#rentListStatusNew').empty();

            //Iteration
            data.forEach(function(rent)
            {
                $('#rentListStatusNew').append('<tr>' +
                '<td>' + rent.rentFirstName + '</td>' +
                '<td>' + rent.rentItem + '</td>' +
                '<td>' + rent.rentPaymentStatus + '</td>' +
                '<td>' + rent.rentTotalPrice  + '</td>' +
                '<td>' + rent.rentStatus + '</td>');
            });
        },
        error: function(xhr, status, error)
        {
            console.log(error);
            let errorMessage = xhr.responseText;
            alert("From the Server: " + errorMessage);
        }
    });
}


/**
 Page: Index
 Item: Card (RENT STATUS IN PROGRESS) -> BUTTON -> Open Modal
 Method: The modal display a table with ALL Rents witch status is IN PROGRESS
 */
function displayAllRentStatusInProgress()
{
    alert("All rents have been paid");


    <!-- Open the modal -->
    let displayAllRentStatusInProgressModal = new bootstrap.Modal(document.getElementById('displayAllRentStatusInProgressModal'));
    displayAllRentStatusInProgressModal.show();

    //Display the table
    loadRentListStatusInProgress();
}

function loadRentListStatusInProgress()
{
    $.ajax({
        url: "/listRentStatusInProgress", //indicates the endpoint, (HomeController)
        type: "GET", //HTTP request methods used to RETRIEVE data from the server (backend)

        success: function(data)
        {
            //clean
            $('#rentListStatusInProgress').empty();

            //Iteration
            data.forEach(function(rent)
            {
                $('#rentListStatusInProgress').append('<tr>' +
                    '<td>' + rent.rentFirstName + '</td>' +
                    '<td>' + rent.rentItem + '</td>' +
                    '<td>' + rent.rentPaymentStatus + '</td>' +
                    '<td>' + rent.rentTotalPrice  + '</td>' +
                    '<td>' + rent.rentStatus + '</td>');
            });
        },
        error: function(xhr, status, error)
        {
            console.log(error);
            let errorMessage = xhr.responseText;
            alert("From the Server: " + errorMessage);
        }
    });
}










