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
//		member.setHireDate(request.getParameter("hiredate"));
		member.setRank(request.getParameter("rank"));
		member.setIsadmin(request.getParameter("isadmin"));
		result = memberdao.joinMember(member); // dao에 joinmember 메소드를 실행해서 회원가입처리
		
		//회원가입 실패시 null 반환
		if(result==false) {
			System.out.println("회원가입 실패");
			return null;
		}
		//회원가입 성공
		forward.setRedirect(true);
		forward.setPath("./MemberLogin.do");
		return forward;
	}

}
