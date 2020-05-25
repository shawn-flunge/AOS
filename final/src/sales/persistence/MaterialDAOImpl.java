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
	         java.sql.PreparedStatement pstmt = con.prepareStatement(sql);   // DB명령 쿼리로 변환
	         pstmt.setString(1, vo.getDate());
	         pstmt.setString(2, vo.getWeather());
	         pstmt.setDouble(3, vo.getTem());
	         pstmt.setString(4, vo.getMemo());
	         
	         pstmt.executeUpdate();   //반환값이 resultSet인데 쿼리의 반환되는 값의 집합
	         
	         pstmt.close();
	         con.close();   //db를 선점하고있는건 안좋음
	         
	      } catch(SQLIntegrityConstraintViolationException i) {
	         i.printStackTrace();
	         JOptionPane.showMessageDialog(null, "이미 존재하는 물품코드입니다.");
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
