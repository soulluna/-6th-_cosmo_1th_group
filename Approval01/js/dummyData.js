var employeesArr = [
    ["이진호","김윤경","정철욱","최영태","김학선","차현진","원치운","석인영","이우인","조현석","신한솔","임소현","이유이","양하준","함종우","유상민","안영우","남기호"], //이름
    [], //사번
    [], //직급
    [], //부서명
    [] //팀명
];
var employeesArrIndex; //랜덤으로 누군가 한명 작성자로 당첨(직급이 이사인 사람 제외)
var ename; //이름
var eno; //사번
var rankArr = ["사원","대리","과장","부장","차장","이사"]; //직급
var rank; //직급
var dnameArr = ["인사부","영업부","개발부"]; //부서명
var dname; //부서명
var dname_twoArr = ["1팀","2팀"]; //팀명
var dname_two; //팀명
var txtcallArr = ["수","발"]; //수신,발신
var txtcall; //수,발
var applistArr = ["기안서","휴가 신청서"]; //문서 종류
var applist; //문서 종류
var progressArr = ["대기","진행","완료"]; //결재 상태
var progress; //결재 상태
var progressArrIndex = [1,2,3]; //대기:1, 진행:2, 완료:3
var progressIndex; //결재 상태 index
var approvalArr = ["승인","반려"]; //결재 결과
var approval; //결재 결과
var teamMemberEnoRank = [[],[]]; //작성자와 같은 부서, 팀인 사원의 사번과 직급
var teamMemberSortedEnoRank = [[],[]]; //직급을 기준으로 오름차순 정렬된 작성자와 같은 부서, 팀인 사원의 사번과 직급
var midSugestEno; //중간 결재자 사번
var midSugestRank; //중간 결재자 직급
var midSugestEname; //중간 결재자 이름
var finSugestEno; //최종 결재자 사번
var finSugestRank; //최종 결재자 직급
var finSugestEname; //최종 결재자 이름
var entrydate; //작성일
var middate; //중간 결재일
var findate; //최종 결재일
var txtnum; //글번호
var vaclist = ["연차","병가","휴가","기타"]; //휴가 종류
var vac; //휴가 종류
var vacstart; //휴가 시작일
var vacend; //휴가 종료일
var txtname; //글제목
var txtcont; //글내용

var date = new Date();
var random_year; //랜덤 년
var random_month; //랜덤 월
var random_date; //랜덤 일
var lastDate; //월의 마지막 날
var random_day; //랜덤 년월일

var article = new Array();
var articleArr = new Array(); //결재글들

//---------------림시-------------------
//승인 또는 반려 관련 변수도 필요함
//---------------림시-------------------

//---------------사원 정보 랜덤 생성 반복문-------------------
for(var i=0; i<employeesArr[0].length; i++){
    employeesArr[1][i] = checkDuplicatedValue(i); //사번 생성
    employeesArr[2][i] = rankArr[Math.floor(Math.random() * rankArr.length)]; //직급 생성
    employeesArr[3][i] = dnameArr[Math.floor(Math.random() * dnameArr.length)]; //부서명 생성
    employeesArr[4][i] = dname_twoArr[Math.floor(Math.random() * dname_twoArr.length)]; //팀명 생성
}
//사원 정보 배열 출력
// for(var i=0; i<employeesArr[0].length; i++){
//     document.write(employeesArr[1][i]+", "+employeesArr[0][i]+", "+employeesArr[2][i]+", "+employeesArr[3][i]+", "+employeesArr[4][i]);
//     document.write("<br>");
// }

