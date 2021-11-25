<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>데베</title>
<style>
	#menu {list-style-type:none;}
	#menu td {font-size:20px; font-weight:bold;}
	#menu_string {text-decoration:none; color:black;}
	.menuhover:hover {background-color:#a9173d; color:white;}
</style>
</head>
<body>
	<%session.setAttribute("userNickname", "테스터"); %>
	<%session.setAttribute("userLevel", 2); %>
	<%session.setAttribute("userId", "20190000"); %>
		<table id="topLayout">
			<tr><td><a href="<c:url value='/user/goMain' />"><img src="<c:url value='/images/schoolLogo.jpg' />" width="250px" height="100px"></a></td><td width="70%"></td><td>${userNickname} 님, 안녕하세요</td></tr>
		</table>
		<nav>
			<table id="menu">
				<tr>
					<td width="30%"></td>
					<td class="menuhover"><a href="<c:url value ='/user/addquestion/form' />" id="menu_string">질문하기</a></td>
					<td width="11%"></td>
					<td class="menuhover"><a href="<c:url value ='/user/questionlist' />" id="menu_string">Q&A</a></td>
					<td width="11%"></td>
					<td class="menuhover"><a href="recommend.html" id="menu_string">추천 코딩 문제</a></td>
					<td width="11%"></td>
					<td class="menuhover"><a href="bookmark.html" id="menu_string">북마크</a></td>
					<td width="11%"></td>
				</tr>
			</table>
	</nav>
	<hr size="5" width="100%" align="left" color="#a9173d" noshade>
</body>
</html>