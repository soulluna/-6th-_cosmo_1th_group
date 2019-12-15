<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;utf-8");
%>
<c:if test='${result==1}'>
	<script>
		window.onload = function(){
			alert("비밀번호가 잘못되었습니다. 다시 입력해주세요");
		}
	</script>
</c:if>
<c:if test="${empty loginUser}">
	<jsp:forward page="${contextPath}/index.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>정보수정 확인</title>
	<script src="${contextPath}/Main01/member/js/jquery-2.1.1.min.js"></script>
	<script src="${contextPath}/Main01/member/js/jquery-ui.min.js"></script>
	<script src="${contextPath}/Main01/member/js/jquery.easing.1.3.js"></script>
	<script src="${contextPath}/Main01/member/js/prefixfree.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/Main01/member/css/confirm.css"/>
</head>
<body>
	<form class="reg_form" action="${contextPath}/Main/pwdConfirm.do" onsubmit="return pwdConfirm();" method="post">
		<div>
			<img src="${contextPath}/img/logo3.gif" width="360" height="250">
		</div>
		<span class="name" data-placeholder="비밀번호"></span>
		<div id="alert">본인확인을 위해 비밀번호를 입력하세요.</div>
		<div class="secb">
			<input class="txtb" type="password" id="pwd" name="pwd" value="${pwd}"> 
				<span class="name" data-placeholder="비밀번호 확인"></span>
		</div>
		<input type="hidden" id="eno" name="eno" value="${loginUser.eno}">
		<input class="reg_submit" type="submit" value="확인">
		<input class="cancel" type="button" value="취소" onclick="location.href='${contextPath}/Main/login.do'">
	</form>
	<script type="text/javascript">
		$(".secb .txtb").on("focus", function() {
			$(this).addClass("focus");
		});

		$(".secb .txtb").on("blur", function() {
			if ($(this).val() == "")
				$(this).removeClass("focus");
		});
		function pwdConfirm() {
			if (pwd.value == "") {
				alert("비밀번호를 확인해 주세요");
				pwd.focus();
				return false;
			}
		}
	</script>
</body>
</html>