//---------------결재글 랜덤 생성 반복문-------------------
for(var i=0; i<300; i++){
    employeesArrIndex = get_employeesArrIndex(); //랜덤으로 누군가 한명 작성자로 당첨(직급이 이사인 사람 제외)

    ename = employeesArr[0][employeesArrIndex]; //이름
    eno = employeesArr[1][employeesArrIndex]; //사번
    rank = employeesArr[2][employeesArrIndex]; //직급
    dname = employeesArr[3][employeesArrIndex]; //부서명
    dname_two = employeesArr[4][employeesArrIndex]; //팀명

    txtnum = i; //글번호
    txtcall = txtcallArr[Math.floor(Math.random() * txtcallArr.length)]; //수,발
    applist = applistArr[Math.floor(Math.random() * applistArr.length)]; //문서 종류
    progress = progressArr[Math.floor(Math.random() * progressArr.length)]; //결재 상태
    progressIndex = get_progressIndex(progress); //결재 상태 index
    approval = getRandom_approval(progress); //결재 결과
    teamMemberEnoRank = get_teamMemberEnoRank(eno, dname, dname_two, rank); //작성자와 같은 부서, 팀인 사원의 사번과 직급
    teamMemberSortedEnoRank = get_teamMemberSortedEnoRank(teamMemberEnoRank); //직급을 기준으로 오름차순 정렬된 작성자와 같은 부서, 팀인 사원의 사번과 직급
    midSugestEno = get_midSugestEno(rank, teamMemberSortedEnoRank, progress, eno); //중간 결재자 사번
    midSugestRank = get_sugetRank(midSugestEno); //중간 결재자 직급
    midSugestEname = get_sugetEname(midSugestEno); //중간 결재자 이름
    finSugestEno = get_finSugestEno(midSugestRank, teamMemberSortedEnoRank, progress, midSugestEno); //최종 결재자 사번
    finSugestRank = get_sugetRank(finSugestEno); //최종 결재자 직급
    finSugestEname = get_sugetEname(finSugestEno); //최종 결재자 이름
    entrydate = getRandom_entrydate(); //작성일
    middate = getRandom_middate_findate(entrydate); //중간 결재일
    findate = getRandom_middate_findate(middate); //최종 결재일
    vac = getRandom_vac(applist); //휴가 종류
    vacstart = getRandom_vacDate(applist, entrydate); //휴가 시작일
    vacend = getRandom_vacDate(applist, vacstart); //휴가 종료일
    txtname = applist + "에 관한 제목입니다.("+txtnum+")"; //글제목
    txtcont = applist + "에 관한 내용입니다.("+txtnum+")"; //글내용

    article = []; //결재글
    article.push(txtnum); //글번호
    article.push(txtcall); //수,발
    article.push(applist); //문서 종류
    article.push(eno); //사번
    article.push(ename); //이름
    article.push(rank); //직급
    article.push(dname); //부서명
    article.push(dname_two); //팀명
    article.push(progress); //결재 상태
    article.push(progressIndex); //결재 상태 index
    article.push(approval); //결재 결과
    article.push(midSugestRank); //중간 결재자 직급
    article.push(midSugestEname); //중간 결재자 이름
    article.push(finSugestRank); //최종 결재자 직급
    article.push(finSugestEname); //최종 결재자 이름
    article.push(entrydate); //작성일
    article.push(middate); //중간 결재일
    article.push(findate); //최종 결재일
    article.push(vac); //휴가 종류
    article.push(vacstart); //휴가 시작일
    article.push(vacend); //휴가 종료일
    article.push(txtname); //글제목
    article.push(txtcont); //글내용

    articleArr.push(article); //배열을 배열에 넣어서 2차원 배열이 대엇다
}
//결재글 배열 출력
// articleArr.forEach(function(value){
//     document.write(value);
//     document.write("<br>");
// });

//---------------결재 상태 index 구하기-------------------
function get_progressIndex(progress){
    var index = progressArr.indexOf(progress);
    return progressArrIndex[index];
}

