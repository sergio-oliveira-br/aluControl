/**
 Page: Product
 Method: Ready()

 Info: As soon as the page's Document Object Model (DOM)
 is ready for manipulation, JavaScript code can be executed.
 */
$(document).ready(function()
{
    //Load the table
    loadProduct();

    //Load in the card the sum of Scaffolds
    loadSumScaffolds();

    //Display the sum of the item "Scaffolds" from ProductController
    loadSumScaffoldsRented();

    //Display the QTY AVAILABLE of "Scaffolds" from RentStatisticsController
    loadQtyScaffoldsAvailable();

});


/**
 Page: Products
 Item: Table
 Method: Create a table with
 all products via AJAX
 */
function loadProduct()
{
    $.ajax({url: "/product", type: "GET", success: function(data)
        {
            //first clean
            $('#productList').empty();

            //Iteration
            data.forEach(function(product)
            {
                $('#productList').append('<tr>' +
                    '<td>' + product.id + '</td>' +
                    '<td>' + product.itemDescription + '</td>' +
                    '<td>' + product.itemQuantity +'</td>' +
                    '<td><button class="btn btn-primary" onclick="openEditModal(' + product.id + ')">Edit</button></td>'
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
 Page: Products
 Item: Card
 Method: Display the sum of ALL item "Scaffolds" from ProductController
 */
function loadSumScaffolds() {
    $.ajax({url: "/sumScaffolds", type: "GET", success: function(data)
        {
            $('#sumScaffolds').text('Total Items ' + data); //this will display the sum
            console.log("Data loaded - This is the sum of all Scaffolds")
        },
        error: function(xhr, status, error)
        {
            console.error(error);
        }
    })
}

/**
 Page: Products
 Item: Card
 Method: Display the sum of the item RENTED "Scaffolds" from RentStatisticsController
 */
function loadSumScaffoldsRented() {
    $.ajax({url: "/sumScaffoldsRented", type: "GET", success: function(data)
        {
           $('#sumScaffoldsRented').text('Total Items Rented ' + data);
           console.log("Data loaded - This is the sum of all Scaffolds RENTED")
        },
        error: function(xhr, status, error)
        {
            console.error(error);
        }
    })
}

/**
 Page: Products
 Item: Card
 Method: Display the QTY AVAILABLE of "Scaffolds" from RentStatisticsController
 */
function loadQtyScaffoldsAvailable() {
    $.ajax({url: "/qtyScaffoldsAvailable", type: "GET", success: function(data)
        {
            $('#qtyScaffoldsAvailable').text('Total Items Available ' + data);
            console.log("Data loaded - This is the sum of all Scaffolds AVAILABLE")
        },
        error: function(xhr, status, error)
        {
            console.error(error);
        }
    })
}


/**
 Page: Products
 Item: Form (modal)
 Method: This is a jQuery method that allows
    the user to make asynchronous requests to the server
    to send or receive data without having to reload the page
    (There is a confirmation msg method as well)
*/
function openEditModal(productId)
{


    //Open the modal
    let editModal = new bootstrap.Modal(document.getElementById('editModal'));
    editModal.show();


}

//Confirmation msg
document.getElementById('editModal').addEventListener('submit', function(event) {
    //Displays the confirmation msg
    let confirmation = confirm('Are you sure you want to save the modifications?');

    //If the user cancels, do not send the form!
    if (!confirmation) {
        event.preventDefault();
        console.log('User cancel the operation')
    }
});


