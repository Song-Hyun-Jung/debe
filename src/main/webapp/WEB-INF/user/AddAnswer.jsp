<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>답변 작성</title>
<style>
#btnSubmit{
	width:90px;
	height:30px;
	background-color:#a9173d;
	color:white;
	font-size:15px;
}
table{
	position: static;
}
tr.answerInfo{
	border-collapse:collapse;
	border:1px solid black;
	height:40px;
}
table.answers{
	border-collapse:collapse;
}
</style>
<script>
function addAnswer() {
	if (form.textarea.value == "") {
		alert("내용을 입력하십시오.");
		form.textarea.focus();
		return false;
	}
	form.action = targetUri;
	form.method="POST";
	form.submit();
}
</script>
</head>
<body>
<%session.setAttribute("userNickname", "최"); %>
<%session.setAttribute("userLevel", 2); %>
<%session.setAttribute("userId", "20170001"); %>

	<div align="center">
	<form name=addMyAnswer method=post action="<c:url value='/user/addanswer'/>">
		<table class="answers">
			<tr class="answerInfo" style="padding:10px 0px 10px 0px">
				<td colspan="2">답변자:${userNickname}&nbsp;&nbsp;&nbsp;경험치:${userLevel}</td>
			</tr> 
			<tr class="answerInfo">
				<td colspan="2"><textarea cols=100 rows=15 class="code" name="answerCodes"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right" style="padding:10px 0px 0px 0px">
				<input id="btnSubmit" type="submit" value="등록" onClick="addAnswer()"></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>