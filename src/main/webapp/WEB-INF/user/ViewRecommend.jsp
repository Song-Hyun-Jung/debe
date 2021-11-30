<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추천 코딩 문제 조회</title>

<script>
function deleteR(targetUri) {
	recommendInfo.action = targetUri;
	recommendInfo.method="POST";
	recommendInfo.submit();
}
function addSolution(targetUri) {
	recommendInfo.action = targetUri;
	recommendInfo.method="GET";
	recommendInfo.submit();
}
function bookmarkRecommend(targetUri) {
	recommendInfo.action = "<c:url value ='/user/bookmarkRecommend' />";
	recommendInfo.submit();
}
function empathizedRecommend(targetUri) {
	recommendInfo.action = "<c:url value ='/user/recommendCount' />";
	recommendInfo.submit();
}
</script>
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
.btnDelete{
	width:90px;
	height:30px;
	background-color:#a9173d;
	color:white;
	font-size:15px;
	visibility:visibility;
}
</style>
</head>
<body>
	<div align="center">
		<%@ include file="/WEB-INF/user/top.jsp" %>
	</div>
	<div align="center">
	<form name=recommendInfo method=post>
		<table class="collapse">
			<tr height="30" width="70">
				
				<td class="info">제목</td>
				<td align="center" colspan="3"><input readonly type="text" name="title" size="40" value="${Recommend.title}"></td>
				<td width="40px" height="40px"><input type="image" 
					<c:if test="${ exist eq 'false'}"> src=" <c:url value='/images/beforeBookmark.jpg' />" </c:if>
					<c:if test="${ exist eq 'true'}"> src=" <c:url value='/images/afterBookmark.jpg' />" </c:if>
				onclick="bookmarkRecommend()" style="max-width:40%"></td>
			</tr>
			<tr class="recommendInfo" >
				<td colspan="5">작성자:  ${Recommend.userNickname} &nbsp; &nbsp; &nbsp; 경험치:  ${Recommend.userLevel }</td>
			</tr>
			<tr class="recommendInfo">
	
				<td colspan="5"><textarea readonly cols=100 rows=15 class="code" name="recommendContent">${Recommend.postContent}</textarea></td>
			</tr>
			<tr>
				<td class="info" colspan="5">난이도: ${Recommend.difficulty } / 작성 날짜: ${Recommend.postDate } / 알고리즘: ${Recommend.algorithm }</td>
			</tr>
			<tr>
				<td colspan="5" align="right"><input type="image" 
					<c:if test="${ empathized eq 'false'}"> src=" <c:url value='/images/beforeEmpathize.jpg' />" </c:if>
					<c:if test="${ empathized eq 'true'}"> src=" <c:url value='/images/afterEmpathize.jpg' />" </c:if>
				onclick="empathizedRecommend()" style="max-width:40%"></td>
			</tr>
			<tr>
				<td class="info" colspan="5" align="right">추천수: ${Recommend.recommendCount }</td>
			</tr>
			<tr><td height="20"></td></tr>
			<tr>
				<td colspan="2" style="padding:10px 0px 0px 0px"><input id="btnSubmit" type="button" value="글 삭제" onclick="deleteR('<c:url value ='/user/deleteRecommend'>
												<c:param name='recommendCode' value='${Recommend.postId}'/><c:param name='userId' value='${Recommend.userId}'/></c:url>')"></td>
				<td colspan="3" align="right">
					<input type="hidden" name="recommendCode" value="${Recommend.postId}" />
					<input id="btnSubmit" type="button" value="답변등록" onclick="addSolution('<c:url value ='/user/addRecommendSolution/form' />')">
				</td>
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
				<td>작성자:${solution.userNickname}   &nbsp; 경험치: ${solution.userLevel }&nbsp; 평가점수: <fmt:formatNumber value="${solution.solutionScore}" pattern="0.0" /> </td>
			</tr>
			<tr class="recommendInfo">
				<td colspan="3"><textarea cols=100 readonly rows=15 class="code" name="answerCodes">${solution.solutionContent}</textarea></td>
			</tr>
			<tr>
				<td align="left" style="padding:10px 0px 10px 0px">
					<c:if test="${(solution.userId eq userId)}"><input class="btnDelete" type="submit" value="삭제" formaction="<c:url value ='/user/deleteSolution' />"></c:if>
					<c:if test="${(solution.userId ne userId)}"><input id="noShow" type="submit" value="삭제" formaction="<c:url value ='/user/deleteSolution' />"></c:if>
					<input type="hidden" name="solutionCode" value="${solution.solutionId}" />
					<input type="hidden" name="solutionUserId" value="${solution.userId}" />
					<input type="hidden" name="recommendCode" value="${Recommend.postId}"/>
				</td>
				<td colspan="2" style="text-align:right">
					<select name="score" style="padding:5px 0px 5px 0px">
						<option value="none" selected>평가점수</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>&nbsp;&nbsp;
					<input id="btnSubmit" type="submit" value="평가하기" formaction="<c:url value ='/user/updateRecommendScore' />">			
				</td>
			</tr>
		</table>
		<hr/>
	</form>
	</c:forEach>
	</div>
	
	<hr/>
	
</body>
</html>