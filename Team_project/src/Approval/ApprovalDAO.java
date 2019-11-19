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
	public List<ApprovalVO> selectAllApproval(String eno) {
		List<ApprovalVO> approvalList = new ArrayList<ApprovalVO>();
		try {
			con = dataFactory.getConnection();

			String query = "select txtnum, applist, progress, txtname, entrydate from approval where eno=?";
			
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, eno);
			ResultSet rs = pstmt.executeQuery();
			
			//첫 번째 목록부터
			while (rs.next()) {
				//값을 가져옴
				int txtnum = rs.getInt("txtnum");
				String applist = rs.getString("applist");
				String progress = rs.getString("progress");
				String txtname = rs.getString("txtname");
				Date entrydate = rs.getDate("entrydate");
				
				//값을 넣어 객체 생성
				ApprovalVO approvalVO = new ApprovalVO(txtnum, applist, progress, txtname, entrydate);
				
				//생성한 객체를 하나씩 추가
				approvalList.add(approvalVO);
				
			}

			//메모리 누수 방지 위해서
			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return approvalList;
	}

	//검색 목록 가져오기
	public List<ApprovalVO> selectAllApproval(String searchType, String searchKey) {
		List<ApprovalVO> approvalList = new ArrayList<ApprovalVO>();
		String query = null;
		try {
			con = dataFactory.getConnection();
			System.out.println(searchType);
			System.out.println(searchKey);
			if(searchType.equals("1")) {
				query = "select txtnum, txtcall, applist, progress, txtname, entrydate from approval where ename='안영우' and applist like ?";
			}else if(searchType.equals("2")) {
				query = "select txtnum, txtcall, applist, progress, txtname, entrydate from approval where ename='안영우' and txtname like ?";
			}
			System.out.println(query);

			pstmt = con.prepareStatement(query);
			System.out.println("pstmt : " + pstmt);
			
			pstmt.setString(1, "%"+searchKey+"%");
			ResultSet rs = pstmt.executeQuery();
			System.out.println("rs : " + rs);
			
			//첫 번째 목록부터
			while (rs.next()) {
				//값을 가져옴
				int txtnum = rs.getInt("txtnum");
				String txtcall = rs.getString("txtcall");
				String applist = rs.getString("applist");
				String progress = rs.getString("progress");
				String txtname = rs.getString("txtname");
				Date entrydate = rs.getDate("entrydate");
				
				//값을 넣어 객체 생성
				ApprovalVO approvalVO = new ApprovalVO(txtnum, txtcall, applist, progress, txtname, entrydate);
				
				//생성한 객체를 하나씩 추가
				approvalList.add(approvalVO);
				
			}

			//메모리 누수 방지 위해서
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
			con = dataFactory.getConnection();
			String query = "select entrydate, middate, findate, eno, ";
					query += "mideno, fineno, txtname, txtcont from approval where txtnum=?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, txtnum);
			System.out.println(txtnum);
			System.out.println(query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Date entrydate = rs.getDate("entrydate");		
			Date middate = rs.getDate("middate");
			Date findate = rs.getDate("findate");
			long eno = rs.getLong("eno");
			long mideno = rs.getLong("mideno");
			long fineno = rs.getLong("fineno");
			String txtname = rs.getString("txtname");
			String txtcont = rs.getString("txtcont");
			
			System.out.println(entrydate);
			System.out.println(middate);
			System.out.println(findate);
			System.out.println(eno);
			System.out.println(mideno);
			System.out.println(fineno);
			System.out.println(txtname);
			System.out.println(txtcont);
			
			approval.setEntrydate(entrydate);
			approval.setMiddate(middate);
			approval.setFindate(findate);
			approval.setEno(eno);
			approval.setMideno(mideno);
			approval.setFineno(fineno);
			approval.setTxtname(txtname);
			approval.setTxtcont(txtcont);
			
			rs.close();
			pstmt.close();
			con.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return approval;
	}

	/*
	 * //작성자 정보 public ApprovalVO writerInfomation(int txtnum, long eno) {
	 * System.out.println("writerInfomation"); ApprovalVO approval = new
	 * ApprovalVO(); try { con = dataFactory.getConnection(); String query =
	 * "select e.rank, e.ename, e.dname from employee e, approval a where a.txtnum=? and e.eno=?"
	 * ; pstmt = con.prepareStatement(query); pstmt.setInt(1, txtnum);
	 * pstmt.setLong(2, eno); ResultSet rs = pstmt.executeQuery(); rs.next(); String
	 * ename = rs.getString("e.rank"); String rank = rs.getString("e.ename"); String
	 * dname = rs.getString("e.dname");
	 * 
	 * System.out.println(ename); System.out.println(rank);
	 * System.out.println(dname);
	 * 
	 * approval.setEname(ename); approval.setRank(rank); approval.setDname(dname);
	 * 
	 * }catch (Exception e) { e.printStackTrace(); } return approval; }
	 */
	
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
			Long eno = rs.getLong("eno");
			Long mideno = rs.getLong("mideno");
			Long fineno = rs.getLong("fineno");
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
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return approval;
	}

	public MemberVO midApprovalGet(String Dname) {
		// TODO Auto-generated method stub
		MemberVO midApproval = new MemberVO(); 
		System.out.println("midApprovalGet");
		System.out.println(Dname);
		System.out.println("midApprovalGet");
		
		String query = "select rank, ename from Employee where dname=? and rank='과장'";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Dname);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			System.out.println(rs.getString("rank"));
			System.out.println(rs.getString("ename"));
			midApproval.setRank(rs.getString("rank"));
			midApproval.setEname(rs.getString("ename"));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return midApproval;
	}
	
	public MemberVO finApprovalGet(String Dname) {
		// TODO Auto-generated method stub
		MemberVO finApproval = new MemberVO(); 
		System.out.println("midApprovalGet");
		System.out.println(Dname);
		System.out.println("midApprovalGet");
		
		String query = "select rank, ename from Employee where dname=? and rank='부장'";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Dname);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			System.out.println(rs.getString("rank"));
			System.out.println(rs.getString("ename"));
			finApproval.setRank(rs.getString("rank"));
			finApproval.setEname(rs.getString("ename"));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return finApproval;
	}

	

	public long appUserGetEno(String userEname) {
		// TODO Auto-generated method stub
		 long midUserEno = 0;
	
		String query = "select eno from Employee where ename=?";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userEname);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			midUserEno = rs.getLong("eno");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return midUserEno;
	}

	public void draftInset(ApprovalVO aVO, MemberVO mVO) {
		// TODO Auto-generated method stub
		System.out.println("--DAO의 draftInset--");
		System.out.println(aVO.getTxtname());
		System.out.println(aVO.getTxtname());
		System.out.println(mVO.getEno());
		System.out.println(mVO.getEname());
		System.out.println(mVO.getRank());
		
		System.out.println(aVO.getMideno());
		System.out.println(aVO.getFineno());
		System.out.println("--DAO의 draftInset--");
		
		String query = "insert into Approval (APPLIST, TXTNUM, TXTNAME, TXTCONT, ENO, ENAME, RANK, MIDSUGESTENO, FINSUGESTENO)";
		query += "values ('기안서', approval_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
		try {
			con = dataFactory.getConnection();

		pstmt = con.prepareStatement(query);
		pstmt.setString(1, aVO.getTxtname());
		pstmt.setString(2, aVO.getTxtname());
		pstmt.setLong(3, Long.parseLong(mVO.getEno()));
		pstmt.setString(4, mVO.getEname());
		pstmt.setString(5, mVO.getRank());
		pstmt.setLong(6, aVO.getMideno());
		pstmt.setLong(7, aVO.getFineno());
		
		pstmt.executeUpdate();		
		pstmt.close();
		con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
