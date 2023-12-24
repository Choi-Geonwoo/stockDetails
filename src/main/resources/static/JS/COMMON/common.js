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
document.addEventListener('DOMContentLoaded', () => {
  const excelDownload = document.querySelector('#excelDownload');
  fileName = "거래내역";
  excelDownload.addEventListener('click', exportExcel);
});

function exportExcel(fileName, table_id) {
  const wb = XLSX.utils.book_new();
  const newWorksheet = excelHandler.getWorksheet(table_id);
  XLSX.utils.book_append_sheet(wb, newWorksheet, excelHandler.getSheetName());

  const wbout = XLSX.write(wb, { bookType: 'xlsx', type: 'binary' });
  saveAs(new Blob([s2ab(wbout)], { type: 'application/octet-stream' }), excelHandler.getExcelFileName(fileName));
}

const excelHandler = {
  getExcelFileName: function (fileName) {
    return fileName+toDay()+'.xlsx'; // 파일명
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
    return XLSX.utils.aoa_to_sheet(this.getExcelData(table_id));
  }
};

function s2ab(s) {
  const buf = new ArrayBuffer(s.length); // s를 arrayBuffer로 변환
  const view = new Uint8Array(buf); // uint8array 생성
  for (let i = 0; i < s.length; i++) view[i] = s.charCodeAt(i) & 0xFF; // 옥텟으로 변환
  return buf;
}