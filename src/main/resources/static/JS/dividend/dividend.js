function dividendList(data){
    //alert(data.length);
    const datasets = {};
    const dataArray = [];
for(var key = 0; key < data.length; key++){
  dataArray.push({
    label: data[key].category,
    data : [
            data[key].january, 
            data[key].february, 
            data[key].march, 
            data[key].april, 
            data[key].may, 
            data[key].june, 
            data[key].july, 
            data[key].august, 
            data[key].september, 
            data[key].october, 
            data[key].november,
            data[key].december
    ],
    backgroundColor : chartColorr[key]
  });
    }
    // 곡선 차트
    //lineChart(dataArray);
    // 막대 차트
    barGraph(dataArray)
}
//막대 차트
function barGraph(dataArray){
var chBar = document.getElementById("bar_chart");
  var chartData = {
    labels: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
    datasets: dataArray
  };
  var myChart = new Chart(chBar, {
    // 챠트 종류를 선택
    type: 'bar',

    // 챠트를 그릴 데이타
    data: chartData,

    // 옵션
    options: {
    scales: {
                y: {
                    beginAtZero: true
                }
    },
      plugins: {
                legend: {
                    display: true,
                    position: 'top'
                },
                tooltip: {
                    mode: 'index',
                    intersect: false
                }
            }

    }
  });
}


const chartColorr = [
    "rgba(18, 203, 87, 0.6)"
  ,"rgba(255, 97, 63, 0.2)"
  ,"rgba(74, 128, 255, 0.8)"
  ,"rgba(150, 50, 200, 0.4)"
  ,"rgba(10, 180, 130, 0.7)"
  ,"rgba(88, 40, 120, 0.5)"
  ,"rgba(200, 80, 40, 0.3)"
  ,"rgba(33, 150, 210, 0.9)"
  ,"rgba(255, 175, 0, 0.1)"
  ,"rgba(120, 60, 255, 0.6)"
  ,"rgba(48, 205, 112, 0.4)"
  ,"rgba(180, 20, 65, 0.8)"
  ,"rgba(100, 160, 240, 0.7)"
  ,"rgba(220, 90, 30, 0.5)"
  ,"rgba(5, 190, 175, 0.3)"
  ,"rgba(75, 130, 220, 0.9)"
  ,"rgba(255, 150, 20, 0.1)"
  ,"rgba(130, 80, 190, 0.6)"
  ,"rgba(55, 215, 100, 0.4)"
  ,"rgba(190, 10, 90, 0.8)"
  ,"rgba(80, 140, 250, 0.7)"
  ,"rgba(240, 100, 50, 0.5)"
  ,"rgba(15, 180, 160, 0.3)"
  ,"rgba(85, 125, 230, 0.9)"
  ,"rgba(255, 135, 10, 0.1)"
  ,"rgba(140, 70, 180, 0.6)"
  ,"rgba(35, 220, 125, 0.4)"
  ,"rgba(200, 30, 60, 0.8)"
  ,"rgba(120, 170, 230, 0.7)"
  ,"rgba(230, 110, 20, 0.5)"
  ,"rgba(25, 200, 145, 0.3)"
  ,"rgba(95, 120, 240, 0.9)"
  ,"rgba(255, 120, 0, 0.1)"
  ,"rgba(160, 90, 170, 0.6)"
  ,"rgba(45, 225, 80, 0.4)"
  ,"rgba(210, 40, 50, 0.8)"
  ,"rgba(110, 150, 220, 0.7)"
  ,"rgba(250, 120, 40, 0.5)"
  ,"rgba(35, 210, 150, 0.3)"
  ,"rgba(65, 110, 235, 0.9)"  
    ]

function yearselect(yearmonth){
    const yearSelect = document.getElementById('yearmonth');
    const currentYear = new Date().getFullYear();
    const startYear = 2020; // 시작 년도
    const endYear = currentYear + 10; // 현재 년도에서 10년 뒤까지 표시

    for (let year = startYear; year <= endYear; year++) {
      const option = document.createElement('option');
      option.value = year;
      option.text = year;
      
      console.log("######################### " + (year == yearmonth));
      if(year == yearmonth){
        option.selected = true; // 현재 년도를 기본 선택으로 설정
        yearSelect.value = yearmonth;
        yearSelect.appendChild(option);
      }else{
      yearSelect.appendChild(option);
      }
    }
    // 현재 년도를 기본값으로 설정
    //yearSelect.value = currentYear;
  }


