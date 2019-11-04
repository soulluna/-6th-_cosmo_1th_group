<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<script src="./js/jquery-2.1.1.min.js"></script>
<script src="./js/jquery-ui.min.js"></script>
<script src="./js/jquery.easing.1.3.js"></script>
<script src="./js/prefixfree.min.js"></script>
<link rel="stylesheet" href="./css/enoCheck.css" />

<script>
function enoCheck(){
	var id = document.getElementById("userID");
	var regexpId = /^[0-9]{10}$/ //사번 정규식
		if(document.frm.eno.value==""){
			alert("사원번호를 입력하세요");
			document.join.eno.focus();
			return;
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
var eno = document.frm.eno.value;
function idok(eno){
	opener.frm.eno.value = document.join.eno.value;
	opener.frm.reid.value = document.join.eno.value;
	self.close();
}
</script>
<title>중복확인</title>
</head>
<body>


	<form action="enoCheck.do" method="get" name="frm" class="frm">
		사원번호 <input type="text" name="eno" value="${eno}" id="userID"> <input
			class="chkId" type="submit" value="중복확인" onclick="enoCheck()"><br>


		<c:if test="${result==1}">
			<script>
				opener.frm.eno.value = "";
			</script>
	    사원번호를 확인해주세요.<br>
		</c:if>

		<c:if test="${result==-1}">
		${eno}는 사용한 가능한 사원번호입니다.
		<input type="button" value="사용" onclick="idok();" class="chkId">
		</c:if>
	</form>
</body>
</html>