<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
   request.setCharacterEncoding("UTF-8");
   response.setContentType("text/html;utf-8");
%>

<c:if test='${result!=null}'>
	<script>
		window.onload = function(){
			alert("아이디 혹은 비밀번호가 잘못되었습니다. 다시 입력해주세요");
		}
	</script>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>::Login Page::</title>
<link rel="stylesheet" href="${contextPath}/css/login.css">
<script src="${contextPath}/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/js/jquery-ui.min.js"></script>
<script src="${contextPath}/js/jquery.easing.1.3.js"></script>
<script src="${contextPath}/js/prefixfree.min.js"></script>
<script src="${contextPath}/js/main.js"></script>

</head>
<body>
	<!-- action="Team2_kim/indexMain.html" -->
	<form class="login-form" action="login.do" method="post">
		<div>
			<img src="${contextPath}/img/logo3.gif">
		</div>
		<div class="txtb">
			<input type="text" id="userID" name="eno" value="${employee.eno}">
			<span data-placeholder="사원번호"></span>
		</div>
		<div class="txtb">
			<input type="password" id="pwd" name="pwd" value="${employee.userpw}"> 
			<span data-placeholder="비밀번호"></span>
		</div>
		<input type="submit" class="lgnbtn" value="Login" id="login_submit" onclick="return validate()">
		<div class="bottom-text">
			아이디가 없을 경우 <a href="join.do">회원가입</a>을 해주세요.
		</div>
	</form>
	<script type="text/javascript">
        $(".txtb input").on("focus", function () {
            $(this).addClass("focus");
        });
        $(".txtb input").on("blur", function () {
            if ($(this).val()=="")
                $(this).removeClass("focus");
            return false;
        });
	</script>
</body>
</html>