<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문 작성</title>
<style>
#btnSubmit{
	width:90px;
	height:35px;
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
	<div id="menu"><h2>질문하기</h2></div>
	<div align="center">
	<form name=addQuestion method=post>
		<table>
			<tr height="30" width="70">
				<td class="info">제목</td>
				<td align="center" colspan="4"><input type="text" name="title" size="70"></td>
				<td><input id="btnSubmit" type="submit" value="등록"></td>
			</tr>
			<tr height="50">
				<td class="info">과목</td>
				<td style="padding:0px 0px 0px 20px">
					<select name="subject" style="padding:5px 0px 5px 0px">
						<option value="0" selected>과목명</option>
						<option value="1">알고리즘</option>
						<option value="2">빅데이터처리</option>
						<option value="3">모바일응용</option>
						<option value="4">네트워크</option>
						<option value="5">자료구조</option>
						<option value="6">데이터베이스프로그래밍</option>
						<option value="7">기타</option>
					</select>
				</td>
				<td class="info">사용언어</td>
				<td colspan="2">
					<select name="language" style="padding:5px 0px 5px 0px">
						<option value="0" selected>사용언어</option>
						<option value="1">C언어</option>
						<option value="2">JAVA</option>
						<option value="3">Python</option>
						<option value="4">C++</option>
						<option value="5">기타</option>
					</select>
				</td>
				<td> </td>
			</tr>
			<tr>
				<td colspan="6"><textarea cols=100 rows=20 class="code" name="inputCode"></textarea></td>
			</tr>
		</table>
	</form>
	</div>

</body>
</html>