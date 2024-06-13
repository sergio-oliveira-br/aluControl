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


/**
 Page: Rent
 Item: Table
 Method: Create a table with
 all customers via AJAX
 */

$(document).ready(function()
{
    loadRent();

    loadRentDays();

    //The script will load the available customers in the rental form when the page loads
    loadCustomerForRentForm(); //This is for original form

    //The script will load the available items in the rental form when the page loads
    loadItemsForRentForm();
    loadEditItemsForRentFormModal(); //This is for edit modal

    //(Modal)The script will load the available items in the rental form when the page loads
    updateLoadCustomerForRentForm();

    //(Modal)Update the rent status, if is "Finished", the item stock will return to total available
    $('#editRentStatus').on('change', function() {
        console.log('Rent Status Changed');
        updateRentStatus();
    });

    //(Modal)Update total price when price or quantity change
    $('#editRentPrice, #editRentQtyItem').on('change', function() {
        console.log('Price or Quantity input changed');
        updateTotalPrice();
    });

    //(Modal)Update total price when dates change
    $('#editRentStarts, #editRentEnds').on('change', function() {
        console.log('Start or End date changed');
        //loadRentDays();
        updateRentDays();
    });

    //(Modal)
    $('#editRentForm').on('submit', function(e) {
        e.preventDefault();
        submitEditForm();
    });
})

/**
 Page: Rent
 Item: Table
 Method: The script will load all items in a table
 */
function loadRent()
{
    //Call the generic function, that perform an AJAX request
    ajaxRequest("/rent", function(data)
    {
        //first clean
        $('#rentList').empty();

        //Iteration
        data.forEach(function(rent)
        {
            $('#rentList').append('<tr>' +
                '<td>' + rent.id + '</td>' +
                '<td>' + rent.rentFirstName + '</td>' +
                /** '<td>' + rent.rentLastName + '</td>' + */
                '<td>' + rent.rentAddress + '</td>' +
                '<td>' + rent.rentItem + '</td>' +
                '<td>' + rent.rentPrice.toFixed(2) + '</td>' + //Formatting to two decimal places
                '<td>' + rent.rentStarts + '</td>' +
                '<td>' + rent.rentEnds + '</td>' +
                '<td>' + rent.rentTotalPrice.toFixed(2) + '</td>' + //Formatting to two decimal places
                '<td>' + rent.rentPaymentStatus + '</td>' +
                '<td>' + rent.rentStatus+ '</td>' +
                '<td><button class="btn btn-primary" onclick="openEditModal(' + rent.id + ')">Edit</button></td>'
            );
        });
    });
}


/**
 Page: Rent
 Item: Form - Customer field
 Method: The script will load the available CUSTOMERS in the rental form when the page loads
 */
function loadCustomerForRentForm()
{
    //Call the generic function, that perform an AJAX request
    ajaxRequest("/customers", function(data)
    {
        //Local variable
        var rentCustomerSelect = $('#rentFirstName');

        //cleaning
        rentCustomerSelect.empty();

        //Iteration
        data.forEach(function(customer) {
            rentCustomerSelect.append('<option value="' + customer.firstName + " "+ customer.lastName + " - " + customer.phoneNumber +'">' +
                customer.firstName + " "+ customer.lastName + " - " + customer.phoneNumber +'</option>');
        });
    });
}



/**
 Page: Rent
 Item: Form - Customer field
 Method: The script will load the available ITEMS in the rental form when the page loads
 */
function loadItemsForRentForm()
{
    ajaxRequest("/product", function(data)
    {
        //Local Variable
        var rentItemSelect = $('#rentItem');

        //Cleaning
        rentItemSelect.empty();

        //Iteration
        data.forEach(function(product)
        {
            rentItemSelect.append('<option value="' + product.itemDescription + '">' + product.itemDescription + '</option>');
        });
    });
}




/**
 Page: Rent
 Item: Form -> Days = (End - Start)
 Method: Simple math to calculate the days
 */
function mathDays(end, start)
{
    let start_Date = new Date(start);
    let end_Date = new Date(end);
    let diffTime = Math.abs(end_Date - start_Date);
    let diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

    return diffDays;
}



/**
 Page: Rent
 Item: Form -> Days = (End - Start)
 Method: applying the mathDays method
 */
