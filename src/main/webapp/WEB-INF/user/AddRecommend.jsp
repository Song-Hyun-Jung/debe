<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추천 문제 작성</title>
<script>
function addR() {
	if(form.title == "") { alert("제목을 입력하십시오."); } 
	if(form.algorithm == "") { alert("사용된 알고리즘을 입력하세요."); } 
	if(form.recommendContent == "") { 
		alert("추천 문제 내용을 입력하십시오.");
		form.textarea.focus();
	}
	form.submit();
}
</script>
<style>
#btnSubmit{
	width:90px;
	height:30px;
	background-color:#a9173d;
	color:white;
}
#recommendMenu{
	position: relative;
	top:0px;
	left:250px;
}
table.recommend{
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
	<div id="recommendMenu"><h2>추천 문제 작성</h2></div>
	<div align="center">
	<form name=form method=post action="<c:url value='/user/addRecommend' />">
		<table class="recommend">
			<tr height="30" width="70">
				<td class="info">제목</td>
				<td colspan="2"><input type="text" name="title" size="80"></td>
				<td><input id="btnSubmit" type="button" value="등록" onClick="addR()"></td>
			</tr>
			<tr height="50">
				<td class="info"> 난이도 &nbsp; </td>
				<td style="padding:0px 0px 0px 45px, margin:10px;">
					<select name="difficulty" style="padding:5px 0px 5px 0px">
						<option value="high">상</option>
						<option value="mid" selected>중</option>
						<option value="low">하</option>
					</select>
				</td>
				<td style="padding:0px 0px 0px 80px"> 알고리즘 &nbsp; <input type="text" name="algorithm" size="20px"> </td>
			</tr>
			<tr>
				<td colspan="4"><textarea cols=100 rows=20 class="code" name="recommendContent"></textarea></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>