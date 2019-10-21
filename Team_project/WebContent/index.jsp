<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>::Login Page::</title>
    <link rel="stylesheet" href="css/login.css">
    <script src="./js/jquery-2.1.1.min.js"></script>
    <script src="./js/jquery-ui.min.js"></script>
    <script src="./js/jquery.easing.1.3.js"></script>
    <script src="./js/prefixfree.min.js"></script>
    <script src="./js/main.js"></script>
    <script>
        console.log(location.href, "\n");
        console.log(location.hash, "\n");
        console.log(location.hostname, "\n");
        console.log(location.host, "\n");
        console.log(location.protocol, "\n");
    </script>

<body>
    <form class="login-form" onsubmit="return validate();" action="Team2_kim/indexMain.html" method="post">
        <div><img src="./img/logo3.gif"></div>

        <div class="txtb">
            <input type="text" id="userID">
            <span data-placeholder="사원번호"></span>
        </div>

        <div class="txtb">
            <input type="password" id="pwd">
            <span data-placeholder="비밀번호"></span>
        </div>

        <input type="submit" class="lgnbtn" value="Login" id="login_submit">

        <div class="bottom-text">
            아이디가 없을 경우 <a href="./Main/registration/reg.jsp">회원가입</a>을 해주세요.
        </div>

    </form>

    <script type="text/javascript">
        $(".txtb input").on("focus", function () {
            $(this).addClass("focus");
        });

        $(".txtb input").on("blur", function () {
            if ($(this).val()=="")
                $(this).removeClass("focus");
            return false;
        });
        // 로그인
        // $(document).ready(function(){
        //     $('#login_submit:submit').click(function(){
        //         invaildItem();
        //     });
        // });
    </script>

</body>
</html>