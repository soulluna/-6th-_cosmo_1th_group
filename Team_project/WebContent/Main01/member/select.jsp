<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;utf-8");
%>
<c:if test="${empty loginUser}">
	<jsp:forward page="${contextPath}/index.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>정보수정</title>
<script src="${contextPath}/Main01/member/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/Main01/member/js/jquery-ui.min.js"></script>
<script src="${contextPath}/Main01/member/js/jquery.easing.1.3.js"></script>
<script src="${contextPath}/Main01/member/js/prefixfree.min.js"></script>
<link rel="stylesheet" href="${contextPath}/Main01/css/gnb.css" />
<link rel="stylesheet" href="${contextPath}/Main01/member/css/select.css" />
</head>
<body>
	<form>
		<!-- fullWrap -->
		<div class="fullWrap">
		<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false" />
			<div class="section_menu">정보수정</div>
			<!-- section_wrap -->
			<div id="section_wrap">
				<div id="section_block1" class="block">
					<img src="${contextPath}/Main01/member/img/pwd.png" width="150" height="150"> 
						<input type="button" class="infobtn" value="비밀번호 수정" onclick="location.href='${contextPath}/Main/pwdChangeForm.do'">
				</div>
				<div id="section_block2" class="block">
					<img src="${contextPath}/Main01/member/img/user.png" width="150" height="100"> 
					<input type="button" class="infobtn" value="내정보 수정" onclick="location.href='${contextPath}/Main/userInfoChangeForm.do'">
				</div>
			</div>
			<!-- section_wrap -->
		</div>
		<!-- //fullWrap -->
	</form>
</body>
</html>