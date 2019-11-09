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
<title>글쓰기</title>
<link rel="stylesheet" href="./css/rmfwkrtjd.css">
<link rel="stylesheet" href="./css/gnb.css" />
<script src="./js/jquery-2.1.1.min.js"></script>
<script src="./js/jquery.easing.1.3.js"></script>
<script src="./js/jquery-ui.min.js"></script>
<script src="./js/prefixfree.min.js"></script>

<script>
	function fn_write() {
		frmArticle.action = "${contextPath}/Board/write.do";
		frmArticle.submit();
	}
</script>
</head>

<body>
	<div class="fullWrap">
		<!--gnb-->
		<div class="gnb">
			<!--logoBar-->
			<ul class="logobar">
				<li id="mainLogo"><a href="${contextPath}/Main01/indexMain.html"><img
						src="./img/logo3.gif"></a></li>
				<table id="memberinfo">
					<tbody>
						<tr>
							<td id="profile_img" rowspan="2"><img
								src="http://placehold.it/70x70"></td>
							<td colspan="2">관리자 님 환영합니다.</td>
						</tr>
						<tr>
							<td><a href="../index.html">로그아웃</a> <a
								href="${contextPath}/Main01/member/confirm.html">내정보수정</a></td>
						</tr>
					</tbody>
				</table>
			</ul>
			<!--//logoBar-->
			<!--nav bar-->
			<ul class="topBar">
				<li id="main" class="t_menu btn3"><a
					href="${contextPath}/Main01/indexMain.jsp">메인</a></li>
				<li id="system" class="t_menu btn1"><a
					href="${contextPath}/Approval01/docList.jsp">전자결재시스템</a></li>
				<li id="board" class="t_menu btn2"><a
					href="${contextPath}/Board01/noticeBoardMain.jsp">게시판</a></li>
				<li id="info_tab" class="t_menu btn4"><a
					href="${contextPath}/Main01/member/confirm.jsp">내정보수정</a></li>
			</ul>
			<!--//navBar-->
		</div>
		<!--//gnb-->
		<h1>게시판 지정및 게시글 작성</h1>
		<div class="wjdduf">
			<div class="wjddu">
				<div class="rptlvks">
					<div>
						<h2>게시판</h2>
					</div>
					<div>
						<select class="tpqn1" name="searchtype">
							<option value="부서">부서</option>
							<option value="취미">취미</option>
							<option value="자유">자유</option>
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
						<input type="text" name="w_title" placeholder="제목을 입력해주세요">${article.txtname}
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
					<a herf="MainPage.html"><img src="${contextPath}/Board01/img/logo3.gif"></a>
				</div>

				<div class="zmrl">
					<textarea class="noresize" name="contents"
						placeholder="내용을 입력해주세요.">${article.txtcont}</textarea>
				</div>
				<div class="gkeks">
					<div class="rksrur_bottom_left">
						<button class="back">돌아가기</button>

					</div>
					<div class="rksrur_bottom_right">
						<button>
							<a href="#">임시저장</a>
						</button>
						<button class="make"
							onclick="location.href='${contextPath}/Board01/free.jsp'">작성하기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="./js/main.js"></script>
</body>

</html>