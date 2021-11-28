<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>데베</title>
<style>
	#modifyButton, #deleteButton {background-color:#a9173d; color:white; width:70px;}
	th.info {width:100px; text-align:left;}
	th.info, td.info{height:40px;}
	#td_button {text-align:right;}
</style>
</head>
<body>
	<div align="center">
		<%@ include file="/WEB-INF/user/top.jsp" %>
	</div>
	
	<h2>관리자 회원 관리</h2>
	<h3>회원정보</h3><br><br>
		<div align="center">
		<form>
			<table>
				<tr>
					<th class="info">이름: </th>
					<td class="info"><input type="text" name="name" size="20" required></td>
				</tr>
				<tr>
					<th class="info">아이디: </th>
					<td class="info"><input type="text" name="ID" size="20" required></td>
				</tr>
				<tr>
					<th class="info">비밀번호: </th>
					<td class="info"><input type="password" name="password" size="20" placeholder="8자 이상 권장" required></td>
				</tr>
				<tr>
					<th class="info">닉네임: </th>
					<td class="info"><input type="text" name="nickname" size="20" required></td>
				</tr>
				<tr>
					<td colspan="2" id="td_button" class="info"><br>
						<input type="submit" value="수정" id="modifyButton""> &nbsp; &nbsp; <input type="submit" value="삭제" id="deleteButton"">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>