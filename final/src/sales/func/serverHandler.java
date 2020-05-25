package sales.func;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import sales.connector.DBConnection;
import sales.controller.salesController;
import sales.domain.MaterialVO;
import sales.persistence.MaterialDAOImpl;


public class serverHandler implements ActionListener {

	salesController sc;
	Socket socket;
	MaterialDAOImpl mdao = new MaterialDAOImpl();
	MaterialVO mvo = new MaterialVO();
	getEnvironment gE;
	int rs;
	double temp;
	String weather="";
	List<Map<String, String>> result = new ArrayList<Map<String, String>>();
	
	
	public serverHandler(salesController sc) {
		super();
		this.sc = sc;
		this.socket =sc.getSocket();
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd", Locale.KOREA );
		Date current = new Date();
		current.setDate(current.getDate());
	
		String today = mSimpleDateFormat.format(current);
		
         
         if(e.getSource()==sc.m.getDp().btn4)
         {
        	Connection con = DBConnection.getCon();
        	
        	mdao.setCon(con);
        	
        	gE = new getEnvironment();
			
			result= gE.getTownForecast("57", "125");
			
			
			temp = Double.parseDouble(result.get(0).get("온도").toString());
			weather = result.get(0).get("날씨").toString();
			
        	if(rs == 0) {
        		weather = "맑음";
        	}else if(rs == 1) {
        		weather = "비";
        	}else if(rs == 2) {
        		weather = "눈";
        	}
        	
        	mvo.setDate(today);
        	mvo.setWeather(weather);
        	mvo.setTem(temp);
        	mvo.setMemo("특이사항 없음");
        	 
        	mdao.create(mvo);
        	
        	sc.server= new serverThread(true);
     		sc.server.start();
     		
     		try {
     			sc.socket = new Socket("127.0.0.1", 1369);
     		} catch (IOException e1) {
     			// TODO Auto-generated catch block
     			e1.printStackTrace();
     		}
              new clientThread(sc);
              
              
         }
         else if(e.getSource()==sc.m.getDp().btn5)
         {
//        	 sc.wt.sendMsg();
        	 sc.sendWarn("여기다가 집어넣으셈");
        	 //System.out.println("afafafa");
        	 
         }
		
		

	}

}
