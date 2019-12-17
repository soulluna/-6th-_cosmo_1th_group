<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="${contextPath}/Approval01/css/main.css" />
<link rel="stylesheet" href="${contextPath}/Approval01/css/gnb.css" />
<link rel="stylesheet" href="${contextPath}/Approval01/css/jquery-ui.css" />

<script src="${contextPath}/Approval01/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/Approval01/js/jquery-ui.min.js"></script>
<script src="${contextPath}/Approval01/js/jquery.easing.1.3.js"></script>
<script src="${contextPath}/Approval01/js/prefixfree.min.js"></script>
<script src="${contextPath}/Approval01/js/main.js"></script>
<script>
function docModify(pageNum, pageSessionNum, searchKey, searchType, sendReceive, serachDocState, serachDocList, searchDatepicker1, searchDatepicker2) {
	if (confirm("수정하시겠습니까?") == true) {
		frm.action = 'vacationModify.do?pageNum='+pageNum+'&pageSession='+pageSessionNum+'&sendReceive='+sendReceive+'&serachDocState='+serachDocState+'&serachDocList='+serachDocList+'&searchType='+searchType+'&searchKey='+searchKey+'&searchDatepicker1='+searchDatepicker1+'&searchDatepicker2='+searchDatepicker2;
		frm.submit();
	} else {
		return false;
	}
}
function docDelete(pageNum, pageSessionNum, searchKey, searchType, sendReceive, serachDocState, serachDocList, searchDatepicker1, searchDatepicker2) {
	  if (confirm("정말 삭제하시겠습니까?") == true) {
		  	frm.action = 'draftdelete.do?pageNum='+pageNum+'&pageSession='+pageSessionNum+'&sendReceive='+sendReceive+'&serachDocState='+serachDocState+'&serachDocList='+serachDocList+'&searchType='+searchType+'&searchKey='+searchKey+'&searchDatepicker1='+searchDatepicker1+'&searchDatepicker2='+searchDatepicker2;
		  	frm.submit();

	} else {
			return false;
		}
	}
</script>
<title>휴가신청서</title>
</head>

