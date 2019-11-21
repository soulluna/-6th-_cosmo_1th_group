package Main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/Main/*")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
				System.out.println("로그인 버튼 클릭");
				String eno = request.getParameter("eno");
				String pwd = request.getParameter("pwd");
				MemberDAO memberDAO = MemberDAO.getInstance(); //eno와 pwd를 담을 객체생성
				int result = memberDAO.ConfirmID(eno, pwd);
				if(result==1) {//로그인 성공
					MemberVO memberVO = memberDAO.getMember(eno);
					HttpSession session = request.getSession(); //세션을 열어준다.
					session.setAttribute("loginUser", memberVO);
					nextPage = "/Main01/indexMain.jsp";
				}else {//로그인 실패
					String message="아이디 혹은 비밀번호가 잘못되었습니다. 다시 입력해주세요";
					System.out.println(message);
					request.setAttribute("result", result);
					nextPage = "/index.jsp";
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
			else if(action.equals("/enoCheck.do")) {
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
}
