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
	<div class="fullWrap">
		<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false" />
		<h1>상세페이지</h1>
		<div class="wjdduf">
			<h2>${details.txtname}</h2>
			<div class="main_tkdtp">
				<P>${details.ename}</P>
				<p>댓글</p>
				<p>${details.comtotal}</p>
				<p>조회수</p>
				<p>${details.viewtotal}</p>
				<p>추천수</p>
				<p class="co7">${details.likenum}</p>
				<span class="font-11 text-muted"> 
					<span class="media-info">
						<i class="fa fa-clock-o"></i> 
						<span class="orangered">${details.entrydate}
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
				<button
					onclick="fn_modForm('${contextPath}/board/modForm.do','${board.txtxnum}');">
					<a href="#">수정하기</a>
				</button>
			</div>
			<div class="wjddu">
				<textarea class="text_sodyd" readonly="readonly" cols="400"
					rows="100">${details.txtcont}</textarea>
			</div>
			<div class="cncjsqjxms">
				<c:if test="${loginUser.eno==details.eno}">
					<button class="delete" onclick="deleteArticle('${contextPath}');">삭제</button>
				</c:if>
				<button class="n_good">
					<a href="${contextPath}/Board/like.do?txtnum=${details.txtnum}">추천</a>
				</button>
			</div>
			<div class="media" id="c_1">
				<div class="photo pull-left">
					<div class="media-object">
						<i class="fa fa-user"></i>
					</div>
				</div>
				<div class="media-body">
					<div class="media-heading">
						<b><a href="#"> <span>코스모6기맴버</span></a></b> 
						<span class="font-11 text-muted"> <span class="media-info">
								<i class="fa fa-clock-o"></i> 
								<span class="orangered">08/29 20:14</span>
						</span>
							<button class="del">X</button>
							<p class="ud">수정하기</p>
						</span>
						<div class="print-hide pull-right font-11 ">
							<a href="#"> </a>
						</div>
					</div>
					<div class="media-content">
						현재 40%는 된듯
						<div class="cmt-good-btn">
							<a href="#" class="cmt-good">
								<p>추천 :</p> <span id="c_good1">0</span>
							</a>
						</div>
						<span id="reply_1"></span>
						<!-- 답변 -->
						<input type="hidden" value="#wr_id=1&amp;crows=30&amp;page=1"
							id="comment_url_1"> <input type="hidden" value="1"
							id="comment_page_1"> <input type="hidden" value=""
							id="secret_comment_1">
						<textarea id="save_comment_1" style="display: none"></textarea>
					</div>
				</div>
				<div class="media-body">
					<div class="media-heading">
						<b><a href="#"> <span>코스모6기1조조장</span></a></b> <span
							class="font-11 text-muted"> <span class="media-info">
								<i class="fa fa-clock-o"></i> <span class="orangered">08/29
									22:14</span>
						</span>
							<button class="del">X</button>
							<p class="ud">수정하기</p>
						</span>
						<div class="print-hide pull-right font-11 ">
							<a href="#" returnfalse;> </a>
						</div>
					</div>
					<div class="media-content">
						현재 60%는 된듯?
						<div class="cmt-good-btn">
							<a href="#" class="cmt-good">
								<p>추천 :</p> <span id="c_good2">3</span>
							</a>
						</div>
						<span id="reply_2"></span>
						<!-- 답변 -->
						<input type="hidden" value="#wr_id=1&amp;crows=30&amp;page=1"
							id="comment_url_1"> <input type="hidden" value="1"
							id="comment_page_1"> <input type="hidden" value=""
							id="secret_comment_1">
						<textarea id="save_comment_1" style="display: none"></textarea>
					</div>
				</div>
			</div>
			<div class="eotrmf">
				<textarea class="eotrmfdlqfur" name="comment"
					placeholder="댓글을 입력해주세요."></textarea>
				<button class="c_write">
					<a href="#">댓글 등록</a>
				</button>
			</div>
		</div>
	</div>
</body>

</html>