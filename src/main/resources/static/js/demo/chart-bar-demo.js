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

//관리자 대시보드 Bar Chart: 지점별 출고 현황
//User
const labels_user = [];
//출고량
const data_quantity = [];

axios.get('/admin/home_admin/barchart_user_output')
    .then(function (response) {
      const barChartData = response.data;
      for (let i = 0; i < barChartData.length; i++) {
        labels_user.push(barChartData[i].userId);
        data_quantity.push(barChartData[i].releaseQuantity);
      }
      console.log(barChartData);

      var ctx = document.getElementById("myBarChart");
      var myBarChart = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: labels_user,
          datasets: [{
            label: "출고량",
            backgroundColor: "#0b30c2",
            hoverBackgroundColor: "#2e59d9",
            borderColor: "#4e73df",
            data: data_quantity,
            maxBarThickness: 25,
          }],
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
            //x축
            x: {
              time: {
                unit: 'month'
              },
              gridLines: {
                display: false,
                drawBorder: false
              },
              ticks: {
                maxTicksLimit: labels_user.length,
                autoSkip: true
              }
            },
            //y축
            y: {
              beginAtZero: true,  //Y축 0부터 시작
              ticks: {
                callback: function(value) {
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
            },
          },
          legend: {
            display: false
          },
          tooltips: {
            titleMarginBottom: 10,
            titleFontColor: '#6e707e',
            titleFontSize: 14,
            backgroundColor: "rgb(255,255,255)",
            bodyFontColor: "#858796",
            borderColor: '#dddfeb',
            borderWidth: 1,
            xPadding: 15,
            yPadding: 15,
            displayColors: false,
            caretPadding: 10,
            callbacks: {
              label: function(tooltipItem, chart) {
                var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                return datasetLabel + ': ' + number_format(tooltipItem.yLabel);
              }
            }
          },
        }
      });
    })
    .catch(function (error) {
      console.log("error: " + error);
    });

//사용자 대시보드 Bar Chart: 월별 발주량, 발주금액
//발주금액
const data_outputPrice = [];
//발주수량
const data_outputQuantity = [];

axios.get('/user/home/barchart_output')
    .then(function (response) {
      const UserOutputPrice = response.data.UserOutputPrice;
      const UserOutputQuantity = response.data.UserOutputQuantity;

      // 배열에 데이터 넣기
      for (let i = 0; i < 12; i++) {
        //발주금액 데이터
        data_outputPrice.push(UserOutputPrice[i].releasePrice);
        //발주수량 데이터
        data_outputQuantity.push(UserOutputQuantity[i].releaseQuantity);
      }

      //bar chart
      let ctx = document.getElementById("myBarChart2");
      let myBarChart2 = new Chart(ctx, {
        type: 'bar',
        data: {
          //x축 고정 1~12월
          labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
          datasets: [{
            //막대그래프: 발주금액
            label: "발주금액",
            data: data_outputPrice,
            yAxisID: 'yAxisLeft',  //왼쪽 축ID
            backgroundColor: "#0b30c2",
            hoverBackgroundColor: "#2e59d9",
            borderColor: "#4e73df",
            maxBarThickness: 25,
          },{
            //선그래프: 발주수량
            type: 'line',
            label: "발주수량",
            data: data_outputQuantity,
            yAxisID: 'yAxisRight', //오른쪽 축ID
            borderColor: "#f6c23e",
            backgroundColor: "#f6c23e",
            fill: false,
            tension: 0.2,         // 선의 곡률(부드럽게)
            pointRadius: 3,
            pointHoverRadius: 3,
          }],
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
                unit: 'month'
              },
              gridLines: {
                display: false,
                drawBorder: false
              },
              ticks: {
                maxTicksLimit: 12,
                autoSkip: true
              }
            }],
            yAxes: [
              {
                //왼쪽 축
                id: 'yAxisLeft',
                type: 'linear',
                position: 'left',
                ticks: {
                  beginAtZero: true,
                  callback: function(value) {
                    return number_format(value);
                  }
                },
                gridLines: {
                  color: "rgb(234, 236, 244)",
                  drawBorder: false,
                  borderDash: [2],
                  zeroLineBorderDash: [2]
                }
              },
              {
                //오른쪽 축
                id: 'yAxisRight',
                type: 'linear',
                position: 'right',
                ticks: {
                  beginAtZero: true,
                  callback: function(value) {
                    return number_format(value);
                  }
                },
              }
            ]
          },
          legend: {
            display: true,
            position: 'right',
            align: 'start',
          },
          tooltips: {
            titleMarginBottom: 10,
            titleFontColor: '#6e707e',
            titleFontSize: 14,
            backgroundColor: "rgb(255,255,255)",
            bodyFontColor: "#858796",
            borderColor: '#dddfeb',
            borderWidth: 1,
            xPadding: 15,
            yPadding: 15,
            displayColors: false,
            caretPadding: 10,
            callbacks: {
              label: function (tooltipItem, chart) {
                var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                return datasetLabel + ': ' + number_format(tooltipItem.yLabel);
              }
            }
          },
        }
      });
    })
    .catch(function (error) {
      console.log("error: " + error);
    });