package Main;

import java.sql.Date;

public class regVO {
	private String eno;
	private String pwd;
	private String ename;
	private String eng_name;
	private String email;
	private String tel;
	private String dname;
	private String dname_two;
	private Date hireDate;
	private String rank;
	private String isadmin;
	
	public regVO(String eno, String pwd, String ename, String eng_name, String email, String tel, String dname,
			String dname_two, Date hireDate, String rank, String isadmin) {
		super();
		this.eno = eno;
		this.pwd = pwd;
		this.ename = ename;
		this.eng_name = eng_name;
		this.email = email;
		this.tel = tel;
		this.dname = dname;
		this.dname_two = dname_two;
		this.hireDate = hireDate;
		this.rank = rank;
		this.isadmin = isadmin;
	}

	public regVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEno() {
		return eno;
	}

	public void setEno(String eno) {
		this.eno = eno;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEng_name() {
		return eng_name;
	}

	public void setEng_name(String eng_name) {
		this.eng_name = eng_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDname_two() {
		return dname_two;
	}

	public void setDname_two(String dname_two) {
		this.dname_two = dname_two;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}
	
	
	


	
	
}
