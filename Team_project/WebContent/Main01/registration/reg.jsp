<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
   request.setCharacterEncoding("UTF-8");
   response.setContentType("text/html;utf-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>regist</title>
<script src="${contextPath}/Main01/registration/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/Main01/registration/js/jquery-ui.min.js"></script>
<script src="${contextPath}/Main01/registration/js/jquery.easing.1.3.js"></script>
<script src="${contextPath}/Main01/registration/js/prefixfree.min.js"></script>
<script src="${contextPath}/Main01/registration/js/main.js"></script>
<link rel="stylesheet" href="${contextPath}/Main01/registration/css/reg.css" />
</head>
<body>
	<form class="reg_form" name="join" onsubmit="return validate();" action="${contextPath}/main/addMember.do" method="post">
		<div>
			<img src="${contextPath}/Main01/registration/img/logo3.gif">
		</div>
		<div class="secb">
			<input class="txtb" type="text" id="userID" name="eno" value="${employee.eno}"> 
			<span class="name" data-placeholder="사원번호"></span> 
		</div>
		<div class="secb">
			<input class="txtb" type="text" id="userName" name="ename" value="${employee.ename}"> 
				<span class="name" data-placeholder="이름"></span>
		</div>
		<div class="secb">
			<input id="pwd" class="txtb" type="password" name="pwd" value="${employee.pwd}"> 
			<span class="name"data-placeholder="비밀번호"></span>
		</div>

		<div class="secb">
			<input id="pwdconfirm" class="txtb" type="password" name="pwc">
			<span class="name" data-placeholder="비밀번호 확인"></span>
		</div>
		<div class="a_section">
			<select class="area" id="dep1" name="dname">
				<option value="0">선택하세요</option>
				<option value="1">영업부</option>
				<option value="2">인사부</option>
				<option value="3">기술지원팀</option>
			</select> <span class="block"> &nbsp;>&nbsp;</span> <select class="area2"
				id="dep2" name="dname_two">
				<option value="0">선택하세요</option>
				<option value="4">1팀</option>
				<option value="5">2팀</option>
				<option value="6">3팀</option>
			</select>
		</div>
		<input class="reg_submit" type="submit" value="등록"> 
		<input class="clear" type="reset" value="취소" onclick="location.href ='${contextPath}/index.jsp';">
	</form>
</body>
	<script type="text/javascript">
    	$(".secb .txtb").on("focus", function () {
        	$(this).addClass("focus");
    	});
    	$(".secb .txtb").on("blur", function () {
        	if ($(this).val()=="")
            	$(this).removeClass("focus");
    	});
    </script>
</html>