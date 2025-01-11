// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

const labels_product = []
const data_totalQuantity = [];

axios.get('/user/home/chart3')
    .then(function (response){
        const userPieChartData = response.data;
        console.log(userPieChartData);
        for (let i = 0; i < userPieChartData.length; i++) {
            labels_product.push(userPieChartData[i].productName);
            data_totalQuantity.push(userPieChartData[i].totalQuantity);
        }

        // Pie Chart Example
        var ctx = document.getElementById("myPieChart");
        var myPieChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: labels_product,
                datasets: [{
                    data: data_totalQuantity,
                    backgroundColor: ['#5b93ff', '#bfc5ef', '#ff8f6b','#bfc5ef','#ffd66b'],
                    hoverBackgroundColor: ['#3775ef', '#e5b435', '#ec683c','#3775ef','#3775ef'],
                    hoverBorderColor: "rgba(234, 236, 244, 1)",
                }],
            },
            options: {
                maintainAspectRatio: false,
                tooltips: {
                    backgroundColor: "rgb(255,255,255)",
                    bodyFontColor: "#858796",
                    borderColor: '#dddfeb',
                    borderWidth: 1,
                    xPadding: 15,
                    yPadding: 15,
                    displayColors: false,
                    caretPadding: 10,
                },
                legend: {
                    display: false
                },
                cutoutPercentage: 65,
            },
        });
    })
    .catch(function (error) {
        console.log("error: " + error)
    })

