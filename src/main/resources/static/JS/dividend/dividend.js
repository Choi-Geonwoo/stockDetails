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
  ]

function dividendList(data){
    //alert(data.length);
    const datasets = {};
    const dataArray = [];
    const TRNSCDATE = [];
for(var key = 0; key < data.length; key++){
  TRNSCDATE.push(data[key].TRNSCDATE),
  dataArray.push({
    label: data[key].TRNSCDATE + " - "+data[key].STOCK_NAME,
    data : [
            data[key].JANUARY, 
            data[key].FEBRUARY, 
            data[key].MARCH, 
            data[key].APRIL, 
            data[key].MAY, 
            data[key].JUNE, 
            data[key].JULY, 
            data[key].AUGUST, 
            data[key].SEPTEMBER, 
            data[key].OCTOBER, 
            data[key].NOVEMBER,
            data[key].DECEMBER
    ],
    backgroundColor : chartColorr[key]
  });
  }
  //console.log(TRNSCDATE);
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


// 조회 년도 셀렉트 박스
function trnscdateSelect(yearmonth){
    const yearSelect = document.getElementById('trnscdateSelect');
    const currentYear = new Date().getFullYear();
    const startYear = 2020; // 시작 년도
    const endYear = currentYear + 10; // 현재 년도에서 10년 뒤까지 표시

    for (let year = startYear; year <= endYear; year++) {
      const option = document.createElement('option');
      option.value = year;
      option.text = year;
      //console.log("year " + year + " yearmonth : " + yearmonth);
      if(year == yearmonth){
        option.selected = true; // 현재 년도를 기본 선택으로 설정
        yearSelect.value = yearmonth;
        yearSelect.appendChild(option);
      }else{
      yearSelect.appendChild(option);
      }
    }
    // 현재 년도를 기본값으로 설정
    if(null == yearmonth){
        yearSelect.value = currentYear;
    }
  }

 // 배당 등록
  function transactionInsert(){
    // 주식명
    var selectBox  = document.getElementById("selectBox");
		var stockName = (selectBox.options[selectBox.selectedIndex].value);
    
    const inputTrnscdate = document.querySelector('input[type="date"]');
    const inputAmount = document.getElementById('inputAmount').value;
    //alert(stockName+" | "+ dateControl.value+" | "+inputTrnscdate + " | " + inputAmount);

    fetch("/dividendInsert.do",
        {
            method : "post",
            headers : {
                "Content-Type" : "application/json",
            },
            body : JSON.stringify({
                stockName : stockName, // 주식명
                trnscdate : inputTrnscdate.value, //거래일자
                amount : inputAmount // 거래 금액
            }),
        })
        .then((response) => {
            console.log(response.status);
            if(response.status == 200){
                alert("등록되었습니다..");
            }
        })
        .then(data => {
            location.reload();
        })
        .catch((error) => {
            alert("error " + error)
        }); 
}

// 배당 거래 내역등록
function detailsInsert() {
  const inputYearmonth = document.getElementById('inputYearmonth').value;
  const inputJanuary = document.getElementById('inputJanuary').value;
  const inputFebruary = document.getElementById('inputFebruary').value;
  
  // You can use the name and age values for your update logic
  // For now, let's just display an alert
  //alert(order + " " + name);
  fetch("/dividendInsert.do",
      {
          method : "post",
          headers : {
              "Content-Type" : "application/json",
          },
          body : JSON.stringify({
              yearmonth : inputYearmonth,
              january : inputJanuary,
              february : inputFebruary,
              march : inputMarch,
              april : inputApril,
              may : inputMay,
              june : inputJune,
              july : inputJuly,
              august : inputAugust,
              september : inputSeptember,
              october : inputOctober,
              november : inputNovember,
              december : inputDecember
          }),
      })
      .then((response) => {
          console.log(response.status);
          if(response.status == 200){
              alert("등록되었습니다..");
          }
      })
      .then(data => {
          //alert(data);
          location.reload();
      })
      .catch((error) => {
          alert("error " + error)
      });
}

function update(){
  alert();
	$("#new_modal").modal("show");
}