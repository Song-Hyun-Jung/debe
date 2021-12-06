<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 관리</title>
<script language = "JavaScript">
function viewUser() {
	userForm.action = targetUri;
	userForm.method="GET";
	userForm.submit();
}

</script>
<style>
table.post{
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
}
td.info{
	height: 20px;
}
.delete{
	color:rgb(230, 10, 0);
	font-weight:bold;
	text-decoration: none;
	
}
.button {
	border: none;
	width: 80px;
	color: white;
	background-color: rgb(137, 21, 52);
}
#indexfont{
	text-decoration:none;
	color: black;
	font-weight:bold;
}
a { text-decoration:none !important }
a:link{color:black;}
a:visited{color:black;}
a:hover{color:blue;}
</style>
</head>
<body>
<!-- for each문으로 불러올것 페이징 인인덱스 해야함 -->

<div align="center">
		<%@ include file="/WEB-INF/user/top.jsp" %>
</div>
<h2>관리자 게시판 관리</h2>

<h3>질문 목록</h3>
  <form name="userForm">
	<table border="1" width="100%" class="post">
		<colgroup>
			<col style="width:20%">
			<col style="width:35%">
			<col style="width:30%">
			<col style="width:15%">
		</colgroup>
		<tr>
			<th>회원 id(학번)</th>
			<th>회원 비밀번호</th>
			<th>회원 닉네임</th>
			<th>회원 이름</th>
		</tr>
		
		<!-- 한 페이지 당 회원 10명씩 -->
		<c:forEach var="user" items="${userList}" begin="${userIndex}" end="${userIndex+9}">
			<tr>
				<td class="info"><a href="<c:url value ='/user/adminUpdateUser'>
								<c:param name='userId' value='${user.userId}' />
							</c:url>"> ${user.userId} </a>
				</td>
				<td class="info"> ${user.userPassword} </td>
				<td class="info"> ${user.userNickname} </td>
				<td class="info"> ${user.userName} </td>
			</tr>
		</c:forEach>
	</table>
	
	<p>
	<div align="center">
		<c:forEach var="user" items="${userList}" varStatus="statusQ">
			<c:if test="${statusQ.count % 10 eq 1}">
		  	<a href="<c:url value ='/user/adminUserList'> <c:param name='userIndex' value='${statusQ.index}' /> </c:url>"  id="indexfont"> 
		  		<font><fmt:formatNumber value="${(statusQ.index)/10 + 1}" pattern="#" />&nbsp;&nbsp;|</font> </a>
		  </c:if>
		</c:forEach>
	</div>
   </form>
  
</body>

</html>