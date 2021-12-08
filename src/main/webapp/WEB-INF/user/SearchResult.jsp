<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>검색 결과</title>
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
	table.postTable{
		position:static;
		border-collapse:collapse;
		width:90%; 
		margin-left:30px;
		
	}
	table.layout{
		position:static;
		border-collapse:collapse;
		width:90%; 
		
		margin-left:30px;
	}
	.line{
		border-right:5px solid #a9173d;
		width:50%;
	}
	td.list{
		border:1px solid;
		height:40px;
	}
	a { text-decoration:none !important }
	a:link{color:black;}
	a:visited{color:black;}
	a:hover{color:blue;}
</style>
</head>
<body>
	<div align="center">
		<%@ include file="/WEB-INF/user/top.jsp" %>
	</div>
	
	<div align="center">
		<table class="layout">
			<tr><td colspan="3"><h2>"${searchKeyword}" 검색결과</h2></td></tr>
			<tr>
				<td class="line">
					<table class="postTable">
						<tr><td><h3>Q&A</h3></td></tr>
						<c:if test="${fn:length(findQuestionResult) eq 0}"><tr><td><h3 style="color:red">x 검색 결과 없음 x</h3></td></c:if>
						<c:forEach var="question" items="${findQuestionResult}" begin="${sqIndex}" end="${sqIndex+4}" >
							<tr>
								<td class="list">
									<a href="<c:url value ='/user/viewquestion'>
												<c:param name='questionCode' value='${question.postId}' />
											</c:url>"> ${question.title}
									</a>
								</td>
							</tr>
						</c:forEach>
					</table>
					<p> <!-- 인덱스 -->
				<div align="center">
					<c:forEach  var="question" items="${findQuestionResult}" varStatus="statusSQ">
						<c:if test="${statusSQ.count % 5 eq 1}">
					  	<a href="<c:url value ='/user/findKeyword'> <c:param name='sqIndex' value='${statusSQ.index}' /> <c:param name='indexButton' value='indexButton' /></c:url>"  id="indexfont"> 
					  		<font><fmt:formatNumber value="${(statusSQ.index)/5 + 1}" pattern="#" />&nbsp;&nbsp;|</font> </a>
					  </c:if>
					</c:forEach>
				</div>
				</td>
				<td>
					<table class="postTable"> 
						<tr><td><h3>추천 코딩 문제</h3></td></tr>
						<c:if test="${fn:length(findRecommendResult) eq 0}"><tr><td><h3 style="color:red">x 검색 결과 없음 x</h3></td></c:if>
						<c:forEach var="recommend" items="${findRecommendResult}" begin="${srIndex}" end="${srIndex+4}" >
							<tr>
								<td class="list">
									<a href="<c:url value ='/user/viewrecommend'>
												<c:param name='recommendCode' value='${recommend.postId}' />
											</c:url>"> ${recommend.title}
									</a>
								</td>
							</tr>
						</c:forEach>
					</table>
					<p> <!-- 인덱스 -->
				<div align="center">
					<c:forEach  var="recommend" items="${findRecommendResult}" varStatus="statusSR">
						<c:if test="${statusSR.count % 5 eq 1}">
					  	<a href="<c:url value ='/user/findKeyword'> <c:param name='srIndex' value='${statusSR.index}' /><c:param name='indexButton' value='indexButton' /> </c:url>"  id="indexfont"> 
					  		<font><fmt:formatNumber value="${(statusSR.index)/5 + 1}" pattern="#" />&nbsp;&nbsp;|</font> </a>
					  </c:if>
					</c:forEach>
				</div>
				</td>
			</tr>
		</table>
		
	</div>

</body>
</html>