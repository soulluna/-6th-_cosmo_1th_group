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
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/Main01/registration/reg.jsp"); // 경로지정
		rd.forward(request, response); // 다음페이지로 이동
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //요청객체 utf-8로 지정
		response.setContentType("text/html;charset=utf-8"); //응답타입 utf-8로 지정
		System.out.println("회원가입버튼 클릭");
		String eno = request.getParameter("eno");
		String ename = request.getParameter("ename");
		String pwd = request.getParameter("pwd");
		String dname = request.getParameter("dname");
		String dname_two = request.getParameter("dname_two");
		String rank="사원";
		MemberVO vo = new MemberVO();
		String url="/Main01/registration/reg.jsp";
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
		if(result==1) {
			session.setAttribute(eno, vo.getEno());
			url="/index.jsp";
		}
		System.out.println("다음페이지 : "+url);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url); // 경로지정
		dispatcher.forward(request, response); // 다음페이지로 이동
	}
	

}
