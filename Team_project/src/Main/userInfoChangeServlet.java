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
 * Servlet implementation class userInfoChangeServlet
 */
@WebServlet("/userInfoChange.do")
public class userInfoChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userInfoChangeServlet() {
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
		System.out.println("내정보수정 버튼 클릭");
		String eno=request.getParameter("eno");
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberVO memberVO = new MemberVO();
		memberVO = memberDAO.getMember(eno);
		request.setAttribute("memberVO", memberVO);
		String url="Main01/member/change.jsp";
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
		// TODO Auto-generated method stub
		System.out.println("정보수정완료 버튼 클릭");
		String url="Main01/member/change.jsp";
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberVO memberVO = new MemberVO();
		String eno=request.getParameter("eno");
		String ename=request.getParameter("ename");
		String eng_name=request.getParameter("eng_name");
		String tel=request.getParameter("tel");
		String email=request.getParameter("email");
		memberVO.setEno(eno);
		memberVO.setEname(ename);
		memberVO.setEng_name(eng_name);
		memberVO.setTel(tel);
		memberVO.setEmail(email);
		int result=memberDAO.UpdateUserInfo(memberVO);
		if(result==1) {
			url="Main01/member/select.jsp";
		}
		else {
			request.setAttribute("result", result);
			url = "Main01/member/change.jsp";
		}
		System.out.println("결과 : "+result);
		System.out.println("다음 페이지 : "+url);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
