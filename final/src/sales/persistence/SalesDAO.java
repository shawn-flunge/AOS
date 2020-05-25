package sales.persistence;

import java.util.List;


import sales.domain.SalesVO;


public interface SalesDAO {

	
	void create(SalesVO vo);	//create
	SalesVO read(String isbn);	//select
	void update(SalesVO vo);	//update
	void delete(String isbn);	//delete
	List<SalesVO> searchList();	//select ������� �������� ��,����� arraylist���� ������ list�ι���
	List<SalesVO> reads();
	void delete(String product_id, String date);
	
}
