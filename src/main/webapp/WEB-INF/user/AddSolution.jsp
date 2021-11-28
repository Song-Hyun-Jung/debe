<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
tr.answerInfo{
	border-collapse:collapse;
	border:1px solid black;
	height:40px;
}
table.answers{
	position:static;
	border-collapse:collapse;
}
</style>
<script>
function addSolution(targetUri) {
	//alert(${requestScope.questionCode});
	if (addMySolution.solutionCodes.value == "") {
		alert("내용을 입력하십시오.");
		addMySolution.solutionCodes.focus();
		return false;
	}
	addMySolution.action = targetUri;
	addMySolution.method="POST";
	addMySolution.submit();
}
</script>
</head>
<body>
<% //나중에 지울것
session.setAttribute("userId", 20190000); 
session.setAttribute("userNickname", "최");
session.setAttribute("userLevel", 2); 
%>
	<div align="center">
		<%@ include file="/WEB-INF/user/top.jsp" %>
	</div>
	<div align="center">
	<form name="addMySolution">
		<table class="answers">
			<tr class="answerInfo" style="padding:10px 0px 10px 0px">
				<td colspan="2">답변자:${userNickname}&nbsp;&nbsp;&nbsp;경험치:${userLevel}</td>
			</tr>
			<tr class="answerInfo">
				<td colspan="2"><textarea cols=100 rows=15 class="code" name="solutionCodes"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right" style="padding:10px 0px 0px 0px">
				<input id="btnSubmit" type="button" value="등록" onclick="addSolution('<c:url value ='/user/addRecommendSolution'>
												<c:param name='recommendCode' value='${requestScope.recommendCode}'/>
												</c:url>')"></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>