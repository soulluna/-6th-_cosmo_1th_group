package Board;

import java.sql.Date;

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
	  
	   private String noticelist;	//부서지정
	   private int txtnum;	//	글번호
	   private String txtname;	//글제목
	   private String txtcont;	//글내용
	   private String ename;	//사원이름
	   private Date entrydate;	//입력일
	   private int viewtotal;	//조회수
	   private int likenum;		//추천수
	public BoardVO(String noticelist, int txtnum, String txtname, String txtcont, String ename, Date entrydate,
			int viewtotal, int likenum) {
		super();
		this.noticelist = noticelist;
		this.txtnum = txtnum;
		this.txtname = txtname;
		this.txtcont = txtcont;
		this.ename = ename;
		this.entrydate = entrydate;
		this.viewtotal = viewtotal;
		this.likenum = likenum;
	}
	public BoardVO() {
		// TODO Auto-generated constructor stub
	}
	public String getNoticelist() {
		return noticelist;
	}
	public void setNoticelist(String noticelist) {
		this.noticelist = noticelist;
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
	public Date getEntrydate() {
		return entrydate;
	}
	public void setEntrydate(Date entrydate) {
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
	   
}