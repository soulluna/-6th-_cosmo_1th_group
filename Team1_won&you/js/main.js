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

//수정 등록 값
function inputValue() {

  console.log($(".inputTitle").val());
  console.log($("input[name=leaveradio]:checked").val());
  console.log($("#datepicker1").val());
  console.log($("#datepicker2").val());
  console.log($(".inputContent").val());
}


//날짜 선택 에러 표기
function pickedDateEr() {
  var toDay;
  var forDay;
  $('#datepicker1').change(function () {
    toDay = $('#datepicker1').val();
    forDay = $('#datepicker2').val();
    var c = getDateDiff(forDay, toDay) + 1;
    if (c) {
      $('.dayCount').text(c);
    }
    if (c < 1) {
      $('.dayCountAlret').css({ 'display': 'inline' });
    } else {
      $('.dayCountAlret').css({ 'display': 'none' });
    }
  }); //datepicker1 변동 시 날짜 변경
  $('#datepicker2').change(function () {
    toDay = $('#datepicker1').val();
    forDay = $('#datepicker2').val();
    var c = getDateDiff(forDay, toDay) + 1;
    if (c) {
      $('.dayCount').text(c);
    }
    if (c < 1) {
      $('.dayCountAlret').css({ 'display': 'inline' });
    } else {
      $('.dayCountAlret').css({ 'display': 'none' });
    }
  }); //datepicker2 변동 시 날짜 변경
}

//문서 이름 구하기
function docName() {
  return document.URL.substring(document.URL.lastIndexOf("/") + 1, document.URL.length);
}
var thisfilefullname;

//문서 수정
function docModify() {
  thisfilefullname = docName();
  console.log(thisfilefullname);
  if (confirm("수정하시겠습니까?") == true) {
    if (thisfilefullname == "createdDraftDoc.html") {
      location.href = '#';
    } else if (thisfilefullname == "vacationWait.html") {
      location.href = './vacationModify.html';
    } else if (thisfilefullname == "draftWait.html") {
      location.href = './draftModify.html';
    }
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
    alert("내용을 입력해주세요.");
  } else {
    if (confirm("등록하시겠습니까?") == true) {
      for (i = 0; i <= 1; i++) {
        console.log(draftInputValue[i]);
      }
      location.href = './createdDraftDoc.html';
    } else {
      return false;
    }
  } // else
} //function draftCheck()

