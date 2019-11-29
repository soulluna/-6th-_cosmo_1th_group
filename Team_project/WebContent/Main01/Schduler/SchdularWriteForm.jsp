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
<link rel="stylesheet" href="${contextPath}/Main01/css/index.css" />
<script src="${contextPath}/Main01/Schduler/js/main.js"></script>
</head>
<body>
	<form method="post" action="${contextPath}/Main/SchedulWrite.do" name="frm">
		<table>
			<tr>
				<td>일정명</td>
				<td colspan="2"><input type="text" name="schname"></td>
			</tr>
			<tr>
				<td>일정내용</td>
				<td colspan="2"><textarea name="schcont"></textarea></td>
			</tr>
			<tr>
				<td>시작시간</td>
				<td><input type="date" name="startDate"></td>
				<td><input type="time" name="startTime"></td>
			</tr>
			<tr>
				<td>종료시간</td>
				<td><input type="date" name="endDate"></td>
				<td><input type="time" name="endTime"></td>
			</tr>
		</table>
		<input type="submit" value="작성완료" onclick="pageClose();">
		<input type="reset" value="다시 작성">
		<input type="button" value="메인페이지로" onclick="pageClose();">
	</form>
</body>
</html>