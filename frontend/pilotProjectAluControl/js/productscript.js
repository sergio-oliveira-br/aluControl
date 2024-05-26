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

