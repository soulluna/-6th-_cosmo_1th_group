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
 * Servlet implementation class PwdConfirmServlet
 */
@WebServlet("/pwdConfirm.do")
public class PwdConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdConfirmServlet() {
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
		String url="/Main01/member/confirm.jsp";
		System.out.println("개인정보 수정 탭 누름");
		String eno = request.getParameter("eno");
		//System.out.println(eno);
		request.setAttribute("eno",eno);
		System.out.println("다음 페이지 : "+url);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //요청객체 utf-8로 지정
		response.setContentType("text/html;charset=utf-8"); //응답타입 utf-8로 지정
		// TODO Auto-generated method stub
		System.out.println("비밀번호 확인버튼 클릭");
		String url = "/Main01/member/confirm.jsp";
		String pwd = request.getParameter("pwd");
		String eno = request.getParameter("eno");
		System.out.println(eno+"   "+pwd);
		MemberDAO memberDAO = MemberDAO.getInstance(); //eno와 pwd를 담을 객체생성
		//System.out.println(eno);
		int result = memberDAO.ConfirmID(eno, pwd);
		if(result==1) {
			request.setAttribute("eno", eno);
			url = "/Main01/member/select.jsp";
		}
		else {
			request.setAttribute("result", result);
			url = "/Main01/member/confirm.jsp";
		}
		System.out.println("결과 : "+result);
		System.out.println("다음페이지 : "+url);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
