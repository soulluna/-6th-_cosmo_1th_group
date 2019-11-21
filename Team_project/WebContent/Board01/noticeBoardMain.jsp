<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
	request.setCharacterEncoding("UTF-8");
%>

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
</head>

<body>
	<div class="fullWrap">
		<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false" />
		<%-- <%@include file="/WEB-INF/GNB/header.jsp" %>--%>
		<h1>6기 1조 프로젝트 전체 게시판</h1>
		<div class="side">
			<a href="${contextPath}/Board/noticeBoardMain.do">
				<div>전체</div>
			</a> <a href="${contextPath}/Board01/department.jsp">
				<div>부서</div>
			</a> <a href="${contextPath}/Board01/hobby.jsp">
				<div>취미</div>
			</a> <a href="${contextPath}/Board01/free.jsp">
				<div>자유</div>
			</a>
		</div>

		<c:choose>
			<c:when test="${boardList==null}">
				<tr>
					<td colspan="4">

						<p align="center">
							<b> <span>게시글이 존재하지 않습니다.</span>
							</b>
						</p>
					</td>
				</tr>
			</c:when>
			<c:when test="${boardList!=null}">
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
							<tbody>

								<%-- <tr>
							<td class="co1">부서</td>
							<td class="co2">공지</td>
							<td class="co3"><a href="${contextPath}/Board01/details.jsp">
									<p>가나다라마바사아자차카타파하abcdefghijklmnopqrstuvwxyz</p>
							</a><span>(2)</span></td>
							<td class="co4">팀장</td>
							<td class="co5">19.08.05</td>
							<td class="co6">123</td>
							<td class="co7">132</td>
						</tr> --%>
								<c:forEach var="board" items="${boardList}">
									<tr class="record">
										<td class="co1">${write.noticelist}</td>
										<td class="co2">${board.txtnum}</td>
										<td class="co3"><a
											href="${contextPath}/Board/details.do?txtnum=${board.txtnum}<%-- BoardController?command=viewBoard&txtnum=${board.txtnum} --%>">${board.txtname}</a></td>
										<td class="co4">${board.ename}</td>
										<td class="co5">${board.entrydate}</td>
										<td class="co6">${board.viewtotal}</td>
										<td class="co7">${board.likenum}</td>
									</tr>
								</c:forEach>
								
							</tbody>
						</table>
					</div>
					</div>
					</c:when>
								</c:choose>
					<div class="dlehd">
						<div class="page">
							<a href="#">이전</a> <a href="#">1</a> <a href="#">2</a> <a
								href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">다음</a>
						</div>



						<div class="wkrtjd">
							<a onclick="location.href='${contextPath}/Board01/write.jsp'">글쓰기</a>
						</div>
					</div>

					<div class="gkeks">
						<select class="tpqn" name="searchType">
							<option value="제목">제목</option>
							<option value="제목+내용">제목+내용</option>
							<option value="작성자">작성자</option>
						</select>
						<div class="rjator">
							<input type="text" name="searchKey" placeholder="검색어 입력">
							<button class="search">
								<a href="#">검색 </a>
							</button>
							
						</div>
					</div>
				</div>

	<script src="${contextPath}/Board01/js/main.js"></script>
</body>

</html>