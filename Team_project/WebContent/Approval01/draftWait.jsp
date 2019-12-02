<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
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
<link rel="stylesheet"
	href="${contextPath}/Approval01/css/jquery-ui.css" />

<script src="${contextPath}/Approval01/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/Approval01/js/jquery-ui.min.js"></script>
<script src="${contextPath}/Approval01/js/jquery.easing.1.3.js"></script>
<script src="${contextPath}/Approval01/js/prefixfree.min.js"></script>
<script src="${contextPath}/Approval01/js/main.js"></script>
<script>
function docModify() {
	if (confirm("수정하시겠습니까?") == true) {
		frm.action = "draftModify.do";
		frm.submit();
	} else {
		return false;
	}
}
function docDelete() {
	  if (confirm("정말 삭제하시겠습니까?") == true) {
		  	frm.action = "draftdelete.do";
		  	frm.submit();
	  } else {
	    return false;
	  }
	}
	function docApprove() {
		if(${approvalVO.mideno==loginUser.eno}){
			if (confirm("승인하시겠습니까?") == true) {
				frm.action = "midapproveddraft.do";
		  		frm.submit();
			} else {
				return false;
			}
		}else if(${approvalVO.fineno==loginUser.eno}){
			if (confirm("승인하시겠습니까?") == true) {
				frm.action = "finapproveddraft.do";
		  		frm.submit();
			} else {
				return false;
			}
		}
	}
	function docReturn() {
		if(${approvalVO.mideno==loginUser.eno}){
			if (confirm("반려하시겠습니까?") == true) {
				frm.action = "midreturneddraft.do";
		  		frm.submit();
			} else {
				return false;
			}
		}else if(${approvalVO.fineno==loginUser.eno}){
			if (confirm("반려하시겠습니까?") == true) {
				frm.action = "finreturneddraft.do";
		  		frm.submit();
			} else {
				return false;
			}
		}
		}
</script>
<title>기안서</title>
</head>

<body>

	<div class="content">
		<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false" />
		<form name="frm" method="post">
			<input type="hidden" name="txtnum" value="${txtnum}"> <select
				style="visibility: hidden;" class="docSelecter"
				onchange="if(this.value) location.href=(this.value)">
				<option value="./draft.html" selected>기안서</option>
				<option value="./vacation.html">휴가신청서</option>
			</select>

			<div class="docName">
				<h1>기안서</h1>
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

					<%-- <input class="loginUserEno" type="hidden" value="${loginUser.eno}">
					<input class="approvalVOEno" type="hidden" value="${approvalVO.eno}">
					<input class="midEno" type="hidden" value="${createdMidUser.eno}">
					<input class="finEno" type="hidden" value="${createdFinUser.eno}"> --%>

					<table class="signtableright" border="1">
						<tr>
							<th>${approvalVO.rank}</th>
							<th>${createdMidUser.rank}</th>
							<th>${createdFinUser.rank}</th>
						</tr>
						<tr>
							<td style="vertical-align: top">${approvalVO.ename}<br>
								<span style="color: red;">[승인]</span>
							</td>
							<td style="vertical-align: top">${createdMidUser.ename}<br>
								<c:choose>
									<c:when test="${approvalVO.progress == '대기'}">
										<span style="color: red;"></span>
									</c:when>
									<c:when
										test="${(approvalVO.progress == '진행') || (approvalVO.progress == '반려2' && createdMidUser.eno != null) || (approvalVO.progress == '완료' && createdMidUser.eno != null)}">
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
							<td class="createdDayInput2">${approvalVO.middate}</td>
							<td class="createdDayInput3">${approvalVO.findate}</td>
						</tr>
					</table>
				</div>
				<div class="inputarea">
					<table>
						<tr>
							<td>제목<br>
								<div class="inputTitle" style="background-color: white;">${approvalVO.txtname}</div>
							</td>
						</tr>
						<tr>
							<td><br> 내용<br>
							<textarea class="inputContent" name="reason" maxlength="1000" readOnly>${approvalVO.txtcont}</textarea>
							</td>
						</tr>
					</table>

					<br>
					<div class="bottomBt">
						<button type="button" onclick="draftCheck()" disabled>등록</button>

						<c:choose>
							<c:when
								test="${approvalVO.eno==loginUser.eno && approvalVO.progress == '대기'}">
								<button type="button" onclick="docModify()">수정</button>
							</c:when>
							<c:otherwise>
								<button type="button" onclick="docModify()" disabled>수정</button>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when
								test="${approvalVO.eno==loginUser.eno && approvalVO.progress == '대기'}">
								<button type="button" onclick="docDelete()">삭제</button>
							</c:when>
							<c:otherwise>
								<button type="button" onclick="docDelete()" disabled>삭제</button>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when
								test="${(createdMidUser.eno == null && createdFinUser.eno == loginUser.eno && approvalVO.progress == '대기') || (approvalVO.progress == '진행' && createdFinUser.eno == loginUser.eno) || (createdMidUser.eno == loginUser.eno && approvalVO.progress == '대기')}">
								<button class="approve" type="button" onclick="docApprove()">승인</button>
								<button class="cancle" type="button" onclick="docReturn()">반려</button>
							</c:when>
							<c:otherwise>
								<button class="approve" type="button" onclick="docApprove()"
									disabled>승인</button>
								<button class="cancle" type="button" onclick="docReturn()"
									disabled>반려</button>
							</c:otherwise>
						</c:choose>

						<button type="button" onclick="docCancle()">취소</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>