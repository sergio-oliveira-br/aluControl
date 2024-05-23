
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


    //Update total price when price or quantity change
    $('#editRentPrice, #editRentQtyItem').on('input', function() {
        console.log('Price or Quantity input changed');
        updateTotalPrice();
    });
    $('#editRentStarts, #editRentEnds').on('change', function() {
        console.log('Start or End date changed');
        loadRentDays();
    });
})


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
                    '<td>' + rent.rentLastName + '</td>' +
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

    //Math
    let newRentTotalPrice = newTotalDays * newTotalQty * newUnitPrice;

    // Formatting the result with two decimal places is necessary.
    let formattedTotalPrice = (Math.floor(newRentTotalPrice * 100) / 100).toFixed(2);

    //Write the field
    document.getElementById('rentTotalPrice').value = formattedTotalPrice;
    console.log("The Rent Total Price was calculated by loadTotalPrice(). Result: " + formattedTotalPrice);
}


//Update everytime that one of these three field is changed
document.getElementById('rentTotalDays').addEventListener('change', loadTotalPrice)
document.getElementById('rentQtyItem').addEventListener('change', loadTotalPrice)
document.getElementById('rentPrice').addEventListener('change', loadTotalPrice)
document.getElementById('rentStarts').addEventListener('change',loadRentDays);
document.getElementById('rentEnds').addEventListener('change',loadRentDays);






/*
//Calculates the total price
function updateTotalPrice()
{
    let rentPrice = parseFloat($('#editRentPrice').val().replace(',', '.')) || 0;
    let rentQtyItem = parseInt($('#editRentQtyItem').val()) || 0;


    let newRentTotalPrice = Math.ceil(rentPrice * rentQtyItem);

    //Write the field
    document.getElementById('rentTotalPrice').value = newRentTotalPrice;

    let rentTotalPrice = rentPrice * rentQtyItem;
    $('#editRentTotalPrice').val(rentTotalPrice.toFixed(2));
}

 */















function openEditModal(rentId) {
    // Obter os dados da linha correspondente
    $.ajax({
        url: '/rent/' + rentId,
        type: 'GET',
        success: function(rent) {
            // Preencher os campos do formulário no modal
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
            var editModal = new bootstrap.Modal(document.getElementById('editModal'));
            editModal.show();

            //Update the total price when opening the modal
            updateTotalPrice();
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}



//Submission event of the editing form
$(document).ready(function() {
    $('#editRentForm').on('submit', function(e) {
        e.preventDefault();

        var rentData = {
            id: $('#editRentId').val(),
            rentFirstName: $('#editRentFirstName').val(),
            rentLastName: $('#editRentLastName').val(),
            rentAddress: $('#editRentAddress').val(),
            rentItem: $('#editRentItem').val(),
            rentQtyItem: parseInt($('#editRentQtyItem').val()) || 0,
            rentPrice: parseFloat($('#editRentPrice').val()) || 0,
            rentStarts: $('#editRentStarts').val(),
            rentEnds: $('#editRentEnds').val(),
            rentTotalDays: parseInt($('#editRentTotalDays').val()) || 0,
            rentTotalPrice: parseFloat($('#editRentTotalPrice').val()) || 0,
            rentPaymentStatus: $('#editRentPaymentStatus').val(),
            rentDetails: $('#editRentDetails').val()
        };

        console.log('Submitting data:', rentData);

        $.ajax({
            url: '/rent/' + rentData.id,
            type: 'PUT', //The HTTP PUT method is used to send data to a server with the intention of updating or creating a specific resource.
            data: JSON.stringify(rentData),
            contentType: 'application/json',
            success: function(result) {
                //Refresh the table and close the modal
                loadRent();
                var editModal = bootstrap.Modal.getInstance(document.getElementById('editModal'));
                editModal.hide();
            },
            error: function(xhr, status, error) {
                console.error(error);
            }
        });
    });
});

//Update everytime that one of these two field is changed
document.getElementById('rentStarts').addEventListener('change',loadRentDays);
document.getElementById('rentEnds').addEventListener('change',loadRentDays);


