package sales.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sales.domain.Sale_pVO;
import sales.domain.SalesVO;
import sales.domain.startVO;

public class Sale_pDAOImpl implements Sale_pDAO{

	private Connection con;
	private Sale_pVO vo;
	private startVO svo;
	String colName ="sale_date";
	String searchWord="";
	String searchWord2="";
	JTextField[] Text;
	
	public void setText(JTextField[] text) {
		Text = text;
	}
	
	public void setCon(Connection con) {
		this.con = con;
	}
	
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
	public void setSearchWord2(String searchWord2) {
		this.searchWord2 = searchWord2;
	}
	
	public void setColName(String colName) {
		this.colName = colName;
	}
	
	@Override
	public void create(Sale_pVO vo) {
		// TODO Auto-generated method stub
		
		String sql = "insert into sale_p values (?, ?, ?, ?)";
		
		try {
			java.sql.PreparedStatement pstmt = con.prepareStatement(sql);	// DB명령 쿼리로 변환
			pstmt.setString(1, vo.getSale_date());
			pstmt.setString(2, vo.getProduct_id());
			pstmt.setInt(3, vo.getS_count());
			pstmt.setInt(4, vo.getN_count());
			
			pstmt.executeUpdate();	//반환값이 resultSet인데 쿼리의 반환되는 값의 집합
			
			pstmt.close();
			con.close();	//db를 선점하고있는건 안좋음
			
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
	public Sale_pVO read(String isbn) {
		return null;
	}

	@Override
	public void update(Sale_pVO vo) {
		String sql = "update sale_p "
				+ "set sale_date=?, product_id=?, s_count=?, n_count=?"
				+ " where product_id = ? AND sale_date = ?";
		
		try {
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, vo.getSale_date());
		pstmt.setString(2, vo.getProduct_id());
		pstmt.setInt(3, vo.getS_count());
		pstmt.setInt(4, vo.getN_count());
		pstmt.setString(5, vo.getProduct_id());
		pstmt.setString(6, vo.getSale_date());
	
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(String product_id, String sale_date) {
		// TODO Auto-generated method stub
		String sql = "delete from sale_p"
				+ " where product_id=? AND sale_date = ?";
		
		try {
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, product_id);
		pstmt.setString(2, sale_date);
		
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Sale_pVO> searchList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sale_pVO> reads() {
		List<Sale_pVO> List = new ArrayList<Sale_pVO>();		//가져온 데이터를 저장하기위한
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			sql = "select * from sale_p"
					+ " where product_id like ? and sale_date like ?";
			
			pstmt = con.prepareStatement(sql);	// DB명령 쿼리로 변환
			
			pstmt.setString(1, "%" + searchWord + "%");
			pstmt.setString(2, "%" + searchWord2 + "%");
			
			rs = pstmt.executeQuery();		//반환값이 resultSet인데 쿼리의 반환되는 값의 집합
			
			Sale_pVO vo = null;
			
			while (rs.next()) {	//next는 다음 행으로 커서를 옮기는데 while안에서 검사하는건 커서가 가리키는게(데이터 행) 있는지 검사하는거
				vo = new Sale_pVO();	//데이터가있으면 저장하기위한 클래스인 BookVO형 객체 생성 
				vo.setSale_date(rs.getString("sale_date"));
				vo.setProduct_id(rs.getString("product_id"));
				vo.setS_count(rs.getInt("s_count"));
				vo.setN_count(rs.getInt("n_count"));
				
				List.add(vo);	//가져온 데이터를 저장
			}
			
			rs.close();
			con.close();	// DB를 선점하고있는건 안좋음
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return List;
	}

	@Override
	public List<startVO> readsB() {
		List<startVO> sList = new ArrayList<startVO>();
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd", Locale.KOREA );
		Date current = new Date();
		String today = mSimpleDateFormat.format(current);
		
		try {
			sql = "select b.sale_date, p.name, b.n_count, p.price_out, sum(b.s_count) - sum(b.n_count) as sale_count"
					+ " from sale_p b , product p"
					+ " where b.product_id = p.product_id and"
					+ " b.sale_date = ?"
					+ " group by p.name";
			
			pstmt = con.prepareStatement(sql);	// DB명령 쿼리로 변환
			pstmt.setString(1, today);
			rs = pstmt.executeQuery();		//반환값이 resultSet인데 쿼리의 반환되는 값의 집합
			
			startVO svo = null;
			
			while (rs.next()) {	//next는 다음 행으로 커서를 옮기는데 while안에서 검사하는건 커서가 가리키는게(데이터 행) 있는지 검사하는거
				svo = new startVO();	//데이터가있으면 저장하기위한 클래스인 BookVO형 객체 생성 
				svo.setSale_date(rs.getString("sale_date"));
				svo.setName(rs.getString("name"));
				svo.setN_count(rs.getInt("n_count"));
				svo.setPrice_out(rs.getInt("price_out"));
				svo.setSale_count(rs.getInt("sale_count"));
				
				sList.add(svo);	//가져온 데이터를 저장
			}
			
			rs.close();
			con.close();	// DB를 선점하고있는건 안좋음
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sList;
	}

	@Override
	public void updatestock(String name) {
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd", Locale.KOREA );
	      Date current = new Date();
	      String today = mSimpleDateFormat.format(current);
	      ResultSet rs = null;
	      int s;
	      
	      try {
	         String sql = "select b.name, p.n_count from product b, sale_p p "
	               + " where b.product_id = p.product_id and p.sale_date = ? and b.name = ?";
	            
	         PreparedStatement pstmt = con.prepareStatement(sql);
	            
	         pstmt.setString(1, today);
	         pstmt.setString(2, name);
	            
	         rs = pstmt.executeQuery();
	         rs.next();
	         if(rs.getInt("n_count") <= 0) {
	            rs.close();
	            con.close();
	            JOptionPane.showMessageDialog(null, "매진된 상품입니다.");
	         }else {
	         
	            sql = "update product b, sale_p p" + 
	                  " set p.n_count = n_count - 1" + 
	                  " where b.product_id = p.product_id" + 
	                  " and b.name = ?" + 
	                  " and p.sale_date = ?";
	            
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, name);
	            pstmt.setString(2, today);
	         
	            pstmt.executeUpdate();
	            pstmt.close();
	            con.close();
	         }
	      
	      }catch(SQLException e) {
	         e.printStackTrace();
	      }
		
	}

	public void calcu(){
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int range;
		String [] ids;
		int [] counts;
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd", Locale.KOREA );
		Date current = new Date();
		current.setDate(current.getDate());
	
		String today = mSimpleDateFormat.format(current);
		
		try {
			sql = "select * from sale_p" + 
				" where sale_date = ?";
			
			pstmt = con.prepareStatement(sql);	// DB명령 쿼리로 변환
			
			pstmt.setString(1, today);
			
			rs = pstmt.executeQuery();
			rs.last();	range = rs.getRow();	rs.first();
			ids = new String[range];	counts = new int[range];
			
			for (int i = 0; i < range; i++) {
				ids[i] = rs.getString("product_id");
				counts[i] = rs.getInt("n_count");
				System.out.println(ids[i]);
				rs.next();
			}
			
			rs.close();
			
			current.setDate(current.getDate()+1);
			today = mSimpleDateFormat.format(current);
			
			sql = "insert into sale_p values (?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			
			for (int i = 0; i < range; i++) {
				pstmt.setString(1, today);
				pstmt.setString(2, ids[i]);
				pstmt.setInt(3, counts[i]);
				pstmt.setInt(4, counts[i]);
				
				pstmt.executeUpdate();
			}
			
			pstmt.close();
			con.close();	// DB를 선점하고있는건 안좋음
			
		}catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "이미 정산을 완료했습니다.");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String anl() {
		int max = 0, count = 0, total = 0, total_s = 0, total_pro = 0;
		String best = "", anl = "";
		
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd", Locale.KOREA );
		Date current = new Date();
		current.setDate(current.getDate());
		String today = mSimpleDateFormat.format(current);
		
		try {
			sql = "select b.name, p.s_count - p.n_count as count, (p.s_count - p.n_count) * b.price_out as sale,"
				+ " (p.s_count - p.n_count) * b.price_out - (p.s_count - p.n_count) * b.price_in as total_pro"
				+ " from product b, sale_p p"
				+ " where b.product_id = p.product_id and p.sale_date = ?";
			
			pstmt = con.prepareStatement(sql);	// DB명령 쿼리로 변환
			
			pstmt.setString(1, today);
			
			rs = pstmt.executeQuery();		//반환값이 resultSet인데 쿼리의 반환되는 값의 집합
			
			while (rs.next()) {	//next는 다음 행으로 커서를 옮기는데 while안에서 검사하는건 커서가 가리키는게(데이터 행) 있는지 검사하는거
				if(count < rs.getInt("count")) {
					best = rs.getString("b.name");
					max = rs.getInt("count");
				}
				total = total + rs.getInt("count");
				total_s = total_s + rs.getInt("sale");
				System.out.println(rs.getInt("sale"));
				total_pro = total_pro + rs.getInt("total_pro");
				System.out.println(rs.getInt("total_pro"));
			}
			
			rs.close();
			con.close();	// DB를 선점하고있는건 안좋음
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		anl = "인기 메뉴 : " + best + "     판매량 : " + String.valueOf(max) + "     총 판매량 : " + String.valueOf(total) + "     총 매출 : " + String.valueOf(total_s)
				+ "     순 이익 : " + String.valueOf(total_pro);
		
		return anl;
	}
	
}
