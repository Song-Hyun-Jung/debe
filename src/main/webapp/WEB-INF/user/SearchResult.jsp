<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			<tr><td colspan="3"><h2>"${param.keyword}" 검색결과</h2></td></tr>
			<tr>
				<td class="line">
					<table class="postTable">
						<tr><td><h3>Q&A</h3></td></tr>
						<c:if test="${fn:length(findQuestionResult) eq 0}"><tr><td><h3 style="color:red">x 검색 결과 없음 x</h3></td></c:if>
						<c:forEach var="question" items="${findQuestionResult}">
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
				</td>
				<td>
					<table class="postTable"> 
						<tr><td><h3>추천 코딩 문제</h3></td></tr>
						<c:if test="${fn:length(findRecommendResult) eq 0}"><tr><td><h3 style="color:red">x 검색 결과 없음 x</h3></td></c:if>
						<c:forEach var="recommend" items="${findRecommendResult}">
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
				</td>
			</tr>
		</table>
		
	</div>

</body>
</html>