<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>솔루션 등록</title>
<style>
#btnSubmit{
	width:90px;
	height:30px;
	background-color:#a9173d;
	color:white;
	font-size:15px;
}
#show{
	visibility:visibility;
}
#noShow{
	visibility:hidden;
}
tr.recommendInfo{
	border-collapse:collapse;
	border:1px solid black;
}
table.collapse{
	position:static;
	border-collapse:collapse;
}

td.info{
	font-weight:900;
	font-size:20px;
}
td.info-right{
	font-weight:900;
	font-size:20px;
	text-align:right;
}
hr{
	color:#a9173d;
}
</style>
</head>
<body>
<% //나중에 지울것
session.setAttribute("userId", 20190000); 
session.setAttribute("userNickname", "최");
session.setAttribute("userLevel", 2); 
%>
	<div align="center">
		<%@ include file="/WEB-INF/user/top.jsp" %>
	</div>
	<div align="center">
	<form name=recommendInfo method=post>
		<table class="collapse">
			<tr height="30" width="70">
				<td class="info">문제</td>
				<td align="center" colspan="2"><input type="text" name="title" size="70"></td>
				<td width="40px" height="40px"><input type="image" src="../../images/beforeBookmark.jpg" class="imageAlign" style="max-width:80%"></td>
			</tr>
			<tr class="recommendInfo" >
				<td colspan="4">작성자:  &nbsp; 경험치:   </td>
			</tr>
			<tr class="recommendInfo">
				<td colspan="4"><textarea cols=100 rows=15 class="code" name="answerCodes"></textarea></td>
			</tr>
			<tr>
				<td class="info" colspan="2">난이도: &nbsp; 추천수:  </td>
				<td class="info" colspan="2">알고리즘: </td>
			</tr>
			<tr>
				<td colspan="2" style="padding:10px 0px 0px 0px"><input id="btnSubmit" type="submit" value="글 삭제"></td>
				<td colspan="2"  align="right" style="padding:10px 0px 0px 0px"><input id="btnSubmit" type="submit" value="답변하기"></td>
			</tr>
			<tr>
				<td colspan="4" align="right"><input type="image" src="../../images/beforeEmpathize.jpg" style="max-width:100%"></td>				
			</tr>
		</table>
	</form>
	</div>
	
	<hr/>
	
	<div align="center" style="padding:10px 0px 10px 0px">
	<c:forEach var="solution" items="${SolutionList}">
	<form name=otherSolution method=post>
		<table class="collapse">
			<tr class="recommendInfo">
				<td>작성자:${solution.userNickname}   &nbsp; 경험치:   &nbsp; 평가점수:${solution.solutionScore} </td>
			</tr>
			<tr class="recommendInfo">
				<td colspan="3"><textarea cols=100 rows=15 class="code" name="answerCodes">${solution.solutionContent}</textarea></td>
			</tr>
			<tr>
				<td align="left" style="padding:10px 0px 10px 0px">
					<c:if test="${(solution.userId eq userId)}"><input class="btnSubmit" type="submit" value="삭제" formaction="<c:url value ='/user/deleteSolution' />"></c:if>
					<c:if test="${(solution.userId ne userId)}"><input id="noShow" type="submit" value="삭제" formaction="<c:url value ='/user/deleteSolution' />"></c:if>
					<input type="hidden" name="solutionCode" value="${solution.solutionId}" />
					<input type="hidden" name="solutionUserId" value="${solution.userId}" />
					<!--  
					<input type="hidden" name="recommendCode" value="${Recommend.postId}"/>
					-->
					<input type="hidden" name="recommendCode" value="35"/>
					  
				</td>
				<td colspan="2" style="text-align:right">
					<select name="score" style="padding:5px 0px 5px 0px">
						<option value="0" selected>평가점수</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>&nbsp;&nbsp;
					<input id="btnSubmit" type="submit" value="평가하기">
				</td>
			</tr>
		</table>
	</form>
	</c:forEach>
	</div>
	
	<hr/>
	
</body>
</html>