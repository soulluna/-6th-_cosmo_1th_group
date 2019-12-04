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
</head>
<body>
	<form name="frm" class="reg_form" action="${contextPath}/Main/schedulUpdate.do?schnum=${schVO.schnum}" method="post">
		<div>
			<img src="${contextPath}/Main01/registration/img/logo3.gif">
		</div>
		<div class="secb">
			<span class="name">일정명</span>
			<input class="txtb" type="text" name="schname" value="${schVO.schname}">
		</div>
		<div class="secb">
			<span class="name">일정내용</span>
			<input class="txtb" type="text" name="schcont" value="${schVO.schcont}">
		</div>
		<div class="secb">
			<span class="name">시작시간</span>
			<input class="dateb" id="datepicker1" type="text" name="startDate" value="${startDate}">
			<input class="dateb" type="time" name="startTime" value="${startTime}">
		</div>
		<div class="secb">
			<span class="name">종료시간</span>
			<input class="dateb" id="datepicker2" type="text" name="endDate" value="${endDate}">
			<input class="dateb" type="time" name="endTime" value="${endTime}">
		</div>
		<input type="hidden" name="schnum" value="${schVO.schnum}">
		<input type="submit" class="reg_submit" value="작성하기">
		<input type="button" class="cancel" value="돌아가기" onclick="location.href='${contextPath}/Main/schdulDetail.do?schnum=${schVO.schnum}'">
	</form>
</body>
</html>