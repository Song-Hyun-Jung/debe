<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script>
function addAnswer(targetUri) {
	//alert(${Question.postId});
	questionInfo.action = targetUri;
	questionInfo.method="GET";
	questionInfo.submit();
}
function deleteAnswer(form) {
	//alert(${Question.postId});
	questionInfo.action = "<c:url value ='/user/deleteanswer' />";
	questionInfo.method="POST";
	questionInfo.submit();
}
function bookmarkQuestion(targetUri) {
	questionInfo.action = "<c:url value ='/user/bookmarkQuestion' />";
	questionInfo.submit();
}
</script>
<meta charset="UTF-8">
<title>질문 조회</title>
<style>
.btnSubmit{
	width:90px;
	height:30px;
	background-color:#a9173d;
	color:white;
	font-size:15px;
	visibility:visibility;
}
#title{
	height:20px;
}
#show{
	visibility:visibility;
}
#noShow{
	visibility:hidden;
}
tr.answerInfo{
	border-collapse:collapse;
	border:1px solid black;
}
table.answers{
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
	<div align="center">
		<%@ include file="/WEB-INF/user/top.jsp" %>
	</div>
	<div align="center">
	<form name=questionInfo method=post action="<c:url value='/user/deletequestion'> <c:param name='questionCode' value='${Question.postId}' /> <c:param name='userId' value='${Question.userId}' /></c:url>" >
		<table>
			<tr>
				<td class="info">제목</td>
				<td align="center" colspan="3"><input readonly id="title" type="text" name="questionTitle" value="${Question.title}" size="75" style="height:25px"></td>
				<td align="right" width="10px" height="10px"><input type="image" 
					<c:if test="${ exist eq 'false'}"> src=" <c:url value='/images/beforeBookmark.jpg' />" </c:if>
					<c:if test="${ exist eq 'true'}"> src=" <c:url value='/images/afterBookmark.jpg' />" </c:if>
				onclick="bookmarkQuestion()" style="max-width:40%"></td>
			</tr>
			<tr height="50">
				<td class="info">해결상태</td>
				<td>
					&nbsp;&nbsp;
					<c:if test="${Question.solve eq 'y'}">해결됨</c:if>
					<c:if test="${Question.solve eq 'n'}">미해결</c:if>
				</td>
				<td colspan="3"> </td>
			</tr>
			<tr>
				<td colspan="5"><textarea readonly cols=110 rows=15 class="code" name="questionContent">${Question.postContent}</textarea></td>
			</tr>
			<tr>
				<td class="info">질문자:${Question.userNickname}</td>
				<td class="info-right" colspan="4">과목명: ${Question.subjectTitle}  / 사용 언어: ${Question.questionLanguage } / 질문날짜: ${Question.postDate} </td>
			</tr>
			<tr>
				<td style="padding:10px 0px 0px 0px">
					<input class="btnSubmit" type="submit" id="show" value="글 삭제">
				<!-- 후에 질문버튼 이걸로 바꿀것 
					<c:if test="${(Question.userId eq userId) and (Question.solve eq 'n')}"><input class="btnSubmit" type="submit" id="show" value="글 삭제"></c:if>
					<c:if test="${(Question.userId ne userId) or (Question.solve eq 'y')}"><input class="btnSubmit" type="submit" id="noShow" value="글 삭제"></c:if>
				-->
				</td>
				<td colspan="3"></td>
				<td><input type="hidden" name="questionCode" value="${Question.postId}" />
					<input class="btnSubmit" type="button" value="답변등록" onclick="addAnswer('<c:url value ='/user/addanswer/form' />')">
				</td>		
			</tr>
		</table>
	</form>
	</div>
	<hr/>
	
	<div align="center">
	<c:forEach var="answer" items="${AnswerList}">
	<form name="othersAnswer" method=post >
		<table class="answers">
			<tr class="answerInfo" style="padding:10px 0px 10px 0px">
				<td>답변자:${answer.userNickname}  경험치: </td>
				<td align="right">
					<c:if test="${answer.answerAdopt eq 'y'}"><img src=<c:url value='/images/adoptButton.jpg' /> id="show" style="max-width:10%"></c:if>	
					<c:if test="${answer.answerAdopt eq 'n'}"><img src=<c:url value='/images/adoptButton.jpg' /> id="noShow" style="max-width:10%"></c:if>	
				</td>
			</tr>
			<tr class="answerInfo">
				<td colspan="2"><textarea readonly cols=110 rows=15 class="code" name="answerContents">${answer.answerContent}</textarea></td>
			</tr>
			<tr>
				<td align="left" style="padding:10px 0px 10px 0px">
					<input type="hidden" name="answerCode" value="${answer.answerId}" />
					<input type="hidden" name="answerUserId" value="${answer.userId}" />
					<input type="hidden" name="questionCode" value="${Question.postId}"/>
					<c:if test="${(answer.userId eq userId) and (Question.solve eq 'n')}"><input class="btnSubmit" type="submit" value="삭제" formaction="<c:url value ='/user/deleteanswer' />"></c:if>
					<c:if test="${(answer.userId ne userId) or (Question.solve eq 'y')}"><input id="noShow" type="submit" value="삭제" formaction="<c:url value ='/user/deleteanswer' />"></c:if>
				</td>
				<td class="info-right" style="padding:10px 0px 10px 0px">
					<c:if test="${(Question.solve eq 'n') and (Question.userId eq userId)}"><input class="btnSubmit" type="submit" id="show" value="채택" formaction="<c:url value ='/user/adoptanswer' />"></c:if>
					<c:if test="${(Question.solve eq 'y') or (Question.userId ne userId)}"><input class="btnSubmit" type="submit" id="noShow" value="채택" formaction="<c:url value ='/user/adoptanswer' />"></c:if>
				</td>
			</tr>
		</table>
		</form> 
		<hr/>
	</c:forEach> 
	</div>
	
	<hr/>
	<div align="center">
	<table class="answers" width="50%">
			<tr style="padding:10px 0px 10px 0px">
				<td class="info">관련 질문 </td>
			</tr>
			<tr class="answerInfo" height="30px">
				<td>                 </td>
			</tr>
			<tr class="answerInfo" height="30px">
				<td>                 </td>
			</tr>
		</table>
	</div>
</body>
</html>