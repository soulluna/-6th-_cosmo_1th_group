package Approval;

import java.util.ArrayList;
import java.util.List;

import Main.MemberVO;

public class ApprovalService {

	ApprovalDAO approvalDAO;
	
	public ApprovalService() {
		approvalDAO = new ApprovalDAO();
	}
	
	//그냥 문서 출력
	public List<ApprovalVO> listApproval(MemberVO mVO) {
		List<ApprovalVO> approvallist = approvalDAO.selectAllApproval(mVO);	
		return approvallist;
	}
	
	//검색어 있을 때 문서 출력
	public List<ApprovalVO> listApproval(String searchType, String searchKey){
		System.out.println("listApproval(매개변수) : " + searchType + ", " + searchKey);
		List<ApprovalVO> approvallist = approvalDAO.selectAllApproval(searchType, searchKey);	
		return approvallist;
	}
	
	
	// 기안서 보기
	public ApprovalVO viewdraft(int txtnum) {
		ApprovalVO approval = approvalDAO.selectDraft(txtnum);
		return approval;
	}
	
	/*
	 * //기안서 정보 public ApprovalVO writerdraft(int txtnum, long eno) { ApprovalVO
	 * approval = approvalDAO.writerInfomation(txtnum, eno); return approval; }
	 */
	
	// 휴가신청서 보기
	public ApprovalVO viewvacation(int txtnum) {
		ApprovalVO approval = approvalDAO.selectVacation(txtnum);
		return approval;
	}
	
	
	//기안서 작성 시 결재 정보
	public MemberVO midApprovalInfo(MemberVO mVO) {
		MemberVO midUser = approvalDAO.midApprovalGet(mVO);
		
		return midUser;
	}
	public MemberVO finApprovalInfo(MemberVO mVO) {
		MemberVO finUser = approvalDAO.finApprovalGet(mVO);
		
		return finUser;
	}
	

	public void draftAdd(ApprovalVO aVO, MemberVO mVO) {
		// TODO Auto-generated method stub
		approvalDAO.draftInset(aVO,mVO);
	}

	public String approvalUser(String userEname) {
		// TODO Auto-generated method stub
		String userEno = approvalDAO.appUserGetEno(userEname);
		return userEno;
	}


}
