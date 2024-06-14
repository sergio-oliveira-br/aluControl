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
//Reference: https://getbootstrap.com/docs/5.0/forms/validation/
//Checking form submissions if there are invalid fields: Used by "Rent Form"/"Rent Form Edit"
(function () {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                    console.log('Checking the form...')
                }
                form.classList.add('was-validated')
            }, false)
        })
})()



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
 Page: Rent and Index
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
 Page: Rent and Index
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
 Page: Rent and Index
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
 Page: Rent and Index
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
 Page: Rent and Index
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