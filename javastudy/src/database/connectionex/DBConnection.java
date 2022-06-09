package database.connectionex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	public static void main(String[] args) {
		
		String driver = "oracle.jdbc.driver.OracleDriver";		//jdbc 고정 문자열 (암기)
		String url = "jdbc:oracle:thin:@localhost:1521:xe";  		//localhost부분은 서버로 사용할 컴퓨터 아이피 , xe 는 사용하고있는 db의 sid
		String user = "ddit";			//db 접속 아이디
		String password = "java";		//db 접속 비밀번호
		
		Connection conn = null;			//db 연결
		ResultSet rs = null;			//sql실행 후 select 결과를 저장하는 객체
		Statement stmt = null;			//connect를 이용해 sql명령을 실행하는 객체 (정적 명령)
		PreparedStatement pstmt = null;	//connect를 이용해 sql명령을 실행하는 객체 (동적 명령)
		
		
		try {	//db접속 예외 처리를 위해 try-catch사용
			Class.forName(driver);		//드라이버를 메모리에 로딩
			conn = DriverManager.getConnection(url,user,password);		//오라클 연결( Connection 연결) url, userid, passwd 사용
			
			String sql = "select mem_id, mem_name, mem_mileage from member where substr(mem_regno2,1,1) = '2'"; //sql문 세미콜론x
			
			stmt = conn.createStatement(); //SQL 실행준비 단계. Statement, PreparedStataement 두 객체를 주로 이용한다. 
										   //Connection에서 명령을 실행해줄 Statement 객체를 만든다.
			
			
			rs = stmt.executeQuery(sql); //Statement의 sql명령 결과를 ResultSet으로 받아서 실행한다. 
										 //select문일 경우 executeQuery()를 사용하며, DML문일 경우엔 executeUpdate()를 사용한다. 
			
			while(rs.next()) {
				String id = rs.getString("mem_id");
				String name = rs.getString("mem_name");
				String mileage = rs.getString("mem_mileage");
				
				System.out.println("-----------------------------");
				System.out.println(id + "\t" + name + "\t" + mileage);
			}
			
		}catch(ClassNotFoundException e) {	
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}

