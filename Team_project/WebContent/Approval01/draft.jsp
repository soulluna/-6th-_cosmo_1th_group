<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
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
<link rel="stylesheet" href="${contextPath}/Approval01/css/main.css" />
<link rel="stylesheet" href="${contextPath}/Approval01/css/gnb.css" />
<link rel="stylesheet" href="${contextPath}/Approval01/css/jquery-ui.css" />

<script src="${contextPath}/Approval01/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/Approval01/js/jquery-ui.min.js"></script>
<script src="${contextPath}/Approval01/js/jquery.easing.1.3.js"></script>
<script src="${contextPath}/Approval01/js/prefixfree.min.js"></script>
<script src="${contextPath}/Approval01/js/main.js"></script>

<title>기안서</title>
</head>
<body>
	<div class="content">
		<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false" />
		<form name="frm" method="post">
			<select class="docSelecter" onchange="if(this.value) location.href=(this.value)">
				<option value="${contextPath}/Approval/draft.do?pageNum=${pageNum}&pageSession=${pageSessionNum}&sendReceive=${sendReceive}&serachDocState=${serachDocState}&serachDocList=${serachDocList}&searchType=${searchType}&searchKey=${searchKey}&sendReceive=${sendReceive}&searchDatepicker1=${searchDatepicker1}&searchDatepicker2=${searchDatepicker2}" selected>기안서</option>
				<option value="${contextPath}/Approval/vacation.do?pageNum=${pageNum}&pageSession=${pageSessionNum}&sendReceive=${sendReceive}&serachDocState=${serachDocState}&serachDocList=${serachDocList}&searchType=${searchType}&searchKey=${searchKey}&sendReceive=${sendReceive}&searchDatepicker1=${searchDatepicker1}&searchDatepicker2=${searchDatepicker2}">휴가신청서</option>
			</select>

			<div class="docName">
				<h1>기안서</h1>
			</div>
			<div class="deptContent">

				<div class="signtable">
					<table class="signtableleft">
						<tr>
							<th>이름</th>
							<td>${loginUser.ename}</td>
						</tr>
						<tr>
							<th>직책</th>
							<td>${loginUser.rank}</td>
						</tr>
						<tr>
							<th>소속</th>
							<td>${loginUser.dname}</td>
						</tr>
					</table>


					<table class="signtableright" border="1">
						<tr>
							<th>${loginUser.rank}</th>
							<c:if test="${createdMidUser.rank!=null}">
								<th>${createdMidUser.rank}</th>
							</c:if>
							<th>${createdFinUser.rank}</th>
						</tr>
						<tr>
							<td style="vertical-align: top">
								<input type="text" name="firUser" style='width: 80px; text-align: center;' value="${loginUser.ename}" disabled>
								<input type="hidden" name="firUser" style='width: 80px; text-align: center;' value="${loginUser.ename}">
								<br>
								<span style="color: red;"></span>
							</td>
							<c:if test="${createdMidUser.rank!=null}">
								<td style="vertical-align: top">
									<input type="text" name="midUser" style='width: 80px; text-align: center;' value="${createdMidUser.ename}" disabled>
									<input type="hidden" name="midUser" style='width: 80px; text-align: center;' value="${createdMidUser.ename}">
									<br>
									<span style="color: red;"></span>
								</td>
							</c:if>
							<td style="vertical-align: top">
								<input type="text" name="finUser" style='width: 80px; text-align: center;' value="${createdFinUser.ename}" disabled>
								<input type="hidden" name="finUser" style='width: 80px; text-align: center;' value="${createdFinUser.ename}">
								<br>
								<span style="color: red;"></span>
							</td>
						</tr>
						<tr>
							<td></td>
							<c:if test="${createdMidUser.rank!=null}">
								<td></td>
							</c:if>
							<td></td>
						</tr>
					</table>
				</div>

				<div class="inputarea">

					<table>
						<tr>
							<td>
								제목<br>
								<input class="inputTitle" type="text" name="title" required placeholder="제목을 입력해주세요." maxlength="50">
							</td>
						</tr>
						<tr>
							<td>
								<br> 내용<br>
								<textarea class="inputContent" name="reason" placeholder="내용을 입력해주세요." required maxlength="1000"></textarea>
							</td>
						</tr>
					</table>
					<br>
					<div class="bottomBt">
						<button type="button" onclick="draftCheck('draft','${pageNum}','${pageSessionNum}','${searchKey}','${searchType}','${sendReceive}','${serachDocState}','${serachDocList}','${searchDatepicker1}','${searchDatepicker2}')">등록</button>
						<button type="button" onclick="docModify()" disabled>수정</button>
						<button type="button" onclick="docDelete()" disabled>삭제</button>
						<button type="button" onclick="docApprov()" disabled>승인</button>
						<button type="button" onclick="docReturn()" disabled>반려</button>
						
						<button type="button" onclick="docCancle('${pageNum}','${pageSessionNum}','${searchKey}','${searchType}','${sendReceive}','${serachDocState}','${serachDocList}','${searchDatepicker1}','${searchDatepicker2}')">취소</button>
					</div>
				</div>
			</div>
		</form>
	</div>

</body>
</html>