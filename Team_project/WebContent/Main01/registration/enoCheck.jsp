<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<script src="${contextPath}/Main01/registration//js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/Main01/registration//js/jquery-ui.min.js"></script>
<script src="${contextPath}/Main01/registration//js/jquery.easing.1.3.js"></script>
<script src="${contextPath}/Main01/registration//js/prefixfree.min.js"></script>
<link rel="stylesheet" href="${contextPath}/Main01/registration/css/enoCheck.css" />
<script>
function enoCheck(){
	var id = document.getElementById("userID");
	var regexpId = /^[0-9]{10}$/ //사번 정규식
		if(document.frm.eno.value==""){
			alert("사원번호를 입력하세요");
			document.join.eno.focus();
			return false;
		}
	if(!check(regexpId,userID,"사원번호는 10자리의 숫자만 입력해주세요")){
		return false;
	}
	function check(regexpId, what, message) {
		if(regexpId.test(what.value)) {
			return true;
		}
		alert(message);
		what.value = "";
		what.focus();
		//return false;
	}

}
function idok(eno){
	opener.join.eno.value = document.frm.eno.value;
	opener.join.reid.value = document.frm.eno.value;
	self.close();
}
</script>
<title>중복확인</title>
</head>
<body>
	<form action="${contextPath}/Main/enoCheck.do" method="get" name="frm" class="frm">
		사원번호 <input type="text" name="eno" value="${eno}" id="userID"> 
		<input class="chkId" type="submit" value="중복확인" onclick="return enoCheck();">
		<br>
		<script>
			document.frm.userID.value=opener.document.join.eno.value;
		</script>
		<c:if test="${result==1}">
			<script>
				opener.document.join.eno.value=""; 
			</script>
	    사원번호를 확인해주세요.<br>
		</c:if>
		<c:if test="${result==-1}">
		${eno}는 사용한 가능한 사원번호입니다.
		<script>
			document.frm.userID.value=${eno};
		</script>
		<input type="button" value="사용" onclick="idok('${eno}');" class="chkId">
		</c:if>
	</form>
</body>
</html>