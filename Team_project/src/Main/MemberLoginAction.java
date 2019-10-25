package Main;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLoginAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(); //회원인증 성공시 아이디를 세션에 등록할 세션 객체 생성
		MemberDAO memberdao = new MemberDAO();
		MemberVO member = new MemberVO();
		
		int result = -1; // 기본 결과 값을 -1로 지정한다.(아이디가 존재하지 않는 경우)
		
		//로그인 폼에서 입력한 값을 memberVO객체에 저장한다.
		member.setEno(request.getParameter("eno"));
		member.setPwd(request.getParameter("pwd"));
		result = memberdao.isMember(member); // dao에 ismember메소드 호출하여 회원 인증
		
		//로그인 실패의 경우
		if(result==0) {//비밀번호가 틀릴 때
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.)");
			out.println("location.href='./MemberLogin.do';");
			out.println("</script>");
			out.close();
			return null;
		}else if(result==-1) { //아이디가 없을 때
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디가 존재하지 않습니다.)");
			out.println("location.href='./MemberLogin.do';");
			out.println("</script>");
			out.close();
			return null;
		}
		
		//로그인 성공의 경우
		session.setAttribute("eno", member.getEno()); //세션에 사번등록
		forward.setRedirect(true); //접속 끊었다가 다시 연결하면서 새로운 정보를 보여준다.
		forward.setPath("./IndexMain.do");
		return forward;
	}
}
