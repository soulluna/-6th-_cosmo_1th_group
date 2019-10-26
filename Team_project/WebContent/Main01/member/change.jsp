<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>��������</title>
<script src="./js/jquery-2.1.1.min.js"></script>
<script src="./js/jquery-ui.min.js"></script>
<script src="./js/jquery.easing.1.3.js"></script>
<script src="./js/prefixfree.min.js"></script>
<script src="js/change.js"></script>
<link rel="stylesheet" href="./css/change.css" />
<link rel="stylesheet" href="../css/gnb.css" />
</head>

<body>
	<form onsubmit="return validate();" action="./select.html">
		<div class="fullWrap">
			<!--gnb-->
			<div class="gnb">
				<!--logoBar-->
				<ul class="logobar">
					<li id="mainLogo"><a href="../indexMain.html"><img
							src="./img/logo3.gif"></a></li>
					<table id="memberinfo">
						<tbody>
							<tr>
								<td id="profile_img" rowspan="2"><img
									src="http://placehold.it/70x70"></td>
								<td colspan="2">������ �� ȯ���մϴ�.</td>
							</tr>
							<tr>
								<td><a href="../../index.html">�α׾ƿ�</a> <a
									href="./confirm.html">����������</a></td>
							</tr>
						</tbody>
					</table>
				</ul>
				<!--//logoBar-->
				<!--nav bar-->
				<ul class="topBar">
					<li id="main" class="t_menu btn3"><a href="../indexMain.html">����</a></li>
					<li id="system" class="t_menu btn1"><a
						href="../Team1_won&you/docList.html">���ڰ���ý���</a></li>
					<li id="board" class="t_menu btn2"><a
						href="../Team3_cha/noticeBoardMain.html">�Խ���</a></li>
					<li id="info_tab" class="t_menu btn4"><a href="./confirm.html">����������</a></li>
				</ul>
				<!--//navBar-->
			</div>
			<!--//gnb-->
			<!--top_side-->
			<div id="top_section">
				<div class="section_menu">�������</div>
				<table id="info_table">
					<tbody class="filebox">
						<tr>
							<td rowspan="4" align="center"><img
								src="http://placehold.it/120x150" id="imgPreview" width="120"
								height="150"><br> <input type="file" id="img"
								onchange="showImagePreview(this);"> <label for="img">÷��</label>
							</td>
							<td class="text_info">���</td>
							<td><input type="text" readonly></td>
							<td class="text_info">�μ�</td>
							<td>
								<select class="area" id="dep1">
									<option value="0">�����ϼ���</option>
									<option value="1">������</option>
									<option value="2">�λ��</option>
									<option value="3">���������</option>
								</select></td>
							<td class="text_info">�Ҽ�</td>
							<td>
								<select class="area" id="dep2">
									<option value="0">�����ϼ���</option>
									<option value="4">1��</option>
									<option value="5">2��</option>
									<option value="6">3��</option>
							</select></td>
						</tr>
						<tr>
							<td class="text_info">�Ի���</td>
							<td><input type="date" readonly></td>
							<td class="text_info">����</td>
							<td><input type="text" readonly="readonly"></td>
						</tr>
						<tr>
							<td class="text_info">�̸�</td>
							<td><input type="text" id="userName"
								placeholder="�ѱ۸� �Է����ּ���"></td>
							<td class="text_info">�����̸�</td>
							<td><input type="text" id="engName" placeholder="������ �Է����ּ���"></td>
						</tr>
						<tr>
							<td class="text_info">����ó</td>
							<td><input type="phone" id="phone" placeholder="-�� ���� �Է��ϼ���."></td>
							<td class="text_info">�̸���</td>
							<td><input type="text" id="email"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<!--//top_side-->
			<div id="button_section">
				<input type="submit" value="Ȯ��" class="e_btn"> <input
					type="button" value="���" class="e_btn" onclick="cancel();">
			</div>
		</div>
		<!--//fullwarp-->
	</form>
</body>
</html>