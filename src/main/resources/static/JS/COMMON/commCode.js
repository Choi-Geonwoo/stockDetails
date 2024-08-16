
window.onload = function(){
    addRow(tableId)
    //addRow01(tableId, cList)
    
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


  

// 신규 함수
function tableCheck001(SECTION_CD, tableId) { // 버튼이 속한 행(row)을 찾습니다.

    //alert(button + " | " + tableId);

    var table = document.querySelector(tableId);
    // 테이블의 모든 행을 제거
    while (table.rows.length-2 > 0) {
      table.deleteRow(2);
    }
    var jsonObj		= new Object();
    var jsonArray 	= new Array();
    
    jsonObj.SECTION_CD  = SECTION_CD;  
    jsonObj = JSON.stringify(jsonObj);
    //String 형태로 파싱한 객체를 다시 json으로 변환
    jsonArray.push(JSON.parse(jsonObj));
    //console.log(JSON.stringify(jsonArray));
    //addRow01('myTable01', null);
    //alert(JSON.stringify(dataAry));
    // Convert the jsonArray to a query string format
    const queryString = new URLSearchParams(jsonArray[0]).toString();
    fetch(`/com/comCodeClsfcSelect.do?${queryString}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(result => {
        console.log('Success:', JSON.stringify(result));
        //alert(JSON.stringify(result));
        addRow02("",result);
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to update row.');
    });
}

function tableCheck002(sectionCd, tableId) {
        // Get the table element
        var table = document.querySelector(tableId);

        // Remove all rows except the header
        while (table.rows.length > 2) {
            table.deleteRow(2);
        }

        // Create query string from the sectionCd parameter
        const queryString = new URLSearchParams({ SECTION_CD: sectionCd }).toString();
        console.log(queryString);

        // Create a form and submit it via GET request
        var form = document.createElement('form');
        form.method = 'GET';
        //form.action = `/com/comCodeClsfcSelect01.do?${queryString}`;
        form.action = '/com/comCodeClsfcSelect01.do';
        
        var input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'SECTION_CD';
        input.value = sectionCd;
        form.appendChild(input);

        //document.body.appendChild(form);

        document.body.appendChild(form);
        console.log(form);
        form.submit();
}

function sendRowData001(button) {
    // 버튼이 속한 행(row)을 찾습니다.
    const row = button.closest('tr');
    const cells = row.getElementsByTagName('td');
    const inputs = row.getElementsByTagName('input');
    //const data = {};
    console.log("1.   " + cells.length);
    const dataAry = {};
    for (let i = 0; i < cells.length; i++) { // 마지막 셀은 버튼이므로 제외합니다.
        //data['column' + i] = cells[i].innerText;
        //values.push(inputs[i].value);
            console.log("1-"+i+".   " + inputs[i].type + "  || " + inputs[i].value);
        if (inputs[i].type === 'text') {
            dataAry['value' + i] = inputs[i].value;
        } else if (inputs[i].type === 'checkbox') {
            dataAry['value' + i] = inputs[i].checked ? "Y" : "N";
        }
        //console.log(i + " | " + inputs[i].value);
    }
    //console.log(JSON.stringify(dataAry));
    // 서버로 데이터를 전송합니다.
    fetch001('/com/comonCodeNewUpdate.do', 'POST', dataAry);
/*
fetch('/com/comonCodeNewUpdate.do', {
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
*/
};





//  테이블 행추가
    function addRow01(tableId, cList) {

    //var table = document.getElementById(tableId);
    var table = document.querySelector(tableId);
    //alert(table);
    var rowCount = table.rows.length;
    //alert(rowCount);
    let allValues = getAllValues().split(",");
    var row = table.insertRow(rowCount);
    var cellCount = 7; // Number of cells to create
  
    for (var i = 0; i < cellCount; i++) {
        var cell = row.insertCell(i);
        var cellContent;

        if (i === 0) {
            cellContent = document.createElement("p");
            cellContent.textContent = rowCount;
        } else
        if (i === 1) {
            cellContent = document.createElement("input");
            cellContent.type = "checkbox";
            cellContent.name = "user_CheckBox";
            cellContent.className = "form-check";
        } else if (i >= 2 && i <= 4) {
            cellContent = document.createElement("input");
            cellContent.type = "text";
            cellContent.className = "form-control";
  
            if (i === 2) {
                cellContent = document.createElement("select");
                cellContent.className = "form-control";
                
                cellContent.id = "CATEGORY_CD_" + rowCount;
                cellContent.name = "CATEGORY_CD_" + rowCount;
                cellContent.setAttribute("data-placeholder", "대분류 코드");
                // Add options for CATEGORY_CD
                for (let index = 0; index < allValues.length; index++) {
                    var option = document.createElement("option");
                    const element = allValues[index];
                    //console.log(element);                        
                    option.value = element;
                    option.textContent = (isEmpty(element) ? '선택' : element);
                    cellContent.appendChild(option);
                }
            } else if (i === 3) {
                cellContent.id = "SECTION_CD_" + rowCount;
                cellContent.name = "SECTION_CD_" + rowCount;
                cellContent.placeholder = "중분류 코드";
            } else if (i === 4) {
                cellContent.id = "SECTION_NM_" + rowCount;
                cellContent.name = "SECTION_NM_" + rowCount;
                cellContent.placeholder = "중분류 명";
            }
        } else if (i === 5) {
            cellContent = document.createElement("div");
            cellContent.className = "form-check";
  
            var checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.value = "Y";
            checkbox.name = "USE_YN";
            checkbox.className = "form-check-input";
  
            var label = document.createElement("label");
            label.className = "form-check-label";
            label.textContent = "사용여부";
  
            cellContent.appendChild(checkbox);
            cellContent.appendChild(label);
        } else if (i === 6) {
            cellContent = document.createElement("button");
            cellContent.type = "button";
            cellContent.className = "btn btn-outline-success";
            cellContent.textContent = "등록";
            cellContent.addEventListener("click", addRow); // Add listener for dynamically added rows
        }
  
        cell.appendChild(cellContent);
    }

    }
    
//  테이블 행추가
function addRow02(tableId, cList) {
    if (cList.length === 0) return;
    
    var table = document.querySelector("#myTable01");
    var rowCount = table.rows.length;
    let allValues = getAllValues().split(",");
    
    // Remove existing rows except the header row
    while (table.rows.length > 1) {
        table.deleteRow(1);
    }
    
    // Loop through each item in cList and add a new row for each
    cList.forEach((item, index) => {
        var row = table.insertRow();
        
        for (var i = 0; i < 7; i++) {
            var cell = row.insertCell(i);
            var cellContent;
            
            if (i === 0) {
                cellContent = document.createElement("p");
                cellContent.textContent = index + 1;
            } else if (i === 1) {
                cellContent = document.createElement("input");
                cellContent.type = "checkbox";
                cellContent.name = "user_CheckBox";
                cellContent.className = "form-check";
            } else if (i >= 2 && i <= 4) {
                if (i === 2) {
                    cellContent = document.createElement("select");
                    cellContent.className = "form-control";
                    cellContent.id = "CATEGORY_CD_" + rowCount;
                    cellContent.name = "CATEGORY_CD_" + rowCount;
                    cellContent.setAttribute("data-placeholder", "대분류 코드");
                    
                    allValues.forEach(value => {
                        var option = document.createElement("option");
                        option.value = value;
                        option.textContent = value || '선택';
                        cellContent.appendChild(option);
                    });
                } else {
                    cellContent = document.createElement("input");
                    cellContent.type = "text";
                    cellContent.className = "form-control";
                    
                    if (i === 3) {
                        cellContent.id = "SECTION_CD_" + rowCount;
                        cellContent.name = "SECTION_CD_" + rowCount;
                        cellContent.placeholder = "중분류 코드";
                        cellContent.value = item.CLSFC_CD;
                    } else if (i === 4) {
                        cellContent.id = "SECTION_NM_" + rowCount;
                        cellContent.name = "SECTION_NM_" + rowCount;
                        cellContent.placeholder = "중분류 명";
                        cellContent.value = item.CLSFC_NM;
                    }
                }
            } else if (i === 5) {
                cellContent = document.createElement("div");
                cellContent.className = "form-check";
                
                var checkbox = document.createElement("input");
                checkbox.type = "checkbox";
                checkbox.value = "Y";
                checkbox.name = "USE_YN";
                checkbox.className = "form-check-input";
                
                var label = document.createElement("label");
                label.className = "form-check-label";
                label.textContent = "사용여부";
                
                cellContent.appendChild(checkbox);
                cellContent.appendChild(label);
            } else if (i === 6) {
                cellContent = document.createElement("button");
                cellContent.type = "button";
                cellContent.className = "btn btn-outline-success";
                cellContent.textContent = "등록";
                cellContent.addEventListener("click", addRow); // Add listener for dynamically added rows
            }
            
            cell.appendChild(cellContent);
        }
    });
}

//select 전체 요소를 가져오기
function getAllValues() {
    // select 요소를 가져옴
    const selectElement = document.getElementById('trnscdateSelect');
    // 전체 옵션을 저장할 배열
    let allValues = [];
    //console.log(selectElement.options.length);
    // 모든 option 요소를 반복
    for (let i = 0; i < selectElement.options.length; i++) {
        allValues.push(selectElement.options[i].value);
        //console.log(selectElement.options[i].value);
    }
    // 결과를 표시할 곳에 출력
    //document.getElementById('result').innerText = '전체 값: ' + allValues.join(', ');
    //console.log(allValues.join(', '));
    return allValues.join(', ');
}

// 중분류코드 등록
function clsfcInsterInsert() {
			var rowData = [];
			var tdArr = [];
			var checkboxes = document.querySelectorAll("input[name=user_CheckBox]:checked");
            var jsonArray 	= new Array();
			//console.log("갯수  " + checkboxes.length);
            if(0 == checkboxes.length){
                alert("선택 후 저장해주세요.");
                return;
            }
			checkboxes.forEach(function(checkbox, i) {
				var tr = checkbox.closest("tr");
				var td = tr.children;
				var jsonObj		= new Object();
				// td[1]에 위치한 select 요소의 선택된 값을 가져온다.
				var selectedOption = td[2].querySelector("select").value;
				
				// td[0]은 체크박스 이므로 td[1]의 값부터 가져온다.
				var CATEGORY_CD = selectedOption;
				var SECTION_CD = td[3].querySelector("input").value;
				var SECTION_NM = td[4].querySelector("input").value;
				var USE_YN = td[5].querySelector("input[name=USE_YN]").checked ? "Y" : "N";
				if(isEmpty(CATEGORY_CD)) {
                    alert("대분류코드 선택 후 저장해주세요. " + CATEGORY_CD); 
                    return;
                }

                if(isEmpty(SECTION_CD)){
                     alert("중분류코드 입력 후 저장해주세요."); 
                     return;
                }

                if(isEmpty(SECTION_NM)){ 
                    alert("중분류코드명 입력 후 저장해주세요."); 
                    return;
                }

				// 가져온 값을 배열에 담는다.
				//tdArr.push("CATEGORY_CD : "+CATEGORY_CD);
				//tdArr.push(SECTION_CD);
				//tdArr.push(SECTION_NM);
				//tdArr.push(USE_YN);
                jsonObj.SECTION_CD  = CATEGORY_CD.replace(" ",""); //대분류코드
                jsonObj.CLSFC_CD    = SECTION_CD.replace(" ",""); //중분류코드
                jsonObj.CLSFC_NM    = SECTION_NM.replace(" ",""); //중분류코드명
                jsonObj.USE_YN      = USE_YN.replace(" ","");     // 사용여부
				//tdArr.push(selectedOption.length);
                
                jsonObj = JSON.stringify(jsonObj);
                //String 형태로 파싱한 객체를 다시 json으로 변환
                jsonArray.push(JSON.parse(jsonObj));
			});
			
            //console.log(JSON.stringify(jsonArray));
			//document.getElementById("ex3_Result1").innerHTML = " * 체크된 Row의 모든 데이터 = " + tdArr.join('');	
			//document.getElementById("ex3_Result2").innerHTML = tdArr.join('');
            // 서버로 데이터를 전송합니다.
            fetch001('/com/comCodeClsfcInster.do', 'POST', jsonArray);	
}