<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
   request.setCharacterEncoding("UTF-8");
   response.setContentType("text/html;utf-8");
   String eno = request.getParameter("eno");
   String dname = request.getParameter("dname");
   String dname_two = request.getParameter("dname_two");
   String hireDate = request.getParameter("hireDate");
   String rank = request.getParameter("rank");
   String ename = request.getParameter("ename");
   String eng_name = request.getParameter("eng_name");
   String tel = request.getParameter("tel");
   String email = request.getParameter("email");
    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>정보수정</title>
<script src="./js/jquery-2.1.1.min.js"></script>
<script src="./js/jquery-ui.min.js"></script>
<script src="./js/jquery.easing.1.3.js"></script>
<script src="./js/prefixfree.min.js"></script>
<script src="js/change.js"></script>
<link rel="stylesheet" href="./css/change.css" />
<link rel="stylesheet" href="../css/gnb.css" />
</head>

<body>
	<form onsubmit="return validate();" action="./select.html">
		<div class="fullWrap">
			<!--gnb-->
			<div class="gnb">
				<!--logoBar-->
				<ul class="logobar">
					<li id="mainLogo"><a href="../indexMain.html"><img
							src="./img/logo3.gif"></a></li>
					<table id="memberinfo">
						<tbody>
							<tr>
								<td id="profile_img" rowspan="2"><img
									src="http://placehold.it/70x70"></td>
								<td colspan="2">관리자 님 환영합니다.</td>
							</tr>
							<tr>
								<td><a href="../../index.html">로그아웃</a> <a
									href="./confirm.html">내정보수정</a></td>
							</tr>
						</tbody>
					</table>
				</ul>
				<!--//logoBar-->
				<!--nav bar-->
				<ul class="topBar">
					<li id="main" class="t_menu btn3"><a href="../indexMain.html">메인</a></li>
					<li id="system" class="t_menu btn1"><a
						href="../Team1_won&you/docList.html">전자결재시스템</a></li>
					<li id="board" class="t_menu btn2"><a
						href="../Team3_cha/noticeBoardMain.html">게시판</a></li>
					<li id="info_tab" class="t_menu btn4"><a href="./confirm.html">내정보수정</a></li>
				</ul>
				<!--//navBar-->
			</div>
			<!--//gnb-->
			<!--top_side-->
			<div id="top_section">
				<div class="section_menu">사원정보</div>
				
				<table id="info_table">
					<tbody class="filebox">
						<tr>
							<td rowspan="4" align="center"><img
								src="http://placehold.it/120x150" id="imgPreview" width="120"
								height="150"><br> <input type="file" id="img"
								onchange="showImagePreview(this);"> <label for="img">첨부</label>
							</td>
							<td class="text_info">사번</td>
							<td><input type="text" readonly><%=eno %></td>
							<td class="text_info">부서</td>
							<td>
								<select class="area" id="dep1"><%=dname %>
									<option value="0">선택하세요</option>
									<option value="1">영업부</option>
									<option value="2">인사부</option>
									<option value="3">기술지원팀</option>
								</select></td>
							<td class="text_info">소속</td>
							<td>
								<select class="area" id="dep2"><%=dname_two %>
									<option value="0">선택하세요</option>
									<option value="4">1팀</option>
									<option value="5">2팀</option>
									<option value="6">3팀</option>
							</select></td>
						</tr>
						<tr>
							<td class="text_info">입사일</td>
							<td><input type="date" readonly><%=hireDate %></td>
							<td class="text_info">직급</td>
							<td><input type="text" readonly="readonly"><%=rank %></td>
						</tr>
						<tr>
							<td class="text_info">이름</td>
							<td><input type="text" id="userName"
								placeholder="한글만 입력해주세요"><%=ename %></td>
							<td class="text_info">영문이름</td>
							<td><input type="text" id="engName" placeholder="영문만 입력해주세요"><%=eng_name %></td>
						</tr>
						<tr>
							<td class="text_info">연락처</td>
							<td><input type="phone" id="phone" placeholder="-을 빼고 입력하세요."><%=tel %></td>
							<td class="text_info">이메일</td>
							<td><input type="text" id="email"><%=email %></td>
						</tr>
					</tbody>
				</table>
			</div>
			<!--//top_side-->
			<div id="button_section">
				<input type="submit" value="확인" class="e_btn"> <input
					type="button" value="취소" class="e_btn" onclick="cancel();">
			</div>
		</div>
		<!--//fullwarp-->
	</form>
</body>
</html>