<body>
	<div class="content">
		<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false" />
		<form name="frm" method="post">
			<input type="hidden" name="txtnum" value="${txtnum}">
			<select style="visibility: hidden;" class="docSelecter" onchange="if(this.value) location.href=(this.value)">
				<option value="./draft.html" selected>기안서</option>
				<option value="./vacation.html">휴가신청서</option>
			</select>

			<div class="docName">
				<h1>휴가신청서</h1>
			</div>
			<div class="deptContent">
				<div class="signtable">
					<table class="signtableleft">
						<tr>
							<th>이름</th>
							<td>${approvalVO.ename}</td>
						</tr>
						<tr>
							<th>직책</th>
							<td>${approvalVO.rank}</td>
						</tr>
						<tr>
							<th>소속</th>
							<td>${approvalVO.dname}</td>
						</tr>
					</table>

					<table class="signtableright" border="1">
						<tr>
							<th>${approvalVO.rank}</th>
							<c:if test="${createdMidUser.rank!=null}">
								<th>${createdMidUser.rank}</th>
							</c:if>
							<th>${createdFinUser.rank}</th>
						</tr>
						<tr>
							<td style="vertical-align: top">${approvalVO.ename}<br>
								<span style="color: red;">[승인]</span>
							</td>
							<c:if test="${createdMidUser.rank!=null}">
								<td style="vertical-align: top">${createdMidUser.ename}<br>
									<c:choose>
										<c:when test="${approvalVO.progress == '대기'}">
											<span style="color: red;"></span>
										</c:when>
										<c:when test="${(approvalVO.progress == '진행') || (approvalVO.progress == '반려2' && createdMidUser.eno != null) || (approvalVO.progress == '완료' && createdMidUser.eno != null)}">
											<span style="color: red;">[승인]</span>
										</c:when>
										<c:when test="${approvalVO.progress == '반려1'}">
											<span style="color: red;">[반려]</span>
										</c:when>
										<c:otherwise>
											<span style="color: red;"></span>
										</c:otherwise>
									</c:choose>
								</td>
							</c:if>
							<td style="vertical-align: top">${createdFinUser.ename}<br>
								<c:choose>
									<c:when test="${approvalVO.progress == '완료'}">
										<span style="color: red;">[승인]</span>
									</c:when>
									<c:when test="${approvalVO.progress == '반려2'}">
										<span style="color: red;">[반려]</span>
									</c:when>
									<c:otherwise>
										<span style="color: red;"></span>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td class="createdDayInput1">${approvalVO.entrydate}</td>
							<c:if test="${createdMidUser.rank!=null}">
								<td class="createdDayInput2">${approvalVO.middate}</td>
							</c:if>
							<td class="createdDayInput3">${approvalVO.findate}</td>
						</tr>
					</table>
				</div>
				<div class="inputarea">
					<table>
						<tr>
							<td>
								제목<br>
								<div class="inputTitle" style="background-color: white;">${approvalVO.txtname}</div>
							</td>
						</tr>
						<tr>
							<td>
								<br> 1.다음 중 요청하고자 하는 휴가의 종류로 알맞은 것을 고르세요.<br>
								<span>
									<input type="radio" name="leaveradio" value="연차" class="modifySelect1" id="kindsSelect1" checked="checked" disabled>
									<label for="kindsSelect1">연차</label>
								</span>
								<span>
									<input type="radio" name="leaveradio" value="병가" class="modifySelect2" id="kindsSelect2" disabled>
									<label for="kindsSelect2">병가</label>
								</span>
								<span>
									<input type="radio" name="leaveradio" value="휴가" class="modifySelect3" id="kindsSelect3" disabled>
									<label for="kindsSelect3">휴가</label>
								</span>
								<span>
									<input type="radio" name="leaveradio" value="기타" class="modifySelect4" id="kindsSelect4" disabled>
									<label for="kindsSelect4">기타</label>
								</span>
							</td>
						</tr>
						<tr>
							<td>
								<br> 1-2. 요청한 휴가의 기간을 입력하세요.<br>
								<span class="selectedDay">${approvalVO.vacstart}</span>
								~
								<span class="selectedDay">${approvalVO.vacend}</span>
								(
								<span class="dayCount"></span>
								일 간)
							</td>
						</tr>

						<tr>
							<td>
								<br> 2. 1번 보기를 선택한 사유를 쓰세요.<br>
								<textarea class="inputContent" name="reason" required maxlength="1000" readOnly>${approvalVO.txtcont}</textarea>
							</td>
						</tr>
					</table>

					<br>
					<div class="bottomBt">
						<button type="button" onclick="draftCheck()" disabled>등록</button>

						<c:choose>
							<c:when test="${approvalVO.eno==loginUser.eno && approvalVO.progress == '대기'}">
								<button type="button"
									onclick="docModify('${pageNum}','${pageSessionNum}','${searchKey}','${searchType}','${sendReceive}','${serachDocState}','${serachDocList}','${searchDatepicker1}','${searchDatepicker2}')"
								>수정</button>
							</c:when>
							<c:otherwise>
								<button type="button" onclick="docModify()" disabled>수정</button>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${approvalVO.eno==loginUser.eno && approvalVO.progress == '대기'}">
								<button type="button" onclick="docDelete('${pageNum}','${pageSessionNum}','${searchKey}','${searchType}','${sendReceive}','${serachDocState}','${serachDocList}','${searchDatepicker1}','${searchDatepicker2}')">삭제</button>
							</c:when>
							<c:otherwise>
								<button type="button" onclick="docDelete()" disabled>삭제</button>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when
								test="${(createdMidUser.eno == null && createdFinUser.eno == loginUser.eno && approvalVO.progress == '대기') || (approvalVO.progress == '진행' && createdFinUser.eno == loginUser.eno) || (createdMidUser.eno == loginUser.eno && approvalVO.progress == '대기')}"
							>
								<button class="approve" type="button" onclick="docApprove('vacationWait','${loginUser.eno}','${approvalVO.mideno}','${approvalVO.fineno}','${pageNum}','${pageSessionNum}','${searchKey}','${searchType}','${sendReceive}','${serachDocState}','${serachDocList}','${searchDatepicker1}','${searchDatepicker2}')">승인</button>
								<button class="cancle" type="button" onclick="docReturn('vacationWait','${loginUser.eno}','${approvalVO.mideno}','${approvalVO.fineno}','${pageNum}','${pageSessionNum}','${searchKey}','${searchType}','${sendReceive}','${serachDocState}','${serachDocList}','${searchDatepicker1}','${searchDatepicker2}')">반려</button>
							</c:when>
							<c:otherwise>
								<button class="approve" type="button" onclick="docApprove('vacationWait','${loginUser.eno}','${approvalVO.mideno}','${approvalVO.fineno}','${pageNum}','${pageSessionNum}','${searchKey}','${searchType}','${sendReceive}','${serachDocState}','${serachDocList}','${searchDatepicker1}','${searchDatepicker2}')" disabled>승인</button>
								<button class="cancle" type="button" onclick="docReturn('vacationWait','${loginUser.eno}','${approvalVO.mideno}','${approvalVO.fineno}','${pageNum}','${pageSessionNum}','${searchKey}','${searchType}','${sendReceive}','${serachDocState}','${serachDocList}','${searchDatepicker1}','${searchDatepicker2}')" disabled>반려</button>
							</c:otherwise>
						</c:choose>
						<button type="button"
							onclick="docCancle('${pageNum}','${pageSessionNum}','${searchKey}','${searchType}','${sendReceive}','${serachDocState}','${serachDocList}','${searchDatepicker1}','${searchDatepicker2}')"
						>취소</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
<script>
var vaclist = "${approvalVO.vaclist}";
if (vaclist == "연차") {
	$(".modifySelect1").attr("checked", "checked");
} else if (vaclist == "병가") {
	$(".modifySelect2").attr("checked", "checked");
} else if (vaclist == "휴가") {
	$(".modifySelect3").attr("checked", "checked");
} else if (vaclist == "기타") {
	$(".modifySelect4").attr("checked", "checked");
}

$(document).ready(function() {
	function getDateDiff(date1, date2) {
		var arrDate1 = date1.split("-");
		var getDate1 = new Date(parseInt(arrDate1[0]), parseInt(arrDate1[1]) - 1,
				parseInt(arrDate1[2]));
		var arrDate2 = date2.split("-");
		var getDate2 = new Date(parseInt(arrDate2[0]), parseInt(arrDate2[1]) - 1,
				parseInt(arrDate2[2]));
		var getDiffTime = getDate1.getTime() - getDate2.getTime();
		return Math.floor(getDiffTime / (1000 * 60 * 60 * 24));
	}
	$('.dayCount').text(getDateDiff("${approvalVO.vacend}","${approvalVO.vacstart}") +1);
});
</script>
</html>