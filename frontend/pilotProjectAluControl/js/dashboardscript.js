/**
 Page: Dashboard
 Item: Chart
 Method: Create the Chart
*/
window.onload = function () {
    // Graphs
    const ctx = document.getElementById('myChart')
    const myChart = new Chart(ctx,{
        type: 'line',
        data: {
            labels:['Monday', 'Sunday', 'Tuesday', 'Wednesday', 'Thursday'],
            datasets: [{
                data: [111, 222, 333, 444],
                backgroundColor: 'red',
                lineTension: 0,
                borderColor: '#007bff',
                borderWidth: 4,
                pointBackgroundColor: '#007bff'
            }],
        },
        options:{
            plugins:{
                legend:{
                    display: true,
                },
                tooltip:{
                    boxPadding: '5px',
                },
            }
        }
    });
}


