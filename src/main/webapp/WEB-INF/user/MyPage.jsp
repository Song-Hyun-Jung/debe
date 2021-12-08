<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
.btnSubmit{
	width:90px;
	height:30px;
	background-color:#a9173d;
	color:white;
	font-size:15px;
	visibility:visibility;
}
td.bigLayoutRight{
	align:center;
	vertical-align:top;
	padding-right:50px;
	padding-left:50px;
}
td.bigLayoutLeft{
	align:center;
	vertical-align:top;
	padding-right:50px;
	padding-left:50px;
	border-right:3px solid #a9173d;
}
table{
	border-collapse:collapse;
}
td.bookmarkTable{
	border-collapse:collapse;
	border:1px solid;
	height:30px;
}
td.subjectTitle{
	padding-left:15px;
	border-left: 5px solid #a9173d;
	}
a { text-decoration:none !important }
a:link{color:black;}
a:visited{color:#a9173d;}
a:hover{color:blue;}
</style>
</head>
<body>
<div align="center">
	<%@ include file="/WEB-INF/user/top.jsp" %>
</div>
<div align="left" style="margin-left:100px">
	<h2>My Page</h2>
</div>
<div align="center">
	<table style="width:90%">
		<tr>
			<td class="bigLayoutLeft" rowspan="2" height="200px" width="50%"> <!-- 사용자 과목 수정 -->
				<form name="updateSubject">
					<table class="userSubjectInfo" style="width:100%; padding-right:100px">
						<tr><td><h3>-수강 과목</h3></td></tr>
						<tr><td class="subjectTitle"><h4>${userSubject.subjectTitle}</h4></td></tr>
						<tr><td align="right"><input class="btnSubmit" type="submit" value="수정" formaction="<c:url value ='/user/updateSubject/form' />"></td></tr>
					</table>
				</form>
			</td>
			<td class="bigLayoutRight" rowspan="3" height="300px"> <!-- question 북마크 --> 
				<table style="width:100%">
					<tr><td><h3>- 나의 북마크 : 질문 </h3></td></tr>
					<c:forEach var="questionBookmark" items="${findMyQuestionBookmarks}"  begin="${bqIndex}" end="${bqIndex+4}" >
							<tr>
								<td class="bookmarkTable">
									<a href="<c:url value ='/user/viewquestion'>
												<c:param name='questionCode' value='${questionBookmark.postId}' />
											</c:url>"> - ${questionBookmark.title}
									</a>
								</td>
							</tr>
						</c:forEach>
				</table>
				<p> <!-- 인덱스 -->
				<div align="center">
					<c:forEach  var="questionBookmark" items="${findMyQuestionBookmarks}" varStatus="statusBQ">
						<c:if test="${statusBQ.count % 5 eq 1}">
					  	<a href="<c:url value ='/user/myPage'> <c:param name='bqIndex' value='${statusBQ.index}' /> </c:url>"  id="indexfont"> 
					  		<font><fmt:formatNumber value="${(statusBQ.index)/5 + 1}" pattern="#" />&nbsp;&nbsp;|</font> </a>
					  </c:if>
					</c:forEach>
				</div>
			</td>	
		</tr>
		<tr></tr>
		<tr>
			<td class="bigLayoutLeft" rowspan="4" height="400px"> <!-- 사용자 질문 내가 한 질문 5개로 페이지 인덱싱-->
				<table style="width:100%">
					<tr><td><h3>-나의 질문</h3></td></tr>
					<c:forEach var="question" items="${findMyQuestions}"  begin="${mqIndex}" end="${mqIndex+4}">
							<tr>
								<td class="bookmarkTable">
									<a href="<c:url value ='/user/viewquestion'>
												<c:param name='questionCode' value='${question.postId}' />
											</c:url>"> - ${question.title}
									</a>
								</td>
							</tr>
						</c:forEach>
				</table>
				<p> <!-- 인덱스 -->
				<div align="center">
					<c:forEach  var="question" items="${findMyQuestions}" varStatus="statusMQ">
						<c:if test="${statusMQ.count % 5 eq 1}">
					  	<a href="<c:url value ='/user/myPage'> <c:param name='mqIndex' value='${statusMQ.index}' /> </c:url>"  id="indexfont"> 
					  		<font><fmt:formatNumber value="${(statusMQ.index)/5 + 1}" pattern="#" />&nbsp;&nbsp;|</font> </a>
					  </c:if>
					</c:forEach>
				</div>
			</td> 
		</tr>
		<tr>
			<td class="bigLayoutRight" rowspan="3" height="300px"> <!-- recommend 북마크 -->
				<table style="width:100%">
					<tr><td><h3>-나의 북마크 : 추천 코딩 문제</h3></td></tr>
					<c:forEach var="recommendBookmark" items="${findMyRecommendBookmarks}" begin="${brIndex}" end="${brIndex+4}">
							<tr>
								<td class="bookmarkTable">
									<a href="<c:url value ='/user/viewrecommend'>
												<c:param name='recommendCode' value='${recommendBookmark.postId}' />
											</c:url>"> - ${recommendBookmark.title}
									</a>
								</td>
							</tr>
					</c:forEach>
				</table>
				<p> <!-- 인덱스 -->
				<div align="center">
					<c:forEach  var="recommendBookmark" items="${findMyRecommendBookmarks}" varStatus="statusBR">
						<c:if test="${statusBR.count % 5 eq 1}">
					  	<a href="<c:url value ='/user/myPage'> <c:param name='brIndex' value='${statusBR.index}' /> </c:url>"  id="indexfont"> 
					  		<font><fmt:formatNumber value="${(statusBR.index)/5 + 1}" pattern="#" />&nbsp;&nbsp;|</font> </a>
					  </c:if>
					</c:forEach>
				</div>
			</td>
		</tr> 
		<tr></tr>
		<tr></tr>
	
	</table>
</div>
</body>
</html>