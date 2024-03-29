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
<title>일정 보기</title>
<script src="${contextPath}/Main01/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/Main01/js/jquery-ui.min.js"></script>
<script src="${contextPath}/Main01/js/jquery.easing.1.3.js"></script>
<script src="${contextPath}/Main01/js/prefixfree.min.js"></script>
<link rel="stylesheet" href="${contextPath}/Main01/registration/css/reg.css"/>
<link rel="stylesheet" href="${contextPath}/Main01/Schduler/css/schduler.css"/>
<script src="${contextPath}/Main01/Schduler/js/main.js"></script>
</head>
<body>
	<form name="frm" class="reg_form">
		<div>
			<img src="${contextPath}/Main01/registration/img/logo3.gif">
		</div>
		<div class="secb">
			<span class="name">일정명</span>
			<input class="txtb" type="text" name="schname" value="${schVO.schname}" readonly>
		</div>
		<div class="secb">
			<span class="name">일정내용</span>
			<input class="txtb" type="text" name="schcont" value="${schVO.schcont}" readonly>
		</div>
		<div class="secb">
			<span class="name">시작시간</span>
			<input class="txtb" readonly><fmt:formatDate value="${schVO.startDate}" pattern="yyyy-MM-dd hh:mm" />
		</div>
		<div class="secb">
			<span class="name">종료시간</span>
			<input class="txtb" readonly><fmt:formatDate value="${schVO.endDate}" pattern="yyyy-MM-dd hh:mm" />
		</div>
		<input type="hidden" name="schnum" value="${schVO.schnum}">
		<input type="button" class="reg_submit1" value="삭제하기" onclick="location.href='${contextPath}/Main/schdulDelete.do?schnum=${schVO.schnum}'">
		<input type="button" class="reg_submit2" value="수정하기" onclick="location.href='${contextPath}/Main/schdulUpdateForm.do?schnum=${schVO.schnum}'">
		<input type="button" class="cancel" value="닫기" onclick="location.href='${contextPath}/Main/login.do'">
	</form>  
</body>
</html>