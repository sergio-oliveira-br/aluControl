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

/**                             ------------ Generic Function ------------ */
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
 Method: Generic function to perform an AJAX request and handle success and error responses
 Type: GET
 */
function ajaxRequest(url, successCallback)
{
    $.ajax({
        url: url,     //indicates the endpoint from the argument to the ajaxRequest function
        type: "GET",  //HTTP request methods used to RETRIEVE data from the server (backend)

        //If the request is successful, a callback function will be called from the argument to the ajaxRequest function
        success: successCallback,

        //If there is any error
        error: function(xhr, status, error)
        {
            console.error(error);
            let errorMessage = xhr.responseText;
            alert("From the Server: " + errorMessage);
        }
    });
}

/** Function: Handle errors, showing the error message to the user */
function handleError(errorMessage)
{
    alert(errorMessage);
}


/**
 Item: Card (UNPAID)
 Method: Obtain the number of rent witch the status is UNPAID
 */
function loadNumRentUnpaid ()
{
    //Call the generic function, that perform an AJAX request
    ajaxRequest("/qtyRentUnpaid", function (data)
    {
        if (data == 0)
        {
            $('#loadRentUnpaid').text("That's Good! All your rentals have been paid");
        }

        else
        {
            $('#loadRentUnpaid').text('You have ' + data + ' rentals unpaid.');
            alert("You have " + data + " rentals unpaid.");
        }
    });
}



/**
 Item: Card (RENT STATUS NEW)
 Method: Obtain the number of rent witch the status is NEW
 */
function loadNumRentStatusNew()
{
    //Call the generic function, that perform an AJAX request
    ajaxRequest("/qtyRentStatusNew", function (data)
    {
        $('#loadRentStatusNew').text('You have ' + data + ' New Rents')
    });
}



/**
 Item: Card (Rent Status: IN PROGRESS)
 Method: Obtain the number of rent witch the status is "In Progress"
 */
function loadRentStatusInProgress()
{
    //Call the generic function, that perform an AJAX request
    ajaxRequest("/qtyRentStatusInProgress", function (data)
    {
        $('#loadRentStatusInProgress').text('You have ' + data + ' Rents In Progress');
    });
}




/**                   ------------ Functions for the cards Rent Status: In Progress & New ------------ */

/** *                                   ------------ Generic Function ------------
 Page: Index
 Method: To load rent lists into a table within a modal.
 */
function loadRentList(url, tableSelector)
{
    //Call the generic function, that perform an AJAX request
    ajaxRequest(url, function(data)
    {
        //first clean
        $(tableSelector).empty();

        //Iteration
        data.forEach(function(rent)
        {
            $(tableSelector).append('<tr>' +
                '<td>' + rent.rentFirstName + '</td>' +
                '<td>' + rent.rentItem + '</td>' +
                '<td>' + rent.rentPaymentStatus + '</td>' +
                '<td>' + rent.rentTotalPrice  + '</td>' +
                '<td>' + rent.rentStatus + '</td></tr>');
        });
    });
}


/**                                  ------------ Generic Function ------------
 Page: Index
 Method: To display a modal and load the rent list
 */
function displayRentStatusModal(modalId, url, tableSelector)
{
    let modal = new bootstrap.Modal(document.getElementById(modalId));
    modal.show();

    //Call the generic function to load rent lists, then display into a table within a modal.
    loadRentList(url, tableSelector);
}


/**             ------------ Display the table content calling the generic methods  ------------
 Page: Index
 Item: Card -> BUTTON -> Open Modal
 Method: The modal will display a table with the info selected. (Rent Status: New or In Progress)
 */
//Rent Status: New
function displayAllRentStatusNew()
{
    displayRentStatusModal('displayAllRentStatusNewModal', '/listRentStatusNew', '#rentListStatusNew');
}

//Rent Status: In Progress
function displayAllRentStatusInProgress()
{
    displayRentStatusModal('displayAllRentStatusInProgressModal', '/listRentStatusInProgress', '#rentListStatusInProgress');
}







