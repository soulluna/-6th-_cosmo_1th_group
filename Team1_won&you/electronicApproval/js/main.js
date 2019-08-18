$(document).ready(function(){

  //문서 등록 시 콘솔 확인
$(".register").on("click", function(){
    
    var a = $("input[name=title]").val();
    var b = $(':radio[name="leaveradio"]:checked').val();
    var c = $("textarea[name=reason]").val();
    var date1 = $("#datepicker1").val();
    var date2 = $("#datepicker2").val();
    
    console.log(a);
    console.log(b);
    console.log(date1);
    console.log(date2);
    console.log(c);
})

// 문서목록 검색 타입 및 검색값 콘솔 확인
$(".search").on("click", function(){
    
  var a = $("select[name=searchType] option:selected").val();
  var b = $("input[name=searchKey]").val();
  
  
  console.log(a);
  console.log(b);

  location.href='#';
})


$("button[name=docCreate").on("click", function(){
    location.href="./draft.html";

});



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

  $(function() {
    $("#datepicker1, #datepicker2").datepicker();
  });

});