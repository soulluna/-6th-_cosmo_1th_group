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
		frm.action = "draftModify.do?txtnum="+${txtnum};
		frm.submit();
	} else {
		return false;
	}
}
function docDelete() {
	  if (confirm("정말 삭제하시겠습니까?") == true) {
		  	frm.action = "draftdelete.do?txtnum="+${txtnum};
		  	frm.submit();
	  } else {
	    return false;
	  }
	}
	function docApprove() {
		if(${approval.mideno==loginUser.eno}){
			if (confirm("승인하시겠습니까?") == true) {
				frm.action = "midapproveddraft.do?txtnum="+${txtnum};
		  		frm.submit();
			} else {
				return false;
			}
		}else if(${approval.fineno==loginUser.eno}){
			if (confirm("승인하시겠습니까?") == true) {
				frm.action = "finapproveddraft.do?txtnum="+${txtnum};
		  		frm.submit();
			} else {
				return false;
			}
		}
	}
	function docReturn() {
		if(${approval.mideno==loginUser.eno}){
			if (confirm("반려하시겠습니까?") == true) {
				frm.action = "midreturneddraft.do?txtnum="+${txtnum};
		  		frm.submit();
			} else {
				return false;
			}
		}else if(${approval.fineno==loginUser.eno}){
			if (confirm("반려하시겠습니까?") == true) {
				frm.action = "finreturneddraft.do?txtnum="+${txtnum};
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
			<select style="visibility: hidden;" class="docSelecter"
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
							<td>${approval.ename}</td>
						</tr>
						<tr>
							<th>직책</th>
							<td>${approval.rank}</td>
						</tr>
						<tr>
							<th>소속</th>
							<td>${approval.dname}</td>
						</tr>
					</table>

					<table class="signtableright" border="1">
						<tr>
							<th>${approval.rank}</th>
							<th>${createdMidUser.rank}</th>
							<th>${createdFinUser.rank}</th>
						</tr>
						<tr>
							<td style="vertical-align: top">${approval.ename}<br> <span
								style="color: red;">[승인]</span>
							</td>
							<td style="vertical-align: top">${createdMidUser.ename}<br>
								<c:choose>
									<c:when test="${approval.progress == '반려'}">
										<span class="midUser" style="color: red;">[반려]</span>
									</c:when>
									<c:when test="${approval.middate != null}">
										<span class="midUser" style="color: red;">[승인]</span>
									</c:when>
									<c:otherwise>
										<span></span>
									</c:otherwise>
								</c:choose>
							</td>

							<td style="vertical-align: top">${createdFinUser.ename}<br>
								<c:choose>
									<c:when
										test="${approval.progress == '반려' && approval.findate != null}">
										<span class="finUser" style="color: red;">[반려]</span>
									</c:when>
									<c:when test="${approval.findate != null}">
										<span class="finUser" style="color: red;">[승인]</span>
									</c:when>
									<c:otherwise>
										<span></span>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						
						<tr>
							<td class="createdDayInput1">${approval.entrydate}</td>
							<td class="createdDayInput2">${approval.middate}</td>
							<td class="createdDayInput3">${approval.findate}</td>
						</tr>
					</table>
				</div>
				<div class="inputarea">
					<table>
						<tr>
							<td>제목<br>
								<div class="inputTitle" style="background-color: white;">
									${approval.txtname}</div>
							</td>
						</tr>
						<tr>
							<td><br> 내용<br>
								<div class="inputContent"
									style="background-color: white; vertical-align: top;">
									${approval.txtcont}</div></td>


						</tr>
					</table>

					<br>
					<div class="bottomBt">
						<button type="button" onclick="draftCheck()" disabled>등록</button>

						<c:choose>
							<c:when test="${approval.eno==loginUser.eno}">
								<button type="button" onclick="docModify()">수정</button>
							</c:when>
							<c:otherwise>
								<button type="button" onclick="docModify()" disabled>수정</button>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${approval.eno==loginUser.eno}">
								<button type="button" onclick="docDelete()">삭제</button>
							</c:when>
							<c:otherwise>
								<button type="button" onclick="docDelete()" disabled>삭제</button>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${approval.progress == '반려'}">
								<button type="button" onclick="docReturn()" disabled>승인</button>
							</c:when>
							<c:when
								test="${approval.mideno==loginUser.eno && approval.middate == null}">
								<button type="button" onclick="docApprove()">승인</button>
							</c:when>
							<c:when
								test="${approval.fineno==loginUser.eno && approval.findate == null && approval.middate != null}">
								<button type="button" onclick="docApprove()">승인</button>
							</c:when>

							<c:otherwise>
								<button type="button" onclick="docApprove()" disabled>승인</button>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${approval.progress == '반려'}">
								<button type="button" onclick="docReturn()" disabled>반려</button>
							</c:when>
							<c:when
								test="${approval.mideno==loginUser.eno && approval.middate == null}">
								<button type="button" onclick="docReturn()">반려</button>
							</c:when>
							<c:when
								test="${approval.fineno==loginUser.eno && approval.findate == null && approval.middate != null}">
								<button type="button" onclick="docReturn()">반려</button>
							</c:when>
							<c:otherwise>
								<button type="button" onclick="docReturn()" disabled>반려</button>
							</c:otherwise>
						</c:choose>
						<button type="button" onclick="docCancle()">취소</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	
</body>
<script>
$(document).ready(function() {
	if(($(".midUser").text()=='[반려]') && ($(".finUser").text()=='[반려]')){
	$(".midUser").text('[승인]');
} 
	
});

</script>
</html>