function validate(){
    var regexpId = /^[0-9]{10}$/ //아이디 정규식
    var regexpPwd = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/ //비밀번호 정규식
    var id = document.getElementById("userID");
    var pwd = document.getElementById("pwd");
    var dep1 = document.getElementById("dep1");
    var dep2 = document.getElementById("dep2");
    //----------------------------------------------------
    if(!check(regexpId,id,"사원번호는 10자리의 숫자만 입력해주세요.")){
        join.userID.focus();
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
    if(!check(regexpPwd,pwd,"비밀번호는 8~15자리의 특수문자 포함 문자열입니다.")){
        return false;
    }
    if(join.pwdconfirm.value==""){
        alert("비밀번호확인을 입력하세요");
        join.pwd.focus();
        return false;
    }

    if(join.pwd.value != join.pwdconfirm.value){
        alert("비밀번호가 다릅니다. 다시 확인해주세요.");
        join.pwdconfirm.value = "";
        join.pwdconfirm.focus();
        return false;
    }
   if(dep1.value=="0"||dep2.value=="0"){
        alert("부서를 선택하지 않았습니다. 확인해 주세요.");
        return false;
    }

    // if(document.join.reid.value.length == 0){
	// 	alert("중복체크를 하지 않았습니다.");
	// 	document.join.userID.focus();
	// 	return false;
    // }
    
   alert(join.userName.value+"님 회원가입을 축하합니다.");
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
	if(confirm("취소시 로그인 페이지로 돌아갑니다. 정말 취소하시겠습니까?")==true){
		location.href="../../index.html";
	}else{
		return false;
	}	
}

//중복확인
function idCheck(){
    var regexpId = /^[0-9]{10}$/ //아이디 정규식
    var id = document.getElementById("userID");
    function check(regexpId, what, message) {
        if(regexpId.test(what.value)) {
            return true;
        }
        alert(message);
        what.value = "";
        what.focus();
   //return false;
    }
	if(!check(regexpId,id,"사원번호는 10자리의 숫자만 입력해주세요.")){
		document.join.userID.focus();
		return;
    }
    // var url = "idCheck.do?userid=" + document.join.userID.value;
    // 	window.open(url, "_blank_1","toolbar=no, scrollbars=yes, menubar=no, resizeable=no, width=300, height=100");
    var url = "./enoCheck.html";
		window.open(url, "_blank_1","toolbar=no, scrollbars=yes, menubar=no, resizeable=no, width=400, height=300");
}

function idok(){
	opener.join.userID.value = document.frm.userid.value;
	window.close();
}

function dbCheck(){
    $("#okButton").hide();
    var regexpId = /^[0-9]{10}$/; //아이디 정규식
    var enoList = [2012114562, 2018113843];
    var checkId = document.getElementById("userid");
    console.log(checkId.value);
    if(!check(regexpId,checkId,"사원번호는 10자리의 숫자만 입력해주세요.")){
        frm.userid.focus();
        return false;
    }
    for(var i=0;i<enoList.length;i++){
        if(checkId.value==enoList[i]){
            $("#okText").text("이미 사용중인 사원번호입니다.");
            $("#okButton").hide();
            checkId.value=enoList[i];
            return false;
        }
    }
    $("#okText").text("사용 가능한 사원번호입니다.");
    $("#okButton").show();
    return true;

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