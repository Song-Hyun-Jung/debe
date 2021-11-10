<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>수강 과목 수정</title>
<style>
	#btnSubjectUpdate {background-color:#a9173d; color:white; width:70px;}
	#subjectUpdateBtn {text-align:right;}
	td {width:250px;}
	table {border:1px solid green;}
</style>
</head>
<body>
 <h2>수강 과목 수정</h2>
 <h5>&nbsp;&nbsp;&nbsp;-수강 과목</h5>
  <div align="center">
   <form>
	<table>
		<tr>
			<td id="subject"> <!-- checkbox->radio -->
				<input type="checkbox" name="interesting" value="0" checked>프로그래밍논리의이해 <br/> <!-- value값 과목코드로 바꿀것 --> 
				<input type="checkbox" name="interesting" value="1">인터넷프로그래밍<br/>
				<input type="checkbox" name="interesting" value="2">컴퓨터프로그래밍 <br/>
				<input type="checkbox" name="interesting" value="3">이산수학<br/>
				<input type="checkbox" name="interesting" value="4">객체프로그래밍 <br/>
				<input type="checkbox" name="interesting" value="5">문제해결기법<br/>
				<input type="checkbox" name="interesting" value="6">객체지향언어2<br/>						
				<input type="checkbox" name="interesting" value="7">정보보호개론<br/>
				<input type="checkbox" name="interesting" value="8">데이터통신<br/>
				<input type="checkbox" name="interesting" value="9">컴퓨터구조<br/>
			</td>
			<td id="subject">
				<input type="checkbox" name="interesting" value="10">자료구조<br/>
				<input type="checkbox" name="interesting" value="11">웹프로그래밍<br/>
				<input type="checkbox" name="interesting" value="12">데이터베이스개론<br/>
				<input type="checkbox" name="interesting" value="13">소프트웨어공학<br/>
				<input type="checkbox" name="interesting" value="14">운영체제<br/>
				<input type="checkbox" name="interesting" value="15">모바일소프트웨어<br/>
				<input type="checkbox" name="interesting" value="16">웹서비스<br/>
				<input type="checkbox" name="interesting" value="17">네트워크<br/>
				<input type="checkbox" name="interesting" value="18">시스템/네트워크보안<br/>						
				<input type="checkbox" name="interesting" value="19">시스템프로그래밍<br/>
			</td>
			<td id="subject">
				<input type="checkbox" name="interesting" value="20">모바일응용<br/>
				<input type="checkbox" name="interesting" value="21">데이터베이스프로그래밍<br/>
				<input type="checkbox" name="interesting" value="22">알고리즘<br/>
				<input type="checkbox" name="interesting" value="23">빅데이터처리<br/>
				<input type="checkbox" name="interesting" value="24">IoT응용<br/>
			</td>
		</tr>
		<tr> <td>&nbsp;</td> </tr>
		<tr> <td id="subjectUpdateBtn" colspan="3"><input id="btnSubjectUpdate" type="submit" value="수정"></td> </tr>
	 </table>
	</form>
	</div>
</body>
</html>