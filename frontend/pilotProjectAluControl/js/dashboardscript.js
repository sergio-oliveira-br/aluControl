/**
 * National College of Ireland - NCI
 *    Higher Diploma in Computing
 *         Final Project
 *              ---
 * Author: Sergio Vinicio da Silva Oliveira
 * ID: x23170981@student.ncirl.ie
 * Project Commencing May 2024
 * Version: 1.0
 */

/**
 Page: Dashboard
 Item: Chart
 Method: Display Chart -
 */
$(document).ready(function ()
{
    //items x sum of total price, group by item
    function renderItemsChart(data)
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

    function renderRentChart(data)
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
                        }
                    }
                }
            });
        }

    //relation unpaid x paid
    function renderPaymentChart(data)
        {
            //variables
            var status = data.map(function(rent){return rent.rentPaymentStatus});
            var price = data.map(function(rent){return rent.rentTotalPrice})
            
            // Graphs
            const ctx3 = document.getElementById('myPaymentChart')
            const myRentChart = new Chart(ctx3,{
                type: 'pie',
                data: {
                    labels: status,
                    datasets: [{
                        data: price,
                        label: 'Values',
                        lineTension: 0,
                        borderWidth: 4,
                        backgroundColor: ['#FF6384', 'rgba(62,198,104,0.66)'],
                        pointBackgroundColor: '#007bff'
                    }],
                },
                options:{
                    //responsive: true,           //allows responsiveness
                    //maintainAspectRatio: false, //disables the maintenance of the aspect
                    //aspectRatio: 1,             //ratio between width and height
                    plugins:{
                        legend:{
                            display: true,
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
                }
            });
        }

    //AJAX calls to load data and render graphics
    ajaxRequest('/findItemsTotalPrice', function (data) {
        renderItemsChart(data);
    });

    ajaxRequest('/findRentItems', function (data) {
        renderRentChart(data);
    });

    ajaxRequest('/findRentPaymentStatus', function (data) {
        renderPaymentChart(data);
    });

});






