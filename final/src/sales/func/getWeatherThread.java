package sales.func;

import sales.view.DefaultPanel;

public class getWeatherThread extends Thread {


	DefaultPanel dp;
	
	public getWeatherThread(DefaultPanel dp){
		this.dp = dp;
	}
	


	@Override
	public void run() {
	while(true)
	{
		getEnvironment ge = new getEnvironment();
		dp.result=ge.getTownForecast("57", "125");
		
		
		dp.str1 = dp.result.get(0).get("�ð�").toString();
		dp.str2 = dp.result.get(0).get("�µ�").toString();
		dp.str3 = dp.result.get(0).get("pty").toString();
		dp.str4 = dp.result.get(0).get("����").toString();
		
		
//		if(str3.equals("1")) {
//			str4 = "��";
//		}
//		if(str3.equals("2")) {
//			str4 = "��";
//		}
//		if(str3.equals("3")) {
//			str4 = "��";
//		}
//		if(str3.equals("4")) {
//			str4 = "��";
//		}
//		

		dp.tmp.setText("�µ� : " +dp.str2);
		dp.weather.setText("���� : " +dp.str4);
		dp.img.setIcon(dp.icon);
		
	
		try{
			sleep(10000);
			
		}catch(InterruptedException e) {
			
		}
	}
	}
}
