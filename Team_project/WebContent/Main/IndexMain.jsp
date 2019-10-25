<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>index</title>
    <script src="${contextPath}/js/jquery-2.1.1.min.js"></script>
    <script src="${contextPath}/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/js/jquery.easing.1.3.js"></script>
    <script src="${contextPath}/js/prefixfree.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/css/index.css" />
    <link rel="stylesheet" href="${contextPath}/css/gnb.css" />
    <link rel="stylesheet" href="${contextPath}/css/calander.css">
    <script src="${contextPath}/js/calander.js"></script>



</head>

<body>
    <div class="fullWrap">
        <!--gnb-->
        <div class="gnb">
            <!--logoBar-->
            <ul class="logobar">
                <li id="mainLogo"><a href="./indexMain.html"><img src="./img/logo3.gif"></a></li>
                <table id="memberinfo">
                    <tbody>
                        <tr>
                            <td id="profile_img" rowspan="2"><img src="http://placehold.it/70x70"></td>
                            <td colspan="2">관리자 님 환영합니다.</td>
                        </tr>
                        <tr>
                            <td><a href="../index.html">로그아웃</a>
                                <a href="./member/confirm.html">내정보수정</a></td>
                        </tr>
                    </tbody>
                </table>
            </ul>
            <!--//logoBar-->
            <!--nav bar-->
            <ul class="topBar">
                <li id="main" class="t_menu btn3"> <a href="./indexMain.html">메인</a></li>
                <li id="cal" class="t_menu btn5"> <a href="${contextPath}/Board/listBoard.do">일정표</a></li>
                <li id="system" class="t_menu btn1"> <a href="../Team1_won&you/docList.html">전자결재시스템</a></li>
                <li id="board" class="t_menu btn2"> <a href="../Team3_cha/noticeBoardMain.html">게시판</a></li>

                <li id="info_tab" class="t_menu btn4"> <a href="./member/confirm.html">내정보수정</a></li>
            </ul>
            <!--//navBar-->
        </div>
        <!--//gnb-->
        <!--left_side-->
        <div id="l_section">
            <div class="section_menu">달력
                <div class="cal_top">
                    <a href="#" id="movePrevMonth"><span id="prevMonth" class="cal_tit"><button>Prev</button></span></a>
                    <span id="cal_top_year"></span>
                    <span id="cal_top_month"></span>
                    <a href="#" id="moveNextMonth"><span id="nextMonth" class="cal_tit"><button>Next</button></span></a>
                </div>
                <div id="cal_tab" class="cal"></div>
            </div>
            <div class="scadule">
                <span class="login"></span>
                <span class="scaduleList"></span>
            </div>
        </div>
        <script type="text/javascript">

            var today = new Date();
            var year = null;
            var month = null;
            var firstDay = null;
            var lastDay = null;
            var $tdDay = null;
            var $tdSche = null;

            $(document).ready(function () {
                drawCalendar();            
                initDate();
                drawDays();
                $("#movePrevMonth").on("click", function () { movePrevMonth(); });
                $("#moveNextMonth").on("click", function () { moveNextMonth(); });
            });

            //calendar 그리기
            function drawCalendar() {
                var setTableHTML = "";
                setTableHTML += '<table class="calendar">';
                setTableHTML += '<tr><th>SUN</th><th>MON</th><th>TUE</th><th>WED</th><th>THU</th><th>FRI</th><th>SAT</th></tr>';
                for (var i = 0; i < 6; i++) {
                    setTableHTML += '<tr height="50">';
                    for (var j = 0; j < 7; j++) {
                        setTableHTML += '<td style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap">';
                        setTableHTML += '<div class="cal-day"></div>';
                        setTableHTML += '<div class="cal-schedule"></div>';
                        setTableHTML += '</td>';
                    }
                    setTableHTML += '</tr>';
                }
                setTableHTML += '</table>';
                $("#cal_tab").html(setTableHTML);
            }

            //날짜 초기화
            function initDate() {
                $tdDay = $("td div.cal-day");
                $tdSche = $("td div.cal-schedule");
                dayCount = 0;
                year = today.getFullYear();
                month = today.getMonth() + 1;
                myDate = today.getDate();
                firstDay = new Date(year, month - 1, 1);
                lastDay = new Date(year, month, 0);
                console.log(myDate);
                console.log(today.getMonth()+1);
            }
            //calendar 날짜표시
            function drawDays() {
                $("#cal_top_year").text(year);
                $("#cal_top_month").text(month);
                for (var i = firstDay.getDay(); i < firstDay.getDay() + lastDay.getDate(); i++) {
                    $tdDay.eq(i).text(++dayCount);
                    // console.log(dayCount);
                    // console.log(month);
                    if (myDate == dayCount&&month==today.getMonth()+1) {
                        $tdDay.eq(i).parent().css("background-color", "#FAF58C");
                    }
                    else{
                        $tdDay.eq(i).parent().css("background-color", "#f1f1f1");
                    }
                }
                for (var i = 0; i < 42; i += 7) {
                    $tdDay.eq(i).css("color", "red");
                }
                for (var i = 6; i < 42; i += 7) {
                    $tdDay.eq(i).css("color", "blue");
                }
            }

            //calendar 월 이동
            function movePrevMonth() {
                month--;
                if (month <= 0) {
                    month = 12;
                    year--;
                }
                if (month < 10) {
                    month = String("0" + month);
                }
                getNewInfo();
            }

            function moveNextMonth() {
                month++;
                if (month > 12) {
                    month = 1;
                    year++;
                }
                if (month < 10) {
                    month = String("0" + month);
                }
                getNewInfo();
            }


            function getNewInfo() {
                for (var i = 0; i < 42; i++) {
                    $tdDay.eq(i).text("");
                }
                dayCount = 0;
                firstDay = new Date(year, month - 1, 1);
                lastDay = new Date(year, month, 0);
                drawDays();
            }
        </script>
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
    </div>

</body>

</html>