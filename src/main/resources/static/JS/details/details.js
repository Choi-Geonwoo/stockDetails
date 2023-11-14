
// 주식 검색
function stockSrch(){
    // 폼 요소와 셀렉트 박스 요소 가져오기
    var form = document.getElementById('myForm');
    var selectBox = document.getElementById('selectBox');

    // 폼 제출 시 실행할 함수 정의
    form.addEventListener('submit', function(e) {

        // 선택된 옵션의 값을 가져오기
        var selectedValue = selectBox.value;
        debugger;

        // 선택된 값을 폼 필드에 설정합니다.
        // 이 예제에서는 폼 필드의 name 속성을 "selectedOption"으로 지정합니다.
        var inputField = document.createElement('input');
        inputField.type = 'hidden';
        inputField.name = 'stockName';
        inputField.value = selectedValue;
        form.appendChild(inputField);

        // 폼을 서버로 제출합니다.
        form.submit();
    });
}

// 등록
function detailsInsert(button) {
    const row               = button.parentElement.parentElement;
    const columns           = row.getElementsByTagName('td');
    const name              = columns[0].querySelector('input').value;
    const shares            = columns[1].querySelector('input').value;
    const stock_dividends   = columns[2].querySelector('input').value;
    const maeipkkeum        = columns[3].querySelector('input').value;
    const dividends         = columns[4].querySelector('input').value;

    // You can use the name and age values for your update logic
    // For now, let's just display an alert
    //alert(order + " " + name);
    fetch("/detailsInsert.do",
        {
            method : "post",
            headers : {
                "Content-Type" : "application/json",
            },
            body : JSON.stringify({
                stockName : name,
                stockQuantity : shares,
                dividendCycle : stock_dividends,
                purchasePrice : maeipkkeum,
                dividendAmount : dividends
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

// 수정
function detailsUpdate(button) {
    const tableRow = button.closest("tr");
    const cells = tableRow.querySelectorAll("td");
    const order             = cells[0].querySelector('input').value;
    const name              = cells[1].querySelector('input').value;
    const shares            = cells[2].querySelector('input').value;
    const stock_dividends   = cells[3].querySelector('input').value;
    const maeipkkeum        = cells[4].querySelector('input').value;
    const dividends         = cells[5].querySelector('input').value;
    //const date              = cells[6].querySelector('input').value;

    fetch("/detailsUpdate.do",
    {
        method : "post",
        headers : {
            "Content-Type" : "application/json",
        },
        body : JSON.stringify({
            no  :    order,
            stockName           : name,
            stockQuantity       : shares,
            dividendCycle       : stock_dividends,
            purchasePrice       : maeipkkeum,
            dividendAmount      : dividends,
            clscd               : 'Y'
        }),
    })
    .then((response) => {
        console.log(response.status);
        if(response.status == 200){
            alert("수정되었습니다.");
        }
    })
    .then((data) => {
        //alert(data);
        location.reload();
    })
    .catch((error) => {
        alert("error " + error);
    });
}


// 삭제
function detailsDelete(button) {
    const row               =button.closest("tr");
    const columns           = row.querySelectorAll("td");
    const registration_order= columns[0].querySelector('input').value;

    // You can use the name and age values for your update logic
    // For now, let's just display an alert
    //alert(order + " " + name);
    fetch("/detailsDelete.do",
        {
            method : "post",
            headers : {
                "Content-Type" : "application/json",
            },
            body : registration_order,
        })
        .then((response) => {
            console.log(response.status);
            if(response.status == 200){
                alert("삭제되었습니다.");
            }
        })
        .then(data => {
            location.reload();
        })
        .catch((error) => {
            alert("error " + error)
        });
    };

    function myChart(data){
        // 주식 수
        let [sharesArray, sharesName] = sharesList(data);
        

        //lineMyChart2(sharesArray, sharesName);
        // 주식 수 기준
        // 파이 차트
        pieMyChart(sharesArray, sharesName,"주식 수", "sharesPieMyChart");
        // 막대 차트
        barChart(sharesArray, sharesName,"주식수 기준" ,"sharesBarMyChart");

        let [dvdndArray, dvdndName] = dvdndList(data);
        // 막대 차트
        barChart(dvdndArray, dvdndName,"배당금 기준", "dvdndBarMyChart");
        // 파이 차트
        pieMyChart(dvdndArray, dvdndName,"배당금 기준", "dvdndPieMyChart");

    }    
    // 파이 차트
    function pieMyChart(y,x,label, pieMyChart){
        var context = document
                .getElementById(pieMyChart)
                .getContext('2d');
                var myChart = new Chart(context, {
                type: 'pie', // 차트의 형태
                data: { // 차트에 들어갈 데이터
                    labels: x,
                    datasets: [
                        { //데이터
                            label: label, //차트 제목
                            fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
                            data: y,
                            backgroundColor: chartColorr,
                            borderColor: chartColorr,
                            borderWidth: 1 //경계선 굵기
                        }
                    ]
                },
                options: {
                    scales: {
                        yAxes: [
                            {
                                ticks: {
                                    beginAtZero: true
                                }
                            }
                        ]
                    }
                }
            });
    }

    // 막대 차트
    function barChart(dataArray,TRNSCDATE,label, barMyChart){
          
        new Chart(document.getElementById(barMyChart), {
            type: 'bar',
            data: {
                labels: TRNSCDATE,
                datasets: [{ 
                    label : label,
                    data: dataArray,
                    //label: TRNSCDATE,
                    backgroundColor: chartColorr,
                    borderColor: chartColorr,
                    fill: false
                  }
                ]
              },
            options: {
              title: {
                //display: true,
                //text: 'World population per region (in millions)'
              }
            }
          }); 
    }    

    // 주식 수
    function sharesList(data){
        //alert(data.length);
        const datasets = {};
        const dataArray = [];
        const TRNSCDATE = [];
        for(var key = 0; key < data.length; key++){
            TRNSCDATE.push(data[key].stockName);
            dataArray.push(data[key].stockQuantity);
        }
      return [dataArray,TRNSCDATE];
    }


    // 배당금 기준
    function dvdndList(data){
        //alert(data.length);
        const datasets = {};
        const dataArray = [];
        const TRNSCDATE = [];
        for(var key = 0; key < data.length; key++){
            if("0" != data[key].dividendAmount){
                TRNSCDATE.push(data[key].stockName);
                dataArray.push(Math.round((data[key].dividendAmount * strIndexOf(data[key].dividendCycle) * 100)  )/ 100);
            }
        }
      return [dataArray,TRNSCDATE];
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

// 등록
function newDetailsInsert2(){
    const inputStockName = document.getElementById('inputStockName').value;
    const inputStockQuantity = document.getElementById('inputStockQuantity').value;
    const inputDividendCycle = document.getElementById('inputDividendCycle').value;
    const inputPurchasePrice = document.getElementById('inputPurchasePrice').value;
    const inputDividendAmount = document.getElementById('inputDividendAmount').value;
    //alert(inputStockQuantity);

    fetch("/detailsInsert.do",
        {
            method : "post",
            headers : {
                "Content-Type" : "application/json",
            },
            body : JSON.stringify({
                stockName : inputStockName,
                stockQuantity : inputStockQuantity,
                dividendCycle : inputDividendCycle,
                purchasePrice : inputPurchasePrice,
                dividendAmount : inputDividendAmount
            }),
        })
        .then((response) => {
            console.log(response.status);
            if(response.status == 200){
                alert("등록되었습니다.");
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


function strIndexOf(text){
    var count = 1;
    var searchChar = ','; // 찾으려는 문자
    var pos = text.indexOf(searchChar); //pos는 0의 값을 가집니다.

    while (pos !== -1) {
        count++;
        pos = text.indexOf(searchChar, pos + 1); // 첫 번째 a 이후의 인덱스부터 a를 찾습니다.
    }
    if('월' == text){
        count = 12;
    }
    return count;
}

function displayCheck(box){
    if(box.checked == true){
        var elements = document.getElementsByName('paragraph');
         for (var i = 0; i < elements.length; i++) {
           elements[i].className='hide_column';
         }
    }else{
        var elements = document.getElementsByName('paragraph');
        for (var i = 0; i < elements.length; i++) {
            elements[i].className ='w-10';
        }
    }
}
