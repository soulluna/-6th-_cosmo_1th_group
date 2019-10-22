
function validate() {
    var regexpId = /^[0-9]{10}$/ //아이디 정규식
    var regexpPwd = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/ //비밀번호 정규식
    var id = document.getElementById("userID");
    var pwd = document.getElementById("pwd");

    // var log_data = [[2012114562, "zerz2658!"], [2018113843, "godwjd34!@#"]];
    // console.log(typeof(id.value));f
    //----------------------------------------------------
    if (id.value == "") {
        alert("사원번호를 확인하세요");
        id.focus();
        return false;
    }
    if (!check(regexpId, id, "사원번호는 10자리의 숫자만 입력해주세요.")) {
        return false;
    }

    if (pwd.value == "") {
        alert("비밀번호를 입력하세요");
        pwd.focus();
        return false;
    }

    if (!check(regexpPwd, pwd, "비밀번호는 8~15자리의 특수문자 포함 문자열입니다.")) {
        return false;
    }

    return(dbCheck(id,pwd,log_data));



    function check(regexpId, what, message) {
        if (regexpId.test(what.value)) {
            return true;
        }
        alert(message);
        what.value = "";
        what.focus();
        return false;
    }

    // function dbCheck(id,pwd,log_data){
    //     var i=0;
    //     while(true){
    //         if(id.value==log_data[i][0]&&pwd.value==log_data[i][1]){
    //             return true;
    //             break;
    //         }
    //         i++;
    //         if(i>=2) {
    //             alert("사원번호 혹은 비밀번호가 잘못되었습니다.");
    //             break;
    //         }
    //     }
    //     return false;
    // }
}
