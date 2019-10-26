package Approval;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ApprovalDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public ApprovalDAO() {
		try {
			// 커넥션풀 사용
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			// DNDI에 접근하기 위한 기본 경로 "java:/comp/env" 지정

			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			// 톰캣 context.xml에 설정한 name 값인 jdbc/oracle을 이용해 톰캣이 미리 연결한 DataSource 받아옴.

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<ApprovalVO> selectAllApproval() {
		List<ApprovalVO> approvalList = new ArrayList<ApprovalVO>();
		try {
			con = dataFactory.getConnection();

			/*
			 * String query =
			 * "select '수신' as call, (case when APPLIST=1 then '휴가신청서' when APPLIST=2 then '기안서' end) as APPLIST, "
			 * ; query +=
			 * "(case when progress=1 then '대기' when progress=2 then '진행' when progress=3 then '완료' end) as progress, "
			 * ; query += "TXTNAME, ENTRYDATE from approval where ename='안영우'";
			 */

			String query = "select * from approval";
			System.out.println(query);

			pstmt = con.prepareStatement(query);
			System.out.println("pstmt : " + pstmt);

			ResultSet rs = pstmt.executeQuery(query);
			System.out.println("rs : " + rs);

			while (rs.next()) {
				System.out.println("while진입");

				String txtname = rs.getString("txtname");
				System.out.println(txtname);
				
				Date entrydate = rs.getDate("entrydate");
				System.out.println(entrydate);
				

				String applist = rs.getString("applist");
				System.out.println(applist);
				
				String progress = rs.getString("progress");
				System.out.println(progress);
				
				String call = rs.getString("call");
				System.out.println(call);
				
				
				

				ApprovalVO approvalVO = new ApprovalVO(call, applist, progress, txtname, entrydate);
				approvalList.add(approvalVO);
			}

			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return approvalList;

	}

}
