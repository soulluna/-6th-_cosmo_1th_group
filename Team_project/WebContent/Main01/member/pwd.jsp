<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<c:if test='${result!=null}'>
	<script>
		window.onload = function(){
			alert("비밀번호 수정에 실패했습니다. 다시 입력해주세요.");
		}
	</script>
</c:if>
<c:if test="${empty loginUser}">
	<jsp:forward page="login.do"/>
</c:if>
<html>
<head>
<meta charset="EUC-KR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>비밀번호 수정</title>
    <script src="${contextPath}/Main01/member/js/jquery-2.1.1.min.js"></script>
    <script src="${contextPath}/Main01/member/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/Main01/member/js/jquery.easing.1.3.js"></script>
    <script src="${contextPath}/Main01/member/js/prefixfree.min.js"></script>
    <script src="${contextPath}/Main01/member/js/pwd.js"></script>
    <link rel="stylesheet" href="${contextPath}/Main01/member/css/pwd.css" />
    <link rel="stylesheet" href="${contextPath}/Main01/css/gnb.css" />
</head>
<body>
        <form action="pwdChange.do" method="post" onsubmit="return validate();">
            <div class="fullWrap">
                <jsp:include page="/WEB-INF/GNB/header.jsp" flush="false"/>
                <!--top_side-->
                <div id="top_section">
                    <div class="section_menu">비밀번호 변경</div>
                    <table id="info_table">
						<tr><td align="right">현재 비밀번호 : </td><td><input type="password" id="pwd"></td></tr>
                        <tr><td align="right">변경할 비밀번호 : </td><td><input type="password" id="chg_pwd" name="changePwd" value="${changePwd}"></td></tr>
                        <tr><td align="right">비밀번호 확인 : </td><td><input type="password" id="chg_pwd_con"></td></tr>
                    </table>
                </div>
                <!--//top_side--> 
                <input type="hidden" name="eno" value="${eno}">
                <div id="button_section">
                    <input type="submit" value="확인" class="e_btn">
                    <input type="button" value="취소" class="e_btn" onclick="cancel();">
                </div>
            </div>
            <!--//fullwarp-->
        </form>
</body>

</html>