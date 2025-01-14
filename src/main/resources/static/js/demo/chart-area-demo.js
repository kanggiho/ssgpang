// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

function number_format(number, decimals, dec_point, thousands_sep) {
    // *     example: number_format(1234.56, 2, ',', ' ');
    // *     return: '1 234,56'
    number = (number + '').replace(',', '').replace(' ', '');
    var n = !isFinite(+number) ? 0 : +number,
        prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
        sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
        dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
        s = '',
        toFixedFix = function(n, prec) {
            var k = Math.pow(10, prec);
            return '' + Math.round(n * k) / k;
        };
    // Fix for IE parseFloat(0.55).toFixed(0) = 0;
    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
    if (s[0].length > 3) {
        s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
    }
    if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
    }
    return s.join(dec);
}

//관리자 대시보드 area Chart: 입출고현황
//입고수량
const InputQuantity = Array(12).fill(0);
//출고수량
const OutputQuantity = Array(12).fill(0);

axios.get('/admin/home_admin/areaChart')
    .then(function (response) {
        const areaChartInputData = response.data.areaChartInputData;
        const areaChartOutputData = response.data.areaChartOutputData;

        console.log(areaChartInputData);
        console.log(areaChartOutputData);

        // 데이터 넣기
        //입고량 (warehousedMonth)
        for (let i = 0; i < areaChartInputData.length; i++) {
            const item = areaChartInputData[i];
            const monthIndex = item.warehousedMonth - 1; // 1~12 → 0~11
            if (monthIndex >= 0 && monthIndex < 12) {
                InputQuantity[monthIndex] = item.warehousedQuantity;
            }
        }

        //출고량 (releaseMonth)
        for (let i = 0; i < areaChartOutputData.length; i++) {
            const item = areaChartOutputData[i];
            const monthIndex = item.releaseMonth - 1;
            if (monthIndex >= 0 && monthIndex < 12) {
                OutputQuantity[monthIndex] = item.releaseQuantity;
            }
        }


        // Area Chart
        var ctx = document.getElementById("myAreaChart");
        var myLineChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                datasets: [{
                    label: "입고량",
                    lineTension: 0.5,
                    backgroundColor: "rgba(78,115,223,0.02)",
                    borderColor: "rgb(123,163,250)",
                    pointRadius:3,
                    pointBackgroundColor: "rgb(123,163,250)",
                    pointBorderColor: "rgba(78, 115, 223, 1)",
                    pointHoverRadius: 5 ,
                    pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
                    pointHoverBorderColor: "rgba(78, 115, 223, 1)",
                    pointHitRadius: 10,
                    pointBorderWidth: 2,
                    data: InputQuantity ,
                },
                    {
                        label: "출고량",
                        lineTension: 0.5,
                        backgroundColor: "rgba(231,74,59,0.02)",
                        borderColor: "rgb(255,143,107)",
                        pointRadius:3,
                        pointBackgroundColor: "rgb(255,143,107)",
                        pointBorderColor: "rgb(252,96,47)",
                        pointHoverRadius: 5,
                        pointHoverBackgroundColor: "rgba(231, 74, 59, 1)",
                        pointHoverBorderColor: "rgba(231, 74, 59, 1)",
                        pointHitRadius: 10,
                        pointBorderWidth: 2,
                        data: OutputQuantity,
                    }
                ],
            },
            options: {
                maintainAspectRatio: false,
                layout: {
                    padding: {
                        left: 10,
                        right: 25,
                        top: 25,
                        bottom: 0
                    }
                },
                scales: {
                    xAxes: [{
                        time: {
                            unit: 'date'
                        },
                        gridLines: {
                            display: false,
                            drawBorder: false
                        },
                        ticks: {
                            maxTicksLimit: 12,
                            autoSkip: true,
                            fontSize: 14, // 글씨 크기 설정
                            fontStyle: 'bold', // 글씨 스타일 설정 (굵게)
                        }
                    }],
                    //y축
                    yAxes: [{
                        ticks: {
                            beginAtZero: true, // Y축 0부터 시작
                            stepSize: 100, // Y축 1000 단위
                            // max: 200, // Y축 최대값
                            // maxTicksLimit: 5,
                            autoSkip: true,
                            fontSize: 14, // 글씨 크기 설정
                            fontStyle: 'bold', // 글씨 스타일 설정 (굵게)
                            callback: function(value, index, values) {
                                return number_format(value);
                            }
                        },
                        gridLines: {
                            color: "rgb(234, 236, 244)",
                            zeroLineColor: "rgb(234, 236, 244)",
                            drawBorder: false,
                            borderDash: [2],
                            zeroLineBorderDash: [2]
                        }
                    }],
                },
                legend: {
                    display: true,
                    position: 'right',
                    align: 'start',
                    labels: {
                        fontSize: 14, // 글씨 크기
                        fontColor: "#000", // 범례 글씨 색상
                        fontStyle: 'bold', // 범례 글씨 스타일
                    }
                },
                tooltips: {
                    backgroundColor: "rgb(255,255,255)",
                    bodyFontColor: "#858796",
                    titleMarginBottom: 10,
                    titleFontColor: '#6e707e',
                    titleFontSize: 14,
                    borderColor: '#dddfeb',
                    borderWidth: 1,
                    xPadding: 15,
                    yPadding: 15,
                    displayColors: false,
                    intersect: false,
                    mode: 'index',
                    caretPadding: 10,
                    callbacks: {
                        label: function(tooltipItem, chart) {
                            var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                            return datasetLabel + ": " + number_format(tooltipItem.yLabel);
                        }
                    }
                }
            }
        });
    })
    .catch(function (error) {
        console.log("error: " + error);
    })
