<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>데베</title>
<script language = "JavaScript">
function updateUser(targetUri) {
	userForm.action = targetUri;
	userForm.method="POST";
	userForm.submit();
}
</script>
<style>
	.Button {background-color:#a9173d; color:white; width:70px;}
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
		<form name="userForm">
			<table>
				<tr>
					<th class="info">이름: </th>
					<td class="info"><input type="text" name="name" size="20" value="${user.userName}" required></td>
				</tr>
				<tr>
					<th class="info">아이디: </th>
					<td class="info"><font>${user.userId}</font></td>
				</tr>
				<tr>
					<th class="info">비밀번호: </th>
					<td class="info"><input type="text" value="${user.userPassword}" name="password" size="20" placeholder="8자 이상 권장" required></td>
				</tr>
				<tr>
					<th class="info">닉네임: </th>
					<td class="info"><input type="text" value="${user.userNickname}" name="nickname" size="20" required></td>
				</tr>
				<tr>
					<td colspan="2" id="td_button" class="info"><br>
						<input type="button" value="수정" class="Button" onClick="updateUser('<c:url value ='/user/adminUpdateUser'>
										<c:param name='button' value='modify' /> <c:param name='userId' value='${user.userId}' /> 
									</c:url>')" > &nbsp; &nbsp; <input type="button" value="삭제" class="Button" onClick="updateUser('<c:url value ='/user/adminUpdateUser'>
										<c:param name='button' value='delete' /> <c:param name='userId' value='${user.userId}' />
									</c:url>')">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>