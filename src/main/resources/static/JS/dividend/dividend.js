function dividendList(data, monthSelect) {
  const dataArray = [];
  const TRNSCDATE = [];
  var dataValues = [];
  
  for (const item of data) {
      TRNSCDATE.push(item.TRNSCDATE);
      
      const label = `${item.TRNSCDATE} - ${item.STOCK_NAME}`;
      if(!isEmpty(monthSelect) ){
        dataValues = [item[monthToNumber(monthSelect)]];
      }
      else{
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
      console.log("라벨 : " + label);
      dataArray.push({
          label: label,
          data: dataValues,
          backgroundColor: backgroundColor
      });
  }
  console.log(monthToNumber(monthSelect));
  console.log(dataArray);
  const months = isEmpty(monthSelect) ? ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"] : [`${monthSelect}월`];

  createBarChart(dataArray, months);
}

function createBarChart(dataArray, months) {
  const chBar = document.getElementById("bar_chart");
  const chartData = {
      labels: months,
      datasets: dataArray
  };
  console.log(chartData);

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

 // 배당 등록
  function transactionInsert(){
    var imageInput = document.getElementById('inputFile');
    var selectedFile = imageInput.files[0];
    const inputAmount = document.getElementById('inputAmount').value;
    const inputDiviend = document.getElementById('inputDiviend').value;
    const inputTrnscdate = document.querySelector('input[type="date"]');
    //inputDiviend
       
    const imageFileInput = document.getElementById('inputFile');
    if(isEmpty(inputAmount)){
      alert("거래금액 입력해주세요.");
      return;
    }
    var fileName;
    var stockName = (selectBox.options[selectBox.selectedIndex].value);
    if(!isEmpty(imageFileInput.files[0])){
      fileName = imageFileInput.files[0].name;
    }
    let data = {
                stockName : stockName,  // 주식명
                trnscdate : inputTrnscdate.value, //거래일자
                amount : inputAmount,   // 거래 금액
                fName : fileName,       // 파일명
                dividend : inputDiviend  // 배당금
    }
      // 이미지 수정하는 경우
  if(!isEmpty(imageFileInput.files[0])){
    imgFile(selectedFile, data, "/dividendInsert.do");
  }else{
    // 데이터만 수정
    dataTransfer(data, "/dividendInsert.do");
  }
    
}


// 배당 거래 클릭 이벤트 (수정 모달창 호출)
function fetchCall(event, exampleModal){
            var className = event.relatedTarget.className.split(' ')[0];
            var button = event.relatedTarget;
            var modalTitle = exampleModal.querySelector('.modal-title');
            var modalBodyStockNameInput = exampleModal.querySelector('.modal-body-stockName input');
            var modalDiviend = exampleModal.querySelector('.modal-body-Diviend input');
            var modalBodyAmountInput = exampleModal.querySelector('.modal-body-Amount input');
            var modalBodyNoInput = exampleModal.querySelector('.modal-body-no input');
            var modalBodyFNoInput = exampleModal.querySelector('.modal-body-fno input');
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
            // 파일 이름
            var modalFileName = exampleModal.querySelector('.modal-fileName');
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
        //debugger;
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
           const el = document.getElementById('updatestockName');  //select box
           const len = el.options.length; //select box의 option 갯수
           for (let i=0; i<len; i++){  
            //select box의 option value가 입력 받은 value의 값과 일치할 경우 selected
            if(el.options[i].value == parsedData.transactionDto.stockName){
              el.options[i].selected = true;
            }
          } 
           //modalBodyStockNameInput.value = parsedData.transactionDto.stockName; // 배당 거래내역 주식명
           modalBodyAmountInput.value = parsedData.transactionDto.amount; // 배당 거래내역 금액
           modalBodyNoInput.value = parsedData.transactionDto.no; // 배당 거래내역 순번
           modalDiviend.value = parsedData.transactionDto.dividend; // 배당금
           modalFileName.textContent = parsedData.transactionDto.stockName; //파일명
           //debugger;
           // // 입력 요소에 날짜 설정
           updateTrnscdate.value = parsedData.transactionDto.trnscdate;
           if(('' != parsedData.fileDTO ) && (null != parsedData.fileDTO )){
             // 이미지 출력
             fu_img(parsedData.fileDTO.reContents);
             modalBodyFNoInput.value = parsedData.fileDTO.fno;
           }else{
              spinner.style.display = 'none';
              image1.style.display = 'none'; 	//숨기기
              modalBody.style.display = 'block';  // 표출
           }    
          }else{
            alert('결과가 없습니다.');
            modalBodyStockNameInput.value = "";
            modalBodyAmountInput.value = "";
            updateTrnscdate.value = "";
            spinner.style.display = 'none';
            image1.style.display = 'none'; 	
            exampleModal.hide();
          }
        })
        .catch(error => {
          console.error(error);
          alert("오류가 발생했습니다.");
          return;
        });
}



