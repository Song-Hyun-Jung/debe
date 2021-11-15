<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문 보기</title>
<style>
#btnSubmit{
	width:90px;
	height:30px;
	background-color:#a9173d;
	color:white;
	font-size:15px;
}
#title{
	height:20px;
}
table{
	position: static;
}
tr.answerInfo{
	border-collapse:collapse;
	border:1px solid black;
}
table.answers{
	border-collapse:collapse;
}

td.info{
	font-weight:900;
	font-size:20px;
}
td.info-right{
	font-weight:900;
	font-size:20px;
	text-align:right;
}
hr{
	color:#a9173d;
}

</style>
</head>
<body>
	<div align="center">
	<form name=questionInfo method=post>
		<table>
			<tr>
				<td class="info">제목</td>
				<td align="center" colspan="3"><input id="title" type="text" name="questionTitle" value="${Question.title}" size="75"></td>
				<td align="right" width="10px" height="10px"><input type="image" src="<c:url value='/images/beforeBookmark.jpg' />"  style="max-width:40%"></td>
			</tr>
			<tr height="50">
				<td class="info">해결상태</td>
				<td>${Question.solve}</td>	<!-- y면 해결 n면 미해결으로 뜨도록 나중에 수정 -->
				<td colspan="3"> </td>
			</tr>
			<tr>
				<td colspan="5"><textarea cols=100 rows=15 class="code" name="questionCode">${Question.postContent}</textarea></td>
			</tr>
			<tr>
				<td class="info">질문자:</td>
				<td>${Question.userId}</td>		<!-- 일단 userId로 함 나중에 닉네임으로 수정 -->
				<td> </td>
				<td class="info-right" colspan="2">과목명, 질문날짜</td>
			</tr>
			<tr>
				<td style="padding:10px 0px 0px 0px"><input id="btnSubmit" type="submit" value="글 삭제"></td>
				<td colspan="3"></td>
				<td><input id="btnSubmit" type="submit" value="답변등록"></td>			
			</tr>
		</table>
	</form>
	</div>
	<hr/>
	
	<div align="center">
	<form name=othersAnswer method=post>
		<c:forEach var="answer" items="${AnswerList}">
		<table class="answers">
			<tr class="answerInfo" style="padding:10px 0px 10px 0px">
				<td>답변자:${answer.userId}  경험치: </td>
				<td align="right">
					<img src=<c:url value='/images/adoptButton.jpg' /> id="adoptState${answer.answerId}" style="max-width:10%" >
					<script>
					//조건별 이미지 뜨도록...아직 안됨...
						var tmp = "<c:out value='${answer.answerAdopt}'/>";
						vat answerId = "<c:out value='${answer.answerId}'/>";
						var adoptImage = document.getElementById("adoptState"+answerId);
						if(tmp == 'y'){
							alert('y');
						}
					</script>			
				</td>
			</tr>
			<tr class="answerInfo">
				<td colspan="2"><textarea cols=100 rows=15 class="code" name="answerCodes">${answer.answerContent}</textarea></td>
			</tr>
			<tr>
				<td align="left" style="padding:10px 0px 0px 0px"><input id="btnSubmit" type="submit" value="삭제"></td>
				<td class="info-right" style="padding:10px 0px 0px 0px"><input id="btnSubmit" type="submit" value="채택"></td>
			</tr>
		</table>
		</c:forEach> 
	</form>
	</div>
	
	<hr/>
	<div align="center">
	<table class="answers" width="50%">
			<tr style="padding:10px 0px 10px 0px">
				<td class="info">관련 질문 </td>
			</tr>
			<tr class="answerInfo" height="30px">
				<td>                 </td>
			</tr>
			<tr class="answerInfo" height="30px">
				<td>                 </td>
			</tr>
		</table>
	</div>
</body>
</html>