function sendRowData(button) {
    // 버튼이 속한 행(row)을 찾습니다.
    const row = button.closest('tr');
    const cells = row.getElementsByTagName('td');
    const data = {};
    for (let i = 0; i < cells.length - 1; i++) { // 마지막 셀은 버튼이므로 제외합니다.
        data['column' + i] = cells[i].innerText;
    }

    // 서버로 데이터를 전송합니다.
    fetch('/com/comonCodeUpdate.do', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(result => {
        console.log('Success:', result);
        alert('Row updated successfully!');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to update row.');
    });
}