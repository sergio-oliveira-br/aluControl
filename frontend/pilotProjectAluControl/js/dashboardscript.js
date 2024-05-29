/**
 Page: Dashboard
 Item: Chart
 Method: Display Chart
*/
$(document).ready(function ()
{
    $.ajax({url: '/allRentedItems',
        method: 'GET',
        dataType: 'json',
        success: function (data)
        {
            //variables
            var items = data.map(function(rent){return rent.rentItem});
            var price = data.map(function(rent){return rent.rentTotalPrice})

            // Graphs
            const ctx = document.getElementById('myChart')
            const myChart = new Chart(ctx,{
                type: 'bar',
                data: {
                    labels: items,
                    datasets: [{
                        data: price,
                        lineTension: 0,
                        borderWidth: 4,
                        pointBackgroundColor: '#007bff'
                    }],
                },
                options:{
                    plugins:{
                        legend:{
                            display: false,
                        },
                        tooltip:{
                            boxPadding: '5px',
                        },
                    }
                }
            });
        }
    })
})


