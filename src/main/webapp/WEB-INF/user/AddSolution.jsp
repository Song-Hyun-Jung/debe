<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>
	<div align="center">
		<%@ include file="/WEB-INF/user/top.jsp" %>
	</div>
	<div align="center">
	<form name=addMySolution method=post>
		<table class="answers">
			<tr class="answerInfo" style="padding:10px 0px 10px 0px">
				<td colspan="2">답변자:  경험치: </td>
			</tr>
			<tr class="answerInfo">
				<td colspan="2"><textarea cols=100 rows=15 class="code" name="answerCodes"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right" style="padding:10px 0px 0px 0px"><input id="btnSubmit" type="submit" value="등록"></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>