function loadRentDays()
{
    //Variables
    let newStart = document.getElementById('rentStarts').value;
    let newEnd = document.getElementById('rentEnds').value;

    //Call the method
    let rentTotalDays = mathDays(newEnd, newStart);

    //Write in the field
    document.getElementById('rentTotalDays').value = rentTotalDays;
    console.log("The days has been calculated", rentTotalDays);

    //update the rentTotalPrice every time that any date has been changed
    loadTotalPrice();


}


/**
 Page: Rent
 Item: Form -> Total Price = (Days * Qty * UnitPrice)
 Method: Simple math to calculate the total price and update
 */
function loadTotalPrice()
{
    //Variable
    let newTotalDays = parseInt(document.getElementById('rentTotalDays').value);
    let newTotalQty = parseInt(document.getElementById('rentQtyItem').value);
    let newUnitPrice = parseFloat(document.getElementById('rentPrice').value); //this is a float number

    //Calculating
    let newRentTotalPrice = (newTotalDays * newTotalQty * newUnitPrice);

    //Write the field
    document.getElementById('rentTotalPrice').value = newRentTotalPrice.toFixed(2);
    console.log("The Rent Total Price was calculated by loadTotalPrice(). Result: " + newRentTotalPrice.toFixed(2));
}

//Update everytime that one of these five field is changed
document.getElementById('rentTotalDays').addEventListener('change', loadTotalPrice);
document.getElementById('rentQtyItem').addEventListener('change', loadTotalPrice);
document.getElementById('rentPrice').addEventListener('change', loadTotalPrice);

document.getElementById('rentStarts').addEventListener('change',loadRentDays);
document.getElementById('rentEnds').addEventListener('change',loadRentDays);


/**
 Page: Rent
 Item: Form (modal)
 Method: This is a jQuery method that allows
 the user to make asynchronous requests to the server
 to send or receive data without having to reload the page.
 */

function openEditModal(rentId)
{
    console.log(rentId);

    //Call the generic function, that perform an AJAX request
    ajaxRequest("/rent/" + rentId, function(rent)
    {
        //Fill in the form fields in the modal using Selectors $() to select HTML elements based on their IDs.
        $('#editRentId').val(rent.id);
        $('#editRentFirstName').val(rent.rentFirstName);
        $('#editRentLastName').val(rent.rentLastName);
        $('#editRentAddress').val(rent.rentAddress);
        $('#editRentItem').val(rent.rentItem);
        $('#editRentQtyItem').val(rent.rentQtyItem);
        $('#editRentPrice').val(rent.rentPrice);
        $('#editRentStarts').val(rent.rentStarts);
        $('#editRentEnds').val(rent.rentEnds);
        $('#editRentTotalDays').val(rent.rentTotalDays);
        $('#editRentTotalPrice').val(rent.rentTotalPrice.toFixed(2));
        $('#editRentPaymentStatus').val(rent.rentPaymentStatus);
        $('#editRentDetails').val(rent.rentDetails);
        $('#editRentStatus').val(rent.rentStatus);

        //Open the modal
        let editModal = new bootstrap.Modal(document.getElementById('editModal'));
        editModal.show();
    });
}



/**
 Page: Rent
 Item: Form (modal) - > Field Customer
 Method: The script will load the available customers in the rental form when the page loads
 */
function updateLoadCustomerForRentForm()
{
    //Call the generic function, that perform an AJAX request
    ajaxRequest("/customers", function(data)
    {
        //Local variable
        var rentCustomerSelect = $('#editRentFirstName');

        //Cleaning
        rentCustomerSelect.empty();

        //Iteration
        data.forEach(function(customer)
        {
            rentCustomerSelect.append('<option value="' + customer.firstName + " "+ customer.lastName + " - " + customer.phoneNumber +'">' +
                customer.firstName + " "+ customer.lastName + " - " + customer.phoneNumber +'</option>');
        });
    });
}

/**
 Page: Rent
 Item: Form - Customer field
 Method: The script will load the available ITEMS in the rental form when the page loads
 */
