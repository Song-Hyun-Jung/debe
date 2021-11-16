<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>데베</title>
<style>
	#mainLayout {width:100%; height:500px;}
	#submitButton {background-color:#a9173d; color:white;}
</style>
</head>
<body>
	<div align="center">
		<%@ include file="/WEB-INF/user/top.jsp" %>
	</div>
	
	<div>
		<table border="1" id="mainLayout">
			<tr>
				<td colspan="2" id="tKeword" width="67%" height="20%">
					<h3>키워드 검색</h3>
					<input type="text" name="searchKeword"> <input id="submitButton" type="submit" value="검색">
				</td>
				<td rowspan="3" id="tMypage" width="33%" height="60%">
					<h3>My Page</h3>
					<table id="mypagelayout" border="1" width="90%">
						<tr><td>닉네임</td></tr>
						<tr><td>경험치</td></tr>
						<tr><td>간격 공간</td></tr>
						<tr><td>나의 질문</td></tr>
						<tr><td>간격 공간</td></tr>
						<tr><td>북마크</td></tr>
					</table>
				</td>
			</tr>
			<tr>
				<td rowspan="4" id="tQuestion"  width="34%" height="80%"><h3>미해결 질문</h3></td>
				<td rowspan="4" id="tRecommend"  width="33%" height="80%"><h3>최근 추천 코딩 문제</h3></td>
			</tr>
			<tr></tr>
			<tr>
				<td rowspan="2" id="tRanking" width="33%" height="40%">
					<h3>랭킹</h3>
					<ol>
						<li>1위 </li>
						<li>2위 </li>
						<li>3위 </li>
					</ol>
				</td>
			</tr>
			<tr></tr>
		</table>
	</div>
	<footer></footer>
</body>
</html>