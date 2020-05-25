package sales.domain;

public class startVO {
	private String sale_date;
	private String name;
	private int n_count;
	private int price_out;
	private int sale_count;
	
	public String getSale_date() {
		return sale_date;
	}
	
	public void setSale_date(String sale_date) {
		this.sale_date = sale_date;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getN_count() {
		return n_count;
	}
	
	public void setN_count(int n_count) {
		this.n_count = n_count;
	}
	
	public int getPrice_out() {
		return price_out;
	}
	
	public void setPrice_out(int price_out) {
		this.price_out = price_out;
	}

	public int getSale_count() {
		return sale_count;
	}

	public void setSale_count(int sale_count) {
		this.sale_count = sale_count;
	}

	@Override
	public String toString() {
		return "startVO [sale_date=" + sale_date + ", name=" + name + ", n_count=" + n_count + ", price_out="
				+ price_out + ", sale_count=" + sale_count + "]";
	}
	
	
}