function loadEditItemsForRentFormModal()
{
    //Call the generic function, that perform an AJAX request
    ajaxRequest("/product", function(data)
    {
        //local variable
        var rentItemSelect = $('#editRentItem');

        //cleaning
        rentItemSelect.empty();

        //Iteration
        data.forEach(function(product)
        {
            rentItemSelect.append('<option value="' + product.itemDescription + '">' + product.itemDescription + '</option>');
        });
    });
}


/**
 Page: Rent
 Item: Form (modal)
 Method: Calculates the difference of the days
 */
function updateRentDays()
{
    //Get the variables from the modal
    let newStart = $('#editRentStarts').val();
    let newEnd = $('#editRentEnds').val();

    //Call the method mathDays()
    let newRentTotalDays = mathDays(newStart, newEnd);
    console.log("New Total Days has been calculated: " + newRentTotalDays);

    //Write in the field
    $('#editRentTotalDays').val(newRentTotalDays);

}
//Update everytime that one of these two field is changed
document.getElementById('editRentStarts').addEventListener('change',updateRentDays);
document.getElementById('editRentEnds').addEventListener('change',updateRentDays);



/**
 Page: Rent
 Item: Form (modal)
 Method: Calculates the total price on Modal
 */
function updateTotalPrice()
{
    //Get the variables
    let newRentPriceModal = parseFloat($('#editRentPrice').val().replace(',', '.')) || 0;
    let newRentQtyItemModal = parseInt($('#editRentQtyItem').val()) || 0;
    let newRentTotalDaysModal = parseInt($('#editRentTotalDays').val());

    //Calculating
    let newRentTotalPriceModal = (newRentPriceModal * newRentQtyItemModal * newRentTotalDaysModal);

    //Writing
    $('#editRentTotalPrice').val(newRentTotalPriceModal.toFixed(2));
}


/**
 Page: Rent
 Item: Form (modal)
 Method: Send the data to update my database
 */
function submitEditForm() {
    let rentData = {
        id: $('#editRentId').val(),
        rentFirstName: $('#editRentFirstName').val(),
        rentLastName: $('#editRentLastName').val(),
        rentAddress: $('#editRentAddress').val(),
        rentItem: $('#editRentItem').val(),
        rentQtyItem: parseInt($('#editRentQtyItem').val()),
        rentPrice: parseFloat($('#editRentPrice').val()),
        rentStarts: $('#editRentStarts').val(),
        rentEnds: $('#editRentEnds').val(),
        rentTotalDays: parseInt($('#editRentTotalDays').val()),
        rentTotalPrice: parseFloat($('#editRentTotalPrice').val()),
        rentPaymentStatus: $('#editRentPaymentStatus').val(),
        rentDetails: $('#editRentDetails').val(),
        rentStatus: $('#editRentStatus').val()
    };

    $.ajax({ //allows updating parts of a web page without reloading the entire page
        url: '/rent/' + rentData.id, //indicates the endpoint
        type: 'PUT', //HTTP request methods used to INSERT data to the server (backend), indicating by the endpoint specified by the URL
        contentType: 'application/json',
        data: JSON.stringify(rentData),

        success: function(response) {
            alert('Rent updated successfully');
            $('#editModal').modal('hide');
            loadRent();
        },
        error: function(xhr, status, error) {
            console.error(error);
            alert('Oops, something went wrong!');
        }
    });
}



/**
 Page: Rent
 Item: Form (modal) -> Edit Rent Status
 Method: This update the stock, adding the qty in to stock available
 */
function updateRentStatus()
{
    //Get the rent ID from the hidden input field in the form
    let rentId = $('#editRentId').val();
    console.log(rentId);

    //Get the new status value from the dropdown menu
    let status = $('#editRentStatus').val();
    console.log(status);

    $.ajax({ //allows updating parts of a web page without reloading the entire page
        url: "/rent/status/" + rentId + "?rentStatus=" + status, //indicates the endpoint
        type: 'PUT', //HTTP request methods used to INSERT/UPDATE data to the server (backend)
        data: {status: status}, //The data to send in the request, here we're sending the new status

        //Callback function to execute if the request is successful
        success: function(response)
        {
            alert('Rent status has been changed.' +
                '\nEnsure that you choose the correct option!');
            console.log("Rent status has been changed", response);
        },
        //Callback function to execute if there's an error with the request
        error: function(xhr, status, error) {
            console.error("Error updating rent status" + error);
        }
    });
}


