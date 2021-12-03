<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>수강 과목 수정</title>
<style>
	.btnSubmit{
		width:90px;
		height:30px;
		background-color:#a9173d;
		color:white;
		font-size:15px;
		visibility:visibility;
	}
	td.info{
		width:250px;
		vertical-align:top;
	}
	table.subjectTable{
		padding: 20px 20px 20px 20px;
		border:2px solid #a9173d;
		border-radius:4px;
		
	}
</style>
<script>
function updateSubject(targetUri) {
	update.action = targetUri;
	update.method="POST";
	update.submit();
}
</script>
</head>
<body>
<div align="center">
		<%@ include file="/WEB-INF/user/top.jsp" %>
</div>
<div align="left" style="margin-left:200px;">
	 <h2>수강 과목 수정</h2>
	 <h3>-수강 과목</h3>
 </div>
  <div align="center">
   <form name="update">
	<table class="subjectTable" style="width:80%">
		<tr>
			<td class="info">
			<c:forEach var="subject" items="${findAllSubjects}">
				<c:if test="${subject.subjectId eq 111}"><input type="radio" name="userSubject" value="${subject.subjectId}" checked>${subject.subjectTitle}</br></br></c:if>
				<c:if test="${subject.subjectId ne 111}"><input type="radio" name="userSubject" value="${subject.subjectId}">${subject.subjectTitle}</br></br></c:if>
				<c:if test="${subject.subjectId eq 211}"></td><td class="info"></c:if>
				<c:if test="${subject.subjectId eq 311}"></td><td class="info"></c:if>
				<c:if test="${subject.subjectId eq 411}"></td><td class="info"></c:if>
			</c:forEach>
			</td>
		</tr>		
	 </table>
	 <div align="right" style="margin-top:50px; padding-right:150px">
	 	<input class="btnSubmit" type="submit" value="수정" onclick="updateSubject('<c:url value ='/user/updateSubject' />')">
	 </div>
	</form>
	</div>
</body>
</html>