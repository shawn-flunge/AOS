package sales.func;

import java.util.Calendar;

import sales.view.DefaultPanel;

public class TimeThread extends Thread {


	DefaultPanel dp;
	
	public TimeThread(DefaultPanel dp){
		this.dp = dp;
	}
	
	

	@Override
	public void run() {
		int year,month,date;
		int hour,min,sec;
		Calendar cal;
	while(true)
	{
		cal=Calendar.getInstance();
		year=cal.get(Calendar.YEAR);
		month=cal.get(Calendar.MONTH)+1;
		date=cal.get(Calendar.DATE);
		hour=cal.get(Calendar.HOUR_OF_DAY);
		min=cal.get(Calendar.MINUTE);
		sec=cal.get(Calendar.SECOND);
		
		dp.ttimer.setText(year+"��"+month+"��"+date+"��"+hour+"��"+min+"��"+sec+"��");
		
		try{
			sleep(1000);		
		}catch(InterruptedException e) {
			
		}
	}
	}
}
