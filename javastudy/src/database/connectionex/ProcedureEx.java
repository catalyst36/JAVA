package database.connectionex;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ProcedureEx {

	public static void main(String[] args) {
		
		String driver = "oracle.jdbc.driver.OracleDriver";		//jdbc 고정 문자열 (암기)
		String url = "jdbc:oracle:thin:@localhost:1521:xe";  		//localhost부분은 서버로 사용할 컴퓨터 아이피 , xe 는 사용하고있는 db의 sid
		String user = "ddit";			//db 접속 아이디
		String password = "java";		//db 접속 비밀번호
		
		StringBuilder sb = new StringBuilder();
		sb.append("{call proc_insert_lprod(?,?)}");
		
		try(Connection conn = DriverManager.getConnection(url,user,password);	
			Statement stmt = conn.createStatement();		
			CallableStatement cstmt = conn.prepareCall(sb.toString());
			){
			
			cstmt.setString(1,"P510");
			cstmt.setString(2,"임산가공식품");
			
			int res = cstmt.executeUpdate();
			if(res == 0)
				System.out.println("insert 실패");
			else 
				System.out.println("insert 성공");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}