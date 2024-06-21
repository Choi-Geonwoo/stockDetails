function sendRowData(button) {
    // ��ư�� ���� ��(row)�� ã���ϴ�.
    const row = button.closest('tr');
    const cells = row.getElementsByTagName('td');
    const inputs = row.getElementsByTagName('input');
    //const data = {};
    const dataAry = {};
    const values = [];
    for (let i = 0; i < cells.length - 1; i++) { // ������ ���� ��ư�̹Ƿ� �����մϴ�.
        //data['column' + i] = cells[i].innerText;
        //values.push(inputs[i].value);
        if (inputs[i].type === 'text') {
            dataAry['value' + i] = inputs[i].value;
        } else if (inputs[i].type === 'checkbox') {
            dataAry['value' + i] = inputs[i].checked ? "Y" : "N";
        }
    }
    // ������ �����͸� �����մϴ�.
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