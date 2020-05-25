package sales.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sales.connector.DBConnection;
import sales.domain.SalesVO;



public class SalesDAOImpl implements SalesDAO {

	private Connection con;
	private SalesVO vo;
	String colName ="name";
	String searchWord="";
	String searchWord2="";
	JTextField[] Text;
	

	public void setText(JTextField[] Text) {
		this.Text = Text;
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
	
	public SalesVO getVo() {
		return vo;
	}
	
	public void setSearchWord2(String searchWord2) {
		this.searchWord2 = searchWord2;
	}
	
	

	@Override
	public void create(SalesVO vo) {
		// TODO Auto-generated method stub
		
		String sql = "insert into product values (?, ?, ?, ?)";
		
		try {
			java.sql.PreparedStatement pstmt = con.prepareStatement(sql);	// DB명령 쿼리로 변환
			pstmt.setString(1, vo.getProduct_id());
			pstmt.setString(2, vo.getName());
			pstmt.setInt(3, vo.getPrice_in());
			pstmt.setInt(4, vo.getPrice_out());
			
			pstmt.executeUpdate();	//반환값이 resultSet인데 쿼리의 반환되는 값의 집합
			
			pstmt.close();
			con.close();	//db를 선점하고있는건 안좋음
			
		} catch(SQLIntegrityConstraintViolationException i) {
			JOptionPane.showMessageDialog(null, "이미 존재하는 물품코드입니다.");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public SalesVO read(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SalesVO vo) {
		String sql = "update product set name=?, price_in=?, price_out=?"
				+ " where product_id = ?";
		
		try {
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, vo.getName());
		pstmt.setInt(2, vo.getPrice_in());
		pstmt.setInt(3, vo.getPrice_out());
		pstmt.setString(4, vo.getProduct_id());
		
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(String product_id) {
		String sql = "delete from product"
				+ " where product_id=?";
		
		try {
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, product_id);
		
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<SalesVO> searchList() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<SalesVO> reads() {

		List<SalesVO> List = new ArrayList<SalesVO>();		//가져온 데이터를 저장하기위한
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			sql = "select * from product"
					+ " where product_id like ? and name like ?";
			
			pstmt = con.prepareStatement(sql);	// DB명령 쿼리로 변환
			
			pstmt.setString(1, "%" + searchWord + "%");
			pstmt.setString(2, "%" + searchWord2 + "%");
			
			rs = pstmt.executeQuery();		//반환값이 resultSet인데 쿼리의 반환되는 값의 집합
			
			SalesVO vo = null;
			
			while (rs.next()) {	//next는 다음 행으로 커서를 옮기는데 while안에서 검사하는건 커서가 가리키는게(데이터 행) 있는지 검사하는거
				vo = new SalesVO();	//데이터가있으면 저장하기위한 클래스인 BookVO형 객체 생성 
				vo.setProduct_id(rs.getString("product_id"));
				vo.setName(rs.getString("name"));
				vo.setPrice_in(rs.getInt("price_in"));
				vo.setPrice_out(rs.getInt("price_out"));
				
				List.add(vo);	//가져온 데이터를 저장
			}
			
			rs.close();
			con.close();	// DB를 선점하고있는건 안좋음
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return List;
		
	}

	@Override
	public void delete(String product_id, String date) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
