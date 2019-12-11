<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:if test="${empty loginUser}">
	<jsp:forward page="${contextPath}/index.jsp" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>전체게시판</title>
<link rel="stylesheet" href="${contextPath}/Board01/css/main.css" />
<link rel="stylesheet" href="${contextPath}/Board01/css/gnb.css" />
<script src="${contextPath}/Board01/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/Board01/js/jquery.easing.1.3.js"></script>
<script src="${contextPath}/Board01/js/jquery-ui.min.js"></script>
<script src="${contextPath}/Board01/js/prefixfree.min.js"></script>
<script src="${contextPath}/Board01/js/main.js"></script>
</head>

<body>
	<div class="fullWrap">
		<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false" />
		<h1>6기 1조 프로젝트 전체 게시판</h1>
		<div class="side">
			<a href="${contextPath}/Board/noticeBoardMain.do">
				<div>전체</div>
			</a> <a href="${contextPath}/Board/noticeBoardMain.do?noticeList=1">
				<div>부서</div>
			</a> <a href="${contextPath}/Board/noticeBoardMain.do?noticeList=2">
				<div>취미</div>
			</a> <a href="${contextPath}/Board/noticeBoardMain.do?noticeList=3">
				<div>자유</div>
			</a>
		</div>

		<div class="wjdduf">
			<div class="akwcna">
				<table border="1" class="line">
					<thead>
						<tr class="name">
							<th class="co1">게시판</th>
							<th class="co2">번호</th>
							<th class="co3">제목</th>
							<th class="co4">작성자</th>
							<th class="co5">생성날자</th>
							<th class="co6">조회</th>
							<th class="co7">추천</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${boardList.size()==0}">
							<tr>
								<td colspan="7" align="center">게시글이 존재하지 않습니다.</td>
							</tr>
						</c:when>
						<c:when test="${boardList.size()!=0}">
							<tbody>
								<c:if test="${announceList.size()!=0 && pagingMap.pageNum==1}">
									<c:forEach var="announce" items="${announceList}">
										<tr class="record">
											<td class="co1" style="color:red"><b>공지</b></td>
											<td class="co2">${announce.txtnum}</td>
											<td class="co3"><a
												href="${contextPath}/Board/details.do?txtnum=${announce.txtnum}&pageNum=${pagingMap.pageNum}">${announce.txtname}</a>
												 <c:if test="${announce.comcount != 0}"><span>[${announce.comcount}]</span></c:if></td>
											<td class="co4">${announce.ename}</td>
											<td class="co5"><fmt:formatDate
													value="${announce.entrydate}" pattern="yyyy-MM-dd" /></td>
											<td class="co6">${announce.viewtotal}</td>
											<td class="co7">${announce.likenum}</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:forEach var="board" items="${boardList}">
									<tr class="record">
										<c:choose>
											<c:when test="${board.noticelist==1}">
												<td class="co1">부서</td>
											</c:when>
											<c:when test="${board.noticelist==2}">
												<td class="co1">취미</td>
											</c:when>
											<c:otherwise>
												<td class="co1">자유</td>
											</c:otherwise>
										</c:choose>
										<td class="co2">${board.txtnum}</td>
										<td class="co3"><a
											href="${contextPath}/Board/details.do?txtnum=${board.txtnum}&pageNum=${pagingMap.pageNum}">${board.txtname}</a>
										 <c:if test="${board.comcount != 0}"><span>[${board.comcount}]</span></c:if>
										 </td>
										<td class="co4">${board.ename}</td>
										<td class="co5"><fmt:formatDate
												value="${board.entrydate}" pattern="yyyy-MM-dd" /></td>
										<td class="co6">${board.viewtotal}</td>
										<td class="co7">${board.likenum}</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:when>
					</c:choose>
				</table>
			</div>
		</div>
		<div class="dlehd">
			<div class="page">
				<!-- ------------------------------------------------------- -->
				<c:if
					test="${(pagingMap.maxSessionNum >= pagingMap.pageSessionNum) && pagingMap.pageSessionNum != 1}">
					<a
						href="${contextPath}/Board/noticeBoardMain.do?pageNum=${(pagingMap.pageSessionNum-1)*5-4}&pageSession=${pagingMap.pageSessionNum-1}&searchType=${searchType}&searchKey=${searchKey}&noticeList=${noticeList}">이전</a>
				</c:if>

				<c:forEach var="page" begin="${(pagingMap.pageSessionNum-1)*5+1}"
					end="${pagingMap.pageSessionNum*5}">
					<c:choose>
						<c:when test="${page==pagingMap.pageNum}">
							<a
								href="${contextPath}/Board/noticeBoardMain.do?pageNum=${page}&pageSession=${pagingMap.pageSessionNum}&searchType=${searchType}&searchKey=${searchKey}&noticeList=${noticeList}"
								style="color: red">${page}</a>
						</c:when>
						<c:when test="${page <= pagingMap.maxPageNum}">
							<a
								href="${contextPath}/Board/noticeBoardMain.do?pageNum=${page}&pageSession=${pagingMap.pageSessionNum}&searchType=${searchType}&searchKey=${searchKey}&noticeList=${noticeList}">${page}</a>
						</c:when>
					</c:choose>
				</c:forEach>

				<c:if test="${pagingMap.maxSessionNum > pagingMap.pageSessionNum}">
					<a
						href="${contextPath}/Board/noticeBoardMain.do?pageNum=${(pagingMap.pageSessionNum-1)*5+6}&pageSession=${pagingMap.pageSessionNum+1}&searchType=${searchType}&searchKey=${searchKey}&noticeList=${noticeList}">다음</a>
				</c:if>
				<!-- ------------------------------------------------------- -->
				<!-- <a href="#">이전</a> <a href="#">1</a> <a href="#">2</a> <a href="#">3</a>
				<a href="#">4</a> <a href="#">5</a> <a href="#">다음</a> -->
			</div>
			<div class="wkrtjd">
				<a onclick="location.href='${contextPath}/Board01/write.jsp'">글쓰기</a>
			</div>
		</div>
		<form name="frm" method="post">
			<div class="gkeks">
				<select class="tpqn" name="searchType">
					<option value="1">제목</option>
					<option value="2">제목+내용</option>
					<option value="3">작성자</option>
				</select>
				<div class="rjator">
					<input type="text" name="searchKey" placeholder="검색어 입력">
					<button type="submit" class="search" onclick="noticeSearchCheck('${contextPath}')">검색</button>
					<input type="hidden" name="searchType" value="${searchType}" /> 
					<input type="hidden" name="searchKey" value="${searchKey}" /> 
					<input type="hidden" name="noticeList" value="${noticeList}">
				</div>
			</div>
		</form>
	</div>

</body>

</html>