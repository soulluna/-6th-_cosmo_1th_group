<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<!--gnb-->
	<div class="gnb">
		<!--logoBar-->
		<ul class="logobar">
			<li id="mainLogo"><a href="login.do"><img src="./img/logo3.gif"></a></li>
			<table id="memberinfo">
				<tbody>
					<tr>
						<td id="profile_img" rowspan="2"><img src="http://placehold.it/70x70"></td>
						<td colspan="2">${loginUser.ename}님 환영합니다.</td>
					</tr>
					<tr>
						<td>
							<input type="button" value="로그아웃" onclick="location.href='logout.do'"> 
							<input type="button" value="내정보수정" onclick="location.href='pwdConfirm.do?eno=${loginUser.eno}'">
							<!-- <a href="${contextPath}/Main01/member/confirm.jsp">내정보수정</a> -->
						</td>
					</tr>
				</tbody>
			</table>
		</ul>
		<!--//logoBar-->
		<!--nav bar-->
		<ul class="topBar">
			<li id="main" class="t_menu btn3"><a href="login.do">메인</a></li>
			<li id="cal" class="t_menu btn5"><a href="../Team3_cha/noticeBoardMain.html">일정표</a></li>
			<li id="system" class="t_menu btn1"><a href="../Team1_won&you/docList.html">전자결재시스템</a></li>
			<li id="board" class="t_menu btn2"><a href="../Team3_cha/noticeBoardMain.html">게시판</a></li>
			<li id="info_tab" class="t_menu btn4"><a href="pwdConfirm.do?eno=${loginUser.eno}">내정보수정</a></li>
		</ul>
		<!--//navBar-->
	</div>
	<!--//gnb-->
</body>
</html>