//---------------휴가 시작 또는 종료일 랜덤 생성(휴가 기간은 1~14일 사이)-------------------
function getRandom_vacDate(applist, startDate){
    if(applist==applistArr[1]){
        var tempArr = startDate.split("-");
        var tempEntrydate_year = Number(tempArr[0]); //작성일의 년(year)만 추출
        var tempEntrydate_month = Number(tempArr[1]); //작성일의 월(month)만 추출
        var tempEntrydate_date = Number(tempArr[2]); //작성일의 일(date)만 추출
        lastDate = new Date(tempEntrydate_year, tempEntrydate_month, 0).getDate(); //특정 년월의 마지막 일(date) 구하기
    
        random_date = tempEntrydate_date + Math.floor(Math.random() * 14)+1; //작성일로부터 1~14일 사이
        random_month = tempEntrydate_month;
        random_year = tempEntrydate_year;
        if(random_date > lastDate){
            random_date = random_date - lastDate;
            random_month = tempEntrydate_month + 1;
            if(random_month > 12){
                random_year = tempEntrydate_year + 1;
                random_month = 1;
            }
        }
        random_day = random_year+"-"+random_month+"-"+random_date; //랜덤 날짜
    }else{
        random_day = "";
    }
    return random_day;
}

//---------------휴가 종류 랜덤 생성-------------------
function getRandom_vac(applist){
    var vac = "";
    if(applist==applistArr[1]){
        vac = vaclist[Math.floor(Math.random() * vaclist.length)]; 
    }
    return vac;
}

//---------------중간 또는 최종 결재자 이름 구하기-------------------
function get_sugetEname(sugestEno){
    var ename;
    var index = employeesArr[1].indexOf(sugestEno);
    if(index!=-1){ //배열에 중간 결재자 사번이 있는 경우
        ename = employeesArr[0][index];
    }else{ //배열에 중간 결재자 사번이 없는 경우
        ename = sugestEno;
    }
    return ename;
}

//---------------중간 또는 최종 결재자 직급 구하기-------------------
function get_sugetRank(sugestEno){
    var rank;
    var index = employeesArr[1].indexOf(sugestEno);
    if(index!=-1){ //배열에 중간 결재자 사번이 있는 경우
        rank = employeesArr[2][index];
    }else{ //배열에 중간 결재자 사번이 없는 경우
        rank = sugestEno;
    }
    return rank;
}

//---------------최종 결재자 사번 구하기-------------------
function get_finSugestEno(midSugestRank, teamMemberSortedEnoRank, progress, midSugestEno){
    var result = "";
    var rankIndex = rankArr.indexOf(midSugestRank); //중간 결재자 직급index
    if(progress==progressArr[2]){ // 결재 상태가 완료일 경우
        var lastIndex = teamMemberSortedEnoRank[1].length-1; //작성자와 같은 부서, 같은 팀인 사원 중에서 가장 높은 직급의 index
        if(rankIndex==-1){ //중간 결재자 직급이 없는 경우
            // result = "윗사람없음"; //get_midSugestEno메서드에서 윗사람 없는 경우도 eno로 처리했기때문에 이것은 작동안해서 주석처리함
        }else if(lastIndex>=0 && teamMemberSortedEnoRank[1][lastIndex] >= rankIndex){ //최종 결재자 후보가 최소 1명 이상 있고 직급이 중간 결재자와 같거나 큰 경우
            if(teamMemberSortedEnoRank[1][lastIndex] == rankIndex){ //직급이 같을 경우
                var sortedEnoIndex = teamMemberSortedEnoRank[0][lastIndex];
                if(sortedEnoIndex < midSugestEno){ //사번으로 비교
                    result = sortedEnoIndex;
                }else{
                    result = midSugestEno;
                }
            }else{ //직급이 다를 경우
                var finSugestEnoIndex = employeesArr[1].indexOf(teamMemberSortedEnoRank[0][lastIndex]);
                result = employeesArr[1][finSugestEnoIndex];
            }
        }else{ //중간 결재자와 같은 부서, 같은 팀인 사원 중에 더 높은 직급인 사원이 없는 경우(즉 중간결재자가 가장 높은 직급인 경우, 혼자인 경우)
            result = midSugestEno;
        }
    }
    return result;
}

