<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
hr.vertical-line{
	width:1px;
	min-height:400px;
	background-color:#a9173d;
	transform:rotate(90deg):
	-o-transform:rotate(90deg):
	-moz-transform:rotate(90deg):
	-webkit-transgorm:rotate(90deg):
}



</style>
</head>
<body>
<div align="center">
	<%@ include file="/WEB-INF/user/top.jsp" %>
</div>
<div align="center">
	<table width="150%">
		<tr>
			<td><jsp:include page="/WEB-INF/user/MyInfo.jsp"></jsp:include></td>
			<td><hr class="vertical-line"></hr></td>
			<td><jsp:include page="/WEB-INF/user/MyBookMark.jsp"></jsp:include></td>
		</tr>
	</table>
</div>
</body>
</html>