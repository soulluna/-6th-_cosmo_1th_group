<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
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
    <link rel="stylesheet" href="./css/main.css">
    <link rel="stylesheet" href="./css/gnb.css" />
    <script src="./js/jquery-2.1.1.min.js"></script>
    <script src="./js/jquery.easing.1.3.js"></script>
    <script src="./js/jquery-ui.min.js"></script>
    <script src="./js/prefixfree.min.js"></script>
</head>

<body>
    <div class="fullWrap">
        <!--gnb-->
        <div class="gnb">
            <!--logoBar-->
            <ul class="logobar">
                <li id="mainLogo"><a href="../Team2_kim/indexMain.html"><img src="./img/logo3.gif"></a></li>
                <table id="memberinfo">
                    <tbody>
                        <tr>
                            <td id="profile_img" rowspan="2"><img src="http://placehold.it/70x70"></td>
                            <td colspan="2">관리자 님 환영합니다.</td>
                        </tr>
                        <tr>
                            <td><a href="../index.html">로그아웃</a>
                                <a href="../Team2_kim/member/confirm.html">내정보수정</a></td>
                        </tr>
                    </tbody>
                </table>
            </ul>
            <!--//logoBar-->
            <!--nav bar-->
            <ul class="topBar">
                <li id="main" class="t_menu btn3"> <a href="../Team2_kim/indexMain.html">메인</a></li>
                <li id="system" class="t_menu btn1"> <a href="../Team1_won&you/docList.html">전자결재시스템</a></li>
                <li id="board" class="t_menu btn2"> <a href="./noticeBoardMain.html">게시판</a></li>
                <li id="info_tab" class="t_menu btn4"> <a href="../Team2_kim/member/confirm.html">내정보수정</a></li>
            </ul>
            <!--//navBar-->
        </div>
        <!--//gnb-->
        <h1>6기 1조 프로젝트 전체 게시판</h1>
        <div class="side">
            <a href="./noticeBoardMain.html">
                <div>전체</div>
            </a>
            <a href="./department.html">
                <div>부서</div>
            </a>
            <a href="./hobby.html">
                <div>취미</div>
            </a>
            <a href="./free.html">
                <div>자유</div>
            </a>
        </div>
	<c:choose>
			<c:when test="${articlesList==null}">
				<tr>
					<td colspan="4">
						<p align="center">
							<b> <span>게시글이 존재하지 않습니다.</span>
							</b>
						</p>
					</td>
				</tr>
			</c:when>			
	</c:choose>
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
                     <tr>
                            <td class="co1">부서</td>
                            <td class="co2">공지</td>
                            <td class="co3"><a href="./details.html">
                                    <p>가나다라마바사아자차카타파하abcdefghijklmnopqrstuvwxyz</p>
                                </a></td>
                            <td class="co4">팀장</td>
                            <td class="co5">19.08.05</td>
                            <td class="co6">123</td>
                            <td class="co7">132</td>
                        </tr> 
						<c:forEach var="board" items="${boardList}">
							<tr class="record">
								<td>${board.detailName}</td>
								<td>${board.num}</td>
								<td>${board.title}</td>
								<td>${board.id}</td>
								<td>${board.writeDate}</td>
								<td>${board.count}</td>
								<td>${board.goods}</td>
								
							</tr>
						</c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="dlehd">
                <div class="page">
                    <a href="#">이전</a>
                    <a href="#">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#">4</a>
                    <a href="#">5</a>
                    <a href="#">다음</a>
                </div>
               
               
               
                <div class="wkrtjd"><a onclick="location.href='${contextPath}/Board01/write.jsp'">글쓰기</a></div>
            </div>

            <div class="gkeks">
                <select class="tpqn" name="searchType">
                    <option value="제목">제목</option>
                    <option value="제목+내용">제목+내용</option>
                    <option value="작성자">작성자</option>
                </select>
                <div class="rjator">
                    <input type="text" name="searchKey" placeholder="검색어 입력">
                    <button class="search"><a href="#">검색</button></a>
                </div>
            </div>
        </div>
    </div>
    <script src="./js/main.js"></script>
</body>

</html>