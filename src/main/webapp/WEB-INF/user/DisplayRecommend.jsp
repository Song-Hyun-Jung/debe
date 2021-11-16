<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DisplayRecommend</title>
<style>
table.recommend{
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
}
td.info{
	height: 20px;
}
.button {
	border: none;
	width: 80px;
	color: white;
	background-color: rgb(137, 21, 52);
}
</style>

</head>
<body>
<div align="center">
		<%@ include file="/WEB-INF/user/top.jsp" %>
</div>
<div>
<b>추천 코딩 문제</b>
</div>

<div align="right">
	<select id="dataSort">
		<option value="recent">최신순</option>
		<option value="recommend">추천순</option>
	</select>
	<button type="button" class="button">정렬</button>
</div>

<table border="1" width="100%" class="recommend">
	<colgroup>
		<col style="width:45%">
		<col style="width:20%">
		<col style="width:15%">
		<col style="width:10%">
		<col style="width:10%">
	</colgroup>
	
	<tr>
		<th>글 제목</th>
		<th>알고리즘</th>
		<th>난이도</th>
		<th>추천수</th>
		<th>작성일자</th>
	</tr>

	<tr>
		<td class="info"></td>
		<td class="info"></td>
		<td class="info"></td>
		<td class="info"></td>
		<td class="info"></td>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	
</table>

<div align="right">
	<button type="button" class="button">문제등록</button>
</div>

</body>
</html>