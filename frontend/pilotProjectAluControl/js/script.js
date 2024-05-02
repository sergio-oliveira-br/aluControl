// script.js


//Testing the JS connection
function displayTestMessage() {
    alert("O arquivo JavaScript foi carregado com sucesso!");
}




//Method to get the Customer's Info, when the page is logged
$(document).ready(function()
{
    loadCustomers();
});

//Method to get the Customer's Info via AJAX
function loadCustomers()
{
    $.ajax({
        url: "/customers",
        type: "GET",
        success: function(data)
        {
            //first clean
            $('#customerList').empty();

            //Iterate over clients and add them to the list
            data.forEach(function(customer)
            {
                $('#customerList').append('<tr>' +
                                                '<td>' + customer.id + '</td>' +
                                                '<td>' + customer.firstName + '<td>'+
                                                '<td>' + customer.lastName + '<td>' +
                                                '<td>' + customer.city + '<td>' +
                                          '</tr>');
            });
        },
        error: function(xhr, status, error)
        {
            console.error(error);
        }
    });
}


