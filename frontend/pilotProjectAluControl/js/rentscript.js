
/**
 Page: Rent
 Item: Table
 Method: Create a table with
 all customers via AJAX
 */

$(document).ready(function()
{
    loadRent();
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
                    '<td>' + rent.rentPrice + '</td>' +
                    '<td>' + rent.rentStarts + '</td>' +
                    '<td>' + rent.rentEnds + '</td>' +
                    '<td>' + rent.rentTotalPrice + '</td>' +
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
            $('#editRentTotalPrice').val(rent.rentTotalPrice);
            $('#editRentPaymentStatus').val(rent.rentPaymentStatus);
            $('#editRentDetails').val(rent.rentDetails);

            // Abrir o modal
            var editModal = new bootstrap.Modal(document.getElementById('editModal'));
            editModal.show();
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
            rentQtyItem: $('#editRentQtyItem').val(),
            rentPrice: $('#editRentPrice').val(),
            rentStarts: $('#editRentStarts').val(),
            rentEnds: $('#editRentEnds').val(),
            rentTotalDays: $('#editRentTotalDays').val(),
            rentTotalPrice: $('#editRentTotalPrice').val(),
            rentPaymentStatus: $('#editRentPaymentStatus').val(),
            rentDetails: $('#editRentDetails').val()
        };

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
