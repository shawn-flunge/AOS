package suply.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnenction {

	
static Connection con = null;
	
	public static Connection getCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://3.134.94.24:3306/javadb?serverTimezone=UTC";
			String user = "javaid";
			String pwd = "1234";
			
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("정상연결");
			
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver를 찾지 못합니다.");	//jdbc드라이버를 찾지 못할때
		} catch (SQLException e) {
			System.out.println("mysql서버가 실행이 안될 때나 url,username,password가 잘못되었을 겁니다");
			//getconnection 호출할때, mysql서버가 실행이 안될 때나 url,username,password가 잘못되었을 때
		}
		
		
		
		return con;
	}
	
	
}
