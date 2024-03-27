document.addEventListener('DOMContentLoaded', function() {
    fetch("/transactionList.do",
        {
            method : "post",
            headers : {
                "Content-Type" : "application/json",
            },
            body : JSON.stringify({})
        })
        .then((response) => {
            return response.json(); 
        })
        .then(data => { 
            // 데이터를 변수에 저장
            const responseData = data;
            // responseData 변수를 이용하여 원하는 작업 수행
            calendarFun(responseData);
        })
        .catch((error) => {
            alert("error " + error)
        }); 
  });


  function calendarFun(responseData){
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'dayGridMonth',
      selectable: true,
      selectMirror: true,
      locale: 'ko', // 한국어 설정
      events: responseData, 
      dateClick: function(info) { 
        // 날짜 클릭 시 발생할 이벤트
        alert(info.dateStr + " | " + info.event);
      },
      eventClick: function(info) {
        // 일정 클릭 시 발생할 이벤트
        //alert(dateFormat(info.event.start) + " | " + info.event.title);
        // showDialog(info.event.title,dateFormat(info.event.start));
      },
      datesSet: function(info) {
        // 달력이 이동될 때 발생하는 이벤트
        var currentMonth = info.view.currentStart;
        //console.log('현재 월:', dateFormat(currentMonth));
        // 월 변경 후 작업 추가 가능
        // 예: 데이터 다시 불러오기, API 호출 등

        fetch("/transactionList.do",
        {
            method : "post",
            headers : {
                "Content-Type" : "application/json",
            },
            body : JSON.stringify({ YMD :dateFormat(currentMonth) })
        })
        .then(response => response.json())
        .then(newData => {
          // 새로운 데이터로 이벤트 업데이트
          calendar.setOption('events', newData);
          console.log('결과 : ' + JSON.stringify(newData));
          add_menu(newData);
        })
        .catch(error => {
          console.error('데이터를 불러오는 중 에러가 발생했습니다:', error);
      });
      }
    });
    calendar.render();
  }



// 날짜 변환
function dateFormat(date) {
        let dateFormat2 = date.getFullYear() +
    '-' + ( (date.getMonth()+1) < 9 ? "0" + (date.getMonth()+1) : (date.getMonth()+1) )+
    '-' + ( (date.getDate()) < 9 ? "0" + (date.getDate()) : (date.getDate()) );
  return dateFormat2;
}


function add_menu(json){
      // 테이블 이름 정의 
      const table = document.getElementById('tbody_00');
      var sum = 0;

      // 테이블의 기존 내용을 모두 지움
      while (table.firstChild) {
          table.removeChild(table.firstChild);
      }

      const parsedData = json;
      for (let i = 0; i < parsedData.length; i++) {
          const currentItem = parsedData[i];
          //console.log(`Item ${i + 1}:`);
          // 새 행(Row) 추가
          const newRow = table.insertRow();
          for (const key in currentItem) {
              if (Object.hasOwnProperty.call(currentItem, key)) {
                  console.log(`${key}: ${currentItem[key]}`);
                  newRow.insertCell(key).innerText = currentItem[key];
                  //if("amount" == key) sum += parseFloat(currentItem[key]);
              }
          }
          console.log('---');
      }
      
      document.querySelector('#sumTotal').innerHTML = sum;
}