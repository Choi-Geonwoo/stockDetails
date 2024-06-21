function sendRowData(button) {
    // 버튼이 속한 행(row)을 찾습니다.
    const row = button.closest('tr');
    const cells = row.getElementsByTagName('td');
    const inputs = row.getElementsByTagName('input');
    //const data = {};
    const dataAry = {};
    const values = [];
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
}