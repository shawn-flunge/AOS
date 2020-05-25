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
	List<Sale_pVO> searchList();	//select ������� �������� ��,����� arraylist���� ������ list�ι���
	List<Sale_pVO> reads();
	List<startVO> readsB();
	void delete(String product_id, String sale_date);
	void updatestock(String name);
	void calcu();
	String anl();
}
