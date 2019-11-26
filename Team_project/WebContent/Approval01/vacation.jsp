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
	$(function() {
		var toDay;
		var forDay;
		$('#datepicker1').change(function() {
			toDay = $('#datepicker1').val();
			forDay = $('#datepicker2').val();
			var c = getDateDiff(forDay, toDay) + 1;
			console.log(c);
			if (c) {
				$('.dayCount').text(c);
			} else if (c == 0) {
				$('.dayCount').text("0");
			}
			if (c < 1) {
				$('.dayCountAlret').css({
					'display' : 'inline'
				});
			} else {
				$('.dayCountAlret').css({
					'display' : 'none'
				});
			}
		}); //datepicker1 변동 시 날짜 변경
		$('#datepicker2').change(function() {
			toDay = $('#datepicker1').val();
			forDay = $('#datepicker2').val();
			var c = getDateDiff(forDay, toDay) + 1;
			console.log(c);
			if (c) {
				$('.dayCount').text(c);
			} else if (c == 0) {
				$('.dayCount').text("0");
			}
			if (c < 1) {
				$('.dayCountAlret').css({
					'display' : 'inline'
				});
			} else {
				$('.dayCountAlret').css({
					'display' : 'none'
				});
			}
		}); //datepicker2 변동 시 날짜 변경

	});
</script>

<title>휴가신청서</title>
</head>
<body>
	<div class="content">
		<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false" />
		<form name="frm" method="post">
			<select class="docSelecter"
				onchange="if(this.value) location.href=(this.value)">
				<option value="${contextPath}/Approval/draft.do">기안서</option>
				<option value="${contextPath}/Approval/vacation.do" selected>휴가신청서
				</option>
			</select>

			<div class="docName">
				<h1>기안서</h1>
			</div>
			<div class="deptContent">
				<div class="signtable">
					<table class="signtableleft">
						<tr>
							<th>이름</th>
							<td>${loginUser.ename}</td>
						</tr>
						<tr>
							<th>직책</th>
							<td>${loginUser.rank}</td>
						</tr>
						<tr>
							<th>소속</th>
							<td>${loginUser.dname}</td>
						</tr>
					</table>

					<table class="signtableright" border="1">
						<tr>
							<th>${loginUser.rank}</th>
							<th>${createdMidUser.rank}</th>
							<th>${createdFinUser.rank}</th>
						</tr>
						<tr>
							<td style="vertical-align: top"><input type="text"
								name="firUser" style='width: 80px; text-align: center;'
								value="${loginUser.ename}" disabled> <input
								type="hidden" name="firUser"
								style='width: 80px; text-align: center;'
								value="${loginUser.ename}"> <br> <span
								style="color: red;"></span></td>
							<td style="vertical-align: top"><input type="text"
								name="midUser" style='width: 80px; text-align: center;'
								value="${createdMidUser.ename}" disabled> <input
								type="hidden" name="midUser"
								style='width: 80px; text-align: center;'
								value="${createdMidUser.ename}"> <br> <span
								style="color: red;"></span></td>
							<td style="vertical-align: top"><input type="text"
								name="finUser" style='width: 80px; text-align: center;'
								value="${createdFinUser.ename}" disabled> <input
								type="hidden" name="finUser"
								style='width: 80px; text-align: center;'
								value="${createdFinUser.ename}"> <br> <span
								style="color: red;"></span></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>

				<div class="inputarea">
					<table>
						<tr>
							<td>제목<br> <input class="inputTitle" type="text"
								name="title" required placeholder="제목을 입력해주세요." maxlength="50">
							</td>
						</tr>
						<tr>
							<td><br> 1.다음 중 요청하고자 하는 휴가의 종류로 알맞은 것을 고르세요.<br>
								<span> <input type="radio" name="leaveradio" value="연차"
									class="modifySelect1" id="kindsSelect1" checked="checked">
									<label for="kindsSelect1">연차</label>
							</span> <span> <input type="radio" name="leaveradio" value="병가"
									class="modifySelect2" id="kindsSelect2"> <label
									for="kindsSelect2">병가</label>
							</span> <span> <input type="radio" name="leaveradio" value="휴가"
									class="modifySelect3" id="kindsSelect3"> <label
									for="kindsSelect3">휴가</label>
							</span> <span> <input type="radio" name="leaveradio" value="기타"
									class="modifySelect4" id="kindsSelect4"> <label
									for="kindsSelect4">기타</label>
							</span></td>
						</tr>
						<tr>
							<td><br> 1-2. 요청한 휴가의 기간을 입력하세요.<br> <input
								type="text" id="datepicker1" name="datepicker1"
								placeholder="년/월/일" readonly style="height: 35px;"> ~ <input
								type="text" id="datepicker2" name="datepicker2"
								placeholder="년/월/일" readonly style="height: 35px;"> (<span
								class="dayCount"></span>일 간) <span class="dayCountAlret"
								style="color: red; display: none;">날짜를 올바르게 선택하세요.</span></td>

						</tr>
						<tr>
							<td><br> 2. 1번 보기를 선택한 사유를 쓰세요.<br> <textarea
									class="inputContent" name="reason" placeholder="사유을 입력해주세요."
									required maxlength="2000"></textarea></td>
						</tr>
					</table>

					<br>
					<div class="bottomBt">
						<button type="button" onclick="vacationCheck()">등록</button>
						<button type="button" onclick="docModify()" disabled>수정</button>
						<button type="button" onclick="docDelete()" disabled>삭제</button>
						<button type="button" onclick="docApprov()" disabled>승인</button>
						<button type="button" onclick="docReturn()" disabled>반려</button>
						<button type="button" onclick="docCancle()">취소</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>