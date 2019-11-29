package Main;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
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

/**
 * Servlet implementation class MainController
 */
@WebServlet("/Main/*")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ARTICLE_IMAGE_PATH = "C:\\Users\\KOSMO-23\\GitHub\\-6th-_cosmo_1th_group\\Team_project\\profileImages";  //이미지의 폴더까지의 경로를 저장

	final String OLD_FORMAT = "yyyy-MM-dd";
	final String NEW_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
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
			if(action!=null&&action.equals("/login.do")) {
				HttpSession session = request.getSession(); //세션을 열어준다.
				if(session.getAttribute("loginUser")!=null) {
					System.out.println("세션 살아있음");
					MemberVO memberVO = (MemberVO) session.getAttribute("loginUser");
					ApprovalService approvalService = new ApprovalService();
					DailySchdulDAO dailyScadulDAO = new DailySchdulDAO();
					List<ApprovalVO> appList = approvalService.mainList10(memberVO);
					List<DailySchdulVO> scadulList = dailyScadulDAO.listScadul(memberVO);
					session.setAttribute("loginUser", memberVO);
					request.setAttribute("appList", appList);
					request.setAttribute("scadulList", scadulList);
					nextPage = "/Main01/indexMain.jsp";
				}
				else {
					System.out.println("로그인 버튼 클릭");
					String eno = request.getParameter("eno");
					String pwd = request.getParameter("pwd");
					MemberDAO memberDAO = MemberDAO.getInstance(); //eno와 pwd를 담을 객체생성
					int result = memberDAO.ConfirmID(eno, pwd);
					if(result==-1) {//로그인 실패
						String message="아이디 혹은 비밀번호가 잘못되었습니다. 다시 입력해주세요";
						System.out.println(message);
						request.setAttribute("result", result);
						nextPage = "/index.jsp";
					}else {//로그인 성공
						MemberVO memberVO = memberDAO.getMember(eno);
						ApprovalService approvalService = new ApprovalService();
						DailySchdulDAO dailyScadulDAO = new DailySchdulDAO();
						List<ApprovalVO> appList = approvalService.mainList10(memberVO);
						List<DailySchdulVO> scadulList = dailyScadulDAO.listScadul(memberVO);
						session.setAttribute("loginUser", memberVO);
						request.setAttribute("appList", appList);
						request.setAttribute("scadulList", scadulList);
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
				DailySchdulDAO schDAO = new DailySchdulDAO();
				DailySchdulVO schVO = schDAO.selectSchdul(request.getParameter("schnum"));
				request.setAttribute("schVO", schVO);
				nextPage = "/Main01/Schduler/schdularDetails.jsp";
			}
			else if(action.equals("/SchedulFormWrite.do")) {
				System.out.println("스캐쥴 작성하기 클릭");
				nextPage = "/Main01/Schduler/SchdularWriteForm.jsp";
			}
			else if(action.equals("/SchedulWrite.do")) {
				System.out.println("스캐쥴 작성완료버튼 클릭");
				HttpSession session = request.getSession();
				MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
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
				schDAO.InsertSchdul(schVO);
				nextPage = "/Main/login.do";
			}
			else if(action.equals("SchdulDelete.do")) {
				System.out.println("댓글 삭제버튼 클릭");
				String schnum=request.getParameter("schnum");
				DailySchdulDAO schDAO = new DailySchdulDAO();
				schDAO.deleteSchdul(schnum);
				nextPage = "/Main/login.do";
			}
			else if(action.equals("/pwdConfirmForm.do")) {//메인페이지 및 gnb에서 개인정보 수정버튼 클릭
				System.out.println("비밀번호 수정버튼 클릭");
				nextPage = "/Main01/member/confirm.jsp";

			}
			else if(action.equals("/pwdConfirm.do")) {//비밀번호 확인페이지에서 확인버튼 클릭
				System.out.println("비밀번호 확인버튼 클릭");
				nextPage = "/Main01/member/confirm.jsp";
				String pwd = request.getParameter("pwd");
				String eno = request.getParameter("eno");
				String checked=null;
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
					request.setAttribute("result",result);
				}
				System.out.println("확인 페이지 체크여부 : "+checked);
				System.out.println("결과 : "+result);
			}
			else if(action.equals("/pwdChangeForm.do")) {//정보수정 선택페이지에서 비밀번호 수정하기 클릭
				System.out.println("비밀번호 수정하기 클릭");
				nextPage = "/Main01/member/pwd.jsp";
			}
			else if(action.equals("/pwdChange.do")) {
				System.out.println("비밀번호 수정버튼 클릭");
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
					nextPage="/Main01/member/select.jsp";
				}
				else {//결과값을 주어서 잘못됨을 말해줌
					request.setAttribute("result", result);
				}
				System.out.println("결과 : "+result);
			}
			else if(action.equals("/userInfoChangeForm.do")) {//정보수정 선택페이지에서 개인정보 수정하기 클릭
				System.out.println("개인정보 수정하기 클릭");
				nextPage = "/Main01/member/change.jsp";
			}
			else if(action.equals("/userInfoChange.do")) {
				System.out.println("정보수정완료 버튼 클릭");
				nextPage="/Main01/member/change.jsp";
				Map<String, String> memberMap = upload(request, response);
				MemberDAO memberDAO = MemberDAO.getInstance();
				MemberVO memberVO = new MemberVO();
				String eno=memberMap.get("eno");
				String ename=memberMap.get("ename");
				String eng_name=memberMap.get("eng_name");
				String tel=memberMap.get("tel");
				String email=memberMap.get("email");
				String imageFileName = memberMap.get("imageFileName");
				System.out.println(eno+"   "+ename+"   "+eng_name+"   "+tel+"   "+email);
				memberVO.setEno(eno);
				memberVO.setEname(ename);
				memberVO.setEng_name(eng_name);
				memberVO.setTel(tel);
				memberVO.setEmail(email);
				memberVO.setImageFileName(imageFileName);
				int result=memberDAO.UpdateUserInfo(memberVO);
				if(result==1) {
					nextPage="/Main01/member/select.jsp";
				}
				else {
					request.setAttribute("result", result);
					nextPage = "/Main01/member/change.jsp";
				}
				System.out.println("결과 : "+result);
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

	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{//이미지를 위한 파일 업로드
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		File currentDirPath = new File(ARTICLE_IMAGE_PATH);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			List items = upload.parseRequest(request);
			for(int i=0; i< items.size(); i++) {
				FileItem fileItem = (FileItem)items.get(i);
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName());
					//					   	System.out.println(fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}else {
					System.out.println(fileItem.getFieldName());
					//				   		System.out.println(fileItem.getString(encoding));
					System.out.println(fileItem.getSize());
					if(fileItem.getSize()>0) {
						int idx = fileItem.getName().lastIndexOf("\\"); //윈도우
						if(idx==-1) {//리눅스나 유닉스
							idx = fileItem.getName().lastIndexOf("/"); //윈도우
						}
						String fileName = fileItem.getName().substring(idx+1);
						System.out.println(fileName);
						articleMap.put(fileItem.getFieldName(), fileName);

						File uploadFile = new File(currentDirPath+"\\"+fileName);
						System.out.println(uploadFile);
						fileItem.write(uploadFile);
					}//end of Inner if
				}//end of Outer if

			}//end of for
		}catch(Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}
}
