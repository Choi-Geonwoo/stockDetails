
window.onload = function(){
    addRow(tableId)
    
}

function sendRowData(button) {
    // 버튼이 속한 행(row)을 찾습니다.
    const row = button.closest('tr');
    const cells = row.getElementsByTagName('td');
    const inputs = row.getElementsByTagName('input');
    //const data = {};
    const dataAry = {};
    for (let i = 0; i < cells.length - 1; i++) { // 마지막 셀은 버튼이므로 제외합니다.
        //data['column' + i] = cells[i].innerText;
        //values.push(inputs[i].value);
        if (inputs[i].type === 'text') {
            dataAry['value' + i] = inputs[i].value;
        } else if (inputs[i].type === 'checkbox') {
            dataAry['value' + i] = inputs[i].checked ? "Y" : "N";
        }
    }
    // 서버로 데이터를 전송합니다.
    fetch('/com/comonCodeUpdate.do', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dataAry)
    })
    .then(response => response.json())
    .then(result => {
        console.log('Success:', JSON.stringify(result));
        //alert(JSON.stringify(result));
        alert(result.str);
        location.href = location.href;
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to update row.');
    });
};






function tableCheck(button, tableId) { // 버튼이 속한 행(row)을 찾습니다.
    const row = button.closest('tr');
    const cells = row.getElementsByTagName('td');
    const inputs = row.getElementsByTagName('input');
    const dataAry = {};
    const values = [];
    for (let i = 0; i < cells.length - 1; i++) { // 마지막 셀은 버튼이므로 제외합니다.
        //data['column' + i] = cells[i].innerText;
        //values.push(inputs[i].value);
        if (inputs[i].type === 'text') {
            dataAry['value' + i] = inputs[i].value;
        } else if (0 != i && (inputs[i].type === 'checkbox')) {
            dataAry['value' + i] = inputs[i].checked ? "Y" : "N";
        }
    }

    var table = document.querySelector(tableId);
  
    // 테이블의 모든 행을 제거
    while (table.rows.length > 0) {
      table.deleteRow(2);
    }
    alert(JSON.stringify(dataAry));
}





//  테이블 행추가
function addRow(tableId) {
    //var table = document.getElementById(tableId);
    var table = document.querySelector(tableId);
    //alert(table);
    var rowCount = table.rows.length;
  
    var row = table.insertRow(rowCount);
    var cellCount = 6; // Number of cells to create
  
    for (var i = 0; i < cellCount; i++) {
        var cell = row.insertCell(i);
        var cellContent;
  
        if (i === 0) {
            cellContent = document.createElement("p");
            cellContent.textContent = rowCount;
        } else if (i >= 1 && i <= 3) {
            cellContent = document.createElement("input");
            cellContent.type = "text";
            cellContent.className = "form-control";
  
            if (i === 1) {
                cellContent.id = "SECTION_CD_" + rowCount;
                cellContent.name = "SECTION_CD_" + rowCount;
                cellContent.placeholder = "대분류 코드";
            } else if (i === 2) {
                cellContent.id = "SECTION_CD_" + rowCount;
                cellContent.name = "SECTION_CD_" + rowCount;
                cellContent.placeholder = "중분류 코드";
            } else if (i === 3) {
                cellContent.id = "SECTION_NM_" + rowCount;
                cellContent.name = "SECTION_NM_" + rowCount;
                cellContent.placeholder = "중분류 명";
            }
        } else if (i === 4) {
            cellContent = document.createElement("div");
            cellContent.className = "form-check";
  
            var checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.value = "Y";
            checkbox.name = "USE_YN_" + rowCount;
            checkbox.className = "form-check-input";
  
            var label = document.createElement("label");
            label.className = "form-check-label";
            label.textContent = "사용여부";
  
            cellContent.appendChild(checkbox);
            cellContent.appendChild(label);
        } else if (i === 5) {
            cellContent = document.createElement("button");
            cellContent.type = "button";
            cellContent.className = "btn btn-outline-success";
            cellContent.textContent = "등록";
            cellContent.addEventListener("click", addRow); // Add listener for dynamically added rows
        }
  
        cell.appendChild(cellContent);
    }
  }