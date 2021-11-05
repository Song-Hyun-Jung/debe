<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>데베</title>
<style>
	#modifyButton, #deleteButton {background-color:#a9173d; color:white; width:70px;}
	th {width:100px; text-align:left;}
	th, td {height:40px;}
	#td_button {text-align:right;}
</style>
</head>
<body>
	<h2>관리자 회원 관리</h2>
	<h3>회원정보</h3><br><br>
		<div align="center">
		<form>
			<table>
				<tr>
					<th>이름: </th>
					<td><input type="text" name="name" size="20" required></td>
				</tr>
				<tr>
					<th>아이디: </th>
					<td><input type="text" name="ID" size="20" required></td>
				</tr>
				<tr>
					<th>비밀번호: </th>
					<td><input type="password" name="password" size="20" placeholder="8자 이상 권장" required></td>
				</tr>
				<tr>
					<th>닉네임: </th>
					<td><input type="text" name="nickname" size="20" required></td>
				</tr>
				<tr>
					<td colspan="2" id="td_button"><br>
						<input type="submit" value="수정" id="modifyButton""> &nbsp; &nbsp; <input type="submit" value="삭제" id="deleteButton"">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>