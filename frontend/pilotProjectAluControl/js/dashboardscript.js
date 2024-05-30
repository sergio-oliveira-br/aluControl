/**
 Page: Dashboard
 Item: Chart
 Method: Display Chart -
 */
$(document).ready(function ()
{
    //items x sum of total price, group by item
    $.ajax({url: '/findItemsTotalPrice',
        method: 'GET',
        dataType: 'json',
        success: function (data)
        {
            //variables
            var items = data.map(function(rent){return rent.rentItem});
            var price = data.map(function(rent){return rent.rentTotalPrice})

            // Graphs
            const ctx = document.getElementById('myItemsChart')
            const myChart = new Chart(ctx,{
                type: 'bar',
                data: {
                    labels: items,
                    datasets: [{
                        data: price,
                        label: 'Total Price',
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
                },
                options:{
                    plugins:{
                        datalabels:{
                            enabled: true,
                        }
                    }
                },
            });
        }
    });

    //items x sum of total price, group by item
    $.ajax({url: '/findRentItems',
        method: 'GET',
        dataType: 'json',
        success: function (data)
        {
            //variables
            var item = data.map(function(rent){return rent.rentItem});
            var price = data.map(function(rent){return rent.rentTotalPrice})

            // Graphs
            const ctx2 = document.getElementById('myRentChart')
            const myRentChart = new Chart(ctx2,{
                type: 'bar',
                data: {
                    labels: item,
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
    });

    //relation unpaid x paid
    $.ajax({url: '/findRentPaymentStatus',
        method: 'GET',
        dataType: 'json',
        success: function (data)
        {
            //variables
            var status = data.map(function(rent){return rent.rentPaymentStatus});
            var price = data.map(function(rent){return rent.rentTotalPrice})

            // Graphs
            const ctx3 = document.getElementById('myPaymentChart')
            const myRentChart = new Chart(ctx3,{
                type: 'bar',
                data: {
                    labels: status,
                    datasets: [{
                        data: price,
                        label: 'Values',
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
                },
                options:{
                    plugins:{
                        datalabels:{
                            enabled: true,
                        }
                    }
                },
            });
        }
    });
});





