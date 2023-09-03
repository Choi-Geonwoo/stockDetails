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
      showDialog(info.event.title,dateFormat(info.event.start));
      }
    });
    calendar.render();
  }
/*

var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'dayGridMonth',
      selectable: true,
      selectMirror: true,
      locale: 'ko', // 한국어 설정
      events: [
            {
                  title : "All Day Event"
                , start : "2023-08-01"
            },
            
            {
                  title : "Day Event"
                , start : "2023-07-20"
            }
        ], 
       dateClick: function(info) { 
       //날짜 클릭 시 발생할 이벤트
      alert(info.dateStr + " | " + info.event);
      },
      eventClick: function(info) {
      // 일정 클릭 시 발생할 이벤트
      alert(dateFormat(info.event.start))
      }
    });
    calendar.render();
*/

const showDialog = (title, start) => {

    const _html = 
`
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">배당 등록</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
            <div class="mb-3 row">
              <label  class="col-sm-3 col-form-label">거래 일자</label>
              <div class="col-sm-8">
                <input type="date" class="form-control" id="inputTrnscdate">
              </div>
            </div>
            <div class="mb-3 row">
              <label  class="col-sm-3 col-form-label">거래 금액</label>
              <div class="col-sm-8">
                <input type="text" class="form-control" id="inputAmount">
              </div>
            </div>
      </div>
      <div class="modal-footer">
        <button type="button" id="cancel" class="btn btn-outline-dark" data-bs-dismiss="modal">취소</button>
        <button type="button" id="ok" class="btn btn-outline-success" onclick="transactionInsert();">저장</button>
      </div>
    </div>
  </div>
`

    const dom = new DOMParser().parseFromString(_html, 'text/html');
    const dialog = dom.querySelector(".modal");
    document.body.appendChild(dialog)

    dialog.querySelector("#cancel").addEventListener("click", () => {
        document.body.removeChild(dialog)
    })

    dialog.querySelector("#ok").addEventListener("click", () => {
        // 로직 처리
        //alert("OK");
        document.body.removeChild(dialog)
    })
}

// 날짜 변환
function dateFormat(date) {
        let dateFormat2 = date.getFullYear() +
    '-' + ( (date.getMonth()+1) < 9 ? "0" + (date.getMonth()+1) : (date.getMonth()+1) )+
    '-' + ( (date.getDate()) < 9 ? "0" + (date.getDate()) : (date.getDate()) );
  return dateFormat2;
}