//---------------중간 결재자 사번 구하기-------------------
function get_midSugestEno(rank, teamMemberSortedEnoRank, progress, eno){
    var result = "";
    var rankIndex = rankArr.indexOf(rank); //작성자 직급index
    if(progress==progressArr[1] || progress==progressArr[2]){ // 결재 상태가 진행 또는 완료일 경우
        for(var l=0; l<teamMemberSortedEnoRank[0].length; l++){
            if(teamMemberSortedEnoRank[1][l] > rankIndex){ //작성자와 같은 부서, 같은 팀인 사원 중에서 작성자보다 한단계 위 직급인 사원을 찾음
                var midSugestEnoIndex =  employeesArr[1].indexOf(teamMemberSortedEnoRank[0][l]);
                result =  employeesArr[1][midSugestEnoIndex];
                break;
            }else if(teamMemberSortedEnoRank[1][l]==rankIndex){ //작성자와 중간 결재자의 직급이 같은 경우
                if(teamMemberSortedEnoRank[0][l] < eno){
                    result = teamMemberSortedEnoRank[0][l];
                }else{
                    result = eno;
                }
                break;
            }
        }
        if(result==""){ //작성자와 같은 부서, 같은 팀인 사원 중에서 작성자보다 윗사람이 없는 경우
            // result = "윗사람없음";
            result = eno; //그냥 중간 결재자도 작성자로 하자
        }
    }
    return result;
}

//---------------직급을 기준으로 오름차순 정렬된 작성자와 같은 부서, 팀인 사원의 사번과 직급 구하기-------------------
function get_teamMemberSortedEnoRank(teamMemberEnoRank){
    var result = teamMemberEnoRank; //정렬된 작성자와 같은 부서, 팀인 사원들(팀원의 사번, 직급index)
    var tempArr = [[],[]];
    for(k=0; k<result[0].length-1; k++){ //선택정렬
        for(j=k+1; j<result[0].length; j++){
            if(result[1][k] > result[1][j]){ //직급 index를 기준으로 오름차순으로 정렬
                tempArr[0][k] = result[0][j];
                tempArr[1][k] = result[1][j];
                result[0][j] = result[0][k];
                result[1][j] = result[1][k];
                result[0][k] = tempArr[0][k];
                result[1][k] = tempArr[1][k];
            }else if(result[1][k] == result[1][j]){ //직급 index값이 같을 경우
                if(result[0][k] < result[0][j]){ //사번을 기준으로 내림차순으로 정렬
                    tempArr[0][k] = result[0][j];
                    tempArr[1][k] = result[1][j];
                    result[0][j] = result[0][k];
                    result[1][j] = result[1][k];
                    result[0][k] = tempArr[0][k];
                    result[1][k] = tempArr[1][k];
                }
            }
        }
    }
    return result;
}

//---------------작성자와 같은 부서, 팀인 사원의 사번과 직급 구하기-------------------
function get_teamMemberEnoRank(eno, dname, dname_two, rank){
    var result = [[],[]]; //작성자와 같은 부서, 팀인 사원들(팀원의 사번, 직급index)
    var increaseIndex=0;
    for(var i=0; i<employeesArr[0].length; i++){
        if(dname==employeesArr[3][i] && dname_two==employeesArr[4][i] && eno!=employeesArr[1][i]){ //작성자의 부서명과 팀명이 같고 작성자가 아닌 경우
            result[0][increaseIndex] = employeesArr[1][i]; //사번
            result[1][increaseIndex] = rankArr.indexOf(employeesArr[2][i]); //직급index
            increaseIndex++;
        }
    }
    if(result[0].length==0){ //작성자의 부서명과 팀명이 같고 작성자가 아닌 사원이 없는것임(즉, 혼자인 경우)
        result[0][0] = eno;
        result[1][0] = rank;
    }
    return result;
}

//---------------결재 결과 랜덤 생성-------------------
function getRandom_approval(progress){
    var result;
    if(progress==progressArr[0]){ //대기일 경우
        result = approvalArr[0]; //승인
    }else{ //진행 또는 완료일 경우
        result = approvalArr[Math.floor(Math.random() * approvalArr.length)]; //승인 또는 반려
    }
    return result;
}

