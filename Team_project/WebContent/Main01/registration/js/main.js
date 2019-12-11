function idok(eno){
	opener.frm.eno.value = document.join.eno.value;
	opener.frm.reid.value = document.join.eno.value;
	self.close();
}

function enoCheck(url){
	var id = document.getElementById("userID");
	var regexpId = /^[0-9]{10}$/ //사번 정규식
		if(document.join.eno.value==""){
			alert("사원번호를 입력하세요");
			document.join.eno.focus();
			return;
		}
	if(!check(regexpId,userID,"사원번호는 10자리의 숫자만 입력해주세요")){
		return false;
	}
	var nextPage = url+"/Main/enoCheckForm.do";
	window.open(nextPage, "_blank_1","toolbar=no, scrollbars=yes, menubar=no, resizeable=no, width=450, height=200");

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



function validate(){
	var regexpId = /^[0-9]{10}$/ //아이디 정규식
	var regexpPwd = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/ //비밀번호 정규식
	var id = document.getElementById("userID");
	var pwd = document.getElementById("pwd");
	var dep1 = document.getElementById("dep1");
	var dep2 = document.getElementById("dep2");
	//----------------------------------------------------

	if(!check(regexpId,id,"사원번호는 10자리의 숫자만 입력해주세요")){
		return false;
	}

	if(join.userName.value=="") {
		alert("이름을 입력해 주세요");
		join.userName.focus();
		return false;
	}

	if(join.pwd.value==""){
		alert("비밀번호를 입력하세요");
		join.pwd.focus();
		return false;
	}

	if(!check(regexpPwd,pwd,"비밀번호는 8~15자리의 특수문자 포함 문자열입니다")){
		return false;
	}

	if(join.pwdconfirm.value==""){
		alert("비밀번호확인을 입력하세요");
		join.pwd.focus();
		return false;
	}

	if(join.pwd.value != join.pwdconfirm.value){
		alert("비밀번호가 다릅니다. 다시 확인해주세요");
		join.pwdconfirm.value = "";
		join.pwdconfirm.focus();
		return false;
	}

	if(dep1.value=="0"||dep2.value=="0"){
		alert("부서를 선택하지 않았습니다. 확인해 주세요");
		return false;
	}

	alert(join.userName.value+"님 회원가입을 축하합니다");

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

function backPage(){
	if(confirm("취소시 로그인 페이지로 돌아갑니다. 정말 취소하시겠습니까?")){
		location.href="index.jsp";
	}else{
		return false;
	}	
}



