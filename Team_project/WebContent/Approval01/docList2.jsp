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
<link rel="stylesheet" href="${contextPath}/Approval01/css/jquery-ui.css" />

<script src="${contextPath}/Approval01/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/Approval01/js/jquery-ui.min.js"></script>
<script src="${contextPath}/Approval01/js/jquery.easing.1.3.js"></script>
<script src="${contextPath}/Approval01/js/prefixfree.min.js"></script>
<script src="${contextPath}/Approval01/js/main.js"></script>
<script>
	
</script>
<title>내 문서함</title>

</head>
<body>


	<!--전체 컨텐츠-->
	<div class="content">
		<jsp:include page="/WEB-INF/GNB/header.jsp" flush="false"/>

		<div class="docName">
			<h1>내 문서함</h1>
		</div>
		<!--깊은 컨텐츠-->
		<div class="deptContent" style="margin: 10px;">
			<!--문서종류, 검색값, 검색버튼-->
			<div>
				<form>
					<select name="searchType">
						<option value="1" selected="">결재종류</option>
						<option value="2">문서제목</option>
					</select>
					<input type="text" class="searchInput" name="searchKey">
					<input type="submit" class="search" value="검색"
					style="height: 30px;" onclick="docListSearchCheck()">
				</form>
			</div>
			<!--//문서종류, 검색값, 검색버튼-->

			<!--문서 목록표-->
			<table class="docListTable" border="1">
				<tr>
					<th width="60px">수/발신</th>
					<th width="120px">결재종류</th>
					<th width="80px">결재상태</th>
					<th width="700px">문서제목</th>
					<th width="100px">등록일자</th>
				</tr>
				<c:forEach items="${approvalList}" var="approval" varStatus="15">
				<tr>
					<td>${approval.txtcall}</td>
					<td>${approval.applist}</td>
					<td>${approval.progress}</td>

					<c:choose>
						<c:when test="${approval.applist == '기안서'}">
							<td><a href = "${contextPath}/Approval/draftWait.do?txtnum=${approval.txtnum}">${approval.txtname}</a></td>
						</c:when>
						<c:when test="${approval.applist == '휴가신청서'}">
							<td><a href = "${contextPath}/Approval/vacationWait.do?txtnum=${approval.txtnum}">${approval.txtname}</a></td>
						</c:when>
					</c:choose>	

					<td>${approval.entrydate}</td>
				</tr>
				</c:forEach>
			</table>
			
			<table align="center" border="1"  width="80%"  >
  <tr height="10" align="center"  bgcolor="lightgreen">
     <td >글번호</td>
     <td >작성자</td>              
     <td >제목</td>
     <td >작성일</td>
  </tr>
<c:choose>
  <c:when test="${articlesList ==null }" >
    <tr  height="10">
      <td colspan="4">
         <p align="center">
            <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${articlesList !=null }" >
    <c:forEach  var="article" items="${articlesList }" varStatus="articleNum" >
     <tr align="center">
	<td width="5%">${articleNum.count}</td>
	<td width="10%">${article.id }</td>
	<td align='left'  width="35%">
	    <span style="padding-right:30px"></span>    
	   <c:choose>
	      <c:when test='${article.level > 1 }'>  
	         <c:forEach begin="1" end="${article.level }" step="1">
	             <span style="padding-left:10px"></span> 
	         </c:forEach>
	         <span style="font-size:12px;">[답변]</span>
                   <a class='cls1' href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
	          </c:when>
	          <c:otherwise>
	            <a class='cls1' href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
	          </c:otherwise>
	        </c:choose>
	  </td>
	  <td  width="10%"><fmt:formatDate value="${article.writeDate}" /></td> 
	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
</table>

<div class="cls2">
 <c:if test="${totArticles != null }" >
      <c:choose>
        <c:when test="${totArticles >100 }">  <!-- 글 개수가 100 초과인경우 -->
	      <c:forEach   var="page" begin="1" end="10" step="1" >
	         <c:if test="${section >1 && page==1 }">
	          <a class="no-uline" href="${contextPath }/board/listArticles.do?section=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp; pre </a>
	         </c:if>
	          <a class="no-uline" href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${(section-1)*10 +page } </a>
	         <c:if test="${page ==10 }">
	          <a class="no-uline" href="${contextPath }/board/listArticles.do?section=${section+1}&pageNum=${section*10+1}">&nbsp; next</a>
	         </c:if>
	      </c:forEach>
        </c:when>
        <c:when test="${totArticles ==100 }" >  <!--등록된 글 개수가 100개인경우  -->
	      <c:forEach   var="page" begin="1" end="10" step="1" >
	        <a class="no-uline"  href="#">${page } </a>
	      </c:forEach>
        </c:when>
        
        <c:when test="${totArticles< 100 }" >   <!--등록된 글 개수가 100개 미만인 경우  -->
	      <c:forEach   var="page" begin="1" end="${totArticles/10 +1}" step="1" >
	         <c:choose>
	           <c:when test="${page==pageNum }">
	            <a class="sel-page"  href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page } </a>
	          </c:when>
	          <c:otherwise>
	            <a class="no-uline"  href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page } </a>
	          </c:otherwise>
	        </c:choose>
	      </c:forEach>
        </c:when>
      </c:choose>
    </c:if>
</div>    
<br><br>
<a  class="cls1"  href="${contextPath}/board/articleForm.do"><p class="cls2">글쓰기</p></a>
</body>
			
			
			<button class="docCreate" name="docCreate" type="button" onclick="location.href='${contextPath}/Approval/draft.do'">작성</button>

</body>
</html>