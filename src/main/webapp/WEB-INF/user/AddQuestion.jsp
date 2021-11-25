<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>질문 작성</title>
<script language = "JavaScript">
function addQuestion() {
	if(addQuesiton.title == "") { alert("제목을 입력하십시오."); } 
	if(addQuesiton.subjectId == "") { alert("과목을 선택하십시오."); }
	if(addQuesiton.questionLanguage == "") { alert("언어를 선택하십시오."); } 
	if(addQuesiton.questionContent == "") { 
		alert("질문 내용을 입력하십시오.");
		form.textarea.focus();
	}
	form.submit();
}
</script>
<style>
#btnSubmit{
	width:90px;
	height:35px;
	background-color:#a9173d;
	color:white;
}
#question{
	position: relative;
	top:0px;
	left:250px;
}
table.answers{
	position: static;
}
td.info{
	font-weight:900;
	font-size:20px;
}
</style>
</head>
<body>
	<div align="center">
		<%@ include file="/WEB-INF/user/top.jsp" %>
	</div>

	<div id="question"><h2>질문하기</h2></div>
	<div align="center">
	<form name=addQuestion method=post action="<c:url value='/user/addquestion' />">
		<table class="answers">
			<tr height="30" width="70">
				<td class="info">제목</td>
				<td align="center" colspan="4"><input type="text" name="title" size="70" style="height:25px"></td>
				<td><input id="btnSubmit" type="submit" value="등록" onclick="addQuestion()"></td>
			</tr>
			<tr height="50">
				<td class="info">과목</td>
				<td style="padding:0px 0px 0px 20px">
					<select name="subjectId" style="padding:5px 0px 5px 0px">
						<option value="0" selected>과목명</option>
						<option value="323">알고리즘</option>
						<option value="324">빅데이터처리</option>
						<option value="321">모바일응용</option>
						<option value="313">네트워크</option>
						<option value="221">자료구조</option>
						<option value="322">데이터베이스프로그래밍</option>
					</select>
				</td>
				<td class="info">사용언어</td>
				<td colspan="2">
					<select name="questionLanguage" style="padding:5px 0px 5px 0px">
						<option value="0" selected>사용언어</option>
						<option value="C언어">C언어</option>
						<option value="JAVA">JAVA</option>
						<option value="Python">Python</option>
						<option value="SQL">SQL</option>
						<option value="HTML">HTML</option>
						<option value="C++">C++</option>
						<option value="기타">기타</option>
					</select>
				</td>
				<td> </td>
			</tr>
			<tr>
				<td colspan="6"><textarea cols=100 rows=20 class="code" name="questionContent"></textarea></td>
			</tr>
		</table>
	</form>
	</div>

</body>
</html>