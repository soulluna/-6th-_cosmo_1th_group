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
	<jsp:forward page="login.do"/>
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
    <link rel="stylesheet" href="${contextPath}/Main01/css/calander.css"/>
    <script src="${contextPath}/Main01/js/calander.js"></script>
</head>
<body>
    <form class="fullWrap">
        <jsp:include page="/WEB-INF/GNB/header.jsp" flush="false"/>
        <!--left_side-->
        <div id="l_section">
            <div class="section_menu">달력
                <div class="cal_top">
                    <span id="prevMonth" class="cal_tit"><button id="movePrevMonth" onclick="movePrevMonth();">Prev</button></span>
                    <span id="cal_top_year"></span>
                    <span id="cal_top_month"></span>
                    <span id="nextMonth" class="cal_tit"><button id="moveNextMonth" onclick="moveNextMonth();">Next</button></span>
                </div>
                <div id="cal_tab" class="cal"></div>
            </div>
            <div class="scadule">
                <span class="login"></span>
                <span class="scaduleList"></span>
            </div>
        </div>
        <script type="text/javascript">
            $(document).ready(function () {
                var isLogin = true;
                var scaduleArray = ["주간회의 : 09:00 ~ 10:00"];
                //로그인 되어있는지 여부 체크
                if (isLogin == true) {
                    $("span.login").text("로그인 되었습니다.");
                    $("span.scaduleList").text(scaduleArray);
                }
            });
        </script>
        <!--//left_side-->
        <!--right_side-->
        <div id="r_section">
        <!-- 나중에 전자결재 데이터베이스 완성되면 그 때viewArticles참조해서 전자결재에서 상위 10개만 읽어오기 -->
            <div class="section_menu"><a href="../Team3_cha/noticeBoardMain.html">전체게시판 ></a> </div>
            <div id="board2">
            </div>
            <script>
                $(document).ready(function(){
                    makeBoard();
                });
                function makeBoard() {
                    var setBoradHTML="";
                    setBoradHTML+="<table class='ListTable' border='1'>";
                            setBoradHTML+="<tr><th width='10%'>번호</th><th>문서제목</th><th width='10%'>작성자</th></tr>";
                    for (var i = 0; i < 10; i++) {
                        setBoradHTML+="<tr>";
                        for (var j = 0; j < 3; j++) {
                            setBoradHTML+="<td>" + "tests" + "</td>";
                        }
                        setBoradHTML+="</tr>";
                        $("#board2").html(setBoradHTML);
                    }
                }
            </script>
            <!-- 나중에 게시판 데이터베이스 완성되면 그 때viewArticles참조해서 게시판에서 상위 10개만 읽어오기 -->
            <div class="section_menu"><a href="../Team1_won&you/docList.html">결재현황></a> </div>
            <div id="board3">        
            </div>
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
        <!--//right_side-->
    </form>
</body>
</html>