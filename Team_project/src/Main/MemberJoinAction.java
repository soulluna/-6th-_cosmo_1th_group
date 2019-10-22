package Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberJoinAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("utf-8"); //요청을 한글처리
		ActionForward forward = new ActionForward();
		MemberDAO memberdao = new MemberDAO();
		MemberVO member = new MemberVO();
		boolean result = false;
		
		//입력정보를 memberVO에 저장
		member.setEno(request.getParameter("eno"));
		member.setPwd(request.getParameter("pwd"));
		member.setEname(request.getParameter("ename"));
		member.setEng_name(request.getParameter("eng_name"));
		member.setEmail(request.getParameter("email"));
		member.setTel(request.getParameter("tel"));
		member.setDname(request.getParameter("dname"));
		member.setDname_two(request.getParameter("dname_two"));
		member.setHireDate(request.getParameter("hiredate"));
		
		
	}

}
