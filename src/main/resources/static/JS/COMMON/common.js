function fu_monthSelect(monthValue){
  // 월 선택 상자 엘리먼트 찾기
  var monthSelect = document.getElementById("monthSelect");
  
  // 현재 날짜를 가져오는 JavaScript 객체 생성
  var currentDate = new Date();
  
  // 현재 월을 가져오기 (0부터 시작하므로 1을 더해줍니다)
  var currentMonth = currentDate.getMonth() + 1;
  
    // 월 선택 상자에서 현재 월과 일치하는 옵션을 선택합니다.
    for (var i = 0; i < monthSelect.options.length; i++) {
      if (monthSelect.options[i].value == monthValue) {
        monthSelect.options[i].selected = true;
        break; // 원하는 옵션을 찾으면 루프 종료
      }else if (monthSelect.options[i].value == currentMonth) {
            monthSelect.options[i].selected = true;
            break; // 원하는 옵션을 찾으면 루프 종료
        }
    }
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


/**
	 * 문자열이 빈 문자열인지 체크하여 결과값을 리턴한다. 
	 * @param str		: 체크할 문자열
 **/
function isEmpty(str){
		
  if(typeof str == "undefined" || str == null || str == "")
    return true;
  else
    return false ;
}

function toDay(){
  const today = new Date();
  const year = today.getFullYear();
  const month = String(today.getMonth() + 1).padStart(2, '0');
  const day = String(today.getDate()).padStart(2, '0');

  const formattedDate = `${year}-${month}-${day}`;
  return formattedDate;
}



/**
	 * 엑셀 다운로드 
	 * @param str		: 체크할 문자열
 **/
const excelHandler = {
  getExcelFileName: function (fileName) {
    return fileName + toDay() + '.xlsx'; // 파일명
  },
  getSheetName: function () {
    return 'Table Test Sheet'; // 시트명
  },
  getExcelData: function (table_id) {
    const table = document.getElementById(table_id);
    const rows = table.querySelectorAll('tbody tr');
    const headerRow = table.querySelectorAll('thead th');
    const data = [Array.from(headerRow).map(th => th.textContent.trim())];

    rows.forEach(row => {
      const rowData = [];
      const cells = row.querySelectorAll('td input');

      cells.forEach(cell => {
        rowData.push(cell.value);
      });

      data.push(rowData);
    });

    return data;
  },
  getWorksheet: function (table_id) {
    console.log("ret : " + table_id);
    //return XLSX.utils.aoa_to_sheet(this.getExcelData(table_id));
  }
};

document.addEventListener('DOMContentLoaded', () => {
  const excelDownload = document.querySelector('#excelDownload');
  if (!excelDownload) return; // excelDownload 버튼이 없으면 리턴

  const fileName = "거래내역";
  const tableId = "myTable2"; // 실제 테이블 ID로 변경

  excelDownload.addEventListener('click', () => exportExcel(fileName, tableId));
});

function exportExcel(fileName, table_id) {
  const wb = XLSX.utils.book_new();
  console.log("ret : " + table_id);
  const newWorksheet = excelHandler.getWorksheet(table_id);
  XLSX.utils.book_append_sheet(wb, newWorksheet, excelHandler.getSheetName());

  const wbout = XLSX.write(wb, { bookType: 'xlsx', type: 'binary' });
  saveAs(new Blob([s2ab(wbout)], { type: 'application/octet-stream' }), excelHandler.getExcelFileName(fileName));
}

function s2ab(s) {
  const buf = new ArrayBuffer(s.length); // s를 arrayBuffer로 변환
  const view = new Uint8Array(buf); // uint8array 생성
  for (let i = 0; i < s.length; i++) view[i] = s.charCodeAt(i) & 0xFF; // 옥텟으로 변환
  return buf;
}


function downloadExcel(tbName, tbId) {
  var fileName = tbName + toDay() + '.xlsx'; // 파일명
  const table = document.getElementById(tbId);
  const workbook = XLSX.utils.table_to_book(table, { sheet: "Sheet1" });

  // 엑셀 파일을 바이너리 형태로 변환
  const wbout = XLSX.write(workbook, { bookType: 'xlsx', type: 'binary' });

  // 바이너리 문자열을 ArrayBuffer로 변환하는 함수
  function s2ab(s) {
    const buf = new ArrayBuffer(s.length);
    const view = new Uint8Array(buf);
    for (let i = 0; i < s.length; i++) view[i] = s.charCodeAt(i) & 0xFF;
    return buf;
  }

  // 엑셀 파일 다운로드
  saveAs(new Blob([s2ab(wbout)], { type: 'application/octet-stream' }), fileName+ '.xlsx');
}


function exportTableToExcel(tbName, tbId) {
  var fileName = tbName + toDay() + '.xlsx'; // 파일명
  // 테이블 요소를 가져옵니다.
  const table = document.getElementById(tbId);
  const wb = XLSX.utils.book_new();
  
  // 테이블의 각 행을 가져옵니다.
  const data = [];
  const rows = table.querySelectorAll('tbody tr');

  rows.forEach(row => {
      const cells = row.querySelectorAll('td');
      const rowData = Array.from(cells).map(cell => {
          // 셀 안에 입력 필드가 있는 경우 값 추출
          const input = cell.querySelector('input');
          return input ? input.value : cell.textContent;
      });
      data.push(rowData);
  });

  // 첫 번째 행은 헤더로 설정합니다.
  const headers = Array.from(table.querySelectorAll('thead th')).map(th => th.textContent);
  data.unshift(headers);

  // 데이터로 워크시트를 생성합니다.
  const ws = XLSX.utils.aoa_to_sheet(data);
  XLSX.utils.book_append_sheet(wb, ws, 'Table Data');
  
  // 워크북을 Excel 파일로 저장합니다.
  XLSX.writeFile(wb, fileName);
}


/* 테이블 정렬 */
function sortTable(id,n) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById(id);
  switching = true;
  //Set the sorting direction to ascending:
  dir = "asc"; 
  /*Make a loop that will continue until
  no switching has been done:*/
  while (switching) {
    //start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    /*Loop through all table rows (except the
    first, which contains table headers):*/
    for (i = 1; i < (rows.length - 1); i++) {
      //start by saying there should be no switching:
      shouldSwitch = false;
      /*Get the two elements you want to compare,
      one from current row and one from the next:*/
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      /*check if the two rows should switch place,
      based on the direction, asc or desc:*/
      if (dir == "asc") {
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          //if so, mark as a switch and break the loop:
          shouldSwitch= true;
          break;
        }
      } else if (dir == "desc") {
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          //if so, mark as a switch and break the loop:
          shouldSwitch = true;
          break;
        }
      }
    }
    if (shouldSwitch) {
      /*If a switch has been marked, make the switch
      and mark that a switch has been done:*/
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      //Each time a switch is done, increase this count by 1:
      switchcount ++;      
    } else {
      /*If no switching has been done AND the direction is "asc",
      set the direction to "desc" and run the while loop again.*/
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}



