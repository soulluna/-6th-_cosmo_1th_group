package Approval;

import java.util.List;

public class ApprovalService {

	ApprovalDAO approvalDAO;
	
	public ApprovalService() {
		approvalDAO = new ApprovalDAO();
	}
	
	
	public List<ApprovalVO> listApproval() {
		List<ApprovalVO> approvallist = approvalDAO.selectAllApproval();	
		return approvallist;
	}
	
	
	// 기안서 보기
	public ApprovalVO viewdraft(int txtnum) {
		ApprovalVO approval = approvalDAO.selectDraft(txtnum);
		return approval;
	}
	
	// 휴가신청서 보기
	public ApprovalVO viewvacation(int txtnum) {
		ApprovalVO approval = approvalDAO.selectVacation(txtnum);
		return approval;
	}
	
	
}
