<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	function page(idx) {
		var pagenum = idx;
		var contentnum = $("#contentnum option:selected").val();
		location.href = "${pageContext.request.contextPath}/list?pagenum="
				+ pagenum + "&contentnum=" + contentnum;
	}
</script>
</head>
<body>
	<select name="contentnum" id="contentnum">
		<!-- 10개씩 , 20개씩 , 30개씩 사용자가 원하는 만큼 게시글을 볼 수 있도록 작성했으나 , 추후 구현 예정입니다 -->
		<option value="10">10</option>
		<option value="20">20</option>
		<option value="30">30</option>
	</select>
	<table>
		<thead>
			<tr>
				<th>tid</th>
				<th>content</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="k" items="${list}">
				<tr>
					<td>${k.tid}</td>
					<td>${k.content}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<!-- 왼쪽 화살표 --> <c:if test="${page.prev}">
						<a style="text-decoration: none;"
							href="javascript:page(${page.getStartPage()-1});">&laquo;</a>
					</c:if> <!-- 페이지 숫자 표시 --> <c:forEach begin="${page.getStartPage()}"
						end="${page.getEndPage()}" var="idx">
						<a style="text-decoration: none;" href="javascript:page(${idx});">${idx}</a>
					</c:forEach> <!-- 오른쪽 화살표 --> <c:if test="${page.next}">
						<a style="text-decoration: none;"
							href="javascript:page(${page.getEndPage()+1});">&raquo;</a>
					</c:if>

				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>