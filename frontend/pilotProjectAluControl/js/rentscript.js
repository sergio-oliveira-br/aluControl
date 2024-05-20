
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
                    '<td><button class="btn btn-primary">Paid</button></td>' +
                    '<td><button class="btn btn-primary">Details</button></td>'
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
