<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:if test="${empty loginUser}">
	<jsp:forward page="${contextPath}/Main/login.do"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>글쓰기</title>
<link rel="stylesheet" href="./css/rmfwkrtjd.css">
<link rel="stylesheet" href="./css/gnb.css" />
<script src="./js/jquery-2.1.1.min.js"></script>
<script src="./js/jquery.easing.1.3.js"></script>
<script src="./js/jquery-ui.min.js"></script>
<script src="./js/prefixfree.min.js"></script>

<script>
	function fn_write() {
		frmArticle.encoding = "application/x-www-form-urlencoded";
		frmArticle.action = "${contextPath}/Board/write.do";
		frmArticle.submit();
	}
</script>
</head>

<body>
<form name="frmArticle" method="post" enctype="multipart/form-data">
	<input type="hidden" id = "eno" name="eno" value="${loginUser.eno}">
	<input type="hidden" id = "ename" name="ename" value="${loginUser.ename}">
	<input type="hidden" id = "rank" name="rank" value="${loginUser.rank}">
	<div class="fullWrap">
	<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false" />
		<h1>게시판 지정및 게시글 작성</h1>
		<div class="wjdduf">
			<div class="wjddu">
				<div class="rptlvks">
					<div>
						<h2>게시판</h2>
					</div>
					<div>
						<select class="tpqn1" name="searchtype">
							<option value="1">부서</option>
							<option value="2">취미</option>
							<option value="3">자유</option>
						</select>
					</div>
					<h3>
						<input type="checkbox" id="jb-input-checkbox" name="empty">
						<label for="jb-input-checkbox">공지로 지정</label>
					</h3>
				</div>
				<div class="wpahr">
					<div>
						<h2>제 목</h2>
					</div>
					<div class="wpahrdlqfur">
						<input type="text" name="w_title" placeholder="제목을 입력해주세요">
					</div>
				</div>

				<div class="cjaqnvkdlf">
					<div>
						<h2>첨부파일</h2>
					  </div>
					<!-- <div class="rksrur"> -->
						<input type="file" id="file" multiple>
						 <!-- <label for="file">파일</label> -->
					<!-- </div> -->
				</div>

				<div class="logo">
					<a href="${contextPath}/Main01/indexMain.jsp"><img src="${contextPath}/Board01/img/logo3.gif"></a>
				</div>

				<div class="zmrl">
					<textarea class="noresize" name="contents"
						placeholder="내용을 입력해주세요."></textarea>
				</div>
				<div class="gkeks">
					<div class="rksrur_bottom_left">
						<button class="back">돌아가기</button>

					</div>
					<div class="rksrur_bottom_right">
						<button>
							<a href="#">임시저장</a>
						</button>
						<button class="make" onclick="fn_write();"><p>작성하기</p></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="./js/main.js"></script>
	</form>
</body>

</html>