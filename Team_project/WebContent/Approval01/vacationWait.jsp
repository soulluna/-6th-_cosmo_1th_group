<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="${contextPath}/Approval01/css/main.css" />
<link rel="stylesheet" href="${contextPath}/Approval01/css/gnb.css" />
<link rel="stylesheet" href="${contextPath}/Approval01/css/jquery-ui.css" />


<title>휴가신청서</title>
</head>

<body>
    <div class="content">
        <!--gnb-->
        <div class="gnb">
            <!--logoBar-->
            <ul class="logobar">
                <li id="mainLogo"><a href="../Team2_kim/indexMain.html"><img src="../img/logo3.gif"></a></li>
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
                <li id="system" class="t_menu btn1"> <a href="./docList.html">전자결재시스템</a></li>
                <li id="board" class="t_menu btn2"> <a href="../Team3_cha/noticeBoardMain.html">게시판</a></li>
                <li id="info_tab" class="t_menu btn4"> <a href="../Team2_kim/member/confirm.html">내정보수정</a></li>
            </ul>
            <!--//navBar-->
        </div>
        <!--//gnb-->
        <select style="visibility: hidden;" class="docSelecter" onchange="if(this.value) location.href=(this.value)">
            <option value="./draft.html">기안서</option>
            <option value="./vacation.html" selected>휴가신청서 </option>

        </select>

        <div class="docName">
            <h1>휴가 신청서</h1>
        </div>
        <div class="deptContent">
            <div class="signtable">
                <table class="signtableleft">
                    <tr>
                        <th>이름</th>
                        <td></td>
                    </tr>
                    <tr>
                        <th>직책</th>
                        <td></td>
                    </tr>
                    <tr>
                        <th>소속</th>
                        <td></td>
                    </tr>
                </table>

                <table class="signtableright" border="1">
                    <tr>
                        <th>사원</th>
                        <th>과장</th>
                        <th>부장</th>
                    </tr>
                    <tr>
                        <td style="vertical-align:top"><span class="approval1"></span><br><span style="color:red;">[승인]</span></td>
                        <td style="vertical-align:top"><span class="approval2"></span><br><span style="color:red;"></span></td>
                        <td style="vertical-align:top"><span class="approval3"></span><br><span style="color:red;"></span></td>
                    </tr>
                    <tr>
                        <td class="createdDayInput1"></td>
                        <td class="createdDayInput2"></td>
                        <td class="createdDayInput3"></td>
                    </tr>
                </table>
            </div>

            <div class="inputarea">
                <table>
                    <tr>
                        <td>
                           	 제목<br>
                            <div class="inputTitle" style="background-color:white;">
                                ${approval.txtname}</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <br>
                            
                            1.다음 중 요청하고자 하는 휴가의 종류로 알맞은 것을 고르세요.<br>
                            <span>
                                <input type="radio" name="leaveradio" value="1" class="modifySelect1" id="kindsSelect1"
                                	disabled>
                                <label for="kindsSelect1">연차</label>
                            </span>
                            <span>
                                <input type="radio" name="leaveradio" value="2" class="modifySelect2" id="kindsSelect2"
                                    disabled>
                                <label for="kindsSelect2">병가</label>
                            </span>
                            <span>
                                <input type="radio" name="leaveradio" value="3" class="modifySelect3" id="kindsSelect3"
                                    disabled>
                                <label for="kindsSelect3">휴가</label>
                            </span>
                            <span>
                                <input type="radio" name="leaveradio" value="4" class="modifySelect4" id="kindsSelect4"
                                    disabled>
                                <label for="kindsSelect4">기타</label>
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <br>
                            1-2. 요청한 휴가의 기간을 입력하세요.<br>
                            <span class="selectedDay">${approval.vacstart}</span> ~ <span class="selectedDay">${approval.vacend}</span>
                            (<span class="selectedDayCount"></span> 일 간)
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <br>
                            2. 1번 보기를 선택한 사유를 쓰세요.<br>
                            <div class="inputContent" style="background-color:white;vertical-align:top;">
                            ${approval.txtcont}
                            </div>
                        </td>
                    </tr>
                </table>
                <br>
                <div class="bottomBt">
                    <button type="button" onclick="draftCheck()" disabled>등록</button>
                    <button type="button" onclick="docModify()">수정</button>
                    <button type="button" onclick="docDelete()">삭제</button>
                    <button type="button" onclick="docApprov()" disabled>승인</button>
                    <button type="button" onclick="docReturn()" disabled>반려</button>
                    <button type="button" onclick="docCancle()">취소</button>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="${contextPath}/Approval01/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/Approval01/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextPath}/Approval01/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="${contextPath}/Approval01/js/prefixfree.min.js"></script>
<script>
	var vaclist = "${approval.vaclist}";
	if (vaclist == "연차") {
		$(".modifySelect1").attr("checked", "checked");
	} else if (vaclist == "병가") {
		$(".modifySelect2").attr("checked", "checked");
	} else if (vaclist == "휴가") {
		$(".modifySelect3").attr("checked", "checked");
	} else if (vaclist == "기타") {
		$(".modifySelect4").attr("checked", "checked");
	}
	
	function getDateDiff(date1, date2) {
		var arrDate1 = date1.split("-");
		var getDate1 = new Date(parseInt(arrDate1[0]), parseInt(arrDate1[1]) - 1,
				parseInt(arrDate1[2]));
		var arrDate2 = date2.split("-");
		var getDate2 = new Date(parseInt(arrDate2[0]), parseInt(arrDate2[1]) - 1,
				parseInt(arrDate2[2]));
		var getDiffTime = getDate1.getTime() - getDate2.getTime();
		return Math.floor(getDiffTime / (1000 * 60 * 60 * 24));
	}
	$('.selectedDayCount').text(getDateDiff("${approval.vacend}","${approval.vacstart}") +1);
</script>
<script type="text/javascript" src="${contextPath}/Approval01/js/ex_main.js"></script>
</html>