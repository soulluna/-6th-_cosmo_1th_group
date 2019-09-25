function makeBoard() {
    var setBoradHTML="";
    setBoradHTML+="<table class='ListTable' border='1'>";
            setBoradHTML+="<tr><th width='10%'>번호</th><th>문서제목</th><th width='10%'>작성자</th></tr>";
    for (var i = 0; i < 10; i++) {
        setBoradHTML+="<tr>";
        for (var j = 0; j < 3; j++) {
            setBoradHTML+="<td>" + "tests" + "</td>";
        }
        setBoradHTML+="</tr>";
        $("#board2").html(setBoradHTML);
    }
}

function makeApproval() {
    var setBoradHTML="";
    setBoradHTML+="<table class='ListTable' border='1'>";
            setBoradHTML+="<tr><th width='10%'>결재종류</th><th>문서제목</th><th width='10%'>결재상태</th></tr>";
    for (var i = 0; i < 10; i++) {
        setBoradHTML+="<tr>";
        for (var j = 0; j < 3; j++) {
            setBoradHTML+="<td>" + "tests" + "</td>";
        }
        setBoradHTML+="</tr>";
        $("#board3").html(setBoradHTML);
    }
}