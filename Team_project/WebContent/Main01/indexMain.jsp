<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
   request.setCharacterEncoding("UTF-8");
   response.setContentType("text/html;utf-8");
%>
<c:if test="${empty loginUser}">
	<jsp:forward page="${contextPath}/index.jsp" />
</c:if>
<!-- contextPath = /Team_project -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>index</title>
<script src="${contextPath}/Main01/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/Main01/js/jquery-ui.min.js"></script>
<script src="${contextPath}/Main01/js/jquery.easing.1.3.js"></script>
<script src="${contextPath}/Main01/js/prefixfree.min.js"></script>
<link rel="stylesheet" href="${contextPath}/Main01/css/index.css" />
<link rel="stylesheet" href="${contextPath}/Main01/css/gnb.css" />
<link rel="stylesheet" href="${contextPath}/Main01/css/calander.css" />
<script src="${contextPath}/Main01/js/calander.js"></script>
<script src="${contextPath}/Main01/js/main.js"></script>
</head>
<body>
	<form class="fullWrap">
		<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false" />
		<!--left_side-->
		<div id="l_section">
			<div class="section_menu">
				달력
				<div class="cal_top">
					<span id="prevMonth" class="cal_tit"><a id="movePrevMonth"
						onclick="movePrevMonth();">Prev</a></span> <span id="cal_top_year"></span>
					<span id="cal_top_month"></span> <span id="nextMonth"
						class="cal_tit"><a id="moveNextMonth"
						onclick="moveNextMonth();">Next</a></span>
				</div>
				<div id="cal_tab" class="cal"></div>
			</div>
			<div class="scadule">
				<table class="ListTable" border="1">
				<c:if test="${scadulList==null}">
					<h3>없다구</h3>
				</c:if>
					<tr>
						<th>일정</th>
						<th>시작시간</th>
						<th>종료시간</th>
					</tr>
					<c:forEach items="${scadulList}" var="schdul">
						<tr>
							<td><a href="#" onclick="schdulDetails('${contextPath}','${schdul.schnum}')">${schdul.schname}</a></td>
							<td><fmt:formatDate value="${schdul.startDate}" pattern="yyyy-MM-dd hh:mm" /></td>
							<td><fmt:formatDate value="${schdul.endDate}" pattern="yyyy-MM-dd hh:mm" /></td>
						</tr>
					</c:forEach>
				</table>
				<div id="buttons">
					<input type="button" value="스캐쥴 작성" onclick="schdulWrite('${contextPath}');">
				</div>
			</div>
		</div>
		<!--//left_side-->
		<!--right_side-->
		<div id="r_section">
			<!-- 나중에 전자결재 데이터베이스 완성되면 그 때viewArticles참조해서 전자결재에서 상위 10개만 읽어오기 -->
			<div class="section_menu">
				<a href="${contextPath}/Approval/docList.do">전자결재 more></a>
			</div>
			<div id="board2">
				<table class='ListTable' border='1'>
					<tr>
						<th width='15%'>결재종류</th>
						<th>문서제목</th>
						<th width='15%'>결재상태</th>
					</tr>
					<c:forEach items="${appList}" var="app">
						<tr>
							<td>${app.applist}</td>
							<c:choose>
								<c:when test="${app.applist=='기안서'}">
									<td><a href="${contextPath}/Approval/draftWait.do?txtnum=${app.txtnum}">${app.txtname}</a></td>
								</c:when>
								<c:when test="${app.applist=='휴가신청서'}">
									<td><a href="${contextPath}/Approval/vacationWait.do?txtnum=${app.txtnum}">${app.txtname}</a></td>
								</c:when>
							</c:choose>
							<td>${app.progress}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<!-- 나중에 게시판 데이터베이스 완성되면 그 때viewArticles참조해서 게시판에서 상위 10개만 읽어오기 -->
			<div class="section_menu">
				<a href="${contextPath}/Board/noticeBoardMain.do">게시판 more></a>
			</div>
			<div id="board3">
				<script>
                    $(document).ready(function(){
                        makeApproval();
                    });
                    function makeApproval() {
                        var setBoradHTML="";
                        setBoradHTML+="<table class='ListTable' border='1'>";
                        setBoradHTML+="<tr><th width='15%'>결재종류</th><th>문서제목</th><th width='15%'>결재상태</th></tr>";
                        for (var i = 0; i < 10; i++) {
                            setBoradHTML+="<tr>";
                            for (var j = 0; j < 3; j++) {
                                setBoradHTML+="<td>" + "tests" + "</td>";
                            }
                            setBoradHTML+="</tr>";
                            $("#board3").html(setBoradHTML);
                        }
                    }
                </script>
			</div>
		</div>
		<!--//right_side-->
	</form>
</body>
</html>