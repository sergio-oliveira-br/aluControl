/**
 Page: Index
*/

//Testing the JS connection
function displayTestMessage() {
    alert("O arquivo JavaScript foi carregado com sucesso!");
}

/**
 Page: Index
 Item: Card
 Method: Obtain a list of outputs created by clients in the database
 */

$(document).ready(function ()
{
    //this is responsible to display the number
    loadNumberCustomers();
})


function loadNumberCustomers()
{
    $.ajax({url: "/qtyCustomers", type: "GET", success: function(data)
        {
            //clean???

            $('#loadNumberCustomers').text('Number of customers: ' + data);

        },
        error: function(xhr, status, error)
        {
            console.error(error);
        }
    })
}