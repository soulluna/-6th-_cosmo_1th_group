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

<title>내 문서함</title>

</head>
<body>

	<!--전체 컨텐츠-->
	<div class="content">
		<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false" />

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
					<input type="text" class="searchInput" name="searchKey" />
					<input type="submit" class="search" value="검색" style="height: 30px;" onclick="docListSearchCheck()" />
					<%-- <input type="text" name="searchType" value="${searchType}"/>
					<input type="text" name="searchKey" value="${searchKey}"/> --%>
				</form>
			</div>
			<!--//문서종류, 검색값, 검색버튼-->

			<!--문서 목록표-->
			<table class="docListTable" border="1">
				<tr>
					<th width="60px"><a href="${contextPath}/Approval/disRecSort.do?pageNum=${pagingMap.pageNum}&pageSession=${pagingMap.pageSessionNum}&searchType=${searchType}&searchKey=${searchKey}">수/발신</a></th>
					<th width="120px">결재종류</th>
					<th width="80px"><a href="${contextPath}/Approval/docStateSort.do?pageNum=${pagingMap.pageNum}&pageSession=${pagingMap.pageSessionNum}&searchType=${searchType}&searchKey=${searchKey}">결재상태</a></th>
					<th width="600px">문서제목</th>
					<th width="100px">작성자</th>
					<th width="100px"><a href="${contextPath}/Approval/docDaySort.do?pageNum=${pagingMap.pageNum}&pageSession=${pagingMap.pageSessionNum}&searchType=${searchType}&searchKey=${searchKey}">등록일자</a></th>
				</tr>
				<c:choose>
					<c:when test="${approvalList.size()==0}">
						<tr>
							<td colspan="5">검색 결과가 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${approvalList}" var="approval" varStatus="15">
							<tr>
								<c:choose>
									<c:when test="${approval.eno==loginUser.eno}">
										<td>발신</td>
									</c:when>
									<c:otherwise>
										<td>수신</td>
									</c:otherwise>
								</c:choose>
								<td>${approval.applist}</td>
								<td class="progress">${approval.progress}</td>
								<c:choose>
									<c:when test="${approval.applist == '기안서'}">
										<td style="text-align: left;">
											&nbsp;<a href="${contextPath}/Approval/draftWait.do?txtnum=${approval.txtnum}">${approval.txtname}</a>
										</td>
									</c:when>
									<c:when test="${approval.applist == '휴가신청서'}">
										<td style="text-align: left;">
											&nbsp;<a href="${contextPath}/Approval/vacationWait.do?txtnum=${approval.txtnum}">${approval.txtname}</a>
										</td>
									</c:when>
								</c:choose>
								<td>${approval.ename}</td>
								<td>${approval.entrydate}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>

			<div class="bottomNumber">
				<c:if test="${(pagingMap.maxSessionNum >= pagingMap.pageSessionNum) && pagingMap.pageSessionNum != 1}">
					<a href="${contextPath}/Approval/docList.do?pageNum=${(pagingMap.pageSessionNum-1)*5-4}&pageSession=${pagingMap.pageSessionNum-1}&searchType=${searchType}&searchKey=${searchKey}">이전</a>
				</c:if>

				<c:forEach var="page" begin="${(pagingMap.pageSessionNum-1)*5+1}" end="${pagingMap.pageSessionNum*5}">
					<c:choose>
						<c:when test="${pagingMap.pageNum == page}">
							<a href="${contextPath}/Approval/docList.do?pageNum=${page}&pageSession=${pagingMap.pageSessionNum}&searchType=${searchType}&searchKey=${searchKey}" style="color: red">${page}</a>
						</c:when>

						<c:when test="${page <= pagingMap.maxPageNum}">
							<a href="${contextPath}/Approval/docList.do?pageNum=${page}&pageSession=${pagingMap.pageSessionNum}&searchType=${searchType}&searchKey=${searchKey}">${page}</a>
						</c:when>

					</c:choose>

				</c:forEach>
				<c:choose>
					<c:when test="${pagingMap.maxSessionNum > pagingMap.pageSessionNum}">
						<a href="${contextPath}/Approval/docList.do?pageNum=${(pagingMap.pageSessionNum-1)*5+6}&pageSession=${pagingMap.pageSessionNum+1}&searchType=${searchType}&searchKey=${searchKey}">다음</a>
					</c:when>

				</c:choose>

				<c:if test="${loginUser.eno != 9052501152}">
					<button class="docCreate" name="docCreate" type="button" onclick="location.href='${contextPath}/Approval/draft.do'">작성</button>
				</c:if>

			</div>

		</div>
		<!--//깊은 컨텐츠-->
	</div>
	<!--//전체 컨텐츠-->
</body>

</html>