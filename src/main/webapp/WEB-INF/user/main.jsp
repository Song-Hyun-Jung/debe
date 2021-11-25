<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>데베</title>
<style>
	.btnSubmit{
		width:90px;
		height:30px;
		background-color:#a9173d;
		color:white;
		font-size:15px;
		visibility:visibility;
	}
	table.mainLayout{
		position:static;
		border-collapse:collapse;
		width:90%; 
		height:500px;
		border:none;
	}
	table.posts{
		position:static;
		border-collapse:collapse;
		width:90%; 
		border: 1px solid;
		margin-left:30px;
	}
	tr.postList{
		height:40px;
		border:solid 1px;
	}
	td.info{
		font-weight:900;
		font-size:20px;
	}
	.borderNone{
		border:none;
		border-collapse:collapse;
	}
	.redBorder{
		border-collapse:collapse;
		border: 2px solid #a9173d;
		height:30px;
	}
</style>
</head>
<body>
	<div align="center">
		<%@ include file="/WEB-INF/user/top.jsp" %>
	</div>
	
	<div align="center" style="padding-top:10px">
		<table class="mainLayout" >
			<tr>
				<td colspan="2" class="info" width="67%" height="20%">
					<h3>키워드 검색</h3>
					<form name="search">
						<input type="text" name="keyword" size="70" style="height:30px"> 
						<input class="btnSubmit" type="submit" value="검색" formaction="<c:url value ='/user/findKeyword'/>">
					</form>
				</td>
				<td rowspan="2" width="30%" height="60%">
					<h2 style="margin-left:100px">My Page</h2>
					<table width="50%" style="margin-left:100px" class="redBorder">
						<tr rowspan="2">
							<td style="text-align:center"><h3>${userNickname}</h3><p/>
								<h4>경험치: ${userLevel}</h4> </td>
						</tr>
						<tr height="40px"><td class="redBorder"><a href="<c:url value ='/user/myPage'/>">*나의 질문</a></td></tr>
						<tr height="20px"><td></td></tr>
						<tr height="40px"><td class="redBorder"><a href="<c:url value ='/user/myPage'/>">*북마크</a></td></tr>
						<tr height="20px"><td></td></tr>
					</table>
				</td>
			</tr>
			<tr>
				<td rowspan="3" width="35%" height="80%">
					<h2>미해결 질문</h2>
					<table class="posts">
					<ol>
						<c:forEach var="question" items="${notSolveQuestionList}">
							<tr class="postList">
								<td>
									<li>
										<a href="<c:url value ='/user/viewquestion'>
													<c:param name='questionCode' value='${question.postId}' />
													</c:url>"> ${question.title}
										</a>
									</li>
								</td>
							</tr>
						</c:forEach>
					</ol>
					</table>
				</td>
				<td rowspan="3" width="35%" height="80%" style="padding-left:30px">
					<h2>최근 추천 코딩 문제</h2>
					<table class="posts">
					<ol>
						<c:forEach var="recommend" items="${display10Recommend}">
							<tr class="postList">
								<td>
									<li>
										<a href="<c:url value ='/user/viewrecommend'>
												<c:param name='questionCode' value='${recommend.postId}' />
												</c:url>"> ${recommend.title}
										</a>
									</li>
								</td>
							</tr>
						</c:forEach>
					</ol>
					</table>
				</td>
				
			</tr>
			<tr>
				<td rowspan="2" width="30%" height="40%">
					<table border="1" width="50%" style="margin-left:100px" class="borderNone">
						<h2 style="margin-left:100px">랭킹</h2>
						<tr class="redBorder"><td width=40px class="borderNone">1위|</td><td class="borderNone">${top3User[0].userNickname}</td><td class="borderNone">LV.${top3User[0].userLevel}</td></tr>
						<tr class="redBorder"><td class="borderNone">2위|</td><td class="borderNone">${top3User[1].userNickname}</td><td class="borderNone">LV.${top3User[1].userLevel}</td></tr>
						<tr class="redBorder"><td class="borderNone">3위|</td><td class="borderNone">${top3User[2].userNickname}</td><td class="borderNone">LV.${top3User[2].userLevel}</td></tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<footer></footer>
</body>
</html>