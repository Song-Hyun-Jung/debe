<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 관리</title>
<script language = "JavaScript">
function deleteQuestion(targetUri) {
	question.action = targetUri;
	question.method="GET";
	question.submit();
}
function deleteRecommend(targetUri) {
	recommend.action = targetUri;
	recommend.method="GET";
	recommend.submit();
}


</script>
<style>
table.post{
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
}
td.info{
	height: 20px;
}
.delete{
	color:rgb(230, 10, 0);
	font-weight:bold;
	text-decoration: none;
	
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
a { text-decoration:none !important }
a:link{color:black;}
a:visited{color:black;}
a:hover{color:blue;}
</style>
</head>

<body>
<div align="center">
		<%@ include file="/WEB-INF/user/top.jsp" %>
</div>
<h2>관리자 게시판 관리</h2>

<h3>질문 목록</h3>
  <form name="question" >
	<table border="1" width="100%" class="post">
		<colgroup>
			<col style="width:20%">
			<col style="width:65%">
			<col style="width:15%">
		</colgroup>
		<tr>
			<th>글 id</th>
			<th>제목</th>
			<th>관리</th>
		</tr>
		
		<!-- 한 페이지 당 질문 5개씩 -->
		<c:forEach var="question" items="${questionList}" begin="${questionIndex}" end="${questionIndex+4}">
			<tr>
				<td class="info">${question.postId}</td>
				<td class="info">
					<a href="<c:url value ='/user/viewquestion'>
								<c:param name='questionCode' value='${question.postId}' />
							</c:url>"> ${question.title}
					</a>
				</td>
				<td class="info"> <a href="<c:url value ='/user/deletequestion'>
								<c:param name='admin' value='admin' />
								<c:param name='questionCode' value='${question.postId}' />
								<c:param name='userId' value='${question.userId}' />
							</c:url>" class="delete"> 삭제하기 </a> 
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<p>
	<div align="center">
		<c:forEach var="question" items="${questionList}" varStatus="statusQ">
			<c:if test="${statusQ.count % 5 eq 1}">
		  	<a href="<c:url value ='/user/adminPostList'> <c:param name='questionIndex' value='${statusQ.index}' /> </c:url>"  id="indexfont"> 
		  		<font><fmt:formatNumber value="${(statusQ.index)/5 + 1}" pattern="#" />&nbsp;&nbsp;|</font> </a>
		  </c:if>
		</c:forEach>
	</div>
   </form>
   
 <p><br>
 <h3>추천 코딩 문제 목록</h3>
  <form name="recommend">
	<table border="1" width="100%" class="post">
		<colgroup>
			<col style="width:20%">
			<col style="width:65%">
			<col style="width:15%">
		</colgroup>
			
		<tr>
			<th>글 id</th>
			<th>제목</th>
			<th>관리</th>
		</tr>
		
		<!-- 한 페이지 당 추천 코딩 문제 5개씩 -->
		<c:forEach var="recommend" items="${recommendList}" begin="${recommendIndex}" end="${recommendIndex+4}">
			<tr>
				<td class="info">${recommend.postId}</td>
				<td class="info">
					<a href="<c:url value ='/user/viewrecommend'>
								<c:param name='recommendCode' value='${recommend.postId}' />
							</c:url>"> ${recommend.title}
					</a>
				</td>		
				<td class="info"> <a href="<c:url value ='/user/deleteRecommend'>
								<c:param name='admin' value='admin' />
								<c:param name='recommendCode' value='${recommend.postId}' />
								<c:param name='userId' value='${recommend.userId}' />
							</c:url>"  class="delete"> 삭제하기 </a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<p>
	<div align="center">
		<c:forEach var="recommend" items="${recommendList}" varStatus="statusR">
			<c:if test="${statusR.count % 5 eq 1}">
		  	<a href="<c:url value ='/user/adminPostList'> <c:param name='recommendIndex' value='${statusR.index}' /> </c:url>"  id="indexfont"> 
		  		<font><fmt:formatNumber value="${(statusR.index)/5 + 1}" pattern="#" />&nbsp;&nbsp;|</font> </a>
		  </c:if>
		</c:forEach>
	</div>
   </form>

</body>
</html>