package sales.persistence;

import java.util.List;


import sales.domain.SalesVO;


public interface SalesDAO {

	
	void create(SalesVO vo);	//create
	SalesVO read(String isbn);	//select
	void update(SalesVO vo);	//update
	void delete(String isbn);	//delete
	List<SalesVO> searchList();	//select 결과행이 여러개일 때,사용은 arraylist지만 리턴은 list로받음
	List<SalesVO> reads();
	void delete(String product_id, String date);
	
}
