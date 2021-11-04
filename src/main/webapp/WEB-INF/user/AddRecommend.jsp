<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제등록</title>
<style>
#btnSubmit{
	width:90px;
	height:30px;
	background-color:#a9173d;
	color:white;
}
#menu{
	position: relative;
	top:0px;
	left:250px;
}
table{
	position: static;
}
td.info{
	font-weight:900;
	font-size:20px;
}
</style>
</head>
<body>
	<div id="menu"><h2>문제등록 하기</h2></div>
	<div align="center">
	<form name=addRecommend method=post>
		<table>
			<tr height="30" width="70">
				<td class="info">제목</td>
				<td colspan="2"><input type="text" name="title" size="80"></td>
				<td><input id="btnSubmit" type="submit" value="등록"></td>
			</tr>
			<tr height="50">
				<td class="info">난이도</td>
				<td style="padding:0px 0px 0px 45px, margin:10px;">
					<select name="subject" style="padding:5px 0px 5px 0px">
						<option value="0" selected>상, 중, 하</option>
						<option value="1">상</option>
						<option value="2">중</option>
						<option value="3">하</option>
					</select>
				</td>
				<td style="padding:0px 0px 0px 80px">알고리즘  <input type="text" name="title" size="20px"> </td>
			</tr>
			<tr>
				<td colspan="4"><textarea cols=100 rows=20 class="code" name="inputCode"></textarea></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>