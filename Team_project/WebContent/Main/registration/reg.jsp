<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
   request.setCharacterEncoding("UTF-8");
   response.setContentType("text/html;utf-8");
%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>regist</title>
    <script src="./js/jquery-2.1.1.min.js"></script>
    <script src="./js/jquery-ui.min.js"></script>
    <script src="./js/jquery.easing.1.3.js"></script>
    <script src="./js/prefixfree.min.js"></script>
    <script src="./js/main.js"></script>
    <link rel="stylesheet" href="./css/reg.css" />
</head>

<body>
    <form class="reg-form" name="join" onsubmit="return validate();" action="../../index.jsp" method="post">
        <div><img src="./img/logo3.gif"></div>
        <div class="secb">
            <input class="txtb" type="text" id="userID" name="eno">
            <span class="name" data-placeholder="사원번호"></span>
            <input class="d_btn" type="button" value="중복확인">
        </div>
        <div class="secb">
            <input class="txtb" type="text" id="userName" name="ename">
            <span class="name" data-placeholder="이름"></span>

        </div>
        <div class="secb">
            <input id="pwd" class="txtb" type="password" name="userpw">
            <span class="name" data-placeholder="비밀번호"></span>
        </div>

        <div class="secb">
            <input id="pwdconfirm" class="txtb" type="password" name="userpwc">
            <span class="name" data-placeholder="비밀번호 확인"></span>
        </div>

        <div class="a_section">
            <select class="area" id="dep1" name="dname1">
                <option value="0">선택하세요</option>
                <option value="1">영업부</option>
                <option value="2">인사부</option>
                <option value="3">기술지원팀</option>
            </select>
            <span class="block"> &nbsp;>&nbsp;</span>
            <select class="area2" id="dep2" name="dname2">
                <option value="0">선택하세요</option>
                <option value="4">1팀</option>
                <option value="5">2팀</option>
                <option value="6">3팀</option>
            </select>
        </div>

        <input class="reg_submit" type="submit" value="등록">
        <input class="clear" type="reset" value="취소" onclick="location.href ='../../index.jsp';">
    </form>


</body>
<script type="text/javascript">
    $(".secb .txtb").on("focus", function () {
        $(this).addClass("focus");
    });

    $(".secb .txtb").on("blur", function () {
        if ($(this).val() == "")
            $(this).removeClass("focus");
    });
    </script>
    
<script>
$("#userID").blur(function(){
    var id=$(this).val();
    console.log(id);
    var regexpId = /^[0-9]{10}$/ //아이디 정규식
    if(id==""){
        alert("사원번호를 입력해주세요");
        $(this).attr("tabindex",1);
    } 
    else if(!id.match(regexpId)){
        alert("사원번호는 10자리의 숫자로 입력해주세요");
        $(this).attr("tabindex",1);
    }
    else{
        $(this).attr("tabindex",-1);
    }
});
// 이름
$("#userName").blur(function(){
    var name=$(this).val();
    console.log(name);
    if(!name){
        alert("이름을 입력해 주세요.");
        $(this).attr("tabindex",1);
    }
    else{
        $(this).attr("tabindex",-1);
    }
});
// 비밀번호
$("#pwd").blur(function(){
    var pwd=$(this).val();
    console.log(pwd);
    var regexpPwd = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/ //비밀번호 정규식
    if(pwd==""){
        alert("비밀번호를 입력해주세요");
        $(this).attr("tabindex",1);
    } 
    else if(!pwd.match(regexpPwd)){
        alert("비밀번호는 8~15자리의 특수문자 포함 문자열입니다.");
        $(this).attr("tabindex",1);
    }
    else{
        $(this).attr("tabindex",-1);
    }
});
// 비밀번호 확인
$("#pwdconfirm").blur(function(){
    var pwd=$("#pwd").val();
    var pwdconfirm=$(this).val();
    if(pwd!=pwdconfirm){
        alert("비밀번호가 다릅니다. 다시 확인해주세요.");
        $(this).attr("tabindex",1);
    }
    else{
        $(this).attr("tabindex",-1);
    }
});
// 부서 선택
$("#dep1").change(function(){
    var value=$(this).val();
    if(value==0){
        alert("부서를 선택하지 않았습니다.");
    }
});
$("#dep2").change(function(){
    var value2=$(this).val();
    if(value2==0){
        alert("부서를 선택하지 않았습니다.");
    }
});

</script>
</html>