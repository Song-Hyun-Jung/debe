<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� ���� ����</title>
<style>
	#btnSubjectUpdate {background-color:#a9173d; color:white; width:70px;}
	#subjectUpdateBtn {text-align:right;}
	td {width:250px;}
	table {border:1px solid green;}
</style>
</head>
<body>
 <h2>���� ���� ����</h2>
 <h5>&nbsp;&nbsp;&nbsp;-���� ����</h5>
  <div align="center">
   <form>
	<table>
		<tr>
			<td id="subject"> <!-- checkbox->radio -->
				<input type="checkbox" name="interesting" value="0" checked>���α׷��ֳ������� <br/> <!-- value�� �����ڵ�� �ٲܰ� --> 
				<input type="checkbox" name="interesting" value="1">���ͳ����α׷���<br/>
				<input type="checkbox" name="interesting" value="2">��ǻ�����α׷��� <br/>
				<input type="checkbox" name="interesting" value="3">�̻����<br/>
				<input type="checkbox" name="interesting" value="4">��ü���α׷��� <br/>
				<input type="checkbox" name="interesting" value="5">�����ذ���<br/>
				<input type="checkbox" name="interesting" value="6">��ü������2<br/>						
				<input type="checkbox" name="interesting" value="7">������ȣ����<br/>
				<input type="checkbox" name="interesting" value="8">���������<br/>
				<input type="checkbox" name="interesting" value="9">��ǻ�ͱ���<br/>
			</td>
			<td id="subject">
				<input type="checkbox" name="interesting" value="10">�ڷᱸ��<br/>
				<input type="checkbox" name="interesting" value="11">�����α׷���<br/>
				<input type="checkbox" name="interesting" value="12">�����ͺ��̽�����<br/>
				<input type="checkbox" name="interesting" value="13">����Ʈ�������<br/>
				<input type="checkbox" name="interesting" value="14">�ü��<br/>
				<input type="checkbox" name="interesting" value="15">����ϼ���Ʈ����<br/>
				<input type="checkbox" name="interesting" value="16">������<br/>
				<input type="checkbox" name="interesting" value="17">��Ʈ��ũ<br/>
				<input type="checkbox" name="interesting" value="18">�ý���/��Ʈ��ũ����<br/>						
				<input type="checkbox" name="interesting" value="19">�ý������α׷���<br/>
			</td>
			<td id="subject">
				<input type="checkbox" name="interesting" value="20">���������<br/>
				<input type="checkbox" name="interesting" value="21">�����ͺ��̽����α׷���<br/>
				<input type="checkbox" name="interesting" value="22">�˰���<br/>
				<input type="checkbox" name="interesting" value="23">������ó��<br/>
				<input type="checkbox" name="interesting" value="24">IoT����<br/>
			</td>
		</tr>
		<tr> <td>&nbsp;</td> </tr>
		<tr> <td id="subjectUpdateBtn" colspan="3"><input id="btnSubjectUpdate" type="submit" value="����"></td> </tr>
	 </table>
	</form>
	</div>
</body>
</html>