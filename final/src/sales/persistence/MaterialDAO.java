package sales.persistence;

import java.util.List;

import sales.domain.MaterialVO;

public interface MaterialDAO {

	void create(MaterialVO vo);	//create
	MaterialVO read(String isbn);	//select
	void update(MaterialVO vo);	//update
	void delete(String isbn);	//delete
	List<MaterialVO> searchList();	//select ������� �������� ��,����� arraylist���� ������ list�ι���
	List<MaterialVO> reads();
}
