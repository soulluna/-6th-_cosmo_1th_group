package Board;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	   private Connection con;
	   private PreparedStatement pstmt;
	   private DataSource dataFactory;
	public BoardDAO() {
		super();
		try {
	         Context ctx = new InitialContext();
	         Context envContext = (Context)ctx.lookup("java:/comp/env");
	         dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
	      }catch(Exception e){
	         e.printStackTrace();
	      }
	   }

	}

