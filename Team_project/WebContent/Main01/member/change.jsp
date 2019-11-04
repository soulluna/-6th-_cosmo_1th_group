<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
   request.setCharacterEncoding("UTF-8");
   response.setContentType("text/html;utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>정보수정</title>
<script src="${contextPath}/Main01/member/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/Main01/member/js/jquery-ui.min.js"></script>
<script src="${contextPath}/Main01/member/js/jquery.easing.1.3.js"></script>
<script src="${contextPath}/Main01/member/js/prefixfree.min.js"></script>
<script src="${contextPath}/Main01/member/js/change.js"></script>
<link rel="stylesheet" href="${contextPath}/Main01/member/css/change.css" />
<link rel="stylesheet" href="${contextPath}/Main01/css/gnb.css" />
</head>

<body>
	<form onsubmit="return validate();" action="userInfoChange.do" method="post">
		<div class="fullWrap">
			<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false"/>
			<!--top_side-->
			<div id="top_section">
				<div class="section_menu">사원정보</div>
				<table id="info_table">
					<tbody class="filebox">
						<tr>
							<td rowspan="4" align="center">
								<img src="http://placehold.it/120x150" id="imgPreview" width="120"height="150"><br> 
								<input type="file" id="img" onchange="showImagePreview(this);"> <label for="img">첨부</label>
							</td>
							<td class="text_info">사번</td>
							<td><input type="text" readonly value="${memberVO.eno}"></td>
							<td class="text_info">부서</td>
							<td>
								<select class="area" id="dep1">
									<option value="0">선택하세요</option>
									<option value="1">영업부</option>
									<option value="2">인사부</option>
									<option value="3">기술지원팀</option>
								</select></td>
							<td class="text_info">소속</td>
							<td>
								<select class="area" id="dep2">
									<option value="0">선택하세요</option>
									<option value="4">1팀</option>
									<option value="5">2팀</option>
									<option value="6">3팀</option>
							</select></td>
						</tr>
						<tr>
							<td class="text_info">입사일</td>
							<td><input type="date" readonly value="${memberVO.hireDate}"></td>
							<td class="text_info">직급</td>
							<td><input type="text" readonly value="${memberVO.rank}"></td>
						</tr>
						<tr>
							<td class="text_info">이름</td>
							<td><input type="text" id="userName" name="ename" placeholder="한글만 입력해주세요" value="${memberVO.ename}"></td>
							<td class="text_info">영문이름</td>
							<td><input type="text" id="engName" name="eng_name" placeholder="영문만 입력해주세요" value="${memberVO.eng_name}"></td>
						</tr>
						<tr>
							<td class="text_info">연락처</td>
							<td><input type="phone" id="phone" name="tel" placeholder="-을 빼고 입력하세요." value="${memberVO.tel}"></td>
							<td class="text_info">이메일</td>
							<td><input type="text" id="email" name="email" value="${memberVO.email}"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<!--//top_side-->
			<div id="button_section">
				<input type="submit" value="확인" class="e_btn"> 
				<input type="button" value="취소" class="e_btn" onclick="cancel();">
			</div>
		</div>
		<!--//fullwarp-->
	</form>
</body>
</html>