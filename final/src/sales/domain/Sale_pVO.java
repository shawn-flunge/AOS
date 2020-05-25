package sales.domain;

public class Sale_pVO {

	private String sale_date;
	private String product_id;
	private int n_count;
	private int s_count;
	
	public String getSale_date() {
		return sale_date;
	}
	public void setSale_date(String sale_date) {
		this.sale_date = sale_date;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public int getN_count() {
		return n_count;
	}
	public void setN_count(int n_count) {
		this.n_count = n_count;
	}
	public int getS_count() {
		return s_count;
	}
	public void setS_count(int s_count) {
		this.s_count = s_count;
	}
	
	@Override
	public String toString() {
		return "Sale_pVO [sale_date=" + sale_date + ", product_id=" + product_id + ", n_count=" + n_count + ", s_count="
				+ s_count + "]";
	}
	
	
}
