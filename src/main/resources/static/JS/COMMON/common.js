function fu_monthSelect(monthValue){
  // �� ���� ���� ������Ʈ ã��
  var monthSelect = document.getElementById("monthSelect");
  
  // ���� ��¥�� �������� JavaScript ��ü ����
  var currentDate = new Date();
  
  // ���� ���� �������� (0���� �����ϹǷ� 1�� �����ݴϴ�)
  var currentMonth = currentDate.getMonth() + 1;
  
    // �� ���� ���ڿ��� ���� ���� ��ġ�ϴ� �ɼ��� �����մϴ�.
    for (var i = 0; i < monthSelect.options.length; i++) {
      if (monthSelect.options[i].value == monthValue) {
        monthSelect.options[i].selected = true;
        break; // ���ϴ� �ɼ��� ã���� ���� ����
      }else if (monthSelect.options[i].value == currentMonth) {
            monthSelect.options[i].selected = true;
            break; // ���ϴ� �ɼ��� ã���� ���� ����
        }
    }
  }

  
// ��ȸ �⵵ ����Ʈ �ڽ�
function trnscdateSelect(yearmonth){
  const yearSelect = document.getElementById('trnscdateSelect');
  const currentYear = new Date().getFullYear();
  const startYear = 2020; // ���� �⵵
  const endYear = currentYear + 10; // ���� �⵵���� 10�� �ڱ��� ǥ��

  for (let year = startYear; year <= endYear; year++) {
    const option = document.createElement('option');
    option.value = year;
    option.text = year;
    //console.log("year " + year + " yearmonth : " + yearmonth);
    if(year == yearmonth){
      option.selected = true; // ���� �⵵�� �⺻ �������� ����
      yearSelect.value = yearmonth;
      yearSelect.appendChild(option);
    }else{
      yearSelect.appendChild(option);
    }
  }
  // ���� �⵵�� �⺻������ ����
  if(null == yearmonth){
      yearSelect.value = currentYear;
  }
}



function dateChek(date){
  const val = "/./g";
  // ���� ǥ�������� ��� ������ �˻�
  const dotCount = (date.match(/\./g) || [0]).length;
  if((1 != dotCount) ){
    alert("���� �߻�");
    return true;
  }else{
    return false;
  }
}


/**
	 * ���ڿ��� �� ���ڿ����� üũ�Ͽ� ������� �����Ѵ�. 
	 * @param str		: üũ�� ���ڿ�
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
	 * ���� �ٿ�ε� 
	 * @param str		: üũ�� ���ڿ�
 **/
document.addEventListener('DOMContentLoaded', () => {
  const excelDownload = document.querySelector('#excelDownload');
  fileName = "�ŷ�����";
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
    return fileName+toDay()+'.xlsx'; // ���ϸ�
  },
  getSheetName: function () {
    return 'Table Test Sheet'; // ��Ʈ��
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
  const buf = new ArrayBuffer(s.length); // s�� arrayBuffer�� ��ȯ
  const view = new Uint8Array(buf); // uint8array ����
  for (let i = 0; i < s.length; i++) view[i] = s.charCodeAt(i) & 0xFF; // �������� ��ȯ
  return buf;
}

/* ���̺� ���� */
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

