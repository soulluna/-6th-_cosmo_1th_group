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
				request.setAttribute("result", 2);
				nextPage = "/index.jsp";
			} else {
				if (action.equals("/docList.do")) {// 문서 정렬
					Map pagingMap = new HashMap();

					System.out.println("action : " + action);
					String serachDocList = request.getParameter("serachDocList");
					String serachDocState = request.getParameter("serachDocState");
					String sendReceive = request.getParameter("sendReceive");
					String datepicker1 = request.getParameter("datepicker1");
					String datepicker2 = request.getParameter("datepicker2");
					String searchType = request.getParameter("searchType");
					String searchKey = request.getParameter("searchKey");
					String _pageNum = request.getParameter("pageNum");
					String _pageSessionNum = request.getParameter("pageSession");
					int pageNum = (Integer.parseInt((_pageNum == null ? "1" : _pageNum)));
					int pageSessionNum = (Integer.parseInt((_pageSessionNum == null ? "1" : _pageSessionNum)));
					int docMaxNum = 0;
					int maxPageNum = 0;
					int maxSessionNum = 0;

					if(serachDocList == null) {
						serachDocList = "";
					}
					if(serachDocState == null) {
						serachDocState  = "";
					}
					if (searchType == null) {
						searchType = "";
					}
					if (searchKey == null) {
						searchKey = "";
					}

					if (sendReceive == null) {
						sendReceive = "수신";
					}
					if(datepicker1 == null) {
						datepicker1 = "";
					}
					if(datepicker2 == null) {
						datepicker2 = "";
					}
					
					System.out.println("------------");
					System.out.println(searchKey);
					System.out.println(searchType);
					System.out.println(serachDocState);
					System.out.println("------------");

					docMaxNum = approvalService.docSearchCount(mVO, searchType, searchKey, sendReceive, serachDocList, serachDocState, datepicker1, datepicker2);

					if (docMaxNum % 15 == 0) {
						maxPageNum = docMaxNum / 15;
					} else {
						maxPageNum = docMaxNum / 15 + 1;
					}
					if (maxPageNum % 5 == 0) {
						maxSessionNum = maxPageNum / 5;
					} else {
						maxSessionNum = maxPageNum / 5 + 1;
					}

					System.out.println("-------------------------");
					System.out.println("pageNum : " + pageNum);
					System.out.println("docMaxNum : " + docMaxNum);
					System.out.println("maxPageNum : " + maxPageNum);
					System.out.println("maxSessionNum : " + maxSessionNum);
					System.out.println("pageSessionNum : " + pageSessionNum);
					System.out.println("-------------------------");

					pagingMap.put("pageNum", pageNum);
					pagingMap.put("docMaxNum", docMaxNum);
					pagingMap.put("maxPageNum", maxPageNum);
					pagingMap.put("maxSessionNum", maxSessionNum);
					pagingMap.put("pageSessionNum", pageSessionNum);

					for (int i = 1; i <= maxPageNum; i++) {
						if (pageNum == i) {
							approvalList = approvalService.listApproval(mVO, searchType, searchKey, sendReceive, serachDocList, serachDocState, datepicker1, datepicker2,
									1 + ((i - 1) * 15), 15 + ((i - 1) * 15));
						}
					}
					request.setAttribute("datepicker1", datepicker1);
					request.setAttribute("datepicker2", datepicker2);
					request.setAttribute("serachDocList", serachDocList);
					request.setAttribute("serachDocState", serachDocState);
					request.setAttribute("sendReceive", sendReceive);
					request.setAttribute("searchType", searchType);
					request.setAttribute("searchKey", searchKey);
					request.setAttribute("pagingMap", pagingMap);
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
					int txtnum = Integer.parseInt(request.getParameter("txtnum"));

					System.out.println(txtnum);

					approvalVO = approvalService.viewdraft(txtnum);

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
					System.out.println(createdMidUser);
					request.setAttribute("createdMidUser", createdMidUser);
					request.setAttribute("createdFinUser", createdFinUser);
					nextPage = "/Approval01/draft.jsp";

				} else if (action.equals("/drafted.do")) { // 기안서 페이지 등록 완료 시
					System.out.println("기안서 등록 클릭");
					String midUser = request.getParameter("midUser");
					String finUser = request.getParameter("finUser");

					ApprovalVO aVO = new ApprovalVO();
					String midUserEno = approvalService.approvalUser(midUser);
					String finUserEno = approvalService.approvalUser(finUser);

					aVO.setMideno(midUserEno);
					aVO.setFineno(finUserEno);
					aVO.setTxtname(request.getParameter("title"));
					aVO.setTxtcont(request.getParameter("reason"));
					int txtnum = approvalService.draftAdd(aVO, mVO);
					nextPage = "/Approval/draftWait.do?txtnum=" + txtnum;

				} else if (action.equals("/vacationed.do")) { // 휴가신청서 페이지 등록 완료 시
					System.out.println("휴가신청서 등록 클릭");
					String midUser = request.getParameter("midUser");
					String finUser = request.getParameter("finUser");

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

					aVO.setVacstart(datepicker1ToDate);
					aVO.setVacend(datepicker2ToDate);

					int txtnum = approvalService.vacationAdd(aVO, mVO);
					nextPage = "/Approval/vacationWait.do?txtnum=" + txtnum;

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
					approvalService.draftmodify(aVO, txtnum);
					nextPage = "/Approval/draftWait.do?txtnum=" + txtnum;

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

					aVO.setVacstart(datepicker1ToDate);
					aVO.setVacend(datepicker2ToDate);

					approvalService.vacationmodify(aVO, txtnum);

					nextPage = "/Approval/vacationWait.do?txtnum=" + txtnum;
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

				}

			}
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

}