// 수정 버튼 동작
function transactionUpdate(){
  let updateTno = document.getElementById('updateTno').value;
  let updateFno = document.getElementById('updateFno').value;
  let updatestockName = document.getElementById('updatestockName').value;
  let updateTrnscdate = document.getElementById('updateTrnscdate').value;
  let updateAmount = document.getElementById('updateAmount').value;
  let updateDiviend = document.getElementById('updateDiviend').value;

  var fileName;
  const updateFile = document.getElementById('updateFile');
  var selectedFile = updateFile.files[0];
  if(!isEmpty(updateFile.files[0])){
    fileName = updateFile.files[0].name;
  }
  let data = {
              no : updateTno,           //배당 거래내역 순번
              stockName : updatestockName, // 주식명
              trnscdate : updateTrnscdate, //거래일자
              amount : updateAmount,    // 거래 금액
              fName : fileName,         // 파일명
              fNo : updateFno,          // 파일 순번
              dividend : updateDiviend   // 배당금
  }
  // 이미지 수정하는 경우
  if(!isEmpty(updateFile.files[0])){
    imgFile(selectedFile, data, "/dividendUpdate.do");
  }else{
    // 데이터 전송
    dataTransfer(data, "/dividendUpdate.do");
  }
  
}

// 삭제 버튼 동작
function transactionDelete(){
  let updateTno = document.getElementById('updateTno').value;

  let data = {
              no : updateTno //배당 거래내역 순번
  }
    // 데이터 전송
    dataTransfer(data, "/dividendDelete.do");  
}

// 데이터 전송
function dataTransfer(data, url){
  // 폼 데이터로 보내줘야 함
  let formData = new FormData();
  formData.append("files", "");
  formData.append("key",  new Blob([JSON.stringify(data)], { type: "application/json" }));
  fetch(url,
    {
        method : "post",
        body : formData,
    })
    .then((response) => {
        //console.log(response.status);
        if(response.status != 200){
            alert("오류 발생했습니다.");
        }
        return response.json(); // 응답 데이터를 파싱하고 반환
    })
    .then(data => {
      if(-1 != data.retNo){
        alert(data.msg+"되었습니다.");
        location.reload();
      }else{
        alert("오류가 발생되었습니다.");
        location.reload();
      }
    })
    .catch((error) => {
        alert("error " + error);
    }); 

}

// 이미지 포함 전송
function imgFile(selectedFile, data, url){
        // 폼 데이터로 보내줘야 함
        let formData = new FormData();
          if (selectedFile) {
            var reader = new FileReader();

            reader.onload = function(event) {
                      var fileData = new Uint8Array(event.target.result);
                      formData.append("files", JSON.stringify(Array.from(fileData)));
                      formData.append(
                        "key",
                        new Blob([JSON.stringify(data)], { type: "application/json" })
                      );

                        fetch(url,
                            {
                                method : "post",
                                body : formData,
                            })
                            .then((response) => {
                                console.log(response.status);
                                if(response.status != 200){
                                  alert("오류 발생했습니다.");
                                }
                                return response.json(); // 응답 데이터를 파싱하고 반환
                            })
                            .then(data => {
                                    if(-1 != data.retNo){
                                      alert(data.msg+"되었습니다.");
                                      location.reload();
                                    }else{
                                      alert("오류가 발생되었습니다.");
                                      location.reload();
                                    }
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



// 이미지 출력
function fu_img(imgData){
  // 스피너
  let spinner = document.getElementById('spinner');
  // 바디
  let modalBody = document.getElementById('modal-body');
  // 이미지를 표시할 <img> 요소 가져오기
  var imageElement1 = document.getElementById("image1");
  // 이미지
  let image1 = document.getElementById('image1');
  var byteData =  JSON.parse(imgData);
  // 바이트 데이터를 Blob으로 변환
  var blobData = new Blob([new Uint8Array(byteData)], { type: 'image/jpeg' });

  // Blob 데이터를 Blob URL로 변환
  var blobUrl = URL.createObjectURL(blobData);
  modalBody.style.display = 'block'; 	// 표출
  image1.style.display = 'block'; 	// 표출
  imageElement1.src =  blobUrl;
  spinner.style.display = 'none'; 	// 숨기기
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



  function monthToNumber(month) {
    const monthMap = {
        '01':'JANUARY',
        '02':'FEBRUARY',
        '03':'MARCH',
        '04':'APRIL',
        '05':'MAY',
        '06':'JUNE',
        '07':'JULY',
        '08':'AUGUST',
        '09':'SEPTEMBER',
        '10':'OCTOBER',
        '11':'NOVEMBER',
        '12':'DECEMBER'
    };

    return monthMap[month] || 0; // Return 0 for invalid months.
}