package Board;

import java.sql.Timestamp;

public class BoardVO {

//	   private String detailName;
//	   private int num;	   
//	   private String title;
//	   private String content;	   
//	   private String id;
//	   private Date writeDate;
//	   private int count;
//	   private int goods;
	   //eno 사번 , rank 직책  comnum comtotal comcont; 
	  
	   private int noticelist;	//부서지정
	   private int txtnum;	//	글번호
	   private String txtname;	//글제목
	   private String txtcont;	//글내용
	   private String ename;	//사원이름
	   private Timestamp entrydate;	//입력일
	   private int viewtotal;	//조회수
	   private int likenum;		//추천수
	   private String isAnnouncement; // 공지여부
	   private String eno; 
	   private String rank;
	   private int comtotal;
	   private String comcont;
	   private String comuser;
	   private int comcount;
	   
	public int getComcount() {
		return comcount;
	}

	public void setComcount(int comcount) {
		this.comcount = comcount;
	}

	public String getComuser() {
		return comuser;
	}

	public void setComuser(String comuser) {
		this.comuser = comuser;
	}

	public BoardVO() {
		// TODO Auto-generated constructor stub
	}
	
	public int getTxtnum() {
		return txtnum;
	}
	public void setTxtnum(int txtnum) {
		this.txtnum = txtnum;
	}
	public String getTxtname() {
		return txtname;
	}
	public void setTxtname(String txtname) {
		this.txtname = txtname;
	}
	public String getTxtcont() {
		return txtcont;
	}
	public void setTxtcont(String txtcont) {
		this.txtcont = txtcont;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Timestamp getEntrydate() {
		return entrydate;
	}
	public void setEntrydate(Timestamp entrydate) {
		this.entrydate = entrydate;
	}
	public int getViewtotal() {
		return viewtotal;
	}
	public void setViewtotal(int viewtotal) {
		this.viewtotal = viewtotal;
	}
	public int getLikenum() {
		return likenum;
	}
	public void setLikenum(int likenum) {
		this.likenum = likenum;
	}
	public String getEno() {
		return eno;
	}
	public void setEno(String string) {
		this.eno = string;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public int getComtotal() {
		return comtotal;
	}
	public void setComtotal(int comtotal) {
		this.comtotal = comtotal;
	}
	public String getComcont() {
		return comcont;
	}
	public void setComcont(String comcont) {
		this.comcont = comcont;
	}
	public int getNoticelist() {
		return noticelist;
	}
	public void setNoticelist(int noticelist) {
		this.noticelist = noticelist;
	}

	public String getIsAnnouncement() {
		return isAnnouncement;
	}

	public void setIsAnnouncement(String isAnnouncement) {
		this.isAnnouncement = isAnnouncement;
	}
	
	   
}