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
<link rel="stylesheet" href="${contextPath}/Approval01/css/main.css" />
<link rel="stylesheet" href="${contextPath}/Approval01/css/gnb.css" />
<link rel="stylesheet" href="${contextPath}/Approval01/css/jquery-ui.css" />

<script src="${contextPath}/Approval01/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/Approval01/js/jquery-ui.min.js"></script>
<script src="${contextPath}/Approval01/js/jquery.easing.1.3.js"></script>
<script src="${contextPath}/Approval01/js/prefixfree.min.js"></script>
<script src="${contextPath}/Approval01/js/main.js"></script>
<script>
	
</script>
<title>내 문서함</title>

</head>
<body>


	<!--전체 컨텐츠-->
	<div class="content">
		<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false"/>

		<div class="docName">
			<h1>내 문서함</h1>
		</div>
		<!--깊은 컨텐츠-->
		<div class="deptContent" style="margin: 10px;">
			<!--문서종류, 검색값, 검색버튼-->
			<div>
				<form>
					<select name="searchType">
						<option value="1" selected="">결재종류</option>
						<option value="2">문서제목</option>
					</select>
					<input type="text" class="searchInput" name="searchKey">
					<input type="submit" class="search" value="검색"
					style="height: 30px;" onclick="docListSearchCheck()">
				</form>
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
			
			<button class="docCreate" name="docCreate" type="button" onclick="location.href='${contextPath}/Approval/draft.do'">작성</button>

</body>
</html>