function fetch001(url, method, body){
      // 서버로 데이터를 전송합니다.
      fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();  // JSON으로 변환된 데이터를 반환
    })
    .then(result => {
        console.log('Success:', JSON.stringify(result));
        //alert(JSON.stringify(result));
        //alert(result.str+ " | " + result.strYn);
        //alert(result.str);
        showAlert(JSON.stringify(result)); //
        
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to update row.');
    });
}



function reChartColorr(){
return reChartColorr = [
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
}



/*모달창 호출 */
function showAlert(data) {
  const modal = document.createElement('div');
  //# JSON 데이터 파싱
  var parsedData= JSON.parse(data);     
  var title = parsedData.strYn == "Y" ? "성공" : "실패";
  modal.classList.add('modal-background01');
  modal.innerHTML = `
      <div class="modal-content01">
          <div class="modal-title01">
            <p >`+title+`</p>
          </div>
          <div class="modal-text01">${parsedData.str}</div>
          <div class="modal-buttons01">
              <button class="close-btn01" onclick="removeModal('`+parsedData.strYn+`')">확인</button>
          </div>
      </div>
  `;
  document.body.appendChild(modal);
}

function removeModal(USE_YN) {
  const modal = document.querySelector('.modal-background01');
  if (modal) {
      document.body.removeChild(modal);
      if("Y" == USE_YN) location.href = location.href;
  }
}


/*모달창 호출 */




/* PDF 다운로드 용 */
function fn_PdfDownload() {
  alert();
  
}
/* PDF 다운로드 용 */

function fn_test(){
  alert();
}