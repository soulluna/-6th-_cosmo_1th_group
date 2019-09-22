//오늘 날짜(-)
function toDayInput() {
  var year = new Date();
  var month = new Date();
  var day = new Date();
  var syear = String(year.getFullYear());
  var smonth = String(month.getMonth() + 1);
  var sday = String(day.getDate());
  if (smonth < 10) {
    smonth = "0" + smonth;
  }
  if (sday < 10) {
    sday = "0" + sday;
  }
  return syear + "-" + smonth + "-" + sday;
}

//문서 수정
function docModify() {
  if (confirm("수정하시겠습니까?") == true) {
    location.href = './vacationModify.html';
  } else {
    return false;
  }
}

//문서 삭제
function docDelete() {
  if (confirm("정말 삭제하시겠습니까?") == true) {
    //document.removefrm.submit();
    location.href = './doclist.html';
  } else {
    return false;
  }
}
//문서 승인
function docApprov() {
  if (confirm("승인하시겠습니까?") == true) {
    location.href = './doclist.html';
  } else {
    return false;
  }
}
//문서 반려
function docReturn() {
  if (confirm("반려하시겠습니까?") == true) {
    location.href = './doclist.html';
  } else {
    return false;
  }
}
//문서 취소
function docCancle() {
  if (confirm("문서함으로 넘어가겠습니까?") == true) {
    location.href = './doclist.html';
  } else {
    return false;
  }
}

var draftInputValue = new Array();
var vacationInputValue = new Array();
var draftModify = new Array();

//draft.html 입력값 확인 및 문서 등록
function draftCheck() {
  draftInputValue[0] = $("input[name=title]").val();
  draftInputValue[1] = $("textarea[name=reason]").val();

  if (!draftInputValue[0]) {
    alert("제목을 입력해주세요.");
  } else if (!draftInputValue[1]) {
    alert("내용을 입력해주세요.")
  } else {
    if (confirm("등록하시겠습니까?") == true) {
      for (i = 0; i <= 1; i++) {
        console.log(draftInputValue[i]);
      }
      location.href = './createdDoc.html';
    } else {
      return false;
    }
  } // else
} //function draftCheck()

//내 문서함 검색 alert()
function docListSearchCheck() {
  var value = $("input[name=searchKey]").val();
  if (value == "") {
    alert("검색어를 입력해주세요.");
  }
}

//vacation.html 입력값 확인 및 문서 등록
function vacationCheck() {
  vacationInputValue[0] = $("input[name=title]").val();
  vacationInputValue[1] = $(':radio[name="leaveradio"]:checked').val();
  vacationInputValue[2] = $("#datepicker1").val();
  vacationInputValue[3] = $("#datepicker2").val();
  vacationInputValue[4] = $("textarea[name=reason]").val();
  vacationInputValue[5] = parseInt($(".dayCount").text());

  if (!vacationInputValue[0]) {
    alert("제목을 입력해주세요.");
  } else if (!vacationInputValue[2] || !vacationInputValue[3]) {
    alert("날짜를 입력해주세요.");
  } else if (!vacationInputValue[4]) {
    alert("사유를 입력해주세요.");
  } else if (vacationInputValue[5] < 1) {
    alert("날짜를 올바르게 선택하세요.");
  } else {
    for (i = 0; i <= 5; i++) {
      console.log(vacationInputValue[i]);
    }
  } // else
} // function vacationCheck()

// 날짜 차이 계산 함수
// date1 : 기준 날짜(YYYY-MM-DD), date2 : 대상 날짜(YYYY-MM-DD)
function getDateDiff(date1, date2) {
  var arrDate1 = date1.split("-");
  var getDate1 = new Date(parseInt(arrDate1[0]), parseInt(arrDate1[1]) - 1, parseInt(arrDate1[2]));
  var arrDate2 = date2.split("-");
  var getDate2 = new Date(parseInt(arrDate2[0]), parseInt(arrDate2[1]) - 1, parseInt(arrDate2[2]));
  var getDiffTime = getDate1.getTime() - getDate2.getTime();
  return Math.floor(getDiffTime / (1000 * 60 * 60 * 24));
} // function getDateDiff

$(document).ready(function () {

  //문서 작성 버튼 클릭 시
  $("button[name=docCreate").on("click", function () {
    location.href = "./draft.html";
  });

  // 문서목록 검색 타입 및 검색값 콘솔 확인
  $(".search").on("click", function () {
    var a = $("select[name=searchType] option:selected").val();
    var b = $("input[name=searchKey]").val();
    console.log(a);
    console.log(b);
    location.href = '#';
  })

  //결재&작성 시 오늘 날짜 입력
  $(".toDayInput").text(toDayInput());

  // 달력 한글화
  $.datepicker.setDefaults({
    dateFormat: 'yy-mm-dd',
    prevText: '이전 달',
    nextText: '다음 달',
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    showMonthAfterYear: true,
    yearSuffix: '년'
  });

  $('#datepicker1').datepicker({
    dateFormat: "yy-mm-dd",

  });
  $('#datepicker2').datepicker({
    dateFormat: "yy-mm-dd",
    // onClose: function( selectedDate ) {
    //     $("#datepicker1").datepicker( "option", "maxDate", selectedDate );
    // }                
  });




});