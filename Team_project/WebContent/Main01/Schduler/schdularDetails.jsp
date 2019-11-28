<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%
   request.setCharacterEncoding("UTF-8");
   response.setContentType("text/html;utf-8");
%>
<c:if test="${empty loginUser}">
	<jsp:forward page="${contextPath}/index.jsp"/>
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
    <link rel="stylesheet" href="${contextPath}/Main01/css/calander.css"/>
    <script src="${contextPath}/Main01/js/calander.js"></script>
</head>
<body>
    <form class="fullWrap">
        <jsp:include page="/WEB-INF/GNB/header.jsp" flush="false"/>
		<table>
			<tr>
				<td>
			</tr>
		</table>
    </form>
</body>
</html>