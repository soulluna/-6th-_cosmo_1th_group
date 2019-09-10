$(function(){
    $(".delete").click(function(){
        if (confirm("정말 삭제 하겠습니까?")) {
            // 확인 버튼 클릭 시 동작
            alert("삭제 되었습니다.");
        } else {
            // 취소 버튼 클릭 시 동작
            alert("삭제를 취소했습니다.");}
    });
    
    $(".back").click(function(){
        if (confirm("확인시 내용이 있을경우 사라집니다.")) {
            // 확인 버튼 클릭 시 동작
            location.replace('./noticeBoardMain.html');
        } else {
            // 취소 버튼 클릭 시 동작
            }
    });
});