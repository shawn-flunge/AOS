package sales.persistence;

import java.util.List;

import sales.domain.Sale_pVO;
import sales.domain.SalesVO;
import sales.domain.startVO;

public interface Sale_pDAO {

	void create(Sale_pVO vo);	//create
	Sale_pVO read(String isbn);	//select
	void update(Sale_pVO vo);	//update
	//void delete(String isbn);	//delete
	List<Sale_pVO> searchList();	//select 결과행이 여러개일 때,사용은 arraylist지만 리턴은 list로받음
	List<Sale_pVO> reads();
	List<startVO> readsB();
	void delete(String product_id, String sale_date);
	void updatestock(String name);
	void calcu();
	String anl();
}
