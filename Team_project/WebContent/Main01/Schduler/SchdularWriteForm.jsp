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
<title>일정 작성</title>
<script src="${contextPath}/Main01/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/Main01/js/jquery-ui.min.js"></script>
<script src="${contextPath}/Main01/js/jquery.easing.1.3.js"></script>
<script src="${contextPath}/Main01/js/prefixfree.min.js"></script>
<link rel="stylesheet" href="${contextPath}/Main01/registration/css/reg.css" />
<link rel="stylesheet" href="${contextPath}/Approval01/css/jquery-ui.css" />
<script src="${contextPath}/Main01/Schduler/js/main.js"></script>
<script>
function compareDate(){
	var startDate1=document.frm.startDate.value;
	var endDate1=document.frm.endDate.value;
	var startDate = startDate1.split('-');
	var endDate = endDate1.split('-');
	var spd="";
	var spd2="";
	for(var i in startDate){//시작날짜와 종료날짜를 비교하기 위해 숫자로 변환 후 크기비교
		spd+=startDate[i];
		spd2+=endDate[i];
	}
	sla=Number(spd);
	sla2=Number(spd2);
	if(spd>spd2){
		alert("종료날짜는 시작날짜보다 이전일 수 없습니다.");
	}
}
function compareTime(){
	var startTime1=document.frm.startTime.value;
	var endTime1=document.frm.endTime.value;
	var startTime = startTime1.split(':');
	var endTime = endTime1.split(':');
	var spt="";
	var spt2="";
	for(var j in startTime){
		spt+=startTime[j];
		spt2+=endTime[j];
	}
	spt=Number(spt);
	spt2=Number(spt2);
	if(spt>spt2){
		alert("종료시간은 시작시간보다 이전일 수 없습니다.");
	}	
}
function schWrite(url){
	var startDate = document.frm.startDate.value;
	var endDate = document.frm.endDate.value;
	compareDate();
	compareTime();
	if(document.frm.schname.value==""){
		alert("스캐쥴명을 입력하지 않았습니다.");
		document.frm.schname.focus();
	}
	else if(document.frm.schcont.value==""){
		alert("스캐쥴내용을 입력하지 않았습니다.");
		docuemtn.frm.schcont.focus();
	}
	else if(document.frm.startDate.value==""){
		alert("시작날짜를 입력하지 않았습니다.");
		document.frm.startDate.focus();
	}
	else if(document.frm.startTime.value==""){
		alert("시작시간을 입력하지 않았습니다.");
		document.frm.startTime.focus();
	}
	else if(document.frm.endDate.value==""){
		alert("종료날짜를 입력하지 않았습니다.");
		document.frm.endDate.focus();
	}
	else if(document.frm.endTime.value==""){
		alert("종료시간를 입력하지 않았습니다.");
		document.frm.endTime.focus();
	}
	else{
		if(confirm("작성하시겠습니까?")){
			alert("작성 완료되었습니다.");
			document.frm.action=url+"/Main/schedulWrite.do";
			document.frm.submit();
		}
	}
}
</script>
</head>
<body>
	<form name="frm" class="reg_form" method="post">
		<div>
			<img src="${contextPath}/Main01/registration/img/logo3.gif">
		</div>
		<div class="secb">
			<span class="name">일정명</span>
			<input class="txtb" type="text" name="schname" maxlength="30">
		</div>
		<div class="secb">
			<span class="name">일정내용</span>
			<input class="txtb" type="text" name="schcont" maxlength="150">
		</div>
		<div class="secb">
			<span class="name">시작시간</span>
			<input class="dateb" id="datepicker1" type="text" name="startDate">
			<input class="dateb" type="time" name="startTime">
		</div>
		<div class="secb">
			<span class="name">종료시간</span>
			<input class="dateb" id="datepicker2" type="text" name="endDate" onBlur="compareDate();">
			<input class="dateb" type="time" name="endTime" onBlur="compareTime();">
		</div>
		<input type="button" class="reg_submit" value="작성하기" onclick="schWrite('${contextPath}')">
		<input type="button" class="cancel" value="돌아가기" onclick="location.href='${contextPath}/Main/login.do'">
	</form>
</body>
</html>