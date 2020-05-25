package sales.domain;

public class SalesVO {

	private String product_id;
	private String name;
	private int price_in;
	private int price_out;
	
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice_in() {
		return price_in;
	}
	public void setPrice_in(int price_in) {
		this.price_in = price_in;
	}
	public int getPrice_out() {
		return price_out;
	}
	public void setPrice_out(int price_out) {
		this.price_out = price_out;
	}
	
	@Override
	public String toString() {
		return "SalesVO [product_id=" + product_id + ", name=" + name + ", price_in=" + price_in + ", price_out="
				+ price_out + "]";
	}
	
}
