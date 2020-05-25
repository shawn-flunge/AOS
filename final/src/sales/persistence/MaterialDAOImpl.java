package sales.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sales.domain.MaterialVO;
import sales.domain.SalesVO;

public class MaterialDAOImpl implements MaterialDAO {

	private Connection con;
	private SalesVO vo;
	String colName ="date";
	String searchWord="";
	JTextField[] Text;
	
	public void setText(JTextField[] text) {
		Text = text;
	}
	
	public void setCon(Connection con) {
		this.con = con;
	}
	
	public void setColName(String colName) {
		this.colName = colName;
	}
	
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
	@Override
	public void create(MaterialVO vo) {
		 String sql = "insert into material values (?, ?, ?, ?)";
	      
	      try {
	         java.sql.PreparedStatement pstmt = con.prepareStatement(sql);   // DB��� ������ ��ȯ
	         pstmt.setString(1, vo.getDate());
	         pstmt.setString(2, vo.getWeather());
	         pstmt.setDouble(3, vo.getTem());
	         pstmt.setString(4, vo.getMemo());
	         
	         pstmt.executeUpdate();   //��ȯ���� resultSet�ε� ������ ��ȯ�Ǵ� ���� ����
	         
	         pstmt.close();
	         con.close();   //db�� �����ϰ��ִ°� ������
	         
	      } catch(SQLIntegrityConstraintViolationException i) {
	         i.printStackTrace();
	         JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ��ǰ�ڵ��Դϴ�.");
	      }
	      catch (SQLException e) {
	         // TODO Auto-generated catch block   
	         e.printStackTrace();
	      }

	}

	@Override
	public MaterialVO read(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(MaterialVO vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String isbn) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MaterialVO> searchList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MaterialVO> reads() {
		// TODO Auto-generated method stub
		return null;
	}

}
