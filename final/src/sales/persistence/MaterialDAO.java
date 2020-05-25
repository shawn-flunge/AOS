package sales.persistence;

import java.util.List;

import sales.domain.MaterialVO;

public interface MaterialDAO {

	void create(MaterialVO vo);	//create
	MaterialVO read(String isbn);	//select
	void update(MaterialVO vo);	//update
	void delete(String isbn);	//delete
	List<MaterialVO> searchList();	//select 결과행이 여러개일 때,사용은 arraylist지만 리턴은 list로받음
	List<MaterialVO> reads();
}
