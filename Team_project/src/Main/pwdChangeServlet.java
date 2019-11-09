package Main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class pwdChangeServlet
 */
@WebServlet("/pwdChange.do")
public class pwdChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pwdChangeServlet() {
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
		System.out.println("비밀번호 수정 버튼 클릭");
		String url = "/Main01/member/pwd.jsp";
		System.out.println("다음 페이지 : "+url);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("비밀번호 수정버튼 클릭");
		String url="Main01/member/pwd.jsp";
		String eno=request.getParameter("eno");
		String pwd=request.getParameter("changePwd");
		System.out.println("변경된 비밀번호 : "+pwd);
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberVO memberVO = new MemberVO();
		memberVO.setEno(eno);
		memberVO.setPwd(pwd);
		int result=memberDAO.updatePwd(memberVO);
		if(result==1) {
			url="Main01/member/select.jsp";
			
		}
		else {//결과값을 주어서 잘못됨을 말해줌
			request.setAttribute("result", result);
		}
		System.out.println("결과 : "+result);
		System.out.println("다음 페이지 : "+url);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

}
