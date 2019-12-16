<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;utf-8");
%>
<c:if test="${empty loginUser}">
	<jsp:forward page="${contextPath}/index.jsp" />
</c:if>
<!-- contextPath = /Team_project -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>index</title>
<script src="${contextPath}/Main01/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/Main01/js/jquery-ui.min.js"></script>
<script src="${contextPath}/Main01/js/jquery.easing.1.3.js"></script>
<script src="${contextPath}/Main01/js/prefixfree.min.js"></script>
<link rel="stylesheet" href="${contextPath}/Main01/css/index.css" />
<link rel="stylesheet" href="${contextPath}/Main01/css/gnb.css" />
<link rel="stylesheet" href="${contextPath}/Main01/css/calander.css" />
<script src="${contextPath}/Main01/js/main.js"></script>
<script>
var today = new Date();
var year = null;
var month = null;
var firstDay = null;
var lastDay = null;
var $tdDay = null;
var $tdSche = null;
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
	console.log(year);
	console.log(month);
	firstDay = new Date(year, month - 1, 1);
	lastDay = new Date(year, month, 0);
	drawDays();
	getSchdule();
}
</script>
<script>
	function getDayScadul(year,month,day){
		day+=1;
		if(month<10){
			month="0"+month;
		}
		if(day<10){
			day="0"+day;
		}
		var toString=year+"-"+month+"-"+day;
		location.href='${contextPath}/Main/login.do?date='+toString;
	}
</script>
</head>
<body>
	<form class="fullWrap">
		<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false" />
		<!--left_side-->
		<div id="l_section">
			<div class="section_menu">
				달력
				<div class="cal_top">
					<span id="prevMonth" class="cal_tit"><a id="movePrevMonth"
						onclick="movePrevMonth();">Prev</a></span> <span id="cal_top_year"></span>
					<span id="cal_top_month"></span> <span id="nextMonth"
						class="cal_tit"><a id="moveNextMonth"
						onclick="moveNextMonth(); getSchdule();">Next</a></span>
				</div>
				<div id="cal_tab" class="cal">
					<script>
						drawCalendar();            
						initDate();
						drawDays();
						getSchdule();
					</script>
				</div>
				<c:forEach items="${startDate}" var="dates">
					<c:if test="${!empty dates}">
					<script>
						function getSchdule(){
							console.log("구분자2");
							for(var j=1;j<31;j++){
								$tdSche.eq(i-1).css("background-color","#f1f1f1");
							}
							for(var i=1;i<=31;i++){
								var compare=year-month-i;
								if(${dates}==compare){
									$tdSche.eq(i-1).css("background-color","#3498db");
									console.log("성공값 : "+(i-1));
								}
							}
							console.log("구분자");
						}
						getSchdule();
					</script>
					</c:if>
				</c:forEach>
			</div>
			<div class="scadule">
			<h2 class="bold">스캐쥴러</h2>
				<table class="ListTable" border="1">	
					<tr>
						<th>일정</th>
						<th>시작시간</th>
						<th>종료시간</th>
					</tr>
					<c:if test="${scadulList.size()==0}">
					<tr>
						<td colspan="3" style="align : center;">스캐줄이 없습니다.</td>
					</tr>
					</c:if>
					<c:forEach items="${scadulList}" var="schdul">
						<tr>
							<td><a href="#"
								onclick="location.href='${contextPath}/Main/schdulDetail.do?schnum=${schdul.schnum}'">${schdul.schname}</a></td>
							<td><fmt:formatDate value="${schdul.startDate}"
									pattern="yyyy-MM-dd hh:mm" /></td>
							<td><fmt:formatDate value="${schdul.endDate}"
									pattern="yyyy-MM-dd hh:mm" /></td>
						</tr>
					</c:forEach>
				</table>
				<div id="buttons">
					<input type="button" value="스캐쥴 작성"
						onclick="location.href='${contextPath}/Main/schedulWriteForm.do'">
					<input type="button" value="스캐쥴 전체보기"
						onclick="location.href='${contextPath}/Main/login.do'" style="float : right; margin-right : 20px;">
				</div>
			</div>
		</div>
		<!--//left_side-->
		<!--right_side-->
		<div id="r_section">
			<!-- 나중에 전자결재 데이터베이스 완성되면 그 때viewArticles참조해서 전자결재에서 상위 10개만 읽어오기 -->
			<div class="section_menu">
				<a href="${contextPath}/Approval/docList.do">전자결재 more></a>
			</div>
			<div id="board2">
				<table class='ListTable' border='1'>
					<tr>
						<th width='15%'>결재종류</th>
						<th>문서제목</th>
						<th width='15%'>결재상태</th>
					</tr>
					<c:forEach items="${appList}" var="app">
						<tr>
							<td>${app.applist}</td>
							<c:choose>
								<c:when test="${app.applist=='기안서'}">
									<td class="ali_left"><a
										href="${contextPath}/Approval/draftWait.do?txtnum=${app.txtnum}&pageNum=1">${app.txtname}</a></td>
								</c:when>
								<c:when test="${app.applist=='휴가신청서'}">
									<td class="ali_left"><a
										href="${contextPath}/Approval/vacationWait.do?txtnum=${app.txtnum}&pageNum=1">${app.txtname}</a></td>
								</c:when>
							</c:choose>
							<td>${app.progress}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<!-- 나중에 게시판 데이터베이스 완성되면 그 때viewArticles참조해서 게시판에서 상위 10개만 읽어오기 -->
			<div class="section_menu">
				<a href="${contextPath}/Board/noticeBoardMain.do">게시판 more></a>
			</div>
			<div id="board3">
				<table class='ListTable' border='1'>
					<tr>
						<th width='15%'>게시판종류</th>
						<th>문서제목</th>
						<th width='15%'>작성자</th>
					</tr>
					<c:forEach items="${boardList}" var="board">
						<tr>
							<c:choose>
								<c:when test="${board.noticelist=='1' }">
									<td>부서</td>
								</c:when>
								<c:when test="${board.noticelist=='2' }">
									<td>부서</td>
								</c:when>
								<c:when test="${board.noticelist=='3' }">
									<td>부서</td>
								</c:when>
							</c:choose>
							<td class="ali_left"><a href="${contextPath}/Board/details.do?txtnum=${board.txtnum}&pageNum=1">${board.txtname}</a></td>
							<td>${board.ename}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<!--//right_side-->
	</form>
</body>
</html>