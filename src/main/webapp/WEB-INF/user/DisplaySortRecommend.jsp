<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>추천 코딩 문제 목록</title>
<script language = "JavaScript">

function sort(targetUri) {
	form.action = targetUri;
	form.submit();	
}

function addRecommend(targetUri) {
	add.action = targetUri;
	add.submit();
}
</script>

<style>
table.recommend{
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
}
td.info{
	height: 20px;
}
.button {
	border: none;
	width: 80px;
	color: white;
	background-color: rgb(137, 21, 52);
}
#indexfont{
	text-decoration:none;
	color: black;
	font-weight:bold;
}
</style>

</head>
<body>
<div align="center">
		<%@ include file="/WEB-INF/user/top.jsp" %>
</div>
<div>
<b>추천 코딩 문제</b>
</div>

<form name="form" method="post">
	<div align="right">
		<select id="dataSort" name="sortRecommend">
			<option value="recent">최신순</option>
			<option value="difficulty">난이도순</option>
			<option value="recommend">추천순</option>
		</select>

		<button type="button" class="button" 
			onClick="sort('<c:url value ='/user/sortList' />')">정렬</button>
	</div>
</form>

<form>
	<p>
	<table border="1" width="100%" class="recommend">
		<colgroup>
			<col style="width:45%">
			<col style="width:20%">
			<col style="width:15%">
			<col style="width:10%">
			<col style="width:10%">
		</colgroup>
		
		<tr>
			<th>글 제목</th>
			<th>알고리즘</th>
			<th>난이도</th>
			<th>추천수</th>
			<th>작성일자</th>
		</tr>
	
		<c:forEach var="recommend" items="${recommendList}" varStatus="status" begin="${recommendIndex}" end="${recommendIndex + 4}">
			<tr>
			<td class="info">
				<a href="<c:url value='/user/viewrecommend'> <c:param name="recommendCode" value='${recommend.postId}' /> </c:url>">
					${recommend.title}</a></td>
			<td class="info">${recommend.algorithm}</td>
			<td class="info">${recommend.difficulty}</td>
			<td class="info">${recommend.recommendCount}</td>
			<td class="info">${recommend.postDate}</td>
		</tr>
		</c:forEach>
	</table>
	
	<p><br>
	<div align="center">
		<c:forEach var="recommend" items="${recommendList}" varStatus="status">
			<c:if test="${status.count % 5 eq 1}">
		  	<a href="<c:url value ='/user/sortList'> <c:param name='recommendIndex' value='${status.index}' /> </c:url>"  id="indexfont"> 
		  		<font><fmt:formatNumber value="${(status.index)/5 + 1}" pattern="#" />&nbsp;&nbsp;|</font> </a>
		  </c:if>
		</c:forEach>
	</div>
</form>

<form name="add">
	<div align="right">
		<button type="button" class="button" onClick="addRecommend('<c:url value='/user/addRecommend/form' />')"> 문제등록 </button>
	</div>
</form>

</body>
</html>