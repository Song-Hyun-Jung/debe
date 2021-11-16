<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyInfo</title>
<style>
table.mypage{
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
}
td.info{
	height: 20px;
}
div.mypage{
	width: 60%;
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

	<div class="mypage">
		<b>My Page</b>
		<p>
	</div>
	
	<b>수강과목</b> 
	<p>
	<table border="1" width="60%" height="100px" class="mypage">
		<tr>
			<td class="info"></td>
		</tr>
	</table> 
	<div align="right">
		<button type="button" class="button">수정</button>
	</div>
	<p><p>
	
	<b>나의 질문</b> 
	<p>
	<table border="1" width="60%" class="mypage">
		<tr>
			<td class="info"></td>
		</tr>
		<tr>
			<td class="info"></td>
		</tr>
		<tr>
			<td class="info"></td>
		</tr>
		<tr>
			<td class="info"></td>
		</tr>
		<tr>
			<td class="info"></td>
		</tr>
		<tr>
			<td class="info"></td>
		</tr>
	</table> 

</body>
</html>