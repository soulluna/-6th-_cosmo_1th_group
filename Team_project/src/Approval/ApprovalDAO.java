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

import Main.MemberVO;

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

	// 전체 글 목록 조회
	public List<ApprovalVO> selectAllApproval(MemberVO mVO) {
		List<ApprovalVO> approvalList = new ArrayList<ApprovalVO>();
		try {

			String query = "select txtnum, applist, progress, txtname, entrydate from approval where eno=? or MIDSUGESTENO=? or FINSUGESTENO=?";
			// or MIDSUGESTENO=?

			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mVO.getEno());
			pstmt.setString(2, mVO.getEno());
			pstmt.setString(3, mVO.getEno());

			ResultSet rs = pstmt.executeQuery();

			// 첫 번째 목록부터
			while (rs.next()) {
				// 값을 가져옴
				int txtnum = rs.getInt("txtnum");
				String applist = rs.getString("applist");
				String progress = rs.getString("progress");
				String txtname = rs.getString("txtname");
				Date entrydate = rs.getDate("entrydate");

				// 값을 넣어 객체 생성
				ApprovalVO approvalVO = new ApprovalVO(txtnum, applist, progress, txtname, entrydate);

				// 생성한 객체를 하나씩 추가
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

	// 검색 목록 가져오기
	public List<ApprovalVO> selectAllApproval(String searchType, String searchKey) {
		List<ApprovalVO> approvalList = new ArrayList<ApprovalVO>();
		String query = null;
		try {
			con = dataFactory.getConnection();
			System.out.println(searchType);
			System.out.println(searchKey);
			if (searchType.equals("1")) {
				query = "select txtnum, applist, progress, txtname, entrydate from approval where ename='안영우' and applist like ?";
			} else if (searchType.equals("2")) {
				query = "select txtnum, applist, progress, txtname, entrydate from approval where ename='안영우' and txtname like ?";
			}
			System.out.println(query);

			pstmt = con.prepareStatement(query);
			System.out.println("pstmt : " + pstmt);

			pstmt.setString(1, "%" + searchKey + "%");
			ResultSet rs = pstmt.executeQuery();
			System.out.println("rs : " + rs);

			// 첫 번째 목록부터
			while (rs.next()) {
				// 값을 가져옴
				int txtnum = rs.getInt("txtnum");
				String applist = rs.getString("applist");
				String progress = rs.getString("progress");
				String txtname = rs.getString("txtname");
				Date entrydate = rs.getDate("entrydate");

				// 값을 넣어 객체 생성
				ApprovalVO approvalVO = new ApprovalVO(txtnum, applist, progress, txtname, entrydate);

				// 생성한 객체를 하나씩 추가
				approvalList.add(approvalVO);

			}

			// 메모리 누수 방지 위해서
			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return approvalList;
	}

	// 기안서 상세 보기
	public ApprovalVO selectDraft(int txtnum) {
		ApprovalVO approval = new ApprovalVO();
		System.out.println("selectDraft");
		try {
			String query = "select applist, txtnum, txtname, txtcont, progress, ";
						query += "entrydate, middate, findate, eno, ename, rank, Midsugesteno, Finsugesteno,";
						query += "(select Employee.dname from employee where employee.eno=Approval.eno) as dname ";
						query += "from approval where txtnum=?";
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, txtnum);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			approval.setTxtname(rs.getString("txtname"));
			approval.setTxtcont(rs.getString("txtcont"));
			approval.setProgress(rs.getString("progress"));
			approval.setEntrydate(rs.getDate("entrydate"));
			approval.setMiddate(rs.getDate("middate"));
			approval.setFindate(rs.getDate("findate"));
			approval.setEno(rs.getString("eno"));
			approval.setEname(rs.getString("ename"));
			approval.setRank(rs.getString("rank"));
			approval.setDname(rs.getString("dname"));
			approval.setMideno(rs.getString("Midsugesteno"));
			approval.setFineno(rs.getString("Finsugesteno"));

			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return approval;
	}

	// 휴가신청서 상세 보기
	public ApprovalVO selectVacation(int txtnum) {
		ApprovalVO approval = new ApprovalVO();
		System.out.println("selectVacation");
		try {
			con = dataFactory.getConnection();
			String query = "select entrydate, middate, findate, eno, ";
			query += "mideno, fineno, txtname, txtcont, vaclist, vacstart, vacend ";
			query += "from approval where txtnum=?";

//			String query2 = "select ename, rank, dname from employee where eno=?";

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, txtnum);

			System.out.println(txtnum);
			System.out.println(query);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			Date entrydate = rs.getDate("entrydate");
			Date middate = rs.getDate("middate");
			Date findate = rs.getDate("findate");
			String eno = rs.getString("eno");
			String mideno = rs.getString("midsugesteno");
			String fineno = rs.getString("finsugesteno");
			String txtname = rs.getString("txtname");
			String txtcont = rs.getString("txtcont");
			String vaclist = rs.getString("vaclist");
			Date vacstart = rs.getDate("vacstart");
			Date vacend = rs.getDate("vacend");

			System.out.println(entrydate);
			System.out.println(middate);
			System.out.println(findate);
			System.out.println(eno);
			System.out.println(mideno);
			System.out.println(fineno);
			System.out.println(txtname);
			System.out.println(txtcont);
			System.out.println(vaclist);
			System.out.println(vacstart);
			System.out.println(vacend);

			approval.setEntrydate(entrydate);
			approval.setMiddate(middate);
			approval.setFindate(findate);
			approval.setEno(eno);
			approval.setMideno(mideno);
			approval.setFineno(fineno);
			approval.setTxtname(txtname);
			approval.setTxtcont(txtcont);
			approval.setVaclist(vaclist);
			approval.setVacstart(vacstart);
			approval.setVacend(vacend);

			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return approval;
	}
	
	//기안서 작성 화면에서 중간 결재자 정보
	public MemberVO midApprovalGet(MemberVO mVO) {
		// TODO Auto-generated method stub
		MemberVO midApproval = new MemberVO();
		System.out.println("midApprovalGet");
		System.out.println(mVO.getDname());
		System.out.println("midApprovalGet");

		String query = "select rank, ename from Employee where dname=? and rank=?";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mVO.getDname());
			if (mVO.getRank().equals("사원")) {
				pstmt.setString(2, "과장");
			} else if (mVO.getRank().equals("대리")) {
				pstmt.setString(2, "과장");
			} else if (mVO.getRank().equals("과장")) {
				pstmt.setString(2, "차장");
			} else if (mVO.getRank().equals("팀장")) {
				pstmt.setString(2, "부장");
			}
			ResultSet rs = pstmt.executeQuery();
			rs.next();

			midApproval.setRank(rs.getString("rank"));
			midApproval.setEname(rs.getString("ename"));

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return midApproval;
	}

	//기안서 작성 화면에서 마지막 결재자 정보
	public MemberVO finApprovalGet(MemberVO mVO) {
		// TODO Auto-generated method stub
		MemberVO finApproval = new MemberVO();

		String query = "select rank, ename from Employee where dname=? and rank=?";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mVO.getDname());
			if (mVO.getRank().equals("사원")) {
				pstmt.setString(2, "부장");
			} else if (mVO.getRank().equals("대리")) {
				pstmt.setString(2, "부장");
			} else if (mVO.getRank().equals("과장")) {
				pstmt.setString(2, "부장");
			} else if (mVO.getRank().equals("팀장")) {
				pstmt.setString(2, "이사");
			} else if (mVO.getRank().equals("부장")) {
				pstmt.setString(2, "이사");
			} else if (mVO.getRank().equals("이사")) {
				pstmt.setString(2, "이사");
			}
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			System.out.println(rs.getString("rank"));
			System.out.println(rs.getString("ename"));
			finApproval.setRank(rs.getString("rank"));
			finApproval.setEname(rs.getString("ename"));

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finApproval;
	}
	
	
	public String appUserGetEno(String userEname) {
		// TODO Auto-generated method stub
		String midUserEno = null;

		String query = "select eno from Employee where ename=?";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userEname);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			midUserEno = rs.getString("eno");

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return midUserEno;
	}
	
	//기안서 작성하기
	public void draftInset(ApprovalVO aVO, MemberVO mVO) {
		// TODO Auto-generated method stub
		System.out.println("DAO draftInset");
		String query = "insert into Approval (APPLIST, TXTNUM, TXTNAME, TXTCONT, PROGRESS, ENO, ENAME, RANK, MIDSUGESTENO, FINSUGESTENO)";
		query += "values ('기안서', approval_seq.nextval, ?, ?, '대기', ?, ?, ?, ?, ?)";
		try {
			con = dataFactory.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, aVO.getTxtname());
			pstmt.setString(2, aVO.getTxtcont());
			pstmt.setString(3, mVO.getEno());
			pstmt.setString(4, mVO.getEname());
			pstmt.setString(5, mVO.getRank());
			pstmt.setString(6, aVO.getMideno());
			pstmt.setString(7, aVO.getFineno());
			
			pstmt.executeUpdate();

			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	//작성된 문서의 중간 결재 유저 정보 뽑아오기
	public MemberVO draftedmidUser(ApprovalVO approvalVO) {
		// TODO Auto-generated method stub
		System.out.println("DAO의 draftedmidUser");
		MemberVO createdMid = new MemberVO();
		
		String query = "select * from Employee where eno=?";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			System.out.println("mid eno : "+approvalVO.getMideno());
			pstmt.setString(1, approvalVO.getMideno());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			createdMid.setRank(rs.getString("rank"));
			createdMid.setEname(rs.getString("ename"));
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return createdMid;
	}
	
	//작성된 문서의 마지막 결재 유저 정보 뽑아오기
	public MemberVO draftedfinUser(ApprovalVO approvalVO) {
		// TODO Auto-generated method stub
		MemberVO createdFin = new MemberVO();

		String query = "select * from Employee where eno=?";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, approvalVO.getFineno());
			ResultSet rs = pstmt.executeQuery();
			rs.next();

			createdFin.setRank(rs.getString("rank"));
			createdFin.setEname(rs.getString("ename"));
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return createdFin;
	}

	public void modifydraft(ApprovalVO aVO, int txtnum) {
		// TODO Auto-generated method stub

		String query = "update Approval set txtname=?, txtcont=? where txtnum=?";

		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			System.out.println(aVO.getTxtname());
			System.out.println(aVO.getTxtcont());
			System.out.println(txtnum);
			pstmt.setString(1, aVO.getTxtname());
			pstmt.setString(2, aVO.getTxtcont());
			pstmt.setInt(3, txtnum);
			System.out.println("executeUpdate");
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteDraft(int txtnum) {
		// TODO Auto-generated method stub
		String query = "delete Approval where txtnum=?";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, txtnum);
			System.out.println("executeUpdate");
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
