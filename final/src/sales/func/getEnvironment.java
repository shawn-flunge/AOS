package sales.func;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class getEnvironment {

	
	 private String rssFeed = "http://www.kma.go.kr/wid/queryDFS.jsp?gridx=%s&gridy=%s"; //주소
	 public int rs = 0;			//날씨
	 public static String hoho="";
	 String temp;						//온도
	 
	public String getTemp() {
		return temp;
	}
	
	public int getRs() {
		return rs;
	}
	 
	 public List<Map<String, String>> getTownForecast(String x, String y){
		 
		 List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		 
		 Date date = new Date();
	     Calendar calendar = GregorianCalendar.getInstance();
	     calendar.setTime(date);   
	     calendar.get(Calendar.HOUR_OF_DAY);
			
		 Calendar rightNow = Calendar.getInstance();
		 int hour = rightNow.get(Calendar.HOUR_OF_DAY);
		 
		 System.out.println(hour);        
	     try {
	     
            SAXBuilder parser = new SAXBuilder();        
            parser.setIgnoringElementContentWhitespace(true);
		 
            String url = String.format(rssFeed, x, y);
            Document doc = parser.build(url);
            Element root = doc.getRootElement();
	            
            Element channel = root.getChild("body");
            List<Element> list = channel.getChildren("data");
	           
            Map<String, String> data = new LinkedHashMap<String, String>();
	           
            Element el = (Element)list.get(0);
	            
	           
            data.put("시간",el.getChildTextTrim("hour") );     

            data.put("온도",el.getChildTextTrim("temp") );    
	        
			data.put("pty",el.getChildTextTrim("pty") );  
            data.put("날씨",el.getChildTextTrim("wfKor") ); 

            System.out.println(data);
            result.add(data);
	           
	         
            String haha; 
            String weathers;		
	            
	            // 날씨 가져오는 방법
            haha = data.get("날씨");
	            // 온도 가져오는 방법
            temp = data.get("온도");
            System.out.println(temp);
	           
	            
            if(haha.equals("맑음"))
            {
            	hoho = "imgs/weather1.png";

            }
	        if(haha.equals("구름 많음"))
	        {
	            hoho = "imgs/weather3.png";

	        }
	        if(haha.equals("구름 조금"))
	        {
	            hoho = "imgs/weather2.png";

	        }
	        if(haha.equals("흐림"))
	        {
	            hoho = "imgs/weather4.png";
	        }
	            
	        weathers = data.get("pty");
	            
	        if(weathers.equals("1")) {
            	hoho = "imgs/weather5.png";
            	rs = 1;
            }
            if(weathers.equals("2")) {
            	hoho = "imgs/weather5.png";
            	rs = 1;
            }
            if(weathers.equals("3")) {
            	hoho = "imgs/weather6.png";
            	rs = 2;
            }
            if(weathers.equals("4")) {
            	hoho = "imgs/weather6.png";
            	rs = 2;
            }
            //////////////
            
            
            
            //하루 지나면 초기화
            if(hour == 0) {
            	rs = 0;
            }
            
        
	        System.out.println(haha);
	        } catch (JDOMException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	      return result;
	  }
		 
	
	
	
	
	
	
	
	
}
