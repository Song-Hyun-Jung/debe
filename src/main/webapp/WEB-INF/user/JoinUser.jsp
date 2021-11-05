<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
#btnJoin{
	margin:0px 0px 0px 10px;
	width:250px;
	height:30px;
	background-color:#a9173d;
	color:white;
	font-size:20px;
}
#btnCheck{
	margin:0px 0px 0px 10px;
	background-color:#a9173d;
	color:white;
}
td{
	width: 130px;
	padding:10px 0px 10px 0px;
}
#subject{
	width:200px;
}
#displayLogo{
	position: static;
}

</style>
</head>
<body>
	<div align="left" style="padding:0px 0px 0px 50px">
		<img src="../../images/schoolLogo.jpg" width="280px" height="100px" id="displayLogo">
	</div>
	<hr size="5" width="100%" align="left" color="#a9173d" noshade>
	<div align="center" style="padding:30px 0px 0px 0px">
	<form name="joinForm" method=post style="align:center">
		<table>
			<tr>
				<td colspan="2"><h1>JOIN</h1></td>
				<td> </td>
				<td rowspan="7" id="subject">
					<input type="checkbox" name="interesting" value="0" checked>프로그래밍논리의이해 <br/> 
					<input type="checkbox" name="interesting" value="1">인터넷프로그래밍<br/>
					<input type="checkbox" name="interesting" value="2">컴퓨터프로그래밍 <br/>
					<input type="checkbox" name="interesting" value="3">이산수학<br/>
					<input type="checkbox" name="interesting" value="4">객체프로그래밍 <br/>
					<input type="checkbox" name="interesting" value="5">문제해결기법<br/>
					<input type="checkbox" name="interesting" value="6">객체지향언어2<br/>
					<input type="checkbox" name="interesting" value="7">정보보호개론<br/>
					<input type="checkbox" name="interesting" value="8">데이터통신<br/>
					<input type="checkbox" name="interesting" value="9">컴퓨터구조<br/>
				</td>
				<td rowspan="7" id="subject">
					<input type="checkbox" name="interesting" value="10">자료구조<br/>
					<input type="checkbox" name="interesting" value="11">웹프로그래밍<br/>
					<input type="checkbox" name="interesting" value="12">데이터베이스개론<br/>
					<input type="checkbox" name="interesting" value="13">소프트웨어공학<br/>
					<input type="checkbox" name="interesting" value="14">운영체제<br/>
					<input type="checkbox" name="interesting" value="15">모바일소프트웨어<br/>
					<input type="checkbox" name="interesting" value="16">웹서비스<br/>
					<input type="checkbox" name="interesting" value="17">네트워크<br/>
					<input type="checkbox" name="interesting" value="18">시스템/네트워크보안<br/>
					<input type="checkbox" name="interesting" value="19">시스템프로그래밍<br/>
				</td>
				<td rowspan="7" id="subject">
					<input type="checkbox" name="interesting" value="20">모바일응용<br/>
					<input type="checkbox" name="interesting" value="21">데이터베이스프로그래밍<br/>
					<input type="checkbox" name="interesting" value="22">알고리즘<br/>
					<input type="checkbox" name="interesting" value="23">빅데이터처리<br/>
					<input type="checkbox" name="interesting" value="24">IoT응용<br/>
				</td>
			</tr>
			<tr>
				<td>아이디: </td>
				<td><input type="text" name="joinId" size="15"></td>
				<td style="text-align:right">수강과목:&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td>비밀번호: </td>
				<td><input type="password" name="joinPassword" size="15"></td>
				<td> </td>
			</tr>
			<tr>
				<td>비밀번호 확인: </td>
				<td><input type="password" name="joinPasswordCheck" size="15"></td>
				<td> </td>
			</tr>
			<tr>
				<td>닉네임: </td>
				<td><input type="text" name="joinNickname" size="15"></td>
				<td><input id="btnCheck" type="submit" value="중복체크"></td>
			</tr>
			<tr>
				<td>이름: </td>
				<td><input type="text" name="joinName" size="15"></td>
				<td> </td>
			</tr>
			<tr>
				<td colspan="3" style="padding:10px 0px 10px 0px"><input id="btnJoin" type="submit" value="회원가입"></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>