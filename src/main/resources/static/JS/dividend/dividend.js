


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
    var imageInput = document.getElementById('inputFile');
    var selectedFile = imageInput.files[0];
    const inputAmount = document.getElementById('inputAmount').value;
    const inputTrnscdate = document.querySelector('input[type="date"]');
    //var byteArrayDisplay = document.getElementById('byteArrayDisplay');
       
    const imageFileInput = document.getElementById('inputFile');
    const fileName = imageFileInput.files[0].name;
    var stockName = (selectBox.options[selectBox.selectedIndex].value);
      
    let data = {
                stockName : stockName, // 주식명
                trnscdate : inputTrnscdate.value, //거래일자
                amount : inputAmount, // 거래 금액
                fName : fileName // 파일명
    }
  
  
    // 폼 데이터로 보내줘야 함
    let formData = new FormData();
    if (selectedFile) {
                    var reader = new FileReader();
    
                    reader.onload = function(event) {
                              var fileData = new Uint8Array(event.target.result);
                              //alert(JSON.stringify(Array.from(fileData)));
                              formData.append("files", JSON.stringify(Array.from(fileData)));
                              formData.append(
                                "key",
                                new Blob([JSON.stringify(data)], { type: "application/json" })
                              );
                                  // 바이트 배열을 출력
                                //byteArrayDisplay.textContent = JSON.stringify(Array.from(fileData));
  
                                fetch("/dividendInsert.do",
                                    {
                                        method : "post",
                                        body : formData,
                                    })
                                    .then((response) => {
                                        console.log(response.status);
                                        if(response.status == 200){
                                            alert("등록되었습니다.");
                                        }
                                    })
                                    .then(data => {
                                        location.reload();
                                    })
                                    .catch((error) => {
                                        alert("error " + error)
                                    }); 
                                };
    
                    reader.readAsArrayBuffer(selectedFile);
                } else {
                    alert('이미지 파일을 선택하세요.');
                }
}


// 배당 거래 클릭 이벤트 (수정 모달창 호출)
function fetchCall(event, exampleModal){
            var className = event.relatedTarget.className;
            var button = event.relatedTarget;
            var modalTitle = exampleModal.querySelector('.modal-title');
            var modalBodyStockNameInput = exampleModal.querySelector('.modal-body-stockName input');
            var modalBodyAmountInput = exampleModal.querySelector('.modal-body-Amount input');
            // 입력 요소 가져오기
            var updateTrnscdate = document.getElementById('updateTrnscdate');
            var data = button.getAttribute('data-bs-whatever');
            // 문자열을 중괄호 {}로 둘러싸고, 각 속성을 큰따옴표 "로 둘러싸는 JSON 형태로 변경해야 합니다.
            const keyValuePairs  = data.replace('{','').replace('}','').split(',');
            // 결과 JSON 객체를 저장할 변수를 생성합니다.
            var dateObj = {};
            // 스피너
            let spinner = document.getElementById('spinner');
            // 이미지
            let image1 = document.getElementById('image1');
            // 바디
            let modalBody = document.getElementById('modal-body');
            spinner.style.display = 'block';  // 표출
            modalBody.style.display = 'none'; // 숨기기
            // 각 키와 값을 분리하여 JSON 객체에 추가합니다.
            keyValuePairs.forEach(function(pair) {
              var keyValue = pair.split('=');
              var key = keyValue[0].trim();
              var value = keyValue[1];
              dateObj[key] = value;
            });

          // 파라미터를 담을 객체 생성
          const params = {
            stockName: dateObj.STOCK_NAME, // 항목명
            trnscdate: dateObj.TRNSCDATE, // 년도
            className : className // 클래스명
          };
          // 파라미터를 URL 형식으로 인코딩
          const queryString = new URLSearchParams(params).toString();

        // Fetch API를 사용하여 GET 요청 보내기
        fetch(`/dividendDtlsInqry.do?${queryString}`)
        .then(response => {
          if (!response.ok) {
            throw new Error('네트워크 오류');
          }
          return response.text();
        })
        .then(data => {
          if('' != data){
          //# JSON 데이터 파싱
          var parsedData = JSON.parse(data);
           modalTitle.textContent = '배당 수정 : ' + parsedData.transactionDto.stockName;
           modalBodyStockNameInput.value = parsedData.transactionDto.stockName;
           modalBodyAmountInput.value = parsedData.transactionDto.amount;
           // // 입력 요소에 날짜 설정
           updateTrnscdate.value = parsedData.transactionDto.trnscdate;
           if(('' != parsedData.fileDTO ) && (null != parsedData.fileDTO )){
              // 이미지 출력
              fu_img(parsedData.fileDTO.reContents);
           }else{
              spinner.style.display = 'none';
              image1.style.display = 'none'; 	
           }    
          }else{
            alert('결과가 없습니다.');
            modalBodyStockNameInput.value = "";
            modalBodyAmountInput.value = "";
            updateTrnscdate.value = "";
            spinner.style.display = 'none';
            image1.style.display = 'none'; 	
          }
        })
        .catch(error => {
          console.error(error);
        });
}

// 이미지 출력
function fu_img(imgData){
  // 스피너
  let spinner = document.getElementById('spinner');
  // 바디
  let modalBody = document.getElementById('modal-body');
  // 이미지를 표시할 <img> 요소 가져오기
  var imageElement1 = document.getElementById("image1");
  var byteData =  JSON.parse(imgData);
  // 바이트 데이터를 Blob으로 변환
  var blobData = new Blob([new Uint8Array(byteData)], { type: 'image/jpeg' });

  modalBody.style.display = 'block'; 	// 표출
  // Blob 데이터를 Blob URL로 변환
  var blobUrl = URL.createObjectURL(blobData);
  imageElement1.src =  blobUrl;
  spinner.style.display = 'none'; 	// 숨기기
}



function dateChek(date){
  const val = "/./g";
  // 정규 표현식으로 모든 출현을 검색
  const dotCount = (date.match(/\./g) || [0]).length;
  if((1 != dotCount) ){
    alert("오류 발생");
    return true;
  }else{
    return false;
  }
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
  ]