//---------------대기 작성일 랜덤 생성-------------------
function getRandom_entrydate(){
    random_month = Math.floor(Math.random() * date.getMonth()+1)+1; //특정 년월의 랜덤 월(month) 구하기(1월~현재 월)
    lastDate = new Date(date.getFullYear(), random_month, 0).getDate(); //특정 년월의 마지막 일(date) 구하기
    if(random_month==date.getMonth()+1){
        random_date = Math.floor(Math.random() * date.getDate())+1; //특정 년월의 랜덤 일(date) 구하기(1일~오늘)
    }else{
        random_date = Math.floor(Math.random() * lastDate)+1; //특정 년월의 랜덤 일(date) 구하기(1일~마지막 날)
    }
    if(random_date<10){
        random_date = "0"+random_date;
    }
    if(random_month<10){
        random_month = "0"+random_month;
    }
    random_day = date.getFullYear()+"-"+random_month+"-"+random_date; //랜덤 날짜
    return random_day;
}

//---------------중간 또는 최종 결재일 랜덤 생성(대기 또는 중간 작성일로부터 7일 이내에 중간 또는 최종 결재일이 됨)-------------------
function getRandom_middate_findate(entrydate_middate){
    var tempArr = entrydate_middate.split("-");
    var entrydate_middate_year = parseInt(tempArr[0]); //대기 작성일(entrydate_Temp)의 년(year)만 추출
    var entrydate_middate_month = parseInt(entrydate_middate.substring(entrydate_middate.indexOf("-")+1, entrydate_middate.lastIndexOf("-"))); //대기 작성일(entrydate_Temp)의 월(month)만 추출
    var entrydate_middate_date = Number(tempArr[2]); //중간 결재일(middate_Temp)의 일(date)만 추출

    lastDate = new Date(entrydate_middate_year, entrydate_middate_month, 0).getDate(); //특정 년월의 마지막 일(date) 구하기
    random_date = entrydate_middate_date + Math.floor(Math.random() * 8); //랜덤 최종 결재일(date) 구하기(중간 작성일의 일(date) + 0~7)
    random_month = entrydate_middate_month;
    random_year = entrydate_middate_year;
    if(random_date > lastDate){ //랜덤 최종 결재일(date)이 해당 월(month)의 마지막 날을 넘어갔을 경우
        random_date = random_date - lastDate;
        random_month = entrydate_middate_month + 1; 
        if(random_month > 12){
            random_year = entrydate_middate_year+1;
            random_month = 1;
        }
    }
    random_day = random_year+"-"+random_month+"-"+random_date; //랜덤 날짜
    return random_day;
}

//랜덤으로 누군가 한명 작성자로 당첨(직급이 이사인 사람 제외)
function get_employeesArrIndex(){
    var index;
    while(true){
        index = Math.floor(Math.random() * employeesArr[0].length);
        var rank = employeesArr[2][index];
        if(rank!=rankArr[5]){ //랜덤으로 뽑은 작성자의 직급이 이사가 아닌 경우
            break;
        }
    }
    return index;
}

//---------------사번 랜덤 생성-------------------
function checkDuplicatedValue(i){
    var eno = Math.floor(Math.random() * 10000000000);
    if(i>0){ //최소 1명의 사원 정보가 있는 경우
        while(true){
            for(var increaseIndex=0; increaseIndex<i; increaseIndex++){
                if(employeesArr[1][increaseIndex]==eno){ //랜덤으로 생성한 사번이 사원 중 한명이라도 중복될 경우
                    eno = Math.floor(Math.random() * 10000000000);
                    break;
                }else{ //중복이 아닐 경우 다음 사원의 사번을 체크함
                    continue;
                }
            }
            if(increaseIndex==i){ //모든 사원의 사번이 중복이 아닌 경우
                break;
            }
        }
    }
    return eno;
}


