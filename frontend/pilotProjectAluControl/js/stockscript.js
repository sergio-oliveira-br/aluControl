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
 Page: Stock
 Item: Chart
 Method: Display Chart -
 Reference: https://www.chartjs.org/docs/latest/samples/line/multi-axis.html
 */
$(document).ready(function ()
{
    //each product in our database
    $.ajax({
        url: '/scaffoldsRentals',
        method: 'GET',
        dataType: 'json',
        success: function (data)
        {
            //variables
            let scaffold = data.map(function(rent){return rent.rentItem});
            let qty = data.map(function(rent){return rent.rentQtyItem});
            let date = data.map(function(rent){return rent.rentStarts});
            //IMPORTANT! I NEED TO CONVERT THE DATE TO WEEK

            //graph
            const ctx = document.getElementById('myStockChart')

            //config
            const config = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: date,
                    datasets: [{
                        data: qty,
                        label: 'Scaffolds',
                        lineTension: 0,
                        borderWidth: 4,
                        pointBackgroundColor: '#007bff',
                        backgroundColor: '#007bff' ,
                    }],
                },
                options:{
                    responsive: true,
                    plugins:{
                        legend:{
                            display: true,
                        },
                        tooltip:{
                            trigger: 'item',
                            boxPadding: '5px',
                        },
                    }
                }
            });
            //setup
        }
    });
})