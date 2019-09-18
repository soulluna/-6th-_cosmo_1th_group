
  //문서 삭제
  function docDelete() {
    if (confirm("정말 삭제하시겠습니까??") == true) {
      //document.removefrm.submit();
    } else {
      return false;
    }
  }

  
  var draftInputValue = new Array();
  var vacationInputValue = new Array();
  var draftModify = new Array();



  //draft.html 입력값 확인
  function draftCheck() {
    draftInputValue[0] = $("input[name=title]").val();
    draftInputValue[1] = $("textarea[name=reason]").val();

    if (!draftInputValue[0]) {
      alert("제목을 입력해주세요.");
    } else if (!draftInputValue[1]) {
      alert("내용을 입력해주세요.")
    } else {
      for (i = 0; i <= 1; i++) {
        console.log(draftInputValue[i]);
      }
    } // else
  } //function draftCheck()

  
  //vacation.html 입력값 확인
  function vacationCheck() {
    vacationInputValue[0] = $("input[name=title]").val();
    vacationInputValue[1] = $(':radio[name="leaveradio"]:checked').val();
    vacationInputValue[2] = $("#datepicker1").val();
    vacationInputValue[3] = $("#datepicker2").val();
    vacationInputValue[4] = $("textarea[name=reason]").val();

    if (!vacationInputValue[0]) {
      alert("제목을 입력해주세요.");
    } else if (!vacationInputValue[2] || !vacationInputValue[3]) {
      alert("날짜를 입력해주세요.")
    } else if (!vacationInputValue[4]) {
      alert("사유를 입력해주세요.")
    } else {
      for (i = 0; i <= 4; i++) {
        console.log(vacationInputValue[i]);
      }
    } // else
  } // function vacationCheck()
  



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


  // 달력
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

  $(function () {
    $("#datepicker1, #datepicker2").datepicker();
  });

});