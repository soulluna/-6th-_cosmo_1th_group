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
		HttpSession session = request.getSession(); // 세션객체 생성
		
		if(session.getAttribute("loginUser")!=null) //중복로그인을 방지한다.
			url = "Main01/indexMain.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/index.jsp";
		String eno = request.getParameter("eno");
		String pwd = request.getParameter("pwd");
		MemberDAO member = MemberDAO.getInstance(); //eno와 pwd를 담을 객체생성
		int result = member.checkUser(eno);
		if(result==1) {//로그인 성공시
			MemberVO vo = member.getMember(eno);
			HttpSession session = request.getSession(); //세션을 열어준다.
			session.setAttribute("loginUser", vo);
			url = "Main01/indexMain.jsp";
		}else {//로그인 실패
			url = "/index.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

}
