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
			System.out.println("���󿬰�");
			
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver�� ã�� ���մϴ�.");	//jdbc����̹��� ã�� ���Ҷ�
		} catch (SQLException e) {
			System.out.println("mysql������ ������ �ȵ� ���� url,username,password�� �߸��Ǿ��� �̴ϴ�");
			//getconnection ȣ���Ҷ�, mysql������ ������ �ȵ� ���� url,username,password�� �߸��Ǿ��� ��
		}
		
		
		
		return con;
	}
	
	
}
