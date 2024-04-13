function yearComparisonBarChart(data) {
  const dataArray = [];
  var dataValues = [];
  
  for (const item of data) {
      
      var label = `${item.TRNSCDATE}` == "합계" ? "" : `${item.TRNSCDATE}`;
      if(label != ""){
        dataValues = [
            item.JANUARY, 
            item.FEBRUARY, 
            item.MARCH, 
            item.APRIL, 
            item.MAY,
            item.JUNE, 
            item.JULY, 
            item.AUGUST, 
            item.SEPTEMBER, 
            item.OCTOBER, 
            item.NOVEMBER, 
            item.DECEMBER
        ];
      }
      
      const backgroundColor = chartColorr[data.indexOf(item)]; // Assuming chartColorr is defined elsewhere.
      if(label != ""){
            dataArray.push({
                label: label,
                data: dataValues,
                backgroundColor: backgroundColor
            });
        }
  }
  //console.log(monthToNumber(monthSelect));
  const months = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"] ;

  createBarChart(dataArray, months);
}



function createBarChart(dataArray, months) {
  const chBar = document.getElementById("bar_chart");
  const chartData = {
      labels: months,
      datasets: dataArray
  };

  const myChart = new Chart(chBar, {
      type: 'bar',
      data: chartData,
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




// 배당 거래 내역등록
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
  ];