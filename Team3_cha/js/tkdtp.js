$(function(){
    $(".delete").click(function(){
        if (confirm("정말 삭제 하겠습니까?")) {
            // 확인 버튼 클릭 시 동작
            alert("삭제 되었습니다.");
        } else {
            // 취소 버튼 클릭 시 동작
            alert("삭제를 취소했습니다.");}
    });
});