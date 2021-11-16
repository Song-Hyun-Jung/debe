<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>질문 목록</title>
<style>
table {
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
}
td {
	height: 20px;
}
.button {
	border: none;
	width: 80px;
	color: white;
	background-color: rgb(137, 21, 52);
}
</style>

</head>

<body>

<div>
<b>Q&A</b>
</div>

<form name=displayQuestion method=post action="<c:url value='/user/addquestion/form' />">
	<div align="right">
		<select id="Language">
			<option value="C/C++">C/C++</option>
			<option value="Java">Java</option>
			<option value="Python">파이썬</option>
		</select>
	
		<select id="subjectName">
			<option value="network">네트워크</option>
			<option value="DBprogramming">데이터베이스프로그래밍</option>
			<option value="mobileApp">모바일응용</option>
			<option value="algorithm">알고리즘</option>
		</select>
		
		<button type="button" class="button">필터링</button>
	</div>
	
	<table border="1" width="100%">
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
		
		<c:forEach var="question" items="${questionList}">
			<tr>
				<td>${question.subjectTitle}</td>
				<td>
					<a href="<c:url value ='/user/viewquestion'>
								<c:param name='questionCode' value='${question.postId}' />
							</c:url>"> ${question.title}
					</a>
				</td>			<!-- 제목 누르면 해당 질문글 viewQuestion -->
				<td>${question.solve}</td>
				<td>${question.postDate}</td>
			</tr>
		</c:forEach>
	</table>
	
	<div align="right">
		<select id="dataSort">
			<option value="solved">해결완료</option>
			<option value="notsolved">미해결</option>
		</select>
		<button type="button" class="button">필터링</button>
	</div>
	<div align="right">
		<button type="submit" class="button">문제등록</button>
	</div>
</form>
</body>
</html>
