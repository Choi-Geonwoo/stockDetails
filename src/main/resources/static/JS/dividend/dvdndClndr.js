document.addEventListener('DOMContentLoaded', function() {
    fetch("/transactionList.do",
        {
            method : "get",
            headers : {
                "Content-Type" : "application/json",
            }
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
       //날짜 클릭 시 발생할 이벤트
      alert(info.dateStr + " | " + info.event);
      },
      eventClick: function(info) {
      // 일정 클릭 시 발생할 이벤트
      alert(dateFormat(info.event.start) + " | " + info.event.title);
      //showDialog(info.event.title,dateFormat(info.event.start));
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