// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

//사용자 대시보드 pie Chart: 나의 발주정보
//나의 발주 품목 TOP5
const labels_product = []
//발주수량
const data_totalQuantity = [];

axios.get('/user/home/pieChart_output')
    .then(function (response){
        const userPieChartData = response.data;
        console.log(userPieChartData);
        for (let i = 0; i < userPieChartData.length; i++) {
            labels_product.push(userPieChartData[i].productName);
            data_totalQuantity.push(userPieChartData[i].totalQuantity);
        }

        // Pie Chart
        var ctx = document.getElementById("myPieChart");
        var myPieChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: labels_product,
                datasets: [{
                    data: data_totalQuantity,
                    backgroundColor: ["#ffe49c","rgba(246,176,133,0.7)","rgba(192,245,174,0.7)", "#9bcaff","#e7d0fd"],
                    hoverBackgroundColor: [
                ],
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
                    display: true,
                    //usePointStyle: true,
                    position: 'right',
                    align: 'start',
                    labels: {
                        fontSize: 15, // 글씨 크기
                        fontColor: "#000", // 범례 글씨 색상
                        fontStyle: 'bold', // 범례 글씨 스타일
                        boxWidth: 15, // 범례 아이콘 크기
                        //padding: 15, // 범례 아이템 간 간격
                    }
                },
                cutoutPercentage: 80,
            },
        });
    })
    .catch(function (error) {
        console.log("error: " + error)
    })

