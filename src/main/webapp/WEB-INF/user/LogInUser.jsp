<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
.btnLogin{
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
<script>
function login(targetUri) {
	if (loginForm.userId.value == "") {
		alert("사용자 ID를 입력하십시오.");
		loginForm.userId.focus();
		return false;
	} 
	if (loginForm.userPassword.value == "") {
		alert("비밀번호를 입력하십시오.");
		loginForm.userPassword.focus();
		return false;
	}	
	
	if(typeof loginForm.userId.value == "string"){
		alert("아이디는 학번입니다.");
		loginForm.userId.value = "";
		return false;
	}
	
	loginForm.action = targetUri;
	loginForm.method="POST";
	loginForm.submit();
}
function userCreate(targetUri) {	
	loginForm.action = targetUri;
	loginForm.method="GET";
	loginForm.submit();
}

</script>
</head>
<body>
	<div align="left" style="padding:0px 0px 0px 50px">
		<a href="<c:url value='/user/login/form' />"><img src="<c:url value='/images/schoolLogo.jpg' />" width="280px" height="100px" id="displayLogo"></a>
	</div>
	<hr size="5" width="100%" align="left" color="#a9173d" noshade>
	
	<div align="center" style="padding:30px 0px 0px 0px">
	<form name="loginForm" style="align:center" >
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
				<td colspan="3" style="padding:10px 0px 10px 0px">
				<input class="btnLogin" type="button" value="로그인" onclick="login('<c:url value ='/user/tryLogin'/>')">
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding:10px 0px 10px 0px">
				<input class="btnLogin" type="submit" value="회원가입" 
				onClick="userCreate('<c:url value='/user/join/form'/>')">
				</td>
			</tr>
		</table>
	</form>
	</div>
	<c:if test="${loginFailed eq 'true'}"><div align="left" style="padding-left:580px; color:red">*로그인 실패</div></c:if>

</body>
</html>