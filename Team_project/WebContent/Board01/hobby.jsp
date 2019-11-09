<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>��̰Խ���</title>
    <link rel="stylesheet" href="./css/main.css">
    <link rel="stylesheet" href="./css/gnb.css" />
    <script src="./js/jquery-2.1.1.min.js"></script>
    <script src="./js/jquery.easing.1.3.js"></script>
    <script src="./js/jquery-ui.min.js"></script>
    <script src="./js/prefixfree.min.js"></script>
</head>

<body>
    <div class="fullWrap">
        <!--gnb-->
        <div class="gnb">
            <!--logoBar-->
            <ul class="logobar">
                <li id="mainLogo"><a href="../Team2_kim/indexMain.html"><img src="./img/logo3.gif"></a></li>
                <table id="memberinfo">
                    <tbody>
                        <tr>
                            <td id="profile_img" rowspan="2"><img src="http://placehold.it/70x70"></td>
                            <td colspan="2">������ �� ȯ���մϴ�.</td>
                        </tr>
                        <tr>
                            <td><a href="../index.html">�α׾ƿ�</a>
                                <a href="../Team2_kim/member/confirm.html">����������</a></td>
                        </tr>
                    </tbody>
                </table>
            </ul>
            <!--//logoBar-->
            <!--nav bar-->
            <ul class="topBar">
                <li id="main" class="t_menu btn3"> <a href="../Team2_kim/indexMain.html">����</a></li>
                <li id="system" class="t_menu btn1"> <a
                        href="../Team1_won&you/docList.html">���ڰ���ý���</a></li>
                <li id="board" class="t_menu btn2"> <a href="./noticeBoardMain.html">�Խ���</a></li>
                <li id="info_tab" class="t_menu btn4"> <a href="../Team2_kim/member/confirm.html">����������</a></li>
            </ul>
            <!--//navBar-->
        </div>
        <!--//gnb-->
        <h1>6�� 1�� ������Ʈ ��� �Խ���</h1>
        <div class="side">
            <a href="./noticeBoardMain.html"><div>��ü</div></a>
            <a href="./department.html"><div>�μ�</div></a>
            <a href="./hobby.html"><div>���</div></a>
            <a href="./free.html"><div>����</div></a>
        </div>
	<c:choose>
			<c:when test="${articlesList==null}">
				<tr>
					<td colspan="4">
						<p align="center">
							<b> <span>�Խñ��� �������� �ʽ��ϴ�.</span>
							</b>
						</p>
					</td>
				</tr>
			</c:when>			
	</c:choose>
        <div class="wjdduf">
          
            <div class="akwcna">
                <table border="1" class="line">
                    <thead>
                        <tr class="name">
                            <th class="co1">�Խ���</th>
                            <th class="co2">��ȣ</th>
                            <th class="co3">����</th>
                            <th class="co4">�ۼ���</th>
                            <th class="co5">��������</th>
                            <th class="co6">��ȸ</th>
                            <th class="co7">��õ</th>
                        </tr>
                    </thead>
                    <tbody>
                       <c:forEach var="board" items="${boardList}">
							<tr class="record">
								<td>${board.num}</td>
								<td>${board.title}</td>
								<td>${board.name}</td>
								<td>${board.writedate}</td>
								<td>${board.readcount}</td>
							</tr>
						</c:forEach>
     
                    </tbody>
                </table>
            </div>
            <div class="dlehd">
                <div class="page">
                    <a href="#">����</a>
                    <a href="#">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#">4</a>
                    <a href="#">5</a>
                    <a href="#">����</a>
                </div>

        <div class="wkrtjd"><a href="./write.html">�۾���</a></div>
    </div>

    <div class="gkeks">
        <select class="tpqn" name="searchType">
            <option value="����">����</option>
            <option value="����+����">����+����</option>
            <option value="�ۼ���">�ۼ���</option>
        </select>
        <div class="rjator">
            <input type="text" name="searchKey" placeholder="�˻��� �Է�">
            <button class="search"><a href="#">�˻�</button></a>
        </div>
    </div>
</div>
</div>
        <script src="./js/main.js"></script>
</body>

</html>