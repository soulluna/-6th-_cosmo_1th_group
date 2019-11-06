package Main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/index.jsp";
		System.out.println("세션 유지");
		HttpSession session = request.getSession(); // 세션객체 생성
		if(session.getAttribute("loginUser")!=null) //중복로그인을 방지한다.
			url = "Main01/indexMain.jsp";
		System.out.println("다음페이지 : "+url);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //요청객체 utf-8로 지정
		response.setContentType("text/html;charset=utf-8"); //응답타입 utf-8로 지정
		String url = "/index.jsp";
		System.out.println("로그인 버튼 클릭");
		String eno = request.getParameter("eno");
		String pwd = request.getParameter("pwd");
		MemberDAO memberDAO = MemberDAO.getInstance(); //eno와 pwd를 담을 객체생성
		int result = memberDAO.ConfirmID(eno, pwd);
		if(result==1) {//로그인 성공시
			MemberVO memberVO = memberDAO.getMember(eno);
			HttpSession session = request.getSession(); //세션을 열어준다.
			session.setAttribute("loginUser", memberVO);
			url = "Main01/indexMain.jsp";
		}else {//로그인 실패
			String message="아이디 혹은 비밀번호가 잘못되었습니다. 다시 입력해주세요";
			System.out.println(message);
			request.setAttribute("result", result);
			url = "/index.jsp";
		}
		System.out.println("결과 : "+result);
		System.out.println("다음페이지 : "+url);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
