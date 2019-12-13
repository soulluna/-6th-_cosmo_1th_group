<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPath}/Main01/css/gnb.css" />
</head>
<body>
	<!--gnb-->
	<div class="gnb">
		<!--logoBar-->
		<ul class="logobar">
			<li id="mainLogo"><a href="${contextPath}/Main/login.do"><img src="${contextPath}/img/logo3.gif"></a></li>
			<table id="memberinfo">
				<tbody>
					<tr>
						<td id="profile_img" rowspan="2">
						<c:choose>
							<c:when test="${loginUser.dname=='영업부'}">
								<img src="${contextPath}/img/sales.jpg">
							</c:when>
							<c:when test="${loginUser.dname=='인사부'}">
								<img src="${contextPath}/img/workers.jpg">
							</c:when>
							<c:when test="${loginUser.dname=='기술개발부'}">
								<img src="${contextPath}/img/development.jpg">
							</c:when>
						</c:choose>
						<td colspan="2">${loginUser.ename}님 환영합니다.</td>
					</tr>
					<tr>
						<td>
							<input type="button" class="lgnbtn" value="로그아웃" onclick="location.href='${contextPath}/Main/logout.do'"> 
							<input type="button" class="lgnbtn" value="내정보수정" onclick="location.href='${contextPath}/Main/pwdConfirmForm.do'">
							<!-- <a href="${contextPath}/Main01/member/confirm.jsp">내정보수정</a> -->
						</td>
					</tr>
				</tbody>
			</table>
		</ul>
		<!--//logoBar-->
		<!--nav bar-->
		<ul class="topBar">
			<li id="main" class="t_menu btn3"><a href="${contextPath}/Main/login.do">메인</a></li>
			<li id="system" class="t_menu btn1"><a href="${contextPath}/Approval/docList.do">전자결재시스템</a></li>
			<li id="board" class="t_menu btn2"><a href="${contextPath}/Board/noticeBoardMain.do">게시판</a></li>
			<li id="info_tab" class="t_menu btn4"><a href="${contextPath}/Main/pwdConfirmForm.do">내정보수정</a></li>
		</ul>
		<!--//navBar-->
	</div>
	<!--//gnb-->
</body>
</html>