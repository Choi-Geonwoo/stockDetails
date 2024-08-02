
function myChart001(data){
  
    // 막대 차트
    barChart001(data, "bar_chart");
    
    // 막대 차트
    //barChart002(data, "bar_chart");
  
  }



    // 주식 수
    function sharesList001(data){
        //alert(data.length);
        const datasets = {};
        const dataArray = [];
        const TRNSCDATE = [];
        const trnscDate = [];
        const eachMoney01 = []; //EACH_MONEY01
        console.log(" || " +JSON.stringify(data));
        for(var key = 0; key < data.length; key++){
            TRNSCDATE.push(data[key].STOCK_NAME);
            dataArray.push(data[key].EACH_MONEY);
            trnscDate.push(data[key].TRNSC_DATE);
            eachMoney01.push(data[key].EACH_MONEY01);
        }
      return [dataArray,TRNSCDATE, trnscDate, eachMoney01];
    }



// 막대 차트
function barChart001(value, barMyChart) {
  var months = ["JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"];
  var stockNames = value.map(stock => stock.STOCK_NAME);

  var datasets = stockNames.map((stockName, index) => {
      return {
          label: stockName,
          data: months.map(month => value[index][month]),
          backgroundColor: `rgba(${Math.floor(Math.random() * 255)}, ${Math.floor(Math.random() * 255)}, ${Math.floor(Math.random() * 255)}, 0.2)`,
          borderColor: `rgba(${Math.floor(Math.random() * 255)}, ${Math.floor(Math.random() * 255)}, ${Math.floor(Math.random() * 255)}, 1)`,
          borderWidth: 1
      }
  });

  var ctx = document.getElementById(barMyChart).getContext('2d');
  var myChart = new Chart(ctx, {
      type: 'bar',
      data: {
          labels: Object.values(monthNumbers),
          datasets: datasets
      },
      options: {
          scales: {
              y: {
                  beginAtZero: true
              }
          }
      }
  });
}


// Filter out zero values
function getFilteredData(stock) {
/*
  console.log("1" + 
    JSON.stringify(Object.keys(monthNumbers)
            .filter(month => stock[month] !== 0)
            .map(month => ({
                month: monthNumbers[month],
                value: stock[month]
            }))
          )
  );
*/
  return Object.keys(monthNumbers)
      .filter(month => stock[month] !== 0)
      .map(month => ({
          month: monthNumbers[month],
          value: stock[month]
      }));
}


// Filter out zero values
function getFilteredLabel(stock) {

  console.log("1" + 
    JSON.stringify(Object.keys(monthNumbers)
            .filter(month => stock[month] !== 0)
            .map(month => ({
                month: monthNumbers[month]
            }))
          )
  );  
  return Object.keys(monthNumbers)
      .filter(month => stock[month] !== 0)
      .map(month => ({
          month: monthNumbers[month]
      }));
  }

// Generate bar chart
function barChart002(value, barMyChart) {
  // JSON 데이터를 자바스크립트 객체로 변환
  var filteredLabel = null;
  //console.log(obj);

      var datasets = value.map(stock => {
      var filteredData = getFilteredData(stock);
      filteredLabel = getFilteredLabel(stock);
      return {
          label: stock.STOCK_NAME,
          data: filteredData.map(data => data.value),
          backgroundColor: `rgba(${Math.floor(Math.random() * 255)}, ${Math.floor(Math.random() * 255)}, ${Math.floor(Math.random() * 255)}, 0.2)`,
          borderColor: `rgba(${Math.floor(Math.random() * 255)}, ${Math.floor(Math.random() * 255)}, ${Math.floor(Math.random() * 255)}, 1)`,
          borderWidth: 1
      };
  });
  console.log(filteredLabel);
  var ctx = document.getElementById(barMyChart).getContext('2d');
  var myChart = new Chart(ctx, {
      type: 'bar',
      data: {
          labels: Object.values(monthNumbers),
          datasets: datasets
      },
      options: {
          scales: {
              y: {
                  beginAtZero: true
              },
              x: {
                  type: 'category',
                  labels: Object.values(monthNumbers)
              }
          }
      }
  });
}






/******
 변수
 ******/
// Convert month names to month numbers
const monthNumbers = {
  "JANUARY": 1, "FEBRUARY": 2, "MARCH": 3, "APRIL": 4, "MAY": 5, "JUNE": 6, 
  "JULY": 7, "AUGUST": 8, "SEPTEMBER": 9, "OCTOBER": 10, "NOVEMBER": 11, "DECEMBER": 12
};
 
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