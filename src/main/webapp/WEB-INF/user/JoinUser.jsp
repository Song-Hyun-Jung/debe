<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script>
function addUser(targetUri) {
	if(joinForm.userPasswordCheck.value != joinForm.userPassword.value){
		alert('비밀번호를 확인해주세요');
		return false;
	}	
	<c:if test="${existNickname ne 'false' or check ne 1}"> 
		alert('닉네임 중복체크를 해주세요'); 
		return false;
	</c:if>
	joinForm.action = targetUri;
	joinForm.method="POST";
	joinForm.submit();
}
function checkNickname(targetUri) {
	joinForm.action = targetUri;
	joinForm.method="POST";
	joinForm.submit();
}
function goLogin(targetUri){
	form.action = targetUri;
	form.method="GET";
	form.submit();
}
</script>
</head>
<body>
	<c:if test="${registerFailed eq 'true'}"><script>alert('회원가입에 실패하였습니다. 다시 시도해주세요.')</script><c:set var="registerFailed" value="false" scope="session" /></c:if>
	<c:if test="${existNickname eq 'true' and check eq 1}"><script>alert('이미 사용중인 닉네임입니다.')</script><c:set var="check" value="-1" scope="session" /></c:if>
	<c:if test="${existNickname eq 'false' and check eq 1}"><script>alert('사용 가능한 닉네임입니다.')</script><c:set var="check" value="-1" scope="session" /></c:if>
	<c:set var="check" value="-1" scope="session" />
	<div align="left" style="padding:0px 0px 0px 50px">
		<form name="form">
			<input type="image"  src="<c:url value='/images/schoolLogo.jpg' />" width="280px" height="100px" onclick="goLogin('<c:url value='/user/quitJoin'/>')"/>
		</form>	
	</div>
	<hr size="5" width="100%" align="left" color="#a9173d" noshade>
	<div align="center" style="padding:30px 0px 0px 0px">
	<form name="joinForm" method=post style="align:center">
		<table>
			<tr>
				<td colspan="2"><h1>JOIN</h1></td>
				<td> </td>
				<td rowspan="7" id="subject"> 
					<c:forEach var="i" begin="0" end="9">
						<c:if test="${i eq 0 or findAllSubjects[i].subjectId eq sessionScope.subjectId}"><input type="radio" name="subjectId" value="${findAllSubjects[i].subjectId}" checked>${findAllSubjects[i].subjectTitle}<br/></c:if>
						<c:if test="${i ne 0}"><input type="radio" name="subjectId" value="${findAllSubjects[i].subjectId}">${findAllSubjects[i].subjectTitle}<br/></c:if>
					</c:forEach>
					
				</td>
				<td rowspan="7" id="subject">
					<c:forEach var="i" begin="10" end="19">
						<c:if test="${findAllSubjects[i].subjectId eq sessionScope.subjectId}"><input type="radio" name="subjectId" value="${findAllSubjects[i].subjectId}" checked>${findAllSubjects[i].subjectTitle}<br/></c:if>
						<c:if test="${findAllSubjects[i].subjectId ne sessionScope.subjectId}"><input type="radio" name="subjectId" value="${findAllSubjects[i].subjectId}">${findAllSubjects[i].subjectTitle}<br/></c:if>					
					</c:forEach>
				</td>
				<td rowspan="7" id="subject">
					<c:forEach var="i" begin="20" end="29">
						<c:if test="${findAllSubjects[i].subjectId eq sessionScope.subjectId}"><input type="radio" name="subjectId" value="${findAllSubjects[i].subjectId}" checked>${findAllSubjects[i].subjectTitle}<br/></c:if>
						<c:if test="${findAllSubjects[i].subjectId ne sessionScope.subjectId}"><input type="radio" name="subjectId" value="${findAllSubjects[i].subjectId}">${findAllSubjects[i].subjectTitle}<br/></c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>아이디: </td>
				<td><input type="text" name="userId" size="15" value="${sessionScope.userId}"></td>
				<td style="text-align:right">수강과목:&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td>비밀번호: </td>
				<td><input type="password" name="userPassword" size="15" value="${sessionScope.userPassword}"></td>
				<td> </td>
			</tr>
			<tr>
				<td>비밀번호 확인: </td>
				<td><input type="password" name="userPasswordCheck" size="15" value="${sessionScope.userPasswordCheck}"></td>
				<td> </td>
			</tr>
			<tr>
				<td>닉네임: </td>
				<td><input type="text" name="userNickname" size="15" value="${sessionScope.userNickname}"></td>
				<td><input id="btnCheck" type="button" value="중복체크" onclick="checkNickname('<c:url value='/user/checkNickname'/>')"></td>
			</tr>
			<tr>
				<td>이름: </td>
				<td><input type="text" name="userName" size="15" value="${sessionScope.userName}"></td>
				<td> </td>
			</tr>
			<tr>
				<td colspan="3" style="padding:10px 0px 10px 0px"><input id="btnJoin" type="button" value="회원가입" onclick="addUser('<c:url value ='/user/join' />')"></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>