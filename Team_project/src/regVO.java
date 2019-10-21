
public class regVO {
	private String eno;
	private String ename;
	private String userpw;
	private String userpwc;
	private String dname1;
	private String dname2;
	
	public regVO() {
		super();
	}
	
	public regVO(String eno, String ename, String userpw, String userpwc, String dname1, String dname2) {
		super();
		this.eno = eno;
		this.ename = ename;
		this.userpw = userpw;
		this.userpwc = userpwc;
		this.dname1 = dname1;
		this.dname2 = dname2;
	}
	public String getEno() {
		return eno;
	}
	public void setEno(String eno) {
		this.eno = eno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUserpwc() {
		return userpwc;
	}
	public void setUserpwc(String userpwc) {
		this.userpwc = userpwc;
	}
	public String getDname1() {
		return dname1;
	}
	public void setDname1(String dname1) {
		this.dname1 = dname1;
	}
	public String getDname2() {
		return dname2;
	}
	public void setDname2(String dname2) {
		this.dname2 = dname2;
	}
	
	
}
