<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>질문 목록</title>
<script language = "JavaScript">
function filterQuestion(targetUri) {
	form1.action = targetUri;
	form1.submit();
}

function filterSolved(targetUri) {
	form2.action = targetUri
	form2.submit();
}

function addQuestion(targetUri) {
	form2.action = targetUri
	form2.submit();
}
</script>
<style>
table.question{
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
div.add{
	margin-top:10px;
}
div.filter{
	margin-bottom:10px;
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
<h2>Q&A</h2>
</div>

   <form name="form1" method=post>
	<div align="right" class="filter">
		<select id="Language" name="language">
			<option value="no">선택 안 함</option>
			<option value="C언어">C언어</option>
			<option value="JAVA">Java</option>
			<option value="Python">파이썬</option>
			<option value="HTML">HTML</option>
			<option value="SQL">SQL</option>
			<option value="기타">기타</option>
		</select>
	
		<select id="subjectName" name="subjectTitle">
			<option value="no">선택 안 함</option>
			<c:forEach var="subject" items="${subjectList}">
				<option value="${subject.subjectId}">${subject.subjectTitle}</option>
			</c:forEach>
		</select>
		
		
		<button type="button" class="button" 
			onClick="filterQuestion('<c:url value ='/user/filterquestion'>
										<c:param name='filter' value='filterLS' /> 
									</c:url>')">필터링</button>	<!-- filter 파라미터로 어떤 필터링 버튼인지 구분 -->
	</div>
   </form>
   
   <form>
	<table border="1" width="100%" class="question">
		<colgroup>
			<col style="width:20%">
			<col style="width:45%">
			<col style="width:20%">
			<col style="width:15%">
		</colgroup>
			
		<tr>
			<th>과목명</th>
			<th>글 제목</th>
			<th>해결상태</th>
			<th>작성일자</th>
		</tr>
		
		<!-- 한 페이지 당 질문 5개씩 -->
		<c:forEach var="question" items="${questionList}" varStatus="status" begin="${questionIndex}" end="${questionIndex+4}">
			<tr>
				<td class="info">${question.subjectTitle}</td>
				<td class="info">
					<a href="<c:url value ='/user/viewquestion'>
								<c:param name='questionCode' value='${question.postId}' />
							</c:url>"> ${question.title}
					</a>
				</td>			<!-- 제목 누르면 해당 질문글 viewQuestion -->
				<td class="info">${question.solve}</td>
				<td class="info">${question.postDate}</td>
			</tr>
		</c:forEach>
	</table>
	
	<p><br>
	<div align="center">
	<c:forEach var="question" items="${questionList}" varStatus="status">
			<c:if test="${status.count % 5 eq 1}">
		  	<a href="<c:url value ='/user/filterquestion'>
								<c:param name='questionIndex' value='${status.index}' />
							</c:url>" id="indexfont"> <font id="indexfont"><fmt:formatNumber value="${(status.index)/5 + 1}" pattern="#" />&nbsp;&nbsp;|</font>
			</a>
		  </c:if>
	</c:forEach>
	</div>	 
   </form>
	
   <form name="form2" method=post>
	<div align="right" class="add">
		<select id="dataSort" name="solved">
			<option value="y">해결완료</option>
			<option value="n">미해결</option>
		</select>
		<button type="button" class="button" onclick="filterSolved('<c:url value ='/user/filterquestion'>
																		<c:param name='filter' value='filterSolved' />
																	</c:url> ')">필터링</button>
	</div>
	<div align="right" class="add">
		<button type="button" class="button" onclick="addQuestion('<c:url value='/user/addquestion/form' />')">문제등록</button>
	</div>
   </form>
</body>
</html>