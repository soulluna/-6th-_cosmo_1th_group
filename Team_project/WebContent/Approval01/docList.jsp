<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="./css/main.css" />
<link rel="stylesheet" href="./css/gnb.css" />
<link rel="stylesheet" href="./css/jquery-ui.css" />

<script src="./js/jquery-2.1.1.min.js"></script>
<script src="./js/jquery-ui.min.js"></script>
<script src="./js/jquery.easing.1.3.js"></script>
<script src="./js/prefixfree.min.js"></script>
<script src="./js/main.js"></script>
<script>
	
</script>
<title>내 문서함</title>

</head>
<body>


	<!--전체 컨텐츠-->
	<div class="content">
		<!--gnb-->
		<div class="gnb">
			<!--logoBar-->
			<ul class="logobar">
				<li id="mainLogo"><a href="../Team2_kim/indexMain.html"><img
						src="../img/logo3.gif"></a></li>
				<table id="memberinfo">
					<tbody>
						<tr>
							<td id="profile_img" rowspan="2"><img
								src="http://placehold.it/70x70"></td>
							<td colspan="2">관리자 님 환영합니다.</td>
						</tr>
						<tr>
							<td><a href="../index.html">로그아웃</a> <a
								href="../Team2_kim/member/confirm.html">내정보수정</a></td>
						</tr>
					</tbody>
				</table>
			</ul>
			<!--//logoBar-->
			<!--nav bar-->
			<ul class="topBar">
				<li id="main" class="t_menu btn3"><a
					href="../Team2_kim/indexMain.html">메인</a></li>
				<li id="system" class="t_menu btn1"><a href="./docList.html">전자결재시스템</a></li>
				<li id="board" class="t_menu btn2"><a
					href="../Team3_cha/noticeBoardMain.html">게시판</a></li>
				<li id="info_tab" class="t_menu btn4"><a
					href="../Team2_kim/member/confirm.html">내정보수정</a></li>
			</ul>
			<!--//navBar-->
		</div>
		<!--//gnb-->

		<div class="docName">
			<h1>내 문서함</h1>
		</div>
		<!--깊은 컨텐츠-->
		<div class="deptContent" style="margin: 10px;">
			<!--문서종류, 검색값, 검색버튼-->
			<div>
				<select name="searchType">
					<option value="1" selected="">결재종류</option>
					<option value="2">문서제목</option>
				</select> <input type="text" class="searchInput" name="searchKey"> <input
					type="submit" class="search" value="검색" style="height: 30px;"
					onclick="docListSearchCheck()">
				</td>
			</div>
			<!--//문서종류, 검색값, 검색버튼-->

			<!--문서 목록표-->
			<table class="docListTable" border="1">
				<tr>
					<th width="60px">수/발신</th>
					<th width="120px">결재종류</th>
					<th width="80px">결재상태</th>
					<th width="700px">문서제목</th>
					<th width="100px">등록일자</th>
				</tr>
				<c:forEach items="${approvalList}" var="approval" varStatus="15">
				<tr>
					<td>${approval.txtcall}</td>
					<td>${approval.applist}</td>
					<td>${approval.progress}</td>

					<c:choose>
						<c:when test="${approval.applist == '기안서'}">
							<td><a href = "${contextPath}/Approval/draftWait.do?txtnum=${approval.txtnum}">${approval.txtname}</a></td>
						</c:when>
						<c:when test="${approval.applist == '휴가신청서'}">
							<td><a href = "${contextPath}/Approval/vacationWait.do?txtnum=${approval.txtnum}">${approval.txtname}</a></td>
						</c:when>
					</c:choose>	

					<td>${approval.entrydate}</td>
				</tr>
				</c:forEach>
			</table>

</body>
</html>