function showImagePreview(input) {
    if (input.files && input.files[0]) {
        var filerdr = new FileReader();
        filerdr.onload = function (e) {
            $('#imgPreview').attr('src', e.target.result);
        }
        filerdr.readAsDataURL(input.files[0]);
    }
}

function validate() {
    var korName = document.getElementById("userName");
    var engName = document.getElementById("engName");
    var phone = document.getElementById("phone")
    var email = document.getElementById("email");
    var dep1 = document.getElementById("dep1");
    var dep2 = document.getElementById("dep2");
    var regexpEngName = /[a-zA-Z]/;
    var regexpKorName = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
    var regexpEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    var regexpPhone = /^\d{11}$/;


    // ------------------------------------------------------


    if (korName.value == "") {
        alert("이름을 입력해 주세요");
        korName.focus();
        return false;
    }

    if (!check(regexpKorName, korName, "한국어만 입력해주세요.")) {
        return false;
    }

    if (engName.value == "") {
        alert("영문이름을 입력해 주세요");
        engName.focus();
        return false;
    }

    if (!check(regexpEngName, engName, "영어만 입력해주세요.")) {
        return false;
    }

    if (dep1.value == "0" || dep2.value == "0") {
        alert("부서를 선택하지 않았습니다. 확인해 주세요.");
        return false;
    }

    if (phone.value == "") {
        alert("연락처를 입력해 주세요");
        phone.focus();
        return false;
    }

    if (!check(regexpPhone, phone, "연락처 형식이 맞지 않습니다.")) {
        phone.focus();
        return false;
    }

    if (email.value == "") {
        alert("이메일을 입력해주세요.");
        email.focus();
        return false;
    }

    if (!check(regexpEmail, email, "이메일 형식이 맞지 않습니다.")) {
        email.focus();
        return false;
    }
    alert("내 정보가 변경되었습니다.");
}

function cancel() {
    var check=confirm("취소시 수정사항은 반영되지 않습니다. 정말 취소하시겠습니까?");
    if(check){
        location.href = './select.html'
        return false;
    }    
}

function check(regexpId, what, message) {
    if (regexpId.test(what.value)) {
        return true;
    }
    alert(message);
    what.value = "";
    what.focus();
    //return false;
}