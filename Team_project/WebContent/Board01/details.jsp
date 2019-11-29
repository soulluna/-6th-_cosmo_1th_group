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
<title>상세게시판</title>
<link rel="stylesheet" href="${contextPath}/Board01/css/tkdtp.css">
<link rel="stylesheet" href="${contextPath}/Board01/css/gnb.css" />
<script src="${contextPath}/Board01/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/Board01/js/jquery.easing.1.3.js"></script>
<script src="${contextPath}/Board01/js/jquery-ui.min.js"></script>
<script src="${contextPath}/Board01/js/prefixfree.min.js"></script>
<script src="${contextPath}/Board01/js/main.js"></script>
</head>

<body>
	<form name="frm">
		<div class="fullWrap">
			<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false" />
			<h1>상세페이지</h1>
			<div class="wjdduf">
				<h2>${board.txtname}</h2>
				<div class="main_tkdtp">
					<P>${board.ename}</P>
					<p>댓글</p>
					<p>${board.comtotal}</p>
					<p>조회수</p>
					<p>${board.viewtotal}</p>
					<p>추천수</p>
					<p class="co7">${board.likenum}</p>
					<span class="font-11 text-muted"> <span class="media-info">
							<i class="fa fa-clock-o"></i> <span class="orangered">${board.entrydate}
						</span>
					</span>
					</span>
				</div>
				<div class="rmfdlehd">
					<button>
						<a href="#(i)-1">이전글</a>
					</button>
					<button>
						<a href="#(i)+1">다음글</a>
					</button>
					<button>
						<a href="${contextPath}/Board/noticeBoardMain.do">목록으로</a>
					</button>
					<button>
						<a href="${contextPath}/Board/modForm.do?txtnum=${board.txtnum}">수정하기</a>
					</button>
				</div>
				<div class="wjddu">
					<textarea class="text_sodyd" readonly="readonly" cols="400"
						rows="100">${board.txtcont}</textarea>
				</div>
				<div class="cncjsqjxms">
				
		
				
					<c:if test="${loginUser.eno==board.eno}">
						<button class="delete"
							onclick="deleteArticle('${contextPath}','${board.txtnum}');">삭제</button>
					</c:if>
					<c:if test="${loginUser.eno!=board.eno}">
					<button style="visibility:hidden" class="delete"></button>
					<button class="n_good">
						<a href="${contextPath}/Board/like.do?txtnum=${board.txtnum}">추천</a>
					</button>
					</c:if>
				</div>
				<c:choose>
					<c:when test="${board.comcont==null}">
						<div class="eotrmf">
							<textarea class="eotrmfdlqfur" name="comment"
								placeholder="댓글을 입력해주세요."></textarea>
							<input type="hidden" name="txtnum" value="${board.txtnum}">
							<button class="c_write" onclick="addComment('${contextPath}');">댓글 등록</button>
						</div>
					</c:when>
					<c:otherwise>
						<div class="media" id="c_1">
							<div class="photo pull-left">
								<div class="media-object">
									<i class="fa fa-user"></i>
								</div>
							</div>
							<div class="media-body">
								<div class="media-heading">
									<b><a href="#"><span>${board.comuser}</span></a></b>
								</div>
								<div class="media-content">${board.comcont}</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form>
</body>

</html>