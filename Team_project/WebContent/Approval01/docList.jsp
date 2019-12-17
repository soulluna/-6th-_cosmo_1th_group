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
	function searchTypeValueReset(a) {

		if (a == '') {
			document.getElementsByName('searchKey')[0].value = "";
			document.getElementsByName('searchType')[0].value = "";
			frm.submit();
		}
	}

	function datepickerValueReset() {
		document.getElementsByName('searchDatepicker1')[0].value = "";
		document.getElementsByName('searchDatepicker2')[0].value = "";
		frm.submit();
	}

	function DateCheck() {
		toDay = $('#datepicker1').val();
		forDay = $('#datepicker2').val();
		if (toDay == '' || forDay == '') {
			alert("날짜를 올바르게 선택하세요.");
			return false;
		} else {
			var c = getDateDiff(forDay, toDay) + 1;
			if (c <= 0) {
				alert("날짜를 올바르게 선택하세요.");
				return false;
			} else {
				frm.submit();
			}
		}
	}
</script>


<title>내 문서함</title>

</head>
<body>

	<!--전체 컨텐츠-->
	<div class="content">
		<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false" />

		<div class="docName">
			<h1>내 문서함</h1>
		</div>
		<!--깊은 컨텐츠-->
		<div class="deptContent" style="margin: 0 10px 10px 10px;">
			<!--문서종류, 검색값, 검색버튼-->
			<form name="frm" method="get">
				<div class="serachArea" style="margin-left: 51px;">

					
					<span>
						<select class="selecter" name="serachDocList" onchange="this.form.submit();">
							<option value="">결재종류</option>
							<option value="기안서" <c:if test="${serachDocList == '기안서'}">selected="selected"</c:if>>기안서</option>
							<option value="휴가신청서" <c:if test="${serachDocList == '휴가신청서'}">selected="selected"</c:if>>휴가신청서</option>
						</select>

						<select class="selecter" name="serachDocState" onchange="this.form.submit();" style="width: 90px;">
							<option value="">결재상태</option>
							<option value="대기" <c:if test="${serachDocState == '대기'}">selected="selected"</c:if>>대기</option>
							<option value="진행" <c:if test="${serachDocState == '진행'}">selected="selected"</c:if>>진행</option>
							<option value="완료" <c:if test="${serachDocState == '완료'}">selected="selected"</c:if>>완료</option>
							<option value="반려1" <c:if test="${serachDocState == '반려1'}">selected="selected"</c:if>>반려1</option>
							<option value="반려2" <c:if test="${serachDocState == '반려2'}">selected="selected"</c:if>>반려2</option>
						</select>
					</span>

					<span style="margin-left: 50px;">
						<input type="text" id="datepicker1" name="searchDatepicker1" placeholder="년/월/일" readonly value="${searchDatepicker1}">
						~
						<input type="text" id="datepicker2" name="searchDatepicker2" placeholder="년/월/일" readonly value="${searchDatepicker2}">
						<input type="button" value="검색" onclick="DateCheck();">
						<input type="button" value="날짜 초기화" onclick="datepickerValueReset();" style="width: 110px">
					</span>
					<span style="margin-left:50px;"> 
						<input type="button" onclick="location.href = '${contextPath}/Approval/docList.do'" value="모든 검색 조건 초기화" style="width:200px;">
					</span>
					<br> <br>
				</div>
				<!--//문서종류, 검색값, 검색버튼-->

				<div class="side">
					<a
						href="${contextPath}/Approval/docList.do?sendReceive=수신&serachDocState=${serachDocState}&serachDocList=${serachDocList}&searchType=${searchType}&searchKey=${searchKey}&sendReceive=${sendReceive}&searchDatepicker1=${searchDatepicker1}&searchDatepicker2=${searchDatepicker2}"
					>
						<div <c:if test="${sendReceive == '수신'}">style="color:white"</c:if>>수신</div>
					</a> <a
						href="${contextPath}/Approval/docList.do?sendReceive=발신&serachDocState=${serachDocState}&serachDocList=${serachDocList}&searchType=${searchType}&searchKey=${searchKey}&sendReceive=${sendReceive}&searchDatepicker1=${searchDatepicker1}&searchDatepicker2=${searchDatepicker2}"
					>
						<div <c:if test="${sendReceive == '발신'}">style="color:white"</c:if>>발신</div>
					</a>
				</div>

				<!--문서 목록표-->
				<table class="docListTable" border="1">
					<tr>
						<th width="80px">문서번호</th>
						<th width="120px">결재종류</th>
						<th width="90px">결재상태</th>
						<th width="550px">문서제목</th>
						<th width="100px">작성자</th>
						<th width="100px">등록일자</th>
					</tr>
					<c:choose>
						<c:when test="${approvalList.size()==0}">
							<tr>
								<td colspan="6">검색 결과가 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${approvalList}" var="approval" varStatus="15">
								<tr>
									<td>${approval.txtnum}</td>
									<td>${approval.applist}</td>
									<td class="progress">${approval.progress}</td>
									<c:choose>
										<c:when test="${approval.applist == '기안서'}">
											<td style="text-align: left;">
												&nbsp;<a
													href="${contextPath}/Approval/draftWait.do?txtnum=${approval.txtnum}&pageNum=${pagingMap.pageNum}&pageSession=${pagingMap.pageSessionNum}&sendReceive=${sendReceive}&serachDocState=${serachDocState}&serachDocList=${serachDocList}&searchType=${searchType}&searchKey=${searchKey}&sendReceive=${sendReceive}&searchDatepicker1=${searchDatepicker1}&searchDatepicker2=${searchDatepicker2}"
												>${approval.txtname}</a>
											</td>
										</c:when>
										<c:when test="${approval.applist == '휴가신청서'}">
											<td style="text-align: left;">
												&nbsp;<a
													href="${contextPath}/Approval/vacationWait.do?txtnum=${approval.txtnum}&pageNum=${pagingMap.pageNum}&pageSession=${pagingMap.pageSessionNum}&sendReceive=${sendReceive}&serachDocState=${serachDocState}&serachDocList=${serachDocList}&searchType=${searchType}&searchKey=${searchKey}&sendReceive=${sendReceive}&searchDatepicker1=${searchDatepicker1}&searchDatepicker2=${searchDatepicker2}"
												>${approval.txtname}</a>
											</td>
										</c:when>
									</c:choose>
									<td>${approval.ename}</td>
									<td>${approval.entrydate}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>

				<div class="bottomNumber">
					<c:if test="${(pagingMap.maxSessionNum >= pagingMap.pageSessionNum) && pagingMap.pageSessionNum != 1}">
						<a href="${contextPath}/Approval/docList.do?pageNum=${(pagingMap.pageSessionNum-1)*5-4}&pageSession=${pagingMap.pageSessionNum-1}&sendReceive=${sendReceive}&serachDocState=${serachDocState}&serachDocList=${serachDocList}&searchType=${searchType}&searchKey=${searchKey}&sendReceive=${sendReceive}&searchDatepicker1=${searchDatepicker1}&searchDatepicker2=${searchDatepicker2}">이전</a>
					</c:if>

					<!-- 페이징 숫자 -->
					<c:forEach var="page" begin="${(pagingMap.pageSessionNum-1)*5+1}" end="${pagingMap.pageSessionNum*5}">
						<c:choose>
							<c:when test="${pagingMap.pageNum == page}">
								<a
									href="${contextPath}/Approval/docList.do?pageNum=${page}&pageSession=${pagingMap.pageSessionNum}&sendReceive=${sendReceive}&serachDocState=${serachDocState}&serachDocList=${serachDocList}&searchType=${searchType}&searchKey=${searchKey}&sendReceive=${sendReceive}&searchDatepicker1=${searchDatepicker1}&searchDatepicker2=${searchDatepicker2}"
									style="color: red"
								>${page}</a>
							</c:when>
							<c:when test="${page <= pagingMap.maxPageNum}">
								<a
									href="${contextPath}/Approval/docList.do?pageNum=${page}&pageSession=${pagingMap.pageSessionNum}&sendReceive=${sendReceive}&serachDocState=${serachDocState}&serachDocList=${serachDocList}&searchType=${searchType}&searchKey=${searchKey}&sendReceive=${sendReceive}&searchDatepicker1=${searchDatepicker1}&searchDatepicker2=${searchDatepicker2}"
								>${page}</a>
							</c:when>
						</c:choose>

					</c:forEach>

					<c:choose>
						<c:when test="${pagingMap.maxSessionNum > pagingMap.pageSessionNum}">
							<a href="${contextPath}/Approval/docList.do?pageNum=${(pagingMap.pageSessionNum-1)*5+6}&pageSession=${pagingMap.pageSessionNum+1}&sendReceive=${sendReceive}&serachDocState=${serachDocState}&serachDocList=${serachDocList}&searchType=${searchType}&searchKey=${searchKey}&sendReceive=${sendReceive}&searchDatepicker1=${searchDatepicker1}&searchDatepicker2=${searchDatepicker2}">다음</a>
						</c:when>

					</c:choose>

					<c:if test="${loginUser.eno != 9052501152}">
						<button class="docCreate" name="docCreate" type="button"
							onclick="location.href='${contextPath}/Approval/draft.do?pageNum=${pagingMap.pageNum}&pageSession=${pagingMap.pageSessionNum}&sendReceive=${sendReceive}&serachDocState=${serachDocState}&serachDocList=${serachDocList}&searchType=${searchType}&searchKey=${searchKey}&sendReceive=${sendReceive}&searchDatepicker1=${searchDatepicker1}&searchDatepicker2=${searchDatepicker2}'"
						>작성</button>
					</c:if>

				</div>
				<span style="margin: 30px 0 0 220px;">
					<select class="selecter" name="searchType" onchange="searchTypeValueReset(this.value);">
						<option value="">검색</option>
						<option value="1" <c:if test="${searchType == '1'}">selected="selected"</c:if>>문서제목</option>
						<option value="2" <c:if test="${searchType == '2'}">selected="selected"</c:if>>문서내용</option>
						<option value="3" <c:if test="${searchType == '3'}">selected="selected"</c:if>>작성자</option>
					</select>
					<input type="text" class="searchInput" name="searchKey" value="${searchKey}" placeholder="검색어 입력" style="width: 400px; margin-left: -6px;">
					<input type="button" class="search" value="검색" onclick="docListSearchCheck()" style="margin-left: -6px;">
				</span>
				<br> <br>
				<input type="hidden" name="searchType" value="${searchType}">
				<input type="hidden" name="searchKey" value="${searchKey}">
				<input type="hidden" name="serachDocState" value="${serachDocState}">
				<input type="hidden" name="serachDocList" value="${serachDocList}">
				<input type="hidden" name="sendReceive" value="${sendReceive}">
				<input type="hidden" name="searchDatepicker1" value="${searchDatepicker1}">
				<input type="hidden" name="searchDatepicker2" value="${searchDatepicker2}">
			</form>
		</div>

		<!--//깊은 컨텐츠-->
	</div>
	<!--//전체 컨텐츠-->
</body>

</html>