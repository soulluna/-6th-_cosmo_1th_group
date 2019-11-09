$(function () {
    //상세 게시판
    //게시판/ 댓글 지우기
    $(".delete").click(function () {
        if (confirm("정말 삭제 하겠습니까?")) {
            // 확인 버튼 클릭 시 동작
            alert("삭제 되었습니다.");
//            location.replace('./noticeBoardMain.html');
        } else {
            // 취소 버튼 클릭 시 동작
            alert("삭제를 취소했습니다.");
        }
    });
    $(".del").click(function () {
        if (confirm("정말 삭제 하겠습니까?")) {
            // 확인 버튼 클릭 시 동작
            alert("삭제 되었습니다.");
            
        //상세페이지 댓글 지우기
         $(this).parents('.media-body').css({'display':'none'})
        
            
        } else {
            // 취소 버튼 클릭 시 동작
            alert("삭제를 취소했습니다.");
        }
    });
    //상세페이지 게시글 추천 색상 변환 및 추천수 증가및 감소
    $(".n_good").click(function(){
        var co = parseInt($(".co7").text());        
        if($(this).hasClass('bgColor')){
            $(".co7").text(co-1);
            $(this).removeClass('bgColor');
            $(".n_good").text("추천").css({"color":"white"})
        } else {
            $(".co7").text(co+1);
            $(this).addClass('bgColor');
            $(".n_good").text("추천취소").css({"color":"white"})
        }

        
        
    })
    //상세페이지 댓글 추천수 변환

    $(".cmt-good-btn").click(function(){
        var span=$(this).children('a').children('span');
        var g = parseInt(span.text());
        if(span.hasClass('cmtBor')){
            span.text(g-1);
            span.removeClass('cmtBor');
            span.parents('.cmt-good-btn').css({'background':'none'})
            span.parents('.cmt-good').css({'color':'#555'})
        } else{
            span.text(g+1);
            span.addClass('cmtBor');
            span.parents('.cmt-good-btn').css({'background':'cyan'})
            span.parents('.cmt-good').css({'color':'white'})
        }
    });
        
    //상세페이지 댓글 출력
    function comment() {
        var comment =  $("textarea[name=comment]").val();
        console.log(comment);
         //댓글 내용없이 작성시 경고창
         var sear = $(".eotrmfdlqfur").val();
         if(sear=="")
             alert("내용을 입력하십시오");
    }
    $(".c_write").click(comment);
  

    //글작성 페이지
    $(".back").click(function () {
        if (confirm("확인시 내용이 있을경우 사라집니다.")) {
            // 확인 버튼 클릭 시 동작
//            location.replace('./noticeBoardMain.html');
        } else {
            // 취소 버튼 클릭 시 동작
        }
    });
      
    
    //글장석.html 입력값 확인
    var write = new Array();
    function check() {
        write[0] = $("select[name=searchtype] option:selected").val();
        write[1] = $("input[name=w_title]").val();
        write[2] = $("textarea[name=contents]").val();
        write[3] = $("input[name=empty]:checked").val();
        console.log(write[0]+"\n"+write[1]+"\n"+write[2]+"\n"+write[3]);
        
      
        if (!write[1]) {
            alert("제목을 입력해주세요.");
            return;
        } else if (!write[2]) {
            alert("내용을 입력해주세요.");
            return;
        } else {
            //location.replace('./noticeBoardMain.html');
        }
    } // else
  
  $(".make").click(check);
  

// 메인게시판페이지 검색 타입 및 검색값 콘솔 확인
$(".search").on("click", function () {
    var a = $("select[name=searchType] option:selected").val();
    var b = $("input[name=searchKey]").val();
    console.log(a);
    console.log(b);
    location.href = '#';
    //메인게시판 검색어 없을때 경고창
});
$(".search").on("click",function(){
    var search = $("input").val();
    if(search=="")
        alert("검색어를 입력하십시오");//검색어 입력않하면 경고창
  });
});