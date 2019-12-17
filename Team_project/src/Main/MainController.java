package Main;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Approval.ApprovalService;
import Approval.ApprovalVO;
import Board.BoardDAO;
import Board.BoardVO;
import Board.Boardservice;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/Main/*")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//이미지의 폴더까지의 경로를 저장
	private static String ARTICLE_IMAGE_PATH = "C:\\Users\\KOSMO-23\\GitHub\\-6th-_cosmo_1th_group\\Team_project\\profileImages";  
	//dataformat 지정
	final String DATE_FORMAT = "yyyy-MM-dd";
	final String TIME_FORMAT = "HH:mm";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		try {
			if(action!=null&&action.equals("/login.do")) {//로그인 및 세션이 살아있는지 확인하는 구문
				HttpSession session = request.getSession(); //세션을 열어준다.
				if(session.getAttribute("loginUser")!=null) { // 로그인 되어있는 상태면
					System.out.println("세션 살아있음");
					MemberVO memberVO = (MemberVO) session.getAttribute("loginUser");
					String getDate=request.getParameter("date");
					ApprovalService approvalService = new ApprovalService();
					DailySchdulDAO dailyScadulDAO = new DailySchdulDAO();
					Boardservice boardService = new Boardservice();
					List<DailySchdulVO> schdulList=null;
					List<DailySchdulVO> schdulList2=null;
					List<DateFormat> startDateList = new ArrayList<DateFormat>();
					DateFormat nowDate = new DateFormat();
					if(getDate!=null) { 
						if(request.getParameter("month")!=null) { // prev 혹은 next 이동
							System.out.println("달 이동");
							DateFormat formatDate2 = new DateFormat(); // startDate
							DateFormat formatDate = new DateFormat(); // endDate
							String[] str2=getDate.split("-");
							String[] str=getDate.split("-");
							formatDate2.setYear(str2[0]);
							formatDate.setYear(str[0]);
							if(str[1].length()==1) {
								str[1]="0"+str[1];
								str2[1]="0"+str2[1];
							}
							formatDate2.setMonth(str2[1]);
							formatDate.setMonth(str[1]);
							//종료날짜 설정
							switch(str[1]) {
								case "01": case "03": case "05": case "07": case "08": case "10": case "12": formatDate.setDay("31"); break;
								case "04": case "06": case "09": case "11": formatDate.setDay("30"); break;
								case "02": formatDate.setDay("28");
							}
							formatDate2.setDay("01");
							if(Integer.parseInt(str[0])%4==0&&(str[1]=="02")) {
								formatDate.setDay("29");
							}
							//db에 가기 위해 다시묶음
							String newStartDate = formatDate2.getYear()+"-"+formatDate2.getMonth()+"-"+formatDate2.getDay();
							String newEndDate = formatDate.getYear()+"-"+formatDate.getMonth()+"-"+formatDate.getDay();
							System.out.println("newStartDate : "+newStartDate);
							System.out.println("newEndDate : "+newEndDate);
							//schdulList : 스캐쥴러 아래꺼 / schdulList2 : 달력에 뿌릴 스캐쥴러
							schdulList=dailyScadulDAO.listScadul(memberVO,newStartDate, newEndDate);
							schdulList2 = dailyScadulDAO.listAllScadul(memberVO, newStartDate, newEndDate);
							//시작날짜만 필요하기 때문에 시작날짜만 스플릿함
							for(int i=0;i<schdulList2.size();i++) { 
								Timestamp dates = schdulList2.get(i).getStartDate();  
								String[] str3 = dates.toString().split(" ");
								String[] str4 = str3[0].split("-");
								DateFormat input = new DateFormat();
								input.setYear(str4[0]);
								input.setMonth(str4[1]);
								input.setDay(str4[2]);
								startDateList.add(input);
							}
							nowDate.setYear(formatDate2.getYear());
							nowDate.setMonth(formatDate.getMonth());
							System.out.println("뿌려주는 연도 : "+nowDate.getYear());
							System.out.println("뿌려주는 달 : "+nowDate.getMonth());
						}
						else { //특정 날짜클릭
							System.out.println("특정 날짜 클릭");
							schdulList=dailyScadulDAO.listScadul(memberVO,getDate);
							DateFormat formatDate2 = new DateFormat(); //특정 날짜에 해당하는 달의 첫날
							DateFormat formatDate = new DateFormat(); // 특정 날짜에 해당하는 달의 마지막날
							String[] str2=getDate.split("-");
							String[] str=getDate.split("-");
							formatDate2.setYear(str2[0]);
							formatDate.setYear(str[0]);
							if(str[1].length()==1) {
								str[1]="0"+str[1];
								str2[1]="0"+str2[1];
							}
							formatDate2.setMonth(str2[1]);
							formatDate.setMonth(str[1]);
							switch(str[1]) {
								case "01": case "03": case "05": case "07": case "08": case "10": case "12": formatDate.setDay("31"); break;
								case "04": case "06": case "09": case "11": formatDate.setDay("30"); break;
								case "02": formatDate.setDay("28");
							}
							formatDate2.setDay("01");
							if(Integer.parseInt(str[0])%4==0&&(str[1]=="02")) {
								formatDate.setDay("29");
							}
							String newStartDate = formatDate2.getYear()+"-"+formatDate2.getMonth()+"-"+formatDate2.getDay();
							String newEndDate = formatDate.getYear()+"-"+formatDate.getMonth()+"-"+formatDate.getDay();
							schdulList2 = dailyScadulDAO.listAllScadul(memberVO, newStartDate, newEndDate);//전체 스캐쥴을 가져와서 달력에 뿌리기 위함
							for(int i=0;i<schdulList2.size();i++) { 
								Timestamp dates = schdulList2.get(i).getStartDate();  
								String[] str3 = dates.toString().split(" ");
								String[] str4 = str3[0].split("-");
								DateFormat input = new DateFormat();
								input.setYear(str4[0]);
								input.setMonth(str4[1]);
								input.setDay(str4[2]);
								startDateList.add(input);
							}
							nowDate.setYear(formatDate2.getYear());
							nowDate.setMonth(formatDate.getMonth());
							System.out.println("뿌려주는 연도 : "+nowDate.getYear());
							System.out.println("뿌려주는 달 : "+nowDate.getMonth());
						}
					}
					else { // 다른 페이지 갔다오는 경우
						System.out.println("다른페이지 갔다옴");
						//다른 페이지 갔다 왔기 때문에 현재 날짜를 기준으로 뿌려주면 된다.
						long systemTime=System.currentTimeMillis();
						SimpleDateFormat format = new  SimpleDateFormat(DATE_FORMAT,Locale.KOREA);
						String dTime=format.format(systemTime);
						System.out.println(dTime);
						String[] splTime=dTime.split("-");
						System.out.print("시간 : ");
						for(int i=0;i<splTime.length;i++) {
							System.out.print(splTime[i]+"  ");
						}
						System.out.println();
						nowDate.setYear(splTime[0]);
						nowDate.setMonth(splTime[1]);
						
						String newStartDate = nowDate.getYear()+"-"+nowDate.getMonth()+"-"+"01";
						String newStartDate2 = nowDate.getYear()+"-"+nowDate.getMonth()+"-"+splTime[2];
						switch(splTime[1]){
							case "01": case "03": case "05": case "07": case "08": case "10": case "12": nowDate.setDay("31"); break;
							case "04": case "06": case "09": case "11": nowDate.setDay("30"); break;
							case "02": nowDate.setDay("28");
						}
						if(Integer.parseInt(splTime[0])%4==0&&(splTime[1]=="02")) {
							nowDate.setDay("29");
						}
						String newEndDate=nowDate.getYear()+"-"+nowDate.getMonth()+"-"+nowDate.getDay();
						schdulList2 = dailyScadulDAO.listAllScadul(memberVO, newStartDate, newEndDate); // 달력에 표시할 것
						schdulList=dailyScadulDAO.listScadul(memberVO,newStartDate2, newEndDate); // 스캐쥴러에 표시할 것
						for(int i=0;i<schdulList2.size();i++) { 
							Timestamp dates = schdulList2.get(i).getStartDate();  
							String[] str = dates.toString().split(" ");
							String[] str2 = str[0].split("-");
							DateFormat input = new DateFormat();
							input.setYear(str2[0]);
							input.setMonth(str2[1]);
							input.setDay(str2[2]);
							startDateList.add(input);
						}
						System.out.println("뿌려주는 연도 : "+nowDate.getYear());
						System.out.println("뿌려주는 달 : "+nowDate.getMonth());
					}
					//각 메소드를 통해 해당 정보를 받아옴
					List<ApprovalVO> appList = approvalService.mainList10(memberVO);
					List<BoardVO> boardList = boardService.selectAllBoards10();
					//setAttribute를 통해 정보를 jsp페이지로 전송
					session.setAttribute("loginUser", memberVO);
					request.setAttribute("appList", appList);
					request.setAttribute("scadulList", schdulList);
					request.setAttribute("boardList", boardList);
					request.setAttribute("startDateList", startDateList);
					request.setAttribute("nowDate", nowDate);
					//다음 페이지 이동
					nextPage = "/Main01/indexMain.jsp";
				}
				else {//세션이 닫혀있는 상태면(최초로그인)
					System.out.println("로그인 버튼 클릭");
					String eno = request.getParameter("eno");
					String pwd = request.getParameter("pwd");
					MemberDAO memberDAO = MemberDAO.getInstance(); 
					int result = memberDAO.ConfirmID(eno, pwd); //데이터베이스에 해당 사번 및 비밀번호가 맞는지 확인
					if(result==-1) {//로그인 실패
						String message="아이디 혹은 비밀번호가 잘못되었습니다. 다시 입력해주세요";
						System.out.println(message);
						request.setAttribute("result", 1);
						nextPage = "/index.jsp";
					}else { //로그인 성공
						//각 사번에 해당하는 사원정보, 스캐쥴정보, 결재정보, 게시판 정보를 받아오기 위한 객체 생성
						MemberVO memberVO = memberDAO.getMember(eno); 
						ApprovalService approvalService = new ApprovalService();
						DailySchdulDAO dailyScadulDAO = new DailySchdulDAO();
						Boardservice boardService = new Boardservice();
						//각 메소드를 통해 해당 정보를 받아옴
						List<ApprovalVO> appList = approvalService.mainList10(memberVO);
						List<BoardVO> boardList = boardService.selectAllBoards10();
						List<DailySchdulVO> schdulList=null;
						List<DailySchdulVO> schdulList2=null;
						long systemTime=System.currentTimeMillis();
						SimpleDateFormat format = new  SimpleDateFormat(DATE_FORMAT,Locale.KOREA);
						String dTime=format.format(systemTime);
						System.out.println(dTime);
						String[] splTime=dTime.split("-");
						DateFormat nowDate = new DateFormat();
						nowDate.setYear(splTime[0]);
						nowDate.setMonth(splTime[1]);
						String newStartDate = nowDate.getYear()+"-"+nowDate.getMonth()+"-"+"01";
						String newStartDate2 = nowDate.getYear()+"-"+nowDate.getMonth()+"-"+splTime[2];
						switch(splTime[1]){
							case "01": case "03": case "05": case "07": case "08": case "10": case "12": nowDate.setDay("31"); break;
							case "04": case "06": case "09": case "11": nowDate.setDay("28"); break;
						}
						if(Integer.parseInt(splTime[0])%4==0&&(splTime[1]=="02")) {
							nowDate.setDay("29");
						}
						String newEndDate=nowDate.getYear()+"-"+nowDate.getMonth()+"-"+nowDate.getDay();
						List<DateFormat> startDateList = new ArrayList<DateFormat>();
						schdulList=dailyScadulDAO.listScadul(memberVO,newStartDate2, newEndDate); // 스캐쥴러에 뿌려줄 것
						schdulList2 = dailyScadulDAO.listAllScadul(memberVO, newStartDate, newEndDate);//달력에 뿌려줄 것
						for(int i=0;i<schdulList2.size();i++) { 
							Timestamp dates = schdulList2.get(i).getStartDate();  
							String[] str = dates.toString().split(" ");
							String[] str2 = str[0].split("-");
							DateFormat input = new DateFormat();
							input.setYear(str2[0]);
							input.setMonth(str2[1]);
							input.setDay(str2[2]);
							startDateList.add(input);
						}
						//setAttribute를 통해 정보를 jsp페이지로 전송
						session.setAttribute("loginUser", memberVO);
						request.setAttribute("appList", appList);
						request.setAttribute("scadulList", schdulList);
						request.setAttribute("boardList", boardList);
						request.setAttribute("startDateList", startDateList);
						request.setAttribute("nowDate", nowDate);
						//다음 페이지 이동
						nextPage = "/Main01/indexMain.jsp";
					}
				}
			}
			else if(action.equals("/joinForm.do")) {//회원가입 페이지 이동
				System.out.println("회원가입 클릭");
				nextPage = "/Main01/registration/reg.jsp";
			}
			else if(action.equals("/join.do")) {
				System.out.println("회원가입버튼 클릭");
				String eno = request.getParameter("eno");
				String ename = request.getParameter("ename");
				String pwd = request.getParameter("pwd");
				String dname = request.getParameter("dname");
				String dname_two = request.getParameter("dname_two");
				String rank="사원";
				MemberVO vo = new MemberVO();
				nextPage="/Main01/registration/reg.jsp";
				vo.setEno(eno);
				vo.setEname(ename);
				vo.setPwd(pwd);
				vo.setDname(dname);
				vo.setDname_two(dname_two);
				vo.setRank(rank);
				MemberDAO dao = MemberDAO.getInstance();
				int result = dao.insertMember(vo); // insertMember함수는 vo를 참조한다.
				System.out.println("결과 : "+result);
				HttpSession session = request.getSession();
				if(result==1) {//아이디가 존재하는 경우 로그인페이지로 넘어감
					session.setAttribute(eno, vo.getEno());
					nextPage="/index.jsp";
				}
			}
			else if(action.equals("/enoCheck.do")) {//중복체크
				System.out.println("중복체크 버튼 클릭");
				String eno = request.getParameter("eno");
				System.out.println(eno);
				MemberDAO dao = MemberDAO.getInstance();
				int result = dao.checkUser(eno);
				request.setAttribute("eno", eno);
				request.setAttribute("result", result);
				nextPage="/Main01/registration/enoCheck.jsp";
				System.out.println("결과 : "+result);
			}
			else if(action.equals("/logout.do")) {
				System.out.println("로그아웃 버튼 클릭");
				nextPage = "/index.jsp";
				HttpSession session = request.getSession();
				session.invalidate(); // 세션종료
			}
			else if(action.equals("/schdulDetail.do")) {
				System.out.println("스캐쥴 상세보기 클릭");
				HttpSession session = request.getSession();
				MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
				if(loginUser==null) {
					System.out.println("세션이 끊어짐");
					nextPage = "/index.jsp";
					request.setAttribute("result", 2);
				}
				else {
					DailySchdulDAO schDAO = new DailySchdulDAO();
					DailySchdulVO schVO = schDAO.selectSchdul(request.getParameter("schnum"));
					request.setAttribute("schVO", schVO);
					nextPage = "/Main01/Schduler/schdularDetails.jsp";
				}
			}
			else if(action.equals("/schedulWriteForm.do")) {
				HttpSession session = request.getSession();
				MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
				if(loginUser==null) {
					System.out.println("세션이 끊어짐");
					nextPage = "/index.jsp";
					request.setAttribute("result", 2);
				}
				else {
				System.out.println("스캐쥴 작성하기 클릭");
				nextPage = "/Main01/Schduler/SchdularWriteForm.jsp";
				}
			}
			else if(action.equals("/schedulWrite.do")) {
				System.out.println("스캐쥴 작성완료버튼 클릭");
				HttpSession session = request.getSession();
				MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
				if(loginUser==null) {
					System.out.println("세션이 끊어짐");
					nextPage = "/index.jsp";
					request.setAttribute("result", 2);
					
				}
				else {
				DailySchdulVO schVO = new DailySchdulVO();
				DailySchdulDAO schDAO = new DailySchdulDAO();
				schVO.setEno(loginUser.getEno());
				schVO.setEname(loginUser.getEname());
				schVO.setRank(loginUser.getRank());
				schVO.setSchname(request.getParameter("schname"));
				schVO.setSchcont(request.getParameter("schcont"));
				String startDate2 = request.getParameter("startDate");
				String endDate2 = request.getParameter("endDate");
				String startTime = request.getParameter("startTime");
				String endTime = request.getParameter("endTime");
				startDate2 += " "+ startTime;
				endDate2 += " "+ endTime;
				startDate2+=":000000";
				endDate2+=":000000";
				System.out.println(startDate2);
				System.out.println(endDate2);
				Timestamp startDate = Timestamp.valueOf(startDate2);
				Timestamp endDate = Timestamp.valueOf(endDate2);
				schVO.setStartDate(startDate);
				schVO.setEndDate(endDate);			
				schDAO.insertSchdul(schVO);
				nextPage = "/Main/login.do";
				}
			}
			else if(action.equals("/schdulDelete.do")) {
				System.out.println("스캐쥴 삭제버튼 클릭");
				HttpSession session = request.getSession();
				MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
				if(loginUser==null) {
					System.out.println("세션이 끊어짐");
					nextPage = "/index.jsp";
					request.setAttribute("result", 2);
					
				}
				else {
				String schnum=request.getParameter("schnum");
				DailySchdulDAO schDAO = new DailySchdulDAO();
				schDAO.deleteSchdul(schnum);
				nextPage = "/Main/login.do";
				}
			}
			else if(action.equals("/schdulUpdateForm.do")) {
				System.out.println("스캐쥴 수정버튼 클릭");
				HttpSession session = request.getSession();
				MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
				if(loginUser==null) {
					System.out.println("세션이 끊어짐");
					nextPage = "/index.jsp";
					request.setAttribute("result", 2);
					
				}
				else {
				DailySchdulDAO schDAO = new DailySchdulDAO();
				DailySchdulVO schVO = schDAO.selectSchdul(request.getParameter("schnum"));
				Timestamp startDate=schVO.getStartDate();
				Timestamp endDate = schVO.getEndDate();
				Date sdate = new Date(0);
				Date edate = new Date(0);
				sdate.setTime(startDate.getTime());
				edate.setTime(endDate.getTime());
				
				String formatsDate = new SimpleDateFormat(DATE_FORMAT).format(sdate);
				String formateDate = new SimpleDateFormat(DATE_FORMAT).format(edate);
				String startTime = new SimpleDateFormat(TIME_FORMAT).format(sdate);
				String endTime = new SimpleDateFormat(TIME_FORMAT).format(edate);
				request.setAttribute("startDate", formatsDate);
				request.setAttribute("endDate", formateDate);
				request.setAttribute("startTime", startTime);
				request.setAttribute("endTime", endTime);
				request.setAttribute("schVO", schVO);
				nextPage = "/Main01/Schduler/schdularUpdateForm.jsp";
				}
			}
			else if(action.equals("/schedulUpdate.do")) {
				System.out.println("스캐쥴 수정완료 클릭");
				HttpSession session = request.getSession();
				MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
				if(loginUser==null) {
					System.out.println("세션이 끊어짐");
					nextPage = "/index.jsp";
					request.setAttribute("result", 2);
					
				}
				else {
				DailySchdulVO schVO = new DailySchdulVO();
				DailySchdulDAO schDAO = new DailySchdulDAO();
				schVO.setEno(loginUser.getEno());
				schVO.setEname(loginUser.getEname());
				schVO.setRank(loginUser.getRank());
				schVO.setSchnum(Integer.parseInt(request.getParameter("schnum")));
				schVO.setSchname(request.getParameter("schname"));
				schVO.setSchcont(request.getParameter("schcont"));
				String startDate2 = request.getParameter("startDate");
				String endDate2 = request.getParameter("endDate");
				String startTime = request.getParameter("startTime");
				String endTime = request.getParameter("endTime");
				startDate2 += " "+ startTime;
				endDate2 += " "+ endTime;
				startDate2+=":000000";
				endDate2+=":000000";
				System.out.println(startDate2);
				System.out.println(endDate2);
				Timestamp startDate = Timestamp.valueOf(startDate2);
				Timestamp endDate = Timestamp.valueOf(endDate2);
				schVO.setStartDate(startDate);
				schVO.setEndDate(endDate);
				schDAO.updateSchdul(schVO);
				nextPage = "/Main/login.do";
				}
			}
			else if(action.equals("/pwdConfirmForm.do")) {//메인페이지 및 gnb에서 개인정보 수정버튼 클릭
				System.out.println("비밀번호 수정버튼 클릭");
				HttpSession session = request.getSession();
				MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
				if(loginUser==null) {
					System.out.println("세션이 끊어짐");
					nextPage = "/index.jsp";
					request.setAttribute("result", 2);
				}
				else {
				nextPage = "/Main01/member/confirm.jsp";
				}

			}
			else if(action.equals("/pwdConfirm.do")) {//비밀번호 확인페이지에서 확인버튼 클릭
				System.out.println("비밀번호 확인버튼 클릭");
				HttpSession session = request.getSession();
				MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
				String pwd = request.getParameter("pwd");
				String eno = request.getParameter("eno");
				String checked=request.getParameter("checked");
				if(loginUser==null) {
					System.out.println("세션이 끊어짐");
					nextPage = "/index.jsp";
					request.setAttribute("result", 2);
				}
				else if(checked!=null) {
					System.out.println("비밀번호 확인 완료됨");
					nextPage = "/Main01/member/select.jsp";
				}
				else {
				nextPage = "/Main01/member/confirm.jsp";
				System.out.println(eno+"   "+pwd);
				MemberDAO memberDAO = MemberDAO.getInstance(); //eno와 pwd를 담을 객체생성
				//System.out.println(eno);
				int result = memberDAO.ConfirmID(eno, pwd);
				if(result==1) {
					checked="checked";
					nextPage = "/Main01/member/select.jsp";
					request.setAttribute("checked",checked);
				}
				else {
					nextPage = "/Main01/member/confirm.jsp";
					request.setAttribute("result",1);
				}
				System.out.println("확인 페이지 체크여부 : "+checked);
				System.out.println("결과 : "+result);
				}
			}
			else if(action.equals("/pwdChangeForm.do")) {//정보수정 선택페이지에서 비밀번호 수정하기 클릭
				System.out.println("비밀번호 수정하기 클릭");
				HttpSession session = request.getSession();
				MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
				if(loginUser==null) {
					System.out.println("세션이 끊어짐");
					nextPage = "/index.jsp";
					request.setAttribute("result", 2);
					
				}
				else {
				nextPage = "/Main01/member/pwd.jsp";
				}
			}
			else if(action.equals("/pwdChange.do")) {
				System.out.println("비밀번호 수정버튼 클릭");
				HttpSession session = request.getSession();
				MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
				if(loginUser==null) {
					System.out.println("세션이 끊어짐");
					nextPage = "/index.jsp";
					request.setAttribute("result", 2);
				}
				else {
				nextPage="/Main01/member/pwd.jsp";
				String eno=request.getParameter("eno");
				String pwd=request.getParameter("changePwd");
				System.out.println("변경된 비밀번호 : "+pwd);
				MemberDAO memberDAO = MemberDAO.getInstance();
				MemberVO memberVO = new MemberVO();
				memberVO.setEno(eno);
				memberVO.setPwd(pwd);
				int result=memberDAO.updatePwd(memberVO);
				if(result==1) {
					memberVO = memberDAO.getMember(eno);
					session.setAttribute("loginUser", memberVO);
					nextPage="/Main01/member/select.jsp";
				}
				else {//결과값을 주어서 잘못됨을 말해줌
					request.setAttribute("result", result);
				}
				System.out.println("결과 : "+result);
				}
			}
			else if(action.equals("/userInfoChangeForm.do")) {//정보수정 선택페이지에서 개인정보 수정하기 클릭
				System.out.println("개인정보 수정하기 클릭");
				HttpSession session = request.getSession();
				MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
				if(loginUser==null) {
					System.out.println("세션이 끊어짐");
					nextPage = "/index.jsp";
					request.setAttribute("result", 2);
					
				}
				else {
				nextPage = "/Main01/member/change.jsp";
				}
			}
			else if(action.equals("/userInfoChange.do")) {
				System.out.println("정보수정완료 버튼 클릭");
				HttpSession session = request.getSession();
				MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
				if(loginUser==null) {
					System.out.println("세션이 끊어짐");
					nextPage = "/index.jsp";
					request.setAttribute("result", 2);		
				}
				else {
				nextPage="/Main01/member/change.jsp";		
				MemberDAO memberDAO = MemberDAO.getInstance();
				MemberVO memberVO = new MemberVO();
				String eno=request.getParameter("eno");
				String ename=request.getParameter("ename");
				String eng_name=request.getParameter("eng_name");
				String tel=request.getParameter("tel");
				String email=request.getParameter("email");
				System.out.println(eno+"   "+ename+"   "+eng_name+"   "+tel+"   "+email);
				memberVO.setEno(eno);
				memberVO.setEname(ename);
				memberVO.setEng_name(eng_name);
				memberVO.setTel(tel);
				memberVO.setEmail(email);
				int result=memberDAO.UpdateUserInfo(memberVO);
				if(result==1) {
					memberVO = memberDAO.getMember(eno);
					session.setAttribute("loginUser", memberVO);
					nextPage="/Main01/member/select.jsp";
				}
				else {
					request.setAttribute("result", result);
					nextPage = "/Main01/member/change.jsp";
				}
				System.out.println("결과 : "+result);
				}
			}
			else { //그 외의 경우에는 로그인이 되어있는지 되어있지 않은지 세션을 이용하여 파악한 후, 로그인이 되어있으면 메인페이지로, 되어있지 않으면 로그인페이지로 이동
				HttpSession session = request.getSession();
				if(session.getAttribute("loginUser")!=null) {
					System.out.println("세션이 살아있음");
					nextPage = "/Main01/indexMain.jsp";
				}
				else {
					System.out.println("세션이 죽어있음");
					nextPage = "/index.jsp";
				}
			}
			System.out.println("다음페이지 : "+nextPage);
			System.out.println();
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);//모델2 기반
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
