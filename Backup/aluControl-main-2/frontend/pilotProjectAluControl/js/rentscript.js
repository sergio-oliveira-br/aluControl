
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
    loadCustomerForRentForm(); //!Important (this additional features)

    //The script will load the available items in the rental form when the page loads
    loadItemsForRentForm();


    //(Modal)The script will load the available items in the rental form when the page loads
    updateLoadCustomerForRentForm();


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


//!Important (this additional features)
//The script will load the available customers in the rental form when the page loads
function loadCustomerForRentForm() {
    $.ajax({
        url: "/customers",
        type: "GET",
        success: function(data) {
            var rentCustomerSelect = $('#rentFirstName');

            rentCustomerSelect.empty();

            data.forEach(function(customer) {
                rentCustomerSelect.append('<option value="' + customer.firstName + " "+ customer.lastName + " - " + customer.phoneNumber +'">' +
                    customer.firstName + " "+ customer.lastName + " - " + customer.phoneNumber +'</option>');
            });
            console.log("customers has been load to the form");
        },
        error: function(xhr, status, error) {
            console.error("Error loading items for rent form: ", error);
        }
    });
}



//The script will load the available items in the rental form when the page loads
function loadItemsForRentForm() {
    $.ajax({
        url: "/product",
        type: "GET",
        success: function(data) {
            var rentItemSelect = $('#rentItem');
            rentItemSelect.empty();
            data.forEach(function(product) {
                rentItemSelect.append('<option value="' + product.itemDescription + '">' + product.itemDescription + '</option>');
            });
        },
        error: function(xhr, status, error) {
            console.error("Error loading items for rent form: ", error);
        }
    });
}


/**
 Page: Rent
 Item: Table
 Method: The script will load all items in a table
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
                    '<td>' + rent.rentFirstName + '</td>' +
                    /** '<td>' + rent.rentLastName + '</td>' + */
                    '<td>' + rent.rentAddress + '</td>' +
                    '<td>' + rent.rentItem + '</td>' +
                    '<td>' + rent.rentPrice.toFixed(2) + '</td>' + //Formatting to two decimal places
                    '<td>' + rent.rentStarts + '</td>' +
                    '<td>' + rent.rentEnds + '</td>' +
                    '<td>' + rent.rentTotalPrice.toFixed(2) + '</td>' + //Formatting to two decimal places
                    '<td>' + rent.rentPaymentStatus + '</td>' +
                    '<td><button class="btn btn-primary" onclick="openEditModal(' + rent.id + ')">Edit</button></td>'
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
 Page: Rent
 Item: Form -> Days = (End - Start)
 Method: Simple math to calculate the days
 */
//Making the Math
function mathDays(end, start)
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
    console.log("The days has been calculated", rentTotalDays);
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


//Update everytime that one of these three field is changed
document.getElementById('rentTotalDays').addEventListener('change', loadTotalPrice)
document.getElementById('rentQtyItem').addEventListener('change', loadTotalPrice)
document.getElementById('rentPrice').addEventListener('change', loadTotalPrice)
document.getElementById('rentStarts').addEventListener('change',loadRentDays);
document.getElementById('rentEnds').addEventListener('change',loadRentDays);


/**
 Page: Rent
 Item: Form (modal)
 Method: This is a jQuery method that allows
 the user to make asynchronous requests to the server
 to send or receive data without having to reload the page.
 */

function openEditModal(rentId) {
    $.ajax({
        url: '/rent/' + rentId,
        type: 'GET',
        success: function(rent)
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

            //Open the modal
            let editModal = new bootstrap.Modal(document.getElementById('editModal'));
            editModal.show();

            //Update the total price when opening the modal
            //loadTotalPrice()
            //updateTotalPrice();
            console.log("updated the total price");
        },
        error: function(xhr, status, error)
        {
            console.error(error);
            console.status = error;
            console.log(status);
        }
    });
}



/**
 Page: Rent
 Item: Form (modal) - > Field Customer
 Method: The script will load the available customers in the rental form when the page loads
 */
function updateLoadCustomerForRentForm() {
    $.ajax({
        url: "/customers",
        type: "GET",
        success: function(data) {
            var rentCustomerSelect = $('#editRentFirstName');

            rentCustomerSelect.empty();

            data.forEach(function(customer) {
                rentCustomerSelect.append('<option value="' + customer.firstName + " "+ customer.lastName + " - " + customer.phoneNumber +'">' +
                    customer.firstName + " "+ customer.lastName + " - " + customer.phoneNumber +'</option>');
            });
            console.log("customers has been load to the form on (modal)");
        },
        error: function(xhr, status, error) {
            console.error("Error loading items for rent form: ", error);
        }
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
        rentDetails: $('#editRentDetails').val()
    };

    $.ajax({
        url: '/rent/' + rentData.id,
        type: 'PUT',
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


