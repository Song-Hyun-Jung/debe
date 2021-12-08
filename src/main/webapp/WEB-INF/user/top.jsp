<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>데베</title>
<script>
function logout(targetUri) {
	alert("로그아웃하였습니다.");
	topForm.action = targetUri;
	topForm.submit();
}
</script>
<style>
	#menu {list-style-type:none;}
	#menu td {font-size:20px; font-weight:bold; padding:5px 5px 5px 5px;}
	#menu_string {text-decoration:none; color:black;}
	.menuhover:hover { outline:3px solid #a9173d; border-radius:4px;}
	.btnLogout{
		width:70px;
		height:25px;
		background-color:#a9173d;
		color:white;
		font-size:13px;
	}
	a { text-decoration:none !important }
</style>
</head>
<body>	
<form name="topForm">
		<table id="topLayout">
			<tr>
				<td><a href="<c:url value='/user/goMain' />"><img src="<c:url value='/images/schoolLogo.jpg' />" width="280px" height="100px"></a></td>
				<td width="65%"></td>
				<td><a href="<c:url value='/user/myPage' />"><span style="color:#a9173d; font-weight:bold;">${userNickname}</span></a> 님, 안녕하세요</td>
				<td style="padding-left:10px"><input class="btnLogout" type="button" value="로그아웃" onClick="logout('<c:url value='/user/logout'/>')"></td>
			</tr>
		</table>
		<nav>
			<table id="menu">
				<tr>
					<td width="30%"></td>
					<td class="menuhover"><a href="<c:url value ='/user/addquestion/form' />" id="menu_string">질문하기</a></td>
					<td width="11%"></td>
					<td class="menuhover"><a href="<c:url value ='/user/questionlist' />" id="menu_string">Q&A</a></td>
					<td width="11%"></td>
					<td class="menuhover"><a href="<c:url value ='/user/recommendlist' />" id="menu_string">추천 코딩 문제</a></td>
					<td width="11%"></td>
					<td class="menuhover"><a href="<c:url value ='/user/myPage' />" id="menu_string">My Page</a></td>
					<td width="11%"></td>
				</tr>
			</table>
	</nav>
	<hr size="5" width="100%" align="left" color="#a9173d" noshade>
</form>
</body>
</html>