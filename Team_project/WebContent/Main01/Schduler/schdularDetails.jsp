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
<link rel="stylesheet" href="${contextPath}/Main01/css/index.css" />
<script src="${contextPath}/Main01/Schduler/js/main.js"></script>
</head>
<body>
	<form name="frm">
		<table>
			<tr>
				<td>일정명</td>
				<td>${schVO.schname}</td>
			</tr>
			<tr>
				<td>일정내용</td>
				<td><textarea readonly cols="40" rows="10">${schVO.schcont}</textarea></td>
			</tr>
			<tr>
				<td>시작시간</td>
				<td><fmt:formatDate value="${schVO.startDate}" pattern="yyyy-mm-dd HH:MM"/></td>
			</tr>
			<tr>
				<td>종료시간</td>
				<td><fmt:formatDate value="${schVO.endDate}" pattern="yyyy-mm-dd HH:MM"/></td>
			</tr>
		</table>
		<input type="hidden" name="schnum" value="${schVO.schnum}">
		<input type="button" value="삭제하기" onclick="location.href='${contextPath}/Main/SchdulDelete.do?schnum=${schVO.schnum}'">
		<input type="button" value="닫기" onclick="pageClose();">
	</form>  
</body>
</html>