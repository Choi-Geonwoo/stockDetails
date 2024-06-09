function sendRowData(button) {
    // ��ư�� ���� ��(row)�� ã���ϴ�.
    const row = button.closest('tr');
    const cells = row.getElementsByTagName('td');
    const data = {};
    for (let i = 0; i < cells.length - 1; i++) { // ������ ���� ��ư�̹Ƿ� �����մϴ�.
        data['column' + i] = cells[i].innerText;
    }

    // ������ �����͸� �����մϴ�.
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