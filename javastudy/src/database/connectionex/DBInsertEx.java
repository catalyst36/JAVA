package database.connectionex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DBInsertEx {

	public static void main(String[] args) {
		
		String driver = "oracle.jdbc.driver.OracleDriver";		//jdbc 고정 문자열 (암기)
		String url = "jdbc:oracle:thin:@localhost:1521:xe";  		//localhost부분은 서버로 사용할 컴퓨터 아이피 , xe 는 사용하고있는 db의 sid
		String user = "ddit";			//db 접속 아이디
		String password = "java";		//db 접속 비밀번호
		
		ResultSet rs = null;			//sql실행 후 select 결과를 저장하는 객체
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into lprod(lprod_id, lprod_gu, lprod_nm)");
		sql.append("values(seq_lprod_id.nextval,?,?)"); // ? ?  : 1번 2번
		
		//try with resource 문 사용으로 자원 자동 반환
		try(Connection conn = DriverManager.getConnection(url,user,password);	
			Statement stmt = conn.createStatement();		
			PreparedStatement pstmt = conn.prepareStatement(sql.toString()); 
			Scanner sc = new Scanner(System.in);){
			
			System.out.print("분류코드(exP501) 입력 : ");
			String gid = sc.nextLine();
			System.out.print("분류명 입력 : ");
			String gname = sc.nextLine();
			
			pstmt.setString(1, gid);		//pstmt 의 1번 ? 에 gid 입력
			pstmt.setString(2, gname);		//pstmt 의 2번 ? 에 gname 입력
			
			int res = pstmt.executeUpdate();	//pstmt 쿼리 실행 결과 저장 (insert문은 실패시 0 반환)
			
			if(res==0)
				System.out.println("입력 실패");
			else
				System.out.println("자료추가 성공");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}