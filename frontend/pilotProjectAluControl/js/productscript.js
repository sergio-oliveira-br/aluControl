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
                    '<td>' + + '</td>' +
                    '<td><button class="btn btn-primary" onclick="">Edit</button></td>'
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
 Item: Table
 Method: Display the sum of the item "Scaffolds" from ProductController
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


