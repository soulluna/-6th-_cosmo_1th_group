package Approval;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Board.Boardservice;
import Main.MemberVO;

@WebServlet("/Approval/*")
public class ApprovalController extends HttpServlet {

	ApprovalVO approvalVO;
	ApprovalService approvalService;

	public void init(ServletConfig config) throws ServletException {
		approvalService = new ApprovalService();
		approvalVO = new ApprovalVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberVO mVO = (MemberVO) session.getAttribute("loginUser");

		String nextPage = null; // 다음 페이지를 담을 변수 선언
		request.setCharacterEncoding("utf-8"); // request 반응 타입 명시함
		response.setContentType("text/html;utf-8"); // response 데이터 종류 명시함

		String action = request.getPathInfo(); // 맵핑 이름 뒤 경로를 받아옴

		try {
			List<ApprovalVO> approvalList = new ArrayList<ApprovalVO>();

			if (mVO == null) {
				System.out.println("로그인 페이지로 ㄱㄱ");
				nextPage = "/index.jsp";
			} else {
				if (action != null && action.equals("/docList.do")) {
					/*
					 * System.out.println("-----페이징 테스트------"); String _section =
					 * request.getParameter("section"); String _pageNum =
					 * request.getParameter("pageNum"); int section = Integer.parseInt((_section ==
					 * null) ? "1" : _section); int pageNum = Integer.parseInt((_pageNum == null) ?
					 * "1" : _pageNum); Map pagingMap = new HashMap(); pagingMap.put("section",
					 * section); pagingMap.put("pageNum", pageNum); Map articlesMap =
					 * approvalService.listArticles(pagingMap); articlesMap.put("section", section);
					 * articlesMap.put("pageNum", pageNum); request.setAttribute("articlesMap",
					 * articlesMap); nextPage = "/Approval01/docList2.jsp";
					 * System.out.println("-----//페이징 테스트------");
					 */

					System.out.println("/docList.do");
					System.out.println("action : " + action);
					String searchType = request.getParameter("searchType");
					System.out.println("searchType : " + searchType);
					String searchKey = request.getParameter("searchKey");
					System.out.println("searchKey : " + searchKey);
					if (searchKey == null || searchKey.equals("")) {
						System.out.println("searchKey가 null인 if문");
						approvalList = approvalService.listApproval(mVO);
					} else {
						approvalList = approvalService.listApproval(mVO, searchType, searchKey);
					}
					request.setAttribute("approvalList", approvalList);
					nextPage = "/Approval01/docList.jsp";

				} else if (action.equals("/vacationWait.do")) { // 휴가신청서 상세보기
					System.out.println();
					System.out.println("vacationWait.do");
					System.out.println("action : " + action);
					int txtnum = Integer.parseInt(request.getParameter("txtnum"));
					System.out.println(txtnum);
					approvalVO = approvalService.viewvacation(txtnum);
					MemberVO createdMidUser = approvalService.middraftInfo(approvalVO);
					MemberVO createdFinUser = approvalService.findraftInfo(approvalVO);
					request.setAttribute("createdMidUser", createdMidUser);
					request.setAttribute("createdFinUser", createdFinUser);
					request.setAttribute("txtnum", txtnum);
					request.setAttribute("approvalVO", approvalVO);

					nextPage = "/Approval01/vacationWait.jsp";

				} else if (action.equals("/draftWait.do")) { // 기안서 상세보기
					System.out.println();
					System.out.println("draftWait.do");
					System.out.println("action : " + action);
					int txtnum = Integer.parseInt(request.getParameter("txtnum"));

					System.out.println(txtnum);

					approvalVO = approvalService.viewdraft(txtnum);
					System.out.println("컨트롤러 MIDENO: " + approvalVO.getMideno());

					MemberVO createdMidUser = approvalService.middraftInfo(approvalVO);
					MemberVO createdFinUser = approvalService.findraftInfo(approvalVO);
					request.setAttribute("createdMidUser", createdMidUser);
					request.setAttribute("createdFinUser", createdFinUser);
					request.setAttribute("txtnum", txtnum);
					request.setAttribute("approvalVO", approvalVO);

					nextPage = "/Approval01/draftWait.jsp";

				} else if (action.equals("/vacation.do")) {
					MemberVO createdMidUser = approvalService.midApprovalInfo(mVO);
					MemberVO createdFinUser = approvalService.finApprovalInfo(mVO);
					request.setAttribute("createdMidUser", createdMidUser);
					request.setAttribute("createdFinUser", createdFinUser);
					nextPage = "/Approval01/vacation.jsp";
				} else if (action.equals("/draft.do")) { // 기안서 작성 페이지

					MemberVO createdMidUser = approvalService.midApprovalInfo(mVO);
					MemberVO createdFinUser = approvalService.finApprovalInfo(mVO);
					request.setAttribute("createdMidUser", createdMidUser);
					request.setAttribute("createdFinUser", createdFinUser);
					nextPage = "/Approval01/draft.jsp";

				} else if (action.equals("/drafted.do")) { // 기안서 페이지 등록 완료 시
					System.out.println("기안서 등록 클릭");
					String midUser = request.getParameter("midUser");
					String finUser = request.getParameter("finUser");
					System.out.println(midUser);
					System.out.println(finUser);

					ApprovalVO aVO = new ApprovalVO();
					String midUserEno = approvalService.approvalUser(midUser);
					String finUserEno = approvalService.approvalUser(finUser);
					aVO.setMideno(midUserEno);
					aVO.setFineno(finUserEno);
					aVO.setTxtname(request.getParameter("title"));
					aVO.setTxtcont(request.getParameter("reason"));
					approvalService.draftAdd(aVO, mVO);
					nextPage = "/Approval/docList.do";

				} else if (action.equals("/vacationed.do")) { // 휴가신청서 페이지 등록 완료 시
					System.out.println("휴가신청서 등록 클릭");
					String midUser = request.getParameter("midUser");
					String finUser = request.getParameter("finUser");
					System.out.println(midUser);
					System.out.println(finUser);

					ApprovalVO aVO = new ApprovalVO();
					String midUserEno = approvalService.approvalUser(midUser);
					String finUserEno = approvalService.approvalUser(finUser);
					aVO.setMideno(midUserEno);
					aVO.setFineno(finUserEno);
					aVO.setTxtname(request.getParameter("title"));
					aVO.setTxtcont(request.getParameter("reason"));
					aVO.setVaclist(request.getParameter("leaveradio"));

					String datepicker1 = request.getParameter("datepicker1");
					String datepicker2 = request.getParameter("datepicker2");

					java.sql.Date datepicker1ToDate = java.sql.Date.valueOf(datepicker1);
					java.sql.Date datepicker2ToDate = java.sql.Date.valueOf(datepicker2);
					System.out.println("----");
					System.out.println(datepicker1ToDate);
					System.out.println("----");

					aVO.setVacstart(datepicker1ToDate);
					aVO.setVacend(datepicker2ToDate);

					approvalService.vacationAdd(aVO, mVO);

					nextPage = "/Approval/docList.do";

				} else if (action.equals("/draftModify.do")) { // 기안서 수정 페이지
					System.out.println("/draftModify.do");
					int txtnum = Integer.parseInt(request.getParameter("txtnum"));
					System.out.println("txtnum : " + txtnum);
					MemberVO createdMidUser = approvalService.midApprovalInfo(mVO);
					MemberVO createdFinUser = approvalService.finApprovalInfo(mVO);
					request.setAttribute("createdMidUser", createdMidUser);
					request.setAttribute("createdFinUser", createdFinUser);
					approvalVO = approvalService.viewdraft(txtnum);
					request.setAttribute("txtnum", txtnum);
					request.setAttribute("approvalVO", approvalVO);
					nextPage = "/Approval01/draftModify.jsp";

				} else if (action.equals("/vacationModify.do")) { // 휴가신청서 수정 페이지
					int txtnum = Integer.parseInt(request.getParameter("txtnum"));
					System.out.println("txtnum : " + txtnum);
					MemberVO createdMidUser = approvalService.midApprovalInfo(mVO);
					MemberVO createdFinUser = approvalService.finApprovalInfo(mVO);
					request.setAttribute("createdMidUser", createdMidUser);
					request.setAttribute("createdFinUser", createdFinUser);
					approvalVO = approvalService.viewvacation(txtnum);
					request.setAttribute("txtnum", txtnum);
					request.setAttribute("approvalVO", approvalVO);

					nextPage = "/Approval01/vacationModify.jsp";

				} else if (action.equals("/modified.do")) { // 기안서 수정 화면에서 등록 버튼 클릭
					System.out.println("/modified.do");
					ApprovalVO aVO = new ApprovalVO();
					int txtnum = Integer.parseInt(request.getParameter("txtnum"));
					aVO.setTxtname(request.getParameter("title"));
					aVO.setTxtcont(request.getParameter("reason"));
					System.out.println("----------");
					System.out.println(aVO.getTxtname());
					System.out.println(aVO.getTxtcont());
					System.out.println(txtnum);
					System.out.println("----------");
					approvalService.draftmodify(aVO, txtnum);
					nextPage = "/Approval/docList.do";

				} else if (action.equals("/vacmodified.do")) { // 휴가신청서 수정 화면에서 등록 버튼 클릭
					System.out.println("vacmodified.do");
					ApprovalVO aVO = new ApprovalVO();
					int txtnum = Integer.parseInt(request.getParameter("txtnum"));
					aVO.setVaclist(request.getParameter("leaveradio"));
					aVO.setTxtname(request.getParameter("title"));
					aVO.setTxtcont(request.getParameter("reason"));

					String datepicker1 = request.getParameter("datepicker1");
					String datepicker2 = request.getParameter("datepicker2");
					java.sql.Date datepicker1ToDate = java.sql.Date.valueOf(datepicker1);
					java.sql.Date datepicker2ToDate = java.sql.Date.valueOf(datepicker2);
					System.out.println("----");
					System.out.println(datepicker1ToDate);
					System.out.println("----");
					aVO.setVacstart(datepicker1ToDate);
					aVO.setVacend(datepicker2ToDate);

					System.out.println("----------");
					System.out.println(aVO.getTxtname());
					System.out.println(aVO.getTxtcont());
					System.out.println(txtnum);
					System.out.println("----------");
					approvalService.vacationmodify(aVO, txtnum);

					nextPage = "/Approval/docList.do";
				} else if (action.equals("/draftdelete.do")) { // 문서 삭제
					System.out.println("draftdelete.do");
					int txtnum = Integer.parseInt(request.getParameter("txtnum"));
					System.out.println(txtnum);
					approvalService.draftDelete(txtnum);
					nextPage = "/Approval/docList.do";

				} else if (action.equals("/midapproveddraft.do")) { // 중간 결재자 결재
					System.out.println("midapproveddraft.do");
					int txtnum = Integer.parseInt(request.getParameter("txtnum"));
					System.out.println(txtnum);
					approvalService.draftmidApprove(txtnum);
					nextPage = "/Approval/docList.do";
				} else if (action.equals("/finapproveddraft.do")) { // 마지막 결재자 결재
					System.out.println("finapproveddraft.do");
					int txtnum = Integer.parseInt(request.getParameter("txtnum"));
					System.out.println(txtnum);
					approvalService.draftfinApprove(txtnum);
					nextPage = "/Approval/docList.do";
				} else if (action.equals("/midreturneddraft.do")) { // 중간 결재자 반려
					System.out.println("midreturneddraft");
					int txtnum = Integer.parseInt(request.getParameter("txtnum"));
					approvalService.draftmidReturn(txtnum);
					nextPage = "/Approval/docList.do";
				} else if (action.equals("/finreturneddraft.do")) { // 마지막 결재자 반려
					System.out.println("finreturneddraft");
					int txtnum = Integer.parseInt(request.getParameter("txtnum"));
					approvalService.draftfinReturn(txtnum);
					nextPage = "/Approval/docList.do";
				} else if (action.equals("/disRecSort.do")) {// 수신발신 정렬
					approvalList = approvalService.listSort1(mVO);
					request.setAttribute("approvalList", approvalList);
					nextPage = "/Approval01/docList.jsp";

				} else if (action.equals("/docStateSort.do")) { // 문서 상태 정렬
					approvalList = approvalService.listSort2(mVO);
					request.setAttribute("approvalList", approvalList);
					nextPage = "/Approval01/docList.jsp";

				} else if (action.equals("/docDaySort.do")) { // 문서 날짜 정렬
					approvalList = approvalService.listSort3(mVO);
					request.setAttribute("approvalList", approvalList);
					nextPage = "/Approval01/docList.jsp";
				}
			}
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
