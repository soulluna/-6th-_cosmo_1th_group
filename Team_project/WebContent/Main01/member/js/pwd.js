function validate(){
    var regexpPwd = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/ //비밀번호 정규식
    var pwd=document.getElementById("pwd");
    var chg_pwd = document.getElementById("chg_pwd");
    var loginPwd = document.getElementById("loginedPwd")

    //----------------------------------------------------
    if(pwd.value=="") {
       alert("현재 비밀번호를 입력해주세요");
       pwd.focus();
       return false;
   }
    if(pwd.value!=loginPwd.value){
    	alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
    	pwd.value="";
    	pwd.focus();
    	return false;
    }
    if(!check(regexpPwd,chg_pwd,"변경할 비밀번호는 8~15자리의 특수문자 포함 문자열입니다.")){
        chg_pwd.focus();
        return false;
    }
    if(chg_pwd.value=="") {
       alert("변경할 비밀번호를 입력해주세요");
       chg_pwd.focus();
       return false;
   }
    if(chg_pwd.value != chg_pwd_con.value){
        alert("비밀번호가 다릅니다. 다시 확인해주세요.");
        chg_pwd_con.value = "";
        chg_pwd_con.focus();
        return false;
    }
    if(pwd.value==chg_pwd.value){
    	alert("현재 비밀번호와 같습니다. 다른 비밀번호를 입력해주세요.");
    	chg_pwd.value="";
    	chg_pwd_con.value="";
    	chg_pwd.focus();
    	return false;
    }
    alert("비밀번호가 변경되었습니다.");
    return true;
}

function cancel(){
    var check=confirm("취소시 수정사항은 반영되지 않습니다.");
    if(check){
    	location.href = "pwdConfirm.do?checked=checked";
    }
    else{
    	return false;
    }
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