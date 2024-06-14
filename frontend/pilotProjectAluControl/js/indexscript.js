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

    //This card display the num of rent is witch Status is :
    loadNumRentByStatus('New');
    loadNumRentByStatus('InProgress');

    //(MODAL)The script will load the available items and customers in the rental form when the page loads
    loadEditItemsForRentFormModal();
    updateLoadCustomerForRentForm();

    //(Modal)
    $('#editRentForm').on('submit', function(e) {
        e.preventDefault();
        submitEditForm();
    });

})


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
 Method: Obtain the number of rent witch the status is NEW
 */
function loadNumRentByStatus(status)
{
    //local variable
    let url = "/qtyRentStatus" + status; //indicates the endpoint
    let selector;

    if (status === 'New')
    {
        selector = '#loadRentStatusNew';
    }

    else if (status === 'InProgress')
    {
        selector = '#loadRentStatusInProgress';
    }

    //Call the generic function, that perform an AJAX request
    ajaxRequest(url, function (data)
    {
        $(selector).text('You have ' + data + ' Rents')
    });
}

/**                                    ------------ Generic Function ------------
 Page: Index
 Method: Display the current stock by clicking in the card called "stock"
*/
function loadCurrentStock()
{

    //Open the modal
    let stockModal = new bootstrap.Modal(document.getElementById('stockModal'));
    stockModal.show();

    //Call the generic function, that perform an AJAX request
    ajaxRequest("/product", function(data)
    {
        //first cleaning: This makes sure that the list will appear only once
        $('#productList').empty();

        //Iteration
        data.forEach(function(product)
        {
            $('#productList').append('<tr>' +
                    '<td>' + product.itemDescription + '</td>' +
                    '<td>' + product.itemAvailableQty + '</td>' +
                '</tr>'
            );
        });
    });
}




/**                   ------------ Functions for the cards Rent Status: In Progress & New ------------ */

/** *                                   ------------ Generic Function ------------
 Page: Index
 Method: To load rent lists into a table within a modal.
 */
function loadRentList(url, status, tableSelector)
{

    //Call the generic function, that perform an AJAX request
    ajaxRequest(url + '?status=' + status, function(data)
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
                '<td>' + rent.rentStatus + '</td>' +
                '<td><button class="btn btn-primary" onclick="openEditModal(' + rent.id + ')">Edit</button></td>' +
            '</tr>');
            console.log(rent.id);
        });
    });
}



/**                                  ------------ Generic Function ------------
 Page: Index
 Method: To display a modal and load the rent list
 */
function displayRentStatusModal(modalId, status, tableSelector)
{
    let modal = new bootstrap.Modal(document.getElementById(modalId));
    modal.show();

    //Call the generic function to load rent lists, then display into a table within a modal.
    loadRentList('/listRentStatus', status, tableSelector);
}


/**             ------------ Display the table content calling the generic methods  ------------
 Page: Index
 Item: Card -> BUTTON -> Open Modal
 Method: The modal will display a table with the info selected. (Rent Status: New or In Progress)
 */
//Rent Status: New
function displayAllRentStatusNew()
{
    displayRentStatusModal('displayAllRentStatusNewModal', 'New', '#rentListStatusNew');
}

//Rent Status: In Progress
function displayAllRentStatusInProgress()
{
    displayRentStatusModal('displayAllRentStatusInProgressModal', 'InProgress', '#rentListStatusInProgress');
}

//Rent Payment Status: Unpaid
function displayListAllRentStatusUnpaid()
{
    displayRentStatusModal('displayListAllRentStatusUnpaidModal', 'Unpaid', '#rentListStatusUnpaid');
}