//내 문서함 검색 alert()
function docListSearchCheck() {
  var reg = /^기안$|^기안서$|^휴가$|^휴가\s?신청서$/;
  var searchType = $("select[name=searchType] option:selected").text();
  var inputValue = $("input[name=searchKey]").val();

  if (inputValue == "") {
    alert("검색어를 입력해주세요.");
  } else if (searchType == "결재종류" && reg.test(inputValue) == false) {
    alert("없습니다.");
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
    thisfilefullname = docName();
    console.log(thisfilefullname);
    if (confirm("등록하시겠습니까?") == true) {
      if (thisfilefullname == "vacationModify.html") {
        for (i = 0; i <= 5; i++) {
          console.log(vacationInputValue[i]);
        }
        location.href = './createdVacationModify.html';
      } else if (thisfilefullname == "vacation.html") {
        location.href = './createdVacationDoc.html';
      }

    } else {
      return false;
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

//DB에 저장될 자료 예시
var documentList =
  // 0:문서종류
  // 1:작성자id
  // 2:중간id
  // 3:최종id
  // 4:작성날짜
  // 5:중간결재날짜
  // 6:최종결재날짜
  // 7:진행도(대기(1),진행(2),완료(3))
  // 8:제목
  // 9:휴가신청종류(연차(1),병가(2),휴가(3),기타(4))
  // 10:휴가시작날짜
  // 11:휴가끝날짜
  // 12:내용
  [
    ["DR-0164", "작성자id", "중간id", "최종id", "2019-11-01", "", "", "1", "안영우 사원 기안서", "", "", "", "기반구조 관계도 기술서 데이터구성요소"],
    ["DR-0139", "작성자id", "중간id", "최종id", "2019-07-22", "2019-07-24", "", "2", "2019년 07월 사내문화 안영우 사원 기안서", "", "", "", "저녁 시간은 가족과 보냅시다."],
    ["DR-0116", "작성자id", "중간id", "최종id", "2019-06-30", "2019-07-05", "2019-07-06", "3", "2019년 06월 업무 개선사항 안영우 사원 기안서", "", "", "", "주간 보고 시간 변경 "],
    ["VA-0030", "작성자id", "중간id", "최종id", "2019-11-01", "", "", "1", "안영우 사원 휴가신청서", "1", "2019-11-04", "2019-11-05", "친척 결혼식 참석."]
  ]

var employee = [
  // 0:사번
  // 1:이름
  // 2:직책
  // 3:소속

  ["em-0001", "안영우", "사원", "기술지원팀"],
  ["em-0001", "함종우", "과장", "기술지원팀"],
  ["em-0001", "조현석", "부장", "기술지원팀"],
]


//문서 시작
$(document).ready(function () {

  thisfilefullname = docName();
  console.log(thisfilefullname);

  if (thisfilefullname == "vacationWait.html") {
    $(".signtableleft tr:first td").text(employee[0][1]);
    $(".signtableleft tr:first+tr td").text(employee[0][2]);
    $(".signtableleft tr:first+tr+tr td").text(employee[0][3]);

    $(".createdDayInput1").text(documentList[3][4]);
    $(".createdDayInput2").text(documentList[3][5]);
    $(".createdDayInput3").text(documentList[3][6]);
    $(".approval1").text(employee[0][1]);
    $(".approval2").text(employee[1][1]);
    $(".approval3").text(employee[2][1]);
    $(".inputTitle").text(documentList[3][8]);

    if (documentList[3][9] == 1) {
      $(".inputarea tr:nth-child(2) input[value=1]").attr("checked", "checked");
    } else if (documentList[3][9] == 2) {
      $(".inputarea tr:nth-child(2) input[value=2]").attr("checked", "checked");
    } else if (documentList[3][9] == 3) {
      $(".inputarea tr:nth-child(2) input[value=3]").attr("checked", "checked");
    } else if (documentList[3][9] == 4) {
      $(".inputarea tr:nth-child(2) input[value=4]").attr("checked", "checked");
    }

    $(".selectedDay:first").text(documentList[3][10]);
    $(".selectedDay:last").text(documentList[3][11]);
    $(".selectedDayCount").text(getDateDiff(documentList[3][11], documentList[3][10])+1);
    $(".inputContent").text(documentList[3][12]);

  } else if (thisfilefullname == "draftProgress.html") {
    $(".createdDayInput1").text(documentList[1][4]);
    $(".createdDayInput2").text(documentList[1][5]);
    $(".createdDayInput3").text(documentList[1][6]);
    $(".inputTitle").text(documentList[1][8]);
    $(".inputContent").text(documentList[1][12]);
  } else if (thisfilefullname == "vacationModify.html") {
    var getDay = getDateDiff(documentList[3][11], documentList[3][10]) + 1;
    $(".signtableleft tr:first td").text(employee[0][1]);
    $(".signtableleft tr:first+tr td").text(employee[0][2]);
    $(".signtableleft tr:first+tr+tr td").text(employee[0][3]);
    $(".approval1").text(employee[0][1]);
    $(".approval2").text(employee[1][1]);
    $(".approval3").text(employee[2][1]);

    $('.inputarea .inputTitle').val(documentList[3][8]);

    if (documentList[3][9] == 1) {
      $(".modifySelect1").attr("checked", "checked");
    } else if (documentList[3][9] == 2) {
      $(".modifySelect2").attr("checked", "checked");
    } else if (documentList[3][9] == 3) {
      $(".modifySelect3").attr("checked", "checked");
    } else if (documentList[3][9] == 4) {
      $(".modifySelect4").attr("checked", "checked");
    }
    $('.inputarea #datepicker1').val(documentList[3][10]);
    $('.inputarea #datepicker2').val(documentList[3][11]);
    $('.inputarea .inputContent').val(documentList[3][12]);
    $(".dayCount").text(getDay);
    pickedDateEr();
  } else if (thisfilefullname == "draftApproval.html") {
    $(".createdDayInput1").text(documentList[2][4]);
    $(".createdDayInput2").text(documentList[2][5]);
    $(".createdDayInput3").text(documentList[2][6]);
    $(".inputTitle").text(documentList[2][8]);
    $(".inputContent").text(documentList[2][12]);
  } else if (thisfilefullname == "draft.html") {
    $(".signtableleft tr:first td").text(employee[0][1]);
    $(".signtableleft tr:first+tr td").text(employee[0][2]);
    $(".signtableleft tr:first+tr+tr td").text(employee[0][3]);
    $(".approval1").text(employee[0][1]);
    $(".approval2").text(employee[1][1]);
    $(".approval3").text(employee[2][1]);

  } else if (thisfilefullname == "vacation.html") {
    $(".signtableleft tr:first td").text(employee[0][1]);
    $(".signtableleft tr:first+tr td").text(employee[0][2]);
    $(".signtableleft tr:first+tr+tr td").text(employee[0][3]);
    $(".approval1").text(employee[0][1]);
    $(".approval2").text(employee[1][1]);
    $(".approval3").text(employee[2][1]);
  }else if (thisfilefullname == "draftWait.html") {
    $(".signtableleft tr:first td").text(employee[0][1]);
    $(".signtableleft tr:first+tr td").text(employee[0][2]);
    $(".signtableleft tr:first+tr+tr td").text(employee[0][3]);
    $(".createdDayInput1").text(documentList[0][4]);
    $(".createdDayInput2").text(documentList[0][5]);
    $(".createdDayInput3").text(documentList[0][6]);
    $(".inputTitle").text(documentList[0][8]);
    $(".inputContent").text(documentList[0][12]);
    $(".approval1").text(employee[0][1]);
    $(".approval2").text(employee[1][1]);
    $(".approval3").text(employee[2][1]);
  }else if (thisfilefullname == "draftModify.html") {
    $(".signtableleft tr:first td").text(employee[0][1]);
    $(".signtableleft tr:first+tr td").text(employee[0][2]);
    $(".signtableleft tr:first+tr+tr td").text(employee[0][3]);
    $(".approval1").text(employee[0][1]);
    $(".approval2").text(employee[1][1]);
    $(".approval3").text(employee[2][1]);
    $('.inputarea .inputTitle').val(documentList[0][8]);
    $('.inputarea .inputContent').val(documentList[0][12]);
  }
  


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