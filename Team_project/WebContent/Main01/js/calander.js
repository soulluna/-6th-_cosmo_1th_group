var today = new Date();
var year = null;
var month = null;
var firstDay = null;
var lastDay = null;
var $tdDay = null;
var $tdSche = null;

window.onload=function(){
	drawCalendar();            
	initDate();
	drawDays();
}
//calendar 그리기
function drawCalendar() {
	var setTableHTML = "";
	setTableHTML += '<table class="calendar">';
	setTableHTML += '<tr><th>SUN</th><th>MON</th><th>TUE</th><th>WED</th><th>THU</th><th>FRI</th><th>SAT</th></tr>';
	for (var i = 0; i < 6; i++) {
		setTableHTML += '<tr height="50">';
		for (var j = 0; j < 7; j++) {
			setTableHTML += '<td style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap">';
			setTableHTML += '<div class="cal-day"></div>';
			setTableHTML += '<div class="cal-schedule"></div>';
			setTableHTML += '</td>';
		}
		setTableHTML += '</tr>';
	}
	setTableHTML += '</table>';
	$("#cal_tab").html(setTableHTML);
}
//날짜 초기화
function initDate() {
	$tdDay = $("td div.cal-day");
	$tdSche = $("td div.cal-schedule");
	dayCount = 0;
	year = today.getFullYear();
	month = today.getMonth() + 1;
	myDate = today.getDate();
	firstDay = new Date(year, month - 1, 1);
	lastDay = new Date(year, month, 0);
	console.log(myDate);
	console.log(today.getMonth()+1);
}
//calendar 날짜표시
function drawDays() {
	$("#cal_top_year").text(year);
	$("#cal_top_month").text(month);
	for (var i = firstDay.getDay(); i < firstDay.getDay() + lastDay.getDate(); i++) {
		$tdDay.eq(i).html("<a href='javascript:void(0);' onclick='getDayScadul("+year+","+month+","+(dayCount)+");' class='black'>"+(++dayCount)+"</a>");
		$tdDay.eq(i).css("color","black");
		// console.log(dayCount);
		// console.log(month);
		if (myDate == dayCount&&month==today.getMonth()+1&&year==today.getFullYear()) {
			$tdDay.eq(i).parent().css("background-color", "#FAF58C");
		}
		else{
			$tdDay.eq(i).parent().css("background-color", "#f1f1f1");
		}
	}
	for (var i = 0; i < 42; i += 7) {
		$tdDay.eq(i).children().css("color", "red");
	}
	for (var i = 6; i < 42; i += 7) {
		$tdDay.eq(i).children().css("color", "blue");
	}
}
//calendar 월 이동
function movePrevMonth() {
	month--;
	if (month <= 0) {
		month = 12;
		year--;
	}
	if (month < 10) {
		month = String("0" + month);
	}
	getNewInfo();
}
function moveNextMonth() {
	month++;
	if (month > 12) {
		month = 1;
		year++;
	}
	if (month < 10) {
		month = String("0" + month);
	}
	getNewInfo();
}
function getNewInfo() {
	for (var i = 0; i < 42; i++) {
		$tdDay.eq(i).text("");
	}
	dayCount = 0;
	firstDay = new Date(year, month - 1, 1);
	lastDay = new Date(year, month, 0);
	drawDays();
}