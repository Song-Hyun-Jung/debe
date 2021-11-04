<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
#btnLogin{
	width:230px;
	height:30px;
	background-color:#a9173d;
	color:white;
	font-size:20px;
}
td{
	width: 100px;
	padding:10px 0px 10px 0px;
}
#caution{
	width:300px;
	text-align:center;
	
}
#displayLogo{
	position: static;
}

</style>
</head>
<body>
	<div align="left" style="padding:0px 0px 0px 50px">
		<img src="../images/schoolLogo.jpg" width="280px" height="100px" id="displayLogo">
	</div>
	<hr size="5" width="100%" align="left" color="#a9173d" noshade>
	<div align="center" style="padding:30px 0px 0px 0px">
	<form name="loginForm" method=post style="align:center">
		<table>
			<tr>
				<td colspan="2"><h1>LOGIN</h1></td>
				<td rowspan="5" id="caution">주의사항<br/> 주의사항<br/> 주의사항<br/>주의사항<br/>주의사항<br/>주의사항<br/>주의사항<br/>주의사항<br/>주의사항<br/>주의사항<br/>
				주의사항<br/>주의사항<br/> </td>
			</tr>
			<tr>
				<td>아이디: </td>
				<td><input type="text" name="userId" size="15"></td>
			</tr>
			<tr>
				<td>비밀번호: </td>
				<td><input type="password" name="userPassword" size="15"></td>
			</tr>
			<tr>
				<td colspan="3" style="padding:10px 0px 10px 0px"><input id="btnLogin" type="submit" value="로그인"></td>
			</tr>
			<tr>
				<td colspan="3" style="padding:10px 0px 10px 0px"><input id="btnLogin" type="submit" value="회원가입"></td>
			</tr>
		</table>
	</form>
	</div>

</body>
</html>