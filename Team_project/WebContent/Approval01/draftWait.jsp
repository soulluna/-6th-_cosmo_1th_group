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

    <script src="${contextPath}/Approval01/js/jquery-2.1.1.min.js"></script>
    <script src="${contextPath}/Approval01/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/Approval01/js/jquery.easing.1.3.js"></script>
    <script src="${contextPath}/Approval01/js/prefixfree.min.js"></script>
    <script src="${contextPath}/Approval01/js/main.js"></script>
    <script>

    </script>
    <title>기안서</title>
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
            <option value="./draft.html" selected>기안서</option>
            <option value="./vacation.html">휴가신청서 </option>
        </select>

        <div class="docName">
            <h1>기안서</h1>
        </div>
        <div class="deptContent">
            <div class="signtable">
                <table class="signtableleft">
                    <tr>
                        <th>이름</th>
                        <td>홍길동</td>
                    </tr>
                    <tr>
                        <th>직책</th>
                        <td>사원</td>
                    </tr>
                    <tr>
                        <th>소속</th>
                        <td>경영지원부</td>
                    </tr>
                </table>

                <table class="signtableright" border="1">
                    <tr>
                        <th>사원</th>
                        <th>과장</th>
                        <th>부장</th>
                    </tr>
                    <tr>
                        <td style="vertical-align:top">홍길동<br><span style="color:red;">[승인]</span></td>
                        <td style="vertical-align:top">어피치<br><span style="color:red;">[승인]</span></td>
                        <td style="vertical-align:top">라이언<br><span style="color:red;"></span></td>
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
                            ${approval.txtname}
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <br>
                            내용<br>
                            <div class="inputContent" style="background-color:white;vertical-align:top;">
                           	${approval.txtcont}
                            </div>
                        </td>
                    </tr>
                </table>

                <br>
                <div class="bottomBt">
                        <button type="button" onclick="draftCheck()" disabled>등록</button>
                        <button type="button" onclick="docModify()" disabled>수정</button>
                        <button type="button" onclick="docDelete()" disabled>삭제</button>
                        <button type="button" onclick="docApprov()" disabled>승인</button>
                        <button type="button" onclick="docReturn()" disabled>반려</button>
                        <button type="button" onclick="docCancle()">취소</button>
                    </div>
            </div>
        </div>
    </div>
</body>
</html>