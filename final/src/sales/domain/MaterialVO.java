package sales.domain;

public class MaterialVO {

	private String date;
	private String weather;
	private double tem;
	private String memo;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public double getTem() {
		return tem;
	}
	public void setTem(double tem) {
		this.tem = tem;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Override
	public String toString() {
		return "Material [date=" + date + ", weather=" + weather + ", tem=" + tem + ", memo=" + memo + "]";
